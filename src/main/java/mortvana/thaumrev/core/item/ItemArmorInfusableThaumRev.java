package mortvana.thaumrev.core.item;

import cpw.mods.fml.common.registry.GameRegistry;
import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.thaumrev.api.util.enums.EnumEquipmentType;
import mortvana.thaumrev.core.common.ThaumicRevelations;
import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.melteddashboard.util.helpers.StringHelper;
import mortvana.thaumrev.util.item.ItemArmorInfusableBase;

public class ItemArmorInfusableThaumRev extends ItemArmorInfusableBase {

	public static TMap<String, ThaumRevMaterialDataSet> materialData = new THashMap<String, ThaumRevMaterialDataSet>(32);

	public ItemArmorInfusableThaumRev(String material, int type) {
		super(material, type);
		register(material, type);
	}

	public void register(String material, int type) {
        this.type = EnumEquipmentType.values()[type];
        setCreativeTab(ThaumRevLibrary.thaumicRevelationsTab);
        setModName(ThaumRevLibrary.RESOURCE_PREFIX);

		ThaumRevMaterialData data;
		if (materialData.containsKey(material)) {
			data = materialData.get(material).getData(type);
			if (data == null) {
				ThaumicRevelations.logger.error("Someone didn't register data for the " + StringHelper.getArmorForInt(type) + " of this set! Not registering this item!");
                return;
			}
		} else {
			ThaumicRevelations.logger.error("Someone either forgot to register their material data, or used the wrong string for it! Not registering this item!");
			return;
		}
        setIndex(data.getIndex());
        setUnlocalizedName(data.getUnlocName());
        sheetName = data.getSheet();
        icon = data.getIcon();
        setRepairMaterial(data.getRepair());
        setTextures(data.getTexture());
        setRarity(data.getRarity());
        if(data.getColorized) {
            setDefaultColor(data.getColor());
        }
        GameRegistry.registerItem(this, data.getRegName());
	}

	public static void setDefaultInfusions(String material, int type) {
		//AspectInfusionHelper.setLockedSlotContents();
	}

}

