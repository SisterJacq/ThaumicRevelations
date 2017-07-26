package mortvana.thaumrev.core.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.melteddashboard.item.ItemArmorFluxGear;
import mortvana.thaumrev.melteddashboard.util.helpers.StringHelper;
import thaumcraft.api.*;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;

public class ItemArmorPrimal extends ItemArmorFluxGear implements IRepairable, IVisDiscountGear, IRevealer, IGoggles, IRunicArmor {

	public ItemArmorPrimal(int type, String name, String icon) {
		super(ThaumcraftApi.armorMatSpecial, getIndexForType(type), type, name, "primal", icon);
		setCreativeTab(ThaumRevLibrary.thaumicRevelationsTab);
		setRepairMaterials();
		setDefaultColor(0x6A3880);
		setModName(ThaumRevLibrary.RESOURCE_PREFIX);
		setTextures("primal");
		setRarity(type == 0 ? EnumRarity.rare : EnumRarity.rare);
	}

	public ItemArmorPrimal register(String armorName, String registrationName) {
		GameRegistry.registerItem(this, registrationName);
		setUnlocalizedName(ThaumRevLibrary.RESOURCE_PREFIX + ".primal." + armorName);
		return this;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(stack, player, list, par4);
		StringHelper.visDiscount(getVisDiscount(stack, player, null));
	}

	// GOGGLES/ROBE STUFF!
	@Override
	public boolean showIngamePopups(ItemStack stack, EntityLivingBase player) {
		return armorType == 0;
	}

	@Override
	public boolean showNodes(ItemStack stack, EntityLivingBase player) {
		return armorType == 0;
	}

	@Override
	public int getRunicCharge(ItemStack stack) {
		return 0;
	}

	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
		return getVisDiscountForType(armorType);
	}

	// TYPE-SENSITIVE GETTERS AND STUFF
	public static int getIndexForType(int type) {
		switch (type) {
			case 0:
				return 4;
			case 2:
				return 2;
			default:
				return 1;
		}
	}

	public static int getVisDiscountForType(int type) {
		switch (type) {
			case 0:
				return 5;
			case 3:
				return 1;
			default:
				return 2;
		}
	}

	public void setRepairMaterials() {
		if (armorType == 0) {
			setRepairMaterial("ingotGold");
		} else {
			setRepairMaterial("itemEnchantedFabric");
		}
	}
}
