package com.subworld.tools;


import com.subworld.item.SubItemMaterials;
import net.minecraft.item.Item;
import com.subworld.item.SubItem;
import static net.minecraft.item.Items.register;

public class SubTools {

    public static final Item UNCERTAIN_PICKAXE = register(
            "uncertain_pickaxe",
            Item::new,
            new Item.Settings().pickaxe(SubItemMaterials.INFECTED_TOOL_MATERIAL, 1, -2.8f));













}
