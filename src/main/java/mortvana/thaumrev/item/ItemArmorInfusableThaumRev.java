package mortvana.thaumrev.item;

import mortvana.melteddashboard.item.entry.ArmorDataAdv;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

import thaumcraft.api.IRunicArmor;

import mortvana.melteddashboard.lib.StringLibrary;
import mortvana.melteddashboard.util.helpers.StringHelper;

import mortvana.thaumrev.api.util.enums.EnumEquipmentType;
import mortvana.thaumrev.common.ThaumicRevelations;
import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.util.item.ItemArmorInfusableBase;
import mortvana.melteddashboard.item.entry.ArmorData;

public class ItemArmorInfusableThaumRev extends ItemArmorInfusableBase implements IRunicArmor {

	public boolean isGoggles = false;

	public ItemArmorInfusableThaumRev(ArmorMaterial material, int index, int type, ArmorDataAdv data) {
		super(material, index, type, data);
        this.type = EnumEquipmentType.values()[type];
        setCreativeTab(ThaumRevLibrary.generalTab);
        setUnlocalizedName(data.getModName() + data.getUnlocName());
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
		//visDiscount = new int[] {discount, discount, discount, discount, discount, discount};
		return this;
	}

	public ItemArmorInfusableThaumRev setGoggles() {
		isGoggles = true;
		return this;
	}
}

