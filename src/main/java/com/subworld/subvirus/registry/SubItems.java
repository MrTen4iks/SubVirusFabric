package com.subworld.subvirus.registry;

import com.subworld.subvirus.SubVirus;
import com.subworld.subvirus.registry.materials.SubItemMaterials;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;


public class SubItems {
    public static final Item INFECTED_RAW = register("infected_raw", Item::new, new Item.Settings());
    public static final Item INFECTED_INGOT = register("infected_ingot", Item::new, new Item.Settings());
    public static final Item INFECTED_DUST = register("infected_dust", Item::new, new Item.Settings());
    public static final Item INFECTED_STICK = register("infected_stick", Item::new, new Item.Settings());


    public static final Item UNCERTAIN_PICKAXE = register("uncertain_pickaxe", Item::new,
            new Item.Settings().enchantable(15).pickaxe(SubItemMaterials.INFECTED_TOOL_MATERIAL, 1, -2.8f));


    public static final Item UNCERTAIN_SWORD = register("uncertain_sword", Item::new,
            new Item.Settings().enchantable(15).sword(SubItemMaterials.INFECTED_TOOL_MATERIAL, 14, -2.4f));

    public static void registerModItems() {
        SubVirus.LOGGER.debug("Регестрация предметов для" + SubVirus.MOD_ID);
    }

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SubVirus.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }
}
