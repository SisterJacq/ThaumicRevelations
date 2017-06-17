package mortvana.thaumicrevelations.core.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import mortvana.thaumicrevelations.core.common.config.*;
import mortvana.thaumicrevelations.eldritch.common.EldritchContent;
import mortvana.thaumicrevelations.melteddashboard.util.module.ModuleLoader;
import mortvana.thaumicrevelations.core.network.CommonProxy;
import mortvana.thaumicrevelations.warden.common.WardenContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ThaumicRevelations.MOD_ID, name = ThaumicRevelations.MOD_NAME, version = ThaumicRevelations.MOD_VERSION, dependencies = ThaumicRevelations.MOD_DEPENDENCIES)
public class ThaumicRevelations {

    public static final String MOD_ID = "ThaumicRevelations";
    public static final String MOD_NAME = "Thaumic Revelations";
    public static final String MOD_VERSION = "vPO.TA.TO.RANDOM-DEV";

    @Instance
    public static ThaumicRevelations instance;

    @SidedProxy(clientSide = "mortvana.thaumicrevelations.core.network.ClientProxy", serverSide = "mortvana.thaumicrevelations.core.network.CommonProxy", modId = MOD_ID)
    public static CommonProxy proxy;

    public static final Logger logger = LogManager.getLogger("Flux Gear");

    public ModuleLoader moduleLoader = new ModuleLoader(MOD_ID);

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

    public static final String MOD_DEPENDENCIES = "required-after:Thaumcraft; after:MagicBees[2.3.0)";

}
