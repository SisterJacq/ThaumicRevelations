package mortvana.melteddashboard.util.libraries;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ThermalLibrary {

	private static boolean loaded = false;

	static final String TF = "ThermalFoundation";
	static final String TE = "ThermalExpansion";

	public static void init() {
		if (!loaded) {
			load();
		}
		loaded = true;
	}

	private static void load() {
		itemMaterialTF = GameRegistry.findItem(TF, "material");
		itemMaterialTE = GameRegistry.findItem(TE, "material");

		itemCinnabar = new ItemStack(itemMaterialTF, 1, 20);

		dustPyrotheum = new ItemStack(itemMaterialTF, 1, 512);
		dustCryotheum = new ItemStack(itemMaterialTF, 1, 513);

		itemSlag = new ItemStack(itemMaterialTE, 1, 514);
		itemSlagRich = new ItemStack(itemMaterialTE, 1, 515);
	}

	/** BLOCKS **/

	/** EQUIPMENT **/

	/** ITEMS **/
	public static Item itemMaterialTF;
	public static Item itemMaterialTE;

	/** ITEMSTACKS (TF) **/
	public static ItemStack itemCinnabar;

	public static ItemStack dustPyrotheum;
	public static ItemStack dustCryotheum;

	/** ITEMSTACKS (TE) **/
	public static ItemStack itemSlag;
	public static ItemStack itemSlagRich;
}
