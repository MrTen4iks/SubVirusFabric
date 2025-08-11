package com.subworld;

import com.subworld.effect.SubEffects;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SubPotion  {

    public static final Potion UNKNOWN_INFECTION_POTION = Registry.register(
            Registries.POTION,
            Identifier.of(Subteam.MOD_ID, "unknown_infection"),
            new Potion("unknown_infection",
                    new StatusEffectInstance(
                            Registries.STATUS_EFFECT.getEntry(SubEffects.UNKNOWN_INFECTION),
                            3600,
                            4
                    )
    ));


     public static void spid() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    // Input potion.
                    Potions.POISON,
                    // Ingredient
                    Items.BEDROCK,
                    // Output potion.
                    Registries.POTION.getEntry(UNKNOWN_INFECTION_POTION)
            );
        });
}}
