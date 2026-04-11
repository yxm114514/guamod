package com.yxm.guamod.init;

import com.yxm.guamod.guaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {
    // 定义附魔的 ResourceKey（用于在代码中引用）
    public static final ResourceKey<Enchantment> BOOM_KEY = ResourceKey.create(
            Registries.ENCHANTMENT,
            new ResourceLocation(guaMod.MODID, "boom")
    );
}