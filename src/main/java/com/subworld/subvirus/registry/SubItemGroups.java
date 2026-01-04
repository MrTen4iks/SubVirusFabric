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


    public static final AbstractTab SUB_TAB = new AbstractTab(
            RegistryKey.of(
                    Registries.ITEM_GROUP.getKey(),
                    Identifier.of(SubVirus.MOD_ID, "sub_group")
            ),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(SubItems.INFECTED_INGOT))
                    .displayName(Text.translatable(SubVirus.MOD_ID + ".group_trans"))
                    .build()
    );

    public static void registerModTabs(){
        System.out.println("Registering item groups for " + SubVirus.MOD_ID);
        SUB_TAB.register();
    }

    public static class AbstractTab {
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
