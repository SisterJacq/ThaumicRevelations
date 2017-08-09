package mortvana.thaumrev.library;

import mortvana.thaumrev.melteddashboard.item.FluxGearItemInteractive;
import mortvana.thaumrev.melteddashboard.MaterialHardAir;
import mortvana.thaumrev.melteddashboard.intermod.baubles.item.FluxGearItemBauble;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.oredict.ShapelessOreRecipe;

import mortvana.thaumrev.melteddashboard.item.ItemArmorFluxGear;
import mortvana.thaumrev.melteddashboard.util.helpers.StringHelper;
import mortvana.thaumrev.util.item.ItemArmorInfusableBase;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.crafting.*;
import thaumcraft.api.research.ResearchItem;

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
    public static CreativeTabs thaumicRevelationsTab; //= new FluxGearCreativeTab("PFG-Thaumic", "fluxgear.thaumicTab", wardenAmulet);

    /** MATERIALS **/
    public static Material materialHardAir = new MaterialHardAir();

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


	public static ItemStack wardenJournal1;                 //01001

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

	/** RECIPES **/
	public static ShapelessOreRecipe recipeBrass;
	public static ShapelessOreRecipe recipeBronze;
	public static ShapelessOreRecipe recipeSalisTiny;
	public static ShapelessOreRecipe recipeSalis;

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

    /** RENDER IDs **/
    public static int wardedChestRenderID = -1;

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
}
