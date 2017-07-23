package mortvana.thaumrev.warden.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.melteddashboard.ColorLibrary;
import mortvana.thaumrev.util.item.ItemArmorInfusableBase;

public class ItemArmorWardenicChain extends ItemArmorInfusableBase {

	public ItemArmorWardenicChain(int type, String name, String icon) {
		super(ArmorMaterial.CHAIN/*ThaumRevLibrary.materialWardenicChain*/, 0, type, name, "wardencloth", icon);
		setRepairMaterial("itemWardencloth");
		//AspectInfusionHelper.setLockedSlotContents();
	}

	public ItemArmorWardenicChain register(String armorName, String registrationName) {
		GameRegistry.registerItem(this, registrationName);
		setUnlocalizedName(ThaumRevLibrary.RESOURCE_PREFIX + ".wardenicChain." + armorName);
		return this;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ThaumRevLibrary.RESOURCE_PREFIX + ":armor/" + icon);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "thaumrev:armor/wardencloth" + (slot != 2 ? "_1" : "_2") + ".png";
	}
}
