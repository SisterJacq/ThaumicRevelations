package mortvana.thaumrev.util.item.data;

import net.minecraft.item.EnumRarity;

public class ThaumRevMaterialData {

	protected String icon;
	protected String repair;
	protected String texture;
	protected EnumRarity rarity;
	protected String unlocName;
	protected String regName;
	protected int color = 0xFFFFFF;
	protected int discount = 0;

	public ThaumRevMaterialData(String icon, String repair, String texture, EnumRarity rarity, String unlocName, String regName, int color) {
		this.icon = icon;
		this.repair = repair;
		this.texture = texture;
		this.rarity = rarity;
		this.unlocName = unlocName;
		this.regName = regName;
		this.color = color;
	}

	public ThaumRevMaterialData(String icon, String repair, String texture, EnumRarity rarity, String unlocName, String regName) {
		this.icon = icon;
		this.repair = repair;
		this.texture = texture;
		this.rarity = rarity;
		this.unlocName = unlocName;
		this.regName = regName;
	}

	public ThaumRevMaterialData() {
		//For automatic use only!
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

	public void setColor(int color) {
		this.color = color;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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

	public boolean getColorized() {
		return color != 0xFFFFFF;
	}

	public int getDiscount() {
		return discount;
	}
}
