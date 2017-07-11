package mortvana.thaumicrevelations.warden.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.thaumicrevelations.library.ThaumicLibrary;
import mortvana.thaumicrevelations.melteddashboard.ColorLibrary;
import mortvana.thaumicrevelations.util.ItemArmorInfusableBase;

public class ItemArmorWardencloth extends ItemArmorInfusableBase {

	public ItemArmorWardencloth(int type, String name, String icon) {
		super(ThaumicLibrary.materialWardencloth, 0, type, name, "wardencloth", icon);
		//AspectInfusionHelper.setLockedSlotContents();
	}

	public Item register(String armorName, String registrationName) {
		GameRegistry.registerItem(this, registrationName);
		return super.setUnlocalizedName(ThaumicLibrary.RESOURCE_PREFIX + ".wardencloth." + armorName);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	// COLORIZER STUFF!
	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public boolean hasColor(ItemStack stack) {
		return true;
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int par1, int pass) {
		return pass == 0 ? itemIcon : overlayIcon;
	}

	@Override
	public int getColor(ItemStack stack) {
		if (stack.getTagCompound() == null) {
			return ColorLibrary.COLOR_TEAL_MAGNEQUAZAR;
		}
		NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("display");
		return nbt.hasKey("color") ? nbt.getInteger("color") : ColorLibrary.COLOR_TEAL_MAGNEQUAZAR;
	}

	@Override
	public void removeColor(ItemStack stack) {
		if (stack.getTagCompound() != null) {
			NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("display");
			if (nbt.hasKey("color")) {
				nbt.removeTag("color");
			}
		}
	}

	@Override
	public void func_82813_b(ItemStack stack, int color) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}
		NBTTagCompound nbt = tag.getCompoundTag("display");
		if (!nbt.hasKey("display")) {
			tag.setTag("display", nbt);
		}
		nbt.setInteger("color", color);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ThaumicLibrary.RESOURCE_PREFIX + ":armor/" + icon + "Color");
		overlayIcon = register.registerIcon(ThaumicLibrary.RESOURCE_PREFIX + ":armor/" + icon + "Overlay");
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "thaumrev:armor/wardencloth" + (slot != 2 ? "_1" : "_2") + (type == null ? "" : "_overlay") + ".png";
	}
}
