package com.yxm.guamod.renderer;

import com.yxm.guamod.entity.WatermelonBombEntity;
import com.yxm.guamod.guaMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

// 西瓜炸弹渲染器：继承 ThrownItemRenderer，自定义纹理
public class WatermelonBombRenderer extends ThrownItemRenderer<WatermelonBombEntity> {

    public WatermelonBombRenderer(EntityRendererProvider.Context context) {
        super(context, 0.5f, true); // 缩放 0.5，启用阴影
    }

    // 使用默认纹理（MELON_SLICE）
}