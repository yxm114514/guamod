package com.yxm.guamod.init;

import com.yxm.guamod.guaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {
    // 附魔的资源键
    public static final ResourceKey<Enchantment> BOOM_KEY = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(guaMod.MODID, "boom")
    );
}