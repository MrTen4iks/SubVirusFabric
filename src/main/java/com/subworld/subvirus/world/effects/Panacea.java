package com.subworld.subvirus.world.effects;


import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

public class Panacea extends StatusEffect {
    public static final Panacea PANACEA = new Panacea();
    public Panacea() {
        super(StatusEffectCategory.HARMFUL, 0x009999); // Красный цвет
    }
    public static RegistryEntry<StatusEffect> getRegistryEntry() {
        return Registries.STATUS_EFFECT.getEntry(PANACEA);
    }

}

