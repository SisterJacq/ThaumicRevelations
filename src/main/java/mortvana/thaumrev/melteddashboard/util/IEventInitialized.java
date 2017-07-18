package mortvana.thaumrev.melteddashboard.util;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IEventInitialized {
	public void preInit(FMLPreInitializationEvent event);
	public void init(FMLInitializationEvent event);
	public void postInit(FMLPostInitializationEvent event);
}
