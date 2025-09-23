package com.subworld.effect;


import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

public class Unknown extends StatusEffect {
    public static final com.subworld.effect.Unknown UNKNOWN = new com.subworld.effect.Unknown();
    public Unknown() {
        super(StatusEffectCategory.HARMFUL, 0xFFFFFF); // Красный цвет
    }
    public static RegistryEntry<StatusEffect> getRegistryEntry() {
        return Registries.STATUS_EFFECT.getEntry(UNKNOWN);
    }










}
