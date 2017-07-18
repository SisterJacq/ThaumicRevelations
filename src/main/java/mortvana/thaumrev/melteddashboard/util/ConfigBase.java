package mortvana.thaumrev.melteddashboard.util;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public abstract class ConfigBase {

	public ConfigBase(FMLPreInitializationEvent event, String location) {
		loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + location));
	}

	public abstract void loadConfig(File file);
}
