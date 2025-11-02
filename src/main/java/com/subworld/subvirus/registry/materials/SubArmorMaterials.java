package com.subworld.subvirus.registry.materials;


import com.google.common.collect.Maps;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;

import java.util.Map;

import static com.subworld.subvirus.registry.SubTags.Items.REPAIRS_INFECTED_ARMOR;
import static com.subworld.subvirus.registry.SubTags.Items.INFECTED_ARMOR_MATERIAL_KEY;

public class SubArmorMaterials {
    public static final int BASE_DURABILITY = 60;


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
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            3.0F,
            0.2F,

            REPAIRS_INFECTED_ARMOR,
            INFECTED_ARMOR_MATERIAL_KEY
    );
}
