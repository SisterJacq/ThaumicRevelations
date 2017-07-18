package mortvana.thaumrev.warden.common;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;

import mortvana.thaumrev.melteddashboard.intermod.thaumcraft.research.FluxGearResearchItem;
import mortvana.thaumrev.melteddashboard.lib.ThaumcraftLibrary;
import mortvana.thaumrev.melteddashboard.util.helpers.ItemHelper;
import mortvana.thaumrev.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;

import mortvana.thaumrev.core.common.ThaumicRevelations;
import mortvana.thaumrev.core.common.config.ThaumicWardenConfig;
import mortvana.thaumrev.melteddashboard.util.ConfigBase;
import mortvana.thaumrev.melteddashboard.util.IConfigInitialized;
import mortvana.thaumrev.warden.item.ItemArmorWardencloth;
import mortvana.thaumrev.warden.world.ExcubituraGenerator;
import thaumcraft.api.research.ResearchPage;

import static thaumcraft.api.aspects.Aspect.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;

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
	    loadThaumicRecipes();
	    loadResearch();
    }

	public void loadMaterials() {
		materialWardencloth = EnumHelper.addArmorMaterial("WARDENCLOTH", 30, new int[]{1, 3, 2, 1}, 20);
	}

	public void loadBlocks() {}

	public void loadItems() {
		excubituraPetal = generalItem.addOreDictItem(100, "excubituraPetal", "itemExcubituraPetal");

		excubituraPaste = generalItem.addOreDictItem(110, "excubituraPaste", "itemExcubituraPaste");
		excubituraFabric = generalItem.addOreDictItem(111, "excubituraFabric", "itemExcubituraFabric"); //FIX
		wardencloth = generalItem.addOreDictItem(112, "wardencloth", "itemWardencloth"); //FIX

		excubituraOil = generalItem.addOreDictItem(120, "excubituraOil", "itemExcubituraOil"); //FIX
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

	public void loadThaumicRecipes() {
		recipeExcubituraPaste = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyExcubituraPaste, excubituraPaste, new AspectList().add(EARTH, 5).add(ENTROPY, 3), "itemExcubituraPetal", "itemExcubituraPetal", new ItemStack(Items.bowl));

		recipeExcubituraFabric = ThaumcraftApi.addArcaneCraftingRecipe(keyWardencloth, ItemHelper.cloneStack(excubituraFabric, 12), new AspectList().add(ORDER, 5), "FFF", "FPF", "FFF", 'F', "itemEnchantedFabric", 'P', "itemExcubituraPaste");
		recipeWardencloth = ThaumcraftApi.addCrucibleRecipe(keyWardencloth, wardencloth, excubituraFabric, new AspectList().add(CLOTH, 2).add(ARMOR, 2).add(WARDEN, 2));

		recipeWardenclothSkullcap = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothSkullcap.stack, ThaumcraftHelper.newPrimalAspectList(10), "WEW", "EGE", 'E', "itemEnchantedFabric", 'W', "itemWardencloth", 'G', ThaumcraftLibrary.itemGoggles);
		recipeWardenclothTunic = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothTunic.stack, ThaumcraftHelper.newPrimalAspectList(20), "W W", "WEW", "EWE", 'E', "itemEnchantedFabric", 'W', "itemWardencloth");
		recipeWardenclothPants = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothPants.stack, ThaumcraftHelper.newPrimalAspectList(15), "EWE", "E E", "W W", 'E', "itemEnchantedFabric", 'W', "itemWardencloth");
		recipeWardenclothBoots = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothBoots.stack, ThaumcraftHelper.newPrimalAspectList(10), "E E", "W W", 'E', "itemEnchantedFabric", 'W', "itemWardencloth");
	}

	public void loadResearch() {
		researchWardenry = new FluxGearResearchItem(keyWardenry, RESEARCH_KEY, new AspectList().add(WARDEN, 4).add(MAGIC, 3).add(ELDRITCH, 2).add(ENERGY, 2), -2, 0, 2, new ItemStack(blockThaumicPlant)).setParents(keyThaumRev).setLost()/*.setItemTriggers(wardenJournal1)*/.setRound().setSpecial().registerResearchItem();
		researchWardenry.setPages(new ResearchPage("0"));

		researchExcubituraPaste = new FluxGearResearchItem(keyExcubituraPaste, RESEARCH_KEY, new AspectList().add(PLANT, 4).add(MAGIC, 4).add(WARDEN, 1), -4, -2, 1, excubituraPaste).setParents(keyWardenry).setConcealed().registerResearchItem();
		researchExcubituraPaste.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraPaste));

		researchWardencloth = new FluxGearResearchItem(keyWardencloth, RESEARCH_KEY, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(WARDEN, 1), -6, -3, 1, wardencloth).setParents(keyExcubituraPaste, "ENCHFABRIC").registerResearchItem();
		researchWardencloth.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraFabric), new ResearchPage(recipeWardencloth));

		researchArmorWardencloth = new FluxGearResearchItem(keyArmorWardencloth, RESEARCH_KEY, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 4).add(WARDEN, 4), -8, -4, 1, wardenclothTunic.stack).setParents(keyWardencloth, "GOGGLES").setSecondary().registerResearchItem();
		researchArmorWardencloth.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenclothSkullcap), new ResearchPage(recipeWardenclothTunic), new ResearchPage(recipeWardenclothPants), new ResearchPage(recipeWardenclothBoots));
	}
}
