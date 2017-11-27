package mortvana.thaumrev.library;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.crafting.*;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.wands.ItemFocusBasic;

import mortvana.melteddashboard.block.material.MaterialFalseAir;
import mortvana.melteddashboard.intermod.baubles.item.FluxGearItemBauble;
import mortvana.melteddashboard.item.FluxGearItemInteractive;
import mortvana.melteddashboard.item.ItemArmorFluxGear;

import mortvana.thaumrev.util.item.ItemArmorInfusableBase;

import static thaumcraft.api.aspects.Aspect.*;

public class ThaumRevLibrary {

	/** MOD CONSTANTS * */
	public static final String MOD_ID = "ThaumicRevelations";
	public static final String MOD_NAME = "Thaumic Revelations";
	public static final String MOD_VERSION = "vPO.TA.TO.RANDOM-DEV";
	public static final String MOD_DEPENDENCIES = "required-after:Thaumcraft; after:MagicBees[2.3.0)";
	public static final String API_NAME = "ThaumicRevelationsAPI";
	public static final String RESOURCE_PREFIX = "thaumrev";
	public static final String TEX_LOC_DEFAULT = "thaumrev:";

	public static int researchLevel;

	/** RESEARCH CATEGORIES * */
	public static final String RESEARCH_KEY = "THAUMREV";

