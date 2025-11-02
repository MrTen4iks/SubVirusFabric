package com.subworld.subvirus.world.materials;


import com.google.common.collect.Maps;
import com.subworld.subvirus.registry.SubTags;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import java.util.Map;

import static com.subworld.subvirus.registry.SubTags.Items.INFECTED_ARMOR_MATERIAL_KEY;
import static com.subworld.subvirus.registry.SubTags.Items.REPAIRS_INFECTED_ARMOR;

public class SubArmorMaterials {
    public static final int BASE_DURABILITY = 60;
    ArmorMaterial LEATHER = new ArmorMaterial(5, createDefenseMap(1, 2, 3, 1, 3), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemTags.REPAIRS_LEATHER_ARMOR, EquipmentAssetKeys.LEATHER);
    private static Map<EquipmentType, Integer> createDefenseMap(int bootsDefense, int leggingsDefense, int chestplateDefense, int helmetDefense, int bodyDefense) {
        return Maps.newEnumMap(Map.of(EquipmentType.BOOTS, bootsDefense, EquipmentType.LEGGINGS, leggingsDefense, EquipmentType.CHESTPLATE, chestplateDefense, EquipmentType.HELMET, helmetDefense, EquipmentType.BODY, bodyDefense));
    }

    public static final ArmorMaterial HAZMAT_ARMOR_MATERIAL =
            new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    EquipmentType.HELMET , 4,
                    EquipmentType.CHESTPLATE, 9,
                    EquipmentType.LEGGINGS, 7,
                    EquipmentType.BOOTS, 4,
                    EquipmentType.BODY, 24
            ),
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            3.0F,
            0.2F,

                    ItemTags.REPAIRS_LEATHER_ARMOR, EquipmentAssetKeys.LEATHER
            //REPAIRS_INFECTED_ARMOR,
           // INFECTED_ARMOR_MATERIAL_KEY
    );
}
