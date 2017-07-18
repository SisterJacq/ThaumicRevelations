package mortvana.thaumrev.melteddashboard.intermod.baubles.util;

import baubles.api.BaubleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public abstract class BaubleData {

	public BaubleType type;
	public boolean canEquip, canUnequip;

	public BaubleData(BaubleType type) {
		this(type, true, true);
	}

	public BaubleData(BaubleType type, boolean canEquip, boolean canUnequip) {
		this.type = type;
		this.canEquip = canEquip;
		this.canUnequip = canUnequip;
	}

	public BaubleType getType() {
		return type;
	}

	public boolean canEquip() {
		return canEquip;
	}

	public boolean canUnequip() {
		return canUnequip;
	}

	public abstract void onWornTick(ItemStack itemstack, EntityLivingBase player);

	public abstract void onEquipped(ItemStack itemstack, EntityLivingBase player);

	public abstract void onUnequipped(ItemStack itemstack, EntityLivingBase player);

	public BaubleData setEquip(boolean canEquip) {
		this.canEquip = canUnequip;
		return this;
	}

	public BaubleData setUnequip(boolean canUnequip) {
		this.canUnequip = canUnequip;
		return this;
	}
}
