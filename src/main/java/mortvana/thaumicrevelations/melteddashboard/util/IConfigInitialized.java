package mortvana.thaumicrevelations.melteddashboard.util;

import mortvana.thaumicrevelations.melteddashboard.util.module.ModuleBase;

public interface IConfigInitialized {
	public void setConfig(ConfigBase config);
	public void preInit();
	public void init();
	public void postInit();
}
