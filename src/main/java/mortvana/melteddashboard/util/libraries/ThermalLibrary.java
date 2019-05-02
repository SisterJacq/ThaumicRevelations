package mortvana.melteddashboard.util.libraries;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

import mortvana.melteddashboard.util.helpers.LoadedHelper;

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
		if (LoadedHelper.isThermalFoundationLoaded) {
			itemMaterialTF = GameRegistry.findItem(TF, "material");

			dustIronTF = new ItemStack(itemMaterialTF, 1, 0);

			nuggetIronTF = new ItemStack(itemMaterialTF, 1, 8);

			itemCinnabar = new ItemStack(itemMaterialTF, 1, 20);

			dustSilverTF = new ItemStack(itemMaterialTF, 1, 34);
			dustLeadTF = new ItemStack(itemMaterialTF, 1, 35);

			ingotSilverTF = new ItemStack(itemMaterialTF, 1, 66);
			ingotLeadTF = new ItemStack(itemMaterialTF, 1, 67);

			ingotSilverTF = new ItemStack(itemMaterialTF, 1, 98);
			ingotLeadTF = new ItemStack(itemMaterialTF, 1, 99);

			dustPyrotheum = new ItemStack(itemMaterialTF, 1, 512);
			dustCryotheum = new ItemStack(itemMaterialTF, 1, 513);

			powderBlizz = new ItemStack(itemMaterialTF, 1, 1025);
			powderBlitz = new ItemStack(itemMaterialTF, 1, 1027);
			powderBasalz = new ItemStack(itemMaterialTF, 1, 1029);
		}

		if (LoadedHelper.isThermalExpansionLoaded) {
			itemMaterialTE = GameRegistry.findItem(TE, "material");

			itemSlag = new ItemStack(itemMaterialTE, 1, 514);
			itemSlagRich = new ItemStack(itemMaterialTE, 1, 515);
		}
	}

	/** MULTIPLIERS **/
	//TODO: Get quantities from TE Config
	public static int multPulvDefault = 2;
	public static int multSmeltDefault = 2;
	public static int multSmeltSpecial = 3;

	/** BLOCKS **/

	/** EQUIPMENT **/

	/** ITEMS **/
	public static Item itemMaterialTF;
	public static Item itemMaterialTE;

	/** ITEMSTACKS (TF) **/
	public static ItemStack dustIronTF;

	public static ItemStack nuggetIronTF;

	public static ItemStack itemCinnabar;

	public static ItemStack dustSilverTF;
	public static ItemStack dustLeadTF;

	public static ItemStack ingotSilverTF;
	public static ItemStack ingotLeadTF;

	public static ItemStack nuggetSilverTF;
	public static ItemStack nuggetLeadTF;

	public static ItemStack dustPyrotheum;
	public static ItemStack dustCryotheum;

	public static ItemStack powderBlizz;
	public static ItemStack powderBlitz;
	public static ItemStack powderBasalz;

	/** ITEMSTACKS (TE) **/
	public static ItemStack itemSlag;
	public static ItemStack itemSlagRich;
}
