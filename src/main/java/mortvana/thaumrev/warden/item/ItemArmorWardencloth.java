package mortvana.thaumrev.warden.item;

import net.minecraft.item.EnumRarity;
import cpw.mods.fml.common.registry.GameRegistry;
import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.melteddashboard.ColorLibrary;
import mortvana.thaumrev.util.item.ItemArmorInfusableBase;

public class ItemArmorWardencloth extends ItemArmorInfusableBase {

	public ItemArmorWardencloth(int type, String name, String icon) {
		super(ArmorMaterial.CLOTH, 0, type, name, "wardencloth", icon);
		setRepairMaterial("itemWardencloth");
		setDefaultColor(ColorLibrary.COLOR_TEAL_MAGNEQUAZAR);
		setModName(ThaumRevLibrary.RESOURCE_PREFIX);
		setTextures("wardencloth");
		setRarity(EnumRarity.uncommon);
		//AspectInfusionHelper.setLockedSlotContents();
	}

	public ItemArmorWardencloth register(String armorName, String registrationName) {
		GameRegistry.registerItem(this, registrationName);
		setUnlocalizedName(ThaumRevLibrary.RESOURCE_PREFIX + ".wardencloth." + armorName);
		return this;
	}
}
