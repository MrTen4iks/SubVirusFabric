package com.subworld.subvirus.item;

import com.subworld.subvirus.SubVirus;
import com.subworld.subvirus.item.armor.SubArmorMaterials;
import com.subworld.subvirus.item.items.HazmatArmorElementItem;
import com.subworld.subvirus.item.items.HazmatArmorPackItem;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentType;
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

    public static final Item UNCERTAIN_PICKAXE = register("uncertain_pickaxe", Item::new,
            new Item.Settings().pickaxe(SubItemMaterials.INFECTED_TOOL_MATERIAL, 1, -2.8f));
    public static final Item UNCERTAIN_SWORD = register("uncertain_sword", Item::new,
            new Item.Settings().sword(SubItemMaterials.INFECTED_TOOL_MATERIAL, 14, -2.4f));





    public static final Item HAZMAT_SUIT_HELMET = register(
            "hazmat_suit_helmet",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.INSTANCE, EquipmentType.HELMET)
                    .maxDamage(EquipmentType.HELMET.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );
    public static final Item HAZMAT_SUIT_CHESTPLATE = register("hazmat_suit_chestplate",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.INSTANCE, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );
    public static final Item HAZMAT_SUIT_LEGGINGS = register(
            "hazmat_suit_leggings",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.INSTANCE, EquipmentType.LEGGINGS)
                    .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );

    public static final Item HAZMAT_SUIT_BOOTS = register(
            "hazmat_suit_boots",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.INSTANCE, EquipmentType.BOOTS)
                    .maxDamage(EquipmentType.BOOTS.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );

    public static final Item HAZMAT_SUIT_PACK = register(
            "hazmat_suit_pack",
            HazmatArmorPackItem::new,
            new Item.Settings().armor(SubArmorMaterials.INSTANCE, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.BODY.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );



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
