package mortvana.melteddashboard.util.helpers;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class LoadedHelper {

	/* Loaded Mod Checker */
	public static boolean isArsMagicaLoaded = Loader.isModLoaded("arsmagica2");
	public static boolean isDartcraftLoaded = Loader.isModLoaded("Dartcraft");
	public static boolean isEnderIOLoaded = Loader.isModLoaded("EnderIO");
	public static boolean isExtraTiCLoaded = Loader.isModLoaded("ExtraTiC");
	public static boolean isForestryLoaded = Loader.isModLoaded("Forestry");
	public static boolean isGrowthcraftLoaded = Loader.isModLoaded("Growthcraft");
	public static boolean isMaricultureLoaded = Loader.isModLoaded("Mariculture");
	public static boolean isMetallurgyLoaded = Loader.isModLoaded("Metallurgy");
	public static boolean isMFRLoaded = Loader.isModLoaded("MineFactoryReloaded");
	public static boolean isNaturaLoaded = Loader.isModLoaded("Natura");
	public static boolean isNetherOresLoaded = Loader.isModLoaded("NetherOres");
	public static boolean isTinkersLoaded = Loader.isModLoaded("TConstruct");
	public static boolean isThaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
	public static boolean isThermalExpansionLoaded = Loader.isModLoaded("ThermalExpansion");
	public static boolean isMekanismLoaded = Loader.isModLoaded("Mekanism");
	public static boolean isBloodMagicLoaded = Loader.isModLoaded("AWWayofTime");
	public static boolean isTSteelworkLoaded = Loader.isModLoaded("TSteelworks");
	public static boolean isBigReactorsLoaded = Loader.isModLoaded("BigReactors");
	public static boolean isRedstoneArsenalLoaded = Loader.isModLoaded("RedstoneArsenal");
	public static boolean isThermalFoundationLoaded = Loader.isModLoaded("ThermalFoundation");
	public static boolean isExtraBeesLoaded = Loader.isModLoaded("ExtraBees");
	public static boolean isRotaryCraftLoaded = Loader.isModLoaded("RotaryCraft");
	public static boolean isMagicBeesLoaded = Loader.isModLoaded("MagicBees");
	public static boolean isJABBALoaded = Loader.isModLoaded("JABBA");
	public static boolean isProjectRedLoaded = Loader.isModLoaded("ProjRed|Core");
	public static boolean isMagicalCropsLoaded = Loader.isModLoaded("magicalcrops");
	public static boolean isOpenBlocksLoaded = Loader.isModLoaded("OpenBlocks");
	public static boolean isExtraUtilitiesLoaded = Loader.isModLoaded("ExtraUtilities");
	public static boolean isAppliedEnergisticsLoaded = Loader.isModLoaded("appliedenergistics2");
	public static boolean isStevesFactoryLoaded = Loader.isModLoaded("StevesFactoryManager");
	public static boolean isArtificeLoaded = Loader.isModLoaded("Artifice");
	public static boolean isLaserCraftLoaded = Loader.isModLoaded("LaserCraft");
	public static boolean isRailcraftLoaded = Loader.isModLoaded("Railcraft");

	//Planned Stuff
	public static boolean isDragonAPILoaded = Loader.isModLoaded("DragonAPI");
	public static boolean isReactorCraftLoaded = Loader.isModLoaded("ReactorCraft");
	public static boolean isWitcheryLoaded = Loader.isModLoaded("witchery");
	public static boolean isMPSLoaded = Loader.isModLoaded("powersuits");
	public static boolean isFSPLoaded = Loader.isModLoaded("Steamcraft");
	public static boolean isPneumatiCraftLoaded = Loader.isModLoaded("PneumaticCraft");
	public static boolean isOodsModLoaded = Loader.isModLoaded("oodmod");

	//Modules
	public static boolean isGCApples = Loader.isModLoaded("Growthcraft|Apples");
	public static boolean isGCBamboo = Loader.isModLoaded("Growthcraft|Bamboo");
	public static boolean isGCBees = Loader.isModLoaded("Growthcraft|Bees");
	public static boolean isGCGrapes = Loader.isModLoaded("Growthcraft|Grapes");
	public static boolean isGCHops = Loader.isModLoaded("Growthcraft|Hops");
	public static boolean isGCRice = Loader.isModLoaded("Growthcraft|Rice");
	public static boolean isProjectRedExploration = Loader.isModLoaded("ProjRed|Exploration");

	/* OreDict Helper */
	public static boolean isSteelRegistered = !OreDictionary.getOres("ingotSteel").isEmpty();
}
