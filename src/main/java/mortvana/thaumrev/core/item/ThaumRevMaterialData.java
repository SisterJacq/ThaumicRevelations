package mortvana.thaumrev.core.item;

import net.minecraft.item.EnumRarity;

public class ThaumRevMaterialData {

	protected int index;
	protected String name;
	protected String sheet;
	protected String icon;
	protected String repair;
	protected String texture;
	protected EnumRarity rarity;
	protected String unlocName;
	protected String regName;

	public ThaumRevMaterialData(int index, String name, String sheet, String icon, String repair, String texture, EnumRarity rarity, String unlocName, String regName) {
		this.index = index;
		this.name = name;
		this.sheet = sheet;
		this.icon = icon;
		this.repair = repair;
		this.texture = texture;
		this.rarity = rarity;
		this.unlocName = unlocName;
		this.regName = regName;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setRepair(String repair) {
		this.repair = repair;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public void setRarity(EnumRarity rarity) {
		this.rarity = rarity;
	}

	public void setUnlocName(String unlocName) {
		this.unlocName = unlocName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public String getSheet() {
		return sheet;
	}

	public String getIcon() {
		return icon;
	}

	public String getRepair() {
		return repair;
	}

	public String getTexture() {
		return texture;
	}

	public EnumRarity rarity() {
		return rarity;
	}

	public String getUnlocName() {
		return unlocName;
	}

	public String getRegName() {
		return regName;
	}

}
