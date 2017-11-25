package mortvana.thaumrev.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

import baubles.api.BaubleType;
import magicbees.api.MagicBeesAPI;
import mortvana.thaumrev.block.*;
import mortvana.thaumrev.item.*;
import mortvana.melteddashboard.intermod.baubles.util.DefaultBaubleData;
import mortvana.melteddashboard.intermod.thaumcraft.research.FluxGearResearchItem;
import mortvana.thaumrev.util.AspectStack;
import mortvana.thaumrev.util.RecipeHelper;
import mortvana.thaumrev.util.item.data.ThaumRevMaterialDataSet;
import mortvana.melteddashboard.ColorLibrary;
import mortvana.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.melteddashboard.item.ItemArmorFluxGear;
import mortvana.melteddashboard.util.helpers.ItemHelper;
import mortvana.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import mortvana.thaumrev.world.ExcubituraGenerator;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;

import static thaumcraft.api.aspects.Aspect.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;
import static mortvana.melteddashboard.lib.ThaumcraftLibrary.*;
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
	    loadWorldGen();
	    loadInit();

	    ((FluxGearCreativeTab) thaumicRevelationsTab).setItem(wardenAmulet);
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
		blockOre = new BlockOre();

		blockStoneDecor = new BlockStoneDecor();

		//blockStorageMain = new BlockMetalStorageMain();

		blockStoneSlab = new BlockStoneSlab();
		blockStoneSlabDouble = new BlockStoneSlab(blockStoneSlab);
	}

	public static void createItems() {
		generalItem = new ItemThaumRev();
		thaumicBauble = new ItemThaumicBauble();

		itemFocusPurity = new ItemFocusPurity();
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class, "blockThaumicPlant");
		GameRegistry.registerBlock(blockOre, ItemBlockOre.class, "blockOre");

		GameRegistry.registerBlock(blockStoneDecor, ItemBlockDecorStone.class, "blockDecorStone");

		//GameRegistry.registerBlock(blockStorageMain, ItemBlockMetalStorage.class, "blockStorageMain");

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
        ((BlockThaumicPlant) blockThaumicPlant).register();

		((BlockOre) blockOre).register();

		((BlockStoneDecor) blockStoneDecor).register();

		//((BlockMetalStorageMain) blockStorageMain).register();

		((BlockStoneSlab) blockStoneSlab).register();
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
        cottonEnchanted = generalItem.addOreDictItem(9, "cottonEnchanted", "itemCottonFabricEnchanted");
        thaumicBronzeChain = generalItem.addOreDictItem(10, "thaumicBronzeChain", chainTBronze);
        eldritchCog = generalItem.addOreDictItem(11, "eldritchCog", "itemEldritchCog");
        eldritchKeystone = generalItem.addOreDictItem(12, "eldritchKeystone", "itemEldritchKeystone");
        thistleLeaf = generalItem.addOreDictItem(13, "thistleLeaf", "itemThistleLeaf");
        thistleFlower = generalItem.addOreDictItem(14, "thistleFlower", "itemThistleFlower");

        seedExcubitura = generalItem.addOreDictItem(480, "seedExcubitura");
        seedCotton = generalItem.addOreDictItem(481, "seedCotton");
        seedThistle = generalItem.addOreDictItem(482, "seedThistle");

		ingotCopper = generalItem.addColorizedOreDictItem(500, iCu, "ingot", ColorLibrary.CMAT_COPPER);
		ingotZinc = generalItem.addColorizedOreDictItem(501, "ingotZinc", "ingot", ColorLibrary.CMAT_ZINC);
        ingotTin = generalItem.addOreDictItem(502, iSn);
        ingotNickel = generalItem.addOreDictItem(503, "ingotNickel");
        ingotSilver = generalItem.addOreDictItem(504, "ingotSilver");
        ingotLead = generalItem.addOreDictItem(505, "ingotLead");
        ingotLanthanides = generalItem.addOreDictItem(506, "ingotXenotime", 1, "ingotXenotimeLanthanides");
        ingotTungsten = generalItem.addOreDictItem(507, "ingotTungsten", 1);
        ingotIridium = generalItem.addOreDictItem(508, "ingotIridium", 2);
        ingotBismuth = generalItem.addOreDictItem(509, "ingotBismuth");
		ingotArsenicalBronze = generalItem.addOreDictItem(510, "ingotArsenicalBronze");
        ingotAntimonialBronze = generalItem.addOreDictItem(511, "ingotAntimonialBronze");
        gemPyrope = generalItem.addOreDictItem(512, "gemPyrope", 2);
        gemDioptase = generalItem.addOreDictItem(513, "gemDioptase", 2);
        gemFluonicSapphire = generalItem.addOreDictItem(514, "gemFluonicSapphire", 2);
		ingotOsmium = generalItem.addOreDictItem(515, "ingotOsmium", 1);
        ingotNeodymium = generalItem.addOreDictItem(516, "ingotNeodymium", 1);
        ingotLutetium = generalItem.addOreDictItem(517, "ingotLutetium", 2);
        ingotPalladium = generalItem.addOreDictItem(518, "ingotPalladium", 1);
        ingotIridosmium = generalItem.addOreDictItem(519, "ingotIridosmium", 2);
        ingotBrass = generalItem.addOreDictItem(520, "ingotBrass");
        ingotBronze = generalItem.addOreDictItem(521, "ingotBronze");
        ingotBismuthBronze = generalItem.addOreDictItem(522, "ingotBismuthBronze");
        ingotMithril = generalItem.addOreDictItem(523, "ingotMithril", 1, "ingotArsenoAntimonialBronze");
        ingotThaumicBronze = generalItem.addOreDictItem(524, "ingotThaumicBronze");
        ingotTinkersBronze = generalItem.addOreDictItem(525, "ingotTinkersBronze");
        ingotThaumicTinkersBronze = generalItem.addOreDictItem(526, "ingotThaumicTinkersBronze", 1);
        ingotWardenicTinkersBronze = generalItem.addOreDictItem(527, "ingotWardenicTinkersBronze", 1);
        ingotElectrum = generalItem.addOreDictItem(528, "ingotElectrum");
        ingotThaumicElectrum = generalItem.addOreDictItem(529, "ingotThaumicElectrum", 1);
        ingotSteel = generalItem.addOreDictItem(530, "ingotSteel");
        ingotWardenicSteel = generalItem.addOreDictItem(531, "ingotWardenicSteel", 1);
        ingotWardenicMetal = generalItem.addOreDictItem(532, "ingotWardenicMetal");
        ingotOsLu = generalItem.addOreDictItem(533, "ingotOsmiumLutetium", 2);
        ingotVoidbrass = generalItem.addOreDictItem(534, "ingotVoidbrass", 1);
        ingotVoidsteel = generalItem.addOreDictItem(535, "ingotVoidsteel", 1);
        ingotVoidtungsten = generalItem.addOreDictItem(536, "ingotVoidtungsten", 2);
        gemFluonicPyroptase = generalItem.addOreDictItem(537, "gemFluonicPyroptase", 3);
        ingotXenotimeJunk = generalItem.addOreDictItem(538, "ingotLanthanides", 1, "ingotXenotimeWaste");

        ingotAluminium = generalItem.addOreDictItem(540, "ingotAluminium");
        ingotAluminiumBronze = generalItem.addOreDictItem(541, "ingotAluminiumBronze");

        nuggetCopper = generalItem.addColorizedOreDictItem(550, "nuggetCopper", "nugget", ColorLibrary.CMAT_COPPER);
		nuggetZinc = generalItem.addOreDictItem(551, "nuggetZinc");
		nuggetTin = generalItem.addOreDictItem(552, "nuggetTin");
        nuggetNickel = generalItem.addOreDictItem(553, "nuggetNickel");
		nuggetSilver = generalItem.addOreDictItem(554, "nuggetSilver");
        nuggetLead = generalItem.addOreDictItem(555, "nuggetLead");
        nuggetLanthanides = generalItem.addOreDictItem(556, "nuggetXenotime", 1, "nuggetXenotimeLanthanides");
		nuggetTungsten = generalItem.addOreDictItem(557, "nuggetTungsten", 1);
		nuggetIridium = generalItem.addOreDictItem(558, "nuggetIridium", 2);
		nuggetBismuth = generalItem.addOreDictItem(559, "nuggetBismuth");
        nuggetArsenicalBronze = generalItem.addOreDictItem(560, "nuggetArsenicalBronze");
        nuggetAntimonialBronze = generalItem.addOreDictItem(561, "nuggetAntimonialBronze");
        shardPyrope = generalItem.addOreDictItem(562, "shardPyrope", 2, "nuggetPyrope");
        shardDioptase = generalItem.addOreDictItem(563, "shardDioptase", 2, "nuggetDioptase");
        shardFluonicSapphire = generalItem.addOreDictItem(564, "shardFluonicSapphire", 2, "nuggetFluonicSapphire");
        nuggetOsmium = generalItem.addOreDictItem(565, "nuggetOsmium", 1);
        nuggetNeodymium = generalItem.addOreDictItem(566, "nuggetNeodymium", 1);
        nuggetLutetium = generalItem.addOreDictItem(567, "nuggetLutetium", 2);
        nuggetPalladium = generalItem.addOreDictItem(568, "nuggetPalladium", 1);
        nuggetIridosmium = generalItem.addOreDictItem(569, "nuggetIridosmium", 2);
        nuggetBrass = generalItem.addOreDictItem(570, "nuggetBrass");
        nuggetBronze = generalItem.addOreDictItem(571, nBronze);
        nuggetBismuthBronze = generalItem.addOreDictItem(572, "nuggetBismuthBronze");
        nuggetMithril = generalItem.addOreDictItem(573, "nuggetMithril", 1, "nuggetArsenoAntimonialBronze");
        nuggetThaumicBronze = generalItem.addOreDictItem(574, "nuggetThaumicBronze");
        nuggetTinkersBronze = generalItem.addOreDictItem(575, "nuggetTinkersBronze");
        nuggetThaumicTinkersBronze = generalItem.addOreDictItem(576, "nuggetThaumicTinkersBronze", 1);
        nuggetWardenicTinkersBronze = generalItem.addOreDictItem(577, "nuggetWardenicTinkersBronze", 1);
        nuggetElectrum = generalItem.addOreDictItem(578, "nuggetElectrum");
        nuggetThaumicElectrum = generalItem.addOreDictItem(579, "nuggetThaumicElectrum", 1);
        nuggetSteel = generalItem.addOreDictItem(580, "nuggetSteel");
        nuggetWardenicSteel = generalItem.addOreDictItem(581, "nuggetWardenicSteel", 1);
        nuggetWardenicMetal = generalItem.addOreDictItem(582, "nuggetWardenicMetal");
        nuggetOsLu = generalItem.addOreDictItem(583, "nuggetOsmiumLutetium", 2);
        nuggetVoidbrass = generalItem.addOreDictItem(584, "nuggetVoidbrass", 1);
        nuggetVoidsteel = generalItem.addOreDictItem(585, "nuggetVoidsteel", 1);
        nuggetVoidtungsten = generalItem.addOreDictItem(586, "nuggetVoidtungsten", 2);
        shardFluonicPyroptase = generalItem.addOreDictItem(587, "shardFluonicPyroptase", 3, "nuggetFluonicPyroptase");
        nuggetXenotimeJunk = generalItem.addOreDictItem(588, "nuggetLanthanides", 1, "nuggetXenotimeWaste");

        nuggetAluminium = generalItem.addOreDictItem(590, "nuggetAluminium");
        nuggetAluminiumBronze = generalItem.addOreDictItem(591, "nuggetAluminiumBronze");

        dustCopper = generalItem.addOreDictItem(600, "dustCopper");
        dustZinc = generalItem.addOreDictItem(601, "dustZinc");
		dustTin = generalItem.addOreDictItem(602, "dustTin");
        dustNickel = generalItem.addOreDictItem(603, "dustNickel");
		dustSilver = generalItem.addOreDictItem(604, "dustSilver");
        dustLead = generalItem.addOreDictItem(605, "dustLead");
        dustLanthanides = generalItem.addOreDictItem(606, "dustXenotime", 1, "dustXenotimeLanthanides");
		dustTungsten = generalItem.addOreDictItem(607, "dustTungsten", 1);
		dustIridium = generalItem.addOreDictItem(608, "dustIridium", 2);
		dustBismuth = generalItem.addOreDictItem(609, "dustBismuth");
        dustArsenicalBronze = generalItem.addOreDictItem(610, "dustArsenicalBronze");
        dustAntimonialBronze = generalItem.addOreDictItem(611, "dustAntimonialBronze");
        dustPyrope = generalItem.addOreDictItem(612, "dustPyrope", 2);
		dustDioptase = generalItem.addOreDictItem(613, "dustDioptase", 2);
		dustFluonicSapphire = generalItem.addOreDictItem(614, "dustFluonicSapphire", 2);
        dustOsmium = generalItem.addOreDictItem(615, "dustOsmium", 1);
        dustNeodymium = generalItem.addOreDictItem(616, "dustNeodymium", 1);
        dustLutetium = generalItem.addOreDictItem(617, "dustLutetium", 2);
        dustPalladium = generalItem.addOreDictItem(618, "dustPalladium", 1);
        dustIridosmium = generalItem.addOreDictItem(619, "dustIridosmium", 2);
        dustBrass = generalItem.addOreDictItem(620, "dustBrass");
        dustBronze = generalItem.addOreDictItem(621, "dustBronze");
        dustBismuthBronze = generalItem.addOreDictItem(622, "dustBismuthBronze");
        dustMithril = generalItem.addOreDictItem(623, "dustMithril", 1, "dustArsenoAntimonialBronze");
        dustThaumicBronze = generalItem.addOreDictItem(624, "dustThaumicBronze");
        dustTinkersBronze = generalItem.addOreDictItem(625, "dustTinkersBronze");
        dustThaumicTinkersBronze = generalItem.addOreDictItem(626, "dustThaumicTinkersBronze", 1);
        dustWardenicTinkersBronze = generalItem.addOreDictItem(627, "dustWardenicTinkersBronze", 1);
        dustElectrum = generalItem.addOreDictItem(628, "dustElectrum");
        dustThaumicElectrum = generalItem.addOreDictItem(629, "dustThaumicElectrum", 1);
        dustSteel = generalItem.addOreDictItem(630, "dustSteel");
        dustWardenicSteel = generalItem.addOreDictItem(631, "dustWardenicSteel", 1);
        dustWardenicMetal = generalItem.addOreDictItem(632, "dustWardenicMetal");
        dustOsLu = generalItem.addOreDictItem(633, "dustOsmiumLutetium", 2);
        dustVoidbrass = generalItem.addOreDictItem(634, "dustVoidbrass", 1);
        dustVoidsteel = generalItem.addOreDictItem(635, "dustVoidsteel", 1);
        dustVoidtungsten = generalItem.addOreDictItem(636, "dustVoidtungsten", 2);
        dustFluonicPyroptase = generalItem.addOreDictItem(637, "dustFluonicPyroptase", 3);
        dustXenotimeJunk = generalItem.addOreDictItem(638, "dustLanthanides", 1, "dustXenotimeWaste");

        dustAluminium = generalItem.addOreDictItem(640, "dustAluminium");
        dustAluminiumBronze = generalItem.addOreDictItem(641, "dustAluminiumBronze");

		rawBrass = generalItem.addOreDictItem(700, "ingotBrassRaw");
		rawBronze = generalItem.addOreDictItem(701, "ingotBronzeRaw");
        rawBismuthBronze = generalItem.addOreDictItem(702, "ingotBismuthBronzeRaw");
        rawMithril = generalItem.addOreDictItem(703, "ingotArsenoAntimonialBronzeRaw");
		rawThaumicBronze = generalItem.addOreDictItem(704, "ingotThaumicBronzeRaw");
        rawTinkersBronze = generalItem.addOreDictItem(705, "ingotTinkersBronzeRaw");
        rawThaumicTinkersBronze = generalItem.addOreDictItem(706, "ingotThaumicTinkersBronzeRaw");

		rawElectrum = generalItem.addOreDictItem(708, "ingotElectrumRaw");

		rawWardenicMetal = generalItem.addOreDictItem(712, "ingotWardenicMetalRaw");
        rawOsLu = generalItem.addOreDictItem(713, "ingotOsmiumLutetiumRaw");

        rawAluminiumBronze = generalItem.addOreDictItem(721, "ingotAluminiumBronze");

        //coatedMithril = generalItem.addOreDictItem(733, "ingotArsenoAntimonialBronzeCoated");
		coatedThaumicBronze = generalItem.addOreDictItem(734, "ingotThaumicBronzeCoated");
		//coatedTinkersBronze = generalItem.addOreDictItem(735, "ingotTinkersBronzeCoated");
		coatedThaumicTinkersBronze = generalItem.addOreDictItem(736, "ingotThaumicTinkersBronzeCoated");

        coatedOsLu = generalItem.addOreDictItem(743, "ingotOsmiumLutetiumCoated");

        //firedMithril = generalItem.addOreDictItem(763, "ingotArsenoAntimonialBronzeFired");
		firedThaumicBronze = generalItem.addOreDictItem(764, "ingotThaumicBronzeFired");
		//firedTinkersBronze = generalItem.addOreDictItem(765, "ingotTinkersBronzeFired");
		firedThaumicTinkersBronze = generalItem.addOreDictItem(766, "ingotThaumicTinkersBronzeFired");

        firedOsLu = generalItem.addOreDictItem(773, "ingotOsmiumLutetiumFire");

		dustAer = generalItem.addOreDictItem(800, "dustAer");
		dustIgnis = generalItem.addOreDictItem(801, "dustIgnis");
		dustAqua = generalItem.addOreDictItem(802, "dustAqua");
		dustTerra = generalItem.addOreDictItem(803, "dustTerra");
		dustOrdo = generalItem.addOreDictItem(804, "dustOrdo");
		dustPerditio = generalItem.addOreDictItem(805, "dustPerditio");
        dustIron = generalItem.addOreDictItem(807, "dustIron");
        dustGold = generalItem.addOreDictItem(808, "dustGold");
        dustThaumium = generalItem.addOreDictItem(809, "dustThaumium");
        dustVoidmetal = generalItem.addOreDictItem(810, "dustVoid");
        
		dustSalisMundusTiny = generalItem.addOreDictItem(856, salisPinch);
        
        clusterZinc = generalItem.addOreDictItem(901, "clusterZinc");
        clusterNickel = generalItem.addOreDictItem(903, "clusterNickel");
        clusterXenotime = generalItem.addOreDictItem(906, "clusterXenotime");
        clusterTungsten = generalItem.addOreDictItem(907, "clusterTungsten");
        clusterIridosmium = generalItem.addOreDictItem(908, "clusterIridosmium");
        clusterBismuth = generalItem.addOreDictItem(909, "clusterBismuth");
        clusterTennantite = generalItem.addOreDictItem(910, "clusterTennantite");
        clusterTetrahedrite = generalItem.addOreDictItem(911, "clusterTetrahedrite");

		ceramicSlag = generalItem.addOreDictItem(950, "itemSlagCeramic");
		thaumicSlag = generalItem.addOreDictItem(951, "itemSlagThaumic");

        //WARDENIC ARSENAL
		excubituraPetal = generalItem.addOreDictItem(1000, "excubituraPetal", "itemExcubituraPetal");

		excubituraPaste = generalItem.addOreDictItem(1020, "excubituraPaste", paste);
		excubituraFabric = generalItem.addOreDictItem(1021, "excubituraFabric", "itemExcubituraFabric");
		itemWardencloth = generalItem.addOreDictItem(1022, "wardencloth", wardencloth);

		excubituraOilUnproc = generalItem.addOreDictItem(1040, "excubituraOilUnproc", "itemExcubituraOilUnprocessed");
		excubituraOil = generalItem.addOreDictItem(1041, "excubituraOil", oilExcu);
		wardenicBronzeChain = generalItem.addOreDictItem(1042, "wardenicBronzeChain", chainWBronze);
		primalBronzeChain = generalItem.addOreDictItem(1043, "primalBronzeChain", chainPBronze);
		wardenicBronzePlate = generalItem.addOreDictItem(1044, "wardenicBronzePlate", "itemPlateWardenicBronze");

		excubituraOilPure = generalItem.addOreDictItem(1060, "excubituraOilPure", "itemExcubituraOilPure");
		wardenicSteelChain = generalItem.addOreDictItem(1061, "wardenicSteelChain", "itemChainWardenicSteel");
		oiledSteelChain = generalItem.addOreDictItem(1062, "wardenicSteelChainOiled", "itemChainWardenicSteelOiled");
		wardenicSteelPlate = generalItem.addOreDictItem(1063, "wardenicSteelPlate", "itemPlateWardenicSteel");
		detailedSteelPlate = generalItem.addOreDictItem(1064, "detailedSteelPlate", "itemPlateWardenicSteelDetailed");
		runicSteelPlate = generalItem.addOreDictItem(1065, "runicSteelPlate", "itemPlateWardenicSteelRunic");
		consecratedSteelPlate = generalItem.addOreDictItem(1066, "consecratedSteelPlate", "itemPlateWardenicSteelConsecrated");

		wardenicQuartz = generalItem.addOreDictItem(1080, "wardenicQuartz", "gemQuartzWardenic");
		wardenicCrystal = generalItem.addOreDictItem(1081, "wardenicCrystal", "crystalWardenic");
		wardenicQuartzInf = generalItem.addOreDictItem(1082, "wardenicQuartzInf", "gemQuartzWardenicInfused");
		dustWardenicQuartz = generalItem.addOreDictItem(1083, "dustWardenicQuartz");
		dustWardenicCrystal = generalItem.addOreDictItem(1084, "dustWardenicCrystal", "dustWardenicCrystal");
		binderWardenic = generalItem.addOreDictItem(1085, "binderWardenic", "itemBinderWardenic");
		rawWardenicComposite = generalItem.addOreDictItem(1086, "ingotWardenicCompositeRaw");
		ingotWardenicComposite = generalItem.addOreDictItem(1087, "ingotWardenicComposite");
		wardenicCompositePlate = generalItem.addOreDictItem(1088, "wardenicCompositePlate", "itemPlateWardenicComposite");
		fittedCompositePlate = generalItem.addOreDictItem(1089, "fittedCompositePlate", "itemPlateWardenicCompositeFitted");
		detailedCompositePlate = generalItem.addOreDictItem(1090, "detailedCompositePlate", "itemPlateWardenicCompositeDetailed");
		runicCompositePlate = generalItem.addOreDictItem(1091, "runicCompositePlate", "itemPlateWardenicCompositeRunic");
		consecratedCompositePlate = generalItem.addOreDictItem(1092, "consecratedCompositePlate", "itemPlateWardenicCompositeConsecrated");
		primalCompositePlate = generalItem.addOreDictItem(1093, "primalCompositePlate", "itemPlateWardenicCompositePrimal");

		wardenicCrystalAwakened = generalItem.addOreDictItem(1100, "wardenicCrystalAwakened", "crystalWardenicAwakened");

		wardenicHardener = generalItem.addOreDictItem(1160, "itemWardenicHardener");



		aluDenseTemp = generalItem.addItem(30000, "tempAluDense");
	}

	public static void loadMaterials() {
		ItemArmorFluxGear.addArmorMaterial(matPrimal, 25, new int[] {1, 3, 2, 1}, 25);
		ItemArmorFluxGear.addArmorMaterial(matBronzeChain, 20, new int[] {2, 5, 4, 2}, 25);
		ItemArmorFluxGear.addArmorMaterial(matWardencloth, 30, new int[] {1, 3, 2, 1}, 30);
		ItemArmorFluxGear.addArmorMaterial(matWardenicChain, 15, new int[] {2, 5, 4, 1}, 25);
		ItemArmorFluxGear.addArmorMaterial(matWardenicSteel, 30, new int[]{3, 7, 5, 2}, 20);
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

	public static void loadWorldGen() {
		GameRegistry.registerWorldGenerator(new ExcubituraGenerator(), 1);
	}

	public static void loadInit() {
		RecipeHelper.addCluster("oreZinc", clusterZinc);
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
		registerOreDict(itemAlumentum, "itemAlumentum");
		registerOreDict(itemNitor, "itemNitor");
		registerOreDict(dustSalisMundus, salisMundus);
		registerOreDict(itemEnchantedFabric, enchFabric);
		registerOreDict(itemQuicksilverDrop, nHg);
		registerOreDict(itemShardBalanced, shardBalanced);
	}

	public static void addLoot() {
		generalItem.addContainer(210, new ItemStack(Items.bowl));
		generalItem.addContainer(221, itemPhial);
		generalItem.addContainer(230, itemPhial);

		generalItem.addLoot(120, ingotThaumicBronze, thaumicSlag);
	}

	public static void loadRecipes() {
		((BlockOre) blockOre).recipes();

		((BlockStoneDecor) blockStoneDecor).recipes();

		//((BlockMetalStorageMain) blockStorageMain).recipes();

		((BlockStoneSlab) blockStoneSlab).recipes();

        recipeBismuthBronze = new ShapelessOreRecipe[2];
        recDustBismuthBronze = new ShapelessOreRecipe[2];

		recipeBrass = generateShapelessSizedOreRecipe(rawBrass, 0, iCu, iCu, iCu, "ingotZinc");
		recipeBronze = generateShapelessSizedOreRecipe(rawBronze, 0, iCu, iCu, iCu, iSn);
        recipeBismuthBronze[0] = generateShapelessSizedOreRecipe(rawBismuthBronze, 0, iBs, iBs, iBs, iBs, iCu, iCu, iCu, "ingotBismuth");
        recipeBismuthBronze[1] = generateShapelessSizedOreRecipe(rawBismuthBronze, 0, iCu, iCu, iCu, iCu, iCu, iCu, "ingotZinc", "ingotBismuth");
        recipeMithril = generateShapelessSizedOreRecipe(rawMithril, 0, "ingotArsenicalBronze", "ingotAntimonialBronze");
        recipeTinkersBronze = generateShapelessSizedOreRecipe(rawTinkersBronze, 0, iMth, iMth, iMth, iMth, "ingotBismuthBronze", "ingotBismuthBronze", "ingotBronze", "ingotBrass", "ingotAluminiumBronze");
		recipeElectrum = generateShapelessSizedOreRecipe(rawElectrum, 0, "ingotGold", "ingotSilver");
        recipeOsLu = generateShapelessSizedOreRecipe(rawOsLu, 0, iOs, iOs, iOs, iOs, iLu, iLu, iLu, "ingotNeodymium", "ingotTungsten");
        if (ThaumRevConfig.backwardsAlBronze) {
            recipeAlBronze = generateShapelessRecipe(rawAluminiumBronze, 0, iCu, "ingotAluminium", "ingotAluminium", "ingotAluminium");
        } else {
            recipeAlBronze = generateShapelessRecipe(rawAluminiumBronze, 0, iCu, iCu, iCu, "ingotAluminium");
        }
        
        recDustBrass = generateShapelessSizedOreRecipe(dustBrass, 0, dCu, dCu, dCu, "dustZinc");
        recDustBronze = generateShapelessSizedOreRecipe(dustBronze, 0, dCu, dCu, dCu, "dustTin");
        recDustBismuthBronze[0] = generateShapelessSizedOreRecipe(dustBismuthBronze, 0, dBs, dBs, dBs, dBs, dCu, dCu, dCu, "dustBismuth");
        recDustBismuthBronze[1] = generateShapelessSizedOreRecipe(dustBismuthBronze, 0, dCu, dCu, dCu, dCu, dCu, dCu, "dustZinc", "dustBismuth");
        recDustMithril = generateShapelessSizedOreRecipe(dustMithril, 0, "dustArsenicalBronze", "dustAntimonialBronze");
        recDustTinkersBronze = generateShapelessSizedOreRecipe(dustTinkersBronze, 0, dMth, dMth, dMth, dMth, "dustBismuthBronze", "dustBismuthBronze", "dustBronze", "dustBrass", "dustAluminiumBronze");
        recDustElectrum = generateShapelessSizedOreRecipe(dustElectrum, 0, "dustGold", "dustSilver");
        if (ThaumRevConfig.backwardsAlBronze) {
            recDustAlBronze = generateShapelessRecipe(dustAluminiumBronze, 0, dCu, "dustAluminium", "dustAluminium", "dustAluminium");
        } else {
            recDustAlBronze = generateShapelessRecipe(dustAluminiumBronze, 0, dCu, dCu, dCu, "dustAluminium");
        }

        recCoatOsLu = generateShapelessRecipe(coatedOsLu, 4, "ingotOsmiumLutetiumRaw", "ingotOsmiumLutetiumRaw", "ingotOsmiumLutetiumRaw", "ingotOsmiumLutetiumRaw", "dustFluonicSapphire", "itemClay", "itemClay", "itemClay", "itemClay");

		recipeWardenicMetal = addShapelessSizedOreRecipe(rawWardenicMetal, 0, "ingotThaumium", "ingotThaumium", "ingotThaumium", "ingotPalladium", "itemBinderWardenic", "ingotBrass", "ingotElectrum", "ingotZinc", "quicksilver");

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
		
		if (ThaumRevConfig.enableBrass) {
			addRecipe(recipeBrass);
            addRecipe(recDustBrass);
			addSmelting(rawBrass, ingotBrass, 1.2F);
			addStorageRecipe(ingotBrass, "nuggetBrass");
			addReverseStorageRecipe(nuggetBrass, "ingotBrass");
            addSmelting(dustBrass, ingotBrass);
		}
		if (ThaumRevConfig.enableBronze) {
			addRecipe(recipeBronze);
            addRecipe(recDustBronze);
			addSmelting(rawBronze, ingotBronze, 1.05F);
			addStorageRecipe(ingotBronze, "nuggetBronze");
			addReverseStorageRecipe(nuggetBronze, "ingotBronze");
            addSmelting(dustBronze, ingotBronze);
		}
        if (ThaumRevConfig.enableBismuthBronze) {
            addRecipe(recipeBismuthBronze);
            addRecipe(recDustBismuthBronze);
            addSmelting(rawBismuthBronze, ingotBismuthBronze, 1.35F);
            addStorageRecipe(ingotBismuthBronze, "nuggetBismuthBronze");
            addReverseStorageRecipe(nuggetBismuthBronze, "ingotBismuthBronze");
            addSmelting(dustBismuthBronze, ingotBismuthBronze);
        }
        if (ThaumRevConfig.enableMithril) {
            addRecipe(recipeMithril);
            addRecipe(recDustMithril);
            addSmelting(rawMithril, ingotMithril, 1.65F);
            addStorageRecipe(ingotMithril, "nuggetArsenoAntimonialBronze");
            addReverseStorageRecipe(nuggetMithril, "ingotArsenoAntimonialBronze");
            addSmelting(dustMithril, ingotMithril);
        }
        if (ThaumRevConfig.enableTinkersBronze) {
            addRecipe(recipeTinkersBronze);
            addRecipe(recDustTinkersBronze);
            addSmelting(rawTinkersBronze, ingotTinkersBronze, 1.85F);
            addStorageRecipe(ingotTinkersBronze, "nuggetTinkersBronze");
            addReverseStorageRecipe(nuggetTinkersBronze, "ingotTinkersBronze");
            addSmelting(dustTinkersBronze, ingotTinkersBronze);
        }
		if (ThaumRevConfig.enableElectrum) {
			addRecipe(recipeElectrum);
            addRecipe(recDustElectrum);
			addSmelting(rawElectrum, ingotElectrum, 1.5F);
			addStorageRecipe(ingotElectrum, "nuggetElectrum");
			addReverseStorageRecipe(nuggetElectrum, "ingotElectrum");
            addSmelting(dustElectrum, ingotElectrum);
		}
        /*if (ThaumRevConfig.enableOsLu) {
            addRecipe(recipeOsLu);
            addRecipe(recCoatOsLu);
            addSmelting(coatedOsLu, ingotOsLu, 2.35F);
            addStorageRecipe(ingotOsLu, "nuggetOsmiumLutetium");
            addReverseStorageRecipe(nuggetOsLu, "ingotOsmiumLutetium");
            addSmelting(dustOsLu, ingotOsLu);
        }*/
        if (ThaumRevConfig.enableAlBronze) {
            addRecipe(recipeAlBronze);
            addRecipe(recDustAlBronze);
            addSmelting(rawAluminiumBronze, ingotAluminiumBronze, 1.15F);
            addStorageRecipe(ingotAluminiumBronze, "nuggetAluminiumBronze");
            addReverseStorageRecipe(nuggetAluminiumBronze, "ingotAluminiumBronze");
            addSmelting(dustAluminiumBronze, ingotAluminiumBronze);
        }

        addStorageRecipe(ingotOsmium, "nuggetOsmium");
        addStorageRecipe(ingotNeodymium, "nuggetNeodymium");
        addStorageRecipe(ingotLutetium, "nuggetLutetium");
        addStorageRecipe(ingotPalladium, "nuggetPalladium");
        addStorageRecipe(ingotIridosmium, "nuggetIridosmium");
        addStorageRecipe(ingotThaumicBronze, "nuggetThaumicBronze");
        addStorageRecipe(ingotThaumicTinkersBronze, "nuggetThaumicTinkersBronze");
        addStorageRecipe(ingotWardenicTinkersBronze, "nuggetWardenicTinkersBronze");
        addStorageRecipe(ingotThaumicElectrum, "nuggetThaumicElectrum");
        addStorageRecipe(ingotSteel, "nuggetSteel");
        addStorageRecipe(ingotWardenicSteel, "nuggetWardenicSteel");
        addStorageRecipe(ingotWardenicMetal, "nuggetWardenicMetal");
        addStorageRecipe(ingotVoidbrass, "nuggetVoidbrass");
        addStorageRecipe(ingotVoidsteel, "nuggetVoidsteel");
        addStorageRecipe(ingotVoidtungsten, "nuggetVoidtugsten");
        addStorageRecipe(gemFluonicPyroptase, "nuggetFluonicPyroptase");
        addStorageRecipe(ingotXenotimeJunk, "nuggetXenotimeWaste");
        addStorageRecipe(ingotAluminium, "nuggetAluminium");

        addReverseStorageRecipe(nuggetOsmium, "ingotOsmium");
        addReverseStorageRecipe(nuggetNeodymium, "ingotNeodymium");
        addReverseStorageRecipe(nuggetLutetium, "ingotLutetium");
        addReverseStorageRecipe(nuggetPalladium, "ingotPalladium");
        addReverseStorageRecipe(nuggetIridosmium, "ingotIridosmium");
        addReverseStorageRecipe(nuggetThaumicBronze, "ingotThaumicBronze");
        addReverseStorageRecipe(nuggetThaumicTinkersBronze, "ingotThaumicTinkersBronze");
        addReverseStorageRecipe(nuggetWardenicTinkersBronze, "ingotWardenicTinkersBronze");
        addReverseStorageRecipe(nuggetThaumicElectrum, "ingotThaumicElectrum");
        addReverseStorageRecipe(nuggetSteel, "ingotSteel");
        addReverseStorageRecipe(nuggetWardenicSteel, "ingotWardenicSteel");
        addReverseStorageRecipe(nuggetWardenicMetal, "ingotWardenicMetal");
        addReverseStorageRecipe(nuggetVoidbrass, "ingotVoidbrass");
        addReverseStorageRecipe(nuggetVoidsteel, "ingotVoidsteel");
        addReverseStorageRecipe(nuggetVoidtungsten, "ingotVoidtungsten");
        addReverseStorageRecipe(shardFluonicPyroptase, "gemFluonicPyroptase");
        addReverseStorageRecipe(nuggetXenotimeJunk, "ingotXenotimeWaste");
        addReverseStorageRecipe(nuggetAluminium, "ingotAluminium");

        addSmelting(dustNeodymium, ingotNeodymium);
        addSmelting(dustLutetium, ingotLutetium);
        addSmelting(dustPalladium, ingotPalladium);
        addSmelting(dustIridosmium, ingotIridosmium);
        addSmelting(dustThaumicBronze, ingotThaumicBronze);
        addSmelting(dustThaumicTinkersBronze, ingotThaumicTinkersBronze);
        addSmelting(dustWardenicTinkersBronze, ingotWardenicTinkersBronze);
        addSmelting(dustThaumicElectrum, ingotThaumicElectrum);
        addSmelting(dustSteel, ingotSteel);
        addSmelting(dustWardenicSteel, ingotWardenicSteel);
        addSmelting(dustWardenicMetal, ingotWardenicMetal);
        addSmelting(dustVoidbrass, ingotVoidbrass);
        addSmelting(dustXenotimeJunk, ingotXenotimeJunk);
        addSmelting(dustAluminium, ingotAluminium);
        
        addSmelting(rawWardenicMetal, ingotWardenicMetal, 1.3F);
        addSmelting(coatedThaumicBronze, firedThaumicBronze, 2.0F);
        addSmelting(coatedThaumicTinkersBronze, firedThaumicTinkersBronze, 2.65F);
        
		recipeSalisTiny = addReverseStorageRecipe(dustSalisMundusTiny, salisMundus);
        recipeSalis = addStorageRecipe(dustSalisMundus, salisPinch);
        
		if (Loader.isModLoaded("ThermalExpansion")) {
			addInductionAlloyRecipe("Copper", 3, "Zinc", 1, ingotBrass);
			addInductionAlloyRecipe("ArsenicalBronze", 1, "AntimonialBronze", 1, ingotMithril);

			addPulverizerRecycleRecipe(wardenicQuartz, wardenicQuartzBlock, 4);
			addPulverizerRecycleRecipe(wardenicQuartz, wardenicQuartzChiseled, 4);
			addPulverizerRecycleRecipe(wardenicQuartz, wardenicQuartzPillar, 4);
			addPulverizerRecycleRecipe(wardenicQuartz, slabWardenicQuartz, 2);
			//addPulverizerRecycleRecipe(wardenicQuartz, , 6);
			addPulverizerRecipe(1600, wardenicQuartz, dustWardenicQuartz);
			addPulverizerRecipe(3200, wardenicCrystal, dustWardenicCrystal);
		}

		//recipeQuartzStair
		//recipeQuartzDestair
		//

		//Temporary
		recipeAluDenseTemp = addShapelessRecipe(aluDenseTemp, "itemAlumentum", "itemAlumentum", "itemAlumentum", "itemAlumentum");

	}

	public static void loadThaumicRecipes() {
		recipeTreatedCotton = addArcaneCraftingRecipe(keyCotton, cottonTreated, ThaumcraftHelper.newPrimalAspectList(2), " S ", "FCF", " F ", 'S', salisPinch, 'F', "itemCottonFiber", 'C', "itemCottonFabric");
		recipeEnchantedCotton = addCrucibleRecipe(keyCotton, cottonEnchanted, cottonTreated, new AspectList().add(CLOTH, 2));
		
		recipeAniPiston = addArcaneCraftingRecipe(keyMaterial, animatedPiston, new AspectList().add(AIR, 5), "IGI", "TAT", "BRB", 'I', "nuggetIron", 'G', greatwoodSlab, 'T', "nuggetThaumium", 'A', "shardAir", 'B', "nuggetBrass", 'R', "dustRedstone");

		recipeThaumicBronzeRaw = addShapelessArcaneCraftingRecipe(keyThaumicBronze, rawThaumicBronze, new AspectList().add(ORDER, 5).add(EARTH, 5).add(FIRE, 5), nBronze, nBronze, nBronze, nBronze, nBronze, nBronze, "nuggetThaumium", "nuggetThaumium", "nuggetBrass");
		recipeThaumicBronzeCoated = addShapelessArcaneCraftingRecipe(keyThaumicBronze, coatedThaumicBronze, new AspectList().add(EARTH, 5).add(WATER, 5), "ingotThaumicBronzeRaw", nHg, salisPinch, "itemClay");

		recipeThaumicBronzeChain = addArcaneCraftingRecipe(keyBronzeChain, ItemHelper.cloneStack(thaumicBronzeChain, 12), new AspectList().add(ORDER, 10).add(FIRE, 5), " X ", "X X", 'X', "ingotThaumicBronze");

		recipeBronzeChainHelmet = addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainHelmet.getStack(), new AspectList().add(ORDER, 10).add(EARTH, 5).add(FIRE, 5), "XXX", "X X", 'X', chainTBronze);
		recipeBronzeChainmail = addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainmail.getStack(), new AspectList().add(ORDER, 25).add(EARTH, 12).add(FIRE, 12), "X X", "XXX", "XXX", 'X', chainTBronze);
		recipeBronzeChainGreaves = addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainGreaves.getStack(), new AspectList().add(ORDER, 20).add(EARTH, 10).add(FIRE, 10), "XXX", "X X", "X X", 'X', chainTBronze);
		recipeBronzeChainBoots = addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainBoots.getStack(), new AspectList().add(ORDER, 5).add(EARTH, 3).add(FIRE, 3), "X X", "X X", 'X', chainTBronze);

		//recipeRunicInfuser = addArcaneCraftingRecipe(keyRunicInfuser, runicInfuser, ThaumcraftHelper.newPrimalAspectList(25, 10, 10, 15, 30, 15), "QRQ", "SBS", "ITI", 'Q', nHg, 'R', visRelay, 'S', arcStoneSlab, 'B', shardBalanced, 'I', "ingotThaumium", 'T', table);
		recipeArcaneSingularity = addShapelessArcaneCraftingRecipe(keyRunicInfuser, arcaneSingularity, ThaumcraftHelper.newPrimalAspectList(2, 10, 0, 0, 5, 5), itemAlumentum, itemNitor);
		recipeStableSingularity = addShapelessArcaneCraftingRecipe(keyRunicInfuser, stabilizedSingularity, ThaumcraftHelper.newPrimalAspectList(5, 10, 5, 5, 30, 5), arcaneSingularity, redstone, salisMundus, shardBalanced);

		recipeEnchSilverwood = addShapelessArcaneCraftingRecipe(keyEnchSilverwood, enchantedSilverwood, new AspectList().add(ORDER, 5), planksSilverwood, salisMundus, salisMundus);
		recipeConsSilverwood = addShapelessArcaneCraftingRecipe(keyEnchSilverwood, consecratedSilverwood, new AspectList().add(ORDER, 10).add(FIRE, 5), consecratedSilverwood, salisPinch, salisPinch, "nuggetSilver", nHg, itemNitor);

		//recipeDarkAlchemicalInfuser = addArcaneCraftingRecipe(keyDarkRunicInfuser, darkRunicInfuser, ThaumcraftHelper.newPrimalAspectList(15, 5, 10, 10, 20, 30), "GVG", "MSM", "ORO", 'G', nAu, 'V', voidSeed, 'M', mirror, 'S', stableSingularity, 'O', obsTotem, 'R', runicInfuser);

		recipePrimalGoggles = addInfusionCraftingRecipe(keyRobesPrimal, primalGoggles.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackGoggles, cottonEnchanted, cottonEnchanted, dustSalisMundus, dustSalisMundus, itemPrimalCharm, stackThaumometer);
		recipePrimalRobes = addInfusionCraftingRecipe(keyRobesPrimal, primalRobes.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackChestRobe, cottonEnchanted, cottonEnchanted, dustSalisMundus, dustSalisMundus, itemPrimalCharm, ingotThaumium);
		recipePrimalPants = addInfusionCraftingRecipe(keyRobesPrimal, primalPants.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackLegsRobe, cottonEnchanted, cottonEnchanted, dustSalisMundus, dustSalisMundus, itemPrimalCharm, itemNitor);
		recipePrimalBoots = addInfusionCraftingRecipe(keyRobesPrimal, primalBoots.getStack(), 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackBootsRobe, cottonEnchanted, cottonEnchanted, dustSalisMundus, dustSalisMundus, itemPrimalCharm, planksGreatwood);

		recipeWardenicHardener = addInfusionCraftingRecipe(keyWardenicObsidian, wardenicHardener, 2, new AspectList().add(EARTH, 8).add(COLD, 8).add(MAGIC, 4).add(ORDER, 8), stabilizedSingularity, new ItemStack(Items.redstone), dustSalisMundus, excubituraOilPure, itemShardWater, itemShardWater, itemShardOrder);
		if (Loader.isModLoaded("ThermalFoundation")) {
			recipeWardenicHardenerAlt = addInfusionCraftingRecipe(keyWardenicObsidian, wardenicHardener, 2, new AspectList().add(EARTH, 8).add(COLD, 8).add(MAGIC, 4).add(ORDER, 8), stabilizedSingularity, new ItemStack(Items.redstone), dustSalisMundus, excubituraOilPure, new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 513));
		}

		recipeExcubituraPaste = addShapelessArcaneCraftingRecipe(keyExcubituraPaste, excubituraPaste, new AspectList().add(EARTH, 5).add(ENTROPY, 3), "itemExcubituraPetal", "itemExcubituraPetal", new ItemStack(Items.bowl));

		recipeExcubituraFabric = addArcaneCraftingRecipe(keyWardencloth, ItemHelper.cloneStack(excubituraFabric, 12), new AspectList().add(ORDER, 5), "FFF", "FPF", "FFF", 'F', "itemCottonFabricEnchanted", 'P', paste);
		recipeWardencloth = addCrucibleRecipe(keyWardencloth, itemWardencloth, excubituraFabric, new AspectList().add(CLOTH, 2).add(ARMOR, 2).add(WARDEN, 2));

		recipeWardenclothSkullcap = addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothSkullcap.getStack(), ThaumcraftHelper.newPrimalAspectList(10), "WEW", "EGE", 'E', enchFabric, 'W', wardencloth, 'G', itemGoggles);
		recipeWardenclothTunic = addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothTunic.getStack(), ThaumcraftHelper.newPrimalAspectList(20), "W W", "WEW", "EWE", 'E', enchFabric, 'W', wardencloth);
		recipeWardenclothPants = addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothPants.getStack(), ThaumcraftHelper.newPrimalAspectList(15), "EWE", "E E", "W W", 'E', enchFabric, 'W', wardencloth);
		recipeWardenclothBoots = addArcaneCraftingRecipe(keyArmorWardencloth, wardenclothBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(10), "E E", "W W", 'E', enchFabric, 'W', wardencloth);

		recipeExcubituraOilUnproc = addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOilUnproc, new AspectList().add(EARTH, 1).add(ORDER, 1), itemPhial, paste, paste, paste, paste);
		recipeExcubituraOil = addShapelessArcaneCraftingRecipe(keyExcubituraOil, excubituraOil, ThaumcraftHelper.newPrimalAspectList(0, 10, 5, 5, 25, 5), "itemExcubituraOilUnprocessed", nHg, salisPinch, itemAlumentum);

		recipeWardenBronzeChain = addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(wardenicBronzeChain, 6), ThaumcraftHelper.newPrimalAspectList(5, 5, 0, 5, 10, 0), "CCC", "SOS", "CCC", 'C', chainTBronze, 'S', salisPinch, 'O', oilExcu);
		recipePrimalBronzeChain = addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(primalBronzeChain, 2), ThaumcraftHelper.newPrimalAspectList(10).add(ORDER, 10), "BCD", "PSP", "DCB", 'B', "nuggetBrass", 'C', chainWBronze, 'D', salisPinch, 'P', itemPrimalCharm, 'S', shardBalanced);
		recipeWardenBronzePlate = addArcaneCraftingRecipe(keyWardenChain, ItemHelper.cloneStack(wardenicBronzePlate, 2), ThaumcraftHelper.newPrimalAspectList(5, 5, 0, 5, 10, 0), "BQC", "DOD", "CTB", 'B', "ingotThaumicBronze", 'C', chainWBronze, 'D', salisPinch, 'Q', nHg, 'O', oilExcu, 'T', "nuggetThaumium");

		recipeWardenicChainHelmet = addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainHelmet.getStack(), ThaumcraftHelper.newPrimalAspectList(20), "PWP", "WGW", 'W', chainWBronze, 'P', chainPBronze, 'G', itemGoggles);
		recipeWardenicChainmail = addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainmail.getStack(), ThaumcraftHelper.newPrimalAspectList(42), "P P", "WBW", "WWW", 'W', chainWBronze, 'P', chainPBronze, 'B', "itemPlateWardenicBronze");
		recipeWardenicChainGreaves = addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainGreaves.getStack(), ThaumcraftHelper.newPrimalAspectList(35), "WBW", "W W", "P P", 'W', chainWBronze, 'P', chainPBronze, 'B', "itemPlateWardenicBronze");
		recipeWardenicChainBoots = addArcaneCraftingRecipe(keyArmorWardenChain, wardenicChainBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(13), "P P", "W W", 'W', chainWBronze, 'P', chainPBronze);

		recipePureOil = addShapelessArcaneCraftingRecipe(keyPureOil, excubituraOilPure, ThaumcraftHelper.newPrimalAspectList(0, 10, 10, 15, 40, 5), oilExcu, oilExcu, oilExcu, oilExcu, salisMundus, "itemArcaneSingularity", itemPhial);

		recipeWardenSteel = addInfusionCraftingRecipe(keyWardenSteel, ingotWardenicSteel, 1, new AspectList().add(METAL, 4).add(ARMOR, 2).add(TOOL, 2).add(ORDER, 4).add(MAGIC, 4).add(WARDEN, 2), ingotSteel, dustSalisMundusTiny, dustSalisMundusTiny, excubituraOilPure, itemQuicksilverDrop);

		recipeWardenSteelChain = addArcaneCraftingRecipe(keyWardenPlate, ItemHelper.cloneStack(wardenicSteelChain, 12), new AspectList().add(ORDER, 25).add(FIRE, 15), " X ", "X X", 'X', "ingotWardenicSteel");
		recipeWardenSteelChainOiled = addArcaneCraftingRecipe(keyWardenPlate, ItemHelper.cloneStack(oiledSteelChain, 6), ThaumcraftHelper.newPrimalAspectList(10, 10, 0, 10, 25, 5), "CCC", "SOS", "CCC", 'C', "itemChainWardenicSteel", 'S', salisMundus, 'O', "itemExcubituraOilPure");

		recipeWardenSteelPlate = addArcaneCraftingRecipe(keyWardenPlate, wardenicSteelPlate, new AspectList().add(ORDER, 1), " A ", "ASA", " A ", 'A', itemAlumentum, 'S', "ingotWardenicSteel");
		recipeDetailedSteelPlate = addArcaneCraftingRecipe(keyWardenPlate, detailedSteelPlate, ThaumcraftHelper.newPrimalAspectList(0, 10, 5, 5, 15, 0), "TCB", "QPQ", "BCT", 'T', "nuggetThaumium", 'B', "nuggetBrass", 'C', "itemChainWardenicSteelOiled", 'Q', nHg, 'P', "itemPlateWardenicSteel");
		recipeRunicSteelPlate = addInfusionCraftingRecipe(keyWardenPlate, runicSteelPlate, 1, new AspectList().add(ARMOR, 4).add(MAGIC, 4).add(ENERGY, 4).add(FLIGHT, 4), detailedSteelPlate, dustSalisMundusTiny, dustSalisMundusTiny, dustSalisMundusTiny, dustSalisMundusTiny, itemWardencloth, new ItemStack(Items.redstone));
		recipesConsecratedSteelPlate = addInfusionCraftingRecipe(keyWardenPlate, consecratedSteelPlate, 2, new AspectList().add(ARMOR, 8).add(MAGIC, 4).add(ORDER, 4), runicSteelPlate, nuggetSilver, itemQuicksilverDrop, new ItemStack(Items.glowstone_dust), consecratedSilverwood, dustSalisMundusTiny);

		recipeWardenicPlateHelmet = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateHelmet.getStack(), ThaumcraftHelper.newPrimalAspectList(30), "CRC", "RGR", 'R', "itemPlateWardenicSteelRunic", 'C', "itemPlateWardenicSteelConsecrated", 'G', stackGoggles);
		recipeWardenicChestplate = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicChestplate.getStack(), ThaumcraftHelper.newPrimalAspectList(70), "C C", "CRC", "RCR", 'R', "itemPlateWardenicSteelRunic", 'C', "itemPlateWardenicSteelConsecrated");
		recipeWardenicPlateGreaves = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateGreaves.getStack(), ThaumcraftHelper.newPrimalAspectList(50), "RCR", "R R", "C C", 'R', "itemPlateWardenicSteelRunic", 'C', "itemPlateWardenicSteelConsecrated");
		recipeWardenicPlateBoots = addArcaneCraftingRecipe(keyArmorWardenSteel, wardenicPlateBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(20), "R R", "C C", 'R',"itemPlateWardenicSteelRunic", 'C',"itemPlateWardenicSteelConsecrated");

		recipeWardenicQuartz = addCrucibleRecipe(keyQuartz, wardenicQuartz, new ItemStack(Items.quartz), new AspectList().add(MAGIC, 4).add(CRYSTAL, 2).add(ENERGY, 2).add(WARDEN, 1));
		recipeWardenicQuartzDust = addCrucibleRecipe(keyQuartz, dustWardenicQuartz, wardenicQuartz, new AspectList().add(ENTROPY, 2));
		recipeWardenicQuartzReconst = addCrucibleRecipe(keyQuartz, wardenicQuartz, dustWardenicQuartz, new AspectList().add(ORDER, 2).add(CRYSTAL, 4));
		recipeWardenicQuartzInf = addInfusionCraftingRecipe(keyQuartz, wardenicQuartzInf, 2, new AspectList().add(WARDEN, 4).add(MAGIC, 8).add(CRYSTAL, 4), wardenicQuartzBlock, dustSalisMundus, excubituraOilPure);

		recipeWardenicCrystal = addCrucibleRecipe(keyWardenCrystal, wardenicCrystal, wardenicQuartzInf, new AspectList().add(CRYSTAL, 32).add(AURA, 8).add(ORDER, 8).add(WARDEN, 16));
		recipeWardenicCrystalDust = addCrucibleRecipe(keyWardenCrystal, dustWardenicCrystal, wardenicCrystal, new AspectList().add(ENTROPY, 4));
		recipeWardenicCrystalReconst = addCrucibleRecipe(keyWardenCrystal, wardenicCrystal, dustWardenicCrystal, new AspectList().add(ORDER, 4).add(CRYSTAL, 8));

		recipeWardenicBinder = addInfusionCraftingRecipe(keyWardenComposite, ItemHelper.cloneStack(binderWardenic, 8), 2, new AspectList(), dustWardenicCrystal, dustWardenicQuartz, dustWardenicQuartz, dustSalisMundus, dustSalisMundus, dustSalisMundus, dustSalisMundus, quicksilver, excubituraOilPure);
		recipeWardenicCompositeRaw = addArcaneCraftingRecipe(keyWardenComposite, ItemHelper.cloneStack(rawWardenicComposite, 2), ThaumcraftHelper.newPrimalAspectList(0, 5, 0, 10, 20, 0), "BBB", "SSS", "WWW", 'B', "ingotWardenicBronze", 'S', "ingotWardenicSteel", 'W', "ingotWardenicMetal");
		recipeWardenicCompositeIngot = addInfusionCraftingRecipe(keyWardenComposite, ingotWardenicComposite, 3, new AspectList().add(METAL, 4).add(MAGIC, 4).add(WARDEN, 4).add(ARMOR, 2).add(TOOL, 2).add(ORDER, 4), rawWardenicComposite, binderWardenic, binderWardenic, dustSalisMundus, dustWardenicQuartz);

		recipeWardenicCompositePlate = addArcaneCraftingRecipe(keyWardenCompositePlate, wardenicCompositePlate, new AspectList().add(ORDER, 1), " A ", "AIA", " A ", 'A', aluDenseTemp, 'I', "ingotWardenicComposite");

		recipeFittedCompositePlate = addArcaneCraftingRecipe(keyWardenCompositeFitting, fittedCompositePlate, ThaumcraftHelper.newPrimalAspectList(0, 15, 0, 10, 15, 0), "CNW", "BPB", "WNC", 'C', "itemChainWardenicSteelOiled", 'W', wardencloth, 'N', "nuggetWardenicSteel", 'B', "itemBinderWardenic", 'P', "itemPlateWardenicComposite");
		recipeDetailedCompositePlate = addArcaneCraftingRecipe(keyWardenCompositeFitting, detailedCompositePlate, ThaumcraftHelper.newPrimalAspectList(0, 10, 5, 10, 15, 0), "EQE", "TPT", "BSB", 'E', "nuggetThaumicElectrum", 'Q', "quicksilver", 'T', "nuggetThaumium", 'P', "itemPlateWardenicCompositeFitted", 'B', "nuggetBrass", 'S', salisMundus);
		recipeRunicCompositePlate = addInfusionCraftingRecipe(keyWardenCompositeFitting, runicCompositePlate, 2, new AspectList().add(ARMOR, 4).add(MAGIC, 8).add(ENERGY, 4).add(FLIGHT, 4).add(WARDEN, 2), detailedCompositePlate, dustSalisMundus, dustSalisMundus, nuggetThaumicElectrum, new ItemStack(Items.redstone));
		recipeConsecratedCompositePlate = addInfusionCraftingRecipe(keyWardenCompositeFitting, consecratedCompositePlate, 4, new AspectList().add(ARMOR, 8).add(MAGIC, 12).add(ORDER, 4).add(ENERGY, 4).add(WARDEN, 2), runicCompositePlate, nuggetSilver, itemQuicksilverDrop, new ItemStack(Items.glowstone_dust), consecratedSilverwood, consecratedSilverwood, dustSalisMundus);
		recipePrimalCompositePlate = addInfusionCraftingRecipe(keyWardenCompositeFitting, primalCompositePlate, 6, ThaumcraftHelper.newPrimalAspectList(8).add(MAGIC, 16).add(ENERGY, 8).add(WARDEN, 4).add(ARMOR, 4), consecratedCompositePlate, itemPrimalCharm, dustSalisMundus, dustSalisMundus, itemShardBalanced, nuggetBrass, quicksilver);

		recipeWardenicCompositeHelmet = addArcaneCraftingRecipe(keyArmorWardenComposite, wardenicCompositeHelmet.getStack(), ThaumcraftHelper.newPrimalAspectList(65), "PCP", "CGC", 'C', "itemPlateWardenicCompositeConsecrated", 'P', "itemPlateWardenicCompositePrimal", 'G', stackGoggles);
		recipeWardenicCompositeChestplate = addArcaneCraftingRecipe(keyArmorWardenComposite, wardenicCompositeChestplate.getStack(), ThaumcraftHelper.newPrimalAspectList(110), "P P", "CCC", "CCC", 'C', "itemPlateWardenicCompositeConsecrated", 'P', "itemPlateWardenicCompositePrimal");
		recipeWardenicCompositeGreaves = addArcaneCraftingRecipe(keyArmorWardenComposite, wardenicCompositeGreaves.getStack(), ThaumcraftHelper.newPrimalAspectList(100), "PCP", "C C", "C C", 'C', "itemPlateWardenicCompositeConsecrated", 'P', "itemPlateWardenicCompositePrimal");
		recipeWardenicCompositeBoots = addArcaneCraftingRecipe(keyArmorWardenComposite, wardenicCompositeBoots.getStack(), ThaumcraftHelper.newPrimalAspectList(60), "P P", "C C", 'C', "itemPlateWardenicCompositeConsecrated", 'P', "itemPlateWardenicCompositePrimal");

	}

	public static void loadResearch() {
		researchThaumRev = new FluxGearResearchItem(keyThaumRev, new AspectList(), 0, 0, 0, potato);
		researchMaterial = new FluxGearResearchItem(keyMaterial, new AspectList(), 0, 3, 0, oreDioptase);
        researchAlloys = new FluxGearResearchItem(keyAlloys, new AspectList(), -2, 2, 0, ingotBrass);

		researchCotton = new FluxGearResearchItem(keyCotton, new AspectList().add(CLOTH, 4).add(ARMOR, 3).add(MAGIC, 3), 0, 7, 1, cottonEnchanted);
		researchAniPiston = new FluxGearResearchItem(keyAniPiston, new AspectList().add(AIR, 3).add(MECHANISM, 3).add(MOTION, 3), 0, 5, 1, animatedPiston);
		researchThaumicBronze = new FluxGearResearchItem(keyThaumicBronze, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ORDER, 1), -3, 4, 1, ingotThaumicBronze);
		researchBronzeChain = new FluxGearResearchItem(keyBronzeChain, new AspectList().add(METAL, 4).add(MAGIC, 2).add(CRAFT, 3), -5, 5, 1, thaumicBronzeChain);
		researchArmorBronzeChain = new FluxGearResearchItem(keyArmorBronzeChain, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ARMOR, 3), -7, 4, 1, bronzeChainmail);
		researchRunicInfuser = new FluxGearResearchItem(keyRunicInfuser, new AspectList().add(ENERGY, 4).add(MAGIC, 4).add(AURA, 2).add(CRAFT, 3).add(MECHANISM, 4), 2, 3, 2, arcaneSingularity);
		researchEnchSilverwood = new FluxGearResearchItem(keyEnchSilverwood, new AspectList().add(TREE, 3).add(MAGIC, 3).add(AURA, 3).add(ORDER, 3), 3, 2, 1, enchantedSilverwood);
		researchPrimalRobes = new FluxGearResearchItem(keyRobesPrimal, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(ENERGY, 2).add(SENSES, 2).add(ThaumcraftHelper.newPrimalAspectList(1)), -8, 2, 3, primalRobes);

		researchWardenry = new FluxGearResearchItem(keyWardenry, new AspectList().add(WARDEN, 4).add(MAGIC, 3).add(ELDRITCH, 2).add(ENERGY, 2), -2, 0, 2, new ItemStack(blockThaumicPlant));

		researchWardenicObsidian = new FluxGearResearchItem(keyWardenicObsidian, new AspectList().add(EARTH, 4).add(CRYSTAL, 4).add(FIRE, 3).add(MAGIC, 3).add(ARMOR, 4).add(WARDEN, 1), -3, -2, 2, wardenicObsidian);

		researchExcubituraPaste = new FluxGearResearchItem(keyExcubituraPaste, new AspectList().add(PLANT, 4).add(MAGIC, 4).add(WARDEN, 1), -4, 2, 1, excubituraPaste);
		researchWardencloth = new FluxGearResearchItem(keyWardencloth, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(WARDEN, 1), -6, 1, 1, itemWardencloth);
		researchArmorWardencloth = new FluxGearResearchItem(keyArmorWardencloth, new AspectList().add(ARMOR, 4).add(MAGIC, 4).add(CLOTH, 4).add(WARDEN, 4), -8, 0, 1, wardenclothTunic);

		researchExcubituraOil = new FluxGearResearchItem(keyExcubituraOil, new AspectList().add(MAGIC, 4).add(PLANT, 3).add(WARDEN, 2).add(WATER, 3), -5, -1, 1, excubituraOil);
		researchWardenChain = new FluxGearResearchItem(keyWardenChain, new AspectList().add(METAL, 4).add(MAGIC, 4).add(ARMOR, 3).add(WARDEN, 2), -7, -2, 1, wardenicBronzeChain);
		researchArmorWardenChain = new FluxGearResearchItem(keyArmorWardenChain, new AspectList().add(ARMOR, 4).add(METAL, 4).add(MAGIC, 4).add(WARDEN, 4), -9, -3, 1, wardenicChainmail);

		researchPureOil = new FluxGearResearchItem(keyPureOil, new AspectList().add(MAGIC, 4).add(WATER, 4).add(WARDEN, 3).add(ENERGY, 3), -6, -4, 2, excubituraOilPure);
		researchWardenSteel = new FluxGearResearchItem(keyWardenSteel, new AspectList().add(METAL, 5).add(MAGIC, 4).add(TOOL, 2).add(ARMOR, 2).add(WARDEN, 3), -8, -5, 2, ingotWardenicSteel);
		researchWardenPlate = new FluxGearResearchItem(keyWardenPlate, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ARMOR, 3).add(WARDEN, 3), -10, -6, 2, wardenicSteelPlate);
		researchArmorWardenSteel = new FluxGearResearchItem(keyArmorWardenSteel, new AspectList().add(ARMOR, 4).add(METAL, 4).add(MAGIC, 4).add(WARDEN, 4), -12, -8, 2, wardenicChestplate);

		researchQuartz = new FluxGearResearchItem(keyQuartz, new AspectList().add(CRYSTAL, 4).add(MAGIC, 4).add(WARDEN, 4).add(ENERGY, 3).add(TOOL, 2), -7, -7, 2, wardenicQuartz);
		researchWardenCrystal = new FluxGearResearchItem(keyWardenCrystal, new AspectList().add(MAGIC, 4).add(CRYSTAL, 4).add(WARDEN, 4).add(ENERGY, 3).add(ORDER, 3), -9, -8, 2, wardenicCrystal);
		researchWardenComposite = new FluxGearResearchItem(keyWardenComposite, new AspectList().add(METAL, 6).add(MAGIC, 4).add(ARMOR, 4).add(TOOL, 3).add(WARDEN, 4), -11, -10, 2, wardenicCompositePlate);
		researchWardenCompositePlate = new FluxGearResearchItem(keyWardenCompositePlate, new AspectList().add(METAL, 3).add(MAGIC, 2).add(WARDEN, 1), -12, -12, 1, wardenicCompositePlate);
		researchWardenCompositeFitting = new FluxGearResearchItem(keyWardenCompositeFitting, new AspectList().add(METAL, 5).add(MAGIC, 4).add(ARMOR, 4).add(WARDEN, 4).add(ENERGY, 2), -10, -13, 3, consecratedCompositePlate);
		researchArmorWardenComposite = new FluxGearResearchItem(keyArmorWardenComposite, new AspectList().add(ARMOR, 4).add(METAL, 4).add(WARDEN, 4).add(MAGIC, 4).add(ORDER, 4), -8, -14, 3, wardenicCompositeChestplate);
	}

	public static void initResearch() {
		researchThaumRev.setRound().setSpecial().setAutoUnlock().setSiblings(keyMaterial);
		researchMaterial.setRound().setSiblings(keyAlloys).setStub();
        researchAlloys.setRound().setStub();
		researchWardenry.setParents(keyThaumRev).setRound().setSpecial().setLost().setItemTriggers(excubituraRose);

		researchCotton.setParents(keyMaterial).setParentsHidden(keyFabric);
		researchAniPiston.setParents(keyMaterial).setSecondary();
		researchThaumicBronze.setParents(keyMaterial).setParentsHidden(keyThaumium);
		researchBronzeChain.setParents(keyThaumicBronze).setSecondary();
		researchArmorBronzeChain.setParents(keyBronzeChain).setSecondary();
		researchRunicInfuser.setParents(keyMaterial).setParentsHidden(keyThaumium, keyAlumentum, keyNitor, keyVisPower);
		researchEnchSilverwood.setParents(keyRunicInfuser).setSecondary();

		researchPrimalRobes.setParentsHidden(keyThaumRev, keyThaumium, keyCotton, keyGoggles, keyNitor, keyInfusion, "INFUSIONENCHANTMENT");

		researchExcubituraPaste.setConcealed().setParents(keyWardenry);
		researchWardencloth.setParents(keyExcubituraPaste);
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
		researchWardenComposite.setParents(keyWardenCrystal).setParentsHidden(keyThaumicBronze, keyWardenSteel);
		researchWardenCompositePlate.setParents(keyWardenComposite).setParentsHidden(keyThaumicHammermill).setSecondary();
		researchWardenCompositeFitting.setParents(keyWardenCompositePlate).setParentsHidden(keyWardencloth, keyEnchSilverwood);
		researchArmorWardenComposite.setParents(keyWardenCompositeFitting).setParentsHidden(keyGoggles).setSecondary();

		if (researchLevel == 0) { //EASY-MODE
			((FluxGearResearchItem) researchThaumRev).addSiblings(keyAniPiston);
			researchAniPiston.setStub();
			researchThaumicBronze.setSiblings(keyBronzeChain, keyArmorBronzeChain);
			researchBronzeChain.setStub();
			researchArmorBronzeChain.setStub();
			researchRunicInfuser.setSiblings(keyEnchSilverwood);
			researchEnchSilverwood.setStub();
			researchExcubituraPaste.setParentsHidden(keyCotton, keyGoggles).setSiblings(keyWardencloth, keyArmorWardencloth);
			researchWardencloth.setSecondary().setStub().registerResearchItem();
			researchArmorWardencloth.setStub();
			researchExcubituraOil.setParentsHidden(keyBronzeChain).setSiblings(keyWardenChain, keyArmorWardenChain);
			researchWardenChain.setSecondary().setStub();
			researchArmorWardenChain.setStub();
			researchPureOil.setParentsHidden(keyThaumicBronze, keyInfusion);
			researchWardenSteel.setStub();
			((FluxGearResearchItem) researchWardenPlate).addParentsHidden(keyGoggles).setSiblings(keyArmorWardenSteel);
			researchArmorWardenSteel.setStub();
		} else if (researchLevel == 2) { //HARD-MODE
			researchAniPiston.setParentsHidden("BELLOWS");
			researchWardencloth.setParentsHidden(keyCotton);
			researchArmorWardencloth.setParentsHidden(keyGoggles);
			researchWardenChain.setParentsHidden(keyBronzeChain);
			researchArmorWardenChain.setParentsHidden(keyArmorBronzeChain, keyGoggles);
			researchWardenSteel.setParentsHidden(keyThaumicBronze, keyInfusion);
			researchArmorWardenSteel.setParentsHidden(keyGoggles);
		} else { //NORMAL-MODE
			researchBronzeChain.setSiblings(keyArmorBronzeChain);
			researchArmorBronzeChain.setStub();
			researchWardencloth.setParentsHidden(keyCotton, keyGoggles).setSiblings(keyArmorWardencloth);
			researchArmorWardencloth.setStub();
			researchWardenChain.setParentsHidden(keyBronzeChain, keyGoggles).setSiblings(keyArmorWardenChain);
			researchArmorWardenChain.setStub();
			researchWardenSteel.setParentsHidden(keyThaumicBronze, keyInfusion);
			((FluxGearResearchItem) researchWardenPlate).addParentsHidden(keyGoggles).setSiblings(keyArmorWardenSteel);
			researchArmorWardenSteel.setStub();
		}
	}

	public static void registerResearch() {
		researchThaumRev.registerResearchItem();
		researchMaterial.registerResearchItem();
        researchAlloys.registerResearchItem();
		researchWardenry.registerResearchItem();

		researchCotton.registerResearchItem();
		researchAniPiston.registerResearchItem();
		researchThaumicBronze.registerResearchItem();
		researchBronzeChain.registerResearchItem();
		researchArmorBronzeChain.registerResearchItem();
		researchRunicInfuser.registerResearchItem();
		researchEnchSilverwood.registerResearchItem();
		researchPrimalRobes.registerResearchItem();

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
		researchWardenComposite.registerResearchItem();
		researchWardenCompositePlate.registerResearchItem();
		researchWardenCompositeFitting.registerResearchItem();
		researchArmorWardenComposite.registerResearchItem();
	}

	public static void setPages() {
		researchThaumRev.setPages(new ResearchPage("0"));
		researchMaterial.setPages(getMaterialPages());
        researchAlloys.setPages(getAlloyPages());

		researchCotton.setPages(new ResearchPage("0"), new ResearchPage(recipeCottonFiber), new ResearchPage(recipeCottonFabric), new ResearchPage(recipeTreatedCotton), new ResearchPage(recipeEnchantedCotton));
		researchAniPiston.setPages(new ResearchPage("0"), new ResearchPage(recipeAniPiston));
		researchThaumicBronze.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeRaw), new ResearchPage(recipeThaumicBronzeCoated), /*new ResearchPage(coatedThaumicBronze),*/ new ResearchPage("1"));
		researchBronzeChain.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeChain));
		researchArmorBronzeChain.setPages(new ResearchPage("0"), new ResearchPage(recipeBronzeChainHelmet), new ResearchPage(recipeBronzeChainmail), new ResearchPage(recipeBronzeChainGreaves), new ResearchPage(recipeBronzeChainBoots));
		researchRunicInfuser.setPages(new ResearchPage("0"), new ResearchPage(recipeArcaneSingularity), new ResearchPage(recipeStableSingularity));
		researchEnchSilverwood.setPages(new ResearchPage("0"), new ResearchPage(recipeEnchSilverwood));
		researchPrimalRobes.setPages(new ResearchPage("0"), new ResearchPage(recipePrimalGoggles), new ResearchPage(recipePrimalRobes), new ResearchPage(recipePrimalPants), new ResearchPage(recipePrimalBoots));

		researchWardenry.setPages(new ResearchPage("0"), new ResearchPage("1"));

		if (Loader.isModLoaded("ThermalFoundation")) {
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
		researchWardenSteel.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenSteel));
		researchWardenPlate.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenSteelChain), new ResearchPage(recipeWardenSteelChainOiled), new ResearchPage(recipeWardenSteelPlate), new ResearchPage(recipeDetailedSteelPlate), new ResearchPage(recipeRunicSteelPlate), new ResearchPage(recipesConsecratedSteelPlate));
		researchArmorWardenSteel.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicPlateHelmet), new ResearchPage(recipeWardenicChestplate), new ResearchPage(recipeWardenicPlateGreaves), new ResearchPage(recipeWardenicPlateBoots));

		researchQuartz.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicQuartz), new ResearchPage(recipeWardenicQuartzDust), new ResearchPage(recipeWardenicQuartzReconst), new ResearchPage(recipeQuartzBlock), new ResearchPage(recipeWardenicQuartzInf), new ResearchPage(recipeQuartzChiseled), new ResearchPage(recipeQuartzPillar), new ResearchPage(recipeQuartzSlab), /*new ResearchPage(recipeQuartzStair),*/ new ResearchPage(recipeQuartzDeblock), new ResearchPage(recipeQuartzDeslab), /*new ResearchPage(recipeQuartzDestair),*/ new ResearchPage(recipeQuartzResetChiseled), new ResearchPage(recipeQuartzResetPillar));
		researchWardenCrystal.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicCrystal), new ResearchPage(recipeWardenicCrystalDust), new ResearchPage(recipeWardenicCrystalReconst));
		researchWardenComposite.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicMetal), new ResearchPage(recipeWardenicCompositeRaw), new ResearchPage(recipeWardenicCompositeIngot));
		researchWardenCompositePlate.setPages(new ResearchPage("0"), new ResearchPage(recipeAluDenseTemp), new ResearchPage(recipeWardenicCompositePlate));
		researchWardenCompositeFitting.setPages(new ResearchPage("0"), new ResearchPage(recipeFittedCompositePlate), new ResearchPage(recipeDetailedCompositePlate), new ResearchPage(recipeRunicCompositePlate), new ResearchPage(recipeConsecratedCompositePlate), new ResearchPage(recipePrimalCompositePlate)/**/);
		researchArmorWardenComposite.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenicCompositeHelmet), new ResearchPage(recipeWardenicCompositeChestplate), new ResearchPage(recipeWardenicCompositeGreaves), new ResearchPage(recipeWardenicCompositeBoots));

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

	public static ResearchPage[] getMaterialPages() {
		List<ResearchPage> list = new ArrayList<ResearchPage>(8);
		list.add(new ResearchPage("0"));

		List<ItemStack> ores = new ArrayList<ItemStack>(9);
		//if (ThaumRevConfig.enableSphalerite) {
			//list.add(new ResearchPage(oreSphalerite));
		//}

		//.addPages(, )
		//.addPages(ThaumRevConfig.enableBronze, new ResearchPage(recipeBronze), new ResearchPage(rawBronze))
		//.addPages(ThaumRevConfig.enableElectrum, new ResearchPage(recipeElectrum), new ResearchPage(rawElectrum))
		list.add(new ResearchPage(recipeSalisTiny));
		list.add(new ResearchPage(recipeSalis));

		ResearchPage[] pages = new ResearchPage[list.size()];
		list.toArray(pages);
		return pages;
	}

    public static ResearchPage[] getAlloyPages() {
        List<ResearchPage> list = new ArrayList<ResearchPage>(8);
        list.add(new ResearchPage("0"));

        List<ResearchPage> smelting = new ArrayList<ResearchPage>(8);
        List<IRecipe> dusts = new ArrayList<IRecipe>(8);
        List<IRecipe> tiny = new ArrayList<IRecipe>(8);

        if (ThaumRevConfig.enableBrass) {
            list.add(new ResearchPage(recipeBrass));
            smelting.add(new ResearchPage(rawBrass));
            dusts.add(recDustBrass);
        }
        if (ThaumRevConfig.enableBronze) {
            list.add(new ResearchPage(recipeBronze));
            smelting.add(new ResearchPage(rawBronze));
            dusts.add(recDustBronze);
        }
        if (ThaumRevConfig.enableBismuthBronze) {
            list.add(new ResearchPage(recipeBismuthBronze));
            smelting.add(new ResearchPage(rawBismuthBronze));
            dusts.add(recDustBismuthBronze[0]);
            dusts.add(recDustBismuthBronze[1]);
        }
        if (ThaumRevConfig.enableMithril) {
            list.add(new ResearchPage(recipeMithril));
            smelting.add(new ResearchPage(rawMithril));
            dusts.add(recDustMithril);
        }
        if (ThaumRevConfig.enableTinkersBronze) {
            list.add(new ResearchPage(recipeTinkersBronze));
            smelting.add(new ResearchPage(rawTinkersBronze));
            dusts.add(recDustTinkersBronze);
        }
        if (ThaumRevConfig.enableElectrum) {
            list.add(new ResearchPage(recipeElectrum));
            smelting.add(new ResearchPage(rawElectrum));
            dusts.add(recDustElectrum);
        }
        if (ThaumRevConfig.enableAlBronze) {
            list.add(new ResearchPage(recipeAlBronze));
            smelting.add(new ResearchPage(rawAluminiumBronze));
            dusts.add(recDustAlBronze);
        }
        /*if (ThaumRevConfig.enableOsLu) {
            list.add(new ResearchPage(recipeOsLu));
            list.add(new ResearchPage(recCoatOsLu));
            smelting.add(new ResearchPage(coatedOsLu));
        }*/
        
        if (!dusts.isEmpty()) {
            list.add(new ResearchPage("dust"));
            IRecipe[] dust = new IRecipe[dusts.size()];
            dusts.toArray(dust);
            list.add(new ResearchPage(dust));
        }
        /*if (!tiny.isEmpty()) {
            list.add(new ResearchPage("tiny"));
            list.add(new ResearchPage(tiny));
        }*/

        if (ThaumRevConfig.thaumonomiconAlloySmelt) {
            list.add(new ResearchPage("smelt"));
            list.addAll(smelting);
        }

        ResearchPage[] pages = new ResearchPage[list.size()];
        list.toArray(pages);
        return pages;
    }



    public static Aspect tempus;

	public static String redstone = "dustRedstone";
	public static String nAu = "nuggetGold";
    public static String iMth = "ingotArsenoAntimonialBronze";

	public static String enchFabric = "itemEnchantedFabric";
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
