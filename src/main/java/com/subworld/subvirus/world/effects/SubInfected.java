package com.subworld.subvirus.world.effects;

import com.subworld.subvirus.SubVirus;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SubInfected extends StatusEffect {
    public SubInfected() {
        super(StatusEffectCategory.HARMFUL, 0xFF0000);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }


    public static class SubEffects {
        public static final StatusEffect UNKNOWN = register("unknown", new SubInfected());

        public static StatusEffect register(String name, StatusEffect effect) {
            return Registry.register(Registries.STATUS_EFFECT, Identifier.of(SubVirus.MOD_ID, name), effect);
        }

        public static void init() {

        }
    }



    }

