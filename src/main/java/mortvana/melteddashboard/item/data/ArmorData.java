package mortvana.melteddashboard.item.data;

import net.minecraft.item.EnumRarity;

import mortvana.melteddashboard.util.libraries.ColorLibrary;

public class ArmorData extends ArmorDataBase {

	public ArmorData(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative) {
		setModName(modName);
		setIcon(icon);
		setSheet(sheet);
		setRepair(repair);
		setRarity(rarity);
		setUnlocName(unlocName);
		setRegName(regName);
		setColor(color);
		setShowInCreative(showInCreative);
	}

	public ArmorData(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color) {
		this(modName, icon, sheet, repair, rarity, unlocName, regName, color, true);
	}

	public ArmorData(String modName, String icon, String sheet, String repair, int rarity, String unlocName, String regName, int color) {
		this(modName, icon, sheet, repair, EnumRarity.values()[rarity], unlocName, regName, color, true);
	}

	public ArmorData(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName) {
		this(modName, icon, sheet, repair, rarity, unlocName, regName, ColorLibrary.CLEAR, true);
	}

	public ArmorData(String modName, String icon, String sheet, String repair, int rarity, String unlocName, String regName) {
		this(modName, icon, sheet, repair, EnumRarity.values()[rarity], unlocName, regName, ColorLibrary.CLEAR, true);
	}

	public ArmorData(String modName, String icon, String sheet, String repair, EnumRarity rarity, String name) {
		this(modName, icon, sheet, repair, rarity, name, name, ColorLibrary.CLEAR, true);
	}

	public ArmorData(String modName, String icon, String sheet, String repair, int rarity, String name) {
		this(modName, icon, sheet, repair, EnumRarity.values()[rarity], name, name, ColorLibrary.CLEAR, true);
	}

	public ArmorData(String modName, String icon, String sheet, String repair, String name) {
		this(modName, icon, sheet, repair, EnumRarity.common, name, name, ColorLibrary.CLEAR, true);
	}

	public ArmorData(String modName, String icon, String sheet, String name) {
		this(modName, icon, sheet, "", EnumRarity.common, name, name, ColorLibrary.CLEAR, true);
	}

	public ArmorData(String icon, String sheet, String name) {
		this("fluxgear", icon, sheet, "", EnumRarity.common, name, name, ColorLibrary.CLEAR, true);
	}

	/** SETTERS **/
	public ArmorData setModName(String modName) {
		this.modName = modName;
		return this;
	}

	public ArmorData setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public ArmorData setSheet(String texture) {
		this.sheet = texture;
		return this;
	}

	public ArmorData setRepair(String repair) {
		this.repair = repair;
		return this;
	}

	public ArmorData setRarity(EnumRarity rarity) {
		this.rarity = rarity;
		return this;
	}

	public ArmorData setRarity(int rarity) {
		this.rarity = EnumRarity.values()[rarity];
		return this;
	}

	public ArmorData setUnlocName(String unlocName) {
		this.unlocName = unlocName;
		return this;
	}

	public ArmorData setRegName(String regName) {
		this.regName = regName;
		return this;
	}

	public ArmorData setColor(int color) {
		this.color = color;
		return this;
	}

	public ArmorData setShowInCreative(boolean bool) {
		showInCreative = bool;
		return this;
	}




}
