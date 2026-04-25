package com.yxm.guamod.init;

import com.yxm.guamod.guaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DeathMessageType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDamageTypes {
    public static final DeferredRegister<DamageType> DAMAGE_TYPES = DeferredRegister.create(Registries.DAMAGE_TYPE, guaMod.MODID);

    public static final DeferredHolder<DamageType, DamageType> WATERMELON_BOMB = DAMAGE_TYPES.register("watermelon_bomb", () -> new DamageType("watermelon_bomb", DamageScaling.NEVER, 0.1f, DamageEffects.HURT, DeathMessageType.DEFAULT));

    public static void register(IEventBus eventBus) {
        DAMAGE_TYPES.register(eventBus);
    }
}
