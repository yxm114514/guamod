package com.yxm.guamod;

import com.yxm.guamod.item.Moditems;
import com.yxm.guamod.item.ZuanguaSword;
import com.yxm.guamod.item.modfoods;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.yxm.guamod.sound.ModSounds;

import static com.yxm.guamod.item.Moditems.ZUANGUA_SWORD;
import static com.yxm.guamod.item.Moditems.Zuangua;
import static com.yxm.guamod.item.modfoods.GOLDEN_GUA;

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
            .icon(() -> Zuangua.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(Zuangua);
                output.accept(GOLDEN_GUA);
                output.accept(ZUANGUA_SWORD);// Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());


    // 使用钻石剑的属性（攻击伤害+3，攻击速度-2.4），但自定义工具等级

    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public guaMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading


        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        modfoods.ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);
        Moditems.ITEMS.register(modEventBus);
        SOUNDS.register(modEventBus);
        ModSounds.SOUNDS.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (guaMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
           // event.accept(EXAMPLE_BLOCK_ITEM);
        }
    }
   // SOUNDS.register(modEventBus);
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
