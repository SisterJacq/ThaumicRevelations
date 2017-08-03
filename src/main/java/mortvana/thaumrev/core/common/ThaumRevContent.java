package mortvana.thaumrev.core.common;

import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

import baubles.api.BaubleType;
import magicbees.api.MagicBeesAPI;
import mortvana.thaumrev.core.block.BlockThaumicPlant;
import mortvana.thaumrev.core.block.ItemBlockThaumicPlant;
import mortvana.thaumrev.core.item.*;
import mortvana.thaumrev.melteddashboard.intermod.baubles.util.DefaultBaubleData;
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
import static mortvana.thaumrev.util.StringLibrary.*;

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
	    registerResearch();
	    setPages();
    }

	public static void createBlocks() {
		blockThaumicPlant = new BlockThaumicPlant();

		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class, "blockThaumicPlant");
	}

	public static void createItems() {
		generalItem = new ItemThaumRev();
		thaumicBauble = new ItemThaumicBauble();
	}

	public static void setResearchLevel() {
		/*int lvl = ThaumRevConfig.researchLevel;
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
		ingotCopper = generalItem.addOreDictItem(0, ICU);
		ingotZinc = generalItem.addOreDictItem(1, IZN);
		ingotTin = generalItem.addOreDictItem(2, ISN);

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
		nuggetBronze = generalItem.addOreDictItem(41, NBRONZE);
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

		rawBrass = generalItem.addOreDictItem(60, "ingotBrassRaw");
		rawBronze = generalItem.addOreDictItem(61, "ingotBronzeRaw");
		rawThaumicBronze = generalItem.addOreDictItem(62, "ingotThaumicBronzeRaw");

		coatedThaumicBronze = generalItem.addOreDictItem(72, "ingotThaumicBronzeCoated");

		ceramicSlag = generalItem.addOreDictItem(80, "itemSlagCeramic");
		thaumicSlag = generalItem.addOreDictItem(81, "itemSlagThaumic");
		arcaneSingularity = generalItem.addOreDictItem(82, "itemArcaneSingularity");

		thaumicBronzeChain = generalItem.addOreDictItem(90, "thaumicBronzeChain", CHAINTBRONZE);

		excubituraPetal = generalItem.addOreDictItem(100, "excubituraPetal", "itemExcubituraPetal");

		excubituraPaste = generalItem.addOreDictItem(110, "excubituraPaste", PASTE);
		excubituraFabric = generalItem.addOreDictItem(111, "excubituraFabric", "itemExcubituraFabric"); //FIX
		wardencloth = generalItem.addOreDictItem(112, "wardencloth", WARDENCLOTH); //FIX

		excubituraOilUnproc = generalItem.addOreDictItem(120, "excubituraOilUnproc", "itemExcubituraOilUnprocessed");
		excubituraOil = generalItem.addOreDictItem(121, "excubituraOil", "itemExcubituraOil"); //FIX
		wardenicBronzeChain = generalItem.addOreDictItem(122, "wardenicBronzeChain", CHAINWBRONZE);
		primalBronzeChain = generalItem.addOreDictItem(123, "primalBronzeChain", CHAINPBRONZE);
		wardenicBronzePlate = generalItem.addOreDictItem(124, "wardenicBronzePlate", "itemPlateWardenicBronze");

		excubituraOilPure = generalItem.addOreDictItem(130, "excubituraOilPure", "itemExcubituraOilPure");
		wardenicSteelChain = generalItem.addOreDictItem(131, "wardenicSteelChain", "itemChainWardenicSteel");
		wardenicSteelPlate = generalItem.addOreDictItem(132, "wardenicSteelPlate", "itemPlateWardenicSteel");
		detailedSteelPlate = generalItem.addOreDictItem(133, "detailedSteelPlate", "itemPlateWardenicSteelDetailed");
		runicSteelPlate = generalItem.addOreDictItem(134, "runicSteelPlate", "itemPlateWardenicSteelRunic");

		wardenicQuartz = generalItem.addOreDictItem(140, "wardenQuartz", "gemQuartzWardenic");
		excubituraCrystal = generalItem.addOreDictItem(141, "wardenicCrystal", "crystalWardenic");

		excubituraCrystalAwakened = generalItem.addOreDictItem(150, "wardenicCrystalAwakened", "crystalWardenicAwakened");

		ingotWardenicSteel = generalItem.addOreDictItem(500, "ingotWardenicSteel");

		firedThaumicBronze = generalItem.addOreDictItem(1102, "ingotThaumicBronzeFired");
	}

	public static void loadMaterials() {
		ItemArmorFluxGear.addArmorMaterial(matPrimal, 25, new int[] { 1, 3, 2, 1 }, 25);
		ItemArmorFluxGear.addArmorMaterial(matBronzeChain, 20, new int[] {2, 5, 4, 2}, 30);
		ItemArmorFluxGear.addArmorMaterial(matWardencloth, 30, new int[] {1, 3, 2, 1}, 20);
		ItemArmorFluxGear.addArmorMaterial(matWardenicChain, 15, new int[]{2, 5, 4, 1}, 20);
		ItemArmorFluxGear.addArmorMaterial(matWardenicSteel, 30, new int[]{3, 7, 5, 2}, 20);

		ItemArmorInfusableThaumRev.materialData.put(matPrimal, new ThaumRevMaterialDataSet().setUnlocName(".primal.", new String[] {"goggles", "robes", "pants", "boots"}).setIcon("primal", new String[] {"Goggles", "Robes", "Pants", "Boots"}).setRepair("ingotGold", "itemEnchantedFabric", "itemEnchantedFabric", "itemEnchantedFabric").setColor(0x6A3880).setTexture("primal").setRarity(EnumRarity.rare, EnumRarity.uncommon, EnumRarity.uncommon, EnumRarity.uncommon).setRegName("Primal", new String[] {"Goggles", "Robes", "Pants", "Boots"}).setNonColorized(0));
		ItemArmorInfusableThaumRev.materialData.put(matBronzeChain, new ThaumRevMaterialDataSet().setUnlocName(".bronzeChain.", new String[] {"helmet", "mail", "greaves", "boots"}).setIcon("bronzeChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}).setRepair("itemThaumicBronzeChain").setTexture("bronzeChain").setRarity(EnumRarity.uncommon).setRegName("BronzeChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}));
		ItemArmorInfusableThaumRev.materialData.put(matWardencloth, new ThaumRevMaterialDataSet().setUnlocName(".wardencloth.", new String[] {"skullcap", "tunic", "pants", "boots"}).setIcon("wardencloth", new String[] {"Skullcap", "Tunic", "Pants", "Boots"}).setRepair("itemWardencloth").setColor(ColorLibrary.COLOR_TEAL_MAGNEQUAZAR).setTexture("wardencloth").setRarity(EnumRarity.uncommon).setRegName("Wardencloth", new String[] {"Skullcap", "Tunic", "Pants", "Boots"}));
		ItemArmorInfusableThaumRev.materialData.put(matWardenicChain, new ThaumRevMaterialDataSet().setUnlocName(".wardenicChain.", new String[] {"helmet", "mail", "greaves", "boots"}).setIcon("wardenicChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}).setRepair("itemChainWardenBronze").setTexture("wardenicChain").setRarity(EnumRarity.uncommon).setRegName("WardenicChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}));
		ItemArmorInfusableThaumRev.materialData.put(matWardenicSteel, new ThaumRevMaterialDataSet().setUnlocName(".wardenicSteel.", new String[] {"helmet", "chestplate", "greaves", "boots"}).setIcon("wardenicSteel", new String[] {"Helmet", "Chestplate", "Greaves", "Boots"}).setRepair("itemPlateWardenSteel").setTexture("wardenSteel").setRarity(EnumRarity.uncommon).setRegName("WardenicSteel", new String[] {"Helmet", "Chestplate", "Greaves", "Boots"}));
	}

	public static void loadArmor() {
		primalGoggles = new ItemArmorInfusableThaumRev(matPrimal, 4, 0).setDiscount(5).setGoggles();
		primalRobes = new ItemArmorInfusableThaumRev(matPrimal, 1, 1).setDiscount(2);
		primalPants = new ItemArmorInfusableThaumRev(matPrimal, 2, 2).setDiscount(2);
		primalBoots = new ItemArmorInfusableThaumRev(matPrimal, 1, 3).setDiscount(1);

		bronzeChainHelmet = new ItemArmorInfusableThaumRev(matBronzeChain, 1, 0);
		bronzeChainmail = new ItemArmorInfusableThaumRev(matBronzeChain, 1, 1);
		bronzeChainGreaves = new ItemArmorInfusableThaumRev(matBronzeChain, 1, 2);
		bronzeChainBoots = new ItemArmorInfusableThaumRev(matBronzeChain, 1, 3);

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

	public static void loadBaubles() {
		wardenAmulet = thaumicBauble.addMetaBauble(0, "wardenAmulet", new DefaultBaubleData(BaubleType.AMULET), 2);
		loveRing = thaumicBauble.addMetaBauble(1, "loveRing", new DefaultBaubleData(BaubleType.RING).setUnequip(false), 3);
	}

	public static void loadWorldGen() {
		GameRegistry.registerWorldGenerator(new ExcubituraGenerator(), 1);
	}

	public static void aluminiumArc() {
		RegistrationWrapper.registerOreDict(new ItemStack(Items.clay_ball), "itemClay");
		RegistrationWrapper.registerOreDict(new ItemStack(Items.glass_bottle), "itemBottle");
		RegistrationWrapper.registerOreDict(dustSalisMundus, SALISMUNDUS);
		RegistrationWrapper.registerOreDict(itemEnchantedFabric, ENCHFABRIC);
		RegistrationWrapper.registerOreDict(itemQuicksilverDrop, "itemQuicksilverDrop");
		RegistrationWrapper.registerOreDict(shardBalanced, "shardBalanced");
	}

	public static void addLoot() {
		generalItem.addLoot(1102, ingotThaumicBronze, thaumicSlag);
	}

	public static void loadRecipes() {
		recipeBrass = RegistrationWrapper.registerShapelessSizedOreRecipe(ingotBrass, 0, ICU, ICU, ICU, IZN);
		recipeBronze = RegistrationWrapper.registerShapelessSizedOreRecipe(ingotBronze, 0, ICU, ICU, ICU, ISN);

		RegistrationWrapper.addSmelting(rawBrass, ingotBrass, 1.2F);
		RegistrationWrapper.addSmelting(rawBronze, ingotBronze, 1.05F);
		RegistrationWrapper.addSmelting(coatedThaumicBronze, firedThaumicBronze, 2.0F);
	}

	public static void loadThaumicRecipes() {
		recipeThaumicBronzeRaw = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyThaumicBronze, rawThaumicBronze, new AspectList().add(ORDER, 5).add(EARTH, 5).add(FIRE, 5), NBRONZE, NBRONZE, NBRONZE, NBRONZE, NBRONZE, NBRONZE, "nuggetThaumium", "nuggetThaumium", "nuggetBrass");
		recipeThaumicBronzeCoated = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyThaumicBronze, coatedThaumicBronze, new AspectList().add(EARTH, 5).add(WATER, 5), "ingotThaumicBronzeRaw", "itemQuicksilverDrop", SALISMUNDUS, "itemClay");

		recipeThaumicBronzeChain = ThaumcraftApi.addArcaneCraftingRecipe(keyBronzeChain, ItemHelper.cloneStack(thaumicBronzeChain, 12), new AspectList().add(ORDER, 10).add(FIRE, 5), " X ", "X X", 'X', "ingotThaumicBronze");

		recipeBronzeChainHelmet = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainHelmet.getItemStack(), new AspectList().add(ORDER, 10).add(EARTH, 5).add(FIRE, 5), "XXX", "X X", 'X', CHAINTBRONZE);
		recipeBronzeChainmail = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainmail.getItemStack(), new AspectList().add(ORDER, 25).add(EARTH, 12).add(FIRE, 12), "X X", "XXX", "XXX", 'X', CHAINTBRONZE);
		recipeBronzeChainGreaves = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainGreaves.getItemStack(), new AspectList().add(ORDER, 20).add(EARTH, 10).add(FIRE, 10), "XXX", "X X", "X X", 'X', CHAINTBRONZE);
		recipeBronzeChainBoots = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainBoots.getItemStack(), new AspectList().add(ORDER, 5).add(EARTH, 3).add(FIRE, 3), "X X", "X X", 'X', CHAINTBRONZE);

		recipePrimalGoggles = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalGoggles.getItemStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackGoggles, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, stackThaumometer });
		recipePrimalRobes = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalRobes.getItemStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackChestRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, ingotThaumium });
		recipePrimalPants = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalPants.getItemStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackLegsRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, itemNitor });
		recipePrimalBoots = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalBoots.getItemStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackBootsRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, planksGreatwood });

		recipeExcubituraPaste = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyExcubituraPaste, excubituraPaste, new AspectList().add(EARTH, 5).add(ENTROPY, 3), "itemExcubituraPetal", "itemExcubituraPetal", new ItemStack(Items.bowl));

		recipeExcubituraFabric = ThaumcraftApi.addArcaneCraftingRecipe(keyWardencloth, ItemHelper.cloneStack(excubituraFabric, 12), new AspectList().add(ORDER, 5), "FFF", "FPF", "FFF", 'F', ENCHFABRIC, 'P', PASTE);
		recipeWardencloth = ThaumcraftApi.addCrucibleRecipe(keyWardencloth, wardencloth, excubituraFabric, new AspectList().add(CLOTH, 2).add(ARMOR, 2).add(WARDEN, 2));

		recipeWardenclothSkullcap = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothSkullcap.getItemStack(), ThaumcraftHelper.newPrimalAspectList(10), "WEW", "EGE", 'E', ENCHFABRIC, 'W', WARDENCLOTH, 'G', ThaumcraftLibrary.itemGoggles);
		recipeWardenclothTunic = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothTunic.getItemStack(), ThaumcraftHelper.newPrimalAspectList(20), "W W", "WEW", "EWE", 'E', ENCHFABRIC, 'W', WARDENCLOTH);
		recipeWardenclothPants = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothPants.getItemStack(), ThaumcraftHelper.newPrimalAspectList(15), "EWE", "E E", "W W", 'E', ENCHFABRIC, 'W', WARDENCLOTH);
		recipeWardenclothBoots = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothBoots.getItemStack(), ThaumcraftHelper.newPrimalAspectList(10), "E E", "W W", 'E', ENCHFABRIC, 'W', WARDENCLOTH);

		recipeExcubituraOilUnproc = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOilUnproc, new AspectList().add(EARTH, 1).add(ORDER, 1), "itemBottle", PASTE, PASTE, PASTE, PASTE);
		recipeExcubituraOil = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOil, new AspectList().add(FIRE, 10).add(WATER, 5).add(EARTH, 5).add(ORDER, 25).add(ENTROPY, 5), "itemExcubituraOilUnprocessed", "quicksilver", SALISMUNDUS, ThaumcraftLibrary.itemAlumentum);

		recipeWardenBronzeChain = ThaumcraftApi.addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(wardenicBronzeChain, 6), new AspectList().add(EARTH, 5).add(AIR, 5).add(FIRE, 5).add(ORDER, 10), "CCC", "SOS", "CCC", 'C', CHAINTBRONZE, 'S', SALISMUNDUS, 'O', "itemExcubituraOil");
		recipePrimalBronzeChain = ThaumcraftApi.addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(primalBronzeChain, 2), ThaumcraftHelper.newPrimalAspectList(10).add(ORDER, 10), "BCD", "PSP", "DCB", 'B', "nuggetBrass", 'C', CHAINWBRONZE, 'D', SALISMUNDUS, 'P', ThaumcraftLibrary.itemPrimalCharm, 'S', "shardBalanced");
		recipeWardenBronzePlate = ThaumcraftApi.addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(wardenicBronzePlate, 2), new AspectList().add(EARTH, 5).add(AIR, 5).add(FIRE, 5).add(ORDER, 10), "BQC", "DOD", "CTB", 'B', "ingotThaumicBronze", 'C', CHAINWBRONZE, 'D', SALISMUNDUS, 'Q', "itemQuicksilverDrop", 'O', "itemExcubituraOil", 'T', "nuggetThaumium");

		recipeWardenicChainHelmet = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainHelmet.getItemStack(), ThaumcraftHelper.newPrimalAspectList(20), "PWP", "WGW", 'W', CHAINWBRONZE, 'P', CHAINPBRONZE, 'G', ThaumcraftLibrary.itemGoggles);
		recipeWardenicChainmail = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainmail.getItemStack(), ThaumcraftHelper.newPrimalAspectList(42), "P P", "WBW", "WWW", 'W', CHAINWBRONZE, 'P', CHAINPBRONZE, 'B', "itemPlateWardenicBronze");
		recipeWardenicChainGreaves = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainGreaves.getItemStack(), ThaumcraftHelper.newPrimalAspectList(35), "WBW", "W W", "P P", 'W', CHAINWBRONZE, 'P', CHAINPBRONZE, 'B', "itemPlateWardenicBronze");
		recipeWardenicChainBoots = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainBoots.getItemStack(), ThaumcraftHelper.newPrimalAspectList(13), "P P", "W W", 'W', CHAINWBRONZE, 'P', CHAINPBRONZE);
	}

	public static void loadResearch() {
		researchThaumRev = new FluxGearResearchItem(keyThaumRev, RESEARCH_KEY, new AspectList(), 0, 0, 0, new ItemStack(Items.potato));
		researchAlloy = new FluxGearResearchItem(keyAlloy, RESEARCH_KEY, new AspectList(), -1, 2, 0, ingotBrass);
		researchThaumicBronze = new FluxGearResearchItem(keyThaumicBronze, RESEARCH_KEY, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ORDER, 1), 0, 4, 1, ingotThaumicBronze);
		researchBronzeChain = new FluxGearResearchItem(keyBronzeChain, RESEARCH_KEY, new AspectList().add(METAL, 4).add(MAGIC, 2).add(CRAFT, 3), -1, 6, 1, thaumicBronzeChain);
		researchArmorBronzeChain = new FluxGearResearchItem(keyArmorBronzeChain, RESEARCH_KEY, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ARMOR, 3), -2, 8, 1, bronzeChainmail.getItemStack());
		researchPrimalRobes = new FluxGearResearchItem(keyRobesPrimal, RESEARCH_KEY, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(ENERGY, 2).add(SENSES, 2).add(ThaumcraftHelper.newPrimalAspectList(1)), 1, 5, 3, primalRobes.getItemStack());

		researchWardenry = new FluxGearResearchItem(keyWardenry, new AspectList().add(WARDEN, 4).add(MAGIC, 3).add(ELDRITCH, 2).add(ENERGY, 2), -2, 0, 2, new ItemStack(blockThaumicPlant));
		researchExcubituraPaste = new FluxGearResearchItem(keyExcubituraPaste, new AspectList().add(PLANT, 4).add(MAGIC, 4).add(WARDEN, 1), -4, -2, 1, excubituraPaste);
		researchWardencloth = new FluxGearResearchItem(keyWardencloth, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(WARDEN, 1), -6, -3, 1, wardencloth);
		researchArmorWardencloth = new FluxGearResearchItem(keyArmorWardencloth, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 4).add(WARDEN, 4), -8, -4, 1, wardenclothTunic.getItemStack());
		researchExcubituraOil = new FluxGearResearchItem(keyExcubituraOil, new AspectList().add(MAGIC, 4).add(PLANT, 3).add(WARDEN, 2).add(WATER, 3), -5, 0, 1, excubituraOil);
		researchWardenChain = new FluxGearResearchItem(keyWardenChain, new AspectList().add(METAL, 4).add(MAGIC, 4).add(ARMOR, 3).add(WARDEN, 2), -7, -1, 1, wardenicBronzeChain);
		researchArmorWardenChain = new FluxGearResearchItem(keyArmorWardenChain, new AspectList().add(METAL, 4).add(MAGIC, 4).add(ARMOR, 4).add(WARDEN, 4), -9, -2, 1, wardenicChainmail.getItemStack());
	}

	public static void initResearch() {
		researchThaumRev.setRound().setSpecial().setAutoUnlock().setSiblings(keyAlloy);
		researchAlloy.setStub();
		researchThaumicBronze.setParents(keyAlloy, "THAUMIUM");
		researchBronzeChain.setParents(keyThaumicBronze).setSecondary();
		researchArmorBronzeChain.setParents(keyBronzeChain).setSecondary();
		researchPrimalRobes.setParents(keyThaumRev, "THAUMIUM", "ENCHFABRIC", "GOGGLES", "NITOR", "INFUSION", "INFUSIONENCHANTMENT");

		researchWardenry.setParents(keyThaumRev).setRound().setSpecial();
		researchExcubituraPaste.setConcealed().setParents(keyWardenry);
		researchWardencloth.setParents(keyExcubituraPaste);
		researchArmorWardencloth.setParents(keyWardencloth).setSecondary();
		researchExcubituraOil.setConcealed().setParents(keyExcubituraPaste).setParentsHidden("ALUMENTUM");
		researchWardenChain.setParents(keyExcubituraOil);
		researchArmorWardenChain.setParents(keyWardenChain).setSecondary();

		if (researchLevel == 0) { //EASY-MODE
			researchThaumicBronze.setSiblings(keyBronzeChain, keyArmorBronzeChain);
			researchBronzeChain.setStub();
			researchArmorBronzeChain.setStub();
			researchExcubituraPaste.setParentsHidden("ENCHFABRIC", "GOGGLES").setSiblings(keyWardencloth, keyArmorWardencloth);
			researchWardencloth.setSecondary().setStub().registerResearchItem();
			researchArmorWardencloth.setStub();
			researchExcubituraOil.setParentsHidden(keyBronzeChain).setSiblings(keyWardenChain, keyArmorWardenChain);
			researchWardenChain.setSecondary().setStub();
			researchArmorWardenChain.setStub();
		} else if (researchLevel == 2) { //HARD-MODE
			//researchWardenry.setLost().setItemTriggers(wardenJournal1);
			researchWardencloth.setParentsHidden("ENCHFABRIC");
			researchArmorWardencloth.setParentsHidden("GOGGLES");
			researchWardenChain.setParentsHidden(keyBronzeChain);
			researchArmorWardenChain.setParentsHidden(keyArmorBronzeChain);
		} else { //NORMAL-MODE
			researchBronzeChain.setSiblings(keyArmorBronzeChain);
			researchArmorBronzeChain.setStub().registerResearchItem();
			researchWardencloth.setParentsHidden("ENCHFABRIC", "GOGGLES").setSiblings(keyArmorWardencloth);
			researchArmorWardencloth.setStub();
			researchWardenChain.setParentsHidden(keyBronzeChain).setSiblings(keyArmorWardenChain);
			researchArmorWardenChain.setStub();
		}
	}

	public static void registerResearch() {
		researchThaumRev.registerResearchItem();
		researchAlloy.registerResearchItem();
		researchThaumicBronze.registerResearchItem();
		researchBronzeChain.registerResearchItem();
		researchArmorBronzeChain.registerResearchItem();
		researchPrimalRobes.registerResearchItem();

		researchWardenry.registerResearchItem();
		researchExcubituraPaste.registerResearchItem();
		researchWardencloth.registerResearchItem();
		researchArmorWardencloth.registerResearchItem();
		researchExcubituraOil.registerResearchItem();
		researchWardenChain.registerResearchItem();
		researchArmorWardenChain.registerResearchItem();
	}

	public static void setPages() {
		researchThaumRev.setPages(new ResearchPage("0"));
		researchAlloy.setPages(new ResearchPage("0"), new ResearchPage(recipeBrass), new ResearchPage(rawBrass), new ResearchPage(recipeBronze), new ResearchPage(rawBronze));
		researchThaumicBronze.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeRaw), new ResearchPage(recipeThaumicBronzeCoated), new ResearchPage(coatedThaumicBronze), new ResearchPage("1"));
		researchBronzeChain.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeChain));
		researchArmorBronzeChain.setPages(new ResearchPage("0"), new ResearchPage(recipeBronzeChainHelmet), new ResearchPage(recipeBronzeChainmail), new ResearchPage(recipeBronzeChainGreaves), new ResearchPage(recipeBronzeChainBoots));
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
