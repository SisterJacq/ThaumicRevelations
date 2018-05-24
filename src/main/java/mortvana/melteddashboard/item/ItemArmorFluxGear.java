package mortvana.melteddashboard.item;

import java.util.Collection;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.util.EnumHelper;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import mortvana.melteddashboard.item.entry.ArmorData;

import mortvana.melteddashboard.util.IStackProvider;
import mortvana.melteddashboard.util.helpers.OreDictHelper;

public abstract class ItemArmorFluxGear extends ItemArmor implements IStackProvider {

	public static final ArmorMaterial MATERIAL = EnumHelper.addArmorMaterial("FLUXGEARWRAPPER", 0, new int[] { 0, 0, 0, 0 }, 0);

	//public ArmorData data;

	//public String sheetName;//
	//public String icon;//
	//public String repairMaterial = "";//
	//public String modName;//
	//public boolean useCustomDir = false;
	//public String[] textures = new String[2];//
	//public boolean showInCreative = true;
	public Multimap<String, AttributeModifier> properties = HashMultimap.create();
	//public boolean colorized = false;//
	//public int defaultColor = 0xFFFFFF;//
	//public EnumRarity rarity = EnumRarity.common;//

	/** CONSTRUCTORS **/
	public ItemArmorFluxGear(ArmorMaterial material, int index, int type, String name, String sheet, String icon) {
		super(material, index, type);
		setUnlocalizedName(name);
		setSheet(sheet);
		setIcon(icon);
	}

	public ItemArmorFluxGear(ArmorMaterial material, int type, String name, String sheet, String icon) {
		super(material, 2, type);
		setUnlocalizedName(name);
		setSheet(sheet);
		setIcon(icon);
	}

	public ItemArmorFluxGear(ArmorMaterial material, int index, int type) {
		super(material, index, type);
	}

	public ItemArmorFluxGear(ArmorMaterial material, int type) {
		super(material, 2, type);
	}

	/** DATA SETTERS **/
	public ItemArmorFluxGear setModName(String modName) {
		getData().setModName(modName);
		return this;
	}

	public ItemArmorFluxGear setIcon(String icon) {
		data.setIcon(icon);
		return this;
	}

	public ItemArmorFluxGear setSheet(String sheet) {
		data.setSheet(sheet);
		return this;
	}

	//public ItemArmorFluxGear setCustomDir(boolean customDir) {
	//	data.setCustomDir(customDir);
	//	return this;
	//}

	public ItemArmorFluxGear setRepairMaterial(String oredict) {
		data.setRepair(oredict);
		return this;
	}

	public ItemArmorFluxGear setRarity(EnumRarity rarity) {
		data.setRarity(rarity);
		return this;
	}

	public ItemArmorFluxGear setRarity(int rarity) {
		data.setRarity(rarity);
		return this;
	}

	public ItemArmorFluxGear setUnlocName(String unlocName) {
		data.setUnlocName(unlocName);
		return this;
	}

	public ItemArmorFluxGear setRegName(String regName) {
		data.setRegName(regName);
		return this;
	}

	public ItemArmorFluxGear setColor(int color) {
		data.setColor(color);
		return this;
	}

	public ItemArmorFluxGear setShowInCreative(boolean bool) {
		data.setShowInCreative(bool);
		return this;
	}

	/*public ItemArmorFluxGear setCustomTextures(String[] textures) {
		this.textures = textures;
		data.setCustomDir(true);
		if (textures.length > 3) {
			setColorized(true);
		}
		return this;
	}*/

	/** DATA GETTERS **/
	public String getModName() {
		return data.getModName();
	}

	public String getIcon() {
		return data.getIcon();
	}

	public String getSheet() {
		return data.getSheet();
	}

	//public boolean getCustomDir() {
	//	return data.getCustomDir();
	//}

	public String getRepair() {
		return data.getRepair();
	}

	public EnumRarity getRarity() {
		return data.getRarity();
	}

	public String getUnlocName() {
		return data.getUnlocName();
	}

	public String getRegName() {
		return data.getRegName();
	}

	public int getColor() {
		return data.getColor();
	}

	public boolean getShowInCreative() {
		return data.getShowInCreative();
	}

	public boolean getColorized() {
		return data.getColorized();
	}

	/** GETTERS **/
	public abstract <T extends ArmorData> getData();

	@Override
	public boolean getIsRepairable(ItemStack armor, ItemStack material) {
		return OreDictHelper.isOreNameEqual(material, data.getRepair());
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return getRarity();
	}

	public ItemStack getStack() {
		return new ItemStack(this);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		if (getShowInCreative()) {
			list.add(new ItemStack(item, 1, 0));
		}
	}

	/** ICON/COLORIZER STUFF**/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		if (getIcon() != null) {
			if (getColorized()) {
				itemIcon = register.registerIcon(getModName() + ":armor/" + getIcon() + "Color");
				overlayIcon = register.registerIcon(getModName() + ":armor/" + getIcon() + "Overlay");
			} else {
				itemIcon = register.registerIcon(getModName() + ":armor/" + getIcon());
			}
		} else {
			super.registerIcons(register);
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		//if (!getCustomDir()) {
		if (getColorized()) {
			return getModName() + ":textures/models/" + getSheet() + (slot != 2 ? "_1" : "_2") + (type == null ? "" : "_overlay") + ".png";
		} else {
			return getModName() + ":textures/models/" + getSheet() + (slot == 2 ? "_2" : "_1") + ".png";
		}
		//} else {
		//	if (getColorized()) {
		//		return slot == 2 ? (type == null ? textures[1] : textures[3]) : (type == null ? textures[0] : textures[2]);
		//	} else {
		//		return slot == 2 ? textures[1] : textures[0];
		//	}
		//}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return getColorized();
	}

	@Override
	public boolean hasColor(ItemStack stack) {
		return getColorized();
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int par1, int pass) {
		return pass == 0 ? itemIcon : overlayIcon;
	}

	@Override
	public int getColor(ItemStack stack) {
		if (!getColorized()) {
			return -1;
		} else {
			if (stack.getTagCompound() == null) {
				return getColor();
			} else {
				NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("display");
				return nbt.hasKey("color") ? nbt.getInteger("color") : getColor();
			}
		}
	}

	@Override
	public void removeColor(ItemStack stack) {
		if (getColorized() && stack.getTagCompound() != null) {
			NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("display");
			if (nbt.hasKey("color")) {
				nbt.removeTag("color");
			}
		}
	}

	@Override
	public void func_82813_b(ItemStack stack, int color) {
		if (!getColorized()) {
			throw new UnsupportedOperationException("Can\'t dye " + getArmorMaterial().name() + " armor!");
		} else {
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

	/** ATTRIBUTE STUFF **/
	@Override
	public Multimap getAttributeModifiers(ItemStack stack) {
		Multimap temp = super.getAttributeModifiers(stack);
		temp.putAll(properties);
		return temp;
	}

	public ItemArmorFluxGear putAttribute(String attribute, AttributeModifier modifier) {
		properties.put(attribute, modifier);
		return this;
	}

	public Collection<AttributeModifier> removeAttribute(String attribute) {
		return properties.removeAll(attribute);
	}


}
