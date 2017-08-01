package mortvana.thaumrev.core.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.core.network.CommonProxy;
import mortvana.thaumrev.melteddashboard.lib.ThaumcraftLibrary;
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

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
	    ThaumRevContent.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ThaumRevContent.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
	    ThaumcraftLibrary.init();
        ThaumRevContent.postInit();
    }
}
