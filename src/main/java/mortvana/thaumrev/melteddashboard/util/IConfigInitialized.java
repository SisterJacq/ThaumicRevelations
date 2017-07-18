package mortvana.thaumrev.melteddashboard.util;

public interface IConfigInitialized {
	public void setConfig(ConfigBase config);
	public void preInit();
	public void init();
	public void postInit();
}
