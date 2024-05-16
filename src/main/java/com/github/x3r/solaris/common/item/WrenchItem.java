package com.github.x3r.solaris.common.item;

import com.github.x3r.solaris.common.block.AncientGlowingBricksBlock;
import com.github.x3r.solaris.common.helper.ParticleHelper;
import com.github.x3r.solaris.common.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class WrenchItem extends Item {
    public WrenchItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
        if(blockState.is(BlockRegistry.ANCIENT_GLOWING_BRICKS.get())) {
            Direction direction = blockState.getValue(DirectionalBlock.FACING);
            if(context.getClickedFace().equals(direction)) {
                Vector3f center = context.getClickedPos().getCenter().toVector3f();
                Vector3f v1 = new Vector3f(0F, 0.5F, 0F);
                Vector3f v2 = Vec3.atLowerCornerOf(context.getClickedFace().getNormal()).toVector3f().mul(0.5F);

                float yRot = direction.get2DDataValue() != -1 ? (float) Math.toRadians(direction.toYRot()+180) : 0F;

                Quaternionf q1 = new Quaternionf().rotateY(yRot).rotateTo(v2, v1);

                Vector3f clickVec = context.getClickLocation().subtract(context.getClickedPos().getCenter()).toVector3f();
                Vector3f clickVecR = q1.transform(clickVec);

                List<Float> l = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    if(v1.get(i) != clickVecR.get(i)) {
                        l.add(clickVecR.get(i)-v1.get(i));
                    }
                }

                double angle = Mth.wrapDegrees(Math.toDegrees(Mth.atan2(l.get(0), l.get(1))));
                toggleBrickState(context.getLevel(), context.getClickedPos(), angle);
                context.getLevel().scheduleTick(context.getClickedPos(), context.getLevel().getBlockState(context.getClickedPos()).getBlock(), 4);
                return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
            }
        }
        return InteractionResult.PASS;
    }

    private void toggleBrickState(Level level, BlockPos pos, double angle) {
        BlockState state = level.getBlockState(pos);
        if(angle > -45F && angle < 45F) {
            boolean b = state.getValue(AncientGlowingBricksBlock.UP);
            level.setBlockAndUpdate(pos, state.setValue(AncientGlowingBricksBlock.UP, !b));
        }
        if(angle > 45F && angle < 135) {
            boolean b = state.getValue(AncientGlowingBricksBlock.LEFT);
            level.setBlockAndUpdate(pos, state.setValue(AncientGlowingBricksBlock.LEFT, !b));
        }
        if((angle > 135 && angle < 180) || (angle > -180 && angle < -135)) {
            boolean b = state.getValue(AncientGlowingBricksBlock.DOWN);
            level.setBlockAndUpdate(pos, state.setValue(AncientGlowingBricksBlock.DOWN, !b));
        }
        if(angle > -135 && angle < -45) {
            boolean b = state.getValue(AncientGlowingBricksBlock.RIGHT);
            level.setBlockAndUpdate(pos, state.setValue(AncientGlowingBricksBlock.RIGHT, !b));
        }
    }
}
