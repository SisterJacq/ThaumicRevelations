package mortvana.thaumrev.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import mortvana.thaumrev.melteddashboard.util.ConfigBase;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

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

	    cuAlloys = config.get("Miscellaneous", "Enable Bronze and Brass", true, "Determines whether or not our Bronze and Brass is craftable. Disabling without other mods adding Bronze and Brass will break stuff.").getBoolean();

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

	public static boolean cuAlloys;
}
