package com.subworld.subvirus.client;


import com.subworld.subvirus.registry.SubEntities;
import com.subworld.subvirus.registry.SubBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.entity.TntEntityRenderer;

public class SubVirusClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(SubEntities.INFECTED_TNT, TntEntityRenderer::new);
        BlockRenderLayerMap.putBlock(SubBlocks.UNCERTAIN_GRASS_BLOCK.block(), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SubBlocks.UNCERTAIN_LEAVES.block(), BlockRenderLayer.CUTOUT_MIPPED);
        ColorProviderRegistry.BLOCK.register(
                (state, world, pos, tintIndex) -> {
                    if (world == null || pos == null) {
                        return -1;
                    }
                    return BiomeColors.getGrassColor(world, pos);
                },
                SubBlocks.UNCERTAIN_GRASS_BLOCK.block()
        );
        ColorProviderRegistry.BLOCK.register(
                (state, world, pos, tintIndex) -> {
                    if (world == null || pos == null) {
                        return -1;
                    }
                    return BiomeColors.getFoliageColor(world, pos);
                },
                SubBlocks.UNCERTAIN_LEAVES.block()
        );
    }
}
