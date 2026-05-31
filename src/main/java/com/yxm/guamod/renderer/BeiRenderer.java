package com.yxm.guamod.renderer;

import com.yxm.guamod.entity.BeiEntity;
import com.yxm.guamod.guaMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BeiRenderer extends EntityRenderer<BeiEntity> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(guaMod.MODID, "textures/entity/bei.png");
    private final ItemRenderer itemRenderer;

    public BeiRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public ResourceLocation getTextureLocation(BeiEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BeiEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(entityYaw));
        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(entity.getXRot()));
        ItemStack stack = new ItemStack(Items.MELON_SLICE);
        this.itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.level(), 0);
        poseStack.popPose();
        // 不调用 super.render，直接完成自定义渲染
    }
}

