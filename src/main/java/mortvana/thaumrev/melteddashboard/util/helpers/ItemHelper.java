package mortvana.thaumrev.melteddashboard.util.helpers;

import net.minecraft.item.ItemStack;

public class ItemHelper {

	public static ItemStack cloneStack(ItemStack stack, int size) {
		if (stack != null) {
			ItemStack ret = stack.copy();
			ret.stackSize = size;
			return ret;
		} else {
			return null;
		}
	}

}
