package com.yxm.guamod.init;

import com.yxm.guamod.entity.WatermelonBombEntity;
import com.yxm.guamod.entity.BeiEntity;
import com.yxm.guamod.guaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, guaMod.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<WatermelonBombEntity>> WATERMELON_BOMB =
            ENTITIES.register("watermelon_bomb",
                    () -> EntityType.Builder.<WatermelonBombEntity>of(WatermelonBombEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("watermelon_bomb"));

    public static final DeferredHolder<EntityType<?>, EntityType<BeiEntity>> BEI =
            ENTITIES.register("bei",
                    () -> EntityType.Builder.<BeiEntity>of(BeiEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .clientTrackingRange(8)
                            .updateInterval(3)
                            .build("bei"));
}