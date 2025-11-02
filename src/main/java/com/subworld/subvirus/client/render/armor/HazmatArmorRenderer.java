package com.subworld.subvirus.client.render.armor;

import com.subworld.subvirus.registry.SubArmors;
import com.subworld.subvirus.registry.SubItems;
import com.subworld.subvirus.world.items.armor.HazmatArmorElementItem;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import net.minecraft.client.render.entity.equipment.EquipmentModel.Dyeable;
import java.util.Set;

import net.minecraft.client.render.item.model.BasicItemModel;
import software.bernie.geckolib.renderer.specialty.DyeableGeoArmorRenderer;


import static com.subworld.subvirus.SubVirus.MOD_ID;

public class HazmatArmorRenderer <R extends BipedEntityRenderState & GeoRenderState> extends GeoArmorRenderer<HazmatArmorElementItem, R> {
    public HazmatArmorRenderer(){
        super(new DefaultedItemGeoModel<>(Identifier.of(MOD_ID, "armor/hazmat_suit")));

        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
    /// НУЖНО ДЛЯ РАСЦВЕТКИ С DyableGeoRenderer
   // private ItemStack currentStack = ItemStack.EMPTY;

    @Override
    public void addRenderData(HazmatArmorElementItem animatable, RenderData relatedObject, R renderState) {
        Set<Item> wornArmor = new ObjectOpenHashSet<>(4);

        boolean fullSetEffect = false;

        if (!(relatedObject.entity() instanceof ArmorStandEntity)) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR)
                    wornArmor.add(relatedObject.entity().getEquippedStack(slot).getItem());
            }

            fullSetEffect = wornArmor.containsAll(ObjectArrayList.of(
                    SubArmors.HAZMAT_SUIT_BOOTS.asItem(),
                    SubArmors.HAZMAT_SUIT_HELMET.asItem(),
                    SubArmors.HAZMAT_SUIT_CHESTPLATE.asItem(),
                    SubArmors.HAZMAT_SUIT_LEGGINGS.asItem()
            ));
        }

        renderState.addGeckolibData(HazmatArmorElementItem.HAS_FULL_SET_EFFECT, fullSetEffect);
        /// НУЖНО ДЛЯ РАСЦВЕТКИ С DyableGeoRenderer
     /*   if (relatedObject.itemStack() != null) {
            this.currentStack = relatedObject.itemStack();
        } else {
            this.currentStack = ItemStack.EMPTY;
        }*/

    }
    /// НУЖНО ДЛЯ РАСЦВЕТКИ С DyableGeoRenderer
    /*@Override
    protected int getColorForBone(R renderState, GeoBone bone, int packedLight, int packedOverlay, int baseColour) {
        if (!currentStack.isEmpty() && currentStack.getItem() instanceof HazmatArmorElementItem armor) {
            return armor.getColor(currentStack);
        }
        return baseColour;
    }

    @Override
    protected boolean isBoneDyeable(GeoBone geoBone) {
        return true;
    }*/
}
