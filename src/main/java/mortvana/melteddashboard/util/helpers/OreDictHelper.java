package mortvana.melteddashboard.util.helpers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHelper {

	public static boolean isOreNameEqual(ItemStack stack, String oredict) {
		int[] names = OreDictionary.getOreIDs(stack);
		for (int id : names) {
			if (OreDictionary.getOreName(id).equals(oredict)) {
				return true;
			}
		}
		return false;
	}

}
