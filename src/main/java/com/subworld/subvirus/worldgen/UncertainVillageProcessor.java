package com.subworld.subvirus.worldgen;

import com.mojang.serialization.MapCodec;
import com.subworld.subvirus.SubVirus;
import com.subworld.subvirus.registry.SubBlocks;
import com.subworld.subvirus.registry.SubProcessors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class UncertainVillageProcessor extends StructureProcessor {
    public static final MapCodec<StructureProcessor> CODEC = MapCodec.unit(new UncertainVillageProcessor());
    private static final Identifier INFECTED_DIMENSION_TYPE_ID =
            Identifier.of(SubVirus.MOD_ID, "infected");

    @Override
    protected StructureProcessorType<?> getType() {
        return SubProcessors.UNCERTAIN_VILLAGE;
    }

    @Override
    public StructureTemplate.StructureBlockInfo process(
            WorldView world,
            BlockPos pos,
            BlockPos pivot,
            StructureTemplate.StructureBlockInfo original,
            StructureTemplate.StructureBlockInfo current,
            StructurePlacementData data
    ) {
        var dimRegistry = world.getRegistryManager().getOrThrow(RegistryKeys.DIMENSION_TYPE);
        var dimTypeId = dimRegistry.getId(world.getDimension());
        if (dimTypeId == null || !dimTypeId.equals(INFECTED_DIMENSION_TYPE_ID)) {
            return current;
        }

        BlockState state = current.state();
        BlockState replaced = null;
        var blockId = Registries.BLOCK.getId(state.getBlock());

        if (state.isOf(Blocks.OAK_PLANKS)) {
            replaced = SubBlocks.UNCERTAIN_PLANKS.block().getDefaultState();
        } else if (state.isOf(Blocks.OAK_LOG)) {
            replaced = SubBlocks.UNCERTAIN_LOG.block()
                    .getDefaultState()
                    .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS));
        } else if (state.isOf(Blocks.DIRT) || state.isOf(Blocks.COARSE_DIRT)) {
            replaced = SubBlocks.UNCERTAIN_DIRT.block().getDefaultState();
        } else if (state.isOf(Blocks.DIRT_PATH)
                || "dirt_path".equals(blockId.getPath())
                || "grass_path".equals(blockId.getPath())) {
            replaced = SubBlocks.UNCERTAIN_DIRT_PATH.block().getDefaultState();
        } else if (state.isOf(Blocks.FARMLAND)) {
            replaced = SubBlocks.UNCERTAIN_DIRT.block().getDefaultState();
        } else if (state.isOf(Blocks.WHEAT)
                || state.isOf(Blocks.CARROTS)
                || state.isOf(Blocks.POTATOES)
                || state.isOf(Blocks.BEETROOTS)) {
            replaced = Blocks.AIR.getDefaultState();
        } else if (state.isOf(Blocks.GRASS_BLOCK)) {
            replaced = SubBlocks.UNCERTAIN_GRASS_BLOCK.block()
                    .getDefaultState()
                    .with(GrassBlock.SNOWY, state.get(GrassBlock.SNOWY));
        }

        if (replaced == null) {
            return current;
        }

        return new StructureTemplate.StructureBlockInfo(current.pos(), replaced, current.nbt());
    }
}
