package mortvana.thaumrev.core.item;

import net.minecraft.item.EnumRarity;

import mortvana.thaumrev.core.common.ThaumicRevelations;

public class ThaumRevMaterialDataSet {
	protected ThaumRevMaterialData helmData, chestData, legsData, bootData;

	public ThaumRevMaterialDataSet(ThaumRevMaterialData helmData, ThaumRevMaterialData chestData, ThaumRevMaterialData legsData, ThaumRevMaterialData bootData) {
		this.helmData = helmData;
		this.chestData = chestData;
		this.legsData = legsData;
		this.bootData = bootData;
	}

	public ThaumRevMaterialDataSet() {
		helmData = new ThaumRevMaterialData();
		chestData = new ThaumRevMaterialData();
		legsData = new ThaumRevMaterialData();
		bootData = new ThaumRevMaterialData();
	}

	public ThaumRevMaterialData getData(int type) {
		switch (type) {
			case 0:
				return helmData;
			case 1:
				return chestData;
			case 2:
				return legsData;
			case 3:
				return bootData;
			default:
				ThaumicRevelations.logger.error("Someone tried to get data for a non-existent armor type! Use 0, 1, 2, or 3! Prepare for likely NullPointerExceptions due to stupidity! Returning a null value");
				return null;
		}
	}

	public ThaumRevMaterialData getHelm() {
		return helmData;
	}

	public ThaumRevMaterialData getChest() {
		return chestData;
	}

	public ThaumRevMaterialData getLegs() {
		return legsData;
	}

	public ThaumRevMaterialData getBoots() {
		return bootData;
	}

	public ThaumRevMaterialDataSet setIcon(String icon) {
		helmData.setIcon(icon);
		chestData.setIcon(icon);
		legsData.setIcon(icon);
		bootData.setIcon(icon);
		return this;
	}

	public ThaumRevMaterialDataSet setRepair(String repair) {
		helmData.setRepair(repair);
		chestData.setRepair(repair);
		legsData.setRepair(repair);
		bootData.setRepair(repair);
		return this;
	}

	public ThaumRevMaterialDataSet setTexture(String texture) {
		helmData.setTexture(texture);
		chestData.setTexture(texture);
		legsData.setTexture(texture);
		bootData.setTexture(texture);
		return this;
	}

	public ThaumRevMaterialDataSet setRarity(EnumRarity rarity) {
		helmData.setRarity(rarity);
		chestData.setRarity(rarity);
		legsData.setRarity(rarity);
		bootData.setRarity(rarity);
		return this;
	}

	public ThaumRevMaterialDataSet setUnlocName(String unlocName) {
		helmData.setUnlocName(unlocName);
		chestData.setUnlocName(unlocName);
		legsData.setUnlocName(unlocName);
		bootData.setUnlocName(unlocName);
		return this;
	}

	public ThaumRevMaterialDataSet setRegName(String regName) {
		helmData.setRegName(regName);
		chestData.setRegName(regName);
		legsData.setRegName(regName);
		bootData.setRegName(regName);
		return this;
	}

	public ThaumRevMaterialDataSet setColor(int color) {
		helmData.setColor(color);
		chestData.setColor(color);
		legsData.setColor(color);
		bootData.setColor(color);
		return this;
	}

	public ThaumRevMaterialDataSet setIcon(String icon, String[] suffix) {
		helmData.setIcon(icon + suffix[0]);
		chestData.setIcon(icon + suffix[1]);
		legsData.setIcon(icon + suffix[2]);
		bootData.setIcon(icon + suffix[3]);
		return this;
	}

	public ThaumRevMaterialDataSet setTexture(String texture, String[] suffix) {
		helmData.setTexture(texture + suffix[0]);
		chestData.setTexture(texture + suffix[1]);
		legsData.setTexture(texture + suffix[2]);
		bootData.setTexture(texture + suffix[3]);
		return this;
	}

	public ThaumRevMaterialDataSet setUnlocName(String unlocName, String[] suffix) {
		helmData.setUnlocName(unlocName + suffix[0]);
		chestData.setUnlocName(unlocName + suffix[1]);
		legsData.setUnlocName(unlocName + suffix[2]);
		bootData.setUnlocName(unlocName + suffix[3]);
		return this;
	}

	public ThaumRevMaterialDataSet setRegName(String regName, String[] suffix) {
		helmData.setRegName(regName + suffix[0]);
		chestData.setRegName(regName + suffix[1]);
		legsData.setRegName(regName + suffix[2]);
		bootData.setRegName(regName + suffix[3]);
		return this;
	}

	//setIcon

	public ThaumRevMaterialDataSet setRepair(String[] repair) {
		helmData.setRepair(repair[0]);
		chestData.setRepair(repair[1]);
		legsData.setRepair(repair[2]);
		bootData.setRepair(repair[3]);
		return this;
	}

	//setTexture

	public ThaumRevMaterialDataSet setRarity(EnumRarity[] rarity) {
		helmData.setRarity(rarity[0]);
		chestData.setRarity(rarity[1]);
		legsData.setRarity(rarity[2]);
		bootData.setRarity(rarity[3]);
		return this;
	}

	//setUnlocName

	//setRegName

}
