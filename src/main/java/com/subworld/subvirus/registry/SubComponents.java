package com.subworld.subvirus.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.subworld.subvirus.SubVirus;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;


public class SubComponents {

    /* public static final ComponentType<Integer> DYABLE_HAZMAT_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(SubVirus.MOD_ID, "my_component"),
            ComponentType.<Integer>builder().codec(Codecs.RGB).build()
    );*/
    public static void initialize() {
        SubVirus.LOGGER.info("Registering {} components", SubVirus.MOD_ID);
    }

}
