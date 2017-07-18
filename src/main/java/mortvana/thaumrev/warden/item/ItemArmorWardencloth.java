package mortvana.thaumrev.warden.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.melteddashboard.ColorLibrary;
import mortvana.thaumrev.util.item.ItemArmorInfusableBase;

public class ItemArmorWardencloth extends ItemArmorInfusableBase {

	public ItemArmorWardencloth(int type, String name, String icon) {
		super(ThaumRevLibrary.materialWardencloth, 0, type, name, "wardencloth", icon);
		setRepairMaterial("itemWardencloth");
		//AspectInfusionHelper.setLockedSlotContents();
	}

	public ItemArmorWardencloth register(String armorName, String registrationName) {
		GameRegistry.registerItem(this, registrationName);
		setUnlocalizedName(ThaumRevLibrary.RESOURCE_PREFIX + ".wardencloth." + armorName);
		return this;
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
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && world.getBlock(x, y, z) == Blocks.cauldron && world.getBlockMetadata(x, y, z) > 0) {
			removeColor(stack);
			world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 1, 2);
			world.func_147453_f(x, y, z, Blocks.cauldron);
			return true;
		}
		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ThaumRevLibrary.RESOURCE_PREFIX + ":armor/" + icon + "Color");
		overlayIcon = register.registerIcon(ThaumRevLibrary.RESOURCE_PREFIX + ":armor/" + icon + "Overlay");
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "thaumrev:armor/wardencloth" + (slot != 2 ? "_1" : "_2") + (type == null ? "" : "_overlay") + ".png";
	}
}
