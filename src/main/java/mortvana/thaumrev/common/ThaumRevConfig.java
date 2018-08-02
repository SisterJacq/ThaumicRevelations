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

		researchLevel = config.get("Miscellaneous", "Research Difficulty", -1, "Independent from Thaumcraft's Research Difficulty. Determines which researches auto-unlock. -1 for using Thaumcraft's difficulty, 0 for easy, 1 for normal (default Thaumcraft setting), 2 for hard.").getInt(-1);

		enableBrass = config.get("Resources", "Enable Brass", true, "Determines whether or not our Brass is craftable. Disabling without other mods adding Brass will break stuff.").getBoolean(true);
		enableBronze = config.get("Resources", "Enable Bronze", true, "Determines whether or not our Bronze is craftable. Disabling without other mods adding Bronze will break stuff.").getBoolean(true);
		enableBismuthBronze = config.get("Resources", "Enable Bismuth Bronze", true, "Determines whether or not our Bismuth Bronze is craftable. Disabling without other mods adding Bismuth Bronze will break stuff.").getBoolean(true);
		enableMithril = config.get("Resources", "Enable Mithril", true, "Determines whether or not our Mithril (Arseno-Antimonial Bronze) is craftable. Disabling without other mods adding REAL Mithril (Fantasy Mithril doesn't count) will break stuff.").getBoolean(true);
		enableAlBronze = config.get("Resources", "Enable Aluminium Bronze", true, "Determines whether or not our Aluminium Bronze is craftable. Disabling without other mods adding Aluminium Bronze will break stuff.").getBoolean(true);
		enableCupronickel = config.get("Resources", "Enable Cupronickel", true, "Determines whether or not our Cupronickel is craftable. Disabling without other mods adding Cupronickel will break stuff.").getBoolean(true);
		enableRiftishBronze = config.get("Resources", "Enable Tinker's Bronze", true, "Determines whether or not our Tinker's Bronze is craftable. Disabling without other mods adding Tinker's Bronze will break stuff.").getBoolean(true);
		enableConstantan = config.get("Resources", "Enable Constantan", true, "Determines whether or not our Constantan is craftable. Disabling without other mods adding Constantan will break stuff.").getBoolean(true);
		enableInvar = config.get("Resources", "Enable Invar", true, "Determines whether or not our Invar is craftable. Disabling without other mods adding Invar will break stuff.").getBoolean(true);
		enableElectrum = config.get("Resources", "Enable Electrum", true, "Determines whether or not our Electrum is craftable. Disabling without other mods adding Electrum will break stuff.").getBoolean(true);
		enableOsLu = config.get("Resources", "Enable Osmium-Lutetium Alloy", true, "Determines whether or not our Osmium-Lutetium Alloy is craftable. Disabling without other mods adding the Osmium-Lutetium Alloy will break stuff.").getBoolean(true);

		backwardsAlBronze = config.get("Compatiblity", "Backwards Aluminium Bronze", false, "When enabled, Aluminium Bronze requires 3 Aluminium and 1 Copper, like in TiC, instead of the pseudo-realistic 3 Copper and 1 Aluminium (Typically only has 6-11% Al IRL). Also OreDicts the two as the same").getBoolean(false);

		thaumonomiconAlloySmelt = config.get("Clientside", "Enable Thaumonomicon Smelting", true, "Enables showing of obvious smelting recipes in the Thaumonomicon.").getBoolean();

		debug = config.get("Melted Dashboard - Debug", "Enable Debug", false).getBoolean(false);

		rightClickHarvest = config.get("Melted Dashboard - General", "Right-Click Harvest", true, "Determines whether crops added by mods using Melted Dashboard Core can by harvested by right-clicking.").getBoolean(true);

		seaLevel = config.get("Melted Dashboard - World", "Sea Level", 64, "Offered for mods that alter world heights, looking at you TFC").getInt(64);
		poorOre = config.get("Melted Dashboard - World", "Poor Ores", true, "Enable Poor Ores set to Automatic when Railcraft is installed. When disabled, individual Poor Ores can still be enabled").getBoolean(true);
		gravelOre = config.get("Melted Dashboard - World", "Gravel Ores", true, "Enable Gravel Ores set to Automatic when Tinker's Construct is installed. When disabled, individual Gravel Ores can still be enabled").getBoolean(true);

		if (config.hasChanged()) {
			config.save();
		}
		ThaumicRevelations.logger.info("Thaumic Revelations Config Loaded");
	}

	public boolean getModule(String name, String description, boolean bool) {
		return config.get("Modules", "Enable " + name, bool, description).getBoolean();
	}

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

	public static boolean backwardsAlBronze;

	//TODO: MDCore-ify These
	public static boolean debug;
	public static boolean rightClickHarvest;
	public static int seaLevel;
	public static boolean poorOre;
	public static boolean gravelOre;
}
