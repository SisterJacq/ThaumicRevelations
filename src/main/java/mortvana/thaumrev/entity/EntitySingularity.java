package mortvana.thaumrev.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

public class EntitySingularity extends EntityThrowable {

    public EntitySingularity(World world) {
        super(world);
    }

    public EntitySingularity(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    public EntitySingularity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected float func_70182_d() {
        return 0.75F;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (worldObj.isRemote) {
            for (int i = 0; i < 3; i++) {
                //TODO: CoFH-style Particles
                Thaumcraft.proxy.wispFX2(worldObj, offset(posX), offset(posY), offset(posZ), 0.3F, 5, true, true, 0.02F);
                Thaumcraft.proxy.wispFX2(worldObj, offset(posX, prevPosX), offset(posY, prevPosY), offset(posZ, prevPosZ), 0.3F, 5, true, true, 0.02F);
                Thaumcraft.proxy.sparkle(offset2(posX), offset2(posY), offset2(posZ), 6);
            }
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (!worldObj.isRemote) {
            worldObj.createExplosion(null, posX, posY, posZ, 1.66F, worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
            setDead();
        }
    }

    @Override
    public float getShadowSize() {
        return 0.1F;
    }

    public double offset() {
        return worldObj.rand.nextFloat() - worldObj.rand.nextFloat();
    }

    public double offset(double val) {
        return val + (offset() * 0.3F);
    }

    public double offset(double d1, double d2) {
        return offset((d1 + d2) / 2.0D);
    }

    public float offset2(double val) {
        return (float)(val + offset() * 0.1F);
    }
}
