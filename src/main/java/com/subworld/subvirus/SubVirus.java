package com.subworld.subvirus;


import com.subworld.subvirus.registry.*;
import com.subworld.subvirus.world.biomes.SubBiom;
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


public class SubVirus implements ModInitializer {
    public static final String MOD_ID = "subvirus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



    @Override
    public void onInitialize() {
        SubEffects.registerModEffects();
        SubItems.registerModItems();
        SubBlocks.registerModBlocks();
        //SubArmors.registerModArmors();
        SubComponents.initialize();
        SubBiom.registerModBiomes();

       // SubFood.registerModFoods();
       // var ignore = SubPotion.UNKNOWN_INFECTION_POTION;
        SubPotions.spid();

        SubItemGroups.registerModTabs();
        /*final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(SubVirus.MOD_ID, "sub_group"));
        final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
                .icon(() -> new ItemStack(SubItems.INFECTED_INGOT))
                .displayName(Text.translatable(MOD_ID+".group_trans"))
                .build();
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(SubItems.INFECTED_INGOT);
            itemGroup.add(SubItems.INFECTED_RAW);
            itemGroup.add(SubItems.INFECTED_DUST);
            itemGroup.add(SubItems.INFECTED_STICK);
            itemGroup.add(SubBlocks.INFECTED_ORE.asItem());

            itemGroup.add(SubBlocks.UNCERTAIN_BLOCK.asItem());
            itemGroup.add(SubBlocks.UNCERTAIN_LOG.asItem());
            itemGroup.add(SubBlocks.UNCERTAIN_PLANKS.asItem());
            itemGroup.add(SubItems.Tools.UNCERTAIN_PICKAXE);
            itemGroup.add(SubItems.Tools.UNCERTAIN_SWORD);

            itemGroup.add(SubItems.Armors.HAZMAT_SUIT_BOOTS);
            itemGroup.add(SubItems.Armors.HAZMAT_SUIT_LEGGINGS);
            itemGroup.add(SubItems.Armors.HAZMAT_SUIT_CHESTPLATE);
            itemGroup.add(SubItems.Armors.HAZMAT_SUIT_HELMET);
            itemGroup.add(SubItems.Armors.HAZMAT_SUIT_PACK);

            itemGroup.add(SubItems.Foods.PANACEA_STEW);
            itemGroup.add(PotionContentsComponent.createStack(Items.POTION, SubPotions.UNKNOWN_INFECTION_POTION));
            itemGroup.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SubPotions.UNKNOWN_INFECTION_POTION));
            itemGroup.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SubPotions.UNKNOWN_INFECTION_POTION));
            itemGroup.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SubPotions.UNKNOWN_INFECTION_POTION));

            itemGroup.add(PotionContentsComponent.createStack(Items.POTION, SubPotions.UNKNOWNS));
            itemGroup.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SubPotions.UNKNOWNS));
            itemGroup.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SubPotions.UNKNOWNS));
            itemGroup.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SubPotions.UNKNOWNS));
            itemGroup.add(PotionContentsComponent.createStack(Items.POTION, SubPotions.PANACEA));

            itemGroup.add(PotionContentsComponent.createStack(Items.SPLASH_POTION, SubPotions.PANACEA));
            itemGroup.add(PotionContentsComponent.createStack(Items.LINGERING_POTION, SubPotions.PANACEA));
            itemGroup.add(PotionContentsComponent.createStack(Items.TIPPED_ARROW, SubPotions.PANACEA));

        });*/


    }



}