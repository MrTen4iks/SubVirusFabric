package com.subworld.item;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.sound.SoundEvents;

import java.util.Map;

import static com.subworld.item.SubItemMaterials.BASE_DURABILITY;
import static com.subworld.util.SubTags.Items.INFECTED_ARMOR_MATERIAL_KEY;
import static com.subworld.util.SubTags.Items.REPAIRS_INFECTED_ARMOR;

public class SubArmor {



    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    EquipmentType.HELMET, 4,
                    EquipmentType.CHESTPLATE, 9,
                    EquipmentType.LEGGINGS, 7,
                    EquipmentType.BOOTS, 4,
                    EquipmentType.BODY, 20
            ),
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,

            REPAIRS_INFECTED_ARMOR,
            INFECTED_ARMOR_MATERIAL_KEY
    );






}
