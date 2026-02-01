package com.subworld.subvirus.world.blocks;

import com.subworld.subvirus.registry.SubBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class UncertainGrassBlock extends GrassBlock {
    public UncertainGrassBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView,
                                                BlockPos pos, Direction direction, BlockPos neighborPos,
                                                BlockState neighborState, Random random) {
        if (direction == Direction.UP) {
            boolean hasFluidAbove = !neighborState.getFluidState().isEmpty();
            boolean hasSolidAbove = neighborState.isSolidBlock(world, pos.up());
            if (hasFluidAbove || hasSolidAbove) {
                return SubBlocks.UNCERTAIN_DIRT.block().getDefaultState();
            }
        }
        return super.getStateForNeighborUpdate(
                state,
                world,
                tickView,
                pos,
                direction,
                neighborPos,
                neighborState,
                random
        );
    }
}
