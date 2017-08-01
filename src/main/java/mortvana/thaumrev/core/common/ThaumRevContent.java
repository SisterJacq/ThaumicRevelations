package mortvana.thaumrev.core.common;

import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

import magicbees.api.MagicBeesAPI;
import mortvana.thaumrev.core.block.BlockThaumicPlant;
import mortvana.thaumrev.core.block.ItemBlockThaumicPlant;
import mortvana.thaumrev.core.item.*;
import mortvana.thaumrev.util.item.data.ThaumRevMaterialDataSet;
import mortvana.thaumrev.melteddashboard.ColorLibrary;
import mortvana.thaumrev.melteddashboard.intermod.thaumcraft.research.FluxGearResearchItem;
import mortvana.thaumrev.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.thaumrev.melteddashboard.item.ItemArmorFluxGear;
import mortvana.thaumrev.melteddashboard.lib.ThaumcraftLibrary;
import mortvana.thaumrev.melteddashboard.registry.RegistrationWrapper;
import mortvana.thaumrev.melteddashboard.util.helpers.ItemHelper;
import mortvana.thaumrev.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import mortvana.thaumrev.world.ExcubituraGenerator;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;

import static thaumcraft.api.aspects.Aspect.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;
import static mortvana.thaumrev.melteddashboard.lib.ThaumcraftLibrary.*;

public class ThaumRevContent {

    public static void preInit() {
        thaumicRevelationsTab = new FluxGearCreativeTab("Thaumic Revelations", "thaumicRevelations", wardenAmulet);

	    createBlocks();
	    createItems();
	    setResearchLevel();
    }

    public static void init() {
	    loadBlocks();
	    loadItems();
	    loadMaterials();
	    loadArmor();
	    loadTools();
	    loadBaubles();
	    loadWorldGen();
    }

