package com.yxm.guamod.item;

import com.yxm.guamod.entity.WatermelonBombEntity;
import com.yxm.guamod.init.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class ZuanguaSword extends SwordItem {

    public ZuanguaSword(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // 获取 BOOM 附魔的 Holder
        Holder<Enchantment> boomHolder = level.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolder(ModEnchantments.BOOM_KEY)
                .orElse(null);

        if (boomHolder == null) {
            // 附魔未加载（例如 JSON 缺失），直接返回原版行为
            return super.use(level, player, hand);
        }

        // 直接通过 ItemStack 获取附魔等级
        int boomLevel = stack.getEnchantmentLevel(boomHolder);

        if (boomLevel > 0) {
            if (!level.isClientSide) {
                boolean isCreative = player.isCreative();
                if (!isCreative) {
                    // 生存模式：检查并消耗西瓜片
                    if (player.getInventory().clearOrCountMatchingItems((itemStack) -> itemStack.is(net.minecraft.world.item.Items.MELON_SLICE), 1, player.getInventory()) < 1) {
                        player.sendSystemMessage(net.minecraft.network.chat.Component.literal("西瓜片不足！"));
                        return InteractionResultHolder.fail(stack);
                    }
                }
                // 发射西瓜炸弹
                WatermelonBombEntity bomb = new WatermelonBombEntity(level, player);
                bomb.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(bomb);
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            }
            player.getCooldowns().addCooldown(this, 5);
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }
        return super.use(level, player, hand);
    }
}