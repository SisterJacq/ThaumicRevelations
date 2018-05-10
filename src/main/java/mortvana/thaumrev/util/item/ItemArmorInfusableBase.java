package mortvana.thaumrev.util.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.common.Optional;
import net.minecraftforge.common.ISpecialArmor;

import thaumcraft.api.aspects.Aspect;

import mortvana.melteddashboard.item.ItemArmorFluxGear;
import mortvana.melteddashboard.util.helpers.*;
import mortvana.melteddashboard.util.helpers.mod.ThaumcraftHelper;

import mortvana.thaumrev.api.item.infusion.IInfusableItem;
import mortvana.thaumrev.api.util.enums.EnumEquipmentType;
import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.util.enums.EnumPrimalAspect;

public abstract class ItemArmorInfusableBase extends ItemArmorFluxGear implements ISpecialArmor, IInfusableItem {

	protected EnumEquipmentType type;

	protected int visDiscount[] = {0, 0, 0, 0, 0, 0};
	protected int maxEnergy;

	public ItemArmorInfusableBase(String material, int index, int type, String name, String sheet, String icon) {
		super(material, index, type, name, sheet, icon);
		this.type = EnumEquipmentType.values()[type];
		setCreativeTab(ThaumRevLibrary.generalTab);
	}

	public ItemArmorInfusableBase(String material, int index, int type) {
		super(material, index, type);
		this.type = EnumEquipmentType.values()[type];
		setCreativeTab(ThaumRevLibrary.generalTab);
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		ThaumcraftHelper.addDiscountInformation(stack, player, list, par4);
		AspectInfusionHelper.addInformation(stack, player, list, par4);
		super.addInformation(stack, player, list, par4);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		AspectInfusionHelper.applyInfusions(world, player, stack, getType(stack));
		super.onArmorTick(world, player, stack);
	}

	@Override
	public void setDamage(ItemStack stack, int damage) {
		if (damage > getMaxDamage()) {
			stack.setItemDamage(getMaxDamage());
			NBTHelper.setBroken(stack, true);
		}
	}

	/** ISpecialArmor **/
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, damageReduceAmount / 25D, 20);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return NBTHelper.isBroken(armor) ? 0 : damageReduceAmount;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		/*if (!NBTHelper.isBroken(stack)) {
			if (source != DamageSource.fall) {
				stack.damageItem(damage, entity);
				AspectInfusionHelper.damageArmor(entity, stack, source, damage, slot);
			}
		}*/
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
		return type;
	}

	@Override
	public IInfusableItem setType(ItemStack stack, EnumEquipmentType type) {
		this.type = type;
		return this;
	}

	/** IVisDiscountGear **/
	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
		return ThaumcraftHelper.getDiscountForAspect(stack, player, aspect, visDiscount[EnumPrimalAspect.getPrimal(aspect).ordinal()]);
	}

	/** IRevealer **/
	@Override
	public boolean showNodes(ItemStack stack, EntityLivingBase entity) {
		return NBTHelper.isRevealingGoggles(stack, entity);
	}

	/** IGoogles **/
	@Override
	public boolean showIngamePopups(ItemStack stack, EntityLivingBase entity) {
		return NBTHelper.isRevealingGoggles(stack, entity);
	}

	//TODO: RF Stuff
	/** IEnergyContainerItem **/
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
