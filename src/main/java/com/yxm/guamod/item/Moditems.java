package com.yxm.guamod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.yxm.guamod.guaMod.ITEMS;
import static com.yxm.guamod.guaMod.MODID;

public class Moditems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(MODID);
    public static final DeferredItem<Item> Zuangua=

            ITEMS.register("zuangua",()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_GUA = ITEMS.register("golden_gua",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(1.2F)
                            // 所有正面效果 (每个效果持续 3 分钟 = 3600 ticks)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 3600, 1), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 3600, 3), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 0), 1.0F)      // 原 SPEED
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 3600, 0), 1.0F)           // 原 HASTE
                            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 3600, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 3600, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 0), 1.0F)        // 原 STRENGTH
                            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 3600, 0), 1.0F)                 // 原 JUMP_BOOST
                            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 3600, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 3600, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.CONDUIT_POWER, 3600, 0), 1.0F)
                            // 可选：村庄英雄（通常不通过食物获得，若需要可取消注释）
                            .effect(() -> new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 3600, 0), 1.0F)
                            .alwaysEdible()
                            .build())));
    public static final DeferredItem<ZuanguaSword> ZUANGUA_SWORD = ITEMS.register("zuangua_sword",
            () -> new ZuanguaSword(Tiers.DIAMOND, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 500, -0.4F))));
    public Moditems(IEventBus modBus) {
        // 将物品注册表添加到 Mod 事件总线
        ITEMS.register(modBus);
    }
    // 附魔书物品（与普通物品一样，不需要特殊类）
   // public static final DeferredItem<Item> BOOM_BOOK = ITEMS.register("boom_book",
  //          () -> new Item(new Item.Properties().stacksTo(1)));  // 最大堆叠1个
}