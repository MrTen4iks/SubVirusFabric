package com.subworld.item;

import com.subworld.Subteam;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class SubItem {
    public static final Item INFECTED_RAW = register("infected_raw", Item::new, new Item.Settings());
    public static final Item INFECTED_INGOT = register("infected_ingot", Item::new, new Item.Settings());
    public static final Item INFECTED_DUST = register("infected_dust", Item::new, new Item.Settings());
    public static final Item INFECTED_STICK = register("infected_stick", Item::new, new Item.Settings());
    public static final Item UNCERTAIN_PICKAXE = register("uncertain_pickaxe", Item::new, new Item.Settings());
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Subteam.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }













    public static void registerModItems() {
        Subteam.LOGGER.debug("Регестрация предметов для" + Subteam.MOD_ID);


    }



}
