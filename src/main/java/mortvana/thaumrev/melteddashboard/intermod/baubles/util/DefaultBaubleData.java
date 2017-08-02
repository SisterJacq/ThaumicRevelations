package mortvana.thaumrev.melteddashboard.intermod.baubles.util;

import baubles.api.BaubleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class DefaultBaubleData extends BaubleData {

	public DefaultBaubleData(BaubleType type) {
		super(type, true, true);
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}
}
