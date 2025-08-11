package com.subworld.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import com.subworld.SubDamage;

public class UnknownEffect extends StatusEffect {
    public static final UnknownEffect UNKNOWN_INFECTION = new UnknownEffect();

    public UnknownEffect() {
        super(StatusEffectCategory.HARMFUL, 0xFFFFFF); // Красный цвет
    }

    public static RegistryEntry<StatusEffect> getRegistryEntry() {
        return Registries.STATUS_EFFECT.getEntry(UNKNOWN_INFECTION);
    }

    public void applyUpdateEffect(LivingEntity entity, int amplifier, ServerWorld world) {
        if (entity.getWorld().isClient()) return;

        if (entity.getWorld().getTime() % 20 == 0) {
            DamageSource damageSource = new DamageSource(
                    world.getRegistryManager()
                            .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                            .getEntry(SubDamage.TATER_DAMAGE.getValue()).get()
            );
            entity.damage((ServerWorld) entity.getWorld(), damageSource, 1.0F + amplifier);
        }
    }

        public static final int FLOWER_CONTACT_EFFECT_DURATION = 25;

        protected UnknownEffect(StatusEffectCategory statusEffectCategory, int i) {
            super(statusEffectCategory, i);
        }

        public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
            DamageSource damageSource = new DamageSource(
                    world.getRegistryManager()
                            .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                            .getEntry(SubDamage.TATER_DAMAGE.getValue()).get()
            );
            entity.damage(world, damageSource, 0.9f);





            return true;
        }
        @Override
        public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 25 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }
}
