package mortvana.thaumrev.common;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import mortvana.melteddashboard.util.ConfigBase;

public class ThaumRevConfig extends ConfigBase {

	public static Configuration config;

	public ThaumRevConfig(FMLPreInitializationEvent event, String location) {
		super(event, location);
	}

	@Override
	public void loadConfig(File file) {
		ThaumicRevelations.logger.info("Loading Thaumic Revelations Config");
		config = new Configuration(file);
		config.load();

		//enableWarden = getModule("Warden's Arsenal", "All the tools and contraptions of the Thaumic (and Flux) Wardens.", true);
		//enableEldritch = getModule("Eldritch Workings", "The dark side of Thaumaturgy, tinkering with the void itself", true);
		//enableGadgetry = getModule("Thaumic Gadgetry", "Various miscellaneous things that can't be easily categorized.", true);
		//enableMagneoturgy = getModule("Magneoturgy", "Thaumaturgy meets Redstone Flux! Other technology integration as well.", true);
		//enableChronoturgy = getModule("Chronoturgy", "Thaumaturgy of the past (and future), right now.", true);
		//enableExpanded = getModule("Expanded Thaumaturgy", "Integrating our stuff into other stuff.", false);
		//enableIntegration = getModule("Thamic Integration", "Integrating other stuff with our stuff. Not to be confused with Derpkus' mod.", true);
		//enableDynamics = getModule("Thaumic Dynamics", "Essentia. It's useful.", true);
		//enableGolemancy = getModule("Advanced Golemancy", "Be the Kreiger of golemancy! You can't make Nigel though...", true);
		//enableReliquary = getModule("Xeno's (Thaumic) Reliquary", "Xeno's Reliquary done thaumicly!", false);
		//enableStandardist = getModule("Ichabodian Standardist System", "Total Aspect and Research Tree Overhaul.", false);

		researchLevel = config.get("Miscellaneous", "Research Difficulty", -1, "Independent from Thaumcraft's Research Difficulty. Determines which researches auto-unlock. -1 for using Thaumcraft's difficulty, 0 for easy, 1 for normal (default Thaumcraft setting), 2 for hard.").getInt();

		enableBrass = config.get("Resources", "Enable Brass", true, "Determines whether or not our Brass is craftable. Disabling without other mods adding Brass will break stuff.").getBoolean();
		enableBronze = config.get("Resources", "Enable Bronze", true, "Determines whether or not our Bronze is craftable. Disabling without other mods adding Bronze will break stuff.").getBoolean();
		enableBismuthBronze = config.get("Resources", "Enable Bismuth Bronze", true, "Determines whether or not our Bismuth Bronze is craftable. Disabling without other mods adding Bismuth Bronze will break stuff.").getBoolean();
		enableMithril = config.get("Resources", "Enable Mithril", true, "Determines whether or not our Mithril (Arseno-Antimonial Bronze) is craftable. Disabling without other mods adding REAL Mithril (Fantasy Mithril doesn't count) will break stuff.").getBoolean();
		enableAlBronze = config.get("Resources", "Enable Aluminium Bronze", true, "Determines whether or not our Aluminium Bronze is craftable. Disabling without other mods adding Aluminium Bronze will break stuff.").getBoolean();
		enableCupronickel = config.get("Resources", "Enable Cupronickel", true, "Determines whether or not our Cupronickel is craftable. Disabling without other mods adding Cupronickel will break stuff.").getBoolean();
		enableRiftishBronze = config.get("Resources", "Enable Tinker's Bronze", true, "Determines whether or not our Tinker's Bronze is craftable. Disabling without other mods adding Tinker's Bronze will break stuff.").getBoolean();
		enableConstantan = config.get("Resources", "Enable Constantan", true, "Determines whether or not our Constantan is craftable. Disabling without other mods adding Constantan will break stuff.").getBoolean();
		enableInvar = config.get("Resources", "Enable Invar", true, "Determines whether or not our Invar is craftable. Disabling without other mods adding Invar will break stuff.").getBoolean();
		enableElectrum = config.get("Resources", "Enable Electrum", true, "Determines whether or not our Electrum is craftable. Disabling without other mods adding Electrum will break stuff.").getBoolean();
		enableOsLu = config.get("Resources", "Enable Osmium-Lutetium Alloy", true, "Determines whether or not our Osmium-Lutetium Alloy is craftable. Disabling without other mods adding the Osmium-Lutetium Alloy will break stuff.").getBoolean();

		backwardsAlBronze = config.get("Compatiblity", "Backwards Aluminium Bronze", false, "When enabled, Aluminium Bronze requires 3 Aluminium and 1 Copper, like in TiC, instead of the pseudo-realistic 3 Copper and 1 Aluminium (Typically only has 6-11% Al IRL).").getBoolean();

		enableChalcocite = config.get("World", "Enable Chalcocite Ore", true, "Adds Chalcocite (Copper Sulfide) Ore to the Overworld. Disabling without other mods adding a source of Copper will break stuff.").getBoolean();
		enableSphalerite = config.get("World", "Enable Sphalerite Ore", true, "Adds Sphalerite (Zinc Sulfide) Ore to the Overworld. Disabling without other mods adding a source of Zinc will break stuff.").getBoolean();
		enableCassiterite = config.get("World", "Enable Cassiterite Ore", true, "Adds Sphalerite (Tin Oxide) Ore to the Overworld. Disabling without other mods adding a source of Tin will break stuff.").getBoolean();
		enableMillerite = config.get("World", "Enable Millerite Ore", true, "Adds Millerite (Nickel Sulfide) Ore to the Nether. Disabling without other mods adding a source of Nickel Ore or Nickel and Palladium will break stuff.").getBoolean();
		enableNativeSilver = config.get("World", "Enable Native Silver Ore", true, "Adds Native Silver Ore to the Overworld. Disabling without other mods adding a source of Silver will break stuff.").getBoolean();
		enableGalena = config.get("World", "Enable Galena Ore", true, "Adds Galena (Lead Sulfide) Ore to the Overworld. Disabling without other mods adding a source of Lead will break stuff.").getBoolean();
		enableXenotime = config.get("World", "Enable Xenotime Ore", true, "Adds Xenotime (Rare Earth Phosphate) Ore to the Overworld. Disabling without other mods adding a source of Neodymium and Lutetium will break stuff.").getBoolean();
		enableWolframite = config.get("World", "Enable Wolframite Ore", true, "Adds Wolframite (Iron-Tungsten Oxide) Ore to the Overworld. Disabling without other mods adding a source of Tungsten will break stuff.").getBoolean();
		enableIridosmium = config.get("World", "Enable Iridosmium Ore", true, "Adds Iridosmium (Natural Iridium-Osmium Alloy) Ore to the Nether. Disabling without other mods adding a source of Osmium and Iridium will break stuff.").getBoolean();
		enableBismuthinite = config.get("World", "Enable Bismuthinite Ore", true, "Adds Bismuthinite (Bismuth Sulfide) Ore to the Overworld. Disabling without other mods adding a source of Bismuth will break stuff.").getBoolean();
		enableTennantite = config.get("World", "Enable Tennantite Ore", true, "Adds Tennantite (Copper Arsenic Sulfide) Ore to the Overworld. Disabling without other mods adding a source of Arsenic will break stuff.").getBoolean();
		enableTetrahedrite = config.get("World", "Enable Tetrahedrite Ore", true, "Adds Tetrahedrite (Copper Antimony Sulfide) Ore to the Overworld. Disabling without other mods adding a source of Antimony will break stuff.").getBoolean();
		enablePyrope = config.get("World", "Enable Pyrope Ore", true, "Adds Pyrope Ore to the Nether. Disabling without other mods adding a source of Pyrope will break stuff.").getBoolean();
		enableDioptase = config.get("World", "Enable Dioptase Ore", true, "Adds Dioptase Ore to the Overworld. Disabling without other mods adding a source of Dioptase will break stuff.").getBoolean();
		enableFluonicSapphire = config.get("World", "Enable Fluonic Sapphire Ore", true, "Adds Fluonic Sapphire Ore to the Overworld. Disabling without other mods adding a source of Fluonic Sapphire will break stuff.").getBoolean();

		thaumonomiconAlloySmelt = config.get("Clientside", "Enable Thaumonomicon Smelting", true, "Enables showing of obvious smelting recipes in the Thaumonomicon.").getBoolean();

		if (config.hasChanged()) {
			config.save();
		}
		ThaumicRevelations.logger.info("Thaumic Revelations Config Loaded");
	}

