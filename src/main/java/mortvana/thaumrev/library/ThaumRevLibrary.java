package mortvana.thaumrev.library;

import mortvana.thaumrev.melteddashboard.item.FluxGearItemInteractive;
import mortvana.thaumrev.melteddashboard.MaterialHardAir;
import mortvana.thaumrev.melteddashboard.intermod.baubles.item.FluxGearItemBauble;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import mortvana.thaumrev.melteddashboard.item.ItemArmorFluxGear;
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

	/** ARMOR ARRAYS **/
	public int[] discountWardencloth = new int[] {5, 2, 2, 1};

	/** ENCHANTMENTS **/
    public static Enchantment enchantStabilizing;

    /** CREATIVE TABS **/
    public static CreativeTabs thaumicRevelationsTab; //= new FluxGearCreativeTab("PFG-Thaumic", "fluxgear.thaumicTab", wardenAmulet);

    /** MATERIALS **/
    public static Material materialHardAir = new MaterialHardAir();

    /** BLOCKS **/
    public static Block blockThaumicPlant;
    public static Block blockFakeAir;

    /** ITEMS **/
    public static FluxGearItemInteractive generalItem;
    public static FluxGearItemBauble thaumicBauble;


    /** ITEMSTACKS **/
	public static ItemStack ingotCopper;                    //00000
	public static ItemStack ingotZinc;                      //00001
	public static ItemStack ingotTin;                       //00002

	public static ItemStack nuggetCopper;                   //00010
	public static ItemStack nuggetZinc;                     //00011
	public static ItemStack nuggetTin;                      //00012

	public static ItemStack dustCopper;                     //00020
	public static ItemStack dustZinc;                       //00021
	public static ItemStack dustTin;                        //00022

	public static ItemStack ingotBrass;                     //00030
	public static ItemStack ingotBronze;                    //00031
	public static ItemStack ingotThaumicBronze;             //00032
	public static ItemStack ingotSteel;                     //00033
	public static ItemStack ingotVoidbrass;                 //00034
	public static ItemStack ingotVoidsteel;                 //00035

	public static ItemStack nuggetBrass;                    //00040
	public static ItemStack nuggetBronze;                   //00041
	public static ItemStack nuggetThaumicBronze;            //00042
	public static ItemStack nuggetSteel;                    //00043
	public static ItemStack nuggetVoidbrass;                //00044
	public static ItemStack nuggetVoidsteel;                //00045

	public static ItemStack dustBrass;                      //00050
	public static ItemStack dustBronze;                     //00051
	public static ItemStack dustThaumicBronze;              //00052
	public static ItemStack dustSteel;                      //00053
	public static ItemStack dustVoidbrass;                  //00054
	public static ItemStack dustVoidsteel;                  //00055

	public static ItemStack rawThaumicBronze;               //00062

	public static ItemStack coatedThaumicBronze;            //00072

	public static ItemStack ceramicSlag;                    //00080
	public static ItemStack thaumicSlag;                    //00081

	public static ItemStack thaumicBronzeChain;             //0090

	public static ItemStack excubituraPetal;                //00100

	public static ItemStack excubituraPaste;                //00110
	public static ItemStack excubituraFabric;               //00111
	public static ItemStack wardencloth;                    //00112

	public static ItemStack excubituraOilUnproc;            //00120
	public static ItemStack excubituraOil;                  //00121
	public static ItemStack wardenBronzeChain;              //00122
	public static ItemStack primalBronzeChain;              //00123
	public static ItemStack wardenBronzePlate;              //00124

	public static ItemStack excubituraOilPure;              //00130

	public static ItemStack wardenicQuartz;                 //00140
	public static ItemStack excubituraCrystal;              //00141

	public static ItemStack excubituraCrystalAwakened;      //00150

	public static ItemStack wardenJournal1;                 //01001

	public static ItemStack firedThaumicBronze;             //01102

    public static ItemStack wardenAmulet;                   //00000
    public static ItemStack loveRing;                       //00001

	/** ARMORS **/
	public static ItemArmorFluxGear bronzeChainHelmet;
	public static ItemArmorFluxGear bronzeMail;
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
	public static ShapelessArcaneRecipe recipeThaumicBronzeRaw;
	public static ShapelessArcaneRecipe recipeThaumicBronzeCoated;

	public static ShapedArcaneRecipe recipeThaumicBronzeChain;

	public static ShapedArcaneRecipe recipeBronzeChainHelmet;
	public static ShapedArcaneRecipe recipeBronzeMail;
	public static ShapedArcaneRecipe recipeBronzeChainGreaves;
	public static ShapedArcaneRecipe recipeBronzeChainBoots;

	public static InfusionRecipe recipePrimalGoggles;
	public static InfusionRecipe recipePrimalRobes;
	public static InfusionRecipe recipePrimalPants;
	public static InfusionRecipe recipePrimalBoots;

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
	public static ShapedArcaneRecipe recipeWardenicMail;
	public static ShapedArcaneRecipe recipeWardenicChainGreaves;
	public static ShapedArcaneRecipe recipeWardenicChainBoots;


	// /** RESEARCH **/
	public static ResearchItem researchThaumRev;
	public static String keyThaumRev = "THAUMIC_REVELATIONS";

	public static ResearchItem researchWardenry;
	public static String keyWardenry = "THAUMIC_WARDEN";

	public static ResearchItem researchEldritch;
	public static String keyEldritch = "ELDRITCH_WORKINGS";

	public static ResearchItem researchMagneoturgy;
	public static String keyMagneoturgy = "MAGNEOTURGY";


	public static ResearchItem researchThaumicBronze;
	public static String keyThaumicBronze = "THAUMIC_BRONZE";

	public static ResearchItem researchBronzeChain;
	public static String keyBronzeChain = "TBRONZE_CHAIN";

	public static ResearchItem researchArmorBronzeChain;
	public static String keyArmorBronzeChain = "ARMOR_TBRONZE";

	public static ResearchItem researchPrimalRobes;
	public static String keyRobesPrimal = "ROBES_PRIMAL";


	public static ResearchItem researchExcubituraPaste;
	public static String keyExcubituraPaste = "EXCUBITURA_PASTE";

	public static ResearchItem researchWardencloth;
	public static String keyWardencloth = "WARDENCLOTH";

	public static ResearchItem researchArmorWardencloth;
	public static String keyArmorWardencloth = "ARMOR_WARDENCLOTH";

	public static ResearchItem researchExcubituraOil;
	public static String keyExcubituraOil = "EXCUBITURA_OIL";

	public static ResearchItem researchWardenBronze;
	public static String keyWardenBronze = "WARDENBRONZE";

	public static ResearchItem researchArmorWardenBronze;
	public static String keyArmorWardenBronze = "ARMOR_WARDENBRONZE";

    /** RENDER IDs **/
    public static int wardedChestRenderID = -1;

	/** BLOCK NAMES **/
	public static final String[] PLANT_NAMES = new String[] {
			"excubitura"
	};

	public static final String matWardencloth = "WARDENCLOTH";
	public static final String matWardenicChain = "WARDENIC_CHAIN";

	/** NBT KEYS **/
	public static final String DURABILITY = "DURABILITY";
	public static final String REVEALING = "REVEALING";
	public static final String BROKEN = "BROKEN";
	public static final String VISMODIFIER = "VISDISCOUNT";
}
