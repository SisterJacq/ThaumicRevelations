package mortvana.thaumicrevelations.core.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;

import mortvana.thaumicrevelations.api.item.infusion.IInfusableItem;
import mortvana.thaumicrevelations.api.item.infusion.IInfusionItem;
import mortvana.thaumicrevelations.api.util.enums.EnumEquipmentType;
import mortvana.thaumicrevelations.api.util.slot.SlotInfusion;
import mortvana.thaumicrevelations.library.ThaumicLibrary;
import mortvana.thaumicrevelations.melteddashboard.item.ItemArmorFluxGear;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;

public class ArmorInfusableBase extends ItemArmorFluxGear implements IInfusableItem, IVisDiscountGear {

	protected int unlocked;
	protected int locked;
	protected SlotInfusion[] slots;
	protected int[] discounts = new int[4];
	protected EnumEquipmentType type;
	protected boolean enchantable = true;
	protected int durability = 0;
	protected boolean damageable = true;
	protected int rarity = 0;

	public ArmorInfusableBase(ArmorMaterial material, int index, int type, String name, String sheet, String icon) {
		super(material, index, type, name, sheet, icon);
		this.type = EnumEquipmentType.values()[type];
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return enchantable;
	}

	public ArmorInfusableBase setEnchantable(boolean bool) {
		enchantable = bool;
		return this;
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(ThaumicLibrary.DURABILITY)) {
			return stack.getTagCompound().getInteger(ThaumicLibrary.DURABILITY);
		} else if(durability != 0) {
			return durability;
		} else {
			return getMaxDurability();
		}
	}

	@Override
	public boolean isDamageable() {
		return damageable;
	}

	public ArmorInfusableBase setDamageable(boolean bool) {
		damageable = bool;
		return this;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[rarity];
	}

	@Override
	public int getNumberSlotsTotal() {
		return unlocked + locked;
	}

	@Override
	public int getNumberSlotsUnlocked() {
		return unlocked;
	}

	@Override
	public int getNumberSlotsLocked() {
		return locked;
	}

	@Override
	public IInfusableItem setNumberSlots(int unlocked, int locked) {
		this.unlocked = unlocked;
		this.locked = locked;
		slots = new SlotInfusion[unlocked + locked];
		return this;
	}

	@Override
	public IInfusionItem getSlotItem(int slot) {
		Item contents = slots[slot].getContents().getItem();
		return contents instanceof IInfusionItem ? (IInfusionItem) contents : null;
	}

	@Override
	public ItemStack getSlotContent(int slot) {
		return slots[slot].getContents();
	}

	@Override
	public Aspect getSlotAspect(int slot) {
		return getSlotItem(slot).getAspect();
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
		slots[slot].setContents(contents);
		return this;
	}

	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
		return discounts[armorType];
	}
}
