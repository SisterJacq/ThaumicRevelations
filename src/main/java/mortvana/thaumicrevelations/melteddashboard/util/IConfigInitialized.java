package mortvana.thaumicrevelations.melteddashboard.util;

public interface IConfigInitialized {
	public void preInit(ConfigBase config);
	public void init(ConfigBase config);
	public void postInit(ConfigBase config);
}
