package com.subworld.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Util;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class SubteamClient implements ClientModInitializer {

    private static AtomicInteger alpha = new AtomicInteger(0);

    @Override
    public void onInitializeClient() {
        startAlpha();
    }

    public static AtomicInteger getAlpha() {
        return alpha;
    }

    private void startAlpha() {
        Thread d = new Thread(() -> {
            try {
                while (true) {
                    while (alpha.get() < 255) {
                        alpha.getAndAdd(1);
                        System.out.println(alpha);
                        Thread.sleep(10);
                    }
                    Thread.sleep(1000);
                    while (alpha.get() > 0) {
                        alpha.getAndAdd(-1);
                        Thread.sleep(10);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        d.setDaemon(true);
        d.start();
    }
}
