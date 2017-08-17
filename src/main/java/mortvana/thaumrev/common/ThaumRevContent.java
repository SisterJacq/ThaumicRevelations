package mortvana.thaumrev.common;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

import baubles.api.BaubleType;
import magicbees.api.MagicBeesAPI;
import mortvana.thaumrev.block.*;
import mortvana.thaumrev.item.*;
import mortvana.thaumrev.melteddashboard.intermod.baubles.util.DefaultBaubleData;
import mortvana.thaumrev.util.AspectStack;
import mortvana.thaumrev.util.RecipeHelper;
import mortvana.thaumrev.util.item.data.ThaumRevMaterialDataSet;
import mortvana.thaumrev.melteddashboard.ColorLibrary;
import mortvana.thaumrev.melteddashboard.intermod.thaumcraft.research.FluxGearResearchItem;
import mortvana.thaumrev.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.thaumrev.melteddashboard.item.ItemArmorFluxGear;
import mortvana.thaumrev.melteddashboard.util.helpers.ItemHelper;
import mortvana.thaumrev.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import mortvana.thaumrev.world.ExcubituraGenerator;
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

		blockStoneDecor = new BlockStoneDecor();

		blockStorage = new BlockMetalStorage();

		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class, "blockThaumicPlant");

		GameRegistry.registerBlock(blockStoneDecor, ItemBlockDecorStone.class, "blockDecorStone");

		GameRegistry.registerBlock(blockStorage, ItemBlockMetalStorage.class, "blockStorage");
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

	public static void loadBlocks() {
		excubituraRose = new ItemStack(blockThaumicPlant, 1, 0);

		wardenicObsidian = new ItemStack(blockStoneDecor, 1, 0);
		eldritchStone = new ItemStack(blockStoneDecor, 1, 1);
		wardenicQuartzBlock = new ItemStack(blockStoneDecor, 1, 2);
		wardenicQuartzChiseled = new ItemStack(blockStoneDecor, 1, 3);
		wardenicQuartzPillar = new ItemStack(blockStoneDecor, 1, 4);

		blockCopper = new ItemStack(blockStorage, 1, 0);
		blockZinc = new ItemStack(blockStorage, 1, 1);
		blockTin = new ItemStack(blockStorage, 1, 2);
		blockSilver = new ItemStack(blockStorage, 1, 3);
		blockBrass = new ItemStack(blockStorage, 1, 4);
		blockBronze = new ItemStack(blockStorage, 1, 5);
		blockThaumicBronze = new ItemStack(blockStorage, 1, 6);
		blockSteel = new ItemStack(blockStorage, 1, 7);
		blockVoidbrass = new ItemStack(blockStorage, 1, 8);
		blockVoidsteel = new ItemStack(blockStorage, 1, 9);
		blockElectrum = new ItemStack(blockStorage, 1, 10);
		blockWardenicSteel = new ItemStack(blockStorage, 1, 11);
		blockThaumicElectrum = new ItemStack(blockStorage, 1, 12);
		blockVoidmetal = new ItemStack(blockStorage, 1, 13);
	}

	public static void loadItems() {
		ingotCopper = generalItem.addOreDictItem(0, iCu);
		ingotZinc = generalItem.addOreDictItem(1, iZn);
		ingotTin = generalItem.addOreDictItem(2, iSn);
		ingotSilver = generalItem.addOreDictItem(3, "ingotSilver");

		nuggetCopper = generalItem.addOreDictItem(10, "nuggetCopper");
		nuggetZinc = generalItem.addOreDictItem(11, "nuggetZinc");
		nuggetTin = generalItem.addOreDictItem(12, "nuggetTin");
		nuggetSilver = generalItem.addOreDictItem(13, "nuggetSilver");

		dustCopper = generalItem.addOreDictItem(20, "dustCopper");
		dustZinc = generalItem.addOreDictItem(21, "dustZinc");
		dustTin = generalItem.addOreDictItem(22, "dustTin");
		dustSilver = generalItem.addOreDictItem(23, "dustSilver");

		ingotBrass = generalItem.addOreDictItem(30, "ingotBrass");
		ingotBronze = generalItem.addOreDictItem(31, "ingotBronze");
		ingotThaumicBronze = generalItem.addOreDictItem(32, "ingotThaumicBronze");
		ingotSteel = generalItem.addOreDictItem(33, "ingotSteel");
		ingotVoidbrass = generalItem.addOreDictItem(34, "ingotVoidbrass");
		ingotVoidsteel = generalItem.addOreDictItem(35, "ingotVoidsteel");
		ingotElectrum = generalItem.addOreDictItem(36, "ingotElectrum");

		nuggetBrass = generalItem.addOreDictItem(40, "nuggetBrass");
		nuggetBronze = generalItem.addOreDictItem(41, nBronze);
		nuggetThaumicBronze = generalItem.addOreDictItem(42, "nuggetThaumicBronze");
		nuggetSteel = generalItem.addOreDictItem(43, "nuggetSteel");
		nuggetVoidbrass = generalItem.addOreDictItem(44, "nuggetVoidbrass");
		nuggetVoidsteel = generalItem.addOreDictItem(45, "nuggetVoidsteel");
		nuggetElectrum = generalItem.addOreDictItem(46, "nuggetElectrum");

		dustBrass = generalItem.addOreDictItem(50, "dustBrass");
		dustBronze = generalItem.addOreDictItem(51, "dustBronze");
		dustThaumicBronze = generalItem.addOreDictItem(52, "dustThaumicBronze");
		dustSteel = generalItem.addOreDictItem(53, "dustSteel");
		dustVoidbrass = generalItem.addOreDictItem(54, "dustVoidbrass");
		dustVoidsteel = generalItem.addOreDictItem(55, "dustVoidsteel");
		dustElectrum = generalItem.addOreDictItem(56, "dustElectrum");


		rawBrass = generalItem.addOreDictItem(60, "ingotBrassRaw");
		rawBronze = generalItem.addOreDictItem(61, "ingotBronzeRaw");
		rawThaumicBronze = generalItem.addOreDictItem(62, "ingotThaumicBronzeRaw");

		rawElectrum = generalItem.addOreDictItem(66, "ingotElectrumRaw");

		dustSalisMundusTiny = generalItem.addOreDictItem(70, salisPinch);

		coatedThaumicBronze = generalItem.addOreDictItem(72, "ingotThaumicBronzeCoated");

		ceramicSlag = generalItem.addOreDictItem(80, "itemSlagCeramic");
		thaumicSlag = generalItem.addOreDictItem(81, "itemSlagThaumic");
		arcaneSingularity = generalItem.addOreDictItem(82, "itemArcaneSingularity");
		stabilizedSingularity = generalItem.addOreDictItem(83, "itemStabilizedSingularity");

		thaumicBronzeChain = generalItem.addOreDictItem(92, "thaumicBronzeChain", chainTBronze);

		excubituraPetal = generalItem.addOreDictItem(100, "excubituraPetal", "itemExcubituraPetal");

		excubituraPaste = generalItem.addOreDictItem(110, "excubituraPaste", paste);
		excubituraFabric = generalItem.addOreDictItem(111, "excubituraFabric", "itemExcubituraFabric"); //FIX
		itemWardencloth = generalItem.addOreDictItem(112, "wardencloth", wardencloth); //FIX

		excubituraOilUnproc = generalItem.addOreDictItem(120, "excubituraOilUnproc", "itemExcubituraOilUnprocessed");
		excubituraOil = generalItem.addOreDictItem(121, "excubituraOil", oilExcu); //FIX
		wardenicBronzeChain = generalItem.addOreDictItem(122, "wardenicBronzeChain", chainWBronze);
		primalBronzeChain = generalItem.addOreDictItem(123, "primalBronzeChain", chainPBronze);
		wardenicBronzePlate = generalItem.addOreDictItem(124, "wardenicBronzePlate", "itemPlateWardenicBronze");

		excubituraOilPure = generalItem.addOreDictItem(130, "excubituraOilPure", "itemExcubituraOilPure");
		wardenicSteelChain = generalItem.addOreDictItem(131, "wardenicSteelChain", "itemChainWardenicSteel");
		wardenicSteelPlate = generalItem.addOreDictItem(132, "wardenicSteelPlate", "itemPlateWardenicSteel");
		detailedSteelPlate = generalItem.addOreDictItem(133, "detailedSteelPlate", "itemPlateWardenicSteelDetailed");
		runicSteelPlate = generalItem.addOreDictItem(134, "runicSteelPlate", "itemPlateWardenicSteelRunic");

		wardenicQuartz = generalItem.addOreDictItem(140, "wardenQuartz", "gemQuartzWardenic");
		wardenicCrystal = generalItem.addOreDictItem(141, "wardenicCrystal", "crystalWardenic");
		dustWardenicQuartz = generalItem.addOreDictItem(142, "dustWardenicQuartz");
		wardenicCrystalCrushed = generalItem.addOreDictItem(143, "dustWardenicCrystal", "dustWardenic");
		binderWardenic = generalItem.addOreDictItem(144, "binderWardenic", "itemBinderWardenic");
		ingotWardenicAlloy = generalItem.addOreDictItem(145, "ingotWardenicComposite");
		plateWardenic = generalItem.addOreDictItem(146, "itemPlateWardenicComposite");

		wardenicCrystalAwakened = generalItem.addOreDictItem(150, "wardenicCrystalAwakened", "crystalWardenicAwakened");

		ingotWardenicSteel = generalItem.addOreDictItem(500, "ingotWardenicSteel");
		ingotThaumicElectrum = generalItem.addOreDictItem(501, "ingotThaumicElectrum");

		nuggetWardenicSteel = generalItem.addOreDictItem(510, "nuggetWardenicSteel");
		nuggetThaumicElectrum = generalItem.addOreDictItem(511, "nuggetThaumicElectrum");

		dustWardenicSteel = generalItem.addOreDictItem(520, "dustWardenicSteel");
		dustThaumicElectrum = generalItem.addOreDictItem(521, "dustThaumicElectrum");

		wardenicHardener = generalItem.addOreDictItem(1050, "itemWardenicHardener");

		firedThaumicBronze = generalItem.addOreDictItem(1102, "ingotThaumicBronzeFired");

		seedExcubitura = generalItem.addOreDictItem(1200, "seedExcubitura");
		seedCotton = generalItem.addOreDictItem(1201, "seedCotton");
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

		wardenicPlateHelmet = new ItemArmorInfusableThaumRev(matWardenicSteel, 2, 0).setGoggles();
		wardenicChestplate = new ItemArmorInfusableThaumRev(matWardenicSteel, 2, 1);
		wardenicPlateGreaves = new ItemArmorInfusableThaumRev(matWardenicSteel, 2, 2);
		wardenicPlateBoots = new ItemArmorInfusableThaumRev(matWardenicSteel, 2, 3);
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
		RecipeHelper.registerOreDict(new ItemStack(Items.clay_ball), "itemClay");
		RecipeHelper.registerOreDict(new ItemStack(Items.glass_bottle), "itemBottle");
		RecipeHelper.registerOreDict(dustSalisMundus, salisMundus);
		RecipeHelper.registerOreDict(itemEnchantedFabric, enchFabric);
		RecipeHelper.registerOreDict(itemQuicksilverDrop, nHg);
		RecipeHelper.registerOreDict(itemShardBalanced, shardBalanced);
	}

	public static void addLoot() {
		generalItem.addLoot(1102, ingotThaumicBronze, thaumicSlag);
	}

	public static void loadRecipes() {
		recipeBrass = RecipeHelper.generateShapelessSizedOreRecipe(ingotBrass, 0, iCu, iCu, iCu, iZn);
		recipeBronze = RecipeHelper.generateShapelessSizedOreRecipe(ingotBronze, 0, iCu, iCu, iCu, iSn);

		if (ThaumRevConfig.cuAlloys) {
			RecipeHelper.addRecipe(recipeBrass);
			RecipeHelper.addRecipe(recipeBrass);

			RecipeHelper.addSmelting(rawBrass, ingotBrass, 1.2F);
			RecipeHelper.addSmelting(rawBronze, ingotBronze, 1.05F);

			//RecipeHelper.addStorageRecipes(nuggetBrass, ingotBrass, blockBrass);
		}

		recipeSalisTiny = RecipeHelper.addReverseStorageRecipe(dustSalisMundusTiny, salisMundus);
		recipeSalis = RecipeHelper.addStorageRecipe(dustSalisMundus, salisPinch);

		RecipeHelper.addSmelting(coatedThaumicBronze, firedThaumicBronze, 2.0F);
	}

	public static void loadThaumicRecipes() {
		recipeThaumicBronzeRaw = RecipeHelper.addShapelessArcaneCraftingRecipe(keyThaumicBronze, rawThaumicBronze, new AspectList().add(ORDER, 5).add(EARTH, 5).add(FIRE, 5), nBronze, nBronze, nBronze, nBronze, nBronze, nBronze, "nuggetThaumium", "nuggetThaumium", "nuggetBrass");
		recipeThaumicBronzeCoated = RecipeHelper.addShapelessArcaneCraftingRecipe(keyThaumicBronze, coatedThaumicBronze, new AspectList().add(EARTH, 5).add(WATER, 5), "ingotThaumicBronzeRaw", nHg, salisPinch, "itemClay");

		recipeThaumicBronzeChain = RecipeHelper.addArcaneCraftingRecipe(keyBronzeChain, ItemHelper.cloneStack(thaumicBronzeChain, 12), new AspectList().add(ORDER, 10).add(FIRE, 5), " X ", "X X", 'X', "ingotThaumicBronze");

		recipeBronzeChainHelmet = RecipeHelper.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainHelmet.getStack(), new AspectList().add(ORDER, 10).add(EARTH, 5).add(FIRE, 5), "XXX", "X X", 'X', chainTBronze);
		recipeBronzeChainmail = RecipeHelper.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainmail.getStack(), new AspectList().add(ORDER, 25).add(EARTH, 12).add(FIRE, 12), "X X", "XXX", "XXX", 'X', chainTBronze);
		recipeBronzeChainGreaves = RecipeHelper.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainGreaves.getStack(), new AspectList().add(ORDER, 20).add(EARTH, 10).add(FIRE, 10), "XXX", "X X", "X X", 'X', chainTBronze);
		recipeBronzeChainBoots = RecipeHelper.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainBoots.getStack(), new AspectList().add(ORDER, 5).add(EARTH, 3).add(FIRE, 3), "X X", "X X", 'X', chainTBronze);

		//recipeRunicInfuser = RecipeHelper.addArcaneCraftingRecipe(keyRunicInfuser, runicInfuser, ThaumcraftHelper.newPrimalAspectList(25, 10, 10, 15, 30, 15), "QRQ", "SBS", "ITI", 'Q', nHg, 'R', visRelay, 'S', arcStoneSlab, 'B', shardBalanced, 'I', "ingotThaumium", 'T', table);
		recipeArcaneSingularity = RecipeHelper.addShapelessArcaneCraftingRecipe(keyRunicInfuser, arcaneSingularity, ThaumcraftHelper.newPrimalAspectList(2, 10, 0, 0, 5, 5), itemAlumentum, itemNitor);
		recipeStableSingularity = RecipeHelper.addShapelessArcaneCraftingRecipe(keyRunicInfuser, stabilizedSingularity, ThaumcraftHelper.newPrimalAspectList(5, 10, 5, 5, 30, 5), arcaneSingularity, redstone, salisMundus, shardBalanced);

		//recipeDarkAlchemicalInfuser = RecipeHelper.addArcaneCraftingRecipe(keyDarkRunicInfuser, darkRunicInfuser, ThaumcraftHelper.newPrimalAspectList(15, 5, 10, 10, 20, 30), "GVG", "MSM", "ORO", 'G', nAu, 'V', voidSeed, 'M', mirror, 'S', stableSingularity, 'O', obsTotem, 'R', runicInfuser);


		recipePrimalGoggles = RecipeHelper.addInfusionCraftingRecipe(keyRobesPrimal, primalGoggles.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackGoggles, new ItemStack[] {itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, stackThaumometer});
		recipePrimalRobes = RecipeHelper.addInfusionCraftingRecipe(keyRobesPrimal, primalRobes.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackChestRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, ingotThaumium });
		recipePrimalPants = RecipeHelper.addInfusionCraftingRecipe(keyRobesPrimal, primalPants.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackLegsRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, itemNitor });
		recipePrimalBoots = RecipeHelper.addInfusionCraftingRecipe(keyRobesPrimal, primalBoots.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackBootsRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, planksGreatwood });

		recipeWardenicHardener = RecipeHelper.addInfusionCraftingRecipe(keyWardenicObsidian, wardenicHardener, 2, new AspectList().add(EARTH, 8).add(COLD, 8).add(MAGIC, 4).add(ORDER, 8), stabilizedSingularity, new ItemStack[] { new ItemStack(Items.redstone), dustSalisMundus, excubituraOilPure, itemShardWater, itemShardWater, itemShardOrder });
		if (Loader.isModLoaded("ThermalFoundation")) {
			recipeWardenicHardenerAlt = RecipeHelper.addInfusionCraftingRecipe(keyWardenicObsidian, wardenicHardener, 2, new AspectList().add(EARTH, 8).add(COLD, 8).add(MAGIC, 4).add(ORDER, 8), stabilizedSingularity, new ItemStack[] {new ItemStack(Items.redstone), dustSalisMundus, excubituraOilPure, new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 513)});
		}

		recipeExcubituraPaste = RecipeHelper.addShapelessArcaneCraftingRecipe(keyExcubituraPaste, excubituraPaste, new AspectList().add(EARTH, 5).add(ENTROPY, 3), "itemExcubituraPetal", "itemExcubituraPetal", new ItemStack(Items.bowl));

		recipeExcubituraFabric = RecipeHelper.addArcaneCraftingRecipe(keyWardencloth, ItemHelper.cloneStack(excubituraFabric, 12), new AspectList().add(ORDER, 5), "FFF", "FPF", "FFF", 'F', enchFabric, 'P', paste);
		recipeWardencloth = RecipeHelper.addCrucibleRecipe(keyWardencloth, itemWardencloth, excubituraFabric, new AspectList().add(CLOTH, 2).add(ARMOR, 2).add(WARDEN, 2));

		recipeWardenclothSkullcap = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothSkullcap.getStack(), ThaumcraftHelper.newPrimalAspectList(10), "WEW", "EGE", 'E', enchFabric, 'W', wardencloth, 'G', itemGoggles);
		recipeWardenclothTunic = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothTunic.getStack(), ThaumcraftHelper.newPrimalAspectList(20), "W W", "WEW", "EWE", 'E', enchFabric, 'W', wardencloth);
		recipeWardenclothPants = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothPants.getStack(), ThaumcraftHelper.newPrimalAspectList(15), "EWE", "E E", "W W", 'E', enchFabric, 'W', wardencloth);
		recipeWardenclothBoots = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(10), "E E", "W W", 'E', enchFabric, 'W', wardencloth);

		recipeExcubituraOilUnproc = RecipeHelper.addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOilUnproc, new AspectList().add(EARTH, 1).add(ORDER, 1), "itemBottle", paste, paste, paste, paste);
		recipeExcubituraOil = RecipeHelper.addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOil, ThaumcraftHelper.newPrimalAspectList(0, 10, 5, 5, 25, 5), "itemExcubituraOilUnprocessed", nHg, salisPinch, itemAlumentum);

		recipeWardenBronzeChain = RecipeHelper.addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(wardenicBronzeChain, 6), ThaumcraftHelper.newPrimalAspectList(5, 5, 0, 5, 10, 0), "CCC", "SOS", "CCC", 'C', chainTBronze, 'S', salisPinch, 'O', oilExcu);
		recipePrimalBronzeChain = RecipeHelper.addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(primalBronzeChain, 2), ThaumcraftHelper.newPrimalAspectList(10).add(ORDER, 10), "BCD", "PSP", "DCB", 'B', "nuggetBrass", 'C', chainWBronze, 'D', salisPinch, 'P', itemPrimalCharm, 'S', shardBalanced);
		recipeWardenBronzePlate = RecipeHelper.addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(wardenicBronzePlate, 2), ThaumcraftHelper.newPrimalAspectList(5, 5, 0, 5, 10, 0), "BQC", "DOD", "CTB", 'B', "ingotThaumicBronze", 'C', chainWBronze, 'D', salisPinch, 'Q', nHg, 'O', oilExcu, 'T', "nuggetThaumium");

		recipeWardenicChainHelmet = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainHelmet.getStack(), ThaumcraftHelper.newPrimalAspectList(20), "PWP", "WGW", 'W', chainWBronze, 'P', chainPBronze, 'G', itemGoggles);
		recipeWardenicChainmail = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainmail.getStack(), ThaumcraftHelper.newPrimalAspectList(42), "P P", "WBW", "WWW", 'W', chainWBronze, 'P', chainPBronze, 'B', "itemPlateWardenicBronze");
		recipeWardenicChainGreaves = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainGreaves.getStack(), ThaumcraftHelper.newPrimalAspectList(35), "WBW", "W W", "P P", 'W', chainWBronze, 'P', chainPBronze, 'B', "itemPlateWardenicBronze");
		recipeWardenicChainBoots = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(13), "P P", "W W", 'W', chainWBronze, 'P', chainPBronze);

		recipePureOil = RecipeHelper.addShapelessArcaneCraftingRecipe(keyPureOil, excubituraOilPure, ThaumcraftHelper.newPrimalAspectList(0, 10, 10, 15, 40, 5), oilExcu, oilExcu, oilExcu, oilExcu, salisMundus, "itemArcaneSingularity");

		recipesWardenSteel = RecipeHelper.addInfusionCraftingRecipes(keyWardenSteel, ingotWardenicSteel, 1, new AspectList().add(METAL, 4).add(ARMOR, 2).add(TOOL, 2).add(ORDER, 4).add(MAGIC, 4).add(WARDEN, 4), "ingotSteel", new ItemStack[] {dustSalisMundusTiny, dustSalisMundusTiny, excubituraOilPure, itemQuicksilverDrop});

		recipeWardenSteelChain = RecipeHelper.addArcaneCraftingRecipe(keyWardenPlate, ItemHelper.cloneStack(wardenicSteelChain, 12), new AspectList().add(ORDER, 25).add(FIRE, 15), " X ", "X X", 'X', "ingotWardenicSteel");
		recipeWardenSteelPlate = RecipeHelper.addArcaneCraftingRecipe(keyWardenPlate, wardenicSteelPlate, new AspectList().add(ORDER, 1), " A ", "ASA", " A ", 'A', itemAlumentum, 'S', "ingotWardenicSteel");
		recipeDetailedSteelPlate = RecipeHelper.addArcaneCraftingRecipe(keyWardenPlate, detailedSteelPlate, ThaumcraftHelper.newPrimalAspectList(0, 10, 5, 5, 15, 0), "TCB", "QPQ", "BCT", 'T', "nuggetThaumium", 'B', "nuggetBrass", 'C', "itemChainWardenicSteel", 'Q', nHg, 'P', "itemPlateWardenicSteel");
		recipeRunicSteelPlate = RecipeHelper.addInfusionCraftingRecipe(keyWardenPlate, runicSteelPlate, 1, new AspectList().add(ARMOR, 4).add(MAGIC, 4).add(ENERGY, 4).add(FLIGHT, 4), detailedSteelPlate, new ItemStack[] { dustSalisMundusTiny, dustSalisMundusTiny, dustSalisMundusTiny, dustSalisMundusTiny, itemWardencloth, new ItemStack(Items.redstone)});

		recipeWardenicPlateHelmet = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateHelmet.getStack(), ThaumcraftHelper.newPrimalAspectList(30), "XXX", "XGX", 'X',"itemPlateWardenicSteelRunic", 'G', stackGoggles);
		recipeWardenicChestplate = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicChestplate.getStack(), ThaumcraftHelper.newPrimalAspectList(70), "X X", "XXX", "XXX", 'X',"itemPlateWardenicSteelRunic");
		recipeWardenicPlateGreaves = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateGreaves.getStack(), ThaumcraftHelper.newPrimalAspectList(50), "XXX", "X X", "X X", 'X',"itemPlateWardenicSteelRunic");
		recipeWardenicPlateBoots = RecipeHelper.addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(20), "X X", "X X", 'X',"itemPlateWardenicSteelRunic");
	}

	public static void loadResearch() {
		researchThaumRev = new FluxGearResearchItem(keyThaumRev, new AspectList(), 0, 0, 0, potato);
		researchMaterial = new FluxGearResearchItem(keyMaterial, new AspectList(), -1, 2, 0, ingotBrass);

		researchThaumicBronze = new FluxGearResearchItem(keyThaumicBronze, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ORDER, 1), -2, 4, 1, ingotThaumicBronze);
		researchBronzeChain = new FluxGearResearchItem(keyBronzeChain, new AspectList().add(METAL, 4).add(MAGIC, 2).add(CRAFT, 3), -4, 5, 1, thaumicBronzeChain);
		researchArmorBronzeChain = new FluxGearResearchItem(keyArmorBronzeChain, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ARMOR, 3), -6, 4, 1, bronzeChainmail);
		researchRunicInfuser = new FluxGearResearchItem(keyRunicInfuser, new AspectList().add(ENERGY, 4).add(MAGIC, 4).add(AURA, 2).add(CRAFT, 3).add(MECHANISM, 4), 2, 2, 2, arcaneSingularity);
		researchPrimalRobes = new FluxGearResearchItem(keyRobesPrimal, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(ENERGY, 2).add(SENSES, 2).add(ThaumcraftHelper.newPrimalAspectList(1)), -8, 2, 3, primalRobes);

		researchWardenry = new FluxGearResearchItem(keyWardenry, new AspectList().add(WARDEN, 4).add(MAGIC, 3).add(ELDRITCH, 2).add(ENERGY, 2), -2, 0, 2, new ItemStack(blockThaumicPlant));

		researchWardenicObsidian = new FluxGearResearchItem(keyWardenicObsidian, new AspectList().add(EARTH, 4).add(CRYSTAL, 4).add(FIRE, 3).add(MAGIC, 3).add(ARMOR, 4).add(WARDEN, 1), -3, -2, 2, wardenicHardener);

		researchExcubituraPaste = new FluxGearResearchItem(keyExcubituraPaste, new AspectList().add(PLANT, 4).add(MAGIC, 4).add(WARDEN, 1), -4, 2, 1, excubituraPaste);
		researchWardencloth = new FluxGearResearchItem(keyWardencloth, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(WARDEN, 1), -6, 1, 1, itemWardencloth);
		researchArmorWardencloth = new FluxGearResearchItem(keyArmorWardencloth, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 4).add(WARDEN, 4), -8, 0, 1, wardenclothTunic);

		researchExcubituraOil = new FluxGearResearchItem(keyExcubituraOil, new AspectList().add(MAGIC, 4).add(PLANT, 3).add(WARDEN, 2).add(WATER, 3), -5, -1, 1, excubituraOil);
		researchWardenChain = new FluxGearResearchItem(keyWardenChain, new AspectList().add(METAL, 4).add(MAGIC, 4).add(ARMOR, 3).add(WARDEN, 2), -7, -2, 1, wardenicBronzeChain);
		researchArmorWardenChain = new FluxGearResearchItem(keyArmorWardenChain, new AspectList().add(METAL, 4).add(MAGIC, 4).add(ARMOR, 4).add(WARDEN, 4), -9, -3, 1, wardenicChainmail);

		researchPureOil = new FluxGearResearchItem(keyPureOil, new AspectList().add(MAGIC, 4).add(WATER, 4).add(WARDEN, 3).add(ENERGY, 3), -6, -4, 2, excubituraOilPure);
		researchWardenSteel = new FluxGearResearchItem(keyWardenSteel, new AspectList().add(METAL, 5).add(MAGIC, 4).add(TOOL, 2).add(ARMOR, 2).add(WARDEN, 3), -8, -5, 2, ingotWardenicSteel);
		researchWardenPlate = new FluxGearResearchItem(keyWardenPlate, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ARMOR, 3).add(WARDEN, 3), -10, -6, 2, wardenicSteelPlate);
		researchArmorWardenSteel = new FluxGearResearchItem(keyArmorWardenSteel, new AspectList().add(METAL, 4).add(MAGIC, 4).add(ARMOR, 4).add(WARDEN, 4), -12, -8, 2, wardenicChestplate);
	}

	public static void initResearch() {
		researchThaumRev.setRound().setSpecial().setAutoUnlock().setSiblings(keyMaterial);
		researchMaterial.setStub();
		researchThaumicBronze.setParents(keyMaterial).setParentsHidden(keyThaumium);
		researchBronzeChain.setParents(keyThaumicBronze).setSecondary();
		researchArmorBronzeChain.setParents(keyBronzeChain).setSecondary();
		researchRunicInfuser.setParents(keyThaumRev).setParentsHidden(keyThaumium, keyAlumentum, keyNitor, keyVisPower);
		researchPrimalRobes.setParentsHidden(keyThaumRev, keyThaumium, keyFabric, keyGoggles, keyNitor, keyInfusion, "INFUSIONENCHANTMENT");

		researchWardenry.setParents(keyThaumRev).setRound().setSpecial().setLost().setItemTriggers(excubituraRose);

		researchWardenicObsidian.setParents(keyPureOil).setParentsHidden(keyRunicInfuser).setSecondary().setHidden().setItemTriggers(new ItemStack(Blocks.obsidian));

		researchExcubituraPaste.setConcealed().setParents(keyWardenry);
		researchWardencloth.setParents(keyExcubituraPaste);
		researchArmorWardencloth.setParents(keyWardencloth).setSecondary();

		researchExcubituraOil.setConcealed().setParents(keyExcubituraPaste).setParentsHidden(keyAlumentum);
		researchWardenChain.setParents(keyExcubituraOil);
		researchArmorWardenChain.setParents(keyWardenChain).setSecondary();

		researchPureOil.setParents(keyExcubituraOil).setParentsHidden(keyRunicInfuser);
		researchWardenSteel.setParents(keyPureOil);
		researchWardenPlate.setParents(keyWardenSteel).setParentsHidden(keyWardencloth);
		researchArmorWardenSteel.setParents(keyWardenPlate).setSecondary();

		if (researchLevel == 0) { //EASY-MODE
			researchThaumicBronze.setSiblings(keyBronzeChain, keyArmorBronzeChain);
			researchBronzeChain.setStub();
			researchArmorBronzeChain.setStub();
			researchExcubituraPaste.setParentsHidden(keyFabric, keyGoggles).setSiblings(keyWardencloth, keyArmorWardencloth);
			researchWardencloth.setSecondary().setStub().registerResearchItem();
			researchArmorWardencloth.setStub();
			researchExcubituraOil.setParentsHidden(keyBronzeChain).setSiblings(keyWardenChain, keyArmorWardenChain);
			researchWardenChain.setSecondary().setStub();
			researchArmorWardenChain.setStub();
			researchPureOil.setParentsHidden(keyThaumicBronze, keyInfusion);
			researchWardenSteel.setStub();
			researchWardenPlate.setParentsHidden(keyGoggles).setSiblings(keyArmorWardenSteel);
			researchArmorWardenSteel.setStub();
		} else if (researchLevel == 2) { //HARD-MODE
			researchWardencloth.setParentsHidden(keyFabric);
			researchArmorWardencloth.setParentsHidden(keyGoggles);
			researchWardenChain.setParentsHidden(keyBronzeChain);
			researchArmorWardenChain.setParentsHidden(keyArmorBronzeChain, keyGoggles);
			researchWardenSteel.setParentsHidden(keyThaumicBronze, keyInfusion);
			researchArmorWardenSteel.setParentsHidden(keyGoggles);
		} else { //NORMAL-MODE
			researchBronzeChain.setSiblings(keyArmorBronzeChain);
			researchArmorBronzeChain.setStub();
			researchWardencloth.setParentsHidden(keyFabric, keyGoggles).setSiblings(keyArmorWardencloth);
			researchArmorWardencloth.setStub();
			researchWardenChain.setParentsHidden(keyBronzeChain, keyGoggles).setSiblings(keyArmorWardenChain);
			researchArmorWardenChain.setStub();
			researchWardenSteel.setParentsHidden(keyThaumicBronze, keyInfusion);
			researchWardenPlate.setParentsHidden(keyGoggles).setSiblings(keyArmorWardenSteel);
			researchArmorWardenSteel.setStub();
		}
	}

	public static void registerResearch() {
		researchThaumRev.registerResearchItem();
		researchMaterial.registerResearchItem();
		researchThaumicBronze.registerResearchItem();
		researchBronzeChain.registerResearchItem();
		researchArmorBronzeChain.registerResearchItem();
		researchRunicInfuser.registerResearchItem();
		researchPrimalRobes.registerResearchItem();

		researchWardenry.registerResearchItem();
		researchWardenicObsidian.registerResearchItem();
		researchExcubituraPaste.registerResearchItem();
		researchWardencloth.registerResearchItem();
		researchArmorWardencloth.registerResearchItem();
		researchExcubituraOil.registerResearchItem();
		researchWardenChain.registerResearchItem();
		researchArmorWardenChain.registerResearchItem();
		researchPureOil.registerResearchItem();
		researchWardenSteel.registerResearchItem();
		researchWardenPlate.registerResearchItem();
		researchArmorWardenSteel.registerResearchItem();
	}

	public static void setPages() {
		researchThaumRev.setPages(new ResearchPage("0"));

		if (ThaumRevConfig.cuAlloys) {
			researchMaterial.setPages(new ResearchPage("0"), new ResearchPage(recipeBrass), new ResearchPage(rawBrass), new ResearchPage(recipeBronze), new ResearchPage(rawBronze), new ResearchPage(recipeSalisTiny), new ResearchPage(recipeSalis));
		} else {
			researchMaterial.setPages(new ResearchPage("0"), new ResearchPage(recipeSalisTiny), new ResearchPage(recipeSalis));
		}

		researchThaumicBronze.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeRaw), new ResearchPage(recipeThaumicBronzeCoated), new ResearchPage(coatedThaumicBronze), new ResearchPage("1"));
		researchBronzeChain.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeChain));
		researchArmorBronzeChain.setPages(new ResearchPage("0"), new ResearchPage(recipeBronzeChainHelmet), new ResearchPage(recipeBronzeChainmail), new ResearchPage(recipeBronzeChainGreaves), new ResearchPage(recipeBronzeChainBoots));
		researchRunicInfuser.setPages(new ResearchPage("0"), new ResearchPage(recipeArcaneSingularity), new ResearchPage(recipeStableSingularity));
		researchPrimalRobes.setPages(new ResearchPage("0"), new ResearchPage(recipePrimalGoggles), new ResearchPage(recipePrimalRobes), new ResearchPage(recipePrimalPants), new ResearchPage(recipePrimalBoots));

		researchWardenry.setPages(new ResearchPage("0"), new ResearchPage("1"));

		if (recipeWardenicHardenerAlt != null) {
			researchWardenicObsidian.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicHardener), new ResearchPage(recipeWardenicHardenerAlt));
		} else {
			researchWardenicObsidian.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicHardener));
		}

		researchExcubituraPaste.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraPaste));
		researchWardencloth.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraFabric), new ResearchPage(recipeWardencloth));
		researchArmorWardencloth.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenclothSkullcap), new ResearchPage(recipeWardenclothTunic), new ResearchPage(recipeWardenclothPants), new ResearchPage(recipeWardenclothBoots));
		researchExcubituraOil.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraOilUnproc), new ResearchPage(recipeExcubituraOil));
		researchWardenChain.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenBronzeChain), new ResearchPage(recipePrimalBronzeChain), new ResearchPage(recipeWardenBronzePlate));
		researchArmorWardenChain.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicChainHelmet), new ResearchPage(recipeWardenicChainmail), new ResearchPage(recipeWardenicChainGreaves), new ResearchPage(recipeWardenicChainBoots));
		researchPureOil.setPages(new ResearchPage("0"), new ResearchPage(recipePureOil));
		researchWardenSteel.setPages(new ResearchPage("0"), new ResearchPage(recipesWardenSteel));
		researchWardenPlate.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenSteelChain), new ResearchPage(recipeWardenSteelPlate), new ResearchPage(recipeDetailedSteelPlate), new ResearchPage(recipeRunicSteelPlate));
		researchArmorWardenSteel.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicPlateHelmet), new ResearchPage(recipeWardenicChestplate), new ResearchPage(recipeWardenicPlateGreaves), new ResearchPage(recipeWardenicPlateBoots));
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
		RecipeHelper.addAspects(new ItemStack(Items.clock), new AspectStack(tempus, 4));
		RecipeHelper.addAspects(new ItemStack(Items.repeater), new AspectStack(tempus, 2));
	}

    public static Aspect tempus;
}
