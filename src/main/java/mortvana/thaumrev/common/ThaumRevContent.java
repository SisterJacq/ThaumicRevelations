package mortvana.thaumrev.common;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import baubles.api.BaubleType;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;

import magicbees.api.MagicBeesAPI;

import mortvana.melteddashboard.lib.ColorLibrary;
import mortvana.melteddashboard.intermod.baubles.util.DefaultBaubleData;
import mortvana.melteddashboard.intermod.thaumcraft.research.DummyResearchItem;
import mortvana.melteddashboard.intermod.thaumcraft.research.FluxGearResearchItem;
import mortvana.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.melteddashboard.item.ItemArmorFluxGear;
import mortvana.melteddashboard.lib.GradientLibrary;
import mortvana.melteddashboard.util.helpers.ItemHelper;
import mortvana.melteddashboard.util.helpers.mod.ThaumcraftHelper;

import mortvana.thaumrev.block.*;
import mortvana.thaumrev.block.itemblock.*;
import mortvana.thaumrev.item.*;
import mortvana.thaumrev.util.*;
import mortvana.thaumrev.util.item.data.ThaumRevMaterialDataSet;
import mortvana.thaumrev.world.ThaumRevWorldGenerator;

import static mortvana.melteddashboard.lib.ColorLibrary.*;
import static thaumcraft.api.aspects.Aspect.*;
import static mortvana.melteddashboard.lib.ThaumcraftLibrary.*;
import static mortvana.melteddashboard.lib.ThermalLibrary.*;
import static mortvana.melteddashboard.lib.StringLibrary.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;
import static mortvana.thaumrev.util.RecipeHelper.*;

public class ThaumRevContent {

	public static void preInit() {
		generalTab = new FluxGearCreativeTab("Thaumic Revelations", "thaumicRevelations");
		createBlocks();
		createItems();
		registerBlocks();
		registerItems();
		setResearchLevel();
	}

	public static void init() {
		loadBlocks();
		loadMetalBlocks();
		loadItems();
		loadMetalItems();
		loadMaterials();
		loadArmor();
		loadTools();
		loadBaubles();

		GameRegistry.registerWorldGenerator(new ThaumRevWorldGenerator(), 1);

		loadInit();

		((FluxGearCreativeTab) generalTab).setItem(wardenAmulet);
	}

	public static void postInit() {
		ResearchCategories.registerCategory(RESEARCH_KEY_MAIN, new ResourceLocation(RESOURCE_PREFIX, "textures/items/baubles/amuletWarden.png"), new ResourceLocation("fluxgear", "textures/gui/gui_researchbackthaumrev.png"));
		ResearchCategories.registerCategory(RESEARCH_KEY_METAL, new ResourceLocation(RESOURCE_PREFIX, "textures/items/material/ingotThaumicBronze.png"), new ResourceLocation("fluxgear", "textures/gui/gui_researchbackthaumrev.png"));
		aluminiumArc();
		addLoot();
		determineTempus();
		//loadRecipes();
		//loadMetalRecipes(); //TODO
		//loadThaumicRecipes();
		//loadResearch();
		//initResearch();
		//registerResearch();
		//setPages();
	}

	public static void createBlocks() {
		blockThaumicPlant = new BlockThaumicPlant();
		blockOre = new BlockOre();

		blockWoodDecor = new BlockWoodDecor();
		blockStoneDecor = new BlockStoneDecor();

		blockStorageOre = new BlockStorageOre();
		blockStorageAlloy1 = new BlockStorageAlloy1();

		blockStoneSlab = new BlockStoneSlab();
		blockStoneSlabDouble = new BlockStoneSlab(blockStoneSlab);
		blockWardenicQuartzStairs = new BlockStairsWardenicQuartz();
	}

	public static void createItems() {
		generalItem = new ItemThaumRev();
		thaumicBauble = new ItemThaumicBauble();

		itemFocusPurity = new ItemFocusPurity();
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class, "blockThaumicPlant");
		GameRegistry.registerBlock(blockOre, ItemBlockOre.class, "blockOre");

		GameRegistry.registerBlock(blockWoodDecor, ItemBlockWoodDecor.class, "blockWoodDecor");
		GameRegistry.registerBlock(blockStoneDecor, ItemBlockStoneDecor.class, "blockStoneDecor");

		GameRegistry.registerBlock(blockStorageOre, ItemBlockStorageOre.class, "blockStorageOre");
		GameRegistry.registerBlock(blockStorageAlloy1, ItemBlockStorageAlloy1.class, "blockStorageAlloy1");

