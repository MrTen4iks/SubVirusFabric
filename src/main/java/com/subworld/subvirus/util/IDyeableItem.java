package com.subworld.subvirus.util;


import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.item.ItemStack;

public interface IDyeableItem {
    int getColor(ItemStack stack);
}
