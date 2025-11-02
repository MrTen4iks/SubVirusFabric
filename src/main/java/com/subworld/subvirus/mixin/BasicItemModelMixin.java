package com.subworld.subvirus.mixin;

import com.subworld.subvirus.util.IDyeableItem;
import net.minecraft.client.render.item.model.BasicItemModel;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BasicItemModel.class)
public class BasicItemModelMixin {

    /**
     * Перехватываем вызов TintSource#getTint(...)
     * внутри BasicItemModel#update и подменяем результат
     * только для наших предметов.
     */
    @Redirect(
            method = "update(Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/item/ItemModelManager;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/entity/LivingEntity;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/tint/TintSource;getTint(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/entity/LivingEntity;)I"
            )
    )
    private int subvirus$injectCustomTint(
            TintSource instance,
            ItemStack stack,
            @Nullable ClientWorld world,
            @Nullable LivingEntity user
    ) {
        // стандартный цвет, который бы вернул TintSource
        int vanillaColor = instance.getTint(stack, world, user);

        // если не наш предмет — возвращаем оригинал
        if (!(stack.getItem() instanceof IDyeableItem dyeable)) {
            return vanillaColor;
        }

        // получаем пользовательский цвет
        int customColor = dyeable.getColor(stack);

        // если цвет не установлен — используем ванильный
        if (customColor == -1) {
            return vanillaColor;
        }

        return customColor;
    }
}
