package com.yxm.guamod.renderer;

import com.yxm.guamod.guaMod;
import net.minecraft.resources.ResourceLocation;

/**
 * 占位类：只提供资源路径常量，避免在没有 Geckolib 依赖的情况下编译失败。
 * 如果将来使用 Geckolib，可替换为 AnimatedGeoModel 实现。
 */
public final class BeiModel {
    private BeiModel() {}

    public static ResourceLocation getModelLocation() {
        return ResourceLocation.fromNamespaceAndPath(guaMod.MODID, "geo/bei.geo.json");
    }

    public static ResourceLocation getTextureLocation() {
        return ResourceLocation.fromNamespaceAndPath(guaMod.MODID, "textures/entity/bei.png");
    }

    public static ResourceLocation getAnimationFileLocation() {
        return ResourceLocation.fromNamespaceAndPath(guaMod.MODID, "animations/bei.animation.json");
    }
}