	/** ASPECTS * */
	public static final Aspect WARDEN = new Aspect("excubitor", 0x3CD4FC, new Aspect[] {ELDRITCH, DEATH}, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/exubitor.png"), 771);
	public static final Aspect CITRUS = new Aspect("citrus", 0xFF6E00, new Aspect[] {TREE, SENSES}, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/citrus.png"), 771);
	public static final Aspect MAGNET = new Aspect("magnes", 0x515970, new Aspect[] {METAL, ENERGY}, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/magnes.png"), 771);
	public static final Aspect FLUX = new Aspect("fluxus", 0xAD0200, new Aspect[] {MAGNET, MECHANISM}, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/fluxus.png"), 771);
	public static final Aspect REVELATIONS = new Aspect("patefactio", 0x3971AD, new Aspect[] {TRAVEL, MIND}, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/revelatiofez.png"), 771);

	/** ENCHANTMENTS **/
	//public static Enchantment enchantStabilizing;

	/** CREATIVE TABS * */
	public static CreativeTabs thaumicRevelationsTab;

	/** MATERIALS * */
	public static Material materialFalseAir = new MaterialFalseAir();

	/** BLOCKS * */
	public static Block blockThaumicPlant;
	public static Block blockOre;
	//public static Block blockFakeAir;
	//public static Block blockWoodDecor;
	public static Block blockStoneDecor;
	//public static Block blockMetalDecor;
	public static Block blockStorageMain;
	//public static Block blockStorageAux;
	//public static Block blockTransparent;
	//public static Block blockWoodDevice;
	//public static Block blockStoneDevice;
	//public static Block blockMetalDevice;
	public static Block blockStoneSlab;
	public static Block blockStoneSlabDouble;

	/** ITEMS * */
	public static FluxGearItemInteractive generalItem;
	public static FluxGearItemBauble thaumicBauble;

	/** ITEMSTACKS * */
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
	public static ItemStack oreMillerite;
	public static ItemStack oreNativeSilver;
	public static ItemStack oreGalena;
	public static ItemStack oreXenotime;
	public static ItemStack oreWolframite;
	public static ItemStack oreIridosmium;
	public static ItemStack oreBismuthinite;
	public static ItemStack oreTennantite;
	public static ItemStack oreTetrahedrite;
	public static ItemStack orePyrope;
	public static ItemStack oreDioptase;
	public static ItemStack oreFluonicSapphire;

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
	//00000-00479 GENERAL ITEMS
	public static ItemStack arcaneSingularity;				//00000
	public static ItemStack stabilizedSingularity;			//00001
	public static ItemStack animatedPiston;					//00002
	public static ItemStack enchantedSilverwood;			//00003
	public static ItemStack consecratedSilverwood;			//00004
	public static ItemStack cotton;							//00005
	public static ItemStack cottonFiber;					//00006
	public static ItemStack cottonFabric;					//00007
	public static ItemStack cottonTreated;					//00008
	public static ItemStack cottonEnchanted;				//00009
	public static ItemStack thaumicBronzeChain;				//00010
	public static ItemStack eldritchCog;					//00011
	public static ItemStack eldritchKeystone;				//00012
	public static ItemStack thistleLeaf;					//00013
	public static ItemStack thistleFlower;					//00014

	public static ItemStack aluminiumHydroxide;				//00016

	//00280-00299 SEEDS
	public static ItemStack seedExcubitura;					//00280
	public static ItemStack seedCotton;						//00281
	public static ItemStack seedThistle;					//00282

	//00300-00324 BASE METAL INGOTS/NATURAL GEMS
	public static ItemStack ingotCopper;					//00300
	public static ItemStack ingotZinc;						//00301
	public static ItemStack ingotTin;						//00302
	public static ItemStack ingotNickel;					//00303
	public static ItemStack ingotSilver;					//00304
	public static ItemStack ingotLead;						//00305
	public static ItemStack ingotLanthanides;				//00306
	public static ItemStack ingotTungsten;					//00307
	public static ItemStack ingotIridium;					//00308
	public static ItemStack ingotBismuth;					//00309
	public static ItemStack ingotArsenicalBronze;			//00310
	public static ItemStack ingotAntimonialBronze;			//00311
	public static ItemStack gemPyrope;						//00312
	public static ItemStack gemDioptase;					//00313
	public static ItemStack gemFluonicSapphire;				//00314
	public static ItemStack ingotOsmium;					//00315
	public static ItemStack ingotNeodymium;					//00316
	public static ItemStack ingotLutetium;					//00317
	public static ItemStack ingotPalladium;					//00318
	public static ItemStack ingotIridosmium;				//00319
	public static ItemStack ingotAluminium;					//00320
	public static ItemStack ingotXenotimeJunk;				//00321

	//00325-00349 SIMPLE ALLOY INGOTS
	public static ItemStack ingotBrass;						//00325
	public static ItemStack ingotBronze;					//00326
	public static ItemStack ingotBismuthBronze;				//00327
	public static ItemStack ingotMithril;					//00328
	public static ItemStack ingotAluminiumBronze;			//00329
	public static ItemStack ingotCupronickel;				//00330
	public static ItemStack ingotTinkersBronze;				//00331
	public static ItemStack ingotConstantan;				//00332
	public static ItemStack ingotInvar;						//00333
	public static ItemStack ingotElectrum;					//00334
	public static ItemStack ingotWardenicMetal;				//00335

	//00350-00374 COMPLEX ALLOY INGOTS/COMPOUND GEMS
	public static ItemStack ingotThaumicBronze;				//00350
	public static ItemStack ingotOsLu;						//00351
	public static ItemStack gemFluonicPyroptase;			//00352

	//00375-00399 SPECIAL ALLOY INGOTS
	public static ItemStack ingotThaumicElectrum;			//00375
	public static ItemStack ingotThaumicTinkersBronze;		//00376
	public static ItemStack ingotSteel;						//00377
	public static ItemStack ingotWardenicSteel;				//00378
	public static ItemStack ingotWardenicTinkersBronze;		//00379
	public static ItemStack ingotVoidbrass;					//00380
	public static ItemStack ingotVoidsteel;					//00381
	public static ItemStack ingotVoidtungsten;				//00382

	//00400-00424 BASE METAL INGOTS/NATURAL GEMS
	public static ItemStack nuggetCopper;					//00400
	public static ItemStack nuggetZinc;						//00401
	public static ItemStack nuggetTin;						//00402
	public static ItemStack nuggetNickel;					//00403
	public static ItemStack nuggetSilver;					//00404
	public static ItemStack nuggetLead;						//00405
	public static ItemStack nuggetLanthanides;				//00406
	public static ItemStack nuggetTungsten;					//00407
	public static ItemStack nuggetIridium;					//00408
	public static ItemStack nuggetBismuth;					//00409
	public static ItemStack nuggetArsenicalBronze;			//00410
	public static ItemStack nuggetAntimonialBronze;			//00411
	public static ItemStack shardPyrope;					//00412
	public static ItemStack shardDioptase;					//00413
	public static ItemStack shardFluonicSapphire;			//00414
	public static ItemStack nuggetOsmium;					//00415
	public static ItemStack nuggetNeodymium;				//00416
	public static ItemStack nuggetLutetium;					//00417
	public static ItemStack nuggetPalladium;				//00418
	public static ItemStack nuggetIridosmium;				//00419
	public static ItemStack nuggetAluminium;				//00420
	public static ItemStack nuggetXenotimeJunk;				//00421

	//00425-00449 SIMPLE ALLOY INGOTS
	public static ItemStack nuggetBrass;					//00425
	public static ItemStack nuggetBronze;					//00426
	public static ItemStack nuggetBismuthBronze;			//00427
	public static ItemStack nuggetMithril;					//00428
	public static ItemStack nuggetAluminiumBronze;			//00429
	public static ItemStack nuggetCupronickel;				//00430
	public static ItemStack nuggetTinkersBronze;			//00431
	public static ItemStack nuggetConstantan;				//00432
	public static ItemStack nuggetInvar;					//00433
	public static ItemStack nuggetElectrum;					//00434
	public static ItemStack nuggetWardenicMetal;			//00435

	//00450-00474 COMPLEX ALLOY NUGGETS/COMPOUND GEM SHARDS
	public static ItemStack nuggetThaumicBronze;			//00450
	public static ItemStack nuggetOsLu;						//00451
	public static ItemStack shardFluonicPyroptase;			//00452

	//00475-00499 SPECIAL ALLOY NUGGETS
	public static ItemStack nuggetThaumicElectrum;			//00475
	public static ItemStack nuggetThaumicTinkersBronze;		//00476
	public static ItemStack nuggetSteel;					//00477
	public static ItemStack nuggetWardenicSteel;			//00478
	public static ItemStack nuggetWardenicTinkersBronze;	//00479
	public static ItemStack nuggetVoidbrass;				//00480
	public static ItemStack nuggetVoidsteel;				//00481
	public static ItemStack nuggetVoidtungsten;				//00482

	//00400-00424 BASE METAL DUSTS/NATURAL GEM DUSTS
	public static ItemStack dustCopper;						//00500
	public static ItemStack dustZinc;						//00501
	public static ItemStack dustTin;						//00502
	public static ItemStack dustNickel;						//00503
	public static ItemStack dustSilver;						//00504
	public static ItemStack dustLead;						//00505
	public static ItemStack dustLanthanides;				//00506
	public static ItemStack dustTungsten;					//00507
	public static ItemStack dustIridium;					//00508
	public static ItemStack dustBismuth;					//00509
	public static ItemStack dustArsenicalBronze;			//00510
	public static ItemStack dustAntimonialBronze;			//00511
	public static ItemStack dustPyrope;						//00512
	public static ItemStack dustDioptase;					//00513
	public static ItemStack dustFluonicSapphire;			//00514
	public static ItemStack dustOsmium;						//00515
	public static ItemStack dustNeodymium;					//00516
	public static ItemStack dustLutetium;					//00517
	public static ItemStack dustPalladium;					//00518
	public static ItemStack dustIridosmium;					//00519
	public static ItemStack dustAluminium;					//00520
	public static ItemStack dustXenotimeJunk;				//00521

	//00425-00449 SIMPLE ALLOY DUSTS
	public static ItemStack dustBrass;						//00525
	public static ItemStack dustBronze;						//00526
	public static ItemStack dustBismuthBronze;				//00527
	public static ItemStack dustMithril;					//00528
	public static ItemStack dustAluminiumBronze;			//00529
	public static ItemStack dustCupronickel;				//00530
	public static ItemStack dustTinkersBronze;				//00531
	public static ItemStack dustConstantan;					//00532
	public static ItemStack dustInvar;						//00533
	public static ItemStack dustElectrum;					//00534
	public static ItemStack dustWardenicMetal;				//00535

	//00450-00474 COMPLEX ALLOY DUSTS/COMPOUND GEM DUSTS
	public static ItemStack dustThaumicBronze;				//00550
	public static ItemStack dustOsLu;						//00551
	public static ItemStack dustFluonicPyroptase;			//00552

	//00475-00499 SPECIAL ALLOY NUGGETS
	public static ItemStack dustThaumicElectrum;			//00575
	public static ItemStack dustThaumicTinkersBronze;		//00551
	public static ItemStack dustSteel;						//00576
	public static ItemStack dustWardenicSteel;				//00577
	public static ItemStack dustWardenicTinkersBronze;		//00578
	public static ItemStack dustVoidbrass;					//00579
	public static ItemStack dustVoidsteel;					//00580
	public static ItemStack dustVoidtungsten;				//00581

	//00700-00724 RAW SIMPLE ALLOY INGOTS
	public static ItemStack rawBrass;						//00700
	public static ItemStack rawBronze;						//00701
	public static ItemStack rawBismuthBronze;				//00702
	public static ItemStack rawMithril;						//00703
	public static ItemStack rawAluminiumBronze;				//00704
	public static ItemStack rawCupronickel;					//00705
	public static ItemStack rawTinkersBronze;				//00706
	public static ItemStack rawConstantan;					//00707
	public static ItemStack rawInvar;						//00708
	public static ItemStack rawElectrum;					//00709
	public static ItemStack rawWardenicMetal;				//00710

	//00725-00749 RAW COMPLEX ALLOY INGOTS/COMPOUND GEM DUSTS
	public static ItemStack rawThaumicBronze;				//00725
	public static ItemStack rawOsLu;						//00726
	public static ItemStack blendFluonicPyrotase;			//00727

	//00750-00774 COATED COMPLEX ALLOY INGOTS
	public static ItemStack coatedThaumicBronze;			//00750
	public static ItemStack coatedOsLu;						//00751

	//00775-00799 FIRED COMPLEX ALLOY INGOTS
	public static ItemStack firedThaumicBronze;				//00775
	public static ItemStack firedOsLu;						//00776

	//00800-00849 OTHER DUSTS
	public static ItemStack dustAer;						//00800
	public static ItemStack dustIgnis;						//00801
	public static ItemStack dustAqua;						//00802
	public static ItemStack dustTerra;						//00803
	public static ItemStack dustOrdo;						//00804
	public static ItemStack dustPerditio;					//00805
	public static ItemStack dustIron;						//00806
	public static ItemStack dustGold;						//00807
	public static ItemStack dustThaumium;					//00808
	public static ItemStack dustVoidmetal;					//00809
	public static ItemStack dustArsenic;					//00810
	public static ItemStack dustAntimony;					//00811
	//Salis Mundus

	public static ItemStack dustWardenicBinder;				//00830

	//00850-00899 OTHER TINY DUSTS
	public static ItemStack tinySalisMundus;				//00862

	public static ItemStack tinyWardenicBinder;				//00880

	//00900-00924 ORE CLUSTERS
	public static ItemStack clusterZinc;					//00901
	public static ItemStack clusterNickel;					//00903
	public static ItemStack clusterXenotime;				//00906
	public static ItemStack clusterTungsten;				//00907
	public static ItemStack clusterIridosmium;				//00908
	public static ItemStack clusterBismuth;					//00909
	public static ItemStack clusterTennantite;				//00910
	public static ItemStack clusterTetrahedrite;			//00911

	//00925-00949 CRYSTAL SEEDS


	//00950-00974 CERAMIC SLAGS
	public static ItemStack ceramicSlag;					//00950
	public static ItemStack thaumicSlag;					//00951

	//01000-01199 WARDENIC ARSENAL
	public static ItemStack excubituraPetal;				//01000

	public static ItemStack excubituraPaste;				//01020
	public static ItemStack excubituraFabric;				//01021
	public static ItemStack itemWardencloth;				//01022

	public static ItemStack excubituraOilUnproc;			//01040
	public static ItemStack excubituraOil;					//01041
	public static ItemStack wardenicBronzeChain;			//01042
	public static ItemStack primalBronzeChain;				//01043
	public static ItemStack wardenicBronzePlate;			//01044

	public static ItemStack excubituraOilPure;				//01060
	public static ItemStack wardenicSteelChain;				//01061
	public static ItemStack oiledSteelChain;				//01062
	public static ItemStack wardenicSteelPlate;				//01063
	public static ItemStack detailedSteelPlate;				//01064
	public static ItemStack runicSteelPlate;				//01065
	public static ItemStack consecratedSteelPlate;			//01066

	public static ItemStack wardenicQuartz;					//01080
	public static ItemStack wardenicCrystal;				//01081
	public static ItemStack wardenicQuartzInf;				//01082
	public static ItemStack dustWardenicQuartz;				//01083
	public static ItemStack dustWardenicCrystal;			//01084
	public static ItemStack rawWardenicComposite;			//01085
	public static ItemStack ingotWardenicComposite;			//01086
	public static ItemStack wardenicCompositePlate;			//01087
	public static ItemStack fittedCompositePlate;			//01088
	public static ItemStack detailedCompositePlate;			//01089
	public static ItemStack runicCompositePlate;			//01090
	public static ItemStack consecratedCompositePlate;		//01091
	public static ItemStack primalCompositePlate;			//01092

	public static ItemStack wardenicCrystalAwakened;		//01100

	public static ItemStack wardenicHardener;				//01160

	//01200-01399 MAGNEOTURGY
	//01400-01599 ELDTRICH
	// TENTATIVE ITEMS
	//01600-01799 THAUMIC ASCENSION
	//01800-01999 RUNIC MECHANICS
	//02000-02199 TRANSMUTIVE ENGINEERING
	//02200-02399 THAUMIC DYNAMICS
	//02400-02599 CHRONOTURGY
	//02600-02799 ASTROTURGY/MISC
	//02800-02999 INFUSION SYSTEM
	//03000-03099 WAND CAPS
	//03100-03199 WAND CORES
	//03200-03299 STAFF CORES

	//public static ItemStack wardenJournal1;				//05001 //TODO: Wardenic Backstory

	//30000+ TEMPORARY ITEMS
	public static ItemStack aluDenseTemp;					//30000


	public static ItemStack alum;							//00015


	public static ItemStack wardenAmulet;					//00000
	public static ItemStack loveRing;						//00001

	/** ARMORS * */
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

	/** OTHER EQUIPMENT * */
	public static ItemFocusBasic itemFocusPurity;

	/** RECIPES * */
	public static ShapelessOreRecipe recipeBrass;
	public static ShapelessOreRecipe recipeBronze;
	public static ShapelessOreRecipe[] recipeBismuthBronze;
	public static ShapelessOreRecipe recipeMithril;
	public static ShapelessOreRecipe recipeAlBronze;
	public static ShapelessOreRecipe recipeCupronickel;
	public static ShapelessOreRecipe recipeTinkersBronze;
	public static ShapelessOreRecipe recipeConstantan;
	public static ShapelessOreRecipe recipeInvar;
	public static ShapelessOreRecipe recipeElectrum;

	public static ShapelessOreRecipe recDustBrass;
	public static ShapelessOreRecipe recDustBronze;
	public static ShapelessOreRecipe[] recDustBismuthBronze;
	public static ShapelessOreRecipe recDustMithril;
	public static ShapelessOreRecipe recDustAlBronze;
	public static ShapelessOreRecipe recDustCupronickel;
	public static ShapelessOreRecipe recDustTinkersBronze;
	public static ShapelessOreRecipe recDustConstantan;
	public static ShapelessOreRecipe recDustInvar;
	public static ShapelessOreRecipe recDustElectrum;

	//public static ShapelessOreRecipe recipeOsLu;
	//public static ShapelessOreRecipe recCoatOsLu;

	/*public static ShapelessOreRecipe recTinyBrass;
	public static ShapelessOreRecipe recTinyBronze;
	public static ShapelessOreRecipe recTinyMithril;
	public static ShapelessOreRecipe recTinyTinkersBronze;
	public static ShapelessOreRecipe recTinyElectrum;
	public static ShapelessOreRecipe recTinyOsLu;*/

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

	public static InfusionRecipe recipeThaumicTBronze;

	public static CrucibleRecipe recipeThaumicElectrum;

	public static ShapedArcaneRecipe recipeThaumicBronzeChain;

	public static ShapedArcaneRecipe recipeBronzeChainHelmet;
	public static ShapedArcaneRecipe recipeBronzeChainmail;
	public static ShapedArcaneRecipe recipeBronzeChainGreaves;
	public static ShapedArcaneRecipe recipeBronzeChainBoots;

	//public static ShapedArcaneRecipe recipeRunicInfuser;
	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeArcaneSingularity;
	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeStableSingularity;

	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeEnchSilverwood;
	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeConsSilverwood;

	//public static ShapedArcaneRecipe recipeDarkRunicInfuser;

	//public static InfusionRecipe recipeAlchemicalInfuser;

	//public static InfusionRecipe recipeDarkAlchemicalInfuser;

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
	public static ShapelessOreRecipe recipeBinderTiny;
	public static ShapelessOreRecipe recipeBinderCombine;

	public static InfusionRecipe recipeWardenBronze;

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

	public static ResearchItem researchAlloys;
	public static String keyAlloys = "ALLOYS_TRv";

	public static ResearchItem researchCotton;
	public static String keyCotton = "ENCHCOTTON";

	public static ResearchItem researchPrimalRobes;
	public static String keyRobesPrimal = "ROBES_PRIMAL";

	public static ResearchItem researchAniPiston;
	public static String keyAniPiston = "ANIMATED_PISTON";

	public static ResearchItem researchThaumicBronze;
	public static String keyThaumicBronze = "THAUMIC_BRONZE";

	public static ResearchItem researchThaumicTBronze;
	public static String keyThaumicTBronze = "THAUMIC_TINKERS_BRONZE";

	public static ResearchItem researchThaumicElectrum;
	public static String keyThaumicElectrum = "THAUMIC_ELECTRUM";

	public static ResearchItem researchBronzeChain;
	public static String keyBronzeChain = "TBRONZE_CHAIN";

	public static ResearchItem researchArmorBronzeChain;
	public static String keyArmorBronzeChain = "ARMOR_TBRONZE";

	public static ResearchItem researchRunicInfuser;
	public static String keyRunicInfuser = "RUNIC_INFUSER";

	public static ResearchItem researchEnchSilverwood;
	public static String keyEnchSilverwood = "ENCH_SILVERWOOD";





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

	public static ResearchItem researchWardenBronze;
	public static String keyWardenBronze = "WARDEN_BRONZE";

	public static ResearchItem researchWardenComposite;
	public static String keyWardenComposite = "WARDEN_COMPOSITE";

	public static ResearchItem researchWardenCompositePlate;
	public static String keyWardenCompositePlate = "WARDEN_COMPOSITE_PLATE";

	public static ResearchItem researchWardenCompositeFitting;
	public static String keyWardenCompositeFitting = "WARDEN_COMPOSITE_FITTING";

	public static ResearchItem researchArmorWardenComposite;
	public static String keyArmorWardenComposite = "ARMOR_WARDEN_COMPOSITE";


	/** BLOCK NAMES * */
	public static final String[] PLANT_NAMES = {"excubitura", "wildCotton", "thistle"};

	public static final String[] DECOR_STONE_NAMES = {"obsidianWardenic", "stoneEldritch", "wardenicQuartz", "wardenicQuartzChiseled", "wardenicQuartzPillar", "wardenicQuartzPillar", "wardenicQuartzPillar",};

	public static final String[] ORE_NAMES = {"oreChalcocite", "oreSphalerite", "oreCassiterite", "oreMillerite", "oreNativeSilver", "oreGalena", "oreXenotime", "oreWolframite", "oreIridosmium", "oreBismuthinite", "oreTennantite", "oreTetrahedrite", "orePyrope", "oreDioptase", "oreFluonicSapphire"};

	//TODO: Update
	public static final String[] STORAGE_MAIN_NAMES = {"blockCopper", "blockZinc", "blockTin", "blockSilver", "blockBrass", "blockBronze", "blockThaumicBronze", "blockSteel", "blockVoidbrass", "blockVoidsteel", "blockElectrum", "blockWardenicSteel", "blockThaumicElectrum", "blockVoid", "blockWardenicMetal", "blockWardenicComposite"};

	public static final String[] FALSE_AIR_NAMES = {"witor"};

	public static final String[] SLAB_STONE_NAMES = {"obsidianWardenic", "stoneEldritch", "wardenicQuartz"};

	public static final String matPrimal = "PRIMAL";
	public static final String matBronzeChain = "BRONZE_CHAIN";
	public static final String matWardencloth = "WARDENCLOTH";
	public static final String matWardenicChain = "WARDENIC_CHAIN";
	public static final String matWardenicSteel = "WARDENIC_STEEL";
	public static final String matWardenicComposite = "WARDENIC_COMPOSITE";

	/** NBT KEYS * */
	public static final String DURABILITY = "DURABILITY";
	public static final String REVEALING = "REVEALING";
	public static final String BROKEN = "BROKEN";
	public static final String VISMODIFIER = "VISDISCOUNT";

	/** CLIENT-SIDE * */
	public static int renderDecorStoneID;
	//public static int wardedChestRenderID = -1;
}
