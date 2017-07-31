package mortvana.thaumrev.melteddashboard.item;

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
import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.thaumrev.melteddashboard.ColorLibrary;
import mortvana.thaumrev.melteddashboard.util.helpers.OreDictHelper;

public abstract class ItemArmorFluxGear extends ItemArmor {

	public static final ArmorMaterial MATERIAL = EnumHelper.addArmorMaterial("FLUXGEARWRAPPER", 0, new int[] { 0, 0, 0, 0 }, 0);

	public String material = null;
	public String sheetName;
	public String icon;
	public String repairMaterial = "";
	public String modName = "fluxgear";
	public boolean useCustomDir = false;
	public String[] textures = new String[2];
	public boolean showInCreative = true;
	public Multimap<String, AttributeModifier> properties = HashMultimap.create();
	public boolean colorized = false;
	public int defaultColor = 0xFFFFFF;
	public EnumRarity rarity = EnumRarity.common;

	public static TMap<String, ArmorMaterialEntry> materials = new THashMap<String, ArmorMaterialEntry>(32);

	/** CONSTRUCTORS **/
	public ItemArmorFluxGear(ArmorMaterial material, int index, int type, String name, String sheet, String icon) {
		super(material, index, type);
		setUnlocalizedName(name);
		sheetName = sheet;
		this.icon = icon;
	}

	public ItemArmorFluxGear(ArmorMaterial material, int type, String name, String sheet, String icon) {
		super(material, 2, type);
		setUnlocalizedName(name);
		sheetName = sheet;
		this.icon = icon;
	}

	public ItemArmorFluxGear(ArmorMaterial material, int type) {
		super(material, 0, type);
	}

	public ItemArmorFluxGear(String material, int index, int type, String name, String sheet, String icon) {
		super(MATERIAL, index, type);
		setMaterial(material);
		setUnlocalizedName(name);
		sheetName = sheet;
		this.icon = icon;
		damageReduceAmount = getReduction(type);
		setMaxDamage(getDurability(type));
	}

	public ItemArmorFluxGear(String material, int type, String name, String sheet, String icon) {
		super(MATERIAL, 2, type);
		setMaterial(material);
		setUnlocalizedName(name);
		sheetName = sheet;
		this.icon = icon;
		damageReduceAmount = getReduction(type);
		setMaxDamage(getDurability(type));
	}

	public ItemArmorFluxGear(String material, int type) {
		super(MATERIAL, 2, type);
		setMaterial(material);
		damageReduceAmount = getReduction(type);
		setMaxDamage(getDurability(type));
	}

	/** SETTERS **/
	public ItemArmorFluxGear setMaterial(String material) {
		this.material = material;
		return this;
	}

	public ItemArmorFluxGear setTextures(String sheet) {
		sheetName = sheet;
		return this;
	}

	public ItemArmorFluxGear setItemIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public ItemArmorFluxGear setRepairMaterial(String oredict) {
		repairMaterial = oredict;
		return this;
	}

	public ItemArmorFluxGear setModName(String name) {
		modName = name;
		return this;
	}

	public ItemArmorFluxGear setCustomTextures(String[] textures) {
		this.textures = textures;
		useCustomDir = true;
		if (textures.length > 3) {
			setColorized(true);
		}
		return this;
	}

	public ItemArmorFluxGear setShowInCreative(boolean bool) {
		showInCreative = bool;
		return this;
	}

	public ItemArmorFluxGear setColorized(boolean bool) {
		colorized = bool;
		return this;
	}

	public ItemArmorFluxGear setDefaultColor(int color) {
		defaultColor = color;
		colorized = true;
		return this;
	}

	public ItemArmorFluxGear setRarity(EnumRarity rarity) {
		this.rarity = rarity;
		return this;
	}

	public ItemArmorFluxGear setRarity(int rarity) {
		return setRarity(EnumRarity.values()[rarity]);
	}

	/** GETTERS **/

	@Override
	public boolean getIsRepairable(ItemStack armor, ItemStack material) {
		return OreDictHelper.isOreNameEqual(material, repairMaterial);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return rarity;
	}

	public ItemStack getItemStack() {
		return new ItemStack(this);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		if (showInCreative) {
			list.add(new ItemStack(item, 1, 0));
		}
	}

	/** PROXY MATERIAL FUNCTIONS **/
	public static void addArmorMaterial(String name, int durability, int helmProt, int chestProt, int legProt, int bootProt, int enchant) {
		addArmorMaterial(name, durability, new int[] {helmProt, chestProt, legProt, bootProt}, enchant);
	}

	public static void addArmorMaterial(String name, int durability, int[] protection, int enchant) {
		/*if (protection.length == 4) {*/
		materials.put(name, new ArmorMaterialEntry(durability, protection, enchant));
		/*} else {
			ThaumicRevelations.logger.error("Someone registered ");
		}*/
	}

	@Override
	public int getItemEnchantability() {
		if (material != null && materials.containsKey(material)) {
			return materials.get(material).getEnchantability();
		} else {
			return super.getItemEnchantability();
		}
	}

	public int getReduction(int type) {
		if (material != null && materials.containsKey(material)) {
			return materials.get(material).getProtectionForSlot(type);
		} else {
			return 0;
		}
	}

	public int getDurability(int type) {
		if (material != null && materials.containsKey(material)) {
			return materials.get(material).getDurability() * maxDamageArray[type];
		} else {
			return 0;
		}
	}

	/** ICON/COLORIZER STUFF**/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		if (icon != null) {
			if (colorized) {
				itemIcon = register.registerIcon(modName + ":armor/" + icon + "Color");
				overlayIcon = register.registerIcon(modName + ":armor/" + icon + "Overlay");
			} else {
				itemIcon = register.registerIcon(modName + ":armor/" + icon);
			}
		} else {
			super.registerIcons(register);
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (!useCustomDir) {
			if (colorized) {
				return modName + ":armor/" + sheetName + (slot != 2 ? "_1" : "_2") + (type == null ? "" : "_overlay") + ".png";
			} else {
				return modName + ":armor/" + sheetName + (slot == 2 ? "_2" : "_1") + ".png";
			}
		} else {
			if (colorized) {
				return slot == 2 ? (type == null ? textures[1] : textures[3]) : (type == null ? textures[0] : textures[2]);
			} else {
				return slot == 2 ? textures[1] : textures[0];
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return colorized;
	}

	@Override
	public boolean hasColor(ItemStack stack) {
		return colorized;
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int par1, int pass) {
		return pass == 0 ? itemIcon : overlayIcon;
	}

	@Override
	public int getColor(ItemStack stack) {
		if (!colorized) {
			return -1;
		} else {
			if (stack.getTagCompound() == null) {
				return defaultColor;
			} else {
				NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("display");
				return nbt.hasKey("color") ? nbt.getInteger("color") : defaultColor;
			}
		}
	}

	@Override
	public void removeColor(ItemStack stack) {
		if (colorized && stack.getTagCompound() != null) {
			NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("display");
			if (nbt.hasKey("color")) {
				nbt.removeTag("color");
			}
		}
	}

	@Override
	public void func_82813_b(ItemStack stack, int color) {
		if (!colorized) {
			throw new UnsupportedOperationException("Can\'t dye " + material + " armor!");
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
