package mortvana.thaumrev.melteddashboard.item;

import java.util.Collection;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import mortvana.thaumrev.melteddashboard.util.helpers.OreDictHelper;

public abstract class ItemArmorFluxGear extends ItemArmor {

	public String sheetName;
	public String icon;
	public String repairMaterial = "";
	public boolean useCustomDir = false;
	public String[] textures = new String[2];
	public boolean showInCreative = true;
	public Multimap<String, AttributeModifier> properties = HashMultimap.create();
	public ItemStack stack;

	public ItemArmorFluxGear(ArmorMaterial material, int index, int type, String name, String sheet, String icon) {
		super(material, index, type);
		setUnlocalizedName(name);
		sheetName = sheet;
		this.icon = icon;
		setItemStack();
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

	public ItemArmorFluxGear setRepairMaterial(String oredict) {
		repairMaterial = oredict;
		return this;
	}

	public ItemArmorFluxGear setTextures(String sheet) {
		sheetName = sheet;
		return this;
	}

	public ItemArmorFluxGear setCustomTextures(String[] textures) {
		this.textures = textures;
		useCustomDir = true;
		return this;
	}

	public ItemArmorFluxGear setShowInCreative(boolean bool) {
		showInCreative = bool;
		return this;
	}

	public ItemArmorFluxGear setItemIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public ItemArmorFluxGear setItemStack() {
		stack = new ItemStack(this);
		return this;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		if (showInCreative) {
			list.add(new ItemStack(item, 1, 0));
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack armor, ItemStack material) {
		return OreDictHelper.isOreNameEqual(material, repairMaterial);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		if (icon != null) {
			itemIcon = register.registerIcon(icon);
		} else {
			super.registerIcons(register);
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (!useCustomDir) {
			return "fluxgear:armor/" + sheetName + (slot == 2 ? "_2" : "_1") + ".png";
		} else {
			return slot == 2 ? textures[1] : textures[0];
		}
	}

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
