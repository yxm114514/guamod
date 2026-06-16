package com.yxm.guamod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * 一个非常简单的生物实体示例（bei）。
 * 该实体目前只实现了基础构造和简单的AI目标，便于在世界中生成和测试。
 */
public class BeiEntity extends PathfinderMob {

    public BeiEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        // 无需特殊路径惩罚设置，使用默认行为
    }

    @Override
    protected void registerGoals() {
        // 简单的闲逛与观察玩家行为
        this.goalSelector.addGoal(0, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        // 暂无同步数据
    }

    // 可选：提供属性构建器，便于在注册时使用
    public static AttributeSupplier.Builder createAttributes() {
        return net.minecraft.world.entity.Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }
}