	public boolean getModule(String name, String description, boolean bool) {
		return config.get("Modules", "Enable " + name, bool, description).getBoolean();
	}

	public static boolean enableWarden;
	public static boolean enableEldritch;
	public static boolean enableGadgetry;
	public static boolean enableMagneoturgy;
	public static boolean enableChronoturgy;
	public static boolean enableExpanded;
	public static boolean enableIntegration;
	public static boolean enableDynamics;
	public static boolean enableGolemancy;
	//public static boolean enableReliquary; Ask Xeno
	public static boolean enableStandardist;

	public static int researchLevel;

	public static boolean thaumonomiconAlloySmelt;

	public static boolean enableBrass;
	public static boolean enableBronze;
	public static boolean enableBismuthBronze;
	public static boolean enableMithril;
	public static boolean enableAlBronze;
	public static boolean enableCupronickel;
	public static boolean enableRiftishBronze;
	public static boolean enableConstantan;
	public static boolean enableInvar;
	public static boolean enableElectrum;
	public static boolean enableOsLu;

	public static boolean enableSteel;

	public static boolean enableChalcocite;
	public static boolean enableSphalerite;
	public static boolean enableCassiterite;
	public static boolean enableMillerite;
	public static boolean enableNativeSilver;
	public static boolean enableGalena;
	public static boolean enableXenotime;
	public static boolean enableWolframite;
	public static boolean enableIridosmium;
	public static boolean enableBismuthinite;
	public static boolean enableTennantite;
	public static boolean enableTetrahedrite;
	public static boolean enablePyrope;
	public static boolean enableDioptase;
	public static boolean enableFluonicSapphire;

	public static boolean backwardsAlBronze;

	public static boolean rightClickHarvest = true; //TODO: MDCore-ify
}
