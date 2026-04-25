package com.yxm.guamod;

import com.yxm.guamod.entity.WatermelonBombEntity;
import com.yxm.guamod.init.ModDamageTypes;
import com.yxm.guamod.init.ModEnchantments;
import com.yxm.guamod.init.ModEntities;
import com.yxm.guamod.item.Moditems;
import com.yxm.guamod.item.ZuanguaSword;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.yxm.guamod.sound.ModSounds;

import static com.yxm.guamod.item.Moditems.ZUANGUA_SWORD;
import static com.yxm.guamod.item.Moditems.Zuangua;
import static com.yxm.guamod.item.Moditems.GOLDEN_GUA;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(guaMod.MODID)
public class guaMod {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "guamod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "guamod" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "guamod" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "guamod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // 在你的主类或一个专门的声音注册类中
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, guaMod.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> ATTACK_SOUND =
            SOUNDS.register("attack_sound", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(guaMod.MODID, "attack_sound")));
    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // Creates a creative tab with the id "guamod:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> guatab = CREATIVE_MODE_TABS.register("guatab", () -> CreativeModeTab.builder()
            .title(Component.translatable("guaitemGroup.guamod")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> new ItemStack(Items.GOLD_INGOT))
            .build());


    // 使用钻石剑的属性（攻击伤害+3，攻击速度-2.4），但自定义工具等级

    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public guaMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading


        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);
        Moditems.ITEMS.register(modEventBus);
        SOUNDS.register(modEventBus);
        ModSounds.SOUNDS.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (guaMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

       // if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
       //     LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
       // }

     //   LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        //Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(guatab.getKey())) {
            event.accept(Zuangua.get());
            event.accept(GOLDEN_GUA.get());
            event.accept(ZUANGUA_SWORD.get());
            // 2. 从事件提供的 HolderLookup.Provider 中获取附魔的 Holder
            //    这是最安全的方式，直接使用了事件上下文中的注册表查找器
            event.getParameters().holders().lookup(Registries.ENCHANTMENT)
                    .flatMap(lookup -> lookup.get(ModEnchantments.BOOM_KEY))
                    .ifPresent(boomHolder -> {
                        ItemStack boomBook = new ItemStack(Items.ENCHANTED_BOOK);
                        // 直接使用 enchant 方法添加附魔（等级1）
                        boomBook.enchant(boomHolder, 1);
                        event.accept(boomBook);
                    });
        }
    }
   // SOUNDS.register(modEventBus);
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        // 死亡消息现在通过 DamageType 处理
    }
}
