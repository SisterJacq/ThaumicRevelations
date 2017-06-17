package mortvana.thaumicrevelations.melteddashboard.util.module;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mortvana.thaumicrevelations.melteddashboard.util.ConfigBase;
import mortvana.thaumicrevelations.melteddashboard.util.IConfigInitialized;
import mortvana.thaumicrevelations.melteddashboard.util.IEventInitialized;

import java.util.ArrayList;
import java.util.List;

public class ModuleLoader implements IEventInitialized {

	public List<ModuleBase> modules = new ArrayList<ModuleBase>();

    protected String name;

    public ModuleLoader(String name) {
        this.name = name;
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

	public void addModule(String name, boolean enabled, IConfigInitialized content, ConfigBase config) {
		modules.add(new ModuleBase(name, enabled, content, config));
	}

    public String getName() {
        return name;
    }

    public static String getName(ModuleLoader loader) {
        return loader.getName();
    }
}
