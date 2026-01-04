package com.subworld.subvirus.world.items.food;

import com.subworld.subvirus.SubVirus;
import com.subworld.subvirus.registry.SubEffects;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

import static net.minecraft.item.Items.register;

public class SubFood {

    public static final ConsumableComponent PANACEA_FOOD_CONSUMABLE_COMPONENT = ConsumableComponents.food()
            // The duration is in ticks, 20 ticks = 1 second
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(SubEffects.PANACEA, 6 * 20, 0), 1.0f)).build();
    public static final FoodComponent PANACEA_FOOD_COMPONENT = new FoodComponent.Builder().alwaysEdible().build();

    public static final Item PANACEA_STEW = register("panacea_stew", Item::new, new Item.Settings().food(PANACEA_FOOD_COMPONENT, PANACEA_FOOD_CONSUMABLE_COMPONENT));
    public static void registerModFoods() {
        SubVirus.LOGGER.debug("Registering foods for " + SubVirus.MOD_ID);
    }
}
