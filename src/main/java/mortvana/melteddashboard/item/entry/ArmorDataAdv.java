package mortvana.melteddashboard.item.entry;

import net.minecraft.item.EnumRarity;

import thaumcraft.api.aspects.Aspect;

import mortvana.thaumrev.util.enums.EnumPrimalAspect;

public class ArmorDataAdv extends ArmorData {
	protected int[] discount = {0, 0, 0, 0, 0, 0};
	protected boolean goggles = false;
	protected int maxEnergy = 0;

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative, int[] discount, boolean goggles, int flux) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName, color, showInCreative);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName, color, showInCreative);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName, color);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, int rarity, String unlocName, String regName, int color) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName, color);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, int rarity, String unlocName, String regName) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, int rarity, String name) {
		super(modName, icon, sheet, repair, rarity, name);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String name) {
		super(modName, icon, sheet, repair, rarity, name);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, String name) {
		super(modName, icon, sheet, repair, name);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String name) {
		super(modName, icon, sheet, name);
	}

	public ArmorDataAdv(String icon, String sheet, String name) {
		super(icon, sheet, name);

	}

	/** SETTERS **/
	public ArmorDataAdv setDiscount(int[] discount) {
		if (discount.length == 6) {
			this.discount = discount;
		}
		return this;
	}

	public ArmorDataAdv setDiscount(int discount) {
		this.discount = new int[] { discount, discount, discount, discount, discount, discount };
		return this;
	}

	public ArmorDataAdv setDiscount(int index, int discount) {
		if (index >= 0 && index < 6) {
			this.discount[index] = discount;
		}
		return this;
	}

	public ArmorDataAdv setDiscount(EnumPrimalAspect aspect, int discount) {
		this.discount[aspect.ordinal()] = discount;
		return this;
	}

	public ArmorDataAdv setDiscount(Aspect aspect, int discount) {
		if (aspect.isPrimal()) {
			this.discount[EnumPrimalAspect.getPrimal(aspect).ordinal()] = discount;
		}
		return this;
	}

	public ArmorDataAdv setGoggles(boolean bool) {
		goggles = bool;
		return this;
	}

	public ArmorDataAdv setMaxEnergy(int maxFlux) {
		maxEnergy = maxFlux;
		return this;
	}

	/** GETTERS **/
	public int[] getDiscount() {
		return discount;
	}

	public int getDiscount(int index) {
		return (index >= 0 && index < 6) ? discount[index] : 0;
	}

	public int getDiscount(EnumPrimalAspect aspect) {
		return discount[aspect.ordinal()];
	}

	public int getDiscount(Aspect aspect) {
		return aspect.isPrimal() ? discount[EnumPrimalAspect.getPrimal(aspect).ordinal()] : 0;
	}

	public boolean getGoggles() {
		return goggles;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

}
