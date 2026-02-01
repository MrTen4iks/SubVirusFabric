package com.subworld.subvirus.world.blocks;

import com.subworld.subvirus.SubVirus;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UnknownPortalBlock extends Block {
    private static final RegistryKey<World> INFECTED_WORLD_KEY =
            RegistryKey.of(RegistryKeys.WORLD, Identifier.of(SubVirus.MOD_ID, "infected"));

    public UnknownPortalBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler) {
        if (world.isClient() || !(entity instanceof ServerPlayerEntity player)) {
            return;
        }

        MinecraftServer server = world.getServer();
        if (server == null) {
            return;
        }

        boolean isInInfected = world.getRegistryKey().equals(INFECTED_WORLD_KEY);
        ServerWorld targetWorld = server.getWorld(isInInfected ? World.OVERWORLD : INFECTED_WORLD_KEY);
        if (targetWorld == null) {
            return;
        }

        BlockPos spawn = targetWorld.getSpawnPos();
        player.teleport(targetWorld, spawn.getX() + 0.5D, spawn.getY() + 1.0D, spawn.getZ() + 0.5D, java.util.Set.of(), player.getYaw(), player.getPitch(), true);
    }
}
