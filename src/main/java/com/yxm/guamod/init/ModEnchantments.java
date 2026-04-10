package com.yxm.guamod.init;

import com.yxm.guamod.guaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEnchantments {

    // 创建附魔的 DeferredRegister
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(Registries.ENCHANTMENT, guaMod.MODID);

    // 注册 BOOM 附魔（使用新的 Enchantment.Builder 风格）
    public static final DeferredHolder<Enchantment, Enchantment> BOOM = ENCHANTMENTS.register("boom",
            () -> new Enchantment(Enchantment.definition(
                    ItemTags.SWORDS,           // 可附魔的物品：剑类物品
                    1,                         // 最大等级（这里只有1级）
                    10,                        // 权重（影响附魔出现概率）
                    Enchantment.Rarity.RARE    // 稀有度
            )) {
                // 重写最小附魔等级消耗（20级）
                @Override
                public int getMinCost(int level) {
                    return 20;
                }
                // 重写最大附魔等级消耗（50级）
                @Override
                public int getMaxCost(int level) {
                    return 50;
                }
            });
}