package mortvana.thaumrev.core.item;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.thaumrev.core.common.ThaumicRevelations;
import mortvana.thaumrev.melteddashboard.util.helpers.StringHelper;
import mortvana.thaumrev.util.item.ItemArmorInfusableBase;

public class ItemArmorInfusableThaumRev extends ItemArmorInfusableBase {

	public static TMap<String, ThaumRevMaterialDataSet> materialData = new THashMap<String, ThaumRevMaterialDataSet>(32);

	public ItemArmorInfusableThaumRev(String material, int type) {
		super(material, type);
		register(material, type);
	}

	public void register(String material, int type) {
		ThaumRevMaterialData data;
		if (materialData.containsKey(material)) {
			data = materialData.get(material).getData(type);
			if (data == null) {
				ThaumicRevelations.logger.error("Someone didn't register data for the " + StringHelper.getArmorForInt(type) + " of this set! Not registering this item!");
			}
		} else {
			ThaumicRevelations.logger.error("Someone either forgot to register their material data, or used the wrong string for it! Not registering this item!");
			return;
		}

		setUnlocalizedName()
	}

	public static void setDefaultInfusions(String material, int type) {
		//AspectInfusionHelper.setLockedSlotContents();
	}

}

