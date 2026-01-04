package com.subworld.subvirus.world.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;

import net.minecraft.block.FacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SubFacingBlockRaziv extends FacingBlock {

    public static final MapCodec<SubFacingBlockRaziv> CODEC =
            Block.createCodec(SubFacingBlockRaziv::new);

    private static final double THICK = 1.0 / 16.0; // 0.125
    private static final VoxelShape NORTH_SHAPE = VoxelShapes.cuboid(
            0.0, 0.0, 0.0,
            1.0, 1.0, THICK
    );
    private static final VoxelShape SOUTH_SHAPE = VoxelShapes.cuboid(
            0.0, 0.0, 1.0 - THICK,
            1.0, 1.0, 1.0
    );
    private static final VoxelShape EAST_SHAPE = VoxelShapes.cuboid(
            1.0 - THICK, 0.0, 0.0,
            1.0, 1.0, 1.0
    );
    private static final VoxelShape UP_SHAPE = VoxelShapes.cuboid(
            0.0, 1.0 - THICK, 0.0,
            1.0, 1.0, 1.0
    );
    private static final VoxelShape DOWN_SHAPE = VoxelShapes.cuboid(
            0.0, 0.0, 0.0,
            1.0, THICK, 1.0
    );
    private static final VoxelShape WEST_SHAPE = VoxelShapes.cuboid(
            0.0, 0.0, 0.0,
            THICK, 1.0, 1.0
    );


    private static VoxelShape getShapeForFacing(Direction dir) {
        return switch (dir) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST  -> WEST_SHAPE;
            case EAST  -> EAST_SHAPE;
            case UP    -> UP_SHAPE;
            case DOWN  -> DOWN_SHAPE;
        };
    }
    public SubFacingBlockRaziv(Settings settings) {
        super(settings);

        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH));

    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShapeForFacing(state.get(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShapeForFacing(state.get(FACING));
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {

        PlayerEntity p = ctx.getPlayer();
        Direction facing = Direction.NORTH;
        if (p != null){
            facing = ctx.getPlayer().getFacing();
            if (p.isSneaking()) facing = facing.getOpposite();
        }

        return this.getDefaultState().with(FACING, facing);
    }
    @Override
    protected MapCodec<? extends FacingBlock> getCodec() {
        return CODEC;
    }
}
