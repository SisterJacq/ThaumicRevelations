package mortvana.thaumicrevelations.api.item;

import net.minecraft.item.ItemStack;

/**
 *  If this looks familiar, it's because it is. No need to reinvent the wheel when King Lemming has already made this
 *  simple interface. If you still haven't figured out, this is originally from CoFHLib, which is under the LGPLv3
 *  license.
 *
 *  Implement this interface on Item classes that are themselves inventories.
 *
 *  @author King Lemming
 *  (I, Mortvana, simply shaded it)
 */
public interface IInvetoryContainerItem {

	/**
	 * Get the size of this inventory of this container item.
	 */
	int getSizeInventory(ItemStack container);
}