    public static void postInit() {
	    ResearchCategories.registerCategory(RESEARCH_KEY, new ResourceLocation(RESOURCE_PREFIX, "textures/items/baubles/amuletWarden.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
	    aluminiumArc();
	    addLoot();
	    determineTempus();
	    loadRecipes();
	    loadThaumicRecipes();
	    loadResearch();
	    initResearch();
	    setPages();
    }

	public static void createBlocks() {
		blockThaumicPlant = new BlockThaumicPlant();

		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class, "blockThaumicPlant");
	}

	public static void createItems() {
		generalItem = new ItemThaumRev();
	}

	public static void setResearchLevel() {
		/*int lvl = ThaumRevCoreConfig.researchLevel;
		if (lvl < -1) {
			ThaumicRevelations.logger.error("Someone manually set our difficulty to " + lvl + "! Value should be between -1 and 2, inclusive. SETTING IT TO -1 FOR THIS LAUNCH!");
			lvl = -1;
		} else if (lvl > 2) {
			ThaumicRevelations.logger.error("Someone manually set our difficulty to " + lvl + "! I know challenges are fun, but the value should be between -1 and 2, inclusive. SETTING IT TO 2 FOR THIS LAUNCH!");
			lvl = 2;
		}
		if (lvl == -1) {
			try {
				Object obj = Class.forName("thaumcraft.common.config.Config").getField("researchDifficulty").get(null);
				if (obj instanceof Integer) {
					int temp = (Integer) obj;
					if (temp < 2 && temp > -2) {
						lvl = temp + 1;
					} else {
						ThaumicRevelations.logger.error("Thaumcraft's config data was not a value it should be! RESORTING TO DEFAULT OF 1!");
						lvl = 1;
					}
				} else {
					ThaumicRevelations.logger.error("Thaumcraft's config data was not a type it should be! RESORTING TO DEFAULT OF 1!");
					lvl = 1;
				}
			} catch (Exception ex) {
				ThaumicRevelations.logger.error("Thaumic Revelations couldn't find Thaumcraft's config to set our research information through hacky reflection! RESORTING TO DEFAULT OF 1!");
				lvl = 1;
			}
		}*/
		researchLevel = 2;//lvl;
	}

	public static void loadBlocks() {}

	public static void loadItems() {
		ingotCopper = generalItem.addOreDictItem(0, "ingotCopper");
		ingotZinc = generalItem.addOreDictItem(1, "ingotZinc");
		ingotTin = generalItem.addOreDictItem(2, "ingotTin");

		nuggetCopper = generalItem.addOreDictItem(10, "nuggetCopper");
		nuggetZinc = generalItem.addOreDictItem(11, "nuggetZinc");
		nuggetTin = generalItem.addOreDictItem(12, "nuggetTin");

		dustCopper = generalItem.addOreDictItem(20, "dustCopper");
		dustZinc = generalItem.addOreDictItem(21, "dustZinc");
		dustTin = generalItem.addOreDictItem(22, "dustTin");

		ingotBrass = generalItem.addOreDictItem(30, "ingotBrass");
		ingotBronze = generalItem.addOreDictItem(31, "ingotBronze");
		ingotThaumicBronze = generalItem.addOreDictItem(32, "ingotThaumicBronze");
		ingotSteel = generalItem.addOreDictItem(33, "ingotSteel");
		ingotVoidbrass = generalItem.addOreDictItem(34, "ingotVoidbrass");
		ingotVoidsteel = generalItem.addOreDictItem(35, "ingotVoidsteel");

		nuggetBrass = generalItem.addOreDictItem(40, "nuggetBrass");
		nuggetBronze = generalItem.addOreDictItem(41, "nuggetBronze");
		nuggetThaumicBronze = generalItem.addOreDictItem(42, "nuggetThaumicBronze");
		nuggetSteel = generalItem.addOreDictItem(43, "nuggetSteel");
		nuggetVoidbrass = generalItem.addOreDictItem(44, "nuggetVoidbrass");
		nuggetVoidsteel = generalItem.addOreDictItem(45, "nuggetVoidsteel");

		dustBrass = generalItem.addOreDictItem(50, "dustBrass");
		dustBronze = generalItem.addOreDictItem(51, "dustBronze");
		dustThaumicBronze = generalItem.addOreDictItem(52, "dustThaumicBronze");
		dustSteel = generalItem.addOreDictItem(53, "dustSteel");
		dustVoidbrass = generalItem.addOreDictItem(54, "dustVoidbrass");
		dustVoidsteel = generalItem.addOreDictItem(55, "dustVoidsteel");

		rawThaumicBronze = generalItem.addOreDictItem(62, "ingotThaumicBronzeRaw");

		coatedThaumicBronze = generalItem.addOreDictItem(72, "ingotThaumicBronzeCoated");

		ceramicSlag = generalItem.addOreDictItem(80, "itemSlagCeramic");
		thaumicSlag = generalItem.addOreDictItem(81, "itemSlagThaumic");

		thaumicBronzeChain = generalItem.addOreDictItem(90, "thaumicBronzeChain", "itemChainThaumicBronze");

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

		firedThaumicBronze = generalItem.addOreDictItem(1102, "ingotThaumicBronzeFired");
	}

	public static void loadMaterials() {
		ItemArmorFluxGear.addArmorMaterial(matWardencloth, 30, new int[] {1, 3, 2, 1}, 20);
		ItemArmorFluxGear.addArmorMaterial(matWardenicChain, 15, new int[]{2, 5, 4, 1}, 20);
		ItemArmorFluxGear.addArmorMaterial(matPrimal, 25, new int[] { 1, 3, 2, 1 }, 25);

		ItemArmorInfusableThaumRev.materialData.put(matWardencloth, new ThaumRevMaterialDataSet().setUnlocName(".wardencloth.", new String[] {"skullcap", "tunic", "pants", "boots"}).setIcon("wardencloth", new String[] {"Skullcap", "Tunic", "Pants", "Boots"}).setRepair("itemWardencloth").setColor(ColorLibrary.COLOR_TEAL_MAGNEQUAZAR).setTexture("wardencloth").setRarity(EnumRarity.uncommon).setRegName("Wardencloth", new String[] {"Skullcap", "Tunic", "Pants", "Boots"}));
		ItemArmorInfusableThaumRev.materialData.put(matWardenicChain, new ThaumRevMaterialDataSet().setUnlocName(".wardenicChain", new String[] {"helmet", "mail", "greaves", "boots"}).setIcon("wardenicChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}).setRepair("itemChainWardenBronze").setTexture("wardenicChain").setRarity(EnumRarity.uncommon).setRegName("WardenicChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}));
		ItemArmorInfusableThaumRev.materialData.put(matPrimal, new ThaumRevMaterialDataSet().setUnlocName(".primal.", new String[] {"goggles", "robes", "pants", "boots"}).setIcon("primal", new String[] {"Goggles", "Robes", "Pants", "Boots"}).setRepair("ingotGold", "itemEnchantedFabric", "itemEnchantedFabric", "itemEnchantedFabric").setColor(0x6A3880).setTexture("primal").setRarity(EnumRarity.rare, EnumRarity.uncommon, EnumRarity.uncommon, EnumRarity.uncommon).setRegName("Primal", new String[] {"Goggles", "Robes", "Pants", "Boots"}).setNonColorized(0));
	}

	public static void loadArmor() {
		primalGoggles = new ItemArmorInfusableThaumRev(matPrimal, 4, 0).setDiscount(5).setGoggles();
		primalRobes = new ItemArmorInfusableThaumRev(matPrimal, 1, 1).setDiscount(2);
		primalPants = new ItemArmorInfusableThaumRev(matPrimal, 2, 2).setDiscount(2);
		primalBoots = new ItemArmorInfusableThaumRev(matPrimal, 1, 3).setDiscount(1);

		wardenclothSkullcap = new ItemArmorInfusableThaumRev(matWardencloth, 0, 0).setGoggles();
		wardenclothTunic = new ItemArmorInfusableThaumRev(matWardencloth, 0, 1);
		wardenclothPants = new ItemArmorInfusableThaumRev(matWardencloth, 0, 2);
		wardenclothBoots = new ItemArmorInfusableThaumRev(matWardencloth, 0, 3);

		wardenicChainHelmet = new ItemArmorInfusableThaumRev(matWardenicChain, 1, 0).setGoggles();
		wardenicChainmail = new ItemArmorInfusableThaumRev(matWardenicChain, 1, 1);
		wardenicChainGreaves = new ItemArmorInfusableThaumRev(matWardenicChain, 1, 2);
		wardenicChainBoots = new ItemArmorInfusableThaumRev(matWardenicChain, 1, 3);
	}

	public static void loadTools() {}

	public static void loadBaubles() {}

	public static void loadWorldGen() {
		GameRegistry.registerWorldGenerator(new ExcubituraGenerator(), 1);
	}

	public static void aluminiumArc() {
		RegistrationWrapper.registerOreDict(new ItemStack(Items.clay_ball), "itemClay");
		RegistrationWrapper.registerOreDict(new ItemStack(Items.glass_bottle), "itemBottle");
		RegistrationWrapper.registerOreDict(dustSalisMundus, "dustSalisMundus");
		RegistrationWrapper.registerOreDict(itemEnchantedFabric, "itemEnchantedFabric");
		RegistrationWrapper.registerOreDict(itemQuicksilverDrop, "itemQuicksilverDrop");
		RegistrationWrapper.registerOreDict(shardBalanced, "shardBalanced");
	}

	public static void addLoot() {
		generalItem.addLoot(1102, ingotThaumicBronze, thaumicSlag);
	}

	public static void loadRecipes() {
		RegistrationWrapper.addSmelting(coatedThaumicBronze, firedThaumicBronze, 2.0F);
	}

