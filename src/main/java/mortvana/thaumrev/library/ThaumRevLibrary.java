package mortvana.thaumrev.library;

import mortvana.melteddashboard.block.material.MaterialFalseAir;
import mortvana.melteddashboard.item.FluxGearItemInteractive;
import mortvana.melteddashboard.intermod.baubles.item.FluxGearItemBauble;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import mortvana.melteddashboard.item.ItemArmorFluxGear;
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
	public static final String API_NAME = "ThaumicRevelationsAPI";
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
	public static Block blockOre;
    public static Block blockFakeAir;
	public static Block blockWoodDecor;
	public static Block blockStoneDecor;
	public static Block blockMetalDecor;
	public static Block blockStorageMain;
	public static Block blockStorageAux;
	public static Block blockTransparent;
	public static Block blockWoodDevice;
	public static Block blockStoneDevice;
	public static Block blockMetalDevice;
	public static Block blockStoneSlab;
	public static Block blockStoneSlabDouble;

    /** ITEMS **/
    public static FluxGearItemInteractive generalItem;
    public static FluxGearItemBauble thaumicBauble;

    /** ITEMSTACKS **/
    public static ItemStack potato = new ItemStack(Items.potato); //Used for debugging, placeholding, and such

	public static ItemStack excubituraRose;
	public static ItemStack wildCotton;
	public static ItemStack wildThistle;

	public static ItemStack wardenicObsidian;
	public static ItemStack eldritchStone;
	public static ItemStack wardenicQuartzBlock;
	public static ItemStack wardenicQuartzChiseled;
	public static ItemStack wardenicQuartzPillar;

	public static ItemStack oreChalcocite;
	public static ItemStack oreSphalerite;
	public static ItemStack oreCassiterite;
	public static ItemStack oreTetrahedrite;
	public static ItemStack oreTennantite;
	public static ItemStack oreNativeSilver;
	public static ItemStack oreIridosmium;
	public static ItemStack oreWolframite;
	public static ItemStack oreBismuthinite;
	public static ItemStack orePyrope;
	public static ItemStack oreDioptase;
	public static ItemStack oreFluonicSapphire;
	public static ItemStack oreXenotime;
	public static ItemStack oreMillerite;

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
	public static ItemStack blockWardenicMetal;
	public static ItemStack blockWardenicComposite;

	public static ItemStack slabWardenicObsidian;
	public static ItemStack slabEldritch;
	public static ItemStack slabWardenicQuartz;

	//GENERAL ITEM
	//00000-00189 METALS/GEMS
	public static ItemStack ingotCopper;                    //00000
	public static ItemStack ingotZinc;                      //00001
	public static ItemStack ingotTin;                       //00002
	public static ItemStack ingotAntimonialBronze;          //00003
	public static ItemStack ingotArsenicalBronze;           //00004
	public static ItemStack ingotSilver;                    //00005
	public static ItemStack ingotTungsten;                  //00006
	public static ItemStack ingotOsmium;                    //00007
	public static ItemStack ingotIridium;                   //00008
	public static ItemStack ingotBismuth;                   //00009

	public static ItemStack nuggetCopper;                   //00010
	public static ItemStack nuggetZinc;                     //00011
	public static ItemStack nuggetTin;                      //00012
	public static ItemStack nuggetAntimonialBronze;         //00013
	public static ItemStack nuggetArsenicalBronze;          //00014
	public static ItemStack nuggetSilver;                   //00015
	public static ItemStack nuggetTungsten;                 //00016
	public static ItemStack nuggetOsmium;                   //00017
	public static ItemStack nuggetIridium;                  //00018
	public static ItemStack nuggetBismuth;                  //00019

	public static ItemStack dustCopper;                     //00020
	public static ItemStack dustZinc;                       //00021
	public static ItemStack dustTin;                        //00022
	public static ItemStack dustAntimonialBronze;           //00023
	public static ItemStack dustArsenicalBronze;            //00024
	public static ItemStack dustSilver;                     //00025
	public static ItemStack dustTungsten;                   //00026
	public static ItemStack dustOsmium;                     //00027
	public static ItemStack dustIridium;                    //00028
	public static ItemStack dustBismuth;                    //00029

	public static ItemStack gemPyrope;                      //00030
	public static ItemStack gemDioptase;                    //00031
	public static ItemStack gemFluonicSapphire;             //00032
	public static ItemStack shardPyrope;                    //00033
	public static ItemStack shardDioptase;                  //00034
	public static ItemStack shardFluonicSapphire;           //00035
	public static ItemStack dustPyrope;                     //00036
	public static ItemStack dustDioptase;                   //00037
	public static ItemStack dustFluonicSapphire;            //00038
	public static ItemStack dustVoidmetal;                  //00039

	public static ItemStack ingotBrass;                     //00040
	public static ItemStack ingotBronze;                    //00041
	public static ItemStack ingotThaumicBronze;             //00042
	public static ItemStack ingotSteel;                     //00043
	public static ItemStack ingotVoidbrass;                 //00044
	public static ItemStack ingotVoidsteel;                 //00045
	public static ItemStack ingotElectrum;                  //00046
	public static ItemStack ingotThaumicElectrum;           //00047
	public static ItemStack ingotVoidtungsten;              //00048
	public static ItemStack ingotOsLu;                      //00049
	public static ItemStack ingotWardenicSteel;             //00050
	public static ItemStack ingotWardenicMetal;             //00051
	public static ItemStack ingotMithril;                   //00052
	public static ItemStack ingotTinkersBronze;             //00053
	public static ItemStack ingotThaumicTinkersBronze;      //00054
	public static ItemStack ingotWardenicTinkersBronze;     //00055

	public static ItemStack nuggetBrass;                    //00060
	public static ItemStack nuggetBronze;                   //00061
	public static ItemStack nuggetThaumicBronze;            //00062
	public static ItemStack nuggetSteel;                    //00063
	public static ItemStack nuggetVoidbrass;                //00064
	public static ItemStack nuggetVoidsteel;                //00065
	public static ItemStack nuggetElectrum;                 //00066
	public static ItemStack nuggetThaumicElectrum;          //00067
	public static ItemStack nuggetVoidtungsten;             //00068
	public static ItemStack nuggetOsLu;                     //00069
	public static ItemStack nuggetWardenicSteel;            //00070
	public static ItemStack nuggetWardenicMetal;            //00071
	public static ItemStack nuggetMithril;                  //00072
	public static ItemStack nuggetTinkersBronze;            //00073
	public static ItemStack nuggetThaumicTinkersBronze;     //00074
	public static ItemStack nuggetWardenicTinkersBronze;    //00075

	public static ItemStack dustBrass;                      //00080
	public static ItemStack dustBronze;                     //00081
	public static ItemStack dustThaumicBronze;              //00082
	public static ItemStack dustSteel;                      //00083
	public static ItemStack dustVoidbrass;                  //00084
	public static ItemStack dustVoidsteel;                  //00085
	public static ItemStack dustElectrum;                   //00086
	public static ItemStack dustThaumicElectrum;            //00087
	public static ItemStack dustVoidtungsten;               //00088
	public static ItemStack dustOsLu;                       //00089
	public static ItemStack dustWardenicSteel;              //00090
	public static ItemStack dustWardenicMetal;              //00091
	public static ItemStack dustMithril;                    //00092
	public static ItemStack dustTinkersBronze;              //00093
	public static ItemStack dustThaumicTinkersBronze;       //00094
	public static ItemStack dustWardenicTinkersBronze;      //00095

	public static ItemStack rawBrass;                       //00100
	public static ItemStack rawBronze;                      //00101
	public static ItemStack rawThaumicBronze;               //00102
	public static ItemStack rawElectrum;                    //00103
	public static ItemStack rawOsLu;                        //00104
	public static ItemStack rawWardenicMetal;               //00105
	public static ItemStack rawMithril;                     //00106
	public static ItemStack rawTinkersBronze;               //00107
	public static ItemStack rawThaumicTinkersBronze;        //00108

	public static ItemStack coatedThaumicBronze;            //00120
	public static ItemStack coatedMithril;                  //00121
	public static ItemStack coatedTinkersBronze;            //00122
	public static ItemStack coatedThaumicTinkersBronze;     //00123

	public static ItemStack firedThaumicBronze;             //00130
	public static ItemStack firedMithril;                   //00131
	public static ItemStack firedTinkersBronze;             //00132
	public static ItemStack firedThaumicTinkersBronze;      //00133

	public static ItemStack dustAer;                        //00140
	public static ItemStack dustIgnis;                      //00141
	public static ItemStack dustAqua;                       //00142
	public static ItemStack dustTerra;                      //00143
	public static ItemStack dustOrdo;                       //00144
	public static ItemStack dustPerditio;                   //00145
	public static ItemStack dustSalisMundusTiny;            //00146
	public static ItemStack gemFluonicPyroptase;            //00147
	public static ItemStack shardFluonicPyroptase;          //00148
	public static ItemStack dustFluonicPyroptase;           //00149

	public static ItemStack ceramicSlag;                    //00150
	public static ItemStack thaumicSlag;                    //00151


	public static ItemStack clusterZinc;                    //00180
	public static ItemStack clusterTennantite;              //00181
	public static ItemStack clusterTetrahedrite;            //00182
	public static ItemStack clusterTungsten;                //00183
	public static ItemStack clusterIridosmium;              //00184
	public static ItemStack clusterBismuth;                 //00185

	//00190-00199 SEEDS
	public static ItemStack seedExcubitura;                 //00190
	public static ItemStack seedCotton;                     //00191

	//00200-00299 WARDENIC ARSENAL
	public static ItemStack excubituraPetal;                //00200

	public static ItemStack excubituraPaste;                //00210
	public static ItemStack excubituraFabric;               //00211
	public static ItemStack itemWardencloth;                //00212

	public static ItemStack excubituraOilUnproc;            //00220
	public static ItemStack excubituraOil;                  //00221
	public static ItemStack wardenicBronzeChain;            //00222
	public static ItemStack primalBronzeChain;              //00223
	public static ItemStack wardenicBronzePlate;            //00224

	public static ItemStack excubituraOilPure;              //00230
	public static ItemStack wardenicSteelChain;             //00231
	public static ItemStack oiledSteelChain;                //00232
	public static ItemStack wardenicSteelPlate;             //00233
	public static ItemStack detailedSteelPlate;             //00234
	public static ItemStack runicSteelPlate;                //00235
	public static ItemStack consecratedSteelPlate;          //00236

	public static ItemStack wardenicQuartz;                 //00240
	public static ItemStack wardenicCrystal;                //00241
	public static ItemStack wardenicQuartzInf;              //00242
	public static ItemStack dustWardenicQuartz;             //00243
	public static ItemStack dustWardenicCrystal;            //00244
	public static ItemStack binderWardenic;                 //00245
	public static ItemStack rawWardenicComposite;           //00246
	public static ItemStack ingotWardenicComposite;         //00247
	public static ItemStack wardenicCompositePlate;         //00248
	public static ItemStack fittedCompositePlate;           //00249
	public static ItemStack detailedCompositePlate;         //00250
	public static ItemStack runicCompositePlate;            //00251
	public static ItemStack consecratedCompositePlate;      //00252
	public static ItemStack primalCompositePlate;           //00253

	public static ItemStack wardenicCrystalAwakened;        //00255

	public static ItemStack wardenicHardener;               //00275

	//00300-00399 MAGNEOTURGY
	//00400-00499 ELDTRICH
	// TENTATIVE ITEMS
	//00500-00549 THAUMIC ASCENSION
	//00550-00599 RUNIC MECHANICS
	//00600-00649 TRANSMUTIVE ENGINEERING
	//00650-00699 THAUMIC DYNAMICS
	//00700-00799 CHRONOTURGY
	//00800-00899 ASTROTURGY/MISC

	//00900-00999 INFUSION SYSTEM

	//01000-01699 GENERAL ITEMS
	public static ItemStack arcaneSingularity;              //01000
	public static ItemStack stabilizedSingularity;          //01001
	public static ItemStack animatedPiston;                 //01002
	public static ItemStack enchantedSilverwood;            //01003
	public static ItemStack consecratedSilverwood;          //01004
	public static ItemStack cotton;                         //01005
	public static ItemStack cottonFiber;                    //01006
	public static ItemStack cottonFabric;                   //01007
	public static ItemStack cottonTreated;                  //01008
	public static ItemStack cottonEnchanted;                //01009
	public static ItemStack thaumicBronzeChain;             //01010
	public static ItemStack eldritchCog;                    //01011
	public static ItemStack eldritchKeystone;               //01012

	//01700-01799 WAND CAPS
	//01800-01899 WAND CORES
	//01900-01999 STAFF CORES

	public static ItemStack wardenJournal1;                 //03001

	//30000+ TEMPORARY ITEMS
	public static ItemStack aluDenseTemp;                   //30000

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

	public static ItemArmorInfusableBase wardenicCompositeHelmet;
	public static ItemArmorInfusableBase wardenicCompositeChestplate;
	public static ItemArmorInfusableBase wardenicCompositeGreaves;
	public static ItemArmorInfusableBase wardenicCompositeBoots;

	/** TOOLS **/

	/** OTHER EQUIPMENT **/
	public static ItemFocusBasic itemFocusPurity;

	/** RECIPES **/
	public static ShapelessOreRecipe recipeBrass;
	public static ShapelessOreRecipe recipeBronze;
	public static ShapelessOreRecipe recipeElectrum;

	public static ShapelessOreRecipe recipeSalisTiny;
	public static ShapelessOreRecipe recipeSalis;

	public static ShapedOreRecipe recipeWardsidianSlab;
	public static ShapedOreRecipe recipeEldritchSlab;
	public static ShapelessOreRecipe recipeWardsidianDeslab;
	public static ShapelessOreRecipe recipeEldritchDeslab;

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

	public static ShapelessOreRecipe recipeCottonFiber;
	public static ShapelessOreRecipe recipeCottonFabric;
	public static ShapedArcaneRecipe recipeTreatedCotton;
	public static CrucibleRecipe recipeEnchantedCotton;

	public static ShapedArcaneRecipe recipeAniPiston;

	public static ShapelessArcaneRecipe recipeThaumicBronzeRaw;
	public static ShapelessArcaneRecipe recipeThaumicBronzeCoated;

	public static ShapedArcaneRecipe recipeThaumicBronzeChain;

	public static ShapedArcaneRecipe recipeBronzeChainHelmet;
	public static ShapedArcaneRecipe recipeBronzeChainmail;
	public static ShapedArcaneRecipe recipeBronzeChainGreaves;
	public static ShapedArcaneRecipe recipeBronzeChainBoots;

	public static ShapedArcaneRecipe recipeRunicInfuser;
	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeArcaneSingularity;
	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeStableSingularity;

	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeEnchSilverwood;
	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeConsSilverwood;

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
	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeExcubituraOil;

	public static ShapedArcaneRecipe recipeWardenBronzeChain;
	public static ShapedArcaneRecipe recipePrimalBronzeChain;
	public static ShapedArcaneRecipe recipeWardenBronzePlate;

	public static ShapedArcaneRecipe recipeWardenicChainHelmet;
	public static ShapedArcaneRecipe recipeWardenicChainmail;
	public static ShapedArcaneRecipe recipeWardenicChainGreaves;
	public static ShapedArcaneRecipe recipeWardenicChainBoots;

	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipePureOil;

	public static InfusionRecipe recipeWardenSteel;

	public static ShapedArcaneRecipe recipeWardenSteelChain;
	public static ShapedArcaneRecipe recipeWardenSteelChainOiled;
	public static /*ThaumicCompressorRecipe*/ ShapedArcaneRecipe recipeWardenSteelPlate;
	public static ShapedArcaneRecipe recipeDetailedSteelPlate;
	public static /*AlchemicalInfuserRecipe*/ InfusionRecipe recipeRunicSteelPlate;
	public static /*RunicInfuserRecipe*/ InfusionRecipe recipesConsecratedSteelPlate;

	public static ShapedArcaneRecipe recipeWardenicPlateHelmet;
	public static ShapedArcaneRecipe recipeWardenicChestplate;
	public static ShapedArcaneRecipe recipeWardenicPlateGreaves;
	public static ShapedArcaneRecipe recipeWardenicPlateBoots;

	public static CrucibleRecipe recipeWardenicQuartz;
	public static CrucibleRecipe recipeWardenicQuartzDust;
	public static CrucibleRecipe recipeWardenicQuartzReconst;
	public static InfusionRecipe recipeWardenicQuartzInf;

	public static CrucibleRecipe recipeWardenicCrystal;
	public static CrucibleRecipe recipeWardenicCrystalDust;
	public static CrucibleRecipe recipeWardenicCrystalReconst;

	public static InfusionRecipe recipeWardenicBinder;
	public static ShapelessOreRecipe recipeWardenicMetal;
	public static ShapedArcaneRecipe recipeWardenicCompositeRaw;
	public static InfusionRecipe recipeWardenicCompositeIngot;

	public static ShapelessOreRecipe recipeAluDenseTemp;    //TEMP-TODO: Add Thaumic Hammermill
	public static /*ThaumicCompressorRecipe*/ ShapedArcaneRecipe recipeWardenicCompositePlate;

	public static /*ThaumicCompressorRecipe*/ ShapedArcaneRecipe recipeFittedCompositePlate;
	public static ShapedArcaneRecipe recipeDetailedCompositePlate;
	public static /*AlchemicalInfuserRecipe*/ InfusionRecipe recipeRunicCompositePlate;
	public static InfusionRecipe recipeConsecratedCompositePlate;
	public static InfusionRecipe recipePrimalCompositePlate;

	public static ShapedArcaneRecipe recipeWardenicCompositeHelmet;
	public static ShapedArcaneRecipe recipeWardenicCompositeChestplate;
	public static ShapedArcaneRecipe recipeWardenicCompositeGreaves;
	public static ShapedArcaneRecipe recipeWardenicCompositeBoots;

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

	public static ResearchItem researchCotton;
	public static String keyCotton = "ENCHCOTTON";

	public static ResearchItem researchAniPiston;
	public static String keyAniPiston = "ANIMATED_PISTON";

	public static ResearchItem researchThaumicBronze;
	public static String keyThaumicBronze = "THAUMIC_BRONZE";

	public static ResearchItem researchBronzeChain;
	public static String keyBronzeChain = "TBRONZE_CHAIN";

	public static ResearchItem researchArmorBronzeChain;
	public static String keyArmorBronzeChain = "ARMOR_TBRONZE";

	public static ResearchItem researchRunicInfuser;
	public static String keyRunicInfuser = "RUNIC_INFUSER";

	public static ResearchItem researchEnchSilverwood;
	public static String keyEnchSilverwood = "ENCH_SILVERWOOD";


	public static ResearchItem researchPrimalRobes;
	public static String keyRobesPrimal = "ROBES_PRIMAL";


	public static ResearchItem researchWardenicObsidian;
	public static String keyWardenicObsidian = "WARDENIC_OBSIDIAN";

	public static ResearchItem researchThaumicHammermill;
	public static String keyThaumicHammermill = "WARDENIC_OBSIDIAN"; //"THAUMIC_HAMMERMILL";


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

	public static ResearchItem researchQuartz;
	public static String keyQuartz = "WARDEN_QUARTZ";

	public static ResearchItem researchWardenCrystal;
	public static String keyWardenCrystal = "WARDEN_CRYSTAL";

	public static ResearchItem researchWardenComposite;
	public static String keyWardenComposite = "WARDEN_COMPOSITE";

	public static ResearchItem researchWardenCompositePlate;
	public static String keyWardenCompositePlate = "WARDEN_COMPOSITE_PLATE";

	public static ResearchItem researchWardenCompositeFitting;
	public static String keyWardenCompositeFitting = "WARDEN_COMPOSITE_FITTING";

	public static ResearchItem researchArmorWardenComposite;
	public static String keyArmorWardenComposite = "ARMOR_WARDEN_COMPOSITE";


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

	public static final String[] ORE_NAMES = {
			"oreChalcocite",
			"oreSphalerite",
			"oreCassiterite",
			"oreTetrahedrite",
			"oreTennantite",
			"oreNativeSilver",
			"oreIridosmium",
			"oreWolframite",
			"oreBismuthinite",
			"orePyrope",
			"oreDioptase",
			"oreFluonicSapphire"
	};

	public static final String[] STORAGE_MAIN_NAMES = {
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
			"blockVoid",
			"blockWardenicMetal",
			"blockWardenicComposite"
	};

	public static final String[] FALSE_AIR_NAMES = {
			"witor"
	};

	public static final String[] SLAB_STONE_NAMES = {
			"obsidianWardenic",
			"stoneEldritch",
			"wardenicQuartz"
	};

	public static final String matPrimal = "PRIMAL";
	public static final String matBronzeChain = "BRONZE_CHAIN";
	public static final String matWardencloth = "WARDENCLOTH";
	public static final String matWardenicChain = "WARDENIC_CHAIN";
	public static final String matWardenicSteel = "WARDENIC_STEEL";
	public static final String matWardenicComposite = "WARDENIC_COMPOSITE";

	/** NBT KEYS **/
	public static final String DURABILITY = "DURABILITY";
	public static final String REVEALING = "REVEALING";
	public static final String BROKEN = "BROKEN";
	public static final String VISMODIFIER = "VISDISCOUNT";

	/** CLIENT-SIDE **/
	public static int renderDecorStoneID;
	//public static int wardedChestRenderID = -1;
}
