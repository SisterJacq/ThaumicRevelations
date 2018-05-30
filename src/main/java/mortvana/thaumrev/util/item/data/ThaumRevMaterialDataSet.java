package mortvana.thaumrev.util.item.data;

import net.minecraft.item.EnumRarity;

import mortvana.melteddashboard.item.entry.ArmorData;

import mortvana.thaumrev.common.ThaumicRevelations;

public class ThaumRevMaterialDataSet {
	protected ArmorData helmData, chestData, legsData, bootData;

	public ThaumRevMaterialDataSet(ArmorData helmData, ArmorData chestData, ArmorData legsData, ArmorData bootData) {
		this.helmData = helmData;
		this.chestData = chestData;
		this.legsData = legsData;
		this.bootData = bootData;
	}

	public ThaumRevMaterialDataSet() {
		//helmData = new ArmorData();
		//chestData = new ArmorData();
		//legsData = new ArmorData();
		//bootData = new ArmorData();
	}

	public ArmorData getData(int type) {
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

	public ArmorData getHelm() {
		return helmData;
	}

	public ArmorData getChest() {
		return chestData;
	}

	public ArmorData getLegs() {
		return legsData;
	}

	public ArmorData getBoots() {
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
		helmData.setSheet(texture);
		chestData.setSheet(texture);
		legsData.setSheet(texture);
		bootData.setSheet(texture);
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
		helmData.setSheet(texture + suffix[0]);
		chestData.setSheet(texture + suffix[1]);
		legsData.setSheet(texture + suffix[2]);
		bootData.setSheet(texture + suffix[3]);
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

	//setSheet

	public ThaumRevMaterialDataSet setRarity(EnumRarity[] rarity) {
		helmData.setRarity(rarity[0]);
		chestData.setRarity(rarity[1]);
		legsData.setRarity(rarity[2]);
		bootData.setRarity(rarity[3]);
		return this;
	}

	//setUnlocName

	//setRegName

	//setIcon

	public ThaumRevMaterialDataSet setRepair(String repairHelm, String repairChest, String repairLegs, String repairBoot) {
		helmData.setRepair(repairHelm);
		chestData.setRepair(repairChest);
		legsData.setRepair(repairLegs);
		bootData.setRepair(repairBoot);
		return this;
	}

	//setSheet

	public ThaumRevMaterialDataSet setRarity(EnumRarity rarityHelm, EnumRarity rarityChest, EnumRarity rarityLegs, EnumRarity rarityBoot) {
		helmData.setRarity(rarityHelm);
		chestData.setRarity(rarityChest);
		legsData.setRarity(rarityLegs);
		bootData.setRarity(rarityBoot);
		return this;
	}

	//setUnlocName

	//setRegName

	public ThaumRevMaterialDataSet setNonColorized(int type) {
		switch (type) {
			case 0:
				helmData.setColor(0xFFFFFF);
			case 1:
				chestData.setColor(0xFFFFFF);
			case 2:
				legsData.setColor(0xFFFFFF);
			case 3:
				bootData.setColor(0xFFFFFF);
		}
		return this;
	}
}
