package mortvana.thaumicrevelations.warden.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;

import mortvana.thaumicrevelations.core.common.ThaumicRevelations;
import mortvana.thaumicrevelations.core.common.config.ThaumicWardenConfig;
import mortvana.thaumicrevelations.melteddashboard.util.ConfigBase;
import mortvana.thaumicrevelations.melteddashboard.util.IConfigInitialized;
import mortvana.thaumicrevelations.warden.item.ItemArmorWardencloth;
import mortvana.thaumicrevelations.warden.world.ExcubituraGenerator;

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
	    loadWorldGen();
    }

    @Override
    public void postInit() {
	    loadRecipes();
	    loadResearch();
    }

	public void loadMaterials() {
		materialWardencloth = EnumHelper.addArmorMaterial("WARDENCLOTH", 30, new int[]{1, 3, 2, 1}, 20);
	}

	public void loadBlocks() {}

	public void loadItems() {
		excubituraPetal = generalItem.addOreDictItem(100, "excubituraPetal", "itemExcubituraPetal");

		excubituraPaste = generalItem.addOreDictItem(110, "excubituraPaste", "itemExcubituraPaste"); //FIX
		excubituraFabric = generalItem.addOreDictItem(111, "excubituraFabric", "itemExcubituraFabric"); //FIX
		wardencloth = generalItem.addOreDictItem(112, "wardencloth", "itemWardencloth"); //FIX

		excubituraOil = generalItem.addOreDictItem(120, "excubituraOil", "itemExcubituraOil");
		thaumicBronzeChain = generalItem.addOreDictItem(121, "thaumicBronzeChain", "itemChainThaumicBronze");
		wardenBronzeChain = generalItem.addOreDictItem(122, "wardenBronzeChain", "itemChainWardenBronze");

		excubituraOilPure = generalItem.addOreDictItem(130, "excubituraOilPure", "itemExcubituraOilPure");

		wardenicQuartz = generalItem.addOreDictItem(140, "wardenicQuartz", "gemQuartzWardenic");
		excubituraCrystal = generalItem.addOreDictItem(141, "wardenicCrystal", "crystalWardenic");

		excubituraCrystalAwakened = generalItem.addOreDictItem(150, "wardenicCrystalAwakened", "crystalWardenicAwakened");
	}

	public void loadArmor() {
		wardenclothSkullcap = new ItemArmorWardencloth(0, "wardencloth.skullcap", "wardenclothSkullcap").register("skullcap", "WardenclothSkullcap");
		wardenclothTunic = new ItemArmorWardencloth(1, "wardencloth.tunic", "wardenclothTunic").register("tunic", "WardenclothTunic");
		wardenclothPants = new ItemArmorWardencloth(2, "wardencloth.pants", "wardenclothPants").register("pants", "WardenclothPants");
		wardenclothBoots = new ItemArmorWardencloth(3, "wardencloth.boots", "wardenclothBoots").register("boots", "WardenclothBoots");
	}

	public void loadTools() {}

	public void loadBaubles() {}

	public void loadWorldGen() {
		GameRegistry.registerWorldGenerator(new ExcubituraGenerator(), 1);
	}

	public void loadRecipes() {}

	public void loadResearch() {}
}
