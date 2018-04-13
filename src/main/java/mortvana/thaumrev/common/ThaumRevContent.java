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

import mortvana.melteddashboard.ColorLibrary;
import mortvana.melteddashboard.intermod.baubles.util.DefaultBaubleData;
import mortvana.melteddashboard.intermod.thaumcraft.research.DummyResearchItem;
import mortvana.melteddashboard.intermod.thaumcraft.research.FluxGearResearchItem;
import mortvana.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.melteddashboard.item.ItemArmorFluxGear;
import mortvana.melteddashboard.util.helpers.ItemHelper;
import mortvana.melteddashboard.util.helpers.mod.ThaumcraftHelper;

import mortvana.thaumrev.block.*;
import mortvana.thaumrev.block.itemblock.*;
import mortvana.thaumrev.item.*;
import mortvana.thaumrev.util.*;
import mortvana.thaumrev.util.item.data.ThaumRevMaterialDataSet;
import mortvana.thaumrev.world.ThaumRevWorldGenerator;

import static thaumcraft.api.aspects.Aspect.*;
import static mortvana.melteddashboard.lib.ThaumcraftLibrary.*;
import static mortvana.melteddashboard.lib.ThermalLibrary.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;
import static mortvana.thaumrev.util.RecipeHelper.*;

public class ThaumRevContent {

	public static void preInit() {
		thaumicRevelationsTab = new FluxGearCreativeTab("Thaumic Revelations", "thaumicRevelations");
		createBlocks();
		createItems();
		registerBlocks();
		registerItems();
		setResearchLevel();
	}

	public static void init() {
		loadBlocks();
		loadItems();
		loadMaterials();
		loadArmor();
		loadTools();
		loadBaubles();

		GameRegistry.registerWorldGenerator(new ThaumRevWorldGenerator(), 1);

		loadInit();

		((FluxGearCreativeTab) thaumicRevelationsTab).setItem(wardenAmulet);
	}

	public static void postInit() {
		ResearchCategories.registerCategory(RESEARCH_KEY_MAIN, new ResourceLocation(RESOURCE_PREFIX, "textures/items/baubles/amuletWarden.png"), new ResourceLocation("thaumrev", "textures/gui/gui_researchbackthaumrev.png"));
		ResearchCategories.registerCategory(RESEARCH_KEY_METAL, new ResourceLocation(RESOURCE_PREFIX, "textures/items/material/ingotThaumicBronze.png"), new ResourceLocation("thaumrev", "textures/gui/gui_researchbackthaumrev.png"));
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
		blockOre = new BlockOre();

		blockWoodDecor = new BlockWoodDecor();
		blockStoneDecor = new BlockStoneDecor();

		blockStorageOre = new BlockStorageOre();
		blockStorageAlloy1 = new BlockStorageAlloy1();

		blockStoneSlab = new BlockStoneSlab();
		blockStoneSlabDouble = new BlockStoneSlab(blockStoneSlab);
		blockQuartzStairs = new BlockStairsWardenicQuartz();
	}

	public static void createItems() {
		generalItem = new ItemThaumRev();
		thaumicBauble = new ItemThaumicBauble();

		itemFocusPurity = new ItemFocusPurity();
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class, "blockThaumicPlant");
		GameRegistry.registerBlock(blockOre, ItemBlockOre.class, "blockOre");

		GameRegistry.registerBlock(blockStoneDecor, ItemBlockStoneDecor.class, "blockDecorStone");

		GameRegistry.registerBlock(blockStorageOre, ItemBlockStorageOre.class, "blockStorageOre");
		GameRegistry.registerBlock(blockStorageAlloy1, ItemBlockStorageAlloy1.class, "blockStorageAlloy1");

		GameRegistry.registerBlock(blockStoneSlab, ItemBlockStoneSlab.class, "stoneSlab");
		GameRegistry.registerBlock(blockStoneSlabDouble, ItemBlockStoneSlab.class, "stoneSlabDouble");
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

		RecipeHelper.registerOreDict(oreChalcocite, "oreCopper", "oreChalcocite");
		RecipeHelper.registerOreDict(oreSphalerite, "oreZinc", "oreSphalerite");
		RecipeHelper.registerOreDict(oreCassiterite, "oreTin", "oreCassiterite");
		RecipeHelper.registerOreDict(oreMillerite, "oreNickel", "oreMillerite");
		RecipeHelper.registerOreDict(oreNativeSilver, "oreSilver", "oreNativeSilver");
		RecipeHelper.registerOreDict(oreGalena, "oreLead", "oreGalena");
		RecipeHelper.registerOreDict(oreXenotime, "oreXenotime");
		RecipeHelper.registerOreDict(oreWolframite, "oreTungsten", "oreWolframite");
		RecipeHelper.registerOreDict(oreIridosmium, "oreIridosmium");
		RecipeHelper.registerOreDict(oreBismuthinite, "oreBismuth", "oreBismuthinite");
		RecipeHelper.registerOreDict(oreTennantite, "oreTennantite");
		RecipeHelper.registerOreDict(oreTetrahedrite, "oreTetrahedrite");
		RecipeHelper.registerOreDict(orePyrope, "orePyrope");
		RecipeHelper.registerOreDict(oreDioptase, "oreDioptase");
		RecipeHelper.registerOreDict(oreFluonicSapphire, "oreFluonicSapphire");

		wardenicObsidian = new ItemStack(blockStoneDecor, 1, 0);
		eldritchStone = new ItemStack(blockStoneDecor, 1, 1);
		wardenicQuartzBlock = new ItemStack(blockStoneDecor, 1, 2);
		wardenicQuartzChiseled = new ItemStack(blockStoneDecor, 1, 3);
		wardenicQuartzPillar = new ItemStack(blockStoneDecor, 1, 4);
		thaumicStone = new ItemStack(blockStoneDecor, 1, 5);
		infernalBlastBrick = new ItemStack(blockStoneDecor, 1, 6);
		shadowforgeBrick = new ItemStack(blockStoneDecor, 1, 7);

		RecipeHelper.registerOreDict(wardenicObsidian, "blockWardenicObsidian");
		RecipeHelper.registerOreDict(eldritchStone, "blockEldritchStone");
		for (int i = 2; i < 5; i++) {
			RecipeHelper.registerOreDict(new ItemStack(blockStoneDecor, 1, i), "blockQuartzWardenic");
		}
		RecipeHelper.registerOreDict(thaumicStone, "blockThaumicStone");
		RecipeHelper.registerOreDict(infernalBlastBrick, "blockInfernalBastFurnaceBrick");
		RecipeHelper.registerOreDict(shadowforgeBrick, "blockShadowforgeBrick");

		slabWardenicObsidian = new ItemStack(blockStoneSlab, 1, 0);
		slabEldritch = new ItemStack(blockStoneSlab, 1, 1);
		slabWardenicQuartz = new ItemStack(blockStoneSlab, 1, 2);

