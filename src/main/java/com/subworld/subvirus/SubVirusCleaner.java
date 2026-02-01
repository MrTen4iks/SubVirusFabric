package com.subworld.subvirus;

import com.subworld.subvirus.registry.SubBlocks;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.lang.reflect.Method;

import static net.minecraft.server.command.CommandManager.literal;

public final class SubVirusCleaner {
    private static final int RADIUS = 20;
    private static final int INTERVAL_TICKS = 200;
    private static final String PERMISSION_NODE = "subvirus.subscan";
    private static final RegistryKey<World> INFECTED_WORLD_KEY =
            RegistryKey.of(RegistryKeys.WORLD, Identifier.of(SubVirus.MOD_ID, "infected"));

    private static boolean enabled = false;
    private static int tickCounter = 0;

    private SubVirusCleaner() {
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("subon")
                    .requires(SubVirusCleaner::hasAccess)
                    .executes(ctx -> {
                        enabled = true;
                        ctx.getSource().sendFeedback(() -> Text.literal("SubVirus scan ON"), true);
                        return 1;
                    }));

            dispatcher.register(literal("suboff")
                    .requires(SubVirusCleaner::hasAccess)
                    .executes(ctx -> {
                        enabled = false;
                        ctx.getSource().sendFeedback(() -> Text.literal("SubVirus scan OFF"), true);
                        return 1;
                    }));
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter++;
            if (tickCounter < INTERVAL_TICKS) {
                return;
            }
            tickCounter = 0;
            runScan(server);
        });
    }

    private static void runScan(MinecraftServer server) {
        if (!enabled) {
            return;
        }
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            ServerWorld world = (ServerWorld) player.getWorld();
            if (!world.getRegistryKey().equals(INFECTED_WORLD_KEY)) {
                continue;
            }
            int replaced = scanAroundPlayer(player, world);
            if (replaced > 0) {
                broadcastToAdmins(server, Text.literal("SubVirus scan: replaced " + replaced + " blocks near " + player.getName().getString()));
            }
        }
    }

    private static int scanAroundPlayer(ServerPlayerEntity player, ServerWorld world) {
        BlockPos center = player.getBlockPos();
        int minX = center.getX() - RADIUS;
        int maxX = center.getX() + RADIUS;
        int minY = center.getY() - RADIUS;
        int maxY = center.getY() + RADIUS;
        int minZ = center.getZ() - RADIUS;
        int maxZ = center.getZ() + RADIUS;

        int replacedCount = 0;
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    mutablePos.set(x, y, z);
                    if (world.getChunkManager().getWorldChunk(x >> 4, z >> 4) == null) {
                        continue;
                    }
                    BlockState state = world.getBlockState(mutablePos);
                    BlockState replacement = getReplacement(state);
                    if (replacement != null && replacement != state) {
                        world.setBlockState(mutablePos, replacement, Block.NOTIFY_ALL);
                        replacedCount++;
                    }
                }
            }
        }
        return replacedCount;
    }

    private static BlockState getReplacement(BlockState state) {
        if (state.isOf(Blocks.DIRT)) {
            return SubBlocks.UNCERTAIN_DIRT.block().getDefaultState();
        }
        if (state.isOf(Blocks.GRASS_BLOCK)) {
            return SubBlocks.UNCERTAIN_GRASS_BLOCK.block().getDefaultState();
        }
        if (state.isOf(Blocks.DIRT_PATH)) {
            return SubBlocks.UNCERTAIN_DIRT_PATH.block().getDefaultState();
        }
        if (state.isOf(Blocks.OAK_LOG)) {
            BlockState target = SubBlocks.UNCERTAIN_LOG.block().getDefaultState();
            return copySharedProperties(state, target);
        }
        if (state.isOf(Blocks.OAK_LEAVES)) {
            BlockState target = SubBlocks.UNCERTAIN_LEAVES.block().getDefaultState();
            target = copySharedProperties(state, target);
            if (target.contains(LeavesBlock.PERSISTENT)) {
                target = target.with(LeavesBlock.PERSISTENT, true);
            }
            if (target.contains(LeavesBlock.DISTANCE)) {
                target = target.with(LeavesBlock.DISTANCE, LeavesBlock.MAX_DISTANCE);
            }
            return target;
        }
        return null;
    }

    private static BlockState copySharedProperties(BlockState from, BlockState to) {
        for (Property<?> property : from.getProperties()) {
            if (to.contains(property)) {
                to = copyProperty(from, to, property);
            }
        }
        return to;
    }

    private static <T extends Comparable<T>> BlockState copyProperty(BlockState from, BlockState to, Property<T> property) {
        return to.with(property, from.get(property));
    }

    private static boolean hasAccess(ServerCommandSource source) {
        if (source.hasPermissionLevel(2)) {
            return true;
        }
        return checkPermissionApi(source);
    }

    private static void broadcastToAdmins(MinecraftServer server, Text message) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            if (hasAccess(player.getCommandSource())) {
                player.sendMessage(message, false);
            }
        }
    }

    private static boolean checkPermissionApi(ServerCommandSource source) {
        try {
            Class<?> permissions = Class.forName("me.lucko.fabric.api.permissions.v0.Permissions");
            try {
                Method check = permissions.getMethod("check", ServerCommandSource.class, String.class);
                return (boolean) check.invoke(null, source, PERMISSION_NODE);
            } catch (NoSuchMethodException ignored) {
                Method check = permissions.getMethod("check", ServerCommandSource.class, String.class, int.class);
                return (boolean) check.invoke(null, source, PERMISSION_NODE, 2);
            }
        } catch (ClassNotFoundException e) {
            return false;
        } catch (ReflectiveOperationException e) {
            SubVirus.LOGGER.warn("Failed to query permissions API for {}", PERMISSION_NODE, e);
            return false;
        }
    }
}
