package com.github.x3r.solaris.common.entity.brain;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class UrborosMoveControl extends MoveControl {
    public UrborosMoveControl(Mob pMob) {
        super(pMob);
    }

    @Override
    public void tick() {
        if (this.operation == MoveControl.Operation.MOVE_TO) {
            this.operation = MoveControl.Operation.WAIT;
            double xDist = this.wantedX - this.mob.getX();
            double yDist = this.wantedY - this.mob.getY();
            double zDist = this.wantedZ - this.mob.getZ();
            double dist = Mth.square(xDist) + Mth.square(yDist) + Mth.square(zDist);
            if (dist < 0.001F) {
                this.mob.setYya(0.0F);
                this.mob.setZza(0.0F);
                return;
            }

            float angleToTarget = (float)(Mth.atan2(zDist, xDist) * (180F / (float)Math.PI)) - 90.0F;
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), angleToTarget, 10F));

            float speed = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.FLYING_SPEED));
            this.mob.setSpeed(speed);

            double hDist = Mth.sqrt((float) (Mth.square(xDist) + Mth.square(zDist)));
            if(Math.abs(yDist) > 1.0E-5F || Math.abs(hDist) > 1.0E-5F) {
                float pitchToTarget = (float)(-(Mth.atan2(yDist, hDist * (180F / (float)Math.PI))));
                this.mob.setXRot(this.rotlerp(this.mob.getXRot(), pitchToTarget, 10F));
                float ySpeed = Mth.abs((float) yDist)/(Mth.abs((float) yDist)+1);
                this.mob.setYya(yDist > 0.0D ? ySpeed : -ySpeed);
            }
        } else {
            this.mob.setYya(0.0F);
            this.mob.setZza(0.0F);
        }
    }
}
