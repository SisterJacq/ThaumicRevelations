package mortvana.thaumrev.melteddashboard.util.module;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import mortvana.thaumrev.melteddashboard.util.*;

public class ModuleBase implements IEventInitialized {
	public String name;
	public boolean enabled;
	public IInitialized content;
	public ConfigBase config;

	public ModuleBase(String name, boolean enabled, IInitialized content, ConfigBase config) {
		this.name = name;
		this.enabled = enabled;
		this.content = content;
		this.config = config;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		content.preInit();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		content.init();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		content.postInit();
	}

	public boolean isEnabled() {
		return enabled;
	}
}
