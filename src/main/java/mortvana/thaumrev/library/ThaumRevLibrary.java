package mortvana.thaumrev.library;

import mortvana.thaumrev.melteddashboard.MaterialFalseAir;
import mortvana.thaumrev.melteddashboard.item.FluxGearItemInteractive;
import mortvana.thaumrev.melteddashboard.intermod.baubles.item.FluxGearItemBauble;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import mortvana.thaumrev.melteddashboard.item.ItemArmorFluxGear;
import mortvana.thaumrev.util.item.ItemArmorInfusableBase;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.crafting.*;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.wands.ItemFocusBasic;

import static thaumcraft.api.aspects.Aspect.*;

public class ThaumRevLibrary {

    /** MOD CONSTANTS **/
    public static final String MOD_ID = "ThaumicRevelations";
	public static final String MOD_NAME = "Thaumic Revelations";
	public static final String MOD_VERSION = "vPO.TA.TO.RANDOM-DEV";
	public static final String MOD_DEPENDENCIES = "required-after:Thaumcraft; after:MagicBees[2.3.0)";
	public static final String API_NAME = "thaumicrevelationsapi";
    public static final String RESOURCE_PREFIX = "thaumrev";
    public static final String TEX_LOC_DEFAULT = "thaumrev:";

	public static int researchLevel;

    /** RESEARCH CATEGORIES **/
    public static final String RESEARCH_KEY = "THAUMREV";

    /** ASPECTS **/
    public static final Aspect WARDEN = new Aspect("excubitor", 0x3CD4FC, new Aspect[] { ELDRITCH, DEATH }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/exubitor.png"), 771);
    public static final Aspect CITRUS = new Aspect("citrus", 0xFF6E00, new Aspect[] { TREE, SENSES }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/citrus.png"), 771);
    public static final Aspect MAGNET = new Aspect("magnes", 0x515970, new Aspect[] { METAL, ENERGY }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/magnes.png"), 771);
    public static final Aspect FLUX = new Aspect("fluxus", 0xAD0200, new Aspect[] { MAGNET, MECHANISM }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/fluxus.png"), 771);
    public static final Aspect REVELATIONS = new Aspect("patefactio", 0x3971AD, new Aspect[] { TRAVEL, MIND }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/revelatiofez.png"), 771);

	/** ENCHANTMENTS **/
    //public static Enchantment enchantStabilizing;

    /** CREATIVE TABS **/
    public static CreativeTabs thaumicRevelationsTab;

    /** MATERIALS **/
    public static Material materialFalseAir = new MaterialFalseAir();

    /** BLOCKS **/
    public static Block blockThaumicPlant;
    public static Block blockFakeAir;
	public static Block blockWoodDecor;
	public static Block blockStoneDecor;
	public static Block blockMetalDecor;
	public static Block blockStorage;
	public static Block blockTransparent;
	public static Block blockWoodDevice;
	public static Block blockStoneDevice;
	public static Block blockMetalDevice;

    /** ITEMS **/
    public static FluxGearItemInteractive generalItem;
    public static FluxGearItemBauble thaumicBauble;


    /** ITEMSTACKS **/
    public static ItemStack potato = new ItemStack(Items.potato); //Used for debugging, placeholding, and such

	public static ItemStack excubituraRose;

	public static ItemStack wardenicObsidian;
	public static ItemStack eldritchStone;
	public static ItemStack wardenicQuartzBlock;
	public static ItemStack wardenicQuartzChiseled;
	public static ItemStack wardenicQuartzPillar;

	public static ItemStack blockCopper;
	public static ItemStack blockZinc;
	public static ItemStack blockTin;
	public static ItemStack blockSilver;
	public static ItemStack blockBrass;
	public static ItemStack blockBronze;
	public static ItemStack blockThaumicBronze;
	public static ItemStack blockSteel;
	public static ItemStack blockVoidbrass;
	public static ItemStack blockVoidsteel;
	public static ItemStack blockElectrum;
	public static ItemStack blockWardenicSteel;
	public static ItemStack blockThaumicElectrum;
	public static ItemStack blockVoidmetal;

	public static ItemStack ingotCopper;                    //00000
	public static ItemStack ingotZinc;                      //00001
	public static ItemStack ingotTin;                       //00002
	public static ItemStack ingotSilver;                    //00003

	public static ItemStack nuggetCopper;                   //00010
	public static ItemStack nuggetZinc;                     //00011
	public static ItemStack nuggetTin;                      //00012
	public static ItemStack nuggetSilver;                   //00013

	public static ItemStack dustCopper;                     //00020
	public static ItemStack dustZinc;                       //00021
	public static ItemStack dustTin;                        //00022
	public static ItemStack dustSilver;                     //00023

	public static ItemStack ingotBrass;                     //00030
	public static ItemStack ingotBronze;                    //00031
	public static ItemStack ingotThaumicBronze;             //00032
	public static ItemStack ingotSteel;                     //00033
	public static ItemStack ingotVoidbrass;                 //00034
	public static ItemStack ingotVoidsteel;                 //00035
	public static ItemStack ingotElectrum;                  //00036

	public static ItemStack nuggetBrass;                    //00040
	public static ItemStack nuggetBronze;                   //00041
	public static ItemStack nuggetThaumicBronze;            //00042
	public static ItemStack nuggetSteel;                    //00043
	public static ItemStack nuggetVoidbrass;                //00044
	public static ItemStack nuggetVoidsteel;                //00045
	public static ItemStack nuggetElectrum;                 //00046

	public static ItemStack dustBrass;                      //00050
	public static ItemStack dustBronze;                     //00051
	public static ItemStack dustThaumicBronze;              //00052
	public static ItemStack dustSteel;                      //00053
	public static ItemStack dustVoidbrass;                  //00054
	public static ItemStack dustVoidsteel;                  //00055
	public static ItemStack dustElectrum;                   //00056

	public static ItemStack rawBrass;                       //00060
	public static ItemStack rawBronze;                      //00061
	public static ItemStack rawThaumicBronze;               //00062

	public static ItemStack rawElectrum;                    //00066

	public static ItemStack dustSalisMundusTiny;            //00070

	public static ItemStack coatedThaumicBronze;            //00072

	public static ItemStack ceramicSlag;                    //00080
	public static ItemStack thaumicSlag;                    //00081
	public static ItemStack arcaneSingularity;              //00082
	public static ItemStack stabilizedSingularity;          //00083
	public static ItemStack animatedPiston;                 //00084
	public static ItemStack enchantedSilverwood;            //00085

	public static ItemStack thaumicBronzeChain;             //00092
	public static ItemStack eldritchCog;                    //00093

	public static ItemStack excubituraPetal;                //00100

	public static ItemStack excubituraPaste;                //00110
	public static ItemStack excubituraFabric;               //00111
	public static ItemStack itemWardencloth;                //00112

	public static ItemStack excubituraOilUnproc;            //00120
	public static ItemStack excubituraOil;                  //00121
	public static ItemStack wardenicBronzeChain;            //00122
	public static ItemStack primalBronzeChain;              //00123
	public static ItemStack wardenicBronzePlate;            //00124

	public static ItemStack excubituraOilPure;              //00130
	public static ItemStack wardenicSteelChain;             //00131
	public static ItemStack wardenicSteelPlate;             //00132
	public static ItemStack detailedSteelPlate;             //00133
	public static ItemStack runicSteelPlate;                //00134

	public static ItemStack wardenicQuartz;                 //00140
	public static ItemStack wardenicCrystal;                //00141
	public static ItemStack dustWardenicQuartz;             //00142
	public static ItemStack wardenicCrystalCrushed;         //00143
	public static ItemStack binderWardenic;                 //00144
	public static ItemStack ingotWardenicAlloy;             //00145
	public static ItemStack plateWardenic;                  //00146

	public static ItemStack wardenicCrystalAwakened;        //00150

	public static ItemStack ingotWardenicSteel;             //00500
	public static ItemStack ingotThaumicElectrum;           //00501

	public static ItemStack nuggetWardenicSteel;            //00510
	public static ItemStack nuggetThaumicElectrum;          //00511

	public static ItemStack dustWardenicSteel;              //00520
	public static ItemStack dustThaumicElectrum;            //00521

	public static ItemStack wardenJournal1;                 //01001

	public static ItemStack wardenicHardener;               //01050

	public static ItemStack firedThaumicBronze;             //01102

	public static ItemStack seedExcubitura;                 //01200
	public static ItemStack seedCotton;                     //01201

    public static ItemStack wardenAmulet;                   //00000
    public static ItemStack loveRing;                       //00001

	/** ARMORS **/
	public static ItemArmorFluxGear bronzeChainHelmet;
	public static ItemArmorFluxGear bronzeChainmail;
	public static ItemArmorFluxGear bronzeChainGreaves;
	public static ItemArmorFluxGear bronzeChainBoots;

	public static ItemArmorFluxGear primalGoggles;
	public static ItemArmorFluxGear primalRobes;
	public static ItemArmorFluxGear primalPants;
	public static ItemArmorFluxGear primalBoots;

	public static ItemArmorInfusableBase wardenclothSkullcap;
	public static ItemArmorInfusableBase wardenclothTunic;
	public static ItemArmorInfusableBase wardenclothPants;
	public static ItemArmorInfusableBase wardenclothBoots;

	public static ItemArmorInfusableBase wardenicChainHelmet;
	public static ItemArmorInfusableBase wardenicChainmail;
	public static ItemArmorInfusableBase wardenicChainGreaves;
	public static ItemArmorInfusableBase wardenicChainBoots;

	public static ItemArmorInfusableBase wardenicPlateHelmet;
	public static ItemArmorInfusableBase wardenicChestplate;
	public static ItemArmorInfusableBase wardenicPlateGreaves;
	public static ItemArmorInfusableBase wardenicPlateBoots;

	/** TOOLS **/

	/** OTHER EQUIPMENT **/
	public static ItemFocusBasic itemFocusPurity;

	/** RECIPES **/
	public static ShapelessOreRecipe recipeBrass;
	public static ShapelessOreRecipe recipeBronze;
	public static ShapelessOreRecipe recipeSalisTiny;
	public static ShapelessOreRecipe recipeSalis;
	public static ShapedArcaneRecipe recipeAniPiston;

	public static ShapedOreRecipe recipeQuartzBlock;
	public static ShapedOreRecipe recipeQuartzChiseled;
	public static ShapedOreRecipe recipeQuartzPillar;
	public static ShapedOreRecipe recipeQuartzSlab;
	public static ShapedOreRecipe recipeQuartzStair;
	public static ShapelessOreRecipe recipeQuartzDeblock;
	public static ShapelessOreRecipe recipeQuartzDeslab;
	public static ShapelessOreRecipe recipeQuartzDestair;
	public static ShapelessOreRecipe recipeQuartzResetChiseled;
	public static ShapelessOreRecipe recipeQuartzResetPillar;

	public static ShapelessArcaneRecipe recipeThaumicBronzeRaw;
	public static ShapelessArcaneRecipe recipeThaumicBronzeCoated;

	public static ShapedArcaneRecipe recipeThaumicBronzeChain;

	public static ShapedArcaneRecipe recipeBronzeChainHelmet;
	public static ShapedArcaneRecipe recipeBronzeChainmail;
	public static ShapedArcaneRecipe recipeBronzeChainGreaves;
	public static ShapedArcaneRecipe recipeBronzeChainBoots;

	public static ShapedArcaneRecipe recipeRunicInfuser;
	public static ShapelessArcaneRecipe recipeArcaneSingularity;
	public static ShapelessArcaneRecipe recipeStableSingularity;

	public static ShapedArcaneRecipe recipeDarkRunicInfuser;

	public static InfusionRecipe recipeAlchemicalInfuser;

	public static InfusionRecipe recipeDarkAlchemicalInfuser;

	public static InfusionRecipe recipePrimalGoggles;
	public static InfusionRecipe recipePrimalRobes;
	public static InfusionRecipe recipePrimalPants;
	public static InfusionRecipe recipePrimalBoots;

	public static InfusionRecipe recipeWardenicHardener;
	public static InfusionRecipe recipeWardenicHardenerAlt;

	public static ShapelessArcaneRecipe recipeExcubituraPaste;

	public static ShapedArcaneRecipe recipeExcubituraFabric;
	public static CrucibleRecipe recipeWardencloth;

	public static ShapedArcaneRecipe recipeWardenclothSkullcap;
	public static ShapedArcaneRecipe recipeWardenclothTunic;
	public static ShapedArcaneRecipe recipeWardenclothPants;
	public static ShapedArcaneRecipe recipeWardenclothBoots;

	public static ShapelessArcaneRecipe recipeExcubituraOilUnproc;
	public static ShapelessArcaneRecipe recipeExcubituraOil;

	public static ShapedArcaneRecipe recipeWardenBronzeChain;
	public static ShapedArcaneRecipe recipePrimalBronzeChain;
	public static ShapedArcaneRecipe recipeWardenBronzePlate;

	public static ShapedArcaneRecipe recipeWardenicChainHelmet;
	public static ShapedArcaneRecipe recipeWardenicChainmail;
	public static ShapedArcaneRecipe recipeWardenicChainGreaves;
	public static ShapedArcaneRecipe recipeWardenicChainBoots;

	public static ShapelessArcaneRecipe recipePureOil;

	public static InfusionRecipe[] recipesWardenSteel;

	public static ShapedArcaneRecipe recipeWardenSteelChain;
	public static /*ThaumicCompressorRecipe*/ ShapedArcaneRecipe recipeWardenSteelPlate;
	public static ShapedArcaneRecipe recipeDetailedSteelPlate;
	public static InfusionRecipe recipeRunicSteelPlate;

	public static ShapedArcaneRecipe recipeWardenicPlateHelmet;
	public static ShapedArcaneRecipe recipeWardenicChestplate;
	public static ShapedArcaneRecipe recipeWardenicPlateGreaves;
	public static ShapedArcaneRecipe recipeWardenicPlateBoots;

	// /** RESEARCH **/
	public static ResearchItem researchThaumRev;
	public static String keyThaumRev = "THAUMIC_REVELATIONS";

	public static ResearchItem researchWardenry;
	public static String keyWardenry = "THAUMIC_WARDEN";

	public static ResearchItem researchEldritch;
	public static String keyEldritch = "ELDRITCH_WORKINGS";

	public static ResearchItem researchMagneoturgy;
	public static String keyMagneoturgy = "MAGNEOTURGY";


	public static ResearchItem researchMaterial;
	public static String keyMaterial = "MATERIAL_TRv";


	public static ResearchItem researchThaumicBronze;
	public static String keyThaumicBronze = "THAUMIC_BRONZE";

	public static ResearchItem researchBronzeChain;
	public static String keyBronzeChain = "TBRONZE_CHAIN";

	public static ResearchItem researchArmorBronzeChain;
	public static String keyArmorBronzeChain = "ARMOR_TBRONZE";

	public static ResearchItem researchRunicInfuser;
	public static String keyRunicInfuser = "RUNIC_INFUSER";

	public static ResearchItem researchPrimalRobes;
	public static String keyRobesPrimal = "ROBES_PRIMAL";


	public static ResearchItem researchWardenicObsidian;
	public static String keyWardenicObsidian = "WARDENIC_OBSIDIAN";

	public static ResearchItem researchThaumicCompressor;
	public static String keyThaumicCompressor = "THAUMIC_COMPRESSOR";


	public static ResearchItem researchExcubituraPaste;
	public static String keyExcubituraPaste = "EXCUBITURA_PASTE";

	public static ResearchItem researchWardencloth;
	public static String keyWardencloth = "WARDENCLOTH";

	public static ResearchItem researchArmorWardencloth;
	public static String keyArmorWardencloth = "ARMOR_WARDENCLOTH";

	public static ResearchItem researchExcubituraOil;
	public static String keyExcubituraOil = "EXCUBITURA_OIL";

	public static ResearchItem researchWardenChain;
	public static String keyWardenChain = "WARDENCHAIN";

	public static ResearchItem researchArmorWardenChain;
	public static String keyArmorWardenChain = "ARMOR_WARDENCHAIN";

	public static ResearchItem researchPureOil;
	public static String keyPureOil = "PURE_OIL";

	public static ResearchItem researchWardenSteel;
	public static String keyWardenSteel = "WARDEN_STEEL";

	public static ResearchItem researchWardenPlate;
	public static String keyWardenPlate = "WARDEN_PLATE";

	public static ResearchItem researchArmorWardenSteel;
	public static String keyArmorWardenSteel = "ARMOR_WARDEN_STEEL";



	/** BLOCK NAMES **/
	public static final String[] PLANT_NAMES = {
			"excubitura",
			"wildCotton",
			"thistle"
	};

	public static final String[] DECOR_STONE_NAMES = {
			"obsidianWardenic",
			"stoneEldritch",
			"wardenicQuartz",
			"wardenicQuartzChiseled",
			"wardenicQuartzPillar",
			"wardenicQuartzPillar",
			"wardenicQuartzPillar",
	};

	public static final String[] STORAGE_NAMES = {
			"blockCopper",
			"blockZinc",
			"blockTin",
			"blockSilver",
			"blockBrass",
			"blockBronze",
			"blockThaumicBronze",
			"blockSteel",
			"blockVoidbrass",
			"blockVoidsteel",
			"blockElectrum",
			"blockWardenicSteel",
			"blockThaumicElectrum",
			"blockVoid"//,
			//"",
			//""
	};

	public static final String[] FALSE_AIR_NAMES = {
			"witor"
	};

	public static final String matPrimal = "PRIMAL";
	public static final String matBronzeChain = "BRONZE_CHAIN";
	public static final String matWardencloth = "WARDENCLOTH";
	public static final String matWardenicChain = "WARDENIC_CHAIN";
	public static final String matWardenicSteel = "WARDENIC_STEEL";

	/** NBT KEYS **/
	public static final String DURABILITY = "DURABILITY";
	public static final String REVEALING = "REVEALING";
	public static final String BROKEN = "BROKEN";
	public static final String VISMODIFIER = "VISDISCOUNT";

	/** CLIENT-SIDE **/
	public static int renderDecorStoneID;
	//public static int wardedChestRenderID = -1;
}
