package com.subworld.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import com.subworld.Subteam;
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
            return Registry.register(Registries.STATUS_EFFECT, Identifier.of(Subteam.MOD_ID, name), effect);
        }

        public static void init() {

        }
    }



    }

