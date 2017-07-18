package mortvana.thaumrev.core.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import mortvana.thaumrev.core.common.config.*;
import mortvana.thaumrev.eldritch.common.EldritchContent;
import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.melteddashboard.util.module.ModuleLoader;
import mortvana.thaumrev.core.network.CommonProxy;
import mortvana.thaumrev.melteddashboard.lib.ThaumcraftLibrary;
import mortvana.thaumrev.warden.common.WardenContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ThaumRevLibrary.MOD_ID, name = ThaumRevLibrary.MOD_NAME, version = ThaumRevLibrary.MOD_VERSION, dependencies = ThaumRevLibrary.MOD_DEPENDENCIES)
public class ThaumicRevelations {

    @Instance
    public static ThaumicRevelations instance;

    @SidedProxy(clientSide = "mortvana.thaumrev.core.network.ClientProxy", serverSide = "mortvana.thaumrev.core.network.CommonProxy", modId = ThaumRevLibrary.MOD_ID)
    public static CommonProxy proxy;

	// Move to Melted Dashboard Core once I have enough stuff to warrant creating it as a separate library.
    public static final Logger logger = LogManager.getLogger("Flux Gear");

    public ModuleLoader moduleLoader = new ModuleLoader(ThaumRevLibrary.MOD_ID, logger);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        moduleLoader.addModule("Core", true, new ThaumRevContent(), new ThaumicRevelationsCoreConfig(event, "Mortvana/ThaumicRevelations-Core.cfg"));
        moduleLoader.addModule("ThaumicWarden", ThaumicRevelationsCoreConfig.enableWarden, new WardenContent(), new ThaumicWardenConfig(event, "Mortvana/ThaumicRevelations-ThaumicWarden.cfg"));
        moduleLoader.addModule("Eldritch Workings", ThaumicRevelationsCoreConfig.enableEldritch, new EldritchContent(), new EldritchWorkingsConfig(event, "Mortvana/ThaumicRevelations-EldritchWorkings.cfg"));

        moduleLoader.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        moduleLoader.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
	    ThaumcraftLibrary.init();
        moduleLoader.postInit(event);
    }
}
