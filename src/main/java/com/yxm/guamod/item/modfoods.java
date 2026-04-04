package com.yxm.guamod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.yxm.guamod.guaMod.MODID;

public class modfoods {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(MODID);
    public static final DeferredItem<Item> GOLDEN_GUA = ITEMS.register("golden_gua",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(1.2F)
                            .effect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1.0F)
                            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 0), 1.0F)
                            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
                            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0F)
                            .alwaysEdible()
                            .build())));


}
