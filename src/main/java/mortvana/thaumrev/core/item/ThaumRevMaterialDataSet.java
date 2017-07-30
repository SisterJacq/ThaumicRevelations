package mortvana.thaumrev.core.item;

import mortvana.thaumrev.core.common.ThaumicRevelations;

public class ThaumRevMaterialDataSet {
	private ThaumRevMaterialData helmData, chestData, legsData, bootData;

	public ThaumRevMaterialDataSet(ThaumRevMaterialData helmData, ThaumRevMaterialData chestData, ThaumRevMaterialData legsData, ThaumRevMaterialData bootData) {
		this.helmData = helmData;
		this.chestData = chestData;
		this.legsData = legsData;
		this.bootData = bootData;
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
}
