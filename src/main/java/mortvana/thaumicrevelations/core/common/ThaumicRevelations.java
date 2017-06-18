package mortvana.thaumicrevelations.core.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import mortvana.thaumicrevelations.core.common.config.*;
import mortvana.thaumicrevelations.eldritch.common.EldritchContent;
import mortvana.thaumicrevelations.library.ThaumicLibrary;
import mortvana.thaumicrevelations.melteddashboard.util.module.ModuleLoader;
import mortvana.thaumicrevelations.core.network.CommonProxy;
import mortvana.thaumicrevelations.warden.common.WardenContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ThaumicLibrary.MOD_ID, name = ThaumicLibrary.MOD_NAME, version = ThaumicLibrary.MOD_VERSION, dependencies = ThaumicLibrary.MOD_DEPENDENCIES)
public class ThaumicRevelations {

    @Instance
    public static ThaumicRevelations instance;

    @SidedProxy(clientSide = "mortvana.thaumicrevelations.core.network.ClientProxy", serverSide = "mortvana.thaumicrevelations.core.network.CommonProxy", modId = ThaumicLibrary.MOD_ID)
    public static CommonProxy proxy;

	// Move to Melted Dashboard Core once I have enough stuff to warrant creating it as a separate library.
	// So basically during after getting out of alpha and into beta, so v0.1.0.0.
    public static final Logger logger = LogManager.getLogger("Flux Gear");

    public ModuleLoader moduleLoader = new ModuleLoader(ThaumicLibrary.MOD_ID, logger);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        moduleLoader.addModule("Core", true, new ThaumicContent(), new ThaumicRevelationsCoreConfig(event, "Mortvana/ThaumicRevelations-Core.cfg"));
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
        moduleLoader.postInit(event);
    }
}
