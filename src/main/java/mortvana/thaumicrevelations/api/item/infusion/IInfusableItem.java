package mortvana.thaumicrevelations.api.item.infusion;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Optional;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.item.IInventoryContainerItem;

import mortvana.thaumicrevelations.api.util.enums.EnumEquipmentType;
import mortvana.thaumicrevelations.api.util.slot.SlotInfusion;
import thaumcraft.api.aspects.Aspect;

/**
 *  An interface to implement on Items, either armor, weapons, or tools, to allow for them to use Thaumic Revelations'
 *  Aspect Infusion Orbs (and other IInfusionItems) to augment them.
 *
 *  @author Mortvana
 */

@Optional.Interface(modid = "CoFHAPI|energy", iface = "cofh.api.energy.IEnergyContainerItem")
public interface IInfusableItem extends IInventoryContainerItem, IEnergyContainerItem {

	/**
	 *  Returns the total number of slots for infusions on an item. This means both innate equipment infusions, and
	 *  player-chosen infusions.
	 *
	 *  @return - Total number of slots
	 */
	int getNumberSlotsTotal(ItemStack stack);

	/**
	 *  Returns the number of locked slots on an item. Locked slots come with pre-assigned aspect infusions.
	 *
	 *  @return - Number of locked slots
	 */
	int getNumberSlotsUnlocked(ItemStack stack);

	/**
	 *  Returns the number of unlocked slots on an item. Unlocked slots are the standard slots which are taken up by
	 *  players placing IInfusionItems in them.
	 *
	 *  @return - Number of unlocked slots
	 */
	int getNumberSlotsLocked(ItemStack stack);

	/**
	 *  Sets both the number of locked slots and unlocked slots on a given item. May overwrite any already entered data.
	 *
	 *  @param unlocked - Number of unlocked slots
	 *  @param locked - Number of locked slots
	 *  @return - An IInfusableItem. Should return the Item having it's slots set.
	 */
	IInfusableItem setNumberSlots(int unlocked, int locked);

	/**
	 *  Returns the ItemStack of an Item implementing IInfusionItem in a given slot.
	 *
	 *  @param slot - The numeric ID of the slot being queried
	 *  @return - The ItemStackof an Item implementing IInfusionItem in the queried slot
	 */
	ItemStack getSlotContents(int slot);

	SlotInfusion[] getSlots();

	/**
	 *  Returns whether or not a slot is locked. Returns true if it is unlocked, and false if it is locked.
	 *
	 *  @param slot - The numeric ID of the slot being queried
	 *  @return - If the slot is unlocked
	 */
	boolean isSlotUnlocked(int slot);

	/**
	 *  Returns the EnumEquipmentType of the IInfusableItem, which determines the effect given by a given Aspect.
	 *
	 *  @return - EnumEquipmentType of the item queried
	 */
	EnumEquipmentType getType();

	IInfusableItem setType(EnumEquipmentType type);

	IInfusableItem setSlotContents(ItemStack contents, int slot);

	IInfusableItem updateData();

}
