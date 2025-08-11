package com.subworld;


import com.subworld.effect.SubEffects;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Subteam implements ModInitializer {
    public static final String MOD_ID = "subteam";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



    @Override
    public void onInitialize() {
        SubEffects.registerModEffects();
        var ignore = SubPotion.UNKNOWN_INFECTION_POTION;
        SubPotion.spid();


       }



}