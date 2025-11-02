package com.subworld.subvirus.registry;

import com.subworld.subvirus.SubVirus;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class SubPotions {
    public static final RegistryEntry<Potion> UNKNOWN_INFECTION_POTION = Registry.registerReference(
            Registries.POTION,
            Identifier.of(SubVirus.MOD_ID, "unknown_infection"),
            new Potion(
                    "unknown_infection",
                    new StatusEffectInstance(
                            SubEffects.UNKNOWN_INFECTION, // you already registered it
                            3600,
                            0
                    )
            )
    );

    public static final RegistryEntry<Potion> UNKNOWNS = Registry.registerReference(
            Registries.POTION,
            Identifier.of(SubVirus.MOD_ID, "unknowns"),
            new Potion(
                    "unknowns",
                    new StatusEffectInstance(
                            SubEffects.UNKNOWNS,
                            3600,
                            0
                    )
            )
    );

    public static void spid() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    Potions.POISON,                // input
                    Items.BEDROCK,                 // ingredient
                    UNKNOWN_INFECTION_POTION       // output (now a RegistryEntry<Potion>)
            );
        });
    }
}