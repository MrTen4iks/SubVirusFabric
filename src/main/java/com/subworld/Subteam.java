package com.subworld;


import com.subworld.block.SubBlock;
import com.subworld.effect.SubEffects;
import com.subworld.item.SubItem;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Subteam implements ModInitializer {
    public static final String MOD_ID = "subteam";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



    @Override
    public void onInitialize() {
        SubEffects.registerModEffects();
        SubItem.registerModItems();
        SubBlock.registerModBlocks();

        var ignore = SubPotion.UNKNOWN_INFECTION_POTION;
        SubPotion.spid();

        final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Subteam.MOD_ID, "sub_group"));
        final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
                .icon(() -> new ItemStack(SubItem.INFECTED_INGOT))
                .displayName(Text.translatable("subteam.group_trans"))
                .build();
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(SubItem.INFECTED_INGOT);
            itemGroup.add(SubItem.INFECTED_RAW);
            itemGroup.add(SubItem.INFECTED_DUST);
            itemGroup.add(SubItem.INFECTED_STICK);
            itemGroup.add(SubBlock.INFECTED_ORE.asItem());
            itemGroup.add(SubBlock.UNCERTAIN_BLOCK.asItem());
            itemGroup.add(SubBlock.UNCERTAIN_LOG.asItem());
            itemGroup.add(SubBlock.UNCERTAIN_PLANKS.asItem());
            itemGroup.add(SubItem.UNCERTAIN_PICKAXE);
            itemGroup.add(SubItem.UNCERTAIN_SWORD);
            itemGroup.add(SubItem.HAZMAT_SUIT_BOOTS);
            itemGroup.add(SubItem.HAZMAT_SUIT_LEGGINGS);
            itemGroup.add(SubItem.HAZMAT_SUIT_CHESTPLATE);
            itemGroup.add(SubItem.HAZMAT_SUIT_HELMET);
            itemGroup.add(SubItem.HAZMAT_SUIT_PACK);
            itemGroup.add(PotionContentsComponent.createStack(Items.POTION, SubPotion.UNKNOWN_INFECTION_POTION));
            itemGroup.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SubPotion.UNKNOWN_INFECTION_POTION));
            itemGroup.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SubPotion.UNKNOWN_INFECTION_POTION));
            itemGroup.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SubPotion.UNKNOWN_INFECTION_POTION));
            itemGroup.add(PotionContentsComponent.createStack(Items.POTION, SubPotion.UNKNOWNS));
            itemGroup.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SubPotion.UNKNOWNS));
            itemGroup.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SubPotion.UNKNOWNS));
            itemGroup.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SubPotion.UNKNOWNS));


        });


    }



}