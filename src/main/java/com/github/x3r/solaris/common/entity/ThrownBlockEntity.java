package com.github.x3r.solaris.common.entity;

import com.github.x3r.solaris.common.registry.EntityRegistry;
import net.minecraft.CrashReportCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.Optional;

public class ThrownBlockEntity extends Projectile {

    private BlockState blockState = Blocks.SAND.defaultBlockState();

    private int age = 0;
    protected static final EntityDataAccessor<BlockPos> DATA_START_POS = SynchedEntityData.defineId(ThrownBlockEntity.class, EntityDataSerializers.BLOCK_POS);

    public ThrownBlockEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrownBlockEntity(BlockState state, double pX, double pY, double pZ, Level pLevel) {
        super(EntityRegistry.THROWN_BLOCK.get(), pLevel);
        this.blockState = state;
        this.blocksBuilding = true;
        this.setPos(pX, pY, pZ);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
        this.setStartPos(this.blockPosition());
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    public void setStartPos(BlockPos pStartPos) {
        this.entityData.set(DATA_START_POS, pStartPos);
    }

    public BlockPos getStartPos() {
        return this.entityData.get(DATA_START_POS);
    }

    @Override
    protected MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_START_POS, BlockPos.ZERO);
    }

    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }

    @Override
    public void tick() {
        if (this.blockState.isAir()) {
            this.discard();
        } else {
            this.move(MoverType.SELF, this.getDeltaMovement());
            if(!level().isClientSide) {
                if(age++ > 80) {
                    breakThrownBlock();
                }
                level().getEntities(this, getBoundingBox().inflate(0.25), entity -> this.canHitEntity(entity) && !this.ownedBy(entity)).forEach(entity -> {
                    if(entity instanceof LivingEntity livingEntity) {
                        livingEntity.knockback(3F, this.getX() - livingEntity.getX(), this.getZ() - livingEntity.getZ());
                        livingEntity.setTicksFrozen(this.getTicksRequiredToFreeze()+60);
                    }
                    LivingEntity shooter = this.getOwner() instanceof LivingEntity livingEntity ? livingEntity : null;
                    entity.hurt(damageSources().mobProjectile(this, shooter), 16);
                    breakThrownBlock();
                });
                if (this.onGround()) {
                    breakThrownBlock();
                }
            }
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.98, 0.95, 0.98));
        }
    }

    private void breakThrownBlock() {
        if(!this.isRemoved()) {
            ((ServerLevel) level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState), getX(), getY(), getZ(), 100, 0, 0, 0, 0.15);
            this.discard();
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.put("BlockState", NbtUtils.writeBlockState(this.blockState));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.blockState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), pCompound.getCompound("BlockState"));
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    @Override
    public void fillCrashReportCategory(CrashReportCategory pCategory) {
        super.fillCrashReportCategory(pCategory);
        pCategory.setDetail("Imitating BlockState", this.blockState.toString());
    }

    public BlockState getBlockState() {
        return blockState;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this, Block.getId(this.getBlockState()));
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);
        this.blockState = Block.stateById(pPacket.getData());
        double d0 = pPacket.getX();
        double d1 = pPacket.getY();
        double d2 = pPacket.getZ();
        this.setPos(d0, d1, d2);
        this.setStartPos(this.blockPosition());
    }
}
