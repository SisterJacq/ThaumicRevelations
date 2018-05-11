package mortvana.thaumrev.api.item.infusion;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Optional;

import thaumcraft.api.IGoggles;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.nodes.IRevealer;

import cofh.api.energy.IEnergyContainerItem;

import mortvana.thaumrev.api.item.IContainerItem;
import mortvana.thaumrev.api.util.enums.EnumEquipmentType;

/**
 * An interface to implement on Items, either armor, weapons, or tools, to allow for them to use Thaumic Revelations'
 * Aspect Infusion Orbs (and other IInfusionItems) to augment them.
 *
 * @author Mortvana
 */

@Optional.Interface(modid = "CoFHAPI|energy", iface = "cofh.api.energy.IEnergyContainerItem")
public interface IInfusableItem extends IContainerItem, IGoggles, IRevealer, IVisDiscountGear, IEnergyContainerItem {

	/**
	 *  Sets both the number of locked slots and unlocked slots on a given item. May overwrite any already entered data.
	 *
	 *  @param unlocked - Number of unlocked slots
	 *  @param locked - Number of locked slots
	 *  @return - An IInfusableItem. Should return the Item having it's slots set.
	 */
	IInfusableItem setNumberSlots(ItemStack stack, int unlocked, int locked);

	/**
	 * Returns the EnumEquipmentType of the IInfusableItem, which determines the effect given by a given Aspect.
	 *
	 * @return - EnumEquipmentType of the item queried
	 */
	EnumEquipmentType getType(ItemStack stack);

	/**
	 * Sets the EnumEquipmentType of the IInfusableItem, which determines the effect given by a given Aspect.
	 *
	 * @param type - New EnumEquipmentType of the IInfusableItem
	 *
	 * @return - IInfusableItem that had its type set
	 */
	IInfusableItem setType(ItemStack stack, EnumEquipmentType type);
}
