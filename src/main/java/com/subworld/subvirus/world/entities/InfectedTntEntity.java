package com.subworld.subvirus.world.entities;

import com.subworld.subvirus.registry.SubBlocks;
import com.subworld.subvirus.registry.SubEntities;
import com.subworld.subvirus.registry.SubItems;
import com.subworld.subvirus.registry.SubTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class InfectedTntEntity extends TntEntity {
    private static final int INFECTION_RADIUS = 4;

    public InfectedTntEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
    }


    public InfectedTntEntity(World world, double x, double y, double z, LivingEntity igniter) {
        super(world, x, y, z, igniter);
        double velocityX = world.random.nextDouble() * 0.02D - 0.01D;
        double velocityZ = world.random.nextDouble() * 0.02D - 0.01D;
        this.setVelocity(velocityX, 0.2D, velocityZ);
    }

    @Override
    public void tick() {
        if (this.getWorld().isClient()) {
            super.tick();
            return;
        }

        if (this.getFuse() <= 1) {
            explodeInfected();
            this.discard();
            return;
        }

        super.tick();
    }

    private void explodeInfected() {
        World world = this.getWorld();
        BlockPos center = this.getBlockPos();
        List<BlockPos> orePositions = new ArrayList<>();
        List<BlockPos> rawOrePositions = new ArrayList<>();
        List<BlockPos> infectedDarkOrePositions = new ArrayList<>();

        BlockPos min = center.add(-INFECTION_RADIUS, -INFECTION_RADIUS, -INFECTION_RADIUS);
        BlockPos max = center.add(INFECTION_RADIUS, INFECTION_RADIUS, INFECTION_RADIUS);
        for (BlockPos pos : BlockPos.iterate(min, max)) {
            BlockState state = world.getBlockState(pos);
            if (state.isIn(SubTags.Blocks.INFECTABLE_ORES)) {
                orePositions.add(pos.toImmutable());
            } else if (state.isIn(SubTags.Blocks.INFECTABLE_RAW_ORE_BLOCKS)) {
                rawOrePositions.add(pos.toImmutable());
            } else if (state.isOf(SubBlocks.INFECTED_DARK_ORE.block())) {
                infectedDarkOrePositions.add(pos.toImmutable());
            }
        }

        world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 100.0F, World.ExplosionSourceType.TNT);

        for (BlockPos pos : orePositions) {
            if (world.getBlockState(pos).isAir()) {
                world.setBlockState(pos, SubBlocks.INFECTED_ORE.block().getDefaultState(), 3);
            }
        }

        for (BlockPos pos : rawOrePositions) {
            if (world.getBlockState(pos).isAir()) {
                ItemScatterer.spawn(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, new ItemStack(SubItems.INFECTED_RAW));
            }
        }

        for (BlockPos pos : infectedDarkOrePositions) {
            if (world.getBlockState(pos).isAir()) {
                ItemScatterer.spawn(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, new ItemStack(SubItems.INFECTED_RAW));
            }
        }
    }
}
