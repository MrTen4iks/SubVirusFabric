package com.subworld.util;

import com.subworld.Subteam;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class SubTags {

    public static class Blocks {
        public static final TagKey<Block> NEEDS_INFECTED_TOOL = createTag("needs_infected_tool");
        public static final TagKey<Block> INCORRECT_FOR_INFECTED_TOOL = createTag("incorrect_for_infected_tool");
        public static final TagKey<Item> REPAIRS_INFECTED_ARMOR = TagKey.of(Registries.ITEM.getKey(), Identifier.of(Subteam.MOD_ID, "repairs_infected_armor"));

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Subteam.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> INFECTED_ITEMS = createTag("infected_items");
        public static final TagKey<Item> INFECTED_INGOT_REPAIR = createTag("infected_ingot_repair");
        public static final TagKey<Item> REPAIRS_INFECTED_ARMOR = TagKey.of(Registries.ITEM.getKey(), Identifier.of(Subteam.MOD_ID, "repairs_infected_armor"));
        public static final RegistryKey<EquipmentAsset> INFECTED_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(Subteam.MOD_ID, "infected"));


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Subteam.MOD_ID, name));
        }
    }












}
