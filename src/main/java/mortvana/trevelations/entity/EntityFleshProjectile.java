package mortvana.trevelations.entity;

/*import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.magnequasar.entity.EntityThrowableProjectile;

import mortvana.trevelations.client.ClientProxy;

public class EntityFleshProjectile extends EntityThrowableProjectile {

	public EntityFleshProjectile(World world) {
		super(world);
		setKnockbackStrength(3);
	}

	public EntityFleshProjectile(World world, double x, double y, double z) {
		super(world, x, y, z);
		setKnockbackStrength(3);
	}

	public EntityFleshProjectile(World world, EntityLivingBase entity) {
		super(world, entity);
		setKnockbackStrength(3);
	}

	@Override
	protected void dropOnImpact() {
		//AbilityHelper.spawnItemAtEntity(this, new ItemStack(Items.rotten_flesh, 1, 0), 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getParticleID() {
		return ClientProxy.PARTICLE_HANDLER.THROWN_FLESH_ID;
	}
}*/