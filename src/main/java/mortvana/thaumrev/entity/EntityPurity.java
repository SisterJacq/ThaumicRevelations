package mortvana.thaumrev.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import mortvana.thaumrev.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import thaumcraft.common.Thaumcraft;

public class EntityPurity extends EntityThrowable {

	public EntityPurity(World world) {
		super(world);
	}

	public EntityPurity(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityPurity(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.001F;
	}

	@Override
	public void onUpdate() {
		if (worldObj.isRemote) {
			for (int i = 0; i < 3; i++) {
				//TODO: Switch to the CoFH Method
				Thaumcraft.proxy.wispFX2(worldObj, offsetUpdate(posX), offsetUpdate(posY), offsetUpdate(posZ), 0.3F, 2, true, false, 0.02F);

				double y2 = (posY + prevPosY) / 2.0D + offset() * 0.2F;
				double z2 = (posZ + prevPosZ) / 2.0D + offset() * 0.2F;

				Thaumcraft.proxy.wispFX2(worldObj, offsetAvg(posX, prevPosX), offsetAvg(posY, prevPosY), offsetAvg(posZ, prevPosZ), 0.3F, 2, true, false, 0.02F);
			}
		}
	}

	@Override
	public void onImpact(MovingObjectPosition mop) {
		for (int i = 0; i < 27; i++) {
			Thaumcraft.proxy.wispFX3(worldObj, offsetImpact(posX), offsetImpact(posY), offsetImpact(posZ), offsetImpact2(posX), offsetImpact2(posY), offsetImpact2(posZ), 0.3F, 2, true, 0.02F);
		}

		if (!worldObj.isRemote) {
			ThaumcraftHelper.checkAndPurify(mop);
			setDead();
		}
	}


	public double offset() {
		return worldObj.rand.nextFloat() - worldObj.rand.nextFloat();
	}

	public double avg(double d1, double d2) {
		return (d1 + d2) / 2.0D;
	}

	public double offsetUpdate(double val) {
		return val + offset() * 0.2F;
	}

	public double offsetAvg(double d1, double d2) {
		return offsetUpdate(avg(d1, d2));
	}

	public double offsetImpact(double val) {
		return val + (offset() * 0.3F);
	}

	public double offsetImpact2(double val) {
		return val + (offset() * 0.3F) * 8.0F;
	}


}
