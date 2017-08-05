package mortvana.thaumrev.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.thaumrev.api.util.enums.EnumEquipmentType;
import mortvana.thaumrev.common.ThaumicRevelations;
import mortvana.thaumrev.util.item.data.ThaumRevMaterialData;
import mortvana.thaumrev.util.item.data.ThaumRevMaterialDataSet;
import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.melteddashboard.util.helpers.StringHelper;
import mortvana.thaumrev.util.item.ItemArmorInfusableBase;
import thaumcraft.api.IRunicArmor;

public class ItemArmorInfusableThaumRev extends ItemArmorInfusableBase implements IRunicArmor {

	public static TMap<String, ThaumRevMaterialDataSet> materialData = new THashMap<String, ThaumRevMaterialDataSet>(32);

	public boolean isGoggles = false;

	public ItemArmorInfusableThaumRev(String material, int index, int type) {
		super(material, index, type);
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
        setUnlocalizedName(ThaumRevLibrary.RESOURCE_PREFIX + data.getUnlocName());
        setItemIcon(data.getIcon());
        setRepairMaterial(data.getRepair());
        setTextures(data.getTexture());
        setRarity(data.getRarity());
        if(data.getColorized()) {
            setDefaultColor(data.getColor());
        }
        GameRegistry.registerItem(this, data.getRegName());
	}

	public static void setDefaultInfusions(String material, int type) {
		//AspectInfusionHelper.setLockedSlotContents();
	}

	@Override
	public boolean showNodes(ItemStack stack, EntityLivingBase entity) {
		return isGoggles;
	}

	@Override
	public boolean showIngamePopups(ItemStack stack, EntityLivingBase entity) {
		return isGoggles;
	}

	@Override
	public int getRunicCharge(ItemStack stack) {
		return 0;
	}

	public ItemArmorInfusableThaumRev setDiscount(int discount) {
		visDiscount = new int[] {discount, discount, discount, discount, discount, discount};
		return this;
	}

	public ItemArmorInfusableThaumRev setGoggles() {
		isGoggles = true;
		return this;
	}
}

