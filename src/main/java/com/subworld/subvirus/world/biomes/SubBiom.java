package com.subworld.subvirus.world.biomes;

import com.subworld.subvirus.SubVirus;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class SubBiom {
    public static final RegistryKey<Biome> INFECTED_BIOME_KEY =
            RegistryKey.of(RegistryKeys.BIOME, Identifier.of(SubVirus.MOD_ID, "infected"));

    public static void registerModBiomes() {
        SubVirus.LOGGER.debug("Registering Biomes for " + SubVirus.MOD_ID);
        // Biomes are data-driven in 1.21.x (see data/subvirus/worldgen/biome).
    }
}
