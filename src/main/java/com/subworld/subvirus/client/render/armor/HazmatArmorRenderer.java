package com.subworld.subvirus.client.render.armor;

import com.subworld.subvirus.item.SubItem;
import com.subworld.subvirus.item.items.HazmatArmorElementItem;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

import java.util.Set;

import static com.subworld.subvirus.SubVirus.MOD_ID;

public class HazmatArmorRenderer <R extends BipedEntityRenderState & GeoRenderState> extends GeoArmorRenderer<HazmatArmorElementItem, R> {
    public HazmatArmorRenderer(){
        super(new DefaultedItemGeoModel<>(Identifier.of(MOD_ID, "armor/hazmat_suit")));

        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

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
                    SubItem.HAZMAT_SUIT_BOOTS.asItem(),
                    SubItem.HAZMAT_SUIT_HELMET.asItem(),
                    SubItem.HAZMAT_SUIT_CHESTPLATE.asItem(),
                    SubItem.HAZMAT_SUIT_LEGGINGS.asItem()
            ));
        }

        renderState.addGeckolibData(HazmatArmorElementItem.HAS_FULL_SET_EFFECT, fullSetEffect);
    }
}
