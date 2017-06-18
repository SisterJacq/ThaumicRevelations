package mortvana.thaumicrevelations.api.item.infusion;

import net.minecraft.item.ItemStack;

import mortvana.thaumicrevelations.api.util.enums.EnumEquipmentType;
import thaumcraft.api.aspects.Aspect;

/**
 *  An interface to implement on Items, either armor, weapons, or tools, to allow for them to use Thaumic Revelations'
 *  Aspect Infusion Orbs (and other IInfusionItems) to augment them.
 *
 *  @author Mortvana
 * */
public interface IInfusableItem {

	/**
	 *  Returns the total number of slots for infusions on an item. This means both innate equipment infusions, and
	 *  player-chosen infusions.
	 *
	 *  @return - Total number of slots
	 */
	int getNumberSlotsTotal();

	/**
	 *  Returns the number of locked slots on an item. Locked slots come with pre-assigned aspect infusions.
	 *
	 *  @return - Number of locked slots
	 */
	int getNumberSlotsUnlocked();

	/**
	 *  Returns the number of unlocked slots on an item. Unlocked slots are the standard slots which are taken up by
	 *  players placing IInfusionItems in them.
	 *
	 *  @return - Number of unlocked slots
	 */
	int getNumberSlotsLocked();

	/**
	 *  Sets both the number of locked slots and unlocked slots on a given item. May overwrite any already entered data.
	 *
	 *  @param unlocked - Number of unlocked slots
	 *  @param locked - Number of locked slots
	 *  @return - An IInfusableItem. Should return the Item having it's slots set.
	 */
	IInfusableItem setNumberSlots(int unlocked, int locked);

	/**
	 *  Returns the Item implementing IInfusionItem in a given slot, such as Thaumic Revelations' Aspect Orbs.
	 *
	 *  @param slot - The numeric ID of the slot being queried
	 *  @return - The Item implementing IInfusionItem in the queried slot
	 * */
	IInfusionItem getSlotItem(int slot);

	/**
	 *  Returns the ItemStack of an Item implementing IInfusionItem in a given slot.
	 *
	 *  @param slot - The numeric ID of the slot being queried
	 *  @return - The ItemStackof an Item implementing IInfusionItem in the queried slot
	 */
	ItemStack getSlotContent(int slot);

	/**
	 *  Returns the Aspect contained within the IInfusionItem in a given slot. If the slot is empty or the IInfusionItem
	 *  is disabled, returns null.
	 *
	 *  @param slot - The numeric ID of the slot being queried
	 *  @return - The Aspect contained within the queried slot
	 */
	Aspect getSlotAspect(int slot);

	//Aspect getSlotPotency(int slot);

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

}
