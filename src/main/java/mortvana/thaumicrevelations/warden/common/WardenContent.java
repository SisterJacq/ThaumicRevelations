package mortvana.thaumicrevelations.warden.common;

import net.minecraftforge.common.util.EnumHelper;

import mortvana.thaumicrevelations.core.common.ThaumicRevelations;
import mortvana.thaumicrevelations.core.common.config.ThaumicWardenConfig;
import mortvana.thaumicrevelations.melteddashboard.util.ConfigBase;
import mortvana.thaumicrevelations.melteddashboard.util.IConfigInitialized;

import static mortvana.thaumicrevelations.library.ThaumicLibrary.*;

public class WardenContent implements IConfigInitialized {

	public ThaumicWardenConfig config;

	@Override
	public void setConfig(ConfigBase config) {
		if (config instanceof ThaumicWardenConfig) {
			this.config = (ThaumicWardenConfig) config;
		} else {
			ThaumicRevelations.logger.error("Thaumic Warden config missing! Restart Minecraft! If this problem persists, contact The Flux Tinkers.");
		}
	}

    @Override
    public void preInit() {}

    @Override
    public void init() {
	    loadMaterials();
	    loadBlocks();
	    loadItems();
	    loadArmor();
	    loadTools();
	    loadBaubles();
    }

    @Override
    public void postInit() {}

	public void loadMaterials() {
		materialWardencloth = EnumHelper.addArmorMaterial("WARDENCLOTH", 30, new int[]{1, 3, 2, 1}, 20);
	}

	public void loadBlocks() {}

	public void loadItems() {
		excubituraPetal = generalItem.addOreDictItem(1100, "excubituraPetal", "itemExcubituraPetal");
		excubituraPaste = generalItem.addOreDictItem(1101, "excubituraPaste", "itemExcubituraPaste");
		wardencloth = generalItem.addOreDictItem(1121, "wardencloth", "itemWardencloth");
	}

	public void loadArmor() {}

	public void loadTools() {}

	public void loadBaubles() {}

}
