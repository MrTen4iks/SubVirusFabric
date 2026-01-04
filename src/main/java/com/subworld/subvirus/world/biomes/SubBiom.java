package com.subworld.subvirus.world.biomes;

import com.subworld.subvirus.SubVirus;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;

public class SubBiom  {
//    public static final Biome MY_BIOME = createBiome();
//
//    private static Biome createBiome() {
//        BiomeEffects effects = new BiomeEffects.Builder()
//                .fogColor(12638463)
//                .waterColor(4159204)
//                .waterFogColor(329011) // ← ОБЯЗАТЕЛЬНО
//                .skyColor(7972607)
//                .build();
//
//        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
//
//        return new Biome.Builder()
//                .precipitation(false)
//                .temperature(0.8f)
//                .downfall(0.4f)
//                .effects(effects)
//                .generationSettings(generationSettings.build())
//                .build();
//    }


    public static void registerModBiomes() {
        SubVirus.LOGGER.debug("Registering Biomes for " + SubVirus.MOD_ID);

//        Registry.register(
//                Registries.BIOME,
//                Identifier.tryParse(SubVirus.MOD_ID, "my_biome"),
//                MY_BIOME
//        );
    }
}