		RecipeHelper.registerOreDict(slabWardenicObsidian, "slabWardenicObsidian");
		RecipeHelper.registerOreDict(slabEldritch, "slabEldritchStone");
		RecipeHelper.registerOreDict(slabWardenicQuartz, "slabQuartzWardenic");
	}

	public static void loadItems() {
		arcaneSingularity = generalItem.addOreDictItem(0, "itemArcaneSingularity");
		stabilizedSingularity = generalItem.addOreDictItem(1, "itemStabilizedSingularity");
		animatedPiston = generalItem.addOreDictItem(2, "animatedPiston", "itemAnimatedPiston");
		enchantedSilverwood = generalItem.addOreDictItem(3, "enchantedSilverwood", "itemEnchantedSilverwood");
		consecratedSilverwood = generalItem.addOreDictItem(4, "consecratedSilverwood", "itemConsecratedSilverwood");
		cotton = generalItem.addOreDictItem(5, "cotton", "itemCotton");
		cottonFiber = generalItem.addOreDictItem(6, "cottonFiber", "itemCottonFiber");
		cottonFabric = generalItem.addOreDictItem(7, "cottonFabric", "itemCottonFabric");
		cottonTreated = generalItem.addOreDictItem(8, "cottonTreated", "itemCottonFabricTreated");
		cottonEnchanted = generalItem.addOreDictItem(9, "cottonEnchanted", enchCotton);
		thaumicBronzeChain = generalItem.addOreDictItem(10, "thaumicBronzeChain", chainTBronze);
		eldritchCog = generalItem.addOreDictItem(11, "eldritchCog", "itemEldritchCog");
		eldritchKeystone = generalItem.addOreDictItem(12, "eldritchKeystone", "itemEldritchKeystone");
		thistleLeaf = generalItem.addOreDictItem(13, "thistleLeaf", "itemThistleLeaf");
		thistleFlower = generalItem.addOreDictItem(14, "thistleFlower", "itemThistleFlower");
		aspectOrbReceptor = generalItem.addOreDictItem(15, "aspectOrbReceptor", "itemAspectOrbReceptor");

		ingotCopper = generalItem.addOreDictItem(200, iCu);
		ingotZinc = generalItem.addOreDictItem(201, "ingotZinc");
		ingotTin = generalItem.addOreDictItem(202, iSn);
		ingotNickel = generalItem.addOreDictItem(203, "ingotNickel");
		ingotSilver = generalItem.addOreDictItem(204, "ingotSilver");
		ingotLead = generalItem.addOreDictItem(205, "ingotLead");
		ingotLanthanides = generalItem.addOreDictItem(206, "ingotXenotime", 1, "ingotXenotimeLanthanides");
		ingotTungsten = generalItem.addOreDictItem(207, "ingotTungsten", 1);
		ingotIridium = generalItem.addOreDictItem(208, "ingotIridium", 2);
		ingotBismuth = generalItem.addOreDictItem(209, "ingotBismuth");
		ingotArsenicalBronze = generalItem.addOreDictItem(210, "ingotArsenicalBronze");
		ingotAntimonialBronze = generalItem.addOreDictItem(211, "ingotAntimonialBronze");
		gemPyrope = generalItem.addOreDictItem(212, "gemPyrope", 2);
		gemDioptase = generalItem.addOreDictItem(213, "gemDioptase", 2);
		gemFluonicSapphire = generalItem.addOreDictItem(214, "gemFluonicSapphire", 2);
		ingotOsmium = generalItem.addOreDictItem(215, "ingotOsmium", 1);
		ingotNeodymium = generalItem.addOreDictItem(216, "ingotNeodymium", 1);
		ingotLutetium = generalItem.addOreDictItem(217, "ingotLutetium", 2);
		ingotPalladium = generalItem.addOreDictItem(218, "ingotPalladium", 1);
		ingotIridosmium = generalItem.addOreDictItem(219, "ingotIridosmium", 2);
		ingotAluminium = generalItem.addOreDictItem(220, "ingotAluminium");
		ingotXenotimeJunk = generalItem.addOreDictItem(221, "ingotLanthanides", 1, "ingotXenotimeWaste");

		ingotBrass = generalItem.addOreDictItem(225, "ingotBrass");
		ingotBronze = generalItem.addOreDictItem(226, "ingotBronze");
		ingotBismuthBronze = generalItem.addOreDictItem(227, "ingotBismuthBronze");
		ingotMithril = generalItem.addOreDictItem(228, "ingotMithril", 1, "ingotArsenoAntimonialBronze");
		ingotAluminiumBronze = generalItem.addOreDictItem(229, "ingotAluminiumBronze");
		ingotCupronickel = generalItem.addOreDictItem(230, "ingotCupronickel");
		ingotRiftishBronze = generalItem.addOreDictItem(231, "ingotRiftishBronze", 1);
		ingotConstantan = generalItem.addOreDictItem(232, "ingotConstantan");
		ingotInvar = generalItem.addOreDictItem(233, "ingotInvar");
		ingotElectrum = generalItem.addOreDictItem(234, "ingotElectrum");
		ingotWardenicMetal = generalItem.addOreDictItem(235, "ingotWardenicMetal");
		ingotDullRedsolder = generalItem.addOreDictItem(236, "ingotDullRedsolder");
		ingotRedsolder = generalItem.addOreDictItem(237, "ingotRedsolder");

		ingotThaumicBronze = generalItem.addOreDictItem(250, "ingotThaumicBronze");
		ingotOsLu = generalItem.addOreDictItem(251, "ingotOsmiumLutetium", 2);
		gemFluonicPyroptase = generalItem.addOreDictItem(252, "gemFluonicPyroptase", 3);

		ingotArsenic = generalItem.addOreDictItem(255, "ingotArsenic");
		ingotAntimony = generalItem.addOreDictItem(256, "ingotAntimony");

		ingotThaumicElectrum = generalItem.addOreDictItem(260, "ingotThaumicElectrum", 1);
		ingotThaumicRiftishBronze = generalItem.addOreDictItem(261, "ingotThaumicRiftishBronze", 1);
		ingotSteel = generalItem.addOreDictItem(262, "ingotSteel", 1);
		ingotVoidbrass = generalItem.addOreDictItem(263, "ingotVoidbrass", 1);
		ingotVoidsteel = generalItem.addOreDictItem(264, "ingotVoidsteel", 1);
		ingotVoidtungsten = generalItem.addOreDictItem(265, "ingotVoidtungsten", 2);

		ingotWardenicBronze = generalItem.addOreDictItem(270, "ingotWardenicBronze");
		ingotWardenicSteel = generalItem.addOreDictItem(271, "ingotWardenicSteel", 1);
		ingotWardenicRiftishBronze = generalItem.addOreDictItem(272, "ingotWardenicRiftishBronze", 1);
		gemWardenicQuartz = generalItem.addOreDictItem(273, "gemWardenicQuartz", 1);
		gemWardenicCrystal = generalItem.addOreDictItem(274, "gemWardenicCrystal", 2);
		gemWardenicCrystalActivated = generalItem.addOreDictItem(275, "gemWardenicCrystalActivated", 2);
		ingotWardenicComposite = generalItem.addOreDictItem(276, "ingotWardenicComposite", 2);

		ingotRedsolderArcane = generalItem.addOreDictItem(280, "ingotArcaneRedsolder");
		ingotRedbronze = generalItem.addOreDictItem(281, "ingotRedbronze");
		ingotRedbronzeHardened = generalItem.addOreDictItem(282, "ingotHardenedRedbronze", 1);
		ingotFluxsteel = generalItem.addOreDictItem(283, "ingotFluxsteel", 1);
		gemRedquartz = generalItem.addOreDictItem(284, "gemRedquartz", 1);
		ingotFluxedTungsten = generalItem.addOreDictItem(285, "ingotFluxedTungsten", 2);
		ingotMagneoturgicComposite = generalItem.addOreDictItem(286, "ingotMagneoturgicComposite", 2);
		ingotFluxedComposite = generalItem.addOreDictItem(287, "ingotFluxedComposite", 2);
		ingotResonantFluxedComposite = generalItem.addOreDictItem(288, "ingotResonantFluxedComposite", 3);
		
		nuggetCopper = generalItem.addOreDictItem(300, "nuggetCopper");
		nuggetZinc = generalItem.addOreDictItem(301, "nuggetZinc");
		nuggetTin = generalItem.addOreDictItem(302, "nuggetTin");
		nuggetNickel = generalItem.addOreDictItem(303, "nuggetNickel");
		nuggetSilver = generalItem.addOreDictItem(304, "nuggetSilver");
		nuggetLead = generalItem.addOreDictItem(305, "nuggetLead");
		nuggetLanthanides = generalItem.addOreDictItem(306, "nuggetXenotime", 1, "nuggetXenotimeLanthanides");
		nuggetTungsten = generalItem.addOreDictItem(307, "nuggetTungsten", 1);
		nuggetIridium = generalItem.addOreDictItem(308, "nuggetIridium", 2);
		nuggetBismuth = generalItem.addOreDictItem(309, "nuggetBismuth");
		nuggetArsenicalBronze = generalItem.addOreDictItem(310, "nuggetArsenicalBronze");
		nuggetAntimonialBronze = generalItem.addOreDictItem(311, "nuggetAntimonialBronze");
		shardPyrope = generalItem.addOreDictItem(312, "shardPyrope", 2, "nuggetPyrope");
		shardDioptase = generalItem.addOreDictItem(313, "shardDioptase", 2, "nuggetDioptase");
		shardFluonicSapphire = generalItem.addOreDictItem(314, "shardFluonicSapphire", 2, "nuggetFluonicSapphire");
		nuggetOsmium = generalItem.addOreDictItem(315, "nuggetOsmium", 1);
		nuggetNeodymium = generalItem.addOreDictItem(316, "nuggetNeodymium", 1);
		nuggetLutetium = generalItem.addOreDictItem(317, "nuggetLutetium", 2);
		nuggetPalladium = generalItem.addOreDictItem(318, "nuggetPalladium", 1);
		nuggetIridosmium = generalItem.addOreDictItem(319, "nuggetIridosmium", 2);
		nuggetAluminium = generalItem.addOreDictItem(320, "nuggetAluminium");
		nuggetXenotimeJunk = generalItem.addOreDictItem(321, "nuggetLanthanides", 1, "nuggetXenotimeWaste");

		nuggetBrass = generalItem.addOreDictItem(325, "nuggetBrass");
		nuggetBronze = generalItem.addOreDictItem(326, nBronze);
		nuggetBismuthBronze = generalItem.addOreDictItem(327, "nuggetBismuthBronze");
		nuggetMithril = generalItem.addOreDictItem(328, "nuggetMithril", 1, "nuggetArsenoAntimonialBronze");
		nuggetAluminiumBronze = generalItem.addOreDictItem(329, "nuggetAluminiumBronze");
		nuggetCupronickel = generalItem.addOreDictItem(330, "nuggetCupronickel");
		nuggetRiftishBronze = generalItem.addOreDictItem(331, "nuggetRiftishBronze", 1);
		nuggetConstantan = generalItem.addOreDictItem(332, "nuggetConstantan");
		nuggetInvar = generalItem.addOreDictItem(333, "nuggetInvar");
		nuggetElectrum = generalItem.addOreDictItem(334, "nuggetElectrum");
		nuggetWardenicMetal = generalItem.addOreDictItem(335, "nuggetWardenicMetal");
		nuggetDullRedsolder = generalItem.addOreDictItem(336, "nuggetDullRedsolder");
		nuggetRedsolder = generalItem.addOreDictItem(337, "nuggetRedsolder");

		nuggetThaumicBronze = generalItem.addOreDictItem(350, "nuggetThaumicBronze");
		nuggetOsLu = generalItem.addOreDictItem(351, "nuggetOsmiumLutetium", 2);
		shardFluonicPyroptase = generalItem.addOreDictItem(352, "shardFluonicPyroptase", 3, "nuggetFluonicPyroptase");

		nuggetArsenic = generalItem.addOreDictItem(355, "nuggetArsenic");
		nuggetAntimony = generalItem.addOreDictItem(356, "nuggetAntimony");

		nuggetThaumicElectrum = generalItem.addOreDictItem(360, "nuggetThaumicElectrum", 1);
		nuggetThaumicRiftishBronze = generalItem.addOreDictItem(361, "nuggetThaumicRiftishBronze", 1);
		nuggetSteel = generalItem.addOreDictItem(362, "nuggetSteel", 1);
		nuggetVoidbrass = generalItem.addOreDictItem(363, "nuggetVoidbrass", 1);
		nuggetVoidsteel = generalItem.addOreDictItem(364, "nuggetVoidsteel", 1);
		nuggetVoidtungsten = generalItem.addOreDictItem(365, "nuggetVoidtungsten", 2);

		nuggetWardenicBronze = generalItem.addOreDictItem(370, "nuggetWardenicBronze");
		nuggetWardenicSteel = generalItem.addOreDictItem(371, "nuggetWardenicSteel", 1);
		nuggetWardenicRiftishBronze = generalItem.addOreDictItem(372, "nuggetWardenicRiftishBronze", 1);
		shardWardenicQuartz = generalItem.addOreDictItem(373, "shardWardenicQuartz", 1, "nuggetWardenicQuartz");
		shardWardenicCrystal = generalItem.addOreDictItem(374, "shardWardenicCrystal", 2, "nuggetWardenicCrystal");
		shardWardenicCrystalActivated = generalItem.addOreDictItem(375, "shardWardenicCrystalActivated", 2, "nuggetWardenicCrystalActivated");
		nuggetWardenicComposite = generalItem.addOreDictItem(376, "nuggetWardenicComposite", 2);

		nuggetRedsolderArcane = generalItem.addOreDictItem(380, "nuggetArcaneRedsolder");
		nuggetRedbronze = generalItem.addOreDictItem(381, "nuggetRedbronze");
		nuggetRedbronzeHardened = generalItem.addOreDictItem(382, "nuggetHardenedRedbronze", 1);
		nuggetFluxsteel = generalItem.addOreDictItem(383, "nuggetFluxsteel", 1);
		shardRedquartz = generalItem.addOreDictItem(384, "shardRedquartz", 1);
		nuggetFluxedTungsten = generalItem.addOreDictItem(385, "nuggetFluxedTungsten", 2);
		nuggetMagneoturgicComposite = generalItem.addOreDictItem(386, "nuggetMagneoturgicComposite", 2);
		nuggetFluxedComposite = generalItem.addOreDictItem(387, "nuggetFluxedComposite", 2);
		nuggetResonantFluxedComposite = generalItem.addOreDictItem(388, "nuggetResonantFluxedComposite", 3);

		dustCopper = generalItem.addOreDictItem(400, "dustCopper");
		dustZinc = generalItem.addOreDictItem(401, "dustZinc");
		dustTin = generalItem.addOreDictItem(402, "dustTin");
		dustNickel = generalItem.addOreDictItem(403, "dustNickel");
		dustSilver = generalItem.addOreDictItem(404, "dustSilver");
		dustLead = generalItem.addOreDictItem(405, "dustLead");
		dustLanthanides = generalItem.addOreDictItem(406, "dustXenotime", 1, "dustXenotimeLanthanides");
		dustTungsten = generalItem.addOreDictItem(407, "dustTungsten", 1);
		dustIridium = generalItem.addOreDictItem(408, "dustIridium", 2);
		dustBismuth = generalItem.addOreDictItem(409, "dustBismuth");
		dustArsenicalBronze = generalItem.addOreDictItem(410, "dustArsenicalBronze");
		dustAntimonialBronze = generalItem.addOreDictItem(411, "dustAntimonialBronze");
		dustPyrope = generalItem.addOreDictItem(412, "dustPyrope", 2);
		dustDioptase = generalItem.addOreDictItem(413, "dustDioptase", 2);
		dustFluonicSapphire = generalItem.addOreDictItem(414, "dustFluonicSapphire", 2);
		dustOsmium = generalItem.addOreDictItem(415, "dustOsmium", 1);
		dustNeodymium = generalItem.addOreDictItem(416, "dustNeodymium", 1);
		dustLutetium = generalItem.addOreDictItem(417, "dustLutetium", 2);
		dustPalladium = generalItem.addOreDictItem(418, "dustPalladium", 1);
		dustIridosmium = generalItem.addOreDictItem(419, "dustIridosmium", 2);
		dustAluminium = generalItem.addOreDictItem(420, "dustAluminium");
		dustXenotimeJunk = generalItem.addOreDictItem(421, "dustLanthanides", 1, "dustXenotimeWaste");

		dustBrass = generalItem.addOreDictItem(425, "dustBrass");
		dustBronze = generalItem.addOreDictItem(426, "dustBronze");
		dustBismuthBronze = generalItem.addOreDictItem(427, "dustBismuthBronze");
		dustMithril = generalItem.addOreDictItem(428, "dustMithril", 1, "dustArsenoAntimonialBronze");
		dustAluminiumBronze = generalItem.addOreDictItem(429, "dustAluminiumBronze");
		dustCupronickel = generalItem.addOreDictItem(430, "dustCupronickel");
		dustRiftishBronze = generalItem.addOreDictItem(431, "dustRiftishBronze", 1);
		dustConstantan = generalItem.addOreDictItem(432, "dustConstantan");
		dustInvar = generalItem.addOreDictItem(433, "dustInvar");
		dustElectrum = generalItem.addOreDictItem(434, "dustElectrum");
		dustWardenicMetal = generalItem.addOreDictItem(435, "dustWardenicMetal");
		dustDullRedsolder = generalItem.addOreDictItem(436, "dustDullRedsolder");
		dustRedsolder = generalItem.addOreDictItem(437, "dustRedsolder");

		dustThaumicBronze = generalItem.addOreDictItem(450, "dustThaumicBronze");
		dustOsLu = generalItem.addOreDictItem(451, "dustOsmiumLutetium", 2);
		dustFluonicPyroptase = generalItem.addOreDictItem(452, "dustFluonicPyroptase", 3);
		dustArsenic = generalItem.addOreDictItem(455, "dustArsenic");
		dustAntimony = generalItem.addOreDictItem(456, "dustAntimony");

		dustThaumicElectrum = generalItem.addOreDictItem(460, "dustThaumicElectrum", 1);
		dustThaumicRiftishBronze = generalItem.addOreDictItem(461, "dustThaumicRiftishBronze", 1);
		dustSteel = generalItem.addOreDictItem(462, "dustSteel", 1);
		dustVoidbrass = generalItem.addOreDictItem(463, "dustVoidbrass", 1);
		dustVoidsteel = generalItem.addOreDictItem(464, "dustVoidsteel", 1);
		dustVoidtungsten = generalItem.addOreDictItem(465, "dustVoidtungsten", 2);

		dustWardenicBronze = generalItem.addOreDictItem(470, "dustWardenicBronze");
		dustWardenicSteel = generalItem.addOreDictItem(471, "dustWardenicSteel", 1);
		dustWardenicRiftishBronze = generalItem.addOreDictItem(472, "dustWardenicRiftishBronze", 1);
		dustWardenicQuartz = generalItem.addOreDictItem(473, "dustWardenicQuartz", 1);
		dustWardenicCrystal = generalItem.addOreDictItem(474, "dustWardenicCrystal", 2);
		dustWardenicCrystalActivated = generalItem.addOreDictItem(475, "dustWardenicCrystalActivated", 2);
		dustWardenicComposite = generalItem.addOreDictItem(476, "dustWardenicComposite", 2);

		dustRedsolderArcane = generalItem.addOreDictItem(480, "dustArcaneRedsolder");
		dustRedbronze = generalItem.addOreDictItem(481, "dustRedbronze");
		dustRedbronzeHardened = generalItem.addOreDictItem(482, "dustHardenedRedbronze", 1);
		dustFluxsteel = generalItem.addOreDictItem(483, "dustFluxsteel", 1);
		dustRedquartz = generalItem.addOreDictItem(484, "dustRedquartz", 1);
		dustFluxedTungsten = generalItem.addOreDictItem(485, "dustFluxedTungsten", 2);
		dustMagneoturgicComposite = generalItem.addOreDictItem(486, "dustMagneoturgicComposite", 2);
		dustFluxedComposite = generalItem.addOreDictItem(487, "dustFluxedComposite", 2);
		dustResonantFluxedComposite = generalItem.addOreDictItem(488, "dustResonantFluxedComposite", 3);

		tinyCopper = generalItem.addOreDictItem(500, "dustCopperTiny");
		tinyZinc = generalItem.addOreDictItem(501, "dustZincTiny");
		tinyTin = generalItem.addOreDictItem(502, "dustTinTiny");
		tinyNickel = generalItem.addOreDictItem(503, "dustNickelTiny");
		tinySilver = generalItem.addOreDictItem(504, "dustSilverTiny");
		tinyLead = generalItem.addOreDictItem(505, "dustLeadTiny");
		tinyLanthanides = generalItem.addOreDictItem(506, "dustXenotimeTiny", 1, "dustXenotimeLanthanidesTiny");
		tinyTungsten = generalItem.addOreDictItem(507, "dustTungstenTiny", 1);
		tinyIridium = generalItem.addOreDictItem(508, "dustIridiumTiny", 2);
		tinyBismuth = generalItem.addOreDictItem(509, "dustBismuthTiny");
		tinyArsenicalBronze = generalItem.addOreDictItem(510, "dustArsenicalBronzeTiny");
		tinyAntimonialBronze = generalItem.addOreDictItem(511, "dustAntimonialBronzeTiny");
		tinyPyrope = generalItem.addOreDictItem(512, "dustPyropeTiny", 2);
		tinyDioptase = generalItem.addOreDictItem(513, "dustDioptaseTiny", 2);
		tinyFluonicSapphire = generalItem.addOreDictItem(514, "dustFluonicSapphireTiny", 2);
		tinyOsmium = generalItem.addOreDictItem(515, "dustOsmiumTiny", 1);
		tinyNeodymium = generalItem.addOreDictItem(516, "dustNeodymiumTiny", 1);
		tinyLutetium = generalItem.addOreDictItem(517, "dustLutetiumTiny", 2);
		tinyPalladium = generalItem.addOreDictItem(518, "dustPalladiumTiny", 1);
		tinyIridosmium = generalItem.addOreDictItem(519, "dustIridosmiumTiny", 2);
		tinyAluminium = generalItem.addOreDictItem(520, "dustAluminiumTiny");
		tinyXenotimeJunk = generalItem.addOreDictItem(521, "dustLanthanidesTiny", 1, "dustXenotimeWasteTiny");

		tinyBrass = generalItem.addOreDictItem(525, "dustBrassTiny");
		tinyBronze = generalItem.addOreDictItem(526, "dustBronzeTiny");
		tinyBismuthBronze = generalItem.addOreDictItem(527, "dustBismuthBronzeTiny");
		tinyMithril = generalItem.addOreDictItem(528, "dustMithrilTiny", 1, "dustArsenoAntimonialBronzeTiny");
		tinyAluminiumBronze = generalItem.addOreDictItem(529, "dustAluminiumBronzeTiny");
		tinyCupronickel = generalItem.addOreDictItem(530, "dustCupronickelTiny");
		tinyRiftishBronze = generalItem.addOreDictItem(531, "dustRiftishBronzeTiny", 1);
		tinyConstantan = generalItem.addOreDictItem(532, "dustConstantanTiny");
		tinyInvar = generalItem.addOreDictItem(533, "dustInvarTiny");
		tinyElectrum = generalItem.addOreDictItem(534, "dustElectrumTiny");
		tinyWardenicMetal = generalItem.addOreDictItem(535, "dustWardenicMetalTiny");
		tinyDullRedsolder = generalItem.addOreDictItem(536, "dustDullRedsolderTiny");
		tinyRedsolder = generalItem.addOreDictItem(537, "dustRedsolderTiny");

		tinyThaumicBronze = generalItem.addOreDictItem(550, "dustThaumicBronzeTiny");
		tinyOsLu = generalItem.addOreDictItem(551, "dustOsmiumLutetiumTiny", 2);
		tinyFluonicPyroptase = generalItem.addOreDictItem(552, "dustFluonicPyroptaseTiny", 3);

		tinyArsenic = generalItem.addOreDictItem(555, "dustArsenicTiny");
		tinyAntimony = generalItem.addOreDictItem(556, "dustAntimonyTiny");

		tinyThaumicElectrum = generalItem.addOreDictItem(560, "dustThaumicElectrumTiny", 1);
		tinyThaumicRiftishBronze = generalItem.addOreDictItem(561, "dustThaumicRiftishBronzeTiny", 1);
		tinySteel = generalItem.addOreDictItem(562, "dustSteelTiny", 1);
		tinyVoidbrass = generalItem.addOreDictItem(563, "dustVoidbrassTiny", 1);
		tinyVoidsteel = generalItem.addOreDictItem(564, "dustVoidsteelTiny", 1);
		tinyVoidtungsten = generalItem.addOreDictItem(565, "dustVoidtungstenTiny", 2);

		tinyWardenicBronze = generalItem.addOreDictItem(570, "dustWardenicBronzeTiny");
		tinyWardenicSteel = generalItem.addOreDictItem(571, "dustWardenicSteelTiny", 1);
		tinyWardenicRiftishBronze = generalItem.addOreDictItem(572, "dustWardenicRiftishBronzeTiny", 1);
		tinyWardenicQuartz = generalItem.addOreDictItem(573, "dustWardenicQuartzTiny", 1);
		tinyWardenicCrystal = generalItem.addOreDictItem(574, "dustWardenicCrystalTiny", 2);
		tinyWardenicCrystalActivated = generalItem.addOreDictItem(575, "dustWardenicCrystalActivatedTiny", 2);
		tinyWardenicComposite = generalItem.addOreDictItem(576, "dustWardenicCompositeTiny", 2);

		tinyRedsolderArcane = generalItem.addOreDictItem(580, "dustArcaneRedsolderTiny");
		tinyRedbronze = generalItem.addOreDictItem(581, "dustRedbronzeTiny");
		tinyRedbronzeHardened = generalItem.addOreDictItem(582, "dustHardenedRedbronzeTiny", 1);
		tinyFluxsteel = generalItem.addOreDictItem(583, "dustFluxsteelTiny", 1);
		tinyRedquartz = generalItem.addOreDictItem(584, "dustRedquartzTiny", 1);
		tinyFluxedTungsten = generalItem.addOreDictItem(585, "dustFluxedTungstenTiny", 2);
		tinyMagneoturgicComposite = generalItem.addOreDictItem(586, "dustMagneoturgicCompositeTiny", 2);
		tinyFluxedComposite = generalItem.addOreDictItem(587, "dustFluxedCompositeTiny", 2);
		tinyResonantFluxedComposite = generalItem.addOreDictItem(588, "dustResonantFluxedCompositeTiny", 3);

		plateCopper = generalItem.addOreDictItem(600, "plateCopper");
		plateZinc = generalItem.addOreDictItem(601, "plateZinc");
		plateTin = generalItem.addOreDictItem(602, "plateTin");
		plateNickel = generalItem.addOreDictItem(603, "plateNickel");
		plateSilver = generalItem.addOreDictItem(604, "plateSilver");
		plateLead = generalItem.addOreDictItem(605, "plateLead");
		plateTungsten = generalItem.addOreDictItem(607, "plateTungsten", 1);
		plateIridium = generalItem.addOreDictItem(608, "plateIridium", 2);
		plateBismuth = generalItem.addOreDictItem(609, "plateBismuth");
		plateArsenicalBronze = generalItem.addOreDictItem(610, "plateArsenicalBronze");
		plateAntimonialBronze = generalItem.addOreDictItem(611, "plateAntimonialBronze");
		plateOsmium = generalItem.addOreDictItem(615, "plateOsmium", 1);
		plateNeodymium = generalItem.addOreDictItem(616, "plateNeodymium", 1);
		plateLutetium = generalItem.addOreDictItem(617, "plateLutetium", 2);
		platePalladium = generalItem.addOreDictItem(618, "platePalladium", 1);
		plateIridosmium = generalItem.addOreDictItem(619, "plateIridosmium", 2);
		plateAluminium = generalItem.addOreDictItem(620, "plateAluminium");

		plateBrass = generalItem.addOreDictItem(625, "plateBrass");
		plateBronze = generalItem.addOreDictItem(626, "plateBronze");
		plateBismuthBronze = generalItem.addOreDictItem(627, "plateBismuthBronze");
		plateMithril = generalItem.addOreDictItem(628, "plateMithril", 1, "plateArsenoAntimonialBronze");
		plateAluminiumBronze = generalItem.addOreDictItem(629, "plateAluminiumBronze");
		plateCupronickel = generalItem.addOreDictItem(630, "plateCupronickel");
		plateRiftishBronze = generalItem.addOreDictItem(631, "plateRiftishBronze", 1);
		plateConstantan = generalItem.addOreDictItem(632, "plateConstantan");
		plateInvar = generalItem.addOreDictItem(633, "plateInvar");
		plateElectrum = generalItem.addOreDictItem(634, "plateElectrum");
		plateWardenicMetal = generalItem.addOreDictItem(635, "plateWardenicMetal");
		plateDullRedsolder = generalItem.addOreDictItem(636, "plateDullRedsolder");
		plateRedsolder = generalItem.addOreDictItem(637, "plateRedsolder");

		plateThaumicBronze = generalItem.addOreDictItem(650, "plateThaumicBronze");
		plateOsLu = generalItem.addOreDictItem(651, "plateOsmiumLutetium", 2);

		plateThaumicElectrum = generalItem.addOreDictItem(660, "plateThaumicElectrum", 1);
		plateThaumicRiftishBronze = generalItem.addOreDictItem(661, "plateThaumicRiftishBronze", 1);
		plateSteel = generalItem.addOreDictItem(662, "plateSteel", 1);
		plateVoidbrass = generalItem.addOreDictItem(663, "plateVoidbrass", 1);
		plateVoidsteel = generalItem.addOreDictItem(664, "plateVoidsteel", 1);
		plateVoidtungsten = generalItem.addOreDictItem(665, "plateVoidtungsten", 2);

		plateWardenicBronze = generalItem.addOreDictItem(670, "plateWardenicBronze");
		plateWardenicSteel = generalItem.addOreDictItem(671, "plateWardenicSteel", 1);
		plateWardenicRiftishBronze = generalItem.addOreDictItem(672, "plateWardenicRiftishBronze", 1);
		plateWardenicComposite = generalItem.addOreDictItem(676, "plateWardenicComposite",2);

		plateRedsolderArcane = generalItem.addOreDictItem(680, "plateArcaneRedsolder");
		plateRedbronze = generalItem.addOreDictItem(681, "plateRedbronze");
		plateRedbronzeHardened = generalItem.addOreDictItem(682, "plateHardenedRedbronze", 1);
		plateFluxsteel = generalItem.addOreDictItem(683, "plateFluxsteel", 1);
		plateFluxedTungsten = generalItem.addOreDictItem(685, "plateFluxedTungsten", 2);
		plateMagneoturgicComposite = generalItem.addOreDictItem(686, "plateMagneoturgicComposite", 2);
		plateFluxedComposite = generalItem.addOreDictItem(687, "plateFluxedComposite", 2);
		plateResonantFluxedComposite = generalItem.addOreDictItem(688, "plateResonantFluxedComposite", 3);

		rawBrass = generalItem.addOreDictItem(730, "ingotBrassRaw");
		rawBronze = generalItem.addOreDictItem(731, "ingotBronzeRaw");
		rawBismuthBronze = generalItem.addOreDictItem(732, "ingotBismuthBronzeRaw");
		rawMithril = generalItem.addOreDictItem(733, "ingotMithrilRaw", 1, "ingotArsenoAntimonialBronzeRaw");
		rawAluminiumBronze = generalItem.addOreDictItem(734, "ingotAluminiumBronzeRaw");
		rawCupronickel = generalItem.addOreDictItem(735, "ingotCupronickelRaw");
		rawRiftishBronze = generalItem.addOreDictItem(736, "ingotRiftishBronzeRaw");
		rawConstantan = generalItem.addOreDictItem(737, "ingotConstantanRaw");
		rawInvar = generalItem.addOreDictItem(738, "ingotInvarRaw");
		rawElectrum = generalItem.addOreDictItem(739, "ingotElectrumRaw");
		rawWardenicMetal = generalItem.addOreDictItem(740, "ingotWardenicMetalRaw");
		rawRedsolder = generalItem.addOreDictItem(741, "ingotRedsolderRaw");

		rawThaumicBronze = generalItem.addOreDictItem(755, "ingotThaumicBronzeRaw");
		rawOsLu = generalItem.addOreDictItem(756, "ingotOsmiumLutetiumRaw", 2);
		blendFluonicPyrotase = generalItem.addOreDictItem(757, "blendFluonicPyroptase", 2, "itemBlendFluonicPyroptase");

		rawWardenicComposite = generalItem.addOreDictItem(760, "ingotWardenicCompositeRaw", 2);
		rawMagneoturgicComposite = generalItem.addOreDictItem(761, "ingotMagneoturgicCompositeRaw", 2);

		coatedThaumicBronze = generalItem.addOreDictItem(770, "ingotThaumicBronzeCoated");
		coatedOsLu = generalItem.addOreDictItem(771, "ingotOsmiumLutetiumCoated", 2);

		firedThaumicBronze = generalItem.addOreDictItem(775, "ingotThaumicBronzeFired");
		firedOsLu = generalItem.addOreDictItem(776, "ingotOsmiumLutetiumFired", 2);

		carbonSlag = generalItem.addOreDictItem(780, "itemSlagCarbon");
		ceramicSlag = generalItem.addOreDictItem(781, "itemSlagCeramic");
		thaumicSlag = generalItem.addOreDictItem(782, "itemSlagThaumic");
		fluonicSlag = generalItem.addOreDictItem(783, "itemSlagFluonic");

		clusterZinc = generalItem.addOreDictItem(801, "clusterZinc");
		clusterAluminium = generalItem.addOreDictItem(802, "clusterAluminium");
		clusterNickel = generalItem.addOreDictItem(803, "clusterNickel");
		clusterPlatinum = generalItem.addOreDictItem(804, "clusterPlatinum");
		clusterXenotime = generalItem.addOreDictItem(806, "clusterXenotime");
		clusterTungsten = generalItem.addOreDictItem(807, "clusterTungsten");
		clusterIridosmium = generalItem.addOreDictItem(808, "clusterIridosmium");
		clusterBismuth = generalItem.addOreDictItem(809, "clusterBismuth");
		clusterTennantite = generalItem.addOreDictItem(810, "clusterTennantite");
		clusterTetrahedrite = generalItem.addOreDictItem(811, "clusterTetrahedrite");

		dustAer = generalItem.addOreDictItem(890, "dustAer");
		dustIgnis = generalItem.addOreDictItem(891, "dustIgnis");
		dustAqua = generalItem.addOreDictItem(892, "dustAqua");
		dustTerra = generalItem.addOreDictItem(893, "dustTerra");
		dustOrdo = generalItem.addOreDictItem(894, "dustOrdo");
		dustPerditio = generalItem.addOreDictItem(895, "dustPerditio");
		dustIron = generalItem.addOreDictItem(896, "dustIron");
		dustGold = generalItem.addOreDictItem(897, "dustGold");
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
		tinyIron = generalItem.addOreDictItem(946, "dustIronTiny");
		tinyGold = generalItem.addOreDictItem(947, "dustGoldTiny");
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

	public static void loadMetals() {




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

		blockThaumicElectrum = new ItemStack(blockStorageSpecial1, 1, 0);
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
		}

		//block = new ItemStack(blockStorage, 1, );
		//RecipeHelper.registerOreDict(new ItemStack(blockStorageOre, 1, ), "block");
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
		ItemArmorInfusableThaumRev.materialData.put(matWardencloth, new ThaumRevMaterialDataSet().setUnlocName(".wardencloth.", new String[] {"skullcap", "tunic", "pants", "boots"}).setIcon("wardencloth", new String[] {"Skullcap", "Tunic", "Pants", "Boots"}).setRepair("itemWardencloth").setColor(ColorLibrary.COLOR_TEAL_MAGNEQUAZAR).setTexture("wardencloth").setRarity(EnumRarity.uncommon).setRegName("Wardencloth", new String[] {"Skullcap", "Tunic", "Pants", "Boots"}));
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
		wardenAmulet = thaumicBauble.addMetaBauble(0, "wardenAmulet", new DefaultBaubleData(BaubleType.AMULET), 2);
		loveRing = thaumicBauble.addMetaBauble(1, "loveRing", new DefaultBaubleData(BaubleType.RING).setUnequip(false), 3);
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

		recipeQuartzBlock = RecipeHelper.addSquareRecipe(wardenicQuartzBlock, "gemQuartzWardenic");
		recipeQuartzChiseled = RecipeHelper.addShapedRecipe(wardenicQuartzChiseled, "X", "X", 'X', "slabQuartzWardenic");
		recipeQuartzPillar = RecipeHelper.addShapedRecipe(ItemHelper.cloneStack(wardenicQuartzPillar, 2), "X", "X", 'X', "blockQuartzWardenic");
		recipeQuartzDeblock = RecipeHelper.addDeblockingRecipe(gemWardenicQuartz, wardenicQuartzBlock);

		recipeQuartzResetChiseled = RecipeHelper.addShapelessRecipe(wardenicQuartzBlock, wardenicQuartzChiseled);
		recipeQuartzResetPillar = RecipeHelper.addShapelessRecipe(wardenicQuartzBlock, wardenicQuartzPillar);

		recipeWardsidianSlab = RecipeHelper.addSlabRecipe(slabWardenicObsidian, "blockWardenicObsidian");
		recipeEldritchSlab = RecipeHelper.addSlabRecipe(slabEldritch, "blockEldritchStone");
		recipeQuartzSlab = RecipeHelper.addSlabRecipe(slabWardenicQuartz, "blockQuartzWardenic");

		recipeWardsidianDeslab = RecipeHelper.addDeslabingRecipe(wardenicObsidian, "slabWardenicObsidian");
		recipeEldritchDeslab = RecipeHelper.addDeslabingRecipe(eldritchStone, "slabEldritchStone");
		recipeQuartzDeslab = RecipeHelper.addDeslabingRecipe(gemWardenicQuartz, "slabQuartzWardenic");



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

		addShapelessSizedOreRecipe(rawOsLu, 0, iOs, iOs, iOs, iOs, iLu, iLu, iLu, "ingotNeodymium", "ingotTungsten");
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

			addPulverizerRecycleRecipe(gemWardenicQuartz, wardenicQuartzBlock, 4);
			addPulverizerRecycleRecipe(gemWardenicQuartz, wardenicQuartzChiseled, 4);
			addPulverizerRecycleRecipe(gemWardenicQuartz, wardenicQuartzPillar, 4);
			addPulverizerRecycleRecipe(gemWardenicQuartz, slabWardenicQuartz, 2);
			//addPulverizerRecycleRecipe(wardenicQuartz, , 6);
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

		//recipeQuartzStair
		//recipeQuartzDestair
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

		recipePrimalGoggles = addInfusionCraftingRecipe(keyRobesPrimal, primalGoggles.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackGoggles, ingotThaumicElectrum, ingotThaumicRiftishBronze, cottonEnchanted, dustSalisMundus, dustSalisMundus, dustSalisMundus, itemPrimalCharm, stackThaumometer);
		recipePrimalRobes = addInfusionCraftingRecipe(keyRobesPrimal, primalRobes.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackChestRobe, ingotThaumicElectrum, ingotThaumicRiftishBronze, cottonEnchanted, dustSalisMundus, dustSalisMundus, dustSalisMundus, itemPrimalCharm, ingotThaumium);
		recipePrimalPants = addInfusionCraftingRecipe(keyRobesPrimal, primalPants.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackLegsRobe, ingotThaumicElectrum, ingotThaumicRiftishBronze, cottonEnchanted, dustSalisMundus, dustSalisMundus, dustSalisMundus, itemPrimalCharm, itemNitor);
		recipePrimalBoots = addInfusionCraftingRecipe(keyRobesPrimal, primalBoots.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackBootsRobe, ingotThaumicElectrum, ingotThaumicRiftishBronze, cottonEnchanted, dustSalisMundus, dustSalisMundus, dustSalisMundus, itemPrimalCharm, planksGreatwood);

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

		recipeEnchSilverwood = addShapelessArcaneCraftingRecipe(keyEnchSilverwood, enchantedSilverwood, new AspectList().add(ORDER, 5), planksSilverwood, salisMundus, salisMundus); //TODO: v0.0.8: Runic Infuser
		recipeConsSilverwood = addShapelessArcaneCraftingRecipe(keyEnchSilverwood, consecratedSilverwood, new AspectList().add(ORDER, 10).add(FIRE, 5), enchantedSilverwood, salisPinch, salisPinch, "nuggetSilver", nHg, itemNitor); //TODO: Infusionize //TODO: v0.0.9: Alchemical Infuser

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
		recipesConsecratedSteelPlate = addInfusionCraftingRecipe(keyWardenPlate, consecratedSteelPlate, 2, new AspectList().add(ARMOR, 8).add(MAGIC, 4).add(ORDER, 4), runicSteelPlate, nuggetSilver, itemQuicksilverDrop, new ItemStack(Items.glowstone_dust), consecratedSilverwood, tinySalisMundus);

		recipeWardenicPlateHelmet = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateHelmet.getStack(), ThaumcraftHelper.newPrimalAspectList(30), "CRC", "RGR", 'R', "itemPlateWardenicSteelRunic", 'C', "itemPlateWardenicSteelConsecrated", 'G', stackGoggles);
		recipeWardenicChestplate = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicChestplate.getStack(), ThaumcraftHelper.newPrimalAspectList(70), "C C", "CRC", "RCR", 'R', "itemPlateWardenicSteelRunic", 'C', "itemPlateWardenicSteelConsecrated");
		recipeWardenicPlateGreaves = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateGreaves.getStack(), ThaumcraftHelper.newPrimalAspectList(50), "RCR", "R R", "C C", 'R', "itemPlateWardenicSteelRunic", 'C', "itemPlateWardenicSteelConsecrated");
		recipeWardenicPlateBoots = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(20), "R R", "C C", 'R', "itemPlateWardenicSteelRunic", 'C', "itemPlateWardenicSteelConsecrated");

		recipeWardenicQuartz = addCrucibleRecipe(keyQuartz, gemWardenicQuartz, "gemQuartz", new AspectList().add(MAGIC, 4).add(CRYSTAL, 2).add(ENERGY, 2).add(WARDEN, 1));
		recipeWardenicQuartzDust = addCrucibleRecipe(keyQuartz, dustWardenicQuartz, "gemWardenicQuartz", new AspectList().add(ENTROPY, 2));
		recipeWardenicQuartzReconst = addCrucibleRecipe(keyQuartz, gemWardenicQuartz, "dustWardenicQuartz", new AspectList().add(ORDER, 2).add(CRYSTAL, 4));
		recipeWardenicQuartzInf = addInfusionCraftingRecipe(keyQuartz, wardenicQuartzInf, 2, new AspectList().add(WARDEN, 4).add(MAGIC, 8).add(CRYSTAL, 4), wardenicQuartzBlock, dustSalisMundus, excubituraOilPure);

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
		recipeConsecratedCompositePlate = addInfusionCraftingRecipe(keyWardenCompositeFitting, consecratedCompositePlate, 4, new AspectList().add(ARMOR, 8).add(MAGIC, 12).add(ORDER, 4).add(ENERGY, 4).add(WARDEN, 2), runicCompositePlate, nuggetSilver, itemQuicksilverDrop, new ItemStack(Items.glowstone_dust), consecratedSilverwood, consecratedSilverwood, dustSalisMundus);
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
		researchEnchSilverwood = new FluxGearResearchItem(keyEnchSilverwood, key, new AspectList().add(TREE, 3).add(MAGIC, 3).add(AURA, 3).add(ORDER, 3), 7, 8, 1, enchantedSilverwood);

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

		/*researchClusterFe = new DummyResearchItem(keyClusterFe, "PUREIRON", "ALCHEMY", -16, -4, clusterIron).registerResearchItem();
		researchClusterCu = new DummyResearchItem(keyClusterCu, "PURECOPPER", "ALCHEMY", -14, -6, clusterCopper).setParents(keyClusterFe).registerResearchItem();
		researchClusterSn = new DummyResearchItem(keyClusterSn, "PURETIN", "ALCHEMY", -12, -2, clusterTin).setParents(keyClusterFe).registerResearchItem();
		researchClusterPb = new DummyResearchItem(keyClusterPb, "PURELEAD", "ALCHEMY", -16, -1, clusterLead).setParents(keyClusterFe).registerResearchItem();
		researchClusterAu = new DummyResearchItem(keyClusterAu, "PUREGOLD", "ALCHEMY", -18, -2, clusterGold).setParents(keyClusterFe).registerResearchItem();

		researchClusterNi = new FluxGearResearchItem(keyClusterNi, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(VOID, 1), -19, -4, 1, clusterNickel);
		if (OreDictionary.doesOreNameExist("oreAluminium")) {
			researchClusterAl = new FluxGearResearchItem(keyClusterAl, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 3), -18, -6, 1, clusterAluminium);
		}
		researchClusterYPO = new FluxGearResearchItem(keyClusterYPO, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(EARTH, 2).add(ENERGY, 1), -16, -7, 1, clusterXenotime);
		researchClusterZn = new FluxGearResearchItem(keyClusterZn, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(CRYSTAL, 1), -11, 0, 1, clusterZinc);
		researchClusterBi = new FluxGearResearchItem(keyClusterBi, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 3), -14, 0, 1, clusterBismuth);
		researchClusterCuAs = new FluxGearResearchItem(keyClusterCuAs, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(EXCHANGE, 1).add(POISON, 1), -12, -5, 1, clusterTennantite);
		researchClusterCuSb = new FluxGearResearchItem(keyClusterCuSb, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(EXCHANGE, 1).add(POISON, 1), -14, -4, 1, clusterTetrahedrite);
		researchClusterW = new FluxGearResearchItem(keyClusterW, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(MECHANISM, 1).add(ARMOR, 1), -18, 0, 1, clusterTungsten);
		if (OreDictionary.doesOreNameExist("orePlatinum")) {
			researchClusterPt = new FluxGearResearchItem(keyClusterPt, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 2).add(EXCHANGE, 1).add(GREED, 1), -20, -2, 1, clusterPlatinum);
		}
		researchClusterIrOs = new FluxGearResearchItem(keyClusterIrOs, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 4).add(ARMOR, 2).add(LIGHT, 1).add(ENERGY, 1), -19, 2, 1, clusterIridosmium);*/


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

		/*researchClusterNi.setParents(keyClusterFe).setSecondary();
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
			researchClusterPt.setParents(keyClusterNi, keyClusterAu).setSecondary();
			researchClusterIrOs.setParents(keyClusterW, keyClusterPt).setSecondary();
		} else {
			researchClusterIrOs.setParents(keyClusterW).setSecondary();
		}*/

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

		/*researchClusterNi.registerResearchItem();
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
*/


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

		/*researchClusterNi.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterNickel));
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
		researchClusterIrOs.setPages(new ResearchPage("0"), new ResearchPage(recipeClusterIridosmium));*/


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
	public static String nAu = "nuggetGold";
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
	public static String iOs = "ingotOsmium";
	public static String iLu = "ingotLutetium";

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
