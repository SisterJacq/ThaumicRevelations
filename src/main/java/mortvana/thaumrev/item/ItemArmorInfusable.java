package mortvana.thaumrev.item;

import java.util.*;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import thaumcraft.api.aspects.Aspect;

import mortvana.melteddashboard.item.data.ArmorBehavior;
import mortvana.melteddashboard.item.data.ArmorDataAdv;
import mortvana.melteddashboard.util.helpers.*;
import mortvana.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import mortvana.melteddashboard.util.libraries.ColorLibrary;

import mortvana.thaumrev.api.item.infusion.IInfusableArmor;
import mortvana.thaumrev.api.item.infusion.IInfusableItem;
import mortvana.thaumrev.api.util.enums.EnumEquipmentType;
import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.util.enums.EnumPrimalAspect;

public class ItemArmorInfusable extends ItemArmor implements IInfusableArmor {

	public ArmorDataAdv data;
	public Multimap<String, AttributeModifier> properties = HashMultimap.create();

    public ItemArmorInfusable(ArmorMaterial material, int index, int type, ArmorDataAdv data) {
        super(material, index, type);
		this.data = data;
        setCreativeTab(ThaumRevLibrary.generalTab);
		setUnlocalizedName(getModName() + getUnlocName());
		GameRegistry.registerItem(this, data.getRegName());
		if (getType() == EnumEquipmentType.NULL) {
			this.data.setEquipmentType(EnumEquipmentType.values()[type]);
		}
		if (getUnbreakable()) {
			setMaxDamage(0);
		}
    }

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(stack, player, list, par4);
		if (getMaxEnergy() > 0) {
			list.add(StringHelper.localize("fluxgear.tooltip.charge") + ": " + NBTHelper.getFlux(stack) + " / " + getMaxEnergy() + " RF");
		}
		ThaumcraftHelper.addDiscountInformation(stack, player, list, par4);
		AspectInfusionHelper.addInformation(stack, player, list, par4);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		getBehavior().onArmorTick(world, player, armor);
		//AspectInfusionHelper.applyInfusions(world, player, stack, getType(stack));
	}

	@Override
	public void setDamage(ItemStack stack, int damage) {
		if (getUnbreakable()) {
			return;
		}
		if (!getFluxArmor()) {
			if (damage > getMaxDamage()) {
				setItemDamage(stack, getMaxDamage());
				NBTHelper.setBroken(stack, true);
			} else {
				setItemDamage(stack, damage);
			}
		} else {
			super.setDamage(stack, 0);
		}
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		if (getFluxArmor()) {
			NBTHelper.ensureNBTFlux(stack);
			return (double) (getMaxEnergy() - NBTHelper.getFluxBypass(stack)) / getMaxEnergy();
		} else {
			return super.getDurabilityForDisplay(stack);
		}
	}

	@Override
	public boolean isDamaged(ItemStack stack) {
		return super.isDamaged(stack) || getFluxArmor();
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return (super.showDurabilityBar(stack) && !getUnbreakable()) || (getFluxArmor() && (stack.stackTagCompound == null || !stack.stackTagCompound.getBoolean("CreativeTab")));
	}

	public void setItemDamage(ItemStack stack, int damage) {
		stack.itemDamage = damage;
	}


	/** DATA SETTERS **/


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

	public int getAbsorbtion() {
		return data.getAbsorbtion();
	}

	public int[] getDiscount() {
		return data.getDiscount();
	}

	public int getDiscount(int index) {
		return data.getDiscount(index);
	}

	public int getDiscount(EnumPrimalAspect aspect) {
		return data.getDiscount(aspect);
	}

	public int getDiscount(Aspect aspect) {
		return data.getDiscount(aspect);
	}

	public boolean getGoggles() {
		return data.getGoggles();
	}

	public boolean getFluxArmor() {
		return data.getFluxArmor();
	}

	public int getMaxEnergy() {
		return data.getMaxEnergy();
	}

	public int getMaxTransfer() {
		return data.getMaxTransfer();
	}

	public double getAbsorbRatio() {
		return data.getAbsorbRatio();
	}

	public boolean getFluxCharge() {
		return data.getFluxCharge();
	}

	public int getChargeDamage() {
		return data.getChargeDamage();
	}

	public boolean getUnbreakable() {
		return data.getUnbreakable();
	}

	public EnumEquipmentType getType() {
		return data.getType();
	}

	public ArmorBehavior getBehavior() {
		return data.getBehavior();
	}


	/** GETTERS **/
	@Override
	public boolean getIsRepairable(ItemStack armor, ItemStack material) {
		return !getFluxArmor() && OreDictHelper.isOreNameEqual(material, data.getRepair());
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return getRarity();
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		if (getShowInCreative()) {
			ItemStack stack = new ItemStack(item, 1, 0);
			if (!getFluxArmor()) {
				list.add(stack);
			} else {
				list.add(NBTHelper.setStackFlux(stack.copy(), 0));
				list.add(NBTHelper.setStackFlux(stack, getMaxEnergy()));
			}
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
			return ColorLibrary.CLEAR;
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

	public ItemArmorInfusable putAttribute(String attribute, AttributeModifier modifier) {
		properties.put(attribute, modifier);
		return this;
	}

	public Collection<AttributeModifier> removeAttribute(String attribute) {
		return properties.removeAll(attribute);
	}

	/** IInfusableArmor **/
	public int getEnergyPerDamage(ItemStack stack) {
		int unbreaking = MathHelper.clamp_int(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
		return getChargeDamage() * (5 - unbreaking) / 5;
	}

	@Override
	public ArmorDataAdv getData() {
		return data;
	}

	public int getArmorValue() {
		return getAbsorbtion() >= 0 ? ((20 * getAbsorbtion()) / 100) : damageReduceAmount;
	}


	/** ISpecialArmor **/
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return getBehavior().getProperties(player, armor, source, damage, slot);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		if (getFluxArmor()) {
			return getEnergyStored(armor) >= getEnergyPerDamage(armor) ? getArmorValue() : 0;
		} else {
			return NBTHelper.isBroken(armor) ? 0 : getArmorValue();
		}
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
		if (getFluxArmor()) {
			if (source.damageType.equals("flux") && getFluxCharge()) {
				receiveEnergy(armor, damage * (source.getEntity() == null ? getChargeDamage() / 2 : getEnergyPerDamage(armor)), false);
			} else if (source != DamageSource.fall) {
				extractEnergy(armor, damage * getEnergyPerDamage(armor), false);
			}
		} else if (!NBTHelper.isBroken(armor)) {
			if (source != DamageSource.fall) {
				armor.damageItem(damage, entity);
			}
		}
		AspectInfusionHelper.damageArmor(entity, armor, source, damage, slot);
	}


	/** IContainerItem **/
	@Override
	public int getInternalSlots(ItemStack container) {
		return AspectInfusionHelper.getSlotsTotal(container);
	}

	@Override
	public ItemStack[] getInventory(ItemStack container) {
		return InventoryHelper.getInventory(container);
	}

	@Override
	public void setInventory(ItemStack container, ItemStack[] inventory) {
		InventoryHelper.setInventory(container, inventory);
	}

	@Override
	public ItemStack getItemInSlot(ItemStack container, int slot) {
		return InventoryHelper.getItemInSlot(container, slot);
	}

	@Override
	public void setItemInSlot(ItemStack container, ItemStack itemstack, int slot) {
		InventoryHelper.setItemInSlot(container, itemstack, slot);
	}


	/** IInfusableItem **/
	@Override
	public IInfusableItem setNumberSlots(ItemStack stack, int unlocked, int locked) {
		AspectInfusionHelper.setNumberSlots(stack, unlocked, locked);
		return this;
	}

	@Override
	public EnumEquipmentType getType(ItemStack stack) {
		return stack.getItem() instanceof ItemArmorInfusable ? ((ItemArmorInfusable) stack.getItem()).getType() : EnumEquipmentType.NULL;
	}

	@Override
	public IInfusableItem setType(ItemStack stack, EnumEquipmentType type) {
		return this;
	}

	@Override
	public NBTTagCompound getInfusionTag(ItemStack stack) {
		return NBTHelper.getSubTag(stack, "infusions");
	}

	@Override
	public void setInfusionTag(ItemStack stack, NBTTagCompound tag) {
		NBTHelper.setSubTag(stack, "infusions", tag);
	}

	@Override
	public void clearInfusionTag(ItemStack stack) {
		NBTHelper.remove(stack, "infusions");
	}

	@Override
	public void setDefaultInfusions(ItemStack stack) {
		//AspectInfusionHelper.setLockedSlotContents();
	}


	/** IVisDiscountGear **/
	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
		return ThaumcraftHelper.getDiscountForAspect(stack, player, aspect, getDiscount(aspect));
	}


	/** IRevealer **/
	@Override
	public boolean showNodes(ItemStack stack, EntityLivingBase entity) {
		return isGoggles(stack, entity);
	}


	/** IGoogles **/
	@Override
	public boolean showIngamePopups(ItemStack stack, EntityLivingBase entity) {
		return isGoggles(stack, entity);
	}


	/** IRunicArmor **/
	@Override
	public int getRunicCharge(ItemStack stack) {
		return 0; //TODO
	}


	/** IEnergyContainerItem **/
	@Override
	@Optional.Method(modid = "CoFHAPI|energy")
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		if (getMaxEnergy() > 0) {
			NBTHelper.ensureNBTFlux(container);

			int stored = NBTHelper.getFluxBypass(container);
			int receive = Math.min(maxReceive, Math.min(getMaxEnergy() - stored, getMaxTransfer()));

			if (!simulate) {
				NBTHelper.setFluxBypass(container, stored + receive);
			}
			return receive;
		} else {
			return 0;
		}
	}

	@Override
	@Optional.Method(modid = "CoFHAPI|energy")
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		if (getMaxEnergy() > 0) {
			NBTHelper.ensureNBTFlux(container);

			int stored = NBTHelper.getFluxBypass(container);
			int extract = Math.min(maxExtract, stored);

			if (!simulate) {
				NBTHelper.setFluxBypass(container, stored - extract);
			}
			return extract;
		} else {
			return 0;
		}
	}

	@Override
	@Optional.Method(modid = "CoFHAPI|energy")
	public int getEnergyStored(ItemStack container) {
		if (getMaxEnergy() > 0) {
			NBTHelper.ensureNBTFlux(container);
			return NBTHelper.getFluxBypass(container);
		} else {
			return 0;
		}
	}

	@Override
	@Optional.Method(modid = "CoFHAPI|energy")
	public int getMaxEnergyStored(ItemStack container) {
		return getMaxEnergy();
	}


	/** UTILITY **/
	public static boolean isGoggles(ItemStack stack, EntityLivingBase entity) {
		return NBTHelper.isRevealingGoggles(stack, entity) || (stack.getItem() instanceof ItemArmorInfusable && ((ItemArmorInfusable) stack.getItem()).getGoggles());
	}
}