		GameRegistry.registerBlock(blockStoneSlab, ItemBlockStoneSlab.class, "stoneSlab");
		GameRegistry.registerBlock(blockStoneSlabDouble, ItemBlockStoneSlab.class, "stoneSlabDouble");
		GameRegistry.registerBlock(blockWardenicQuartzStairs, ItemBlockStairsWardenicQuartz.class, "wardenicQuartzStairs");
	}

	public static void registerItems() {
		GameRegistry.registerItem(itemFocusPurity, "itemFocusPurity");
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
		wildCotton = new ItemStack(blockThaumicPlant, 1, 1);
		wildThistle = new ItemStack(blockThaumicPlant, 1, 2);

		shiverpearl = new ItemStack(blockThaumicPlant, 1, 5);
		stormpearl = new ItemStack(blockThaumicPlant, 1, 6);
		stonepearl = new ItemStack(blockThaumicPlant, 1, 7);

		oreChalcocite = new ItemStack(blockOre, 1, 0);
		oreSphalerite = new ItemStack(blockOre, 1, 1);
		oreCassiterite = new ItemStack(blockOre, 1, 2);
		oreMillerite = new ItemStack(blockOre, 1, 3);
		oreNativeSilver = new ItemStack(blockOre, 1, 4);
		oreGalena = new ItemStack(blockOre, 1, 5);
		oreXenotime = new ItemStack(blockOre, 1, 6);
		oreWolframite = new ItemStack(blockOre, 1, 7);
		oreIridosmium = new ItemStack(blockOre, 1, 8);
		oreBismuthinite = new ItemStack(blockOre, 1, 9);
		oreTennantite = new ItemStack(blockOre, 1, 10);
		oreTetrahedrite = new ItemStack(blockOre, 1, 11);
		orePyrope = new ItemStack(blockOre, 1, 12);
		oreDioptase = new ItemStack(blockOre, 1, 13);
		oreFluonicSapphire = new ItemStack(blockOre, 1, 14);

		RecipeHelper.registerOreDict(oreChalcocite, ORE + CU, "oreChalcocite");
		RecipeHelper.registerOreDict(oreSphalerite, ORE + ZN, "oreSphalerite");
		RecipeHelper.registerOreDict(oreCassiterite, ORE + SN, "oreCassiterite");
		RecipeHelper.registerOreDict(oreMillerite, ORE + NI, "oreMillerite");
		RecipeHelper.registerOreDict(oreNativeSilver, ORE + AG, "oreNativeSilver");
		RecipeHelper.registerOreDict(oreGalena, ORE + PB, "oreGalena");
		RecipeHelper.registerOreDict(oreXenotime, "oreXenotime");
		RecipeHelper.registerOreDict(oreWolframite, ORE + W, "oreWolframite");
		RecipeHelper.registerOreDict(oreIridosmium, "oreIridosmium");
		RecipeHelper.registerOreDict(oreBismuthinite, ORE + BI, "oreBismuthinite");
		RecipeHelper.registerOreDict(oreTennantite, "oreTennantite");
		RecipeHelper.registerOreDict(oreTetrahedrite, "oreTetrahedrite");
		RecipeHelper.registerOreDict(orePyrope, "orePyrope");
		RecipeHelper.registerOreDict(oreDioptase, "oreDioptase");
		RecipeHelper.registerOreDict(oreFluonicSapphire, "oreFluonicSapphire");

		wardenicObsidian = new ItemStack(blockStoneDecor, 1, 0);
		eldritchStone = new ItemStack(blockStoneDecor, 1, 1);
		blockWardenicQuartz = new ItemStack(blockStoneDecor, 1, 2);
		blockWardenicQuartzChiseled = new ItemStack(blockStoneDecor, 1, 3);
		blockWardenicQuartzPillar = new ItemStack(blockStoneDecor, 1, 4);
		thaumicStone = new ItemStack(blockStoneDecor, 1, 5);
		infernalBlastBrick = new ItemStack(blockStoneDecor, 1, 6);
		shadowforgeBrick = new ItemStack(blockStoneDecor, 1, 7);

		RecipeHelper.registerOreDict(wardenicObsidian, "blockWardenicObsidian");
		RecipeHelper.registerOreDict(eldritchStone, "blockEldritchStone");
		for (int i = 2; i < 5; i++) {
			RecipeHelper.registerOreDict(new ItemStack(blockStoneDecor, 1, i), "blockWardenicQuartz");
		}
		RecipeHelper.registerOreDict(thaumicStone, "blockThaumicStone");
		RecipeHelper.registerOreDict(infernalBlastBrick, "blockInfernalBastFurnaceBrick");
		RecipeHelper.registerOreDict(shadowforgeBrick, "blockShadowforgeBrick");

		slabWardenicObsidian = new ItemStack(blockStoneSlab, 1, 0);
		slabEldritch = new ItemStack(blockStoneSlab, 1, 1);
		slabWardenicQuartz = new ItemStack(blockStoneSlab, 1, 2);

		RecipeHelper.registerOreDict(slabWardenicObsidian, "slabWardenicObsidian");
		RecipeHelper.registerOreDict(slabEldritch, "slabEldritchStone");
		RecipeHelper.registerOreDict(slabWardenicQuartz, "slabWardenicQuartz");

		stairsWardenicQuartz = new ItemStack(blockWardenicQuartzStairs, 1, 0);
	}

	public static void loadMetalBlocks() {
		blockCopper = new ItemStack(blockStorageOre, 1, 0);
		blockZinc = new ItemStack(blockStorageOre, 1, 1);
		blockTin = new ItemStack(blockStorageOre, 1, 2);
		blockNickel = new ItemStack(blockStorageOre, 1, 3);
		blockSilver = new ItemStack(blockStorageOre, 1, 4);
		blockLead = new ItemStack(blockStorageOre, 1, 5);
		blockLanthanides = new ItemStack(blockStorageOre, 1, 6);
		blockTungsten = new ItemStack(blockStorageOre, 1, 7);
		blockIridium = new ItemStack(blockStorageOre, 1, 8);
		blockBismuth = new ItemStack(blockStorageOre, 1, 9);
		blockAsBronze = new ItemStack(blockStorageOre, 1, 10);
		blockSbBronze = new ItemStack(blockStorageOre, 1, 11);
		blockPyrope = new ItemStack(blockStorageOre, 1, 12);
		blockDioptase = new ItemStack(blockStorageOre, 1, 13);
		blockFluonicSapphire = new ItemStack(blockStorageOre, 1, 14);
		blockOsmium = new ItemStack(blockStorageOre, 1, 15);

		for (int i = 0; i < BlockStorageOre.NAMES.length; i++) {
			RecipeHelper.registerOreDict(new ItemStack(blockStorageOre, 1, i), BlockStorageOre.NAMES[i]);
		}

		blockBrass = new ItemStack(blockStorageAlloy1, 1, 0);
		blockBronze = new ItemStack(blockStorageAlloy1, 1, 1);
		blockBiBronze = new ItemStack(blockStorageAlloy1, 1, 2);
		blockMithril = new ItemStack(blockStorageAlloy1, 1, 3);
		blockAlBronze = new ItemStack(blockStorageAlloy1, 1, 4);
		blockCupronickel = new ItemStack(blockStorageAlloy1, 1, 5);
		blockRiftishBronze = new ItemStack(blockStorageAlloy1, 1, 6);
		blockConstantan = new ItemStack(blockStorageAlloy1, 1, 7);
		blockInvar = new ItemStack(blockStorageAlloy1, 1, 8);
		blockElectrum = new ItemStack(blockStorageAlloy1, 1, 9);
		blockWardenicMetal = new ItemStack(blockStorageAlloy1, 1, 10);
		blockDullRedsolder = new ItemStack(blockStorageAlloy1, 1, 11);
		blockRedsolder = new ItemStack(blockStorageAlloy1, 1, 12);

		for (int i = 0; i < BlockStorageAlloy1.NAMES.length; i++) {
			RecipeHelper.registerOreDict(new ItemStack(blockStorageAlloy1, 1, i), BlockStorageAlloy1.NAMES[i]);
		}

		/*blockThaumicElectrum = new ItemStack(blockStorageSpecial1, 1, 0);
		blockThaumicRiftishBronze = new ItemStack(blockStorageSpecial1, 1, 1);
		blockSteel = new ItemStack(blockStorageSpecial1, 1, 2);
		blockVoidbrass = new ItemStack(blockStorageSpecial1, 1, 3);
		blockVoidsteel = new ItemStack(blockStorageSpecial1, 1, 4);
		blockVoidtungsten = new ItemStack(blockStorageSpecial1, 1, 5);
		blockWardenicBronze = new ItemStack(blockStorageSpecial1, 1, 6);
		blockWardenicSteel = new ItemStack(blockStorageSpecial1, 1, 7);
		blockWardenicRiftishBronze = new ItemStack(blockStorageSpecial1, 1, 8);
		blockWardenicCrystal = new ItemStack(blockStorageSpecial1, 1, 9);
		blockWardenicCrystalActivated = new ItemStack(blockStorageSpecial1, 1, 10);
		blockWardenicComposite = new ItemStack(blockStorageSpecial1, 1, 11);
		blockArcaneRedsolder = new ItemStack(blockStorageSpecial1, 1, 12);
		blockRedbronze = new ItemStack(blockStorageSpecial1, 1, 13);
		blockHardenedRedbronze = new ItemStack(blockStorageSpecial1, 1, 14);
		blockFluxsteel = new ItemStack(blockStorageSpecial1, 1, 15);

		for (int i = 0; i < BlockStorageSpecial1.NAMES.length; i++) {
			RecipeHelper.registerOreDict(new ItemStack(blockStorageSpecial1, 1, i), BlockStorageSpecial1.NAMES[i]);
		}*/

		//block = new ItemStack(blockStorage, 1, );
		//RecipeHelper.registerOreDict(new ItemStack(blockStorageOre, 1, ), "block");
	}

	public static void loadItems() {
		cotton = generalItem.addOreDictItem(0, "cotton", "itemCotton");
		cottonFiber = generalItem.addOreDictItem(1, "cottonFiber", "itemCottonFiber");
		cottonFabric = generalItem.addOreDictItem(2, "cottonFabric", "itemCottonFabric");
		cottonTreated = generalItem.addOreDictItem(3, "cottonTreated", "itemCottonFabricTreated");
		cottonEnchanted = generalItem.addOreDictItem(4, "cottonEnchanted", enchCotton);
		thistleLeaf = generalItem.addOreDictItem(5, "thistleLeaf", "itemThistleLeaf");
		thistleFlower = generalItem.addOreDictItem(6, "thistleFlower", "itemThistleFlower");

		arcaneSingularity = generalItem.addOreDictItem(10, "arcaneSingularity", "itemArcaneSingularity");
		stabilizedSingularity = generalItem.addOreDictItem(11, "itemStabilizedSingularity");
		animatedPiston = generalItem.addOreDictItem(12, "animatedPiston", "itemAnimatedPiston");

		aspectOrbReceptor = generalItem.addOreDictItem(20, "aspectOrbReceptor", "itemAspectOrbReceptor");

		eldritchCog = generalItem.addOreDictItem(30, "eldritchCog", "itemEldritchCog");
		eldritchKeystone = generalItem.addOreDictItem(31, "eldritchKeystone", "itemEldritchKeystone");

		greatwoodShaft = generalItem.addOreDictItem(40, "shaftGreatwood", "itemShaftGreatwood");
		greatwoodEnchanted = generalItem.addOreDictItemWithEffect(41, "plankEnchantedGreatwood", "plankGreatwood", "itemPlankEnchantedGreatwood");
		greatwoodShaftEnchanted = generalItem.addOreDictItemWithEffect(42, "shaftEnchantedGreatwood", "itemShaftEnchantedGreatwood");

		silverwoodDensified = generalItem.addOreDictItem(50, "densifiedSilverwood", "itemDensifiedSilverwood");
		silverwoodEnchanted = generalItem.addOreDictItemWithEffect(51, "plankEnchantedSilverwood", "plankSilverwood", "itemPlankEnchantedSilverwood");
		silverwoodEnchantedDensified = generalItem.addOreDictItemWithEffect(52, "plankDensifiedSilverwood", "itemPlankEnchantedDensifiedSilverwood");
		silverwoodConsecrated = generalItem.addOreDictItemWithEffect(53, "plankConsecratedSilverwood", "itemPlankConsecratedSilverwood");
		silverwoodShaftEnchanted = generalItem.addOreDictItemWithEffect(54, "shaftEnchantedSilverwood", "shaftSilverwood", "itemShaftEnchantedSilverwood");
		silverwoodShaftConsecrated = generalItem.addOreDictItemWithEffect(55, "shaftConsecratedSilverwood", "itemShaftConsecratedSilverwood");

		thaumicBronzeChain = generalItem.addOreDictItem(60, "thaumicBronzeChain", chainTBronze);

		coatedThaumicBronze = generalItem.addOreDictItem(770, "ingotThaumicBronzeCoated");
		coatedOsLu = generalItem.addOreDictItem(771, "ingotOsmiumLutetiumCoated", 2);

		firedThaumicBronze = generalItem.addOreDictItem(775, "ingotThaumicBronzeFired");
		firedOsLu = generalItem.addOreDictItem(776, "ingotOsmiumLutetiumFired", 2);

		carbonSlag = generalItem.addOreDictItem(780, "itemSlagCarbon");
		ceramicSlag = generalItem.addOreDictItem(781, "itemSlagCeramic");
		thaumicSlag = generalItem.addOreDictItem(782, "itemSlagThaumic");
		fluonicSlag = generalItem.addOreDictItem(783, "itemSlagFluonic");

		clusterZinc = generalItem.addOreDictItem(801, CLUSTER + ZN);
		clusterAluminium = generalItem.addOreDictItem(802, CLUSTER + AL);
		clusterNickel = generalItem.addOreDictItem(803, CLUSTER + NI);
		clusterPlatinum = generalItem.addOreDictItem(804, CLUSTER + PT);
		clusterXenotime = generalItem.addOreDictItem(806, "clusterXenotime");
		clusterTungsten = generalItem.addOreDictItem(807, CLUSTER + W);
		clusterIridosmium = generalItem.addOreDictItem(808, "clusterIridosmium");
		clusterBismuth = generalItem.addOreDictItem(809, CLUSTER + BI);
		clusterTennantite = generalItem.addOreDictItem(810, "clusterTennantite");
		clusterTetrahedrite = generalItem.addOreDictItem(811, "clusterTetrahedrite");

		dustAer = generalItem.addColorizedOreDictItem(890, DUST + AER, MAGICDUST, COLOR_AER);
		dustIgnis = generalItem.addColorizedOreDictItem(891, DUST + IGNIS, MAGICDUST, COLOR_IGNIS);
		dustAqua = generalItem.addColorizedOreDictItem(892, DUST + AQUA, MAGICDUST, COLOR_AQUA);
		dustTerra = generalItem.addColorizedOreDictItem(893, DUST + TERRA, MAGICDUST, COLOR_TERRA);
		dustOrdo = generalItem.addColorizedOreDictItem(894, DUST + ORDO, MAGICDUST, COLOR_ORDO);
		dustPerditio = generalItem.addColorizedOreDictItem(895, DUST + PERDITIO, MAGICDUST, COLOR_PERDITIO);
		dustIron = generalItem.addOreDictItem(896, DUST + FE);
		dustGold = generalItem.addOreDictItem(897, DUST + AU);
		dustThaumium = generalItem.addOreDictItem(898, "dustThaumium");
		dustVoidmetal = generalItem.addOreDictItem(899, "dustVoid");
		//Salis Mundus
		dustPrimalEssence = generalItem.addOreDictItem(901, "dustPrimalEssence");
		dustSulfur = generalItem.addOreDictItem(902, "dustSulfur");

		dustWardenicBinder = generalItem.addOreDictItem(910, "dustWardenicBinder");

		tinyAer = generalItem.addOreDictItem(940, "dustAerTiny");
		tinyIgnis = generalItem.addOreDictItem(941, "dustIgnisTiny");
		tinyAqua = generalItem.addOreDictItem(942, "dustAquaTiny");
		tinyTerra = generalItem.addOreDictItem(943, "dustTerraTiny");
		tinyOrdo = generalItem.addOreDictItem(944, "dustOrdoTiny");
		tinyPerditio = generalItem.addOreDictItem(945, "dustPerditioTiny");
		tinyIron = generalItem.addOreDictItem(946, DUST + FE + TINY);
		tinyGold = generalItem.addOreDictItem(947, DUST + AU + TINY);
		tinyThaumium = generalItem.addOreDictItem(948, "dustThaumiumTiny");
		tinyVoidmetal = generalItem.addOreDictItem(949, "dustVoidTiny");
		tinySalisMundus = generalItem.addOreDictItem(950, salisPinch);
		tinyPrimalEssence = generalItem.addOreDictItem(951, "dustPrimalEssenceTiny");
		tinySulfur = generalItem.addOreDictItem(952, "dustSulfurTiny");

		tinyWardenicBinder = generalItem.addOreDictItem(960, "dustWardenicBinderTiny");

		seedExcubitura = generalItem.addOreDictItem(990, "seedExcubitura");
		seedCotton = generalItem.addOreDictItem(991, "seedCotton");
		seedThistle = generalItem.addOreDictItem(992, "seedThistle");
		seedShimmerleaf = generalItem.addOreDictItem(993, "seedShimmerleaf");
		seedCinderpearl = generalItem.addOreDictItem(994, "seedCinderpearl");
		seedShiverpearl = generalItem.addOreDictItem(995, "seedShiverpearl");
		seedStormpearl = generalItem.addOreDictItem(996, "seedStormpearl");
		seedStonepearl = generalItem.addOreDictItem(997, "seedStonepearl");

		//WARDENIC ARSENAL
		excubituraPetal = generalItem.addOreDictItem(1000, "excubituraPetal", "itemExcubituraPetal");
		excubituraPaste = generalItem.addOreDictItem(1001, "excubituraPaste", paste);
		excubituraFabric = generalItem.addOreDictItem(1002, "excubituraFabric", "itemExcubituraFabric");
		itemWardencloth = generalItem.addOreDictItem(1003, "wardencloth", wardencloth);

		excubituraOilUnproc = generalItem.addOreDictItem(1035, "excubituraOilUnproc", "itemExcubituraOilUnprocessed");
		excubituraOil = generalItem.addOreDictItem(1036, "excubituraOil", oilExcu);
		wardenicBronzeChain = generalItem.addOreDictItem(1037, "wardenicBronzeChain", chainWBronze);
		primalBronzeChain = generalItem.addOreDictItem(1038, "primalBronzeChain", chainPBronze);

		excubituraOilPure = generalItem.addOreDictItem(1070, "excubituraOilPure", "itemExcubituraOilPure");
		wardenicSteelChain = generalItem.addOreDictItem(1071, "wardenicSteelChain", "itemChainWardenicSteel");
		oiledSteelChain = generalItem.addOreDictItem(1072, "wardenicSteelChainOiled", "itemChainWardenicSteelOiled");
		detailedSteelPlate = generalItem.addOreDictItem(1073, "detailedSteelPlate", "itemPlateWardenicSteelDetailed");
		runicSteelPlate = generalItem.addOreDictItem(1074, "runicSteelPlate", "itemPlateWardenicSteelRunic");
		consecratedSteelPlate = generalItem.addOreDictItem(1075, "consecratedSteelPlate", "itemPlateWardenicSteelConsecrated");

		wardenicQuartzInf = generalItem.addOreDictItem(1105, "wardenicQuartzInf", "gemQuartzWardenicInfused");
		fittedCompositePlate = generalItem.addOreDictItem(1106, "fittedCompositePlate", "itemPlateWardenicCompositeFitted");
		detailedCompositePlate = generalItem.addOreDictItem(1107, "detailedCompositePlate", "itemPlateWardenicCompositeDetailed");
		runicCompositePlate = generalItem.addOreDictItem(1108, "runicCompositePlate", "itemPlateWardenicCompositeRunic");
		consecratedCompositePlate = generalItem.addOreDictItem(1109, "consecratedCompositePlate", "itemPlateWardenicCompositeConsecrated");
		primalCompositePlate = generalItem.addOreDictItem(1110, "primalCompositePlate", "itemPlateWardenicCompositePrimal");

		gemWardenicCrystalAwakened = generalItem.addOreDictItem(1140, "gemWardenicCrystalAwakened", "crystalWardenicAwakened");

		wardenicHardener = generalItem.addOreDictItem(1175, "itemWardenicHardener");

		aluDenseTemp = generalItem.addItem(30000, "tempAluDense");
	}

	public static void loadMetalItems() {
		ingotCopper = generalItem.addColorizedOreDictItem(5000, INGOT + CU, INGOT, COLOR_CU);
		ingotZinc = generalItem.addColorizedOreDictItem(5001, INGOT + ZN, INGOT, COLOR_ZN);
		ingotTin = generalItem.addColorizedOreDictItem(5002, INGOT + SN, INGOT, COLOR_SN);
		ingotNickel = generalItem.addColorizedOreDictItem(5003, INGOT + NI, INGOT, COLOR_NI);
		ingotSilver = generalItem.addColorizedOreDictItem(5004, INGOT + AG, INGOT, COLOR_AG);
		ingotLead = generalItem.addColorizedOreDictItem(5005, INGOT + PB, INGOT, COLOR_PB);
		ingotLutetium = generalItem.addColorizedOreDictItem(5006, INGOT + LU, INGOT, COLOR_LU, 2);
		ingotTungsten = generalItem.addColorizedOreDictItem(5007, INGOT + W, INGOT, COLOR_W, 1);
		ingotIridium = generalItem.addColorizedOreDictItem(5008, INGOT + IR, INGOT, COLOR_IR, 2);
		ingotBismuth = generalItem.addColorizedOreDictItem(5009, INGOT + BI, INGOT, COLOR_BI);
		ingotArsenic = generalItem.addColorizedOreDictItem(5010, INGOT + AS, INGOT, COLOR_AS);
		ingotAntimony = generalItem.addColorizedOreDictItem(5011, INGOT + SB, INGOT, COLOR_SB);
		ingotNeodymium = generalItem.addColorizedOreDictItem(5012, INGOT + ND, INGOT, COLOR_ND, 1);
		ingotOsmium = generalItem.addColorizedOreDictItem(5013, INGOT + OS, INGOT, COLOR_OS, 1);
		ingotPalladium = generalItem.addColorizedOreDictItem(5014, INGOT + PD, INGOT, COLOR_PD, 1);
		ingotAluminium = generalItem.addColorizedOreDictItem(5015, INGOT + AL, INGOT, COLOR_AL);

		ingotBrass = generalItem.addColorizedOreDictItem(5032, INGOT + CUZN, INGOT, COLOR_CUZN);
		ingotBronze = generalItem.addColorizedOreDictItem(5033, INGOT + CUSN, INGOT, COLOR_CUSN);
		ingotArsenicalBronze = generalItem.addOreDictItem(5034, "ingotArsenicalBronze");
		ingotAntimonialBronze = generalItem.addOreDictItem(5035, "ingotAntimonialBronze");
		ingotBismuthBronze = generalItem.addColorizedOreDictItem(5036, INGOT + CUBI, INGOT, COLOR_CUBI);
		ingotMithril = generalItem.addColorizedOreDictItem(5037, "ingotMithril", INGOT, COLOR_MTHR, 1, INGOT + MTHR);
		ingotAluminiumBronze = generalItem.addOreDictItem(5038, "ingotAluminiumBronze");
		ingotCupronickel = generalItem.addColorizedOreDictItem(5039, INGOT + CUNI, INGOT, COLOR_CUNI);
		ingotRiftishBronze = generalItem.addOreDictItem(5040, "ingotRiftishBronze", 1);
		ingotConstantan = generalItem.addOreDictItem(5041, "ingotConstantan");
		ingotInvar = generalItem.addOreDictItem(5042, "ingotInvar");
		ingotElectrum = generalItem.addOreDictItem(5043, "ingotElectrum");
		ingotWardenicMetal = generalItem.addOreDictItem(5044, "ingotWardenicMetal");
		ingotDullRedsolder = generalItem.addOreDictItem(5045, "ingotDullRedsolder");
		ingotRedsolder = generalItem.addOreDictItem(5046, "ingotRedsolder");

		ingotThaumicElectrum = generalItem.addOreDictItem(5064, "ingotThaumicElectrum", 1);
		ingotThaumicRiftishBronze = generalItem.addOreDictItem(5065, "ingotThaumicRiftishBronze", 1);
		ingotSteel = generalItem.addOreDictItem(5066, "ingotSteel", 1);
		ingotVoidbrass = generalItem.addOreDictItem(5067, "ingotVoidbrass", 1);
		ingotVoidsteel = generalItem.addOreDictItem(5068, "ingotVoidsteel", 1);
		ingotVoidtungsten = generalItem.addOreDictItem(5069, "ingotVoidtungsten", 2);
		ingotVoidcupronickel = generalItem.addOreDictItem(5070, "ingotVoidcupronickel", 1);

		ingotWardenicBronze = generalItem.addOreDictItem(5080, "ingotWardenicBronze");
		ingotWardenicSteel = generalItem.addOreDictItem(5081, "ingotWardenicSteel", 1);
		ingotWardenicRiftishBronze = generalItem.addOreDictItem(5082, "ingotWardenicRiftishBronze", 1);
		ingotWardenicComposite = generalItem.addOreDictItem(5083, "ingotWardenicComposite", 2);
		ingotRedsolderArcane = generalItem.addOreDictItem(5084, "ingotArcaneRedsolder");
		ingotRedbronze = generalItem.addOreDictItem(5085, "ingotRedbronze");
		ingotRedbronzeHardened = generalItem.addOreDictItem(5086, "ingotHardenedRedbronze", 1);
		ingotFluxsteel = generalItem.addOreDictItem(5087, "ingotFluxsteel", 1);
		ingotFluxedTungsten = generalItem.addOreDictItem(5088, "ingotFluxedTungsten", 2);
		ingotMagneoturgicComposite = generalItem.addOreDictItem(5089, "ingotMagneoturgicComposite", 2);
		ingotFluxedComposite = generalItem.addOreDictItem(5090, "ingotFluxedComposite", 2);
		ingotResonantFluxedComposite = generalItem.addOreDictItem(5091, "ingotResonantFluxedComposite", 3);
		//ingotEmpoweredVoidbrass
		//ingotCrimsonThaumium
		//ingotOccultVoidtungsten

		gemPyrope = generalItem.addOreDictItem(5096, "gemPyrope", 2);
		gemDioptase = generalItem.addOreDictItem(5097, "gemDioptase", 2);
		gemFluonicSapphire = generalItem.addOreDictItem(5098, "gemFluonicSapphire", 2);
		gemFluonicPyroptase = generalItem.addOreDictItem(5099, "gemFluonicPyroptase", 3);
		gemWardenicCrystal = generalItem.addOreDictItem(5100, "gemWardenicCrystal", 2);
		gemWardenicCrystalActivated = generalItem.addOreDictItem(5101, "gemWardenicCrystalActivated", 2);

		gemWardenicQuartz = generalItem.addOreDictItem(5108, "gemWardenicQuartz", 1);
		gemRedquartz = generalItem.addColorizedOreDictItem(5109, GEM + RQRZ, QUARTZ, COLOR_FLUX, 1);

		ingotLanthanides = generalItem.addOreDictItem(5112, "ingotXenotime", 1, "ingotXenotimeLanthanides");
		ingotXenotimeJunk = generalItem.addOreDictItem(5113, "ingotLanthanides", 1, "ingotXenotimeWaste");
		ingotIridosmium = generalItem.addOreDictItem(5114, "ingotIridosmium", 2);

		ingotThaumicBronze = generalItem.addOreDictItem(5120, "ingotThaumicBronze");
		ingotOsLu = generalItem.addOreDictItem(5121, "ingotOsmiumLutetium", 2);

		nuggetCopper = metalItem.addOreDictItem(5200, "nuggetCopper");
		nuggetZinc = metalItem.addOreDictItem(5201, "nuggetZinc");
		nuggetTin = metalItem.addOreDictItem(5202, "nuggetTin");
		nuggetNickel = metalItem.addOreDictItem(5203, "nuggetNickel");
		nuggetSilver = metalItem.addOreDictItem(5204, "nuggetSilver");
		nuggetLead = metalItem.addOreDictItem(5205, "nuggetLead");
		nuggetLutetium = metalItem.addOreDictItem(5206, "nuggetLutetium", 2);
		nuggetTungsten = metalItem.addOreDictItem(5207, "nuggetTungsten", 1);
		nuggetIridium = metalItem.addOreDictItem(5208, "nuggetIridium", 2);
		nuggetBismuth = metalItem.addOreDictItem(5209, "nuggetBismuth");
		nuggetArsenic = metalItem.addOreDictItem(5210, "nuggetArsenic");
		nuggetAntimony = metalItem.addOreDictItem(5211, "nuggetAntimony");
		nuggetNeodymium = metalItem.addOreDictItem(5212, "nuggetNeodymium", 1);
		nuggetOsmium = metalItem.addOreDictItem(5213, "nuggetOsmium", 1);
		nuggetPalladium = metalItem.addOreDictItem(5214, "nuggetPalladium", 1);
		nuggetAluminium = metalItem.addOreDictItem(5215, "nuggetAluminium");

		nuggetBrass = metalItem.addOreDictItem(5232, "nuggetBrass");
		nuggetBronze = metalItem.addOreDictItem(5233, nBronze);
		nuggetArsenicalBronze = metalItem.addOreDictItem(5234, "nuggetArsenicalBronze");
		nuggetAntimonialBronze = metalItem.addOreDictItem(5235, "nuggetAntimonialBronze");
		nuggetBismuthBronze = metalItem.addOreDictItem(5236, "nuggetBismuthBronze");
		nuggetMithril = metalItem.addOreDictItem(5237, "nuggetMithril", 1, "nuggetArsenoAntimonialBronze");
		nuggetAluminiumBronze = metalItem.addOreDictItem(5238, "nuggetAluminiumBronze");
		nuggetCupronickel = metalItem.addOreDictItem(5239, "nuggetCupronickel");
		nuggetRiftishBronze = metalItem.addOreDictItem(5240, "nuggetRiftishBronze", 1);
		nuggetConstantan = metalItem.addOreDictItem(5241, "nuggetConstantan");
		nuggetInvar = metalItem.addOreDictItem(5242, "nuggetInvar");
		nuggetElectrum = metalItem.addOreDictItem(5243, "nuggetElectrum");
		nuggetWardenicMetal = metalItem.addOreDictItem(5244, "nuggetWardenicMetal");
		nuggetDullRedsolder = metalItem.addOreDictItem(5245, "nuggetDullRedsolder");
		nuggetRedsolder = metalItem.addOreDictItem(5246, "nuggetRedsolder");

		nuggetThaumicElectrum = metalItem.addOreDictItem(5264, "nuggetThaumicElectrum", 1);
		nuggetThaumicRiftishBronze = metalItem.addOreDictItem(5265, "nuggetThaumicRiftishBronze", 1);
		nuggetSteel = metalItem.addOreDictItem(5266, "nuggetSteel", 1);
		nuggetVoidbrass = metalItem.addOreDictItem(5267, "nuggetVoidbrass", 1);
		nuggetVoidsteel = metalItem.addOreDictItem(5268, "nuggetVoidsteel", 1);
		nuggetVoidtungsten = metalItem.addOreDictItem(5269, "nuggetVoidtungsten", 2);

		nuggetWardenicBronze = metalItem.addOreDictItem(5280, "nuggetWardenicBronze");
		nuggetWardenicSteel = metalItem.addOreDictItem(5281, "nuggetWardenicSteel", 1);
		nuggetWardenicRiftishBronze = metalItem.addOreDictItem(5282, "nuggetWardenicRiftishBronze", 1);
		nuggetWardenicComposite = metalItem.addOreDictItem(5283, "nuggetWardenicComposite", 2);
		nuggetRedsolderArcane = metalItem.addOreDictItem(5284, "nuggetArcaneRedsolder");
		nuggetRedbronze = metalItem.addOreDictItem(5285, "nuggetRedbronze");
		nuggetRedbronzeHardened = metalItem.addOreDictItem(5286, "nuggetHardenedRedbronze", 1);
		nuggetFluxsteel = metalItem.addOreDictItem(5287, "nuggetFluxsteel", 1);
		nuggetFluxedTungsten = metalItem.addOreDictItem(5288, "nuggetFluxedTungsten", 2);
		nuggetMagneoturgicComposite = metalItem.addOreDictItem(5289, "nuggetMagneoturgicComposite", 2);
		nuggetFluxedComposite = metalItem.addOreDictItem(5290, "nuggetFluxedComposite", 2);
		nuggetResonantFluxedComposite = metalItem.addOreDictItem(5291, "nuggetResonantFluxedComposite", 3);

		shardPyrope = metalItem.addOreDictItem(5296, "shardPyrope", 2, "nuggetPyrope");
		shardDioptase = metalItem.addOreDictItem(5297, "shardDioptase", 2, "nuggetDioptase");
		shardFluonicSapphire = metalItem.addOreDictItem(5298, "shardFluonicSapphire", 2, "nuggetFluonicSapphire");
		shardFluonicPyroptase = metalItem.addOreDictItem(5299, "shardFluonicPyroptase", 3, "nuggetFluonicPyroptase");
		shardWardenicCrystal = metalItem.addOreDictItem(5300, "shardWardenicCrystal", 2, "nuggetWardenicCrystal");
		shardWardenicCrystalActivated = metalItem.addOreDictItem(5301, "shardWardenicCrystalActivated", 2, "nuggetWardenicCrystalActivated");

		shardWardenicQuartz = metalItem.addOreDictItem(5308, "shardWardenicQuartz", 1, "nuggetWardenicQuartz");
		shardRedquartz = metalItem.addOreDictItem(5309, "shardRedquartz", 1);

		nuggetLanthanides = metalItem.addOreDictItem(5312, "nuggetXenotime", 1, "nuggetXenotimeLanthanides");
		nuggetXenotimeJunk = metalItem.addOreDictItem(5313, "nuggetLanthanides", 1, "nuggetXenotimeWaste");
		nuggetIridosmium = metalItem.addOreDictItem(5314, "nuggetIridosmium", 2);

		nuggetThaumicBronze = metalItem.addOreDictItem(5320, "nuggetThaumicBronze");
		nuggetOsLu = metalItem.addOreDictItem(5321, "nuggetOsmiumLutetium", 2);

		dustCopper = metalItem.addOreDictItem(5400, "dustCopper");
		dustZinc = metalItem.addOreDictItem(5401, "dustZinc");
		dustTin = metalItem.addOreDictItem(5402, "dustTin");
		dustNickel = metalItem.addOreDictItem(5403, "dustNickel");
		dustSilver = metalItem.addOreDictItem(5404, "dustSilver");
		dustLead = metalItem.addOreDictItem(5405, "dustLead");
		dustLutetium = metalItem.addOreDictItem(5406, "dustLutetium", 2);
		dustTungsten = metalItem.addOreDictItem(5407, "dustTungsten", 1);
		dustIridium = metalItem.addOreDictItem(5408, "dustIridium", 2);
		dustBismuth = metalItem.addOreDictItem(5409, "dustBismuth");
		dustArsenic = metalItem.addOreDictItem(5410, "dustArsenic");
		dustAntimony = metalItem.addOreDictItem(5411, "dustAntimony");
		dustNeodymium = metalItem.addOreDictItem(5412, "dustNeodymium", 1);
		dustOsmium = metalItem.addOreDictItem(5413, "dustOsmium", 1);
		dustPalladium = metalItem.addOreDictItem(5414, "dustPalladium", 1);
		dustAluminium = metalItem.addOreDictItem(5415, "dustAluminium");

		dustBrass = metalItem.addOreDictItem(5432, "dustBrass");
		dustBronze = metalItem.addOreDictItem(5433, "dustBronze");
		dustArsenicalBronze = metalItem.addOreDictItem(5434, "dustArsenicalBronze");
		dustAntimonialBronze = metalItem.addOreDictItem(5435, "dustAntimonialBronze");
		dustBismuthBronze = metalItem.addOreDictItem(5436, "dustBismuthBronze");
		dustMithril = metalItem.addOreDictItem(5437, "dustMithril", 1, "dustArsenoAntimonialBronze");
		dustAluminiumBronze = metalItem.addOreDictItem(5438, "dustAluminiumBronze");
		dustCupronickel = metalItem.addOreDictItem(5439, "dustCupronickel");
		dustRiftishBronze = metalItem.addOreDictItem(5440, "dustRiftishBronze", 1);
		dustConstantan = metalItem.addOreDictItem(5441, "dustConstantan");
		dustInvar = metalItem.addOreDictItem(5442, "dustInvar");
		dustElectrum = metalItem.addOreDictItem(5443, "dustElectrum");
		dustWardenicMetal = metalItem.addOreDictItem(5444, "dustWardenicMetal");
		dustDullRedsolder = metalItem.addOreDictItem(5445, "dustDullRedsolder");
		dustRedsolder = metalItem.addOreDictItem(5446, "dustRedsolder");

		dustThaumicElectrum = metalItem.addOreDictItem(5464, "dustThaumicElectrum", 1);
		dustThaumicRiftishBronze = metalItem.addOreDictItem(5465, "dustThaumicRiftishBronze", 1);
		dustSteel = metalItem.addOreDictItem(5466, "dustSteel", 1);
		dustVoidbrass = metalItem.addOreDictItem(5467, "dustVoidbrass", 1);
		dustVoidsteel = metalItem.addOreDictItem(5468, "dustVoidsteel", 1);
		dustVoidtungsten = metalItem.addOreDictItem(5469, "dustVoidtungsten", 2);

		dustWardenicBronze = metalItem.addOreDictItem(5480, "dustWardenicBronze");
		dustWardenicSteel = metalItem.addOreDictItem(5481, "dustWardenicSteel", 1);
		dustWardenicRiftishBronze = metalItem.addOreDictItem(5482, "dustWardenicRiftishBronze", 1);
		dustWardenicComposite = metalItem.addOreDictItem(5483, "dustWardenicComposite", 2);

		dustRedsolderArcane = metalItem.addOreDictItem(5484, "dustArcaneRedsolder");
		dustRedbronze = metalItem.addOreDictItem(5485, "dustRedbronze");
		dustRedbronzeHardened = metalItem.addOreDictItem(5486, "dustHardenedRedbronze", 1);
		dustFluxsteel = metalItem.addOreDictItem(5487, "dustFluxsteel", 1);
		dustFluxedTungsten = metalItem.addOreDictItem(5488, "dustFluxedTungsten", 2);
		dustMagneoturgicComposite = metalItem.addOreDictItem(5489, "dustMagneoturgicComposite", 2);
		dustFluxedComposite = metalItem.addOreDictItem(5490, "dustFluxedComposite", 2);
		dustResonantFluxedComposite = metalItem.addOreDictItem(5491, "dustResonantFluxedComposite", 3);

		dustPyrope = metalItem.addOreDictItem(5496, "dustPyrope", 2);
		dustDioptase = metalItem.addOreDictItem(5497, "dustDioptase", 2);
		dustFluonicSapphire = metalItem.addOreDictItem(5498, "dustFluonicSapphire", 2);
		dustFluonicPyroptase = metalItem.addOreDictItem(5499, "dustFluonicPyroptase", 3);
		dustWardenicCrystal = metalItem.addOreDictItem(5500, "dustWardenicCrystal", 2);
		dustWardenicCrystalActivated = metalItem.addOreDictItem(5501, "dustWardenicCrystalActivated", 2);
		
		dustWardenicQuartz = metalItem.addOreDictItem(5508, "dustWardenicQuartz", 1);
		dustRedquartz = metalItem.addOreDictItem(5509, "dustRedquartz", 1);

		dustLanthanides = metalItem.addOreDictItem(5512, "dustXenotime", 1, "dustXenotimeLanthanides");
		dustXenotimeJunk = metalItem.addOreDictItem(5513, "dustLanthanides", 1, "dustXenotimeWaste");
		dustIridosmium = metalItem.addOreDictItem(5514, "dustIridosmium", 2);

		dustThaumicBronze = metalItem.addOreDictItem(5520, "dustThaumicBronze");
		dustOsLu = metalItem.addOreDictItem(5521, "dustOsmiumLutetium", 2);
		













/*
		tinyCopper = metalItem.addOreDictItem(500, "dustCopperTiny");
		tinyZinc = metalItem.addOreDictItem(501, "dustZincTiny");
		tinyTin = metalItem.addOreDictItem(502, "dustTinTiny");
		tinyNickel = metalItem.addOreDictItem(503, "dustNickelTiny");
		tinySilver = metalItem.addOreDictItem(504, "dustSilverTiny");
		tinyLead = metalItem.addOreDictItem(505, "dustLeadTiny");
		tinyLanthanides = metalItem.addOreDictItem(506, "dustXenotimeTiny", 1, "dustXenotimeLanthanidesTiny");
		tinyTungsten = metalItem.addOreDictItem(507, "dustTungstenTiny", 1);
		tinyIridium = metalItem.addOreDictItem(508, "dustIridiumTiny", 2);
		tinyBismuth = metalItem.addOreDictItem(509, "dustBismuthTiny");
		tinyArsenicalBronze = metalItem.addOreDictItem(510, "dustArsenicalBronzeTiny");
		tinyAntimonialBronze = metalItem.addOreDictItem(511, "dustAntimonialBronzeTiny");
		tinyPyrope = metalItem.addOreDictItem(512, "dustPyropeTiny", 2);
		tinyDioptase = metalItem.addOreDictItem(513, "dustDioptaseTiny", 2);
		tinyFluonicSapphire = metalItem.addOreDictItem(514, "dustFluonicSapphireTiny", 2);
		tinyOsmium = metalItem.addOreDictItem(515, "dustOsmiumTiny", 1);
		tinyNeodymium = metalItem.addOreDictItem(516, "dustNeodymiumTiny", 1);
		tinyLutetium = metalItem.addOreDictItem(517, "dustLutetiumTiny", 2);
		tinyPalladium = metalItem.addOreDictItem(518, "dustPalladiumTiny", 1);
		tinyIridosmium = metalItem.addOreDictItem(519, "dustIridosmiumTiny", 2);
		tinyAluminium = metalItem.addOreDictItem(520, "dustAluminiumTiny");
		tinyXenotimeJunk = metalItem.addOreDictItem(521, "dustLanthanidesTiny", 1, "dustXenotimeWasteTiny");

		tinyBrass = metalItem.addOreDictItem(525, "dustBrassTiny");
		tinyBronze = metalItem.addOreDictItem(526, "dustBronzeTiny");
		tinyBismuthBronze = metalItem.addOreDictItem(527, "dustBismuthBronzeTiny");
		tinyMithril = metalItem.addOreDictItem(528, "dustMithrilTiny", 1, "dustArsenoAntimonialBronzeTiny");
		tinyAluminiumBronze = metalItem.addOreDictItem(529, "dustAluminiumBronzeTiny");
		tinyCupronickel = metalItem.addOreDictItem(530, "dustCupronickelTiny");
		tinyRiftishBronze = metalItem.addOreDictItem(531, "dustRiftishBronzeTiny", 1);
		tinyConstantan = metalItem.addOreDictItem(532, "dustConstantanTiny");
		tinyInvar = metalItem.addOreDictItem(533, "dustInvarTiny");
		tinyElectrum = metalItem.addOreDictItem(534, "dustElectrumTiny");
		tinyWardenicMetal = metalItem.addOreDictItem(535, "dustWardenicMetalTiny");
		tinyDullRedsolder = metalItem.addOreDictItem(536, "dustDullRedsolderTiny");
		tinyRedsolder = metalItem.addOreDictItem(537, "dustRedsolderTiny");

		tinyThaumicBronze = metalItem.addOreDictItem(550, "dustThaumicBronzeTiny");
		tinyOsLu = metalItem.addOreDictItem(551, "dustOsmiumLutetiumTiny", 2);
		tinyFluonicPyroptase = metalItem.addOreDictItem(552, "dustFluonicPyroptaseTiny", 3);

		tinyArsenic = metalItem.addOreDictItem(555, "dustArsenicTiny");
		tinyAntimony = metalItem.addOreDictItem(556, "dustAntimonyTiny");

		tinyThaumicElectrum = metalItem.addOreDictItem(560, "dustThaumicElectrumTiny", 1);
		tinyThaumicRiftishBronze = metalItem.addOreDictItem(561, "dustThaumicRiftishBronzeTiny", 1);
		tinySteel = metalItem.addOreDictItem(562, "dustSteelTiny", 1);
		tinyVoidbrass = metalItem.addOreDictItem(563, "dustVoidbrassTiny", 1);
		tinyVoidsteel = metalItem.addOreDictItem(564, "dustVoidsteelTiny", 1);
		tinyVoidtungsten = metalItem.addOreDictItem(565, "dustVoidtungstenTiny", 2);

		tinyWardenicBronze = metalItem.addOreDictItem(570, "dustWardenicBronzeTiny");
		tinyWardenicSteel = metalItem.addOreDictItem(571, "dustWardenicSteelTiny", 1);
		tinyWardenicRiftishBronze = metalItem.addOreDictItem(572, "dustWardenicRiftishBronzeTiny", 1);
		tinyWardenicQuartz = metalItem.addOreDictItem(573, "dustWardenicQuartzTiny", 1);
		tinyWardenicCrystal = metalItem.addOreDictItem(574, "dustWardenicCrystalTiny", 2);
		tinyWardenicCrystalActivated = metalItem.addOreDictItem(575, "dustWardenicCrystalActivatedTiny", 2);
		tinyWardenicComposite = metalItem.addOreDictItem(576, "dustWardenicCompositeTiny", 2);

		tinyRedsolderArcane = metalItem.addOreDictItem(580, "dustArcaneRedsolderTiny");
		tinyRedbronze = metalItem.addOreDictItem(581, "dustRedbronzeTiny");
		tinyRedbronzeHardened = metalItem.addOreDictItem(582, "dustHardenedRedbronzeTiny", 1);
		tinyFluxsteel = metalItem.addOreDictItem(583, "dustFluxsteelTiny", 1);
		tinyRedquartz = metalItem.addOreDictItem(584, "dustRedquartzTiny", 1);
		tinyFluxedTungsten = metalItem.addOreDictItem(585, "dustFluxedTungstenTiny", 2);
		tinyMagneoturgicComposite = metalItem.addOreDictItem(586, "dustMagneoturgicCompositeTiny", 2);
		tinyFluxedComposite = metalItem.addOreDictItem(587, "dustFluxedCompositeTiny", 2);
		tinyResonantFluxedComposite = metalItem.addOreDictItem(588, "dustResonantFluxedCompositeTiny", 3);

		plateCopper = metalItem.addOreDictItem(600, "plateCopper");
		plateZinc = metalItem.addOreDictItem(601, "plateZinc");
		plateTin = metalItem.addOreDictItem(602, "plateTin");
		plateNickel = metalItem.addOreDictItem(603, "plateNickel");
		plateSilver = metalItem.addOreDictItem(604, "plateSilver");
		plateLead = metalItem.addOreDictItem(605, "plateLead");
		plateTungsten = metalItem.addOreDictItem(607, "plateTungsten", 1);
		plateIridium = metalItem.addOreDictItem(608, "plateIridium", 2);
		plateBismuth = metalItem.addOreDictItem(609, "plateBismuth");
		plateArsenicalBronze = metalItem.addOreDictItem(610, "plateArsenicalBronze");
		plateAntimonialBronze = metalItem.addOreDictItem(611, "plateAntimonialBronze");
		plateOsmium = metalItem.addOreDictItem(615, "plateOsmium", 1);
		plateNeodymium = metalItem.addOreDictItem(616, "plateNeodymium", 1);
		plateLutetium = metalItem.addOreDictItem(617, "plateLutetium", 2);
		platePalladium = metalItem.addOreDictItem(618, "platePalladium", 1);
		//plateIridosmium = metalItem.addOreDictItem(619, "plateIridosmium", 2);
		plateAluminium = metalItem.addOreDictItem(620, "plateAluminium");

		plateBrass = metalItem.addOreDictItem(625, "plateBrass");
		plateBronze = metalItem.addOreDictItem(626, "plateBronze");
		plateBismuthBronze = metalItem.addOreDictItem(627, "plateBismuthBronze");
		plateMithril = metalItem.addOreDictItem(628, "plateMithril", 1, "plateArsenoAntimonialBronze");
		plateAluminiumBronze = metalItem.addOreDictItem(629, "plateAluminiumBronze");
		plateCupronickel = metalItem.addOreDictItem(630, "plateCupronickel");
		plateRiftishBronze = metalItem.addOreDictItem(631, "plateRiftishBronze", 1);
		plateConstantan = metalItem.addOreDictItem(632, "plateConstantan");
		plateInvar = metalItem.addOreDictItem(633, "plateInvar");
		plateElectrum = metalItem.addOreDictItem(634, "plateElectrum");
		plateWardenicMetal = metalItem.addOreDictItem(635, "plateWardenicMetal");
		plateDullRedsolder = metalItem.addOreDictItem(636, "plateDullRedsolder");
		plateRedsolder = metalItem.addOreDictItem(637, "plateRedsolder");

		plateThaumicBronze = metalItem.addOreDictItem(650, "plateThaumicBronze");
		plateOsLu = metalItem.addOreDictItem(651, "plateOsmiumLutetium", 2);

		plateThaumicElectrum = metalItem.addOreDictItem(660, "plateThaumicElectrum", 1);
		plateThaumicRiftishBronze = metalItem.addOreDictItem(661, "plateThaumicRiftishBronze", 1);
		plateSteel = metalItem.addOreDictItem(662, "plateSteel", 1);
		plateVoidbrass = metalItem.addOreDictItem(663, "plateVoidbrass", 1);
		plateVoidsteel = metalItem.addOreDictItem(664, "plateVoidsteel", 1);
		plateVoidtungsten = metalItem.addOreDictItem(665, "plateVoidtungsten", 2);

		plateWardenicBronze = metalItem.addOreDictItem(670, "plateWardenicBronze");
		plateWardenicSteel = metalItem.addOreDictItem(671, "plateWardenicSteel", 1);
		plateWardenicRiftishBronze = metalItem.addOreDictItem(672, "plateWardenicRiftishBronze", 1);
		plateWardenicComposite = metalItem.addOreDictItem(676, "plateWardenicComposite",2);

		plateRedsolderArcane = metalItem.addOreDictItem(680, "plateArcaneRedsolder");
		plateRedbronze = metalItem.addOreDictItem(681, "plateRedbronze");
		plateRedbronzeHardened = metalItem.addOreDictItem(682, "plateHardenedRedbronze", 1);
		plateFluxsteel = metalItem.addOreDictItem(683, "plateFluxsteel", 1);
		plateFluxedTungsten = metalItem.addOreDictItem(685, "plateFluxedTungsten", 2);
		plateMagneoturgicComposite = metalItem.addOreDictItem(686, "plateMagneoturgicComposite", 2);
		plateFluxedComposite = metalItem.addOreDictItem(687, "plateFluxedComposite", 2);
		plateResonantFluxedComposite = metalItem.addOreDictItem(688, "plateResonantFluxedComposite", 3);*/

		/*rawBrass = metalItem.addOreDictItem(730, "ingotBrassRaw");
		rawBronze = metalItem.addOreDictItem(731, "ingotBronzeRaw");
		rawBismuthBronze = metalItem.addOreDictItem(732, "ingotBismuthBronzeRaw");
		rawMithril = metalItem.addOreDictItem(733, "ingotMithrilRaw", 1, "ingotArsenoAntimonialBronzeRaw");
		rawAluminiumBronze = metalItem.addOreDictItem(734, "ingotAluminiumBronzeRaw");
		rawCupronickel = metalItem.addOreDictItem(735, "ingotCupronickelRaw");
		rawRiftishBronze = metalItem.addOreDictItem(736, "ingotRiftishBronzeRaw");
		rawConstantan = metalItem.addOreDictItem(737, "ingotConstantanRaw");
		rawInvar = metalItem.addOreDictItem(738, "ingotInvarRaw");
		rawElectrum = metalItem.addOreDictItem(739, "ingotElectrumRaw");
		rawWardenicMetal = metalItem.addOreDictItem(740, "ingotWardenicMetalRaw");
		rawRedsolder = metalItem.addOreDictItem(741, "ingotRedsolderRaw");

		rawThaumicBronze = metalItem.addOreDictItem(755, "ingotThaumicBronzeRaw");
		rawOsLu = metalItem.addOreDictItem(756, "ingotOsmiumLutetiumRaw", 2);
		blendFluonicPyrotase = metalItem.addOreDictItem(757, "blendFluonicPyroptase", 2, "itemBlendFluonicPyroptase");

		rawWardenicComposite = metalItem.addOreDictItem(760, "ingotWardenicCompositeRaw", 2);
		rawMagneoturgicComposite = metalItem.addOreDictItem(761, "ingotMagneoturgicCompositeRaw", 2);*/





	}

	public static void loadMaterials() {
		ItemArmorFluxGear.addArmorMaterial(matPrimal, 25, new int[] {1, 3, 2, 1}, 25);
		ItemArmorFluxGear.addArmorMaterial(matBronzeChain, 20, new int[] {2, 5, 4, 2}, 25);
		ItemArmorFluxGear.addArmorMaterial(matWardencloth, 30, new int[] {1, 3, 2, 1}, 30);
		ItemArmorFluxGear.addArmorMaterial(matWardenicChain, 15, new int[] {2, 5, 4, 1}, 25);
		ItemArmorFluxGear.addArmorMaterial(matWardenicSteel, 30, new int[] {3, 7, 5, 2}, 20);
		ItemArmorFluxGear.addArmorMaterial(matWardenicComposite, 45, new int[] {3, 7, 6, 3}, 25);

		ItemArmorInfusableThaumRev.materialData.put(matPrimal, new ThaumRevMaterialDataSet().setUnlocName(".primal.", new String[] {"goggles", "robes", "pants", "boots"}).setIcon("primal", new String[] {"Goggles", "Robes", "Pants", "Boots"}).setRepair("ingotGold", "itemEnchantedFabric", "itemEnchantedFabric", "itemEnchantedFabric").setColor(0x6A3880).setTexture("primal").setRarity(EnumRarity.rare, EnumRarity.uncommon, EnumRarity.uncommon, EnumRarity.uncommon).setRegName("Primal", new String[] {"Goggles", "Robes", "Pants", "Boots"}).setNonColorized(0));
		ItemArmorInfusableThaumRev.materialData.put(matBronzeChain, new ThaumRevMaterialDataSet().setUnlocName(".bronzeChain.", new String[] {"helmet", "mail", "greaves", "boots"}).setIcon("bronzeChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}).setRepair("itemThaumicBronzeChain").setTexture("bronzeChain").setRarity(EnumRarity.uncommon).setRegName("BronzeChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}));
		ItemArmorInfusableThaumRev.materialData.put(matWardencloth, new ThaumRevMaterialDataSet().setUnlocName(".wardencloth.", new String[] {"skullcap", "tunic", "pants", "boots"}).setIcon("wardencloth", new String[] {"Skullcap", "Tunic", "Pants", "Boots"}).setRepair("itemWardencloth").setColor(COLOR_TEAL_MAGNEQUAZAR).setTexture("wardencloth").setRarity(EnumRarity.uncommon).setRegName("Wardencloth", new String[] {"Skullcap", "Tunic", "Pants", "Boots"}));
		ItemArmorInfusableThaumRev.materialData.put(matWardenicChain, new ThaumRevMaterialDataSet().setUnlocName(".wardenicChain.", new String[] {"helmet", "mail", "greaves", "boots"}).setIcon("wardenicChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}).setRepair("itemChainWardenBronze").setTexture("wardenicChain").setRarity(EnumRarity.uncommon).setRegName("WardenicChain", new String[] {"Helmet", "mail", "Greaves", "Boots"}));
		ItemArmorInfusableThaumRev.materialData.put(matWardenicSteel, new ThaumRevMaterialDataSet().setUnlocName(".wardenicSteel.", new String[] {"helmet", "chestplate", "greaves", "boots"}).setIcon("wardenicSteel", new String[] {"Helmet", "Chestplate", "Greaves", "Boots"}).setRepair("itemPlateWardenicSteelRunic").setTexture("wardenSteel").setRarity(EnumRarity.uncommon).setRegName("WardenicSteel", new String[] {"Helmet", "Chestplate", "Greaves", "Boots"}));
		ItemArmorInfusableThaumRev.materialData.put(matWardenicComposite, new ThaumRevMaterialDataSet().setUnlocName(".wardenicComposite.", new String[] {"helmet", "chestplate", "greaves", "boots"}).setIcon("wardenicComposite", new String[] {"Helmet", "Chestplate", "Greaves", "Boots"}).setRepair("itemPlateWardenicCompositeConsecrated").setTexture("wardenComposite").setRarity(EnumRarity.rare).setRegName("WardenicComposite", new String[] {"Helmet", "Chestplate", "Greaves", "Boots"}));
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

		wardenicCompositeHelmet = new ItemArmorInfusableThaumRev(matWardenicComposite, 2, 0).setGoggles();
		wardenicCompositeChestplate = new ItemArmorInfusableThaumRev(matWardenicComposite, 2, 1);
		wardenicCompositeGreaves = new ItemArmorInfusableThaumRev(matWardenicComposite, 2, 2);
		wardenicCompositeBoots = new ItemArmorInfusableThaumRev(matWardenicComposite, 2, 3);
	}

	public static void loadTools() {}

	public static void loadBaubles() {
		wardenAmulet = thaumicBauble.addMetaBauble(0, "wardenAmulet", 2, new DefaultBaubleData(BaubleType.AMULET));
		loveRing = thaumicBauble.addMetaBauble(1, "loveRing", 3, new DefaultBaubleData(BaubleType.RING).setUnequip(false));
	}

	public static void loadInit() {
		RecipeHelper.addCluster("oreZinc", clusterZinc);
		RecipeHelper.addCluster("oreAluminium", clusterAluminium);
		RecipeHelper.addCluster("oreAluminum", clusterAluminium); //Why do we spell it wrong in the U.S.?
		RecipeHelper.addCluster("oreNickel", clusterNickel);
		RecipeHelper.addCluster("oreXenotime", clusterXenotime);
		RecipeHelper.addCluster("oreTungsten", clusterTungsten);
		RecipeHelper.addCluster("oreIridosmium", clusterIridosmium);
		RecipeHelper.addCluster("oreBismuth", clusterBismuth);
		RecipeHelper.addCluster("oreTennantite", clusterTennantite);
		RecipeHelper.addCluster("oreTetrahedrite", clusterTetrahedrite);
	}

	public static void aluminiumArc() {
		registerOreDict(new ItemStack(Items.clay_ball), "itemClay");
		registerOreDict(new ItemStack(Items.glass_bottle), "itemBottle");
		registerOreDict(new ItemStack(Items.quartz), "gemQuartz");
		registerOreDict(itemAlumentum, "itemAlumentum");
		registerOreDict(itemNitor, "itemNitor");
		registerOreDict(dustSalisMundus, salisMundus);
		registerOreDict(itemEnchantedFabric, enchSilk);
		registerOreDict(itemQuicksilverDrop, nHg);
		registerOreDict(itemShardBalanced, shardBalanced);
	}

	public static void addLoot() {
		generalItem.addContainer(1001, new ItemStack(Items.bowl));
		generalItem.addContainer(1036, itemPhial);
		generalItem.addContainer(1070, itemPhial);

		generalItem.addLoot(775, ingotThaumicBronze, thaumicSlag);
		generalItem.addLoot(776, ingotOsLu, fluonicSlag);
	}

	public static void loadRecipes() {
		RecipeHelper.addSmelting(oreChalcocite, ingotCopper, 0.85F);
		RecipeHelper.addSmelting(oreSphalerite, ingotZinc, 0.95F);
		RecipeHelper.addSmelting(oreCassiterite, ingotTin, 0.975F);
		RecipeHelper.addSmelting(oreMillerite, ingotNickel, 1.2F);
		RecipeHelper.addSmelting(oreNativeSilver, ingotSilver, 1.5F);
		RecipeHelper.addSmelting(oreGalena, ingotLead, 1.0F);
		RecipeHelper.addSmelting(oreXenotime, ingotLanthanides, 1.0F); //Rare Earths mock your primitive furnace-based attempts at separating them, but will smelt.
		//Tungsten laughs at your mundane smelting
		//Refractory Alloys mock your simple furnace
		RecipeHelper.addSmelting(oreBismuthinite, ingotBismuth, 1.15F);
		RecipeHelper.addSmelting(oreTennantite, ingotArsenicalBronze, 1.35F);
		RecipeHelper.addSmelting(oreTetrahedrite, ingotAntimonialBronze, 1.35F);
		RecipeHelper.addSmelting(orePyrope, gemPyrope, 1.0F);
		RecipeHelper.addSmelting(oreDioptase, gemDioptase, 1.0F);
		RecipeHelper.addSmelting(oreFluonicSapphire, gemFluonicSapphire, 1.0F);

		recipeQuartzBlock = RecipeHelper.addSquareRecipe(blockWardenicQuartz, "gemWardenicQuartz");
		recipeQuartzChiseled = RecipeHelper.addShapedRecipe(blockWardenicQuartzChiseled, "X", "X", 'X', "slabWardenicQuartz");
		recipeQuartzPillar = RecipeHelper.addShapedRecipe(ItemHelper.cloneStack(blockWardenicQuartzPillar, 2), "X", "X", 'X', "blockWardenicQuartz");
		recipeQuartzDeblock = RecipeHelper.addDeblockingRecipe(gemWardenicQuartz, blockWardenicQuartz);

		recipeQuartzResetChiseled = RecipeHelper.addShapelessRecipe(blockWardenicQuartz, blockWardenicQuartzChiseled);
		recipeQuartzResetPillar = RecipeHelper.addShapelessRecipe(blockWardenicQuartz, blockWardenicQuartzPillar);

		recipeWardsidianSlab = RecipeHelper.addSlabRecipe(slabWardenicObsidian, "blockWardenicObsidian");
		recipeEldritchSlab = RecipeHelper.addSlabRecipe(slabEldritch, "blockEldritchStone");
		recipeQuartzSlab = RecipeHelper.addSlabRecipe(slabWardenicQuartz, "blockWardenicQuartz");

		recipeWardsidianDeslab = RecipeHelper.addDeslabingRecipe(wardenicObsidian, "slabWardenicObsidian");
		recipeEldritchDeslab = RecipeHelper.addDeslabingRecipe(eldritchStone, "slabEldritchStone");
		recipeQuartzDeslab = RecipeHelper.addDeslabingRecipe(gemWardenicQuartz, "slabWardenicQuartz");

		recipeQuartzStair = RecipeHelper.addStairRecipe(stairsWardenicQuartz, "blockWardenicQuartz");
		recipeQuartzDestair = RecipeHelper.addDestairRecipe(blockWardenicQuartz, stairsWardenicQuartz);


		recipeBismuthBronze = new ShapelessOreRecipe[2];
		recDustBismuthBronze = new ShapelessOreRecipe[2];

		recipeBrass = generateShapelessSizedOreRecipe(rawBrass, 0, iCu, iCu, iCu, "ingotZinc");
		recipeBronze = generateShapelessSizedOreRecipe(rawBronze, 0, iCu, iCu, iCu, iSn);
		recipeBismuthBronze[0] = generateShapelessSizedOreRecipe(rawBismuthBronze, 0, iBs, iBs, iBs, iBs, iCu, iCu, iCu, "ingotBismuth");
		recipeBismuthBronze[1] = generateShapelessSizedOreRecipe(rawBismuthBronze, 0, iCu, iCu, iCu, iCu, iCu, iCu, "ingotZinc", "ingotBismuth");
		recipeMithril = generateShapelessSizedOreRecipe(rawMithril, 0, "ingotArsenicalBronze", "ingotAntimonialBronze");
		if (ThaumRevConfig.backwardsAlBronze) {
			recipeAlBronze = generateShapelessRecipe(rawAluminiumBronze, 0, iCu, "ingotAluminium", "ingotAluminium", "ingotAluminium");
		} else {
			recipeAlBronze = generateShapelessRecipe(rawAluminiumBronze, 0, iCu, iCu, iCu, "ingotAluminium");
		}
		recipeCupronickel = generateShapelessSizedOreRecipe(rawCupronickel, 0, iCu, iCu, iCu, "ingotNickel");
		recipeRiftishBronze = generateShapelessSizedOreRecipe(rawRiftishBronze, 0, iMth, iMth, iMth, iMth, "ingotBismuthBronze", "ingotBismuthBronze", "ingotBronze", "ingotCupronickel", "ingotAluminiumBronze");
		recipeConstantan = generateShapelessSizedOreRecipe(rawConstantan, 0, iCu, "ingotNickel");
		recipeInvar = generateShapelessSizedOreRecipe(rawInvar, 0, "ingotIron", "ingotIron", "ingotNickel");
		recipeElectrum = generateShapelessSizedOreRecipe(rawElectrum, 0, "ingotGold", "ingotSilver");

		recDustBrass = generateShapelessSizedOreRecipe(dustBrass, 0, dCu, dCu, dCu, "dustZinc");
		recDustBronze = generateShapelessSizedOreRecipe(dustBronze, 0, dCu, dCu, dCu, "dustTin");
		recDustBismuthBronze[0] = generateShapelessSizedOreRecipe(dustBismuthBronze, 0, dBs, dBs, dBs, dBs, dCu, dCu, dCu, "dustBismuth");
		recDustBismuthBronze[1] = generateShapelessSizedOreRecipe(dustBismuthBronze, 0, dCu, dCu, dCu, dCu, dCu, dCu, "dustZinc", "dustBismuth");
		recDustMithril = generateShapelessSizedOreRecipe(dustMithril, 0, "dustArsenicalBronze", "dustAntimonialBronze");
		if (ThaumRevConfig.backwardsAlBronze) {
			recDustAlBronze = generateShapelessRecipe(dustAluminiumBronze, 0, dCu, "dustAluminium", "dustAluminium", "dustAluminium");
		} else {
			recDustAlBronze = generateShapelessRecipe(dustAluminiumBronze, 0, dCu, dCu, dCu, "dustAluminium");
		}
		recDustCupronickel = generateShapelessSizedOreRecipe(dustCupronickel, 0, dCu, dCu, dCu, "dustNickel");
		recDustRiftishBronze = generateShapelessSizedOreRecipe(dustRiftishBronze, 0, dMth, dMth, dMth, dMth, "dustBismuthBronze", "dustBismuthBronze", "dustBronze", "dustCupronickel", "dustAluminiumBronze");
		recDustConstantan = generateShapelessSizedOreRecipe(dustConstantan, 0, dCu, "dustNickel");
		recDustInvar = generateShapelessSizedOreRecipe(dustInvar, 0, "ingotIron", "ingotIron", "ingotNickel");
		recDustElectrum = generateShapelessSizedOreRecipe(dustElectrum, 0, "dustGold", "dustSilver");

		recipeWardenicMetal = addShapelessSizedOreRecipe(rawWardenicMetal, 0, "ingotThaumium", "ingotThaumium", "ingotThaumium", "ingotPalladium", "dustWardenicBinder", "ingotBrass", "ingotElectrum", "ingotZinc", "quicksilver");

		recipeCottonFiber = addShapelessRecipe(cottonFiber, cotton);
		recipeCottonFabric = addStorageRecipe(cottonFabric, cottonFiber);

		addStorageRecipe(ingotCopper, "nuggetCopper");
		addStorageRecipe(ingotZinc, "nuggetZinc");
		addStorageRecipe(ingotTin, "nuggetTin");
		addStorageRecipe(ingotNickel, "nuggetNickel");
		addStorageRecipe(ingotSilver, "nuggetSilver");
		addStorageRecipe(ingotLead, "nuggetLead");
		addStorageRecipe(ingotLanthanides, "nuggetXenotimeLanthanides");
		addStorageRecipe(ingotTungsten, "nuggetTungsten");
		addStorageRecipe(ingotIridium, "nuggetIridium");
		addStorageRecipe(ingotBismuth, "nuggetBismuth");
		addStorageRecipe(ingotArsenicalBronze, "nuggetArsenicalBronze");
		addStorageRecipe(ingotAntimonialBronze, "nuggetAntimonialBronze");
		addStorageRecipe(gemPyrope, "nuggetPyrope");
		addStorageRecipe(gemDioptase, "nuggetDioptase");
		addStorageRecipe(gemFluonicSapphire, "nuggetFluonicSapphire");
		addStorageRecipe(ingotOsmium, "nuggetOsmium");
		addStorageRecipe(ingotNeodymium, "nuggetNeodymium");
		addStorageRecipe(ingotLutetium, "nuggetLutetium");
		addStorageRecipe(ingotPalladium, "nuggetPalladium");
		addStorageRecipe(ingotIridosmium, "nuggetIridosmium");
		addStorageRecipe(ingotAluminium, "nuggetAluminium");
		addStorageRecipe(ingotXenotimeJunk, "nuggetXenotimeWaste");

		addReverseStorageRecipe(nuggetCopper, "ingotCopper");
		addReverseStorageRecipe(nuggetZinc, "ingotZinc");
		addReverseStorageRecipe(nuggetTin, "ingotTin");
		addReverseStorageRecipe(nuggetNickel, "ingotNickel");
		addReverseStorageRecipe(nuggetSilver, "ingotSilver");
		addReverseStorageRecipe(nuggetLead, "ingotLead");
		addReverseStorageRecipe(nuggetLanthanides, "ingotXenotimeLanthanides");
		addReverseStorageRecipe(nuggetTungsten, "ingotTungsten");
		addReverseStorageRecipe(nuggetIridium, "ingotIridium");
		addReverseStorageRecipe(nuggetBismuth, "ingotBismuth");
		addReverseStorageRecipe(nuggetArsenicalBronze, "ingotArsenicalBronze");
		addReverseStorageRecipe(nuggetAntimonialBronze, "ingotAntimonialBronze");
		addReverseStorageRecipe(shardPyrope, "gemPyrope");
		addReverseStorageRecipe(shardDioptase, "gemDioptase");
		addReverseStorageRecipe(shardFluonicSapphire, "gemFluonicSapphire");
		addReverseStorageRecipe(nuggetOsmium, "ingotOsmium");
		addReverseStorageRecipe(nuggetNeodymium, "ingotNeodymium");
		addReverseStorageRecipe(nuggetLutetium, "ingotLutetium");
		addReverseStorageRecipe(nuggetPalladium, "ingotPalladium");
		addReverseStorageRecipe(nuggetIridosmium, "ingotIridosmium");
		addReverseStorageRecipe(nuggetAluminium, "ingotAluminium");
		addReverseStorageRecipe(nuggetXenotimeJunk, "ingotXenotimeWaste");

		addSmelting(dustCopper, ingotCopper);
		addSmelting(dustZinc, ingotZinc);
		addSmelting(dustTin, ingotTin);
		addSmelting(dustNickel, ingotNickel);
		addSmelting(dustSilver, ingotSilver);
		addSmelting(dustLead, ingotLead);
		addSmelting(dustLanthanides, ingotLanthanides);
		addSmelting(dustBismuth, ingotBismuth);
		addSmelting(dustArsenicalBronze, ingotArsenicalBronze);
		addSmelting(dustAntimonialBronze, ingotAntimonialBronze);
		addSmelting(dustNeodymium, ingotNeodymium);
		addSmelting(dustLutetium, ingotLutetium);
		addSmelting(dustPalladium, ingotPalladium);
		addSmelting(dustAluminium, ingotAluminium);
		addSmelting(dustXenotimeJunk, ingotXenotimeJunk);

		addStorageRecipe(dustCopper, "dustCopperTiny");
		addStorageRecipe(dustZinc, "dustZincTiny");
		addStorageRecipe(dustTin, "dustTinTiny");
		addStorageRecipe(dustNickel, "dustNickelTiny");
		addStorageRecipe(dustSilver, "dustSilverTiny");
		addStorageRecipe(dustLead, "dustLeadTiny");
		addStorageRecipe(dustLanthanides, "dustXenotimeLanthanidesTiny");
		addStorageRecipe(dustTungsten, "dustTungstenTiny");
		addStorageRecipe(dustIridium, "dustIridiumTiny");
		addStorageRecipe(dustBismuth, "dustBismuthTiny");
		addStorageRecipe(dustArsenicalBronze, "dustArsenicalBronzeTiny");
		addStorageRecipe(dustAntimonialBronze, "dustAntimonialBronzeTiny");
		addStorageRecipe(dustPyrope, "dustPyropeTiny");
		addStorageRecipe(dustDioptase, "dustDioptaseTiny");
		addStorageRecipe(dustFluonicSapphire, "dustFluonicSapphireTiny");
		addStorageRecipe(dustOsmium, "dustOsmiumTiny");
		addStorageRecipe(dustNeodymium, "dustNeodymiumTiny");
		addStorageRecipe(dustLutetium, "dustLutetiumTiny");
		addStorageRecipe(dustPalladium, "dustPalladiumTiny");
		addStorageRecipe(dustIridosmium, "dustIridosmiumTiny");
		addStorageRecipe(dustAluminium, "dustAluminiumTiny");
		addStorageRecipe(dustXenotimeJunk, "dustXenotimeWasteTiny");

		addReverseStorageRecipe(tinyCopper, "dustCopper");
		addReverseStorageRecipe(tinyZinc, "dustZinc");
		addReverseStorageRecipe(tinyTin, "dustTin");
		addReverseStorageRecipe(tinyNickel, "dustNickel");
		addReverseStorageRecipe(tinySilver, "dustSilver");
		addReverseStorageRecipe(tinyLead, "dustLead");
		addReverseStorageRecipe(tinyLanthanides, "dustXenotimeLanthanides");
		addReverseStorageRecipe(tinyTungsten, "dustTungsten");
		addReverseStorageRecipe(tinyIridium, "dustIridium");
		addReverseStorageRecipe(tinyBismuth, "dustBismuth");
		addReverseStorageRecipe(tinyArsenicalBronze, "dustArsenicalBronze");
		addReverseStorageRecipe(tinyAntimonialBronze, "dustAntimonialBronze");
		addReverseStorageRecipe(tinyPyrope, "gemPyrope");
		addReverseStorageRecipe(tinyDioptase, "gemDioptase");
		addReverseStorageRecipe(tinyFluonicSapphire, "gemFluonicSapphire");
		addReverseStorageRecipe(tinyOsmium, "dustOsmium");
		addReverseStorageRecipe(tinyNeodymium, "dustNeodymium");
		addReverseStorageRecipe(tinyLutetium, "dustLutetium");
		addReverseStorageRecipe(tinyPalladium, "dustPalladium");
		addReverseStorageRecipe(tinyIridosmium, "dustIridosmium");
		addReverseStorageRecipe(tinyAluminium, "dustAluminium");
		addReverseStorageRecipe(tinyXenotimeJunk, "dustXenotimeWaste");

		if (ThaumRevConfig.enableBrass) {
			addRecipe(recipeBrass);
			addRecipe(recDustBrass);
			addSmelting(rawBrass, ingotBrass, 1.2F);
			addStorageRecipe(ingotBrass, "nuggetBrass");
			addReverseStorageRecipe(nuggetBrass, "ingotBrass");
			addStorageRecipe(dustBrass, "dustBrassTiny");
			addReverseStorageRecipe(tinyBrass, "dustBrass");
			addSmelting(dustBrass, ingotBrass);
		}
		if (ThaumRevConfig.enableBronze) {
			addRecipe(recipeBronze);
			addRecipe(recDustBronze);
			addSmelting(rawBronze, ingotBronze, 1.05F);
			addStorageRecipe(ingotBronze, "nuggetBronze");
			addReverseStorageRecipe(nuggetBronze, "ingotBronze");
			addStorageRecipe(dustBronze, "dustBronzeTiny");
			addReverseStorageRecipe(tinyBronze, "dustBronze");
			addSmelting(dustBronze, ingotBronze);
		}
		if (ThaumRevConfig.enableBismuthBronze) {
			addRecipe(recipeBismuthBronze);
			addRecipe(recDustBismuthBronze);
			addSmelting(rawBismuthBronze, ingotBismuthBronze, 1.35F);
			addStorageRecipe(ingotBismuthBronze, "nuggetBismuthBronze");
			addReverseStorageRecipe(nuggetBismuthBronze, "ingotBismuthBronze");
			addStorageRecipe(dustBismuthBronze, "dustBismuthBronzeTiny");
			addReverseStorageRecipe(tinyBismuthBronze, "dustBismuthBronze");
			addSmelting(dustBismuthBronze, ingotBismuthBronze);
		}
		if (ThaumRevConfig.enableMithril) {
			addRecipe(recipeMithril);
			addRecipe(recDustMithril);
			addSmelting(rawMithril, ingotMithril, 1.65F);
			addStorageRecipe(ingotMithril, "nuggetArsenoAntimonialBronze");
			addReverseStorageRecipe(nuggetMithril, "ingotArsenoAntimonialBronze");
			addStorageRecipe(dustMithril, "dustArsenoAntimonialBronzeTiny");
			addReverseStorageRecipe(tinyMithril, "dustArsenoAntimonialBronze");
			addSmelting(dustMithril, ingotMithril);
		}
		if (ThaumRevConfig.enableAlBronze) {
			addRecipe(recipeAlBronze);
			addRecipe(recDustAlBronze);
			addSmelting(rawAluminiumBronze, ingotAluminiumBronze, 1.15F);
			addStorageRecipe(ingotAluminiumBronze, "nuggetAluminiumBronze");
			addReverseStorageRecipe(nuggetAluminiumBronze, "ingotAluminiumBronze");
			addStorageRecipe(dustAluminiumBronze, "dustAluminiumBronzeTiny");
			addReverseStorageRecipe(tinyAluminiumBronze, "dustAluminiumBronze");
			addSmelting(dustAluminiumBronze, ingotAluminiumBronze);
		}
		if (ThaumRevConfig.enableCupronickel) {
			addRecipe(recipeCupronickel);
			addRecipe(recDustCupronickel);
			addSmelting(rawCupronickel, ingotCupronickel, 1.15F);
			addStorageRecipe(ingotCupronickel, "nuggetCupronickel");
			addReverseStorageRecipe(nuggetCupronickel, "ingotCupronickel");
			addStorageRecipe(dustCupronickel, "dustCupronickelTiny");
			addReverseStorageRecipe(tinyCupronickel, "dustCupronickel");
			addSmelting(dustCupronickel, ingotCupronickel);
		}
		if (ThaumRevConfig.enableRiftishBronze) {
			addRecipe(recipeRiftishBronze);
			addRecipe(recDustRiftishBronze);
			addSmelting(rawRiftishBronze, ingotRiftishBronze, 1.85F);
			addStorageRecipe(ingotRiftishBronze, "nuggetRiftishBronze");
			addReverseStorageRecipe(nuggetRiftishBronze, "ingotRiftishBronze");
			addStorageRecipe(dustRiftishBronze, "dustRiftishBronzeTiny");
			addReverseStorageRecipe(tinyRiftishBronze, "dustRiftishBronze");
			addSmelting(dustRiftishBronze, ingotRiftishBronze);
		}
		if (ThaumRevConfig.enableConstantan) {
			addRecipe(recipeConstantan);
			addRecipe(recDustConstantan);
			addSmelting(rawConstantan, ingotConstantan, 1.15F);
			addStorageRecipe(ingotConstantan, "nuggetConstantan");
			addReverseStorageRecipe(nuggetConstantan, "ingotConstantan");
			addStorageRecipe(dustConstantan, "dustConstantanTiny");
			addReverseStorageRecipe(tinyConstantan, "dustConstantan");
			addSmelting(dustConstantan, ingotConstantan);
		}
		if (ThaumRevConfig.enableInvar) {
			addRecipe(recipeInvar);
			addRecipe(recDustInvar);
			addSmelting(rawInvar, ingotInvar, 1.15F);
			addStorageRecipe(ingotInvar, "nuggetInvar");
			addReverseStorageRecipe(nuggetInvar, "ingotInvar");
			addStorageRecipe(dustInvar, "dustInvarTiny");
			addReverseStorageRecipe(tinyInvar, "dustInvar");
			addSmelting(dustInvar, ingotInvar);
		}
		if (ThaumRevConfig.enableElectrum) {
			addRecipe(recipeElectrum);
			addRecipe(recDustElectrum);
			addSmelting(rawElectrum, ingotElectrum, 1.5F);
			addStorageRecipe(ingotElectrum, "nuggetElectrum");
			addReverseStorageRecipe(nuggetElectrum, "ingotElectrum");
			addStorageRecipe(dustElectrum, "dustElectrumTiny");
			addReverseStorageRecipe(tinyElectrum, "dustElectrum");
			addSmelting(dustElectrum, ingotElectrum);
		}

		addStorageRecipe(ingotWardenicMetal, "nuggetWardenicMetal");
		addReverseStorageRecipe(nuggetWardenicMetal, "ingotWardenicMetal");
		addStorageRecipe(dustWardenicMetal, "dustWardenicMetalTiny");
		addReverseStorageRecipe(tinyWardenicMetal, "dustWardenicMetal");
		addSmelting(dustWardenicMetal, ingotWardenicMetal);
		addSmelting(rawWardenicMetal, ingotWardenicMetal, 1.3F);

		addShapelessSizedOreRecipe(rawOsLu, 0, INGOT + OS, INGOT + OS, INGOT + OS, INGOT + OS, INGOT + LU, INGOT + LU, INGOT + LU, "ingotNeodymium", "ingotTungsten");
		addShapelessRecipe(ItemHelper.cloneStack(coatedOsLu, 4), "ingotOsmiumLutetiumRaw", "ingotOsmiumLutetiumRaw", "ingotOsmiumLutetiumRaw", "ingotOsmiumLutetiumRaw", "dustFluonicSapphire", "itemClay", "itemClay", "itemClay", "itemClay");

		addStorageRecipe(ingotThaumicBronze, "nuggetThaumicBronze");
		addStorageRecipe(ingotThaumicRiftishBronze, "nuggetThaumicRiftishBronze");
		addStorageRecipe(ingotOsLu, "nuggetOsmiumLutetium");
		addStorageRecipe(gemFluonicPyroptase, "nuggetFluonicPyroptase");

		addReverseStorageRecipe(nuggetThaumicBronze, "ingotThaumicBronze");
		addReverseStorageRecipe(nuggetThaumicRiftishBronze, "ingotThaumicRiftishBronze");
		addReverseStorageRecipe(nuggetOsLu, "ingotOsmiumLutetium");
		addReverseStorageRecipe(shardFluonicPyroptase, "gemFluonicPyroptase");

		addStorageRecipe(dustThaumicBronze, "dustThaumicBronzeTiny");
		addStorageRecipe(dustThaumicRiftishBronze, "dustThaumicRiftishBronzeTiny");
		addStorageRecipe(dustOsLu, "dustOsmiumLutetiumTiny");
		addStorageRecipe(dustFluonicPyroptase, "dustFluonicPyroptaseTiny");

		addReverseStorageRecipe(tinyThaumicBronze, "dustThaumicBronze");
		addReverseStorageRecipe(tinyThaumicRiftishBronze, "dustThaumicRiftishBronze");
		addReverseStorageRecipe(tinyOsLu, "dustOsmiumLutetium");
		addReverseStorageRecipe(tinyFluonicPyroptase, "dustFluonicPyroptase");

		addSmelting(dustThaumicBronze, ingotThaumicBronze);
		addSmelting(dustThaumicRiftishBronze, ingotThaumicRiftishBronze);

		addSmelting(coatedThaumicBronze, firedThaumicBronze, 2.0F);

		addStorageRecipe(ingotThaumicElectrum, "nuggetThaumicElectrum");
		addStorageRecipe(ingotSteel, "nuggetSteel");
		addStorageRecipe(ingotWardenicSteel, "nuggetWardenicSteel");
		addStorageRecipe(ingotWardenicRiftishBronze, "nuggetWardenicRiftishBronze");
		addStorageRecipe(ingotVoidbrass, "nuggetVoidbrass");
		addStorageRecipe(ingotVoidsteel, "nuggetVoidsteel");
		addStorageRecipe(ingotVoidtungsten, "nuggetVoidtungsten");

		addReverseStorageRecipe(nuggetThaumicElectrum, "ingotThaumicElectrum");
		addReverseStorageRecipe(nuggetSteel, "ingotSteel");
		addReverseStorageRecipe(nuggetWardenicSteel, "ingotWardenicSteel");
		addReverseStorageRecipe(nuggetWardenicRiftishBronze, "ingotWardenicRiftishBronze");
		addReverseStorageRecipe(nuggetVoidbrass, "ingotVoidbrass");
		addReverseStorageRecipe(nuggetVoidsteel, "ingotVoidsteel");
		addReverseStorageRecipe(nuggetVoidtungsten, "ingotVoidtungsten");

		addStorageRecipe(dustThaumicElectrum, "dustThaumicElectrumTiny");
		addStorageRecipe(dustSteel, "dustSteelTiny");
		addStorageRecipe(dustWardenicSteel, "dustWardenicSteelTiny");
		addStorageRecipe(dustWardenicRiftishBronze, "dustWardenicRiftishBronzeTiny");
		addStorageRecipe(dustVoidbrass, "dustVoidbrassTiny");
		addStorageRecipe(dustVoidsteel, "dustVoidsteelTiny");
		addStorageRecipe(dustVoidtungsten, "dustVoidtungstenTiny");

		addReverseStorageRecipe(tinyThaumicElectrum, "dustThaumicElectrum");
		addReverseStorageRecipe(tinySteel, "dustSteel");
		addReverseStorageRecipe(tinyWardenicSteel, "dustWardenicSteel");
		addReverseStorageRecipe(tinyWardenicRiftishBronze, "dustWardenicRiftishBronze");
		addReverseStorageRecipe(tinyVoidbrass, "dustVoidbrass");
		addReverseStorageRecipe(tinyVoidsteel, "dustVoidsteel");
		addReverseStorageRecipe(tinyVoidtungsten, "dustVoidtungsten");

		addSmelting(dustThaumicElectrum, ingotThaumicElectrum);
		addSmelting(dustSteel, ingotSteel);
		addSmelting(dustWardenicSteel, ingotWardenicSteel);
		addSmelting(dustWardenicRiftishBronze, ingotWardenicRiftishBronze);
		addSmelting(dustVoidbrass, ingotVoidbrass);
		addSmelting(dustVoidsteel, ingotVoidsteel);

		recipeSalisTiny = addReverseStorageRecipe(tinySalisMundus, salisMundus);
		recipeSalis = addStorageRecipe(dustSalisMundus, salisPinch);

		recipeBinderTiny = addReverseStorageRecipe(tinyWardenicBinder, "dustWardenicBinder");
		recipeBinderCombine = addStorageRecipe(dustWardenicBinder, "dustWardenicBinderTiny");

		addRefractorySmelting(coatedOsLu, firedOsLu, 2.0F);

		addRefractoryOreSmelting(oreWolframite, ingotTungsten, 1.0F, ingotFe);
		addRefractoryOreSmelting(oreIridosmium, ingotIridosmium, 1.0F, ingotFe);

		addRefractorySmelting(dustTungsten, ingotTungsten);
		addRefractorySmelting(dustOsmium, ingotOsmium);
		addRefractorySmelting(dustIridium, ingotIridium);
		addRefractorySmelting(dustOsLu, ingotOsLu, 1.5F);
		addRefractorySmelting(dustVoidtungsten, ingotVoidtungsten);

		if (Loader.isModLoaded("ThermalExpansion")) {
			addInductionAlloyRecipe("Copper", 3, "Zinc", 1, ingotBrass);
			addInductionAlloyRecipe("ArsenicalBronze", 1, "AntimonialBronze", 1, ingotMithril);
			addInductionAlloyRecipe("Copper", ThaumRevConfig.backwardsAlBronze ? 1 : 3, "Aluminium", ThaumRevConfig.backwardsAlBronze ? 3 : 1, ingotAluminiumBronze);
			addInductionAlloyRecipe("Copper", 3, "Nickel", 1, ingotCupronickel);
			addInductionAlloyRecipe("Copper", 3, "Arsenic", 1, ingotArsenicalBronze);
			addInductionAlloyRecipe("Copper", 3, "Antimony", 1, ingotAntimonialBronze);
			addInductionAlloyRecipe("Copper", 1, "Nickel", 1, ingotConstantan);

			addPulverizerRecycleRecipe(gemWardenicQuartz, blockWardenicQuartz, 4);
			addPulverizerRecycleRecipe(gemWardenicQuartz, blockWardenicQuartzChiseled, 4);
			addPulverizerRecycleRecipe(gemWardenicQuartz, blockWardenicQuartzPillar, 4);
			addPulverizerRecycleRecipe(gemWardenicQuartz, slabWardenicQuartz, 2);
			addPulverizerRecycleRecipe(gemWardenicQuartz, stairsWardenicQuartz, 6);
			addPulverizerRecipe(1600, gemWardenicQuartz, dustWardenicQuartz);
			addPulverizerRecipe(3200, gemWardenicCrystal, dustWardenicCrystal);

			addPulverizerOreRecipe(oreSphalerite, dustZinc, dustLead);
			addPulverizerOreRecipe(oreXenotime, dustLanthanides, dustArsenic);
			addPulverizerOreRecipe(oreWolframite, dustTungsten, dustIron);
			addPulverizerRecipe(4800, oreIridosmium, ItemHelper.cloneStack(dustIridosmium, 2), dustIron, 15);
			addPulverizerOreRecipe(oreBismuthinite, dustBismuth, dustLead);
			addPulverizerOreRecipe(oreTennantite, dustArsenicalBronze, dustSilver);
			addPulverizerRecipe(4000, oreTetrahedrite, ItemHelper.cloneStack(dustAntimonialBronze, 2), itemQuicksilverDrop);
			addPulverizerRecipe(4000, oreDioptase, ItemHelper.cloneStack(gemDioptase, 2));
			addPulverizerRecipe(4000, orePyrope, ItemHelper.cloneStack(gemPyrope, 2));
			addPulverizerRecipe(4000, oreFluonicSapphire, ItemHelper.cloneStack(gemFluonicSapphire, 2));

			addInductionOreRecipes("Zinc", ingotLead);
			addInductionOreRecipes("Xenotime", "XenotimeLanthanides", dustArsenic);
			addInductionOreRecipes("Bismuth", ingotLead);
			addInductionOreRecipes("Tennantite", "ArsenicalBronze", ingotSilver);
			addInductionOreRecipes("Tetrahedrite", "AntimonialBronze", quicksilver);

		}


		//

		//Temporary
		recipeAluDenseTemp = addShapelessRecipe(aluDenseTemp, "itemAlumentum", "itemAlumentum", "itemAlumentum", "itemAlumentum");

	}

	/*public static void loadMetalRecipes() {
		//((BlockStorageOre) blockStorageOre).recipes();
	}*/

	public static void loadThaumicRecipes() {
		//recipeOrbReceptor = addArcaneCraftingRecipe(keyAspectOrb)

		recipeTreatedCotton = addArcaneCraftingRecipe(keyCotton, cottonTreated, ThaumcraftHelper.newPrimalAspectList(2), " S ", "FCF", " F ", 'S', salisPinch, 'F', "itemCottonFiber", 'C', "itemCottonFabric");
		recipeEnchantedCotton = addCrucibleRecipe(keyCotton, cottonEnchanted, "itemCottonFabricTreated", new AspectList().add(CLOTH, 2).add(MAGIC, 1));

		recipePrimalGoggles = addInfusionCraftingRecipe(keyRobesPrimal, primalGoggles.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackGoggles, ingotThaumicElectrum, ingotThaumicRiftishBronze, cottonEnchanted, cottonEnchanted, dustSalisMundus, dustSalisMundus, dustSalisMundus, itemPrimalCharm);
		recipePrimalRobes = addInfusionCraftingRecipe(keyRobesPrimal, primalRobes.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackChestRobe, ingotThaumicElectrum, ingotThaumicRiftishBronze, ingotThaumicRiftishBronze, cottonEnchanted, dustSalisMundus, dustSalisMundus, dustSalisMundus, itemPrimalCharm);
		recipePrimalPants = addInfusionCraftingRecipe(keyRobesPrimal, primalPants.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackLegsRobe, ingotThaumicElectrum, ingotThaumicRiftishBronze, ingotThaumicRiftishBronze, cottonEnchanted, dustSalisMundus, dustSalisMundus, dustSalisMundus, itemPrimalCharm);
		recipePrimalBoots = addInfusionCraftingRecipe(keyRobesPrimal, primalBoots.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackBootsRobe, ingotThaumicElectrum, ingotThaumicRiftishBronze, ingotThaumicRiftishBronze, cottonEnchanted, dustSalisMundus, dustSalisMundus, dustSalisMundus, itemPrimalCharm);

		recipeAniPiston = addArcaneCraftingRecipe(keyMaterial, animatedPiston, new AspectList().add(AIR, 5), "IGI", "TAT", "BRB", 'I', "nuggetIron", 'G', greatwoodSlab, 'T', "nuggetThaumium", 'A', "shardAir", 'B', "nuggetBrass", 'R', "dustRedstone"); //TODO: v0.0.8: Runic Infuser

		recipeThaumicBronzeRaw = addShapelessArcaneCraftingRecipe(keyThaumicBronze, rawThaumicBronze, new AspectList().add(ORDER, 5).add(EARTH, 5).add(FIRE, 5), nBronze, nBronze, nBronze, nBronze, nBronze, nBronze, "nuggetThaumium", "nuggetThaumium", "nuggetBrass");
		recipeThaumicBronzeCoated = addShapelessArcaneCraftingRecipe(keyThaumicBronze, coatedThaumicBronze, new AspectList().add(EARTH, 5).add(WATER, 5), "ingotThaumicBronzeRaw", nHg, salisPinch, "itemClay");

		recipeThaumicRBronze = addInfusionCraftingRecipe(keyThaumicRBronze, ingotThaumicRiftishBronze, 1, new AspectList().add(MAGIC, 8), ingotRiftishBronze, tinySalisMundus, tinySalisMundus, tinySalisMundus, itemQuicksilverDrop);

		recipeThaumicBronzeChain = addArcaneCraftingRecipe(keyBronzeChain, ItemHelper.cloneStack(thaumicBronzeChain, 12), new AspectList().add(ORDER, 10).add(FIRE, 5), " X ", "X X", 'X', "ingotThaumicBronze");

		recipeBronzeChainHelmet = addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainHelmet.getStack(), new AspectList().add(ORDER, 10).add(EARTH, 5).add(FIRE, 5), "XXX", "X X", 'X', chainTBronze);
		recipeBronzeChainmail = addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainmail.getStack(), new AspectList().add(ORDER, 25).add(EARTH, 12).add(FIRE, 12), "X X", "XXX", "XXX", 'X', chainTBronze);
		recipeBronzeChainGreaves = addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainGreaves.getStack(), new AspectList().add(ORDER, 20).add(EARTH, 10).add(FIRE, 10), "XXX", "X X", "X X", 'X', chainTBronze);
		recipeBronzeChainBoots = addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainBoots.getStack(), new AspectList().add(ORDER, 5).add(EARTH, 3).add(FIRE, 3), "X X", "X X", 'X', chainTBronze);

		//recipeRunicInfuser = addArcaneCraftingRecipe(keyRunicInfuser, runicInfuser, ThaumcraftHelper.newPrimalAspectList(25, 25, 25, 25, 25, 25), "QRQ", "SBS", "ITI", 'Q', nHg, 'R', visRelay, 'S', arcStoneSlab, 'B', shardBalanced, 'I', "ingotThaumium", 'T', table);
		recipeArcaneSingularity = addShapelessArcaneCraftingRecipe(keyRunicInfuser, arcaneSingularity, ThaumcraftHelper.newPrimalAspectList(2, 10, 0, 0, 5, 5), itemAlumentum, itemNitor); //TODO: v0.0.8: Runic Infuser
		recipeStableSingularity = addShapelessArcaneCraftingRecipe(keyRunicInfuser, stabilizedSingularity, ThaumcraftHelper.newPrimalAspectList(7, 15, 5, 5, 35, 10), arcaneSingularity, redstone, salisMundus); //TODO: v0.0.8: Runic Infuser

		recipeEnchSilverwood = addShapelessArcaneCraftingRecipe(keyEnchSilverwood, silverwoodEnchanted, new AspectList().add(ORDER, 5), planksSilverwood, salisMundus, salisMundus); //TODO: v0.0.8: Runic Infuser
		recipeConsSilverwood = addInfusionCraftingRecipe(keyEnchSilverwood, silverwoodConsecrated, 3, new AspectList().add(ORDER, 10).add(FIRE, 5), silverwoodEnchanted, silverwoodEnchanted, silverwoodEnchanted, silverwoodEnchanted, silverwoodEnchanted, tinySalisMundus, tinySalisMundus, nuggetSilver, itemQuicksilverDrop, itemNitor, nuggetPalladium); //TODO: Infusionize //TODO: v0.0.9: Alchemical Infuser

		//recipeDarkAlchemicalInfuser = addArcaneCraftingRecipe(keyDarkRunicInfuser, darkRunicInfuser, ThaumcraftHelper.newPrimalAspectList(20, 10, 15, 15, 25, 35), "GVG", "MSM", "ORO", 'G', nAu, 'V', voidSeed, 'M', mirror, 'S', stableSingularity, 'O', obsTotem, 'R', runicInfuser);

		recipeTransNickel = addCrucibleRecipe(keyTransmutationNi, ItemHelper.cloneStack(nuggetNickel, 3), "nuggetNickel", new AspectList().add(METAL, 2).add(VOID, 1));
		recipeTransAluminium = addCrucibleRecipe(keyTransmutationAl, ItemHelper.cloneStack(nuggetAluminium, 3), "nuggetAluminium", new AspectList().add(METAL, 2).add(ORDER, 1));
		recipeTransNeodymium = addCrucibleRecipe(keyTransmutationNd, ItemHelper.cloneStack(nuggetNeodymium, 3), "nuggetNeodymium", new AspectList().add(METAL, 2).add(ENERGY, 1));
		recipeTransZinc = addCrucibleRecipe(keyTransmutationZn, ItemHelper.cloneStack(nuggetZinc, 3), "nuggetZinc", new AspectList().add(METAL, 2).add(CRYSTAL, 1));
		recipeTransArsenic = addCrucibleRecipe(keyTransmutationAs, ItemHelper.cloneStack(tinyArsenic, 3), "dustArsenicTiny", new AspectList().add(METAL, 2).add(POISON, 1));
		recipeTransAntimony = addCrucibleRecipe(keyTransmutationSb, ItemHelper.cloneStack(tinyAntimony, 3), "dustAntimonyTiny", new AspectList().add(METAL, 2).add(POISON, 1));
		recipeTransBismuth = addCrucibleRecipe(keyTransmutationBi, ItemHelper.cloneStack(nuggetBismuth, 3), "nuggetBismuth", new AspectList().add(METAL, 2).add(ORDER, 1));
		recipeTransTungsten = addCrucibleRecipe(keyTransmutationW, ItemHelper.cloneStack(nuggetTungsten, 3), "nuggetTungsten", new AspectList().add(METAL, 2).add(MECHANISM, 1).add(ARMOR, 1));
		recipeTransLutetium = addCrucibleRecipe(keyTransmutationLu, ItemHelper.cloneStack(nuggetLutetium, 3), "nuggetLutetium", new AspectList().add(METAL, 2).add(EARTH, 1));
		recipeTransPalladium = addCrucibleRecipe(keyTransmutationPd, ItemHelper.cloneStack(nuggetPalladium, 3), "nuggetPalladium", new AspectList().add(METAL, 2).add(EXCHANGE, 1));
		if (OreDictionary.doesOreNameExist("nuggetPlatinum")) {
			ItemStack nPt = OreDictionary.getOres("nuggetPlatinum").get(0);
			recipeTransPlatinum = addCrucibleRecipe(keyTransmutationPt, ItemHelper.cloneStack(nPt, 3), "nuggetPlatinum", new AspectList().add(METAL, 2).add(GREED, 1).add(EXCHANGE, 1));
		}
		recipeTransOsmium = addCrucibleRecipe(keyTransmutationOs, ItemHelper.cloneStack(nuggetOsmium, 3), "nuggetOsmium", new AspectList().add(METAL, 2).add(ARMOR, 1).add(ORDER, 1));
		recipeTransIridium = addCrucibleRecipe(keyTransmutationIr, ItemHelper.cloneStack(nuggetIridium, 3), "nuggetIridium", new AspectList().add(METAL, 2).add(ARMOR, 1).add(ORDER, 1).add(LIGHT, 1).add(ENERGY, 1));

		recipeClusterNickel = addCrucibleRecipe(keyClusterNi, clusterNickel, "oreNickel", new AspectList().add(METAL, 1).add(ORDER, 1));
		if (OreDictionary.doesOreNameExist("oreAluminium")) {
			recipeClusterAluminium = addCrucibleRecipe(keyClusterAl, clusterXenotime, "oreAluminium", new AspectList().add(METAL, 1).add(ORDER, 1));
		}
		recipeClusterXenotime = addCrucibleRecipe(keyClusterYPO, clusterXenotime, "oreXenotime", new AspectList().add(METAL, 1).add(ORDER, 1));
		recipeClusterZinc = addCrucibleRecipe(keyClusterZn, clusterZinc, "oreZinc", new AspectList().add(METAL, 1).add(ORDER, 1));
		recipeClusterBismuth = addCrucibleRecipe(keyClusterBi, clusterBismuth, "oreBismuth", new AspectList().add(METAL, 1).add(ORDER, 1));
		recipeClusterTennantite = addCrucibleRecipe(keyClusterCuAs, clusterTennantite, "oreTennantite", new AspectList().add(METAL, 1).add(ORDER, 1));
		recipeClusterTertahedrite = addCrucibleRecipe(keyClusterCuSb, clusterTetrahedrite, "oreTetrahedrite", new AspectList().add(METAL, 1).add(ORDER, 1));
		recipeClusterTungsten = addCrucibleRecipe(keyClusterW, clusterTungsten, "oreTungsten", new AspectList().add(METAL, 1).add(ORDER, 1));
		if (OreDictionary.doesOreNameExist("orePlatinum")) {
			recipeClusterPlatinum = addCrucibleRecipe(keyClusterPt, clusterPlatinum, "orePlatinum", new AspectList().add(METAL, 1).add(ORDER, 1));
		}
		recipeClusterIridosmium = addCrucibleRecipe(keyClusterIrOs, clusterIridosmium, "oreIridosmium", new AspectList().add(METAL, 1).add(ORDER, 1));

		recipeExcubituraPaste = addShapelessArcaneCraftingRecipe(keyExcubituraPaste, excubituraPaste, new AspectList().add(EARTH, 5).add(ENTROPY, 3), "itemExcubituraPetal", "itemExcubituraPetal", salisPinch, new ItemStack(Items.bowl));

		recipeExcubituraFabric = addArcaneCraftingRecipe(keyWardencloth, ItemHelper.cloneStack(excubituraFabric, 8), new AspectList().add(ORDER, 5), "FFF", "FPF", "FFF", 'F', enchCotton, 'P', paste);
		recipeWardencloth = addCrucibleRecipe(keyWardencloth, itemWardencloth, "itemExcubituraFabric", new AspectList().add(CLOTH, 2).add(ARMOR, 2).add(WARDEN, 2));

		recipeWardenclothSkullcap = addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothSkullcap.getStack(), ThaumcraftHelper.newPrimalAspectList(10), "WEW", "EGE", 'E', enchCotton, 'W', wardencloth, 'G', itemGoggles);
		recipeWardenclothTunic = addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothTunic.getStack(), ThaumcraftHelper.newPrimalAspectList(20), "W W", "WEW", "EWE", 'E', enchCotton, 'W', wardencloth);
		recipeWardenclothPants = addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothPants.getStack(), ThaumcraftHelper.newPrimalAspectList(15), "EWE", "E E", "W W", 'E', enchCotton, 'W', wardencloth);
		recipeWardenclothBoots = addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(10), "W W", "E E", 'E', enchCotton, 'W', wardencloth);

		recipeExcubituraOilUnproc = addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOilUnproc, new AspectList().add(EARTH, 1).add(ORDER, 1), itemPhial, paste, paste, paste, paste);
		recipeExcubituraOil = addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOil, ThaumcraftHelper.newPrimalAspectList(0, 10, 5, 5, 25, 5), "itemExcubituraOilUnprocessed", nHg, salisPinch, itemAlumentum); //TODO: v0.0.8: Runic Infuser

		recipeWardenBronzeChain = addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(wardenicBronzeChain, 8), ThaumcraftHelper.newPrimalAspectList(5, 5, 0, 5, 10, 0), "TTT", "SOS", "TTT", 'T', chainTBronze, 'S', salisPinch, 'O', oilExcu);
		recipePrimalBronzeChain = addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(primalBronzeChain, 2), ThaumcraftHelper.newPrimalAspectList(10).add(ORDER, 10), "NCS", "PBP", "SCN", 'N', "nuggetBrass", 'C', chainWBronze, 'S', salisPinch, 'P', itemPrimalCharm, 'B', shardBalanced);
		recipeWardenBronzePlate = addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(plateWardenicBronze, 2), ThaumcraftHelper.newPrimalAspectList(5, 5, 0, 5, 15, 0), "IQC", "SOS", "CRI", 'I', "ingotThaumicBronze", 'C', chainWBronze, 'S', salisPinch, 'Q', nHg, 'O', oilExcu, 'R', "nuggetThaumium");

		recipeWardenicChainHelmet = addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainHelmet.getStack(), ThaumcraftHelper.newPrimalAspectList(20), "PWP", "WGW", 'W', chainWBronze, 'P', chainPBronze, 'G', itemGoggles);
		recipeWardenicChainmail = addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainmail.getStack(), ThaumcraftHelper.newPrimalAspectList(42), "P P", "WBW", "WWW", 'W', chainWBronze, 'P', chainPBronze, 'B', "itemPlateWardenicBronze");
		recipeWardenicChainGreaves = addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainGreaves.getStack(), ThaumcraftHelper.newPrimalAspectList(35), "WBW", "P P", "W W", 'W', chainWBronze, 'P', chainPBronze, 'B', "itemPlateWardenicBronze");
		recipeWardenicChainBoots = addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(13), "P P", "W W", 'W', chainWBronze, 'P', chainPBronze);

		recipePureOil = addShapelessArcaneCraftingRecipe(keyPureOil, excubituraOilPure, ThaumcraftHelper.newPrimalAspectList(0, 10, 10, 15, 50, 5), oilExcu, oilExcu, oilExcu, oilExcu, salisMundus, "itemArcaneSingularity", itemPhial); //TODO: v0.0.9: Alchemical Infuser

		recipeWardenSteel = addInfusionCraftingRecipe(keyWardenSteel, ingotWardenicSteel, 2, new AspectList().add(METAL, 12).add(ARMOR, 8).add(TOOL, 8).add(ORDER, 16).add(MAGIC, 16).add(WARDEN, 8), ingotSteel, dustSalisMundus, dustSalisMundus, excubituraOilPure, quicksilver);

		recipeWardenicHardener = addInfusionCraftingRecipe(keyWardenicObsidian, wardenicHardener, 2, new AspectList().add(EARTH, 8).add(COLD, 8).add(MAGIC, 4).add(ORDER, 8), stabilizedSingularity, new ItemStack(Items.redstone), dustSalisMundus, excubituraOilPure, itemShardWater, itemShardWater, itemShardOrder);
		if (Loader.isModLoaded("ThermalFoundation")) {
			recipeWardenicHardenerAlt = addInfusionCraftingRecipe(keyWardenicObsidian, wardenicHardener, 2, new AspectList().add(EARTH, 8).add(COLD, 8).add(MAGIC, 4).add(ORDER, 8), stabilizedSingularity, new ItemStack(Items.redstone), dustSalisMundus, excubituraOilPure, dustCryotheum);
		}

		recipeWardenSteelChain = addArcaneCraftingRecipe(keyWardenPlate, ItemHelper.cloneStack(wardenicSteelChain, 12), new AspectList().add(ORDER, 25).add(FIRE, 15), " X ", "X X", 'X', "ingotWardenicSteel");
		recipeWardenSteelChainOiled = addArcaneCraftingRecipe(keyWardenPlate, ItemHelper.cloneStack(oiledSteelChain, 6), ThaumcraftHelper.newPrimalAspectList(10, 10, 0, 10, 25, 5), "CCC", "SOS", "CCC", 'C', "itemChainWardenicSteel", 'S', salisMundus, 'O', "itemExcubituraOilPure");

		recipeWardenSteelPlate = addArcaneCraftingRecipe(keyWardenPlate, plateWardenicSteel, new AspectList().add(ORDER, 1), " A ", "ASA", " A ", 'A', itemAlumentum, 'S', "ingotWardenicSteel"); //TODO: v0.0.8: Thaumic Hammermill
		recipeDetailedSteelPlate = addArcaneCraftingRecipe(keyWardenPlate, detailedSteelPlate, ThaumcraftHelper.newPrimalAspectList(0, 10, 5, 5, 15, 0), "TCB", "QPQ", "BCT", 'T', "nuggetThaumium", 'B', "nuggetBrass", 'C', "itemChainWardenicSteelOiled", 'Q', nHg, 'P', "itemPlateWardenicSteel");
		recipeRunicSteelPlate = addInfusionCraftingRecipe(keyWardenPlate, runicSteelPlate, 1, new AspectList().add(ARMOR, 4).add(MAGIC, 4).add(ENERGY, 4).add(FLIGHT, 4), detailedSteelPlate, tinySalisMundus, tinySalisMundus, tinySalisMundus, tinySalisMundus, itemWardencloth, new ItemStack(Items.redstone));
		recipesConsecratedSteelPlate = addInfusionCraftingRecipe(keyWardenPlate, consecratedSteelPlate, 2, new AspectList().add(ARMOR, 8).add(MAGIC, 4).add(ORDER, 4), runicSteelPlate, nuggetSilver, itemQuicksilverDrop, new ItemStack(Items.glowstone_dust), silverwoodConsecrated, tinySalisMundus);

		recipeWardenicPlateHelmet = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateHelmet.getStack(), ThaumcraftHelper.newPrimalAspectList(30), "CRC", "RGR", 'R', "itemPlateWardenicSteelRunic", 'C', "itemPlateWardenicSteelConsecrated", 'G', stackGoggles);
		recipeWardenicChestplate = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicChestplate.getStack(), ThaumcraftHelper.newPrimalAspectList(70), "C C", "CRC", "RCR", 'R', "itemPlateWardenicSteelRunic", 'C', "itemPlateWardenicSteelConsecrated");
		recipeWardenicPlateGreaves = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateGreaves.getStack(), ThaumcraftHelper.newPrimalAspectList(50), "RCR", "R R", "C C", 'R', "itemPlateWardenicSteelRunic", 'C', "itemPlateWardenicSteelConsecrated");
		recipeWardenicPlateBoots = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(20), "R R", "C C", 'R', "itemPlateWardenicSteelRunic", 'C', "itemPlateWardenicSteelConsecrated");

		recipeWardenicQuartz = addCrucibleRecipe(keyQuartz, gemWardenicQuartz, "gemQuartz", new AspectList().add(MAGIC, 4).add(CRYSTAL, 2).add(ENERGY, 2).add(WARDEN, 1));
		recipeWardenicQuartzDust = addCrucibleRecipe(keyQuartz, dustWardenicQuartz, "gemWardenicQuartz", new AspectList().add(ENTROPY, 2));
		recipeWardenicQuartzReconst = addCrucibleRecipe(keyQuartz, gemWardenicQuartz, "dustWardenicQuartz", new AspectList().add(ORDER, 2).add(CRYSTAL, 4));
		recipeWardenicQuartzInf = addInfusionCraftingRecipe(keyQuartz, wardenicQuartzInf, 2, new AspectList().add(WARDEN, 4).add(MAGIC, 8).add(CRYSTAL, 4), blockWardenicQuartz, dustSalisMundus, excubituraOilPure);

		recipeWardenicCrystal = addCrucibleRecipe(keyWardenCrystal, gemWardenicCrystal, "gemWardenicQuartzInfused", new AspectList().add(CRYSTAL, 32).add(AURA, 8).add(ORDER, 8).add(WARDEN, 16));
		recipeWardenicCrystalDust = addCrucibleRecipe(keyWardenCrystal, dustWardenicCrystal, "gemWardenicCrystal", new AspectList().add(ENTROPY, 4));
		recipeWardenicCrystalReconst = addCrucibleRecipe(keyWardenCrystal, gemWardenicCrystal, "dustWardenicCrystal", new AspectList().add(ORDER, 4).add(CRYSTAL, 8));
		recipeWardenicBinder = addInfusionCraftingRecipe(keyWardenCrystal, ItemHelper.cloneStack(dustWardenicBinder, 8), 2, new AspectList().add(MAGIC, 8).add(ENERGY, 4).add(WARDEN, 8), dustWardenicCrystal, dustWardenicQuartz, dustWardenicQuartz, dustSalisMundus, dustSalisMundus, dustSalisMundus, dustSalisMundus, quicksilver, excubituraOilPure);

		recipeWardenBronze = addInfusionCraftingRecipe(keyWardenBronze, ingotWardenicRiftishBronze, 2, new AspectList().add(WARDEN, 4).add(MAGIC, 4).add(ARMOR, 2).add(TOOL, 2), ingotThaumicRiftishBronze, tinyWardenicBinder, tinyWardenicBinder, tinyWardenicBinder);

		recipeWardenicCompositeRaw = addArcaneCraftingRecipe(keyWardenComposite, ItemHelper.cloneStack(rawWardenicComposite, 2), ThaumcraftHelper.newPrimalAspectList(0, 5, 0, 10, 20, 0), "BBB", "SSS", "WWW", 'B', "ingotWardenicRiftishBronze", 'S', "ingotWardenicSteel", 'W', "ingotWardenicMetal");
		recipeWardenicCompositeIngot = addInfusionCraftingRecipe(keyWardenComposite, ingotWardenicComposite, 3, new AspectList().add(METAL, 4).add(MAGIC, 4).add(WARDEN, 4).add(ARMOR, 2).add(TOOL, 2).add(ORDER, 4), rawWardenicComposite, dustWardenicBinder, dustWardenicBinder, dustSalisMundus, dustWardenicQuartz);

		recipeWardenicCompositePlate = addArcaneCraftingRecipe(keyWardenCompositePlate, plateWardenicComposite, new AspectList().add(ORDER, 1), " A ", "AIA", " A ", 'A', aluDenseTemp, 'I', "ingotWardenicComposite"); //TODO: v0.0.8: Thaumic Hammermill

		recipeFittedCompositePlate = addArcaneCraftingRecipe(keyWardenCompositeFitting, fittedCompositePlate, ThaumcraftHelper.newPrimalAspectList(0, 15, 0, 10, 15, 0), "CNW", "BPB", "WNC", 'C', "itemChainWardenicSteelOiled", 'W', wardencloth, 'N', "nuggetWardenicSteel", 'B', "dustWardenicBinder", 'P', "itemPlateWardenicComposite");
		recipeDetailedCompositePlate = addArcaneCraftingRecipe(keyWardenCompositeFitting, detailedCompositePlate, ThaumcraftHelper.newPrimalAspectList(0, 10, 5, 10, 15, 0), "EQE", "TPT", "BSB", 'E', "nuggetThaumicElectrum", 'Q', "quicksilver", 'T', "nuggetThaumium", 'P', "itemPlateWardenicCompositeFitted", 'B', "nuggetBismuthBronze", 'S', salisMundus);
		recipeRunicCompositePlate = addInfusionCraftingRecipe(keyWardenCompositeFitting, runicCompositePlate, 2, new AspectList().add(ARMOR, 4).add(MAGIC, 8).add(ENERGY, 4).add(FLIGHT, 4).add(WARDEN, 2), detailedCompositePlate, dustSalisMundus, dustSalisMundus, nuggetThaumicElectrum, new ItemStack(Items.redstone));
		recipeConsecratedCompositePlate = addInfusionCraftingRecipe(keyWardenCompositeFitting, consecratedCompositePlate, 4, new AspectList().add(ARMOR, 8).add(MAGIC, 12).add(ORDER, 4).add(ENERGY, 4).add(WARDEN, 2), runicCompositePlate, nuggetSilver, itemQuicksilverDrop, new ItemStack(Items.glowstone_dust), silverwoodConsecrated, silverwoodConsecrated, dustSalisMundus);
		recipePrimalCompositePlate = addInfusionCraftingRecipe(keyWardenCompositeFitting, primalCompositePlate, 6, ThaumcraftHelper.newPrimalAspectList(8).add(MAGIC, 16).add(ENERGY, 8).add(WARDEN, 4).add(ARMOR, 4), consecratedCompositePlate, itemPrimalCharm, dustSalisMundus, dustSalisMundus, itemShardBalanced, nuggetBrass, quicksilver);

		recipeWardenicCompositeHelmet = addArcaneCraftingRecipe(keyArmorWardenComposite, wardenicCompositeHelmet.getStack(), ThaumcraftHelper.newPrimalAspectList(65), "PCP", "CGC", 'C', "itemPlateWardenicCompositeConsecrated", 'P', "itemPlateWardenicCompositePrimal", 'G', stackGoggles);
		recipeWardenicCompositeChestplate = addArcaneCraftingRecipe(keyArmorWardenComposite, wardenicCompositeChestplate.getStack(), ThaumcraftHelper.newPrimalAspectList(110), "P P", "CPC", "CPC", 'C', "itemPlateWardenicCompositeConsecrated", 'P', "itemPlateWardenicCompositePrimal");
		recipeWardenicCompositeGreaves = addArcaneCraftingRecipe(keyArmorWardenComposite, wardenicCompositeGreaves.getStack(), ThaumcraftHelper.newPrimalAspectList(100), "PCP", "C C", "C C", 'C', "itemPlateWardenicCompositeConsecrated", 'P', "itemPlateWardenicCompositePrimal");
		recipeWardenicCompositeBoots = addArcaneCraftingRecipe(keyArmorWardenComposite, wardenicCompositeBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(60), "P P", "C C", 'C', "itemPlateWardenicCompositeConsecrated", 'P', "itemPlateWardenicCompositePrimal");

		recipeWardenicCrystalAwakened = addInfusionCraftingRecipe(keyWardenCrystalAwakened, gemWardenicCrystalAwakened, 4, ThaumcraftHelper.newPrimalAspectList(16).add(MAGIC, 32).add(WARDEN, 64).add(CRYSTAL, 16).add(ENERGY, 48), gemWardenicCrystal, dustWardenicBinder, dustWardenicBinder, dustWardenicBinder, dustWardenicBinder, excubituraOilPure, excubituraOilPure, dustSalisMundus, new ItemStack(Items.nether_star));

		recipeThaumicElectrum = addCrucibleRecipe(keyThaumicElectrum, ingotThaumicElectrum, "ingotElectrum", new AspectList().add(MAGIC, 6).add(ENERGY, 3));
	}

	public static void loadResearch() {
		String key = RESEARCH_KEY_MAIN;
		researchThaumRev = new FluxGearResearchItem(keyThaumRev, key, new AspectList(), 0, 0, 0, potato);
		researchMaterial = new FluxGearResearchItem(keyMaterial, key, new AspectList(), -1, 3, 0, oreDioptase);
		researchAlloys = new FluxGearResearchItem(keyAlloys, key, new AspectList(), -3, 6, 0, ingotBrass);

		researchThaumicBronze = new FluxGearResearchItem(keyThaumicBronze, key, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ORDER, 1), -3, 8, 1, ingotThaumicBronze);
		researchBronzeChain = new FluxGearResearchItem(keyBronzeChain, key, new AspectList().add(METAL, 4).add(MAGIC, 2).add(CRAFT, 3), -3, 10, 1, thaumicBronzeChain);
		researchArmorBronzeChain = new FluxGearResearchItem(keyArmorBronzeChain, key, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ARMOR, 3), -5, 11, 1, new ItemStack(bronzeChainmail));
		researchThaumicRBronze = new FluxGearResearchItem(keyThaumicRBronze, key, new AspectList().add(MAGIC, 5).add(METAL, 4).add(ORDER, 1), -5, 7, 2, ingotThaumicRiftishBronze);
		researchThaumicElectrum = new FluxGearResearchItem(keyThaumicElectrum, key, new AspectList().add(METAL, 4).add(MAGIC, 4).add(GREED, 2).add(ENERGY, 2).add(ORDER, 1), -5, 5, 2, ingotThaumicElectrum);
		researchCotton = new FluxGearResearchItem(keyCotton, key, new AspectList().add(CLOTH, 4).add(ARMOR, 3).add(MAGIC, 3), -8, 4, 1, cottonEnchanted);
		researchPrimalRobes = new FluxGearResearchItem(keyRobesPrimal, key, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(ENERGY, 2).add(SENSES, 2).add(ThaumcraftHelper.newPrimalAspectList(1)), -7, 6, 3, new ItemStack(primalRobes));

		researchRunicInfuser = new FluxGearResearchItem(keyRunicInfuser, key, new AspectList().add(ENERGY, 4).add(MAGIC, 4).add(AURA, 2).add(CRAFT, 3).add(MECHANISM, 4), 1, 6, 2, arcaneSingularity);
		researchAniPiston = new FluxGearResearchItem(keyAniPiston, key, new AspectList().add(AIR, 3).add(MECHANISM, 3).add(MOTION, 3), 0, 8, 1, animatedPiston);
		researchEnchSilverwood = new FluxGearResearchItem(keyEnchSilverwood, key, new AspectList().add(TREE, 3).add(MAGIC, 3).add(AURA, 3).add(ORDER, 3), 7, 8, 1, silverwoodEnchanted);

		researchWardenry = new FluxGearResearchItem(keyWardenry, key, new AspectList().add(WARDEN, 4).add(MAGIC, 3).add(ELDRITCH, 2).add(ENERGY, 2), -2, 0, 2, new ItemStack(blockThaumicPlant));

		researchExcubituraPaste = new FluxGearResearchItem(keyExcubituraPaste, key, new AspectList().add(PLANT, 4).add(MAGIC, 4).add(WARDEN, 1), -4, 2, 1, excubituraPaste);
		researchWardencloth = new FluxGearResearchItem(keyWardencloth, key, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(WARDEN, 1), -6, 3, 1, itemWardencloth);
		researchArmorWardencloth = new FluxGearResearchItem(keyArmorWardencloth, key, new AspectList().add(ARMOR, 4).add(MAGIC, 4).add(CLOTH, 4).add(WARDEN, 4), -8, 2, 1, new ItemStack(wardenclothTunic));

		researchExcubituraOil = new FluxGearResearchItem(keyExcubituraOil, key, new AspectList().add(MAGIC, 4).add(PLANT, 3).add(WARDEN, 2).add(WATER, 3), -5, -1, 1, excubituraOil);
		researchWardenChain = new FluxGearResearchItem(keyWardenChain, key, new AspectList().add(METAL, 4).add(MAGIC, 4).add(ARMOR, 3).add(WARDEN, 2), -7, -2, 1, wardenicBronzeChain);
		researchArmorWardenChain = new FluxGearResearchItem(keyArmorWardenChain, key, new AspectList().add(ARMOR, 4).add(METAL, 4).add(MAGIC, 4).add(WARDEN, 4), -9, -3, 1, new ItemStack(wardenicChainmail));

		researchPureOil = new FluxGearResearchItem(keyPureOil, key, new AspectList().add(MAGIC, 4).add(WATER, 4).add(WARDEN, 3).add(ENERGY, 3), -6, -4, 2, excubituraOilPure);
		researchWardenSteel = new FluxGearResearchItem(keyWardenSteel, key, new AspectList().add(METAL, 5).add(MAGIC, 4).add(TOOL, 2).add(ARMOR, 2).add(WARDEN, 3), -8, -5, 2, ingotWardenicSteel);
		researchWardenPlate = new FluxGearResearchItem(keyWardenPlate, key, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ARMOR, 3).add(WARDEN, 3), -10, -6, 2, plateWardenicSteel);
		researchArmorWardenSteel = new FluxGearResearchItem(keyArmorWardenSteel, key, new AspectList().add(ARMOR, 4).add(METAL, 4).add(MAGIC, 4).add(WARDEN, 4), -12, -8, 2, new ItemStack(wardenicChestplate));
		researchWardenicObsidian = new FluxGearResearchItem(keyWardenicObsidian, key, new AspectList().add(EARTH, 4).add(CRYSTAL, 4).add(FIRE, 3).add(MAGIC, 3).add(ARMOR, 4).add(WARDEN, 1), -3, -2, 2, wardenicObsidian);

		researchQuartz = new FluxGearResearchItem(keyQuartz, key, new AspectList().add(CRYSTAL, 4).add(MAGIC, 4).add(WARDEN, 4).add(ENERGY, 3).add(TOOL, 2), -7, -7, 2, gemWardenicQuartz);
		researchWardenCrystal = new FluxGearResearchItem(keyWardenCrystal, key, new AspectList().add(MAGIC, 4).add(CRYSTAL, 4).add(WARDEN, 4).add(ENERGY, 3).add(ORDER, 3), -9, -8, 2, gemWardenicCrystal);
		researchWardenBronze = new FluxGearResearchItem(keyWardenBronze, key, new AspectList().add(METAL, 5).add(MAGIC, 4).add(WARDEN, 4).add(ARMOR, 3).add(TOOL, 3), -11, -10, 2, ingotWardenicRiftishBronze);
		researchWardenComposite = new FluxGearResearchItem(keyWardenComposite, key, new AspectList().add(METAL, 6).add(MAGIC, 4).add(ARMOR, 4).add(TOOL, 3).add(WARDEN, 4), -12, -12, 2, plateWardenicComposite);
		researchWardenCompositePlate = new FluxGearResearchItem(keyWardenCompositePlate, key, new AspectList().add(METAL, 3).add(MAGIC, 2).add(WARDEN, 1), -11, -14, 1, plateWardenicComposite);
		researchWardenCompositeFitting = new FluxGearResearchItem(keyWardenCompositeFitting, key, new AspectList().add(METAL, 5).add(MAGIC, 4).add(ARMOR, 4).add(WARDEN, 4).add(ENERGY, 2), -9, -15, 3, consecratedCompositePlate);
		researchArmorWardenComposite = new FluxGearResearchItem(keyArmorWardenComposite, key, new AspectList().add(ARMOR, 4).add(METAL, 4).add(WARDEN, 4).add(MAGIC, 4).add(ORDER, 4), -7, -16, 3, new ItemStack(wardenicCompositeChestplate));

		researchWardenCrystalAwakened = new FluxGearResearchItem(keyWardenCrystalAwakened, key, ThaumcraftHelper.newPrimalAspectList(3).add(WARDEN, 6).add(MAGIC, 6).add(CRYSTAL, 4).add(ENERGY, 6), -5, -10, 3, gemWardenicCrystalAwakened);

		key = RESEARCH_KEY_METAL;

		researchTransmutationFe = new DummyResearchItem(keyTransmutationFe, key, "TRANSIRON", "ALCHEMY", -33, -8, nuggetIronTC).registerResearchItem();
		researchTransmutationSn = new DummyResearchItem(keyTransmutationSn, key, "TRANSTIN", "ALCHEMY", -36, -14, nuggetTinTC).setParents(keyTransmutationFe).registerResearchItem();
		researchTransmutationAg = new DummyResearchItem(keyTransmutationAg, key, "TRANSSILVER", "ALCHEMY", -38, -12, nuggetSilverTC).setParents(keyTransmutationFe).registerResearchItem();
		researchTransmutationPb = new DummyResearchItem(keyTransmutationPb, key, "TRANSLEAD", "ALCHEMY", -34, -15, nuggetLeadTC).setParents(keyTransmutationFe).registerResearchItem();
		researchTransmutationAu = new DummyResearchItem(keyTransmutationAu, key, "TRANSGOLD", "ALCHEMY", -32, -15, new ItemStack(Items.gold_nugget)).setParents(keyTransmutationFe).registerResearchItem();

		researchTransmutationNi = new FluxGearResearchItem(keyTransmutationNi, key, new AspectList().add(METAL, 3).add(EXCHANGE, 2).add(VOID, 1), -39, -10, 1, nuggetNickel);
		researchTransmutationAl = new FluxGearResearchItem(keyTransmutationAl, key, new AspectList().add(METAL, 3).add(EXCHANGE, 2).add(ORDER, 1), -27, -10, 1, nuggetAluminium);
		researchTransmutationNd = new FluxGearResearchItem(keyTransmutationNd, key, new AspectList().add(METAL, 3).add(EXCHANGE, 2).add(ENERGY, 1), -34, -1, 1, nuggetNeodymium);
		researchTransmutationZn = new FluxGearResearchItem(keyTransmutationZn, key, new AspectList().add(METAL, 3).add(EXCHANGE, 2).add(CRYSTAL, 1), -39, -17, 1, nuggetZinc);
		researchTransmutationAs = new FluxGearResearchItem(keyTransmutationAs, key, new AspectList().add(METAL, 3).add(EXCHANGE, 2).add(POISON, 1), -36, -21, 1, nuggetArsenic);
		researchTransmutationSb = new FluxGearResearchItem(keyTransmutationSb, key, new AspectList().add(METAL, 3).add(EXCHANGE, 2).add(POISON, 1), -35, -19, 1, nuggetAntimony);
		researchTransmutationBi = new FluxGearResearchItem(keyTransmutationBi, key, new AspectList().add(METAL, 3).add(EXCHANGE, 2).add(ORDER, 1), -35, -17, 1, nuggetBismuth);
		researchTransmutationW = new FluxGearResearchItem(keyTransmutationW, key, new AspectList().add(METAL, 4).add(EXCHANGE, 2).add(MECHANISM, 1).add(ARMOR, 1), -33, -17, 1, nuggetTungsten);
		researchTransmutationLu = new FluxGearResearchItem(keyTransmutationLu, key, new AspectList().add(METAL, 3).add(EXCHANGE, 2).add(EARTH, 1), -33, 1, 1, nuggetLutetium);
		researchTransmutationPd = new FluxGearResearchItem(keyTransmutationPd, key, new AspectList().add(METAL, 3).add(EXCHANGE, 3), -41, -10, 1, nuggetPalladium);
		if (OreDictionary.doesOreNameExist("nuggetPlatinum")) {
			researchTransmutationPt = new FluxGearResearchItem(keyTransmutationPt, key, new AspectList().add(METAL, 3).add(EXCHANGE, 3).add(GREED, 1), -31, -21, 1, OreDictionary.getOres("nuggetPlatinum").get(0));
		}
		researchTransmutationOs = new FluxGearResearchItem(keyTransmutationOs, key, new AspectList().add(METAL, 3).add(EXCHANGE, 2).add(ARMOR, 1).add(ORDER, 1), -31, -23, 1, nuggetPalladium);
		researchTransmutationIr = new FluxGearResearchItem(keyTransmutationIr, key, new AspectList().add(METAL, 3).add(EXCHANGE, 2).add(ARMOR, 1).add(ORDER, 1).add(LIGHT, 1).add(ENERGY, 1), -29, -24, 1, nuggetPalladium);

		researchClusterFe = new DummyResearchItem(keyClusterFe, key, "PUREIRON", "ALCHEMY", 8, -12, clusterIron).registerResearchItem();
		researchClusterCu = new DummyResearchItem(keyClusterCu, key, "PURECOPPER", "ALCHEMY", 12, -17, clusterCopper).setParents(keyClusterFe).registerResearchItem();
		researchClusterSn = new DummyResearchItem(keyClusterSn, key, "PURETIN", "ALCHEMY", 14, -15, clusterTin).setParents(keyClusterFe).registerResearchItem();
		researchClusterPb = new DummyResearchItem(keyClusterPb, key, "PURELEAD", "ALCHEMY", 14, -13, clusterLead).setParents(keyClusterFe).registerResearchItem();
		researchClusterAu = new DummyResearchItem(keyClusterAu, key, "PUREGOLD", "ALCHEMY", 13, -11, clusterGold).setParents(keyClusterFe).registerResearchItem();

		researchClusterNi = new FluxGearResearchItem(keyClusterNi, key, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(VOID, 1), 8, -18, 1, clusterNickel);
		if (OreDictionary.doesOreNameExist("oreAluminium")) {
			researchClusterAl = new FluxGearResearchItem(keyClusterAl, key, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 3), 11, -7, 1, clusterAluminium);
		}
		researchClusterYPO = new FluxGearResearchItem(keyClusterYPO, key, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(EARTH, 2).add(ENERGY, 1), 2, -13, 1, clusterXenotime);
		researchClusterZn = new FluxGearResearchItem(keyClusterZn, key, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(CRYSTAL, 1), 20, -15, 1, clusterZinc);
		researchClusterBi = new FluxGearResearchItem(keyClusterBi, key, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 3), 16, -14, 1, clusterBismuth);
		researchClusterCuAs = new FluxGearResearchItem(keyClusterCuAs, key, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(EXCHANGE, 1).add(POISON, 1), 13, -19, 1, clusterTennantite);
		researchClusterCuSb = new FluxGearResearchItem(keyClusterCuSb, key, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(EXCHANGE, 1).add(POISON, 1), 15, -17, 1, clusterTetrahedrite);
		researchClusterW = new FluxGearResearchItem(keyClusterW, key, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(MECHANISM, 1).add(ARMOR, 1), 16, -12, 1, clusterTungsten);
		if (OreDictionary.doesOreNameExist("orePlatinum")) {
			researchClusterPt = new FluxGearResearchItem(keyClusterPt, key, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(EXCHANGE, 1).add(GREED, 1), 6, -20, 1, clusterPlatinum);
		}
		researchClusterIrOs = new FluxGearResearchItem(keyClusterIrOs, key, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 4).add(ARMOR, 2).add(LIGHT, 1).add(ENERGY, 1), 7, -24, 1, clusterIridosmium);

	}

	public static void initResearch() {
		researchThaumRev.setRound().setSpecial().setAutoUnlock().setSiblings(keyMaterial);
		researchMaterial.setRound().setSiblings(keyAlloys).setStub();
		researchAlloys.setRound().setStub();

		researchThaumicBronze.setParents(keyAlloys).setParentsHidden(keyThaumium);
		researchBronzeChain.setParents(keyThaumicBronze).setSecondary();
		researchArmorBronzeChain.setParents(keyBronzeChain).setSecondary();
		researchThaumicRBronze.setParents(keyThaumicBronze).setParentsHidden(keyInfusion);
		researchThaumicElectrum.setParents(keyMaterial).setParentsHidden(keyThaumium);
		researchCotton.setParentsHidden(keyFabric);
		researchPrimalRobes.setParents(keyCotton, keyThaumicRBronze).setParentsHidden(keyThaumicElectrum, keyGoggles, keyNitor, keyInfusion, "INFUSIONENCHANTMENT", "FOCUSPRIMAL", "ARMORVOIDFORTRESS");

		researchRunicInfuser.setParents(keyMaterial).setParentsHidden(keyThaumium, keyAlumentum, keyNitor, keyVisPower);
		researchAniPiston.setParents(keyRunicInfuser).setSecondary();
		researchEnchSilverwood.setParents(keyRunicInfuser).setSecondary();

		researchWardenry.setParents(keyThaumRev).setRound().setSpecial().setLost().setItemTriggers(excubituraRose);

		researchExcubituraPaste.setConcealed().setParents(keyWardenry);
		researchWardencloth.setParents(keyExcubituraPaste, keyCotton);
		researchArmorWardencloth.setParents(keyWardencloth).setSecondary();

		researchExcubituraOil.setConcealed().setParents(keyExcubituraPaste).setParentsHidden(keyAlumentum);
		researchWardenChain.setParents(keyExcubituraOil);
		researchArmorWardenChain.setParents(keyWardenChain).setSecondary();

		researchPureOil.setParents(keyExcubituraOil).setParentsHidden(keyRunicInfuser);
		researchWardenSteel.setParents(keyPureOil);
		researchWardenicObsidian.setParents(keyPureOil).setParentsHidden(keyRunicInfuser).setSecondary().setHidden().setItemTriggers(new ItemStack(Blocks.obsidian));
		researchWardenPlate.setParents(keyWardenSteel).setParentsHidden(keyWardencloth, keyThaumicHammermill, keyEnchSilverwood);
		researchArmorWardenSteel.setParents(keyWardenPlate).setSecondary();


		//WIP
		researchQuartz.setParents(keyPureOil).setParentsHidden(keyInfusion);
		researchWardenCrystal.setParents(keyQuartz).setSecondary();
		researchWardenBronze.setParents(keyWardenCrystal).setParentsHidden(keyThaumicRBronze);
		researchWardenComposite.setParents(keyWardenBronze).setParentsHidden(keyThaumicBronze, keyWardenSteel);
		researchWardenCompositePlate.setParents(keyWardenComposite).setParentsHidden(keyThaumicHammermill).setSecondary();
		researchWardenCompositeFitting.setParents(keyWardenCompositePlate).setParentsHidden(keyWardencloth, keyEnchSilverwood);
		researchArmorWardenComposite.setParents(keyWardenCompositeFitting).setParentsHidden(keyGoggles).setSecondary();

		researchWardenCrystalAwakened.setParents(keyWardenCrystal);

		researchTransmutationNi.setParents(keyTransmutationFe).setSecondary();
		researchTransmutationAl.setParents(keyTransmutationFe).setSecondary();
		researchTransmutationNd.setParents(keyTransmutationFe).setSecondary();
		researchTransmutationZn.setParents(keyTransmutationSn).setSecondary();
		researchTransmutationAs.setParents(keyTransmutationSn).setSecondary();
		researchTransmutationSb.setParents(keyTransmutationSn).setSecondary();
		researchTransmutationBi.setParents(keyTransmutationSn, keyTransmutationPb).setSecondary();
		researchTransmutationW.setParents(keyTransmutationPb, keyTransmutationAu).setHidden().setSecondary();
		researchTransmutationLu.setParents(keyTransmutationNd).setSecondary();
		researchTransmutationPd.setParents(keyTransmutationNi, keyTransmutationAg).setSecondary();
		if (OreDictionary.doesOreNameExist("nuggetPlatinum")) {
			researchTransmutationPt.setParents(keyTransmutationAu).setParentsHidden(keyTransmutationPd).setHidden().setItemTriggers(ContentHelper.getPlatinumTriggers()).setSecondary();
			researchTransmutationOs.setParents(keyTransmutationW, keyTransmutationPt).setSecondary();
		} else {
			researchTransmutationOs.setParents(keyTransmutationW).setParentsHidden(keyTransmutationAu, keyTransmutationPd).setSecondary();
		}
		researchTransmutationIr.setParents(keyTransmutationOs).setHidden().setSecondary();

		researchClusterNi.setParents(keyClusterFe).setSecondary();
		if (OreDictionary.doesOreNameExist("oreAluminium")) {
			researchClusterAl.setParents(keyClusterFe).setSecondary();
		}
		researchClusterYPO.setParents(keyClusterFe).setSecondary();
		researchClusterZn.setParents(keyClusterSn).setSecondary();
		researchClusterBi.setParents(keyClusterSn, keyClusterPb).setSecondary();
		researchClusterCuAs.setParents(keyClusterSn, keyClusterCu).setSecondary();
		researchClusterCuSb.setParents(keyClusterSn, keyClusterCu).setSecondary();
		researchClusterW.setParents(keyClusterPb, keyClusterAu).setSecondary();
		if (OreDictionary.doesOreNameExist("orePlatinum")) {
			researchClusterPt.setParents(keyClusterNi).setParentsHidden(keyClusterAu).setSecondary();
			researchClusterIrOs.setParents(keyClusterPt).setParentsHidden(keyClusterW).setSecondary();
		} else {
			researchClusterIrOs.setParentsHidden(keyClusterW).setSecondary();
		}

		/*if (researchLevel == 0) { //EASY-MODE
			((FluxGearResearchItem) researchThaumRev).addSiblings(keyAniPiston);
			researchAniPiston.setStub();
			researchThaumicBronze.setSiblings(keyBronzeChain, keyArmorBronzeChain);
			researchBronzeChain.setStub();
			researchArmorBronzeChain.setStub();
			researchRunicInfuser.setSiblings(keyEnchSilverwood);
			researchEnchSilverwood.setStub();
			researchExcubituraPaste.setParentsHidden(keyCotton, keyGoggles).setSiblings(keyWardencloth, keyArmorWardencloth);
			researchWardencloth.setSecondary().setStub();
			researchArmorWardencloth.setStub();
			researchExcubituraOil.setParentsHidden(keyBronzeChain, keyGoggles).setSiblings(keyWardenChain, keyArmorWardenChain);
			researchWardenChain.setSecondary().setStub();
			researchArmorWardenChain.setStub();
			researchPureOil.setParentsHidden(keyThaumicBronze, keyInfusion).setSiblings(keyWardenSteel);
			researchWardenSteel.setStub();
			((FluxGearResearchItem) researchWardenPlate).addParentsHidden(keyGoggles).setSiblings(keyArmorWardenSteel);
			researchArmorWardenSteel.setStub();
		} else if (researchLevel == 2) { //HARD-MODE*/
			researchAniPiston.setParentsHidden("BELLOWS");
			researchWardencloth.setParentsHidden(keyCotton);
			researchArmorWardencloth.setParentsHidden(keyGoggles);
			researchWardenChain.setParentsHidden(keyBronzeChain);
			researchArmorWardenChain.setParentsHidden(keyArmorBronzeChain, keyGoggles);
			researchWardenSteel.setParentsHidden(keyThaumicBronze, keyInfusion);
			researchArmorWardenSteel.setParentsHidden(keyGoggles);
		/*} else { //NORMAL-MODE
			researchBronzeChain.setSiblings(keyArmorBronzeChain);
			researchArmorBronzeChain.setStub();
			researchWardencloth.setParentsHidden(keyCotton, keyGoggles).setSiblings(keyArmorWardencloth);
			researchArmorWardencloth.setStub();
			researchWardenChain.setParentsHidden(keyBronzeChain, keyGoggles).setSiblings(keyArmorWardenChain);
			researchArmorWardenChain.setStub();
			researchWardenSteel.setParentsHidden(keyThaumicBronze, keyInfusion);
			((FluxGearResearchItem) researchWardenPlate).addParentsHidden(keyGoggles).setSiblings(keyArmorWardenSteel);
			researchArmorWardenSteel.setStub();
		}*/
	}

	public static void registerResearch() {
		researchThaumRev.registerResearchItem();
		researchMaterial.registerResearchItem();
		//if (ContentHelper.enableAlloys()) {
			researchAlloys.registerResearchItem();
		//}

		researchThaumicBronze.registerResearchItem();
		researchBronzeChain.registerResearchItem();
		researchArmorBronzeChain.registerResearchItem();
		researchThaumicRBronze.registerResearchItem();
		researchThaumicElectrum.registerResearchItem();
		researchCotton.registerResearchItem();
		researchPrimalRobes.registerResearchItem();

		researchRunicInfuser.registerResearchItem();
		researchAniPiston.registerResearchItem();
		researchEnchSilverwood.registerResearchItem();

		researchWardenry.registerResearchItem();
		researchExcubituraPaste.registerResearchItem();
		researchWardencloth.registerResearchItem();
		researchArmorWardencloth.registerResearchItem();
		researchExcubituraOil.registerResearchItem();
		researchWardenChain.registerResearchItem();
		researchArmorWardenChain.registerResearchItem();
		researchPureOil.registerResearchItem();
		researchWardenSteel.registerResearchItem();
		researchWardenicObsidian.registerResearchItem();
		researchWardenPlate.registerResearchItem();
		researchArmorWardenSteel.registerResearchItem();
		researchQuartz.registerResearchItem();
		researchWardenCrystal.registerResearchItem();
		researchWardenBronze.registerResearchItem();
		researchWardenComposite.registerResearchItem();
		researchWardenCompositePlate.registerResearchItem();
		researchWardenCompositeFitting.registerResearchItem();
		researchArmorWardenComposite.registerResearchItem();
		researchWardenCrystalAwakened.registerResearchItem();

		researchTransmutationNi.registerResearchItem();
		researchTransmutationAl.registerResearchItem();
		researchTransmutationNd.registerResearchItem();
		researchTransmutationZn.registerResearchItem();
		researchTransmutationAs.registerResearchItem();
		researchTransmutationSb.registerResearchItem();
		researchTransmutationBi.registerResearchItem();
		researchTransmutationW.registerResearchItem();
		researchTransmutationLu.registerResearchItem();
		researchTransmutationPd.registerResearchItem();
		if (OreDictionary.doesOreNameExist("nuggetPlatinum")) {
			researchTransmutationPt.registerResearchItem();
		}
		researchTransmutationOs.registerResearchItem();
		researchTransmutationIr.registerResearchItem();

		researchClusterNi.registerResearchItem();
		if (OreDictionary.doesOreNameExist("oreAluminium")) {
			researchClusterAl.registerResearchItem();
		}
		researchClusterYPO.registerResearchItem();
		researchClusterZn.registerResearchItem();
		researchClusterBi.registerResearchItem();
		researchClusterCuAs.registerResearchItem();
		researchClusterCuSb.registerResearchItem();
		researchClusterW.registerResearchItem();
		if (OreDictionary.doesOreNameExist("orePlatinum")) {
			researchClusterPt.registerResearchItem();
		}
		researchClusterIrOs.registerResearchItem();

	}

	public static void setPages() {
		researchThaumRev.setPages(new ResearchPage("0"));
		researchMaterial.setPages(ContentHelper.getMaterialPages());
		researchAlloys.setPages(ContentHelper.getAlloyPages());

		researchThaumicBronze.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeRaw), new ResearchPage(recipeThaumicBronzeCoated), new ResearchPage(coatedThaumicBronze), new ResearchPage("1"));
		researchBronzeChain.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeChain));
		researchArmorBronzeChain.setPages(new ResearchPage("0"), new ResearchPage(recipeBronzeChainHelmet), new ResearchPage(recipeBronzeChainmail), new ResearchPage(recipeBronzeChainGreaves), new ResearchPage(recipeBronzeChainBoots));
		researchThaumicRBronze.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicRBronze));
		researchThaumicElectrum.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicElectrum));
		researchCotton.setPages(new ResearchPage("0"), new ResearchPage(recipeCottonFiber), new ResearchPage(recipeCottonFabric), new ResearchPage(recipeTreatedCotton), new ResearchPage(recipeEnchantedCotton));
		researchPrimalRobes.setPages(new ResearchPage("0"), new ResearchPage(recipePrimalGoggles), new ResearchPage(recipePrimalRobes), new ResearchPage(recipePrimalPants), new ResearchPage(recipePrimalBoots));

		researchRunicInfuser.setPages(new ResearchPage("0"), new ResearchPage(recipeArcaneSingularity), new ResearchPage(recipeStableSingularity));
		researchAniPiston.setPages(new ResearchPage("0"), new ResearchPage(recipeAniPiston));
		researchEnchSilverwood.setPages(new ResearchPage("0"), new ResearchPage(recipeEnchSilverwood));

		researchWardenry.setPages(new ResearchPage("0"), new ResearchPage("1"));

		researchExcubituraPaste.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraPaste));
		researchWardencloth.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraFabric), new ResearchPage("1"), new ResearchPage(recipeWardencloth));
		researchArmorWardencloth.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenclothSkullcap), new ResearchPage(recipeWardenclothTunic), new ResearchPage(recipeWardenclothPants), new ResearchPage(recipeWardenclothBoots));

		researchExcubituraOil.setPages(new ResearchPage("0"), new ResearchPage(recipeExcubituraOilUnproc), new ResearchPage(recipeExcubituraOil));
		researchWardenChain.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenBronzeChain), new ResearchPage(recipePrimalBronzeChain), new ResearchPage(recipeWardenBronzePlate));
		researchArmorWardenChain.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicChainHelmet), new ResearchPage(recipeWardenicChainmail), new ResearchPage(recipeWardenicChainGreaves), new ResearchPage(recipeWardenicChainBoots));

		researchPureOil.setPages(new ResearchPage("0"), new ResearchPage(recipePureOil));
		researchWardenSteel.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenSteel));
		researchWardenPlate.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenSteelChain), new ResearchPage(recipeWardenSteelChainOiled), new ResearchPage(recipeWardenSteelPlate), new ResearchPage(recipeDetailedSteelPlate), new ResearchPage(recipeRunicSteelPlate), new ResearchPage(recipesConsecratedSteelPlate));
		researchArmorWardenSteel.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicPlateHelmet), new ResearchPage(recipeWardenicChestplate), new ResearchPage(recipeWardenicPlateGreaves), new ResearchPage(recipeWardenicPlateBoots));
		if (Loader.isModLoaded("ThermalFoundation")) {
			researchWardenicObsidian.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicHardener), new ResearchPage(recipeWardenicHardenerAlt));
		} else {
			researchWardenicObsidian.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicHardener));
		}

		researchQuartz.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicQuartz), new ResearchPage(recipeWardenicQuartzDust), new ResearchPage(recipeWardenicQuartzReconst), new ResearchPage(recipeQuartzBlock), new ResearchPage(recipeWardenicQuartzInf), new ResearchPage(recipeQuartzChiseled), new ResearchPage(recipeQuartzPillar), new ResearchPage(recipeQuartzSlab), /*new ResearchPage(recipeQuartzStair),*/ new ResearchPage(recipeQuartzDeblock), new ResearchPage(recipeQuartzDeslab), /*new ResearchPage(recipeQuartzDestair),*/ new ResearchPage(recipeQuartzResetChiseled), new ResearchPage(recipeQuartzResetPillar));
		researchWardenCrystal.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicCrystal), new ResearchPage(recipeWardenicCrystalDust), new ResearchPage(recipeWardenicCrystalReconst), new ResearchPage("1"), new ResearchPage(recipeWardenicBinder), new ResearchPage(recipeBinderTiny), new ResearchPage(recipeBinderCombine));
		researchWardenBronze.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenBronze));
		researchWardenComposite.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicMetal), new ResearchPage(recipeWardenicCompositeRaw), new ResearchPage(recipeWardenicCompositeIngot));
		researchWardenCompositePlate.setPages(new ResearchPage("0"), new ResearchPage(recipeAluDenseTemp), new ResearchPage(recipeWardenicCompositePlate));
		researchWardenCompositeFitting.setPages(new ResearchPage("0"), new ResearchPage(recipeFittedCompositePlate), new ResearchPage(recipeDetailedCompositePlate), new ResearchPage(recipeRunicCompositePlate), new ResearchPage(recipeConsecratedCompositePlate), new ResearchPage(recipePrimalCompositePlate)/**/);
		researchArmorWardenComposite.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicCompositeHelmet), new ResearchPage(recipeWardenicCompositeChestplate), new ResearchPage(recipeWardenicCompositeGreaves), new ResearchPage(recipeWardenicCompositeBoots));

		researchWardenCrystalAwakened.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicCrystalAwakened));


		researchTransmutationNi.setPages(new ResearchPage("0"), new ResearchPage(recipeTransNickel));
		researchTransmutationAl.setPages(new ResearchPage("0"), new ResearchPage(recipeTransAluminium));
		researchTransmutationNd.setPages(new ResearchPage("0"), new ResearchPage(recipeTransNeodymium));
		researchTransmutationZn.setPages(new ResearchPage("0"), new ResearchPage(recipeTransZinc));
		researchTransmutationAs.setPages(new ResearchPage("0"), new ResearchPage(recipeTransArsenic));
		researchTransmutationSb.setPages(new ResearchPage("0"), new ResearchPage(recipeTransAntimony));
		researchTransmutationBi.setPages(new ResearchPage("0"), new ResearchPage(recipeTransBismuth));
		researchTransmutationW.setPages(new ResearchPage("0"), new ResearchPage(recipeTransTungsten));
		researchTransmutationLu.setPages(new ResearchPage("0"), new ResearchPage(recipeTransLutetium));
		researchTransmutationPd.setPages(new ResearchPage("0"), new ResearchPage(recipeTransPalladium));
		if (OreDictionary.doesOreNameExist("nuggetPlatinum")) {
			researchTransmutationPt.setPages(new ResearchPage("0"), new ResearchPage(recipeTransPlatinum));
		}
		researchTransmutationOs.setPages(new ResearchPage("0"), new ResearchPage(recipeTransOsmium));
		researchTransmutationIr.setPages(new ResearchPage("0"), new ResearchPage(recipeTransIridium));

		researchClusterNi.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterNickel));
		if (OreDictionary.doesOreNameExist("oreAluminium")) {
			researchClusterAl.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterAluminium));
		}
		researchClusterYPO.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterXenotime));
		researchClusterZn.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterZinc));
		researchClusterBi.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterBismuth));
		researchClusterCuAs.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterTennantite));
		researchClusterCuSb.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterTertahedrite));
		researchClusterW.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterTungsten));
		if (OreDictionary.doesOreNameExist("orePlatinum")) {
			researchClusterPt.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterPlatinum));
		}
		researchClusterIrOs.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterIridosmium));
	}

	public static void determineTempus() {
		// Thanks for the API hook, Myst!
		Object protoTempus = MagicBeesAPI.thaumcraftAspectTempus;
		if (protoTempus != null && protoTempus instanceof Aspect) {
			tempus = (Aspect) protoTempus;
		} else {
			tempus = new Aspect("tempus", 0xB68CFF, new Aspect[] {Aspect.VOID, Aspect.ORDER}, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/tempus.png"), 1);
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

	public static ItemStack ingotFe = new ItemStack(Items.iron_ingot);

	public static String redstone = "dustRedstone";
	public static String iMth = "ingotArsenoAntimonialBronze";

	public static String enchSilk = "itemEnchantedFabricSilk";
	public static String enchCotton = "itemEnchantedFabricCotton";
	public static String mirror = "itemMirroredGlass";
	public static String salisMundus = "dustSalisMundus";
	public static String voidSeed = "itemVoidSeed";

	public static String shardBalanced = "shardBalanced";

	public static String nHg = "itemDropQuicksilver";

	public static String iCu = "ingotCopper";
	public static String iSn = "ingotTin";
	public static String iBs = "ingotBrass";

	public static String dCu = "dustCopper";
	public static String dBs = "dustBrass";
	public static String dMth = "dustArsenoAntimonialBronze";

	public static String nBronze = "nuggetBronze";

	public static String salisPinch = "dustSalisMundusTiny";

	public static String paste = "itemExcubituraPaste";

	public static String wardencloth = "itemWardencloth";

	public static String oilExcu = "itemExcubituraOil";
	public static String chainTBronze = "itemChainThaumicBronze";
	public static String chainWBronze = "itemChainWardenicBronze";
	public static String chainPBronze = "itemChainPrimalBronze";

	public static String quartz = "gemQuartzWardenic";
}
