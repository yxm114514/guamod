package com.yxm.guamod.item;

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
    public static final DeferredItem<ZuanguaSword> ZUANGUA_SWORD = ITEMS.register("zuangua_sword",
            () -> new ZuanguaSword(Tiers.DIAMOND, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 500, -0.4F))));
    public Moditems(IEventBus modBus) {
        // 将物品注册表添加到 Mod 事件总线
        ITEMS.register(modBus);
    }
}
