package com.yxm.guamod.item;

import com.yxm.guamod.entity.WatermelonBombEntity;
import com.yxm.guamod.init.ModEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class ZuanguaSword extends SwordItem {

    public ZuanguaSword(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // 1. 从注册表中获取 BOOM 附魔的 Holder（推荐方式）
        Holder<Enchantment> boomHolder = level.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolder(ModEnchantments.BOOM_KEY)
                .orElse(null);

        // 2. 检查附魔是否存在
        if (boomHolder == null) {
            // 附魔未加载，可能是 JSON 文件缺失或命名错误
            System.err.println("BOOM enchantment not found! Check your datapack.");
            return super.use(level, player, hand);
        }

        // 3. 获取剑上的附魔等级
        //int boomLevel = EnchantmentHelper.getEnchantmentLevel(boomHolder, stack);
        int boomLevel = stack.getEnchantmentLevel(Holder.direct(boom));
        // 4. 如果附魔等级 > 0，则发射西瓜炸弹
        if (boomLevel > 0) {
            // 只在服务端生成实体
            if (!level.isClientSide) {
                WatermelonBombEntity bomb = new WatermelonBombEntity(level, player);
                // 发射参数：速度 1.5，偏差 1.0
                bomb.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(bomb);
                // 消耗耐久 1 点
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            }
            // 设置冷却时间 20 tick（1 秒）
            player.getCooldowns().addCooldown(this, 20);
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }
        // 没有附魔则执行原版右键逻辑
        return super.use(level, player, hand);
    }
}