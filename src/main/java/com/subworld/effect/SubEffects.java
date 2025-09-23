package com.subworld.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import com.subworld.Subteam;
import net.minecraft.util.Identifier;


public class SubEffects {
    public static final StatusEffect UNKNOWN_INFECTION = registerEffect("unknown_infection", new UnknownEffect());
    public static final StatusEffect UNKNOWNS = registerEffect("unknowns", new SubInfected());
    private static StatusEffect registerEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, Identifier.of(Subteam.MOD_ID, name),effect);



    }









public static void registerModEffects(){
        Subteam.LOGGER.debug("Регестрация эффектов для" + Subteam.MOD_ID);


    }



}

