package com.subworld.subvirus.effect;

import com.subworld.subvirus.SubVirus;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class SubEffects {
    public static final RegistryEntry<StatusEffect> UNKNOWN_INFECTION = registerEffect(
            "unknown_infection",
            new UnknownEffect()
    );

    public static final RegistryEntry<StatusEffect> UNKNOWNS = registerEffect(
            "unknowns",
            new SubInfected()
    );

    private static RegistryEntry<StatusEffect> registerEffect(String name, StatusEffect effect) {
        return Registry.registerReference(
                Registries.STATUS_EFFECT,
                Identifier.of(SubVirus.MOD_ID, name),
                effect
        );
    }

    public static void registerModEffects() {
        SubVirus.LOGGER.debug("Registering effects for " + SubVirus.MOD_ID);
    }
}