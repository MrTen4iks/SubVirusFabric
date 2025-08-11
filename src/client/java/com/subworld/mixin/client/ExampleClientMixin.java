package com.subworld.mixin.client;

import com.subworld.client.SubteamClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class ExampleClientMixin {

	@Inject(method = "render", at = @At(value = "RETURN"))
	public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
		context.fill(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), ColorHelper.getArgb(SubteamClient.getAlpha().get(), 0, 0, 0));
	}

}