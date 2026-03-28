package com.yxm.guamod.sound;

import com.yxm.guamod.guaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, guaMod.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> ATTACK_SOUND =
            SOUNDS.register("attack_sound", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(guaMod.MODID, "attack_sound")));
}