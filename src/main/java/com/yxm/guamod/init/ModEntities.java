package com.yxm.guamod.init;

import com.yxm.guamod.entity.WatermelonBombEntity;
import com.yxm.guamod.guaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    // 实体注册器
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, guaMod.MODID);

    // 注册西瓜炸弹实体
    public static final DeferredHolder<EntityType<?>, EntityType<WatermelonBombEntity>> WATERMELON_BOMB =
            ENTITIES.register("watermelon_bomb",
                    () -> EntityType.Builder.<WatermelonBombEntity>of(WatermelonBombEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)          // 碰撞箱大小
                            .clientTrackingRange(4)     // 客户端追踪距离
                            .updateInterval(10)         // 更新间隔
                            .build("watermelon_bomb"));
}