package mortvana.thaumrev.warden.common;

import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

import mortvana.thaumrev.core.item.ItemArmorInfusableThaumRev;
import mortvana.thaumrev.core.item.ThaumRevMaterialDataSet;
import mortvana.thaumrev.melteddashboard.ColorLibrary;
import mortvana.thaumrev.melteddashboard.intermod.thaumcraft.research.FluxGearResearchItem;
import mortvana.thaumrev.melteddashboard.item.ItemArmorFluxGear;
import mortvana.thaumrev.melteddashboard.lib.ThaumcraftLibrary;
import mortvana.thaumrev.melteddashboard.util.IInitialized;
import mortvana.thaumrev.melteddashboard.util.helpers.ItemHelper;
import mortvana.thaumrev.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;

import mortvana.thaumrev.warden.world.ExcubituraGenerator;
import thaumcraft.api.research.ResearchPage;

import static thaumcraft.api.aspects.Aspect.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class WardenContent implements IInitialized {

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
	    initResearch();
	    setPages();
    }

	public void loadMaterials() {
		ItemArmorFluxGear.addArmorMaterial(matWardencloth, 30, new int[] {1, 3, 2, 1}, 20);
		ItemArmorFluxGear.addArmorMaterial(matWardenicChain, 15, new int[]{2, 5, 4, 1}, 20);

		ItemArmorInfusableThaumRev.materialData.put(matWardencloth, new ThaumRevMaterialDataSet().setUnlocName(".wardencloth.", new String[] {"skullcap", "tunic", "pants", "boots"}).setIcon("wardencloth", new String[] {"Skullcap", "Tunic", "Pants", "Boots"}).setRepair("itemWardencloth").setColor(ColorLibrary.COLOR_TEAL_MAGNEQUAZAR).setTexture("wardencloth").setRarity(EnumRarity.uncommon).setRegName("Wardencloth", new String[] {"Skullcap", "Tunic", "Pants", "Boots"}));
		ItemArmorInfusableThaumRev.materialData.put(matWardenicChain, new ThaumRevMaterialDataSet().setUnlocName(".wardenicChain", new String[] {"helmet", "mail", "greaves", "boots"}).setIcon("wardenicChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}).setRepair("itemChainWardenBronze").setTexture("wardenicChain").setRarity(EnumRarity.uncommon).setRegName("WardenicChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}));
	}

	public void loadBlocks() {}

	public void loadItems() {
		excubituraPetal = generalItem.addOreDictItem(100, "excubituraPetal", "itemExcubituraPetal");

		excubituraPaste = generalItem.addOreDictItem(110, "excubituraPaste", "itemExcubituraPaste");
		excubituraFabric = generalItem.addOreDictItem(111, "excubituraFabric", "itemExcubituraFabric"); //FIX
		wardencloth = generalItem.addOreDictItem(112, "wardencloth", "itemWardencloth"); //FIX

		excubituraOilUnproc = generalItem.addOreDictItem(120, "excubituraOilUnproc", "itemExcubituraOilUnprocessed");
		excubituraOil = generalItem.addOreDictItem(121, "excubituraOil", "itemExcubituraOil"); //FIX
		wardenBronzeChain = generalItem.addOreDictItem(122, "wardenBronzeChain", "itemChainWardenBronze");
		primalBronzeChain = generalItem.addOreDictItem(123, "primalBronzeChain", "itemChainPrimalBronze");
		wardenBronzePlate = generalItem.addOreDictItem(124, "wardenBronzePlate", "itemPlateWardenBronze");

		excubituraOilPure = generalItem.addOreDictItem(130, "excubituraOilPure", "itemExcubituraOilPure");

		wardenicQuartz = generalItem.addOreDictItem(140, "wardenicQuartz", "gemQuartzWardenic");
		excubituraCrystal = generalItem.addOreDictItem(141, "wardenicCrystal", "crystalWardenic");

		excubituraCrystalAwakened = generalItem.addOreDictItem(150, "wardenicCrystalAwakened", "crystalWardenicAwakened");
	}

	public void loadArmor() {
		wardenclothSkullcap = new ItemArmorInfusableThaumRev(matWardencloth, 0, 0);
		wardenclothTunic = new ItemArmorInfusableThaumRev(matWardencloth, 0, 1);
		wardenclothPants = new ItemArmorInfusableThaumRev(matWardencloth, 0, 2);
		wardenclothBoots = new ItemArmorInfusableThaumRev(matWardencloth, 0, 3);

		wardenicChainHelmet = new ItemArmorInfusableThaumRev(matWardenicChain, 1, 0);
		wardenicChainmail = new ItemArmorInfusableThaumRev(matWardenicChain, 1, 1);
		wardenicChainGreaves = new ItemArmorInfusableThaumRev(matWardenicChain, 1, 2);
		wardenicChainBoots = new ItemArmorInfusableThaumRev(matWardenicChain, 1, 3);
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

		recipeWardenclothSkullcap = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothSkullcap.getItemStack(), ThaumcraftHelper.newPrimalAspectList(10), "WEW", "EGE", 'E', "itemEnchantedFabric", 'W', "itemWardencloth", 'G', ThaumcraftLibrary.itemGoggles);
		recipeWardenclothTunic = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothTunic.getItemStack(), ThaumcraftHelper.newPrimalAspectList(20), "W W", "WEW", "EWE", 'E', "itemEnchantedFabric", 'W', "itemWardencloth");
		recipeWardenclothPants = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothPants.getItemStack(), ThaumcraftHelper.newPrimalAspectList(15), "EWE", "E E", "W W", 'E', "itemEnchantedFabric", 'W', "itemWardencloth");
		recipeWardenclothBoots = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothBoots.getItemStack(), ThaumcraftHelper.newPrimalAspectList(10), "E E", "W W", 'E', "itemEnchantedFabric", 'W', "itemWardencloth");

		recipeExcubituraOilUnproc = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOilUnproc, new AspectList().add(EARTH, 1).add(ORDER, 1), "itemBottle", "itemExcubituraPaste", "itemExcubituraPaste", "itemExcubituraPaste", "itemExcubituraPaste");
		recipeExcubituraOil = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOil, new AspectList().add(FIRE, 10).add(WATER, 5).add(EARTH, 5).add(ORDER, 25).add(ENTROPY, 5), "itemExcubituraOilUnprocessed", "quicksilver", "dustSalisMundus", ThaumcraftLibrary.itemAlumentum);

		recipeWardenBronzeChain = ThaumcraftApi.addArcaneCraftingRecipe(keyWardenBronze, ItemHelper.cloneStack(wardenBronzeChain, 6), new AspectList().add(EARTH, 5).add(AIR, 5).add(FIRE, 5).add(ORDER, 10), "CCC", "SOS", "CCC", 'C', "itemChainThaumicBronze", 'S', "dustSalisMundus", 'O', "itemExcubituraOil");
		recipePrimalBronzeChain = ThaumcraftApi.addArcaneCraftingRecipe(keyWardenBronze, ItemHelper.cloneStack(primalBronzeChain, 2), ThaumcraftHelper.newPrimalAspectList(10).add(ORDER, 10), "BCD", "PSP", "DCB", 'B', "nuggetBrass", 'C', "itemChainWardenBronze", 'D', "dustSalisMundus", 'P', ThaumcraftLibrary.itemPrimalCharm, 'S', "shardBalanced");
		recipeWardenBronzePlate = ThaumcraftApi.addArcaneCraftingRecipe(keyWardenBronze, ItemHelper.cloneStack(wardenBronzePlate, 2), new AspectList().add(EARTH, 5).add(AIR, 5).add(FIRE, 5).add(ORDER, 10), "BQC", "DOD", "CTB", 'B', "ingotThaumicBronze", 'C', "itemChainWardenBronze", 'D', "dustSalisMundus", 'Q', "itemQuicksilverDrop", 'O', "itemExcubituraOil", 'T', "nuggetThaumium");


	}

	public void loadResearch() {
		researchWardenry = new FluxGearResearchItem(keyWardenry, new AspectList().add(WARDEN, 4).add(MAGIC, 3).add(ELDRITCH, 2).add(ENERGY, 2), -2, 0, 2, new ItemStack(blockThaumicPlant));
		researchExcubituraPaste = new FluxGearResearchItem(keyExcubituraPaste, new AspectList().add(PLANT, 4).add(MAGIC, 4).add(WARDEN, 1), -4, -2, 1, excubituraPaste);
		researchWardencloth = new FluxGearResearchItem(keyWardencloth, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(WARDEN, 1), -6, -3, 1, wardencloth);
		researchArmorWardencloth = new FluxGearResearchItem(keyArmorWardencloth, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 4).add(WARDEN, 4), -8, -4, 1, wardenclothTunic.getItemStack());
		researchExcubituraOil = new FluxGearResearchItem(keyExcubituraOil, new AspectList().add(MAGIC, 4).add(PLANT, 3).add(WARDEN, 2).add(WATER, 3), -5, 0, 1, excubituraOil);
		researchWardenBronze = new FluxGearResearchItem(keyWardenBronze, new AspectList().add(METAL, 4).add(MAGIC, 4).add(ARMOR, 3).add(WARDEN, 2), -7, -1, 1, wardenBronzeChain);
		//researchArmorWardenBronze = new FluxGearResearchItem(keyArmorWardenBronze, new AspectList().add(METAL, 4).add(MAGIC, 4).add(ARMOR, 4).add(WARDEN, 4), -9, -2, 1, wardenicChainmail);
	}

	public void initResearch() {
		if (researchLevel == 0) { //EASY-MODE
			researchWardenry.setParents(keyThaumRev).setRound().setSpecial().registerResearchItem();
			researchExcubituraPaste.setConcealed().setParents(keyWardenry).setParentsHidden("ENCHFABRIC", "GOGGLES").setSiblings(keyWardencloth, keyArmorWardencloth).registerResearchItem();
			researchWardencloth.setParents(keyExcubituraPaste).setSecondary().registerResearchItem();
			researchArmorWardencloth.setParents(keyWardencloth).setSecondary().registerResearchItem();
			researchExcubituraOil.setConcealed().setParents(keyExcubituraPaste).setParentsHidden("ALUMENTUM", keyBronzeChain).setSiblings(keyWardenBronze, keyArmorWardenBronze).registerResearchItem();
			researchWardenBronze.setParents(keyExcubituraOil).setSecondary().registerResearchItem();
			//researchArmorWardenBronze.setParents(keyWardenBronze).setSecondary().registerResearchItem();
		} else if (researchLevel == 2) { //HARD-MODE
			researchWardenry.setParents(keyThaumRev).setLost()/*.setItemTriggers(wardenJournal1)*/.setRound().setSpecial().registerResearchItem();
			researchExcubituraPaste.setParents(keyWardenry).setConcealed().registerResearchItem();
			researchWardencloth.setParents(keyExcubituraPaste, "ENCHFABRIC").registerResearchItem();
			researchArmorWardencloth.setParents(keyWardencloth, "GOGGLES").setSecondary().registerResearchItem();
			researchExcubituraOil.setConcealed().setParents(keyExcubituraPaste, "ALUMENTUM").registerResearchItem();
			researchWardenBronze.setParents(keyExcubituraOil).setParentsHidden(keyBronzeChain).registerResearchItem();
			//researchArmorWardenBronze.setParents(keyWardenBronze).setParentsHidden(keyArmorBronzeChain).registerResearchItem();
		} else { //NORMAL-MODE
			researchWardenry.setParents(keyThaumRev).setLost()/*.setItemTriggers(wardenJournal1)*/.setRound().setSpecial().registerResearchItem();
			researchExcubituraPaste.setConcealed().setParents(keyWardenry).registerResearchItem();
			researchWardencloth.setParents(keyExcubituraPaste).setParentsHidden("ENCHFABRIC", "GOGGLES").setSiblings(keyArmorWardencloth).registerResearchItem();
			researchArmorWardencloth.setParents(keyWardencloth).setSecondary().registerResearchItem();
			researchExcubituraOil.setConcealed().setParents(keyExcubituraPaste, "ALUMENTUM").registerResearchItem();
			researchWardenBronze.setParents(keyExcubituraOil).setParentsHidden(keyBronzeChain).setSiblings(keyArmorWardenBronze).registerResearchItem();
			//researchArmorWardenBronze.setParents(keyWardenBronze).setSecondary().registerResearchItem();
		}
	}

	public void setPages() {
		researchWardenry.setPages(new ResearchPage("0"));
		researchExcubituraPaste.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraPaste));
		researchWardencloth.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraFabric), new ResearchPage(recipeWardencloth));
		researchArmorWardencloth.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenclothSkullcap), new ResearchPage(recipeWardenclothTunic), new ResearchPage(recipeWardenclothPants), new ResearchPage(recipeWardenclothBoots));
		researchExcubituraOil.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraOilUnproc), new ResearchPage(recipeExcubituraOil));
		researchWardenBronze.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenBronzeChain), new ResearchPage(recipePrimalBronzeChain), new ResearchPage(recipeWardenBronzePlate));
	}
}
