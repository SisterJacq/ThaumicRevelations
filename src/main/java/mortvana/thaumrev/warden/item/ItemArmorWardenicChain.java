package mortvana.thaumrev.warden.item;

import net.minecraft.item.EnumRarity;
import cpw.mods.fml.common.registry.GameRegistry;
import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.util.item.ItemArmorInfusableBase;

public class ItemArmorWardenicChain extends ItemArmorInfusableBase {

	public ItemArmorWardenicChain(int type, String name, String icon) {
		super(ArmorMaterial.CHAIN/*ThaumRevLibrary.materialWardenicChain*/, 0, type, name, "wardenicChain", icon);
		setRepairMaterial("itemWardenicBronzeChain");
		setModName(ThaumRevLibrary.RESOURCE_PREFIX);
		setTextures("wardenicChain");
		setRarity(EnumRarity.uncommon);
		//AspectInfusionHelper.setLockedSlotContents();
	}

	public ItemArmorWardenicChain register(String armorName, String registrationName) {
		GameRegistry.registerItem(this, registrationName);
		setUnlocalizedName(ThaumRevLibrary.RESOURCE_PREFIX + ".wardenicChain." + armorName);
		return this;
	}
}
