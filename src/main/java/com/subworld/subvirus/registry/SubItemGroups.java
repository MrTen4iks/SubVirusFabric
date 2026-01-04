package com.subworld.subvirus.registry;

import com.subworld.subvirus.SubVirus;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;

public class SubItemGroups {
    /*    public static final RegistryKey<ItemGroup> SUB_CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(SubVirus.MOD_ID, "sub_group"));
    public static final ItemGroup SUB_CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(SubItems.INFECTED_INGOT))
            .displayName(Text.translatable(SubVirus.MOD_ID+".group_trans"))
            .build();
    private static Collection<Item> items = new ArrayList<>();
    private static Collection<ItemStack> stacks = new ArrayList<>();
    public static void addItemToTab(Item i) {
        items.add(i);
    }

    public static void addItemToTab(ItemStack s) {
        stacks.add(s);
    }*/

    public static final AbstractTab SUB_TAB = new AbstractTab(
            RegistryKey.of(
                    Registries.ITEM_GROUP.getKey(),
                    Identifier.of(SubVirus.MOD_ID, "sub_group")
            ),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(SubItems.INFECTED_INGOT))
                    .displayName(Text.translatable(SubVirus.MOD_ID + ".group_trans"))
                    .build()
    ) {};

    public static void registerModTabs(){
        System.out.println("Registering item groups for " + SubVirus.MOD_ID);
       /* Registry.register(Registries.ITEM_GROUP, SUB_CUSTOM_ITEM_GROUP_KEY, SUB_CUSTOM_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(SubItemGroups.SUB_CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            for (Item i : items) {
                System.out.println("add item to tab "+ i.getName());
                itemGroup.add(i);
            }
            for (ItemStack i : stacks) {
                System.out.println("add item to tab "+ i.getName());
                itemGroup.add(i);
            }
        });*/
        SUB_TAB.register();
    }

    public static abstract class AbstractTab {
        protected final RegistryKey<ItemGroup> key;
        protected final ItemGroup group;

        protected final Collection<Item> items = new ArrayList<>();
        protected final Collection<ItemStack> stacks = new ArrayList<>();

        protected AbstractTab(RegistryKey<ItemGroup> key, ItemGroup group) {
            this.key = key;
            this.group = group;
        }

        public void add(Item item) {
            items.add(item);
        }

        public void add(ItemStack stack) {
            stacks.add(stack);
        }

        public void register() {
            Registry.register(Registries.ITEM_GROUP, key, group);
            ItemGroupEvents.modifyEntriesEvent(key).register(entries -> {
                items.forEach(entries::add);
                stacks.forEach(entries::add);
            });
        }

        public ItemGroup getGroup() {
            return group;
        }

        public RegistryKey<ItemGroup> getKey() {
            return key;
        }
    }

}
