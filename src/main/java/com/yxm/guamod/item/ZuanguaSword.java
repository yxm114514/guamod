package com.yxm.guamod.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.player.Player;
import com.yxm.guamod.sound.ModSounds;


public class ZuanguaSword extends SwordItem {
    public ZuanguaSword(Tier tier, Item.Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        System.out.println("hurtEnemy called!");
        boolean result = super.hurtEnemy(stack, target, attacker);
        System.out.println("result = " + result);
        if (attacker instanceof Player player) {
            System.out.println("attacker is player");
            // 只在客户端播放声音
            if (player.level().isClientSide) {
                System.out.println("Playing custom sound on client");
                player.level().playSound(player, target.blockPosition(),
                        SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1.0F, 1.0F);
            } else {
                System.out.println("Not on client, skipping sound");
            }
        }
        return result;
    }
}