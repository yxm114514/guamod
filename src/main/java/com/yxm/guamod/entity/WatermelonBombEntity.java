package com.yxm.guamod.entity;

import com.yxm.guamod.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SynchedEntityData;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class WatermelonBombEntity extends ThrowableProjectile implements ItemSupplier {

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

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        // 无需额外数据，留空即可
    }

    @Override
    public Item getItem() {
        return Items.MELON_SLICE;
    }
}