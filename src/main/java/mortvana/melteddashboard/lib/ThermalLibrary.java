package mortvana.melteddashboard.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ThermalLibrary {

	private static boolean loaded = false;

	static final String TF = "ThermalFoundation";

	public static void init() {}

	private static void load() {
		itemMaterial = GameRegistry.findItem(TF, "material");

		dustPyrotheum = new ItemStack(itemMaterial, 1, 512);
		dustCryotheum = new ItemStack(itemMaterial, 1, 513);
	}

	/** BLOCKS (TF) **/

	/** EQUIPMENT (TF) **/

	/** ITEMS (TF) **/
	public static Item itemMaterial;

	/** ITEMSTACKS (TF) **/
	public static ItemStack dustPyrotheum;
	public static ItemStack dustCryotheum;
}
