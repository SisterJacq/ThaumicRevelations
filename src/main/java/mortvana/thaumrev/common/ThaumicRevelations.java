package mortvana.thaumrev.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;

import mortvana.melteddashboard.util.helpers.LoadedHelper;
import mortvana.melteddashboard.util.libraries.ThaumcraftLibrary;
import mortvana.melteddashboard.util.libraries.ThermalLibrary;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.network.CommonProxy;
import mortvana.thaumrev.world.ThaumRevWorldGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ThaumRevLibrary.MOD_ID, name = ThaumRevLibrary.MOD_NAME, version = ThaumRevLibrary.MOD_VERSION, dependencies = ThaumRevLibrary.MOD_DEPENDENCIES)
public class ThaumicRevelations {

	@Instance
	public static ThaumicRevelations instance;

	@SidedProxy(clientSide = "mortvana.thaumrev.network.ClientProxy", serverSide = "mortvana.thaumrev.network.CommonProxy", modId = ThaumRevLibrary.MOD_ID)
	public static CommonProxy proxy;

	// Move to Melted Dashboard Core once I have enough stuff to warrant creating it as a separate library.
	public static final Logger logger = LogManager.getLogger("Flux Gear");

	public static ThaumRevConfig config;
	public static ThaumRevConfigWorld configWorld;

	/**
	 *	Runs before other things, reading configs, registering handlers, creating blocks, items, and whatever, including
	 *	registering things with GameRegistry.
	 *
	 *  @param event - The FMLPreInitializationEvent passed by Forge Mod Loader.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		config = new ThaumRevConfig(event, "/Mortvana/ThaumicRevelations.cfg");
		configWorld = new ThaumRevConfigWorld(event, "/Mortvana/ThaumicRevelationsWorld.cfg");
		proxy.initRenderers();
		//GuiHandler.init();
		//AspectInfusionHandler.init();
		ThaumRevContent.preInit();

		GameRegistry.registerWorldGenerator(new ThaumRevWorldGenerator(), 1);
	}

	/**
	 *	Register recipes, OreDict stuff, send IMC messages and basically everything else that isn't in postInit.
	 *
	 *	@param event  - The FMLPreInitializationEvent passed by Forge Mod Loader.
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ThaumcraftLibrary.init();
		if (LoadedHelper.isThermalFoundationLoaded) {
			ThermalLibrary.init();
		}
		ThaumRevContent.init();
	}

	/**
	 *	Handle interactions with other mods, initialize research, and register aspects.
	 *
	 *	@param event - The FMLPreInitializationEvent passed by Forge Mod Loader.
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ThaumRevContent.postInit();
	}

	@EventHandler
	public void loadComplete(FMLLoadCompleteEvent event) {
		ThaumRevContent.loadComplete();
	}
}
