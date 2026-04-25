package com.yxm.guamod.entity;

import com.yxm.guamod.init.ModEntities;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class WatermelonBombEntity extends ThrowableProjectile {

    public WatermelonBombEntity(EntityType<? extends ThrowableProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public WatermelonBombEntity(Level level, LivingEntity shooter) {
        super(ModEntities.WATERMELON_BOMB.get(), shooter, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        explode();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        explode();
    }

    private void explode() {
        if (!this.level().isClientSide) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(),
                    4.0F, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

    // 正确重写 defineSynchedData（带参数）
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        // 无需同步数据
    }
}