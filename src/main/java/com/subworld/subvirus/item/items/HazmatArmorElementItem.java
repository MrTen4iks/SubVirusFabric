package com.subworld.subvirus.item.items;


import com.subworld.subvirus.client.render.armor.HazmatArmorRenderer;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class HazmatArmorElementItem extends Item implements GeoItem {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final DataTicket<Boolean> HAS_FULL_SET_EFFECT = DataTicket.create("examplemod_has_full_set_effect", Boolean.class);

    public HazmatArmorElementItem(Settings settings) {
        super(settings);
    }
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private HazmatArmorRenderer<?> renderer;

            @Override
            public <S extends BipedEntityRenderState> @NotNull GeoArmorRenderer<?, ?> getGeoArmorRenderer(
                                                              @Nullable S renderState,
                                                              ItemStack itemStack,
                                                              EquipmentSlot equipmentSlot,
                                                              EquipmentModel.LayerType type,
                                                              @Nullable BipedEntityModel<S> original
            ){
                if (this.renderer == null)
                    this.renderer = new HazmatArmorRenderer<>();

                return this.renderer;
            }
        });
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(20, animTest -> {
            // Play the animation if the full set is being worn, otherwise stop
            if (animTest.getData(HAS_FULL_SET_EFFECT))
                return animTest.setAndContinue(DefaultAnimations.IDLE);

            return PlayState.STOP;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
