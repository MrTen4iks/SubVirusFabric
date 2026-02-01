package com.subworld.subvirus.registry;

import com.subworld.subvirus.SubVirus;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;

public class SubProcessors {
    public static final StructureProcessorType<?> UNCERTAIN_VILLAGE =
            Registry.register(
                    Registries.STRUCTURE_PROCESSOR,
                    Identifier.of(SubVirus.MOD_ID, "uncertain_village"),
                    () -> com.subworld.subvirus.worldgen.UncertainVillageProcessor.CODEC
            );

    public static void registerModProcessors() {
        SubVirus.LOGGER.debug("Registering processors for " + SubVirus.MOD_ID);
    }
}
