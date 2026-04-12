package com.yxm.guamod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

@EventBusSubscriber(modid = guaMod.MODID)   // 不再指定 bus，默认就是 MOD 总线
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    public static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), () -> "", Config::validateItemName);

    static final ModConfigSpec SPEC = BUILDER.build();

    // 缓存配置值
    public static boolean logDirtBlock;
    public static int magicNumber;
    public static String magicNumberIntroduction;
    public static List<String> itemStrings;

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    @SubscribeEvent
    static void onModConfigEvent(ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {
            logDirtBlock = LOG_DIRT_BLOCK.getAsBoolean();
            magicNumber = MAGIC_NUMBER.getAsInt();
            magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();
            itemStrings = List.copyOf(ITEM_STRINGS.get());

            // 在此处执行需要配置的初始化逻辑
            if (logDirtBlock) {
                guaMod.LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(net.minecraft.world.level.block.Blocks.DIRT));
            }
            guaMod.LOGGER.info("{}{}", magicNumberIntroduction, magicNumber);
            itemStrings.forEach(item -> guaMod.LOGGER.info("ITEM >> {}", item));
        }
    }
}