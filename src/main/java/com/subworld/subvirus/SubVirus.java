package com.subworld.subvirus;


import com.subworld.subvirus.registry.*;
import com.subworld.subvirus.world.biomes.SubBiom;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;


public class SubVirus implements ModInitializer {
    public static final String MOD_ID = "subvirus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



    @Override
    public void onInitialize() {
        SubEffects.registerModEffects();
        SubEntities.registerModEntities();
        SubItems.registerModItems();
        SubBlocks.registerModBlocks();
        SubProcessors.registerModProcessors();
        //SubArmors.registerModArmors();
        SubComponents.initialize();
        SubBiom.registerModBiomes();

       // SubFood.registerModFoods();
       // var ignore = SubPotion.UNKNOWN_INFECTION_POTION;
        SubPotions.registerModPotions();

        SubItemGroups.registerModTabs();
        SubVirusCleaner.register();

        UseBlockCallback.EVENT.register((player, world, hand, hit) -> {
            if (world.isClient()) {
                return net.minecraft.util.ActionResult.PASS;
            }

            ItemStack stack = player.getStackInHand(hand);
            BlockPos pos = hit.getBlockPos();
            BlockState state = world.getBlockState(pos);

            if (state.isOf(Blocks.END_PORTAL_FRAME) && stack.isOf(SubItems.INFECTED_DUST)) {
                BlockState next = SubBlocks.UNKNOWN_PORTAL_FRAME.block().getDefaultState()
                        .with(EndPortalFrameBlock.FACING, state.get(EndPortalFrameBlock.FACING))
                        .with(EndPortalFrameBlock.EYE, false);
                world.setBlockState(pos, next, 11);
                world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                return net.minecraft.util.ActionResult.SUCCESS;
            }

            if (state.isOf(Blocks.END_PORTAL_FRAME) && !state.get(EndPortalFrameBlock.EYE)
                    && stack.isOf(SubItems.UNCERTAIN_EYE)) {
                BlockState next = state.with(EndPortalFrameBlock.EYE, true);
                world.setBlockState(pos, next, 11);
                world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                tryCreateUnknownPortal(world, pos);
                return net.minecraft.util.ActionResult.SUCCESS;
            }

            if (state.isOf(SubBlocks.UNKNOWN_PORTAL_FRAME.block()) && !state.get(EndPortalFrameBlock.EYE)
                    && stack.isOf(SubItems.UNCERTAIN_EYE)) {
                BlockState next = state.with(EndPortalFrameBlock.EYE, true);
                world.setBlockState(pos, next, 11);
                world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                tryCreateUnknownPortal(world, pos);
                return net.minecraft.util.ActionResult.SUCCESS;
            }

            if (state.isOf(SubBlocks.UNKNOWN_PORTAL_FRAME.block()) && !state.get(EndPortalFrameBlock.EYE)
                    && stack.isOf(Items.ENDER_EYE)) {
                BlockState next = state.with(EndPortalFrameBlock.EYE, true);
                world.setBlockState(pos, next, 11);
                world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                tryCreateUnknownPortal(world, pos);
                return net.minecraft.util.ActionResult.SUCCESS;
            }

            if (!stack.isIn(ItemTags.SHOVELS)) {
                return net.minecraft.util.ActionResult.PASS;
            }

            if (!state.isOf(SubBlocks.UNCERTAIN_GRASS_BLOCK.block())) {
                return net.minecraft.util.ActionResult.PASS;
            }

            if (!SubBlocks.UNCERTAIN_DIRT_PATH.block().getDefaultState().canPlaceAt(world, pos)) {
                return net.minecraft.util.ActionResult.PASS;
            }

            world.setBlockState(pos, SubBlocks.UNCERTAIN_DIRT_PATH.block().getDefaultState(), 11);
            world.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

            if (!player.isCreative()) {
                stack.damage(1, player, LivingEntity.getSlotForHand(hand));
            }

            return net.minecraft.util.ActionResult.SUCCESS;
        });
        /*final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(SubVirus.MOD_ID, "sub_group"));
        final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
                .icon(() -> new ItemStack(SubItems.INFECTED_INGOT))
                .displayName(Text.translatable(MOD_ID+".group_trans"))
                .build();
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(SubItems.INFECTED_INGOT);
            itemGroup.add(SubItems.INFECTED_RAW);
            itemGroup.add(SubItems.INFECTED_DUST);
            itemGroup.add(SubItems.INFECTED_STICK);
            itemGroup.add(SubBlocks.INFECTED_ORE.asItem());

            itemGroup.add(SubBlocks.UNCERTAIN_BLOCK.asItem());
            itemGroup.add(SubBlocks.UNCERTAIN_LOG.asItem());
            itemGroup.add(SubBlocks.UNCERTAIN_PLANKS.asItem());
            itemGroup.add(SubItems.Tools.UNCERTAIN_PICKAXE);
            itemGroup.add(SubItems.Tools.UNCERTAIN_SWORD);

            itemGroup.add(SubItems.Armors.HAZMAT_SUIT_BOOTS);
            itemGroup.add(SubItems.Armors.HAZMAT_SUIT_LEGGINGS);
            itemGroup.add(SubItems.Armors.HAZMAT_SUIT_CHESTPLATE);
            itemGroup.add(SubItems.Armors.HAZMAT_SUIT_HELMET);
            itemGroup.add(SubItems.Armors.HAZMAT_SUIT_PACK);

            itemGroup.add(SubItems.Foods.PANACEA_STEW);
            itemGroup.add(PotionContentsComponent.createStack(Items.POTION, SubPotions.UNKNOWN_INFECTION_POTION));
            itemGroup.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SubPotions.UNKNOWN_INFECTION_POTION));
            itemGroup.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SubPotions.UNKNOWN_INFECTION_POTION));
            itemGroup.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SubPotions.UNKNOWN_INFECTION_POTION));

            itemGroup.add(PotionContentsComponent.createStack(Items.POTION, SubPotions.UNKNOWNS));
            itemGroup.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SubPotions.UNKNOWNS));
            itemGroup.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SubPotions.UNKNOWNS));
            itemGroup.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SubPotions.UNKNOWNS));
            itemGroup.add(PotionContentsComponent.createStack(Items.POTION, SubPotions.PANACEA));

            itemGroup.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SubPotions.PANACEA));
            itemGroup.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SubPotions.PANACEA));
            itemGroup.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SubPotions.PANACEA));

        });*/


    }

