package com.subworld.subvirus.world.blocks;

import com.subworld.subvirus.world.entities.InfectedTntEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class InfectedTntBlock extends TntBlock {
    public InfectedTntBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onDestroyedByExplosion(ServerWorld world, BlockPos pos, Explosion explosion) {
        primeInfectedTnt(world, pos, null);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock()) && world.isReceivingRedstonePower(pos)) {
            primeInfectedTnt(world, pos, null);
            world.removeBlock(pos, false);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, net.minecraft.block.Block block, WireOrientation wireOrientation, boolean notify) {
        if (world.isReceivingRedstonePower(pos)) {
            primeInfectedTnt(world, pos, null);
            world.removeBlock(pos, false);
        }
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient() && !player.isCreative() && state.get(UNSTABLE)) {
            primeInfectedTnt(world, pos, player);
        }
        return super.onBreak(world, pos, state, player);
    }

    @Override
    public ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isOf(Items.FLINT_AND_STEEL) || stack.isOf(Items.FIRE_CHARGE)) {
            primeInfectedTnt(world, pos, player);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            if (!player.isCreative()) {
                if (stack.isOf(Items.FLINT_AND_STEEL)) {
                    stack.damage(1, player, LivingEntity.getSlotForHand(hand));
                } else {
                    stack.decrement(1);
                }
            }
            return world.isClient() ? ActionResult.SUCCESS : ActionResult.SUCCESS_SERVER;
        }
        return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (!world.isClient() && projectile.isOnFire()) {
            BlockPos pos = hit.getBlockPos();
            LivingEntity owner = projectile.getOwner() instanceof LivingEntity living ? living : null;
            primeInfectedTnt(world, pos, owner);
            world.removeBlock(pos, false);
        }
    }

    private void primeInfectedTnt(World world, BlockPos pos, @Nullable LivingEntity igniter) {
        if (world.isClient()) {
            return;
        }
        InfectedTntEntity tnt = new InfectedTntEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, igniter);
        world.spawnEntity(tnt);
        world.emitGameEvent(igniter, GameEvent.PRIME_FUSE, pos);
    }
}
