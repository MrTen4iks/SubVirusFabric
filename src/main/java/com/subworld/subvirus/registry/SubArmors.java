package com.subworld.subvirus.registry;


import com.subworld.subvirus.SubVirus;
import com.subworld.subvirus.world.items.armor.HazmatArmorElementItem;
import com.subworld.subvirus.world.items.armor.HazmatArmorPackItem;
import com.subworld.subvirus.world.materials.SubArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class SubArmors {
    public static final Item HAZMAT_SUIT_HELMET = register(
            "hazmat_suit_helmet",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.HELMET)
                    .maxDamage(EquipmentType.HELMET.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );
    public static final Item HAZMAT_SUIT_CHESTPLATE = register("hazmat_suit_chestplate",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );
    public static final Item HAZMAT_SUIT_LEGGINGS = register(
            "hazmat_suit_leggings",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.LEGGINGS)
                    .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );

    public static final Item HAZMAT_SUIT_BOOTS = register(
            "hazmat_suit_boots",
            HazmatArmorElementItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.BOOTS)
                    .maxDamage(EquipmentType.BOOTS.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );

    public static final Item HAZMAT_SUIT_PACK = register(
            "hazmat_suit_pack",
            HazmatArmorPackItem::new,
            new Item.Settings().armor(SubArmorMaterials.HAZMAT_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.BODY.getMaxDamage(SubArmorMaterials.BASE_DURABILITY))
    );



    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SubVirus.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void registerModArmors(){
        SubVirus.LOGGER.debug("Registering armor for " + SubVirus.MOD_ID);
    }
}
