package mortvana.thaumrev.melteddashboard.util.module;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import mortvana.thaumrev.melteddashboard.util.*;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ModuleLoader implements IEventInitialized {

	public List<ModuleBase> modules = new ArrayList<ModuleBase>();

    protected String name;
	protected Logger logger;

    public ModuleLoader(String name, Logger logger) {
        this.name = name;
	    this.logger = logger;
    }

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		for (ModuleBase module : modules) {
			if (module.isEnabled()) {
				module.preInit(event);
			}
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		for (ModuleBase module : modules) {
			if (module.isEnabled()) {
				module.init(event);
			}
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		for (ModuleBase module : modules) {
			if (module.isEnabled()) {
				module.postInit(event);
			}
		}
	}

	public void addModule(String name, boolean enabled, IInitialized content, ConfigBase config) {
		if (getModuleFromName(name) == null) {
			modules.add(new ModuleBase(name, enabled, content, config));
		} else {
			logger.error("Someone tried to register a module with " + this.name + " with the name " + name + ". A module with this name already exists, so it has been skipped!");
		}
	}

	public ModuleBase getModuleFromName(String name) {
		for (ModuleBase module : modules) {
			if (module.name.equals(name)) {
				return module;
			}
		}
		return null;
	}

    public String getName() {
        return name;
    }

    public static String getName(ModuleLoader loader) {
        return loader.getName();
    }
}
