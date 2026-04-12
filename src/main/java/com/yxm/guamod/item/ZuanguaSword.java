package com.yxm.guamod.item;

import com.yxm.guamod.entity.WatermelonBombEntity;
import com.yxm.guamod.init.ModEnchantments;
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
import net.minecraft.core.Holder;

public class ZuanguaSword extends SwordItem {

    public ZuanguaSword(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // 获取 Enchantment 对象
        Enchantment boom = level.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .get(ModEnchantments.BOOM_KEY);

        // 使用 Holder.direct() 包装成 Holder<Enchantment>
        int boomLevel = stack.getEnchantmentLevel(Holder.direct(boom));

        if (boomLevel > 0) {
            if (!level.isClientSide) {
                WatermelonBombEntity bomb = new WatermelonBombEntity(level, player);
                bomb.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(bomb);
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            }
            player.getCooldowns().addCooldown(this, 20);
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }
        return super.use(level, player, hand);
    }
}