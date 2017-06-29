package mortvana.thaumicrevelations.util;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import cpw.mods.fml.common.Optional;
import net.minecraftforge.common.ISpecialArmor;

import mortvana.thaumicrevelations.api.item.infusion.*;
import mortvana.thaumicrevelations.api.util.enums.EnumEquipmentType;
import mortvana.thaumicrevelations.api.util.slot.SlotInfusion;
import mortvana.thaumicrevelations.library.ThaumicLibrary;
import mortvana.thaumicrevelations.melteddashboard.item.ItemArmorFluxGear;
import mortvana.thaumicrevelations.melteddashboard.util.helpers.AspectInfusionHelper;
import mortvana.thaumicrevelations.melteddashboard.util.helpers.NBTHelper;
import mortvana.thaumicrevelations.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;

public /*abstract*/ class ArmorInfusableBase extends ItemArmorFluxGear implements ISpecialArmor, IInfusableItem, IVisDiscountGear, IRevealer, IGoggles {

	protected int unlocked;
	protected int locked;
	//protected SlotInfusion[] slots;
	protected int[] discounts = new int[4];
	protected EnumEquipmentType type;

	protected int maxEnergy;

	public ArmorInfusableBase(ArmorMaterial material, int index, int type, String name, String sheet, String icon) {
		super(material, index, type, name, sheet, icon);
		this.type = EnumEquipmentType.values()[type];
		setCreativeTab(ThaumicLibrary.thaumicRevelationsTab);
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		//TODO
		super.addInformation(stack, player, list, par4);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		AspectInfusionHelper.applyInfusions(world, player, stack, getType());
		super.onArmorTick(world, player, stack);
	}

	@Override
	public void setDamage(ItemStack stack, int damage) {
		if (damage > getMaxDamage()) {
			stack.setItemDamage(getMaxDamage());
			NBTHelper.setBroken(stack, true);
		}
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return null;
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return NBTHelper.isBroken(armor) ? 0 : damageReduceAmount;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if (!NBTHelper.isBroken(stack)) {
			if (source != DamageSource.fall) {
				stack.damageItem(damage, entity);
			}
		}
	}

	@Override
	public int getSizeInventory(ItemStack container) {
		return getNumberSlotsTotal(container);
	}

	@Override
	public int getNumberSlotsTotal(ItemStack stack) {
		return unlocked + locked;
	}

	@Override
	public int getNumberSlotsUnlocked(ItemStack stack) {
		return unlocked;
	}

	@Override
	public int getNumberSlotsLocked(ItemStack stack) {
		return locked;
	}

	@Override
	public IInfusableItem setNumberSlots(int unlocked, int locked) {
		this.unlocked = unlocked;
		this.locked = locked;
		//TODO slots = new SlotInfusion[unlocked + locked];
		return this;
	}

	@Override
	public ItemStack getSlotContents(int slot) {
		return null; //TODO slots[slot].getContents();
	}

	@Override
	public SlotInfusion[] getSlots() {
		return null; //TODO slots;
	}

	@Override
	public boolean isSlotUnlocked(int slot) {
		return slot > locked;
	}

	@Override
	public EnumEquipmentType getType() {
		return type;
	}

	@Override
	public IInfusableItem setType(EnumEquipmentType type) {
		this.type = type;
		return this;
	}

	@Override
	public IInfusableItem setSlotContents(ItemStack contents, int slot) {
		//TODO slots[slot].setContents(contents);
		updateData();
		return this;
	}

	@Override
	public IInfusableItem updateData() {
		maxEnergy = AspectInfusionHelper.getEnergyCapacity(getSlots());
		return this;
	}

	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
		return ThaumcraftHelper.getDiscountForAspect(stack, player, aspect, discounts[armorType]);
	}

	@Override
	public boolean showNodes(ItemStack stack, EntityLivingBase entity) {
		return NBTHelper.isRevealingGoggles(stack, entity);
	}

	@Override
	public boolean showIngamePopups(ItemStack stack, EntityLivingBase entity) {
		return NBTHelper.isRevealingGoggles(stack, entity);
	}

	//TODO: RF Stuff
	@Override
	@Optional.Method(modid = "CoFHAPI|energy")
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		return 0;
	}

	@Override
	@Optional.Method(modid = "CoFHAPI|energy")
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	@Optional.Method(modid = "CoFHAPI|energy")
	public int getEnergyStored(ItemStack container) {
		return 0;
	}

	@Override
	@Optional.Method(modid = "CoFHAPI|energy")
	public int getMaxEnergyStored(ItemStack container) {
		return maxEnergy;
	}
}