	public static void loadThaumicRecipes() {


		recipeThaumicBronzeRaw = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyThaumicBronze, rawThaumicBronze, new AspectList().add(ORDER, 5).add(EARTH, 5).add(FIRE, 5), "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetThaumium", "nuggetThaumium", "nuggetBrass");
		recipeThaumicBronzeCoated = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyThaumicBronze, coatedThaumicBronze, new AspectList().add(EARTH, 5).add(WATER, 5), "ingotThaumicBronzeRaw", "itemQuicksilverDrop", "dustSalisMundus", "itemClay");

		recipeThaumicBronzeChain = ThaumcraftApi.addArcaneCraftingRecipe(keyBronzeChain, ItemHelper.cloneStack(thaumicBronzeChain, 12), new AspectList().add(ORDER, 10).add(FIRE, 5), " X ", "X X", 'X', "ingotThaumicBronze");

/*		recipeBronzeChainHelmet = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainHelmet.stack, new AspectList().add(ORDER, 10).add(EARTH, 5).add(FIRE, 5), "XXX", "X X", 'X', "itemChainThaumicBronze");
		recipeBronzeMail = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeMail.stack, new AspectList().add(ORDER, 25).add(EARTH, 12).add(FIRE, 12), "X X", "XXX", "XXX", 'X', "itemChainThaumicBronze");
		recipeBronzeChainGreaves = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainGreaves.stack, new AspectList().add(ORDER, 20).add(EARTH, 10).add(FIRE, 10), "XXX", "X X", "X X", 'X', "itemChainThaumicBronze");
		recipeBronzeChainBoots = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainBoots.stack, new AspectList().add(ORDER, 5).add(EARTH, 3).add(FIRE, 3), "X X", "X X", 'X', "itemChainThaumicBronze");
*/
		recipePrimalGoggles = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalGoggles.getItemStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackGoggles, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, stackThaumometer });
		recipePrimalRobes = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalRobes.getItemStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackChestRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, ingotThaumium });
		recipePrimalPants = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalPants.getItemStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackLegsRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, itemNitor });
		recipePrimalBoots = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalBoots.getItemStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackBootsRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, planksGreatwood });

		recipeExcubituraPaste = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyExcubituraPaste, excubituraPaste, new AspectList().add(EARTH, 5).add(ENTROPY, 3), "itemExcubituraPetal", "itemExcubituraPetal", new ItemStack(Items.bowl));

		recipeExcubituraFabric = ThaumcraftApi.addArcaneCraftingRecipe(keyWardencloth, ItemHelper.cloneStack(excubituraFabric, 12), new AspectList().add(ORDER, 5), "FFF", "FPF", "FFF", 'F', "itemEnchantedFabric", 'P', "itemExcubituraPaste");
		recipeWardencloth = ThaumcraftApi.addCrucibleRecipe(keyWardencloth, wardencloth, excubituraFabric, new AspectList().add(CLOTH, 2).add(ARMOR, 2).add(WARDEN, 2));

		recipeWardenclothSkullcap = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothSkullcap.getItemStack(), ThaumcraftHelper.newPrimalAspectList(10), "WEW", "EGE", 'E', "itemEnchantedFabric", 'W', "itemWardencloth", 'G', ThaumcraftLibrary.itemGoggles);
		recipeWardenclothTunic = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothTunic.getItemStack(), ThaumcraftHelper.newPrimalAspectList(20), "W W", "WEW", "EWE", 'E', "itemEnchantedFabric", 'W', "itemWardencloth");
		recipeWardenclothPants = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothPants.getItemStack(), ThaumcraftHelper.newPrimalAspectList(15), "EWE", "E E", "W W", 'E', "itemEnchantedFabric", 'W', "itemWardencloth");
		recipeWardenclothBoots = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothBoots.getItemStack(), ThaumcraftHelper.newPrimalAspectList(10), "E E", "W W", 'E', "itemEnchantedFabric", 'W', "itemWardencloth");

		recipeExcubituraOilUnproc = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOilUnproc, new AspectList().add(EARTH, 1).add(ORDER, 1), "itemBottle", "itemExcubituraPaste", "itemExcubituraPaste", "itemExcubituraPaste", "itemExcubituraPaste");
		recipeExcubituraOil = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOil, new AspectList().add(FIRE, 10).add(WATER, 5).add(EARTH, 5).add(ORDER, 25).add(ENTROPY, 5), "itemExcubituraOilUnprocessed", "quicksilver", "dustSalisMundus", ThaumcraftLibrary.itemAlumentum);

		recipeWardenBronzeChain = ThaumcraftApi.addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(wardenBronzeChain, 6), new AspectList().add(EARTH, 5).add(AIR, 5).add(FIRE, 5).add(ORDER, 10), "CCC", "SOS", "CCC", 'C', "itemChainThaumicBronze", 'S', "dustSalisMundus", 'O', "itemExcubituraOil");
		recipePrimalBronzeChain = ThaumcraftApi.addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(primalBronzeChain, 2), ThaumcraftHelper.newPrimalAspectList(10).add(ORDER, 10), "BCD", "PSP", "DCB", 'B', "nuggetBrass", 'C', "itemChainWardenBronze", 'D', "dustSalisMundus", 'P', ThaumcraftLibrary.itemPrimalCharm, 'S', "shardBalanced");
		recipeWardenBronzePlate = ThaumcraftApi.addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(wardenBronzePlate, 2), new AspectList().add(EARTH, 5).add(AIR, 5).add(FIRE, 5).add(ORDER, 10), "BQC", "DOD", "CTB", 'B', "ingotThaumicBronze", 'C', "itemChainWardenBronze", 'D', "dustSalisMundus", 'Q', "itemQuicksilverDrop", 'O', "itemExcubituraOil", 'T', "nuggetThaumium");

		recipeWardenicChainHelmet = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainHelmet.getItemStack(), ThaumcraftHelper.newPrimalAspectList(20), "PWP", "WGW", 'W', "itemChainWardenBronze", 'P', "itemChainPrimalBronze", 'G', ThaumcraftLibrary.itemGoggles);
		recipeWardenicChainmail = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainmail.getItemStack(), ThaumcraftHelper.newPrimalAspectList(42), "P P", "WBW", "WWW", 'W', "itemChainWardenBronze", 'P', "itemChainPrimalBronze", 'B', "itemPlateWardenBronze");
		recipeWardenicChainGreaves = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainGreaves.getItemStack(), ThaumcraftHelper.newPrimalAspectList(35), "WBW", "W W", "P P", 'W', "itemChainWardenBronze", 'P', "itemChainPrimalBronze", 'B', "itemPlateWardenBronze");
		recipeWardenicChainBoots = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainBoots.getItemStack(), ThaumcraftHelper.newPrimalAspectList(13), "P P", "W W", 'W', "itemChainWardenBronze", 'P', "itemChainPrimalBronze");
	}

	public static void loadResearch() {
		researchThaumRev = new FluxGearResearchItem(keyThaumRev, RESEARCH_KEY, new AspectList(), 0, 0, 0, new ItemStack(Items.potato));
		researchThaumicBronze = new FluxGearResearchItem(keyThaumicBronze, RESEARCH_KEY, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ORDER, 1), 0, 3, 1, ingotThaumicBronze);
		researchBronzeChain = new FluxGearResearchItem(keyBronzeChain, RESEARCH_KEY, new AspectList().add(METAL, 4).add(MAGIC, 2).add(CRAFT, 3), -2, 4, 1, thaumicBronzeChain);
		researchPrimalRobes = new FluxGearResearchItem(keyRobesPrimal, RESEARCH_KEY, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(ENERGY, 2).add(SENSES, 2).add(ThaumcraftHelper.newPrimalAspectList(1)), 1, 5, 3, primalRobes.getItemStack());

		researchWardenry = new FluxGearResearchItem(keyWardenry, new AspectList().add(WARDEN, 4).add(MAGIC, 3).add(ELDRITCH, 2).add(ENERGY, 2), -2, 0, 2, new ItemStack(blockThaumicPlant));
		researchExcubituraPaste = new FluxGearResearchItem(keyExcubituraPaste, new AspectList().add(PLANT, 4).add(MAGIC, 4).add(WARDEN, 1), -4, -2, 1, excubituraPaste);
		researchWardencloth = new FluxGearResearchItem(keyWardencloth, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(WARDEN, 1), -6, -3, 1, wardencloth);
		researchArmorWardencloth = new FluxGearResearchItem(keyArmorWardencloth, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 4).add(WARDEN, 4), -8, -4, 1, wardenclothTunic.getItemStack());
		researchExcubituraOil = new FluxGearResearchItem(keyExcubituraOil, new AspectList().add(MAGIC, 4).add(PLANT, 3).add(WARDEN, 2).add(WATER, 3), -5, 0, 1, excubituraOil);
		researchWardenChain = new FluxGearResearchItem(keyWardenChain, new AspectList().add(METAL, 4).add(MAGIC, 4).add(ARMOR, 3).add(WARDEN, 2), -7, -1, 1, wardenBronzeChain);
		researchArmorWardenChain = new FluxGearResearchItem(keyArmorWardenChain, new AspectList().add(METAL, 4).add(MAGIC, 4).add(ARMOR, 4).add(WARDEN, 4), -9, -2, 1, wardenicChainmail.getItemStack());
	}

	public static void initResearch() {
		researchThaumRev.setRound().setSpecial().setAutoUnlock().registerResearchItem();
		researchPrimalRobes.setParents(keyThaumRev, "THAUMIUM", "ENCHFABRIC", "GOGGLES", "NITOR", "INFUSION", "INFUSIONENCHANTMENT").registerResearchItem();


		if (researchLevel == 0) { //EASY-MODE
			researchThaumicBronze.setParents(keyThaumRev, "THAUMIUM").setSiblings(keyBronzeChain).registerResearchItem();
			researchBronzeChain.setParents(keyThaumicBronze).setSecondary().registerResearchItem();
			researchWardenry.setParents(keyThaumRev).setRound().setSpecial().registerResearchItem();
			researchExcubituraPaste.setConcealed().setParents(keyWardenry).setParentsHidden("ENCHFABRIC", "GOGGLES").setSiblings(keyWardencloth, keyArmorWardencloth).registerResearchItem();
			researchWardencloth.setParents(keyExcubituraPaste).setSecondary().registerResearchItem();
			researchArmorWardencloth.setParents(keyWardencloth).setSecondary().registerResearchItem();
			researchExcubituraOil.setConcealed().setParents(keyExcubituraPaste).setParentsHidden("ALUMENTUM", keyBronzeChain).setSiblings(keyWardenChain, keyArmorWardenChain).registerResearchItem();
			researchWardenChain.setParents(keyExcubituraOil).setSecondary().registerResearchItem();
			researchArmorWardenChain.setParents(keyWardenChain).setSecondary().registerResearchItem();
		} else if (researchLevel == 2) { //HARD-MODE
			researchThaumicBronze.setParents(keyThaumRev, "THAUMIUM").registerResearchItem();
			researchBronzeChain.setParents(keyThaumicBronze).setSecondary().registerResearchItem();
			researchWardenry.setParents(keyThaumRev).setLost()/*.setItemTriggers(wardenJournal1)*/.setRound().setSpecial().registerResearchItem();
			researchExcubituraPaste.setParents(keyWardenry).setConcealed().registerResearchItem();
			researchWardencloth.setParents(keyExcubituraPaste, "ENCHFABRIC").registerResearchItem();
			researchArmorWardencloth.setParents(keyWardencloth, "GOGGLES").setSecondary().registerResearchItem();
			researchExcubituraOil.setConcealed().setParents(keyExcubituraPaste, "ALUMENTUM").registerResearchItem();
			researchWardenChain.setParents(keyExcubituraOil).setParentsHidden(keyBronzeChain).registerResearchItem();
			researchArmorWardenChain.setParents(keyWardenChain).setParentsHidden(keyArmorBronzeChain).registerResearchItem();
		} else { //NORMAL-MODE
			researchThaumicBronze.setParents(keyThaumRev, "THAUMIUM").registerResearchItem();
			researchBronzeChain.setParents(keyThaumicBronze).setSecondary().registerResearchItem();
			researchWardenry.setParents(keyThaumRev).setLost()/*.setItemTriggers(wardenJournal1)*/.setRound().setSpecial().registerResearchItem();
			researchExcubituraPaste.setConcealed().setParents(keyWardenry).registerResearchItem();
			researchWardencloth.setParents(keyExcubituraPaste).setParentsHidden("ENCHFABRIC", "GOGGLES").setSiblings(keyArmorWardencloth).registerResearchItem();
			researchArmorWardencloth.setParents(keyWardencloth).setSecondary().registerResearchItem();
			researchExcubituraOil.setConcealed().setParents(keyExcubituraPaste, "ALUMENTUM").registerResearchItem();
			researchWardenChain.setParents(keyExcubituraOil).setParentsHidden(keyBronzeChain).setSiblings(keyArmorWardenChain).registerResearchItem();
			researchArmorWardenChain.setParents(keyWardenChain).setSecondary().registerResearchItem();
		}
	}

	public static void temp() {
		ThaumicRevelations.logger.fatal("Null robes! NULL ROBES!!!!");
	}

	public static void setPages() {
		if (recipePrimalGoggles == null ||recipePrimalRobes == null || recipePrimalPants == null || recipePrimalBoots == null) {
			temp();
		}

		researchThaumRev.setPages(new ResearchPage("0"));
		researchThaumicBronze.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeRaw), new ResearchPage(recipeThaumicBronzeCoated), new ResearchPage(coatedThaumicBronze), new ResearchPage("1"));
		researchBronzeChain.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeChain));
		researchPrimalRobes.setPages(new ResearchPage("0"), new ResearchPage(recipePrimalGoggles), new ResearchPage(recipePrimalRobes), new ResearchPage(recipePrimalPants), new ResearchPage(recipePrimalBoots));

		researchWardenry.setPages(new ResearchPage("0"));
		researchExcubituraPaste.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraPaste));
		researchWardencloth.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraFabric), new ResearchPage(recipeWardencloth));
		researchArmorWardencloth.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenclothSkullcap), new ResearchPage(recipeWardenclothTunic), new ResearchPage(recipeWardenclothPants), new ResearchPage(recipeWardenclothBoots));
		researchExcubituraOil.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraOilUnproc), new ResearchPage(recipeExcubituraOil));
		researchWardenChain.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenBronzeChain), new ResearchPage(recipePrimalBronzeChain), new ResearchPage(recipeWardenBronzePlate));
		researchArmorWardenChain.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicChainHelmet), new ResearchPage(recipeWardenicChainmail), new ResearchPage(recipeWardenicChainGreaves), new ResearchPage(recipeWardenicChainBoots));
	}

    public static void determineTempus() {
        // Thanks for the API hook, Myst!
        Object protoTempus = MagicBeesAPI.thaumcraftAspectTempus;
        if (protoTempus != null && protoTempus instanceof Aspect) {
            tempus = (Aspect) protoTempus;
        } else {
            tempus = new Aspect("tempus", 0xB68CFF, new Aspect[] { Aspect.VOID, Aspect.ORDER }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/tempus.png"), 1);
	        if (Loader.isModLoaded("Forestry")) {
		        ThaumicRevelations.logger.info("What's the matter? What's with the lack of Magic Bees? You not like bees? DO YOU NOT LIKE THE BEES!?!? HUH!?!? YOU SOME APIPHOBIC LOSER!?!?");
	        }
	        timeyWimey();
        }
    }

	public static void timeyWimey() {
		//FRESH COPY-PASTA FROM MAGIC BEES FOR THOSE CRAZY PEOPLE WHO DON'T USE MAGIC BEES!!!!
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.clock), new AspectList(new ItemStack(Items.clock)).add(tempus, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.repeater), new AspectList(new ItemStack(Items.repeater)).add(tempus, 2));
	}

    public static Aspect tempus;
}
