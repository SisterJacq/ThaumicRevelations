package mortvana.thaumrev.item.data;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import thaumcraft.common.entities.EntityAspectOrb;

import mortvana.melteddashboard.item.data.ArmorBehavior;
import mortvana.melteddashboard.util.helpers.NBTHelper;
import mortvana.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import mortvana.melteddashboard.util.helpers.science.MathHelper;
import mortvana.melteddashboard.util.libraries.StringLibrary;

import mortvana.thaumrev.common.ThaumicRevelations;

public class ArmorBehaviorPrimalRobes extends ArmorBehavior {

	public static ArmorBehaviorPrimalRobes instance = new ArmorBehaviorPrimalRobes();

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if (!world.isRemote) {
			int r = MathHelper.random(20000);

			if (r < 20 || player.ticksExisted % 20 == 0) {
				ensureNBT(armor);
				int charge = NBTHelper.getInt(armor, StringLibrary.CHARGE);

				if (r < 20) {
					if (charge <= 20) {
						charge++;

					} else {
						EntityAspectOrb orb = new EntityAspectOrb(player.worldObj, player.posX, player.posY, player.posZ, ThaumcraftHelper.getRandomPrimal(), 1);
						player.worldObj.spawnEntityInWorld(orb);
					}
				}

				if (armor.getItemDamage() > 0 && ((player.ticksExisted % 160 == 0) || (player.ticksExisted % 80 == 0 && charge <= 10) || (player.ticksExisted % 40 == 0 && charge <= 15)) || player.ticksExisted % 20 == 0 && charge == 20) {
					armor.damageItem(-1, player);
					charge--;
				}
				NBTHelper.setInt(armor, StringLibrary.CHARGE, charge);
			}
		}
	}

	public void ensureNBT(ItemStack armor) {
		NBTHelper.ensureNBT(armor);
		armor.getTagCompound().setInteger(StringLibrary.CHARGE, 0);
	}
}
