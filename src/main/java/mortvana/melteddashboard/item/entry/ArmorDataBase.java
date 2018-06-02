package mortvana.melteddashboard.item.entry;

import net.minecraft.item.EnumRarity;

public abstract class ArmorDataBase {

	protected String modName;
	protected String icon;
	protected String sheet;
	protected String repair;
	protected EnumRarity rarity;
	protected String unlocName;
	protected String regName;
	protected int color;
	protected boolean showInCreative = true;

	/** GETTERS **/
	public String getModName() {
		return modName;
	}

	public String getIcon() {
		return icon;
	}

	public String getSheet() {
		return sheet;
	}

	public String getRepair() {
		return repair;
	}

	public EnumRarity getRarity() {
		return rarity;
	}

	public String getUnlocName() {
		return unlocName;
	}

	public String getRegName() {
		return regName;
	}

	public int getColor() {
		return color;
	}

	public boolean getShowInCreative() {
		return showInCreative;
	}

	public boolean getColorized() {
		return color != 0xFFFFFF;
	}
}
