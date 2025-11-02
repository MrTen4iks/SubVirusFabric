package com.subworld.subvirus;


import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class SubDamage {


    public static final RegistryKey<DamageType> TATER_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(SubVirus.MOD_ID, "infected"));


}