    private static void tryCreateUnknownPortal(net.minecraft.world.World world, BlockPos pos) {
        int y = pos.getY();
        boolean created = false;
        for (int x0 = pos.getX() - 4; x0 <= pos.getX() + 1; x0++) {
            for (int z0 = pos.getZ() - 4; z0 <= pos.getZ() + 1; z0++) {
                if (!isUnknownPortalFrameRing(world, y, x0, z0) && !isUnknownPortalFrameRingLoose(world, y, x0, z0)) {
                    continue;
                }
                for (int x = x0; x <= x0 + 2; x++) {
                    for (int z = z0; z <= z0 + 2; z++) {
                        world.setBlockState(new BlockPos(x, y, z), SubBlocks.UNKNOWN_PORTAL.block().getDefaultState(), 3);
                    }
                }
                world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                created = true;
                break;
            }
            if (created) {
                break;
            }
        }
        if (!created) {
            created = tryCreateUnknownPortalByBounds(world, y, pos);
        }
        if (!created) {
            int withEye = countPortalFramesWithEye(world, pos);
            LOGGER.info("Unknown portal not created at {} (frames with eye in 5x5 area: {})", pos, withEye);
        }
    }

    private static boolean isUnknownPortalFrameRing(net.minecraft.world.World world, int y, int x0, int z0) {
        for (int dx = -1; dx <= 3; dx++) {
            for (int dz = -1; dz <= 3; dz++) {
                boolean isInner = dx >= 0 && dx <= 2 && dz >= 0 && dz <= 2;
                if (isInner) {
                    continue;
                }
                BlockPos pos = new BlockPos(x0 + dx, y, z0 + dz);
                BlockState state = world.getBlockState(pos);
                if (!isPortalFrameWithEye(state)) {
                    return false;
                }
                Direction expected;
                if (dz == -1) {
                    expected = Direction.SOUTH;
                } else if (dz == 3) {
                    expected = Direction.NORTH;
                } else if (dx == -1) {
                    expected = Direction.EAST;
                } else {
                    expected = Direction.WEST;
                }
                if (state.get(EndPortalFrameBlock.FACING) != expected) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isUnknownPortalFrameRingLoose(net.minecraft.world.World world, int y, int x0, int z0) {
        for (int dx = -1; dx <= 3; dx++) {
            for (int dz = -1; dz <= 3; dz++) {
                boolean isInner = dx >= 0 && dx <= 2 && dz >= 0 && dz <= 2;
                if (isInner) {
                    continue;
                }
                BlockPos pos = new BlockPos(x0 + dx, y, z0 + dz);
                BlockState state = world.getBlockState(pos);
                if (!isPortalFrameWithEye(state)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isPortalFrameWithEye(BlockState state) {
        if (state.isOf(SubBlocks.UNKNOWN_PORTAL_FRAME.block()) || state.isOf(Blocks.END_PORTAL_FRAME)) {
            return state.get(EndPortalFrameBlock.EYE);
        }
        return false;
    }

    private static int countPortalFramesWithEye(net.minecraft.world.World world, BlockPos center) {
        int count = 0;
        int y = center.getY();
        for (int x = center.getX() - 3; x <= center.getX() + 3; x++) {
            for (int z = center.getZ() - 3; z <= center.getZ() + 3; z++) {
                BlockState state = world.getBlockState(new BlockPos(x, y, z));
                if (isPortalFrameWithEye(state)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean tryCreateUnknownPortalByBounds(net.minecraft.world.World world, int y, BlockPos pos) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxZ = Integer.MIN_VALUE;
        int count = 0;
        for (int x = pos.getX() - 6; x <= pos.getX() + 6; x++) {
            for (int z = pos.getZ() - 6; z <= pos.getZ() + 6; z++) {
                BlockState state = world.getBlockState(new BlockPos(x, y, z));
                if (!isPortalFrameWithEye(state)) {
                    continue;
                }
                count++;
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minZ = Math.min(minZ, z);
                maxZ = Math.max(maxZ, z);
            }
        }
        if (count < 12) {
            return false;
        }
        if (maxX - minX != 4 || maxZ - minZ != 4) {
            return false;
        }
        for (int x = minX + 1; x <= maxX - 1; x++) {
            for (int z = minZ + 1; z <= maxZ - 1; z++) {
                world.setBlockState(new BlockPos(x, y, z), SubBlocks.UNKNOWN_PORTAL.block().getDefaultState(), 3);
            }
        }
        world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);
        return true;
    }




}
