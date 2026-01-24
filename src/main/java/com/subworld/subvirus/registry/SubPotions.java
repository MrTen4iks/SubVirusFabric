package com.subworld.subvirus.registry;

import com.subworld.subvirus.SubVirus;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.block.Portal;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class SubPotions {
    public static final RegistryEntry<Potion> UNKNOWN_INFECTION_POTION = register(
            Identifier.of(SubVirus.MOD_ID, "unknown_infection"),
            new Potion(
                    "unknown_infection",
                    new StatusEffectInstance(
                            SubEffects.UNKNOWN_INFECTION, // you already registered it
                            3600,
                            0
                    )
            ),
            true
    );

    public static final RegistryEntry<Potion> UNKNOWNS = register(
            Identifier.of(SubVirus.MOD_ID, "unknowns"),
            new Potion(
                    "unknowns",
                    new StatusEffectInstance(
                            SubEffects.UNKNOWNS,
                            3600,
                            0
                    )
            ),
            true
    );
    public static final RegistryEntry<Potion> PANACEA = register(
            Identifier.of(SubVirus.MOD_ID, "panacea"),
            new Potion(
                    "panacea",
                    new StatusEffectInstance(
                            SubEffects.PANACEA,
                            3600,
                            0
                    )
            ),
            true
    );

    public static RegistryEntry<Potion> register( Identifier id, Potion entry, boolean shouldAddToTab){
        RegistryEntry<Potion> t = Registry.registerReference( Registries.POTION,id,entry);
        if (shouldAddToTab) {
            SubItemGroups.SUB_TAB.add(PotionContentsComponent.createStack(Items.POTION, t));
        }
        return t;
    }

    public static void registerModPotions() {

        SubVirus.LOGGER.debug("Регестрация зелей для" + SubVirus.MOD_ID);
    }



}
