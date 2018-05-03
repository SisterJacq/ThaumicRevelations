package mortvana.thaumrev.library;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
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
import mortvana.melteddashboard.world.*;

import mortvana.thaumrev.util.item.ItemArmorInfusableBase;

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
	public static final String RESEARCH_KEY_MAIN = "TRvMAIN";
	public static final String RESEARCH_KEY_METAL = "TRvMETAL";

	/** ASPECTS **/
	public static final Aspect WARDEN = new Aspect("excubitor", 0x3CD4FC, new Aspect[] {ELDRITCH, DEATH}, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/exubitor.png"), 771);
	public static final Aspect MAGNET = new Aspect("magnes", 0x515970, new Aspect[] {METAL, ENERGY}, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/magnes.png"), 771);
	public static final Aspect FLUX = new Aspect("fluxus", 0xAD0200, new Aspect[] {MAGNET, MECHANISM}, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/fluxus.png"), 771);
	public static final Aspect REVELATIONS = new Aspect("patefactio", 0x3971AD, new Aspect[] {TRAVEL, MIND}, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/revelatiofez.png"), 771);

	/** ENCHANTMENTS **/
	//public static Enchantment enchantStabilizing;

	/** CREATIVE TABS **/
	public static CreativeTabs thaumicRevelationsTab;

	/** MATERIALS **/
	public static Material materialFalseAir = new MaterialFalseAir();

	/** BLOCKS **/
	public static Block blockThaumicPlant;
	public static Block blockOre;
	//public static Block blockFakeAir;
	public static Block blockWoodDecor;
	public static Block blockStoneDecor;
	//public static Block blockMetalDecor;
	public static Block blockStorageOre;
	public static Block blockStorageAlloy1;
	//public static Block blockStorageAlloy2;
	public static Block blockStorageSpecial1;
	public static Block blockStorageSpecial2;
	public static Block blockStorageMisc;
	//public static Block blockTransparent;
	//public static Block blockWoodDevice;
	//public static Block blockStoneDevice;
	//public static Block blockMetalDevice;
	public static Block blockStoneSlab;
	public static Block blockStoneSlabDouble;
	public static Block blockWardenicQuartzStairs;

	/** ITEMS **/
	public static FluxGearItemInteractive generalItem;
	public static FluxGearItemInteractive metalItem;

	public static FluxGearItemBauble thaumicBauble;

	/** ITEMSTACKS **/
	public static ItemStack potato = new ItemStack(Items.potato); //Used for debugging, placeholding, and such

	public static ItemStack excubituraRose;
	public static ItemStack wildCotton;
	public static ItemStack wildThistle;
	public static ItemStack shiverpearl;
	public static ItemStack stormpearl;
	public static ItemStack stonepearl;
	//public static ItemStack blazereed;
	//public static ItemStack blizzreed;
	//public static ItemStack blitzreed;
	//public static ItemStack basalzreed;

	public static ItemStack wardenicObsidian;
	public static ItemStack eldritchStone;
	public static ItemStack blockWardenicQuartz;
	public static ItemStack blockWardenicQuartzChiseled;
	public static ItemStack blockWardenicQuartzPillar;
	public static ItemStack thaumicStone;
	public static ItemStack infernalBlastBrick;
	public static ItemStack shadowforgeBrick;

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
	public static ItemStack blockNickel;
	public static ItemStack blockSilver;
	public static ItemStack blockLead;
	public static ItemStack blockLanthanides;
	public static ItemStack blockTungsten;
	public static ItemStack blockIridium;
	public static ItemStack blockBismuth;
	public static ItemStack blockAsBronze;
	public static ItemStack blockSbBronze;
	public static ItemStack blockPyrope;
	public static ItemStack blockDioptase;
	public static ItemStack blockFluonicSapphire;
	public static ItemStack blockOsmium;

	public static ItemStack blockBrass;
	public static ItemStack blockBronze;
	public static ItemStack blockBiBronze;
	public static ItemStack blockMithril;
	public static ItemStack blockAlBronze;
	public static ItemStack blockCupronickel;
	public static ItemStack blockRiftishBronze;
	public static ItemStack blockConstantan;
	public static ItemStack blockInvar;
	public static ItemStack blockElectrum;
	public static ItemStack blockWardenicMetal;
	public static ItemStack blockDullRedsolder;
	public static ItemStack blockRedsolder;


	public static ItemStack blockThaumicElectrum;
	public static ItemStack blockThaumicRiftishBronze;
	public static ItemStack blockSteel;
	public static ItemStack blockVoidbrass;
	public static ItemStack blockVoidsteel;
	public static ItemStack blockVoidtungsten;
	public static ItemStack blockWardenicBronze;
	public static ItemStack blockWardenicSteel;
	public static ItemStack blockWardenicRiftishBronze;
	public static ItemStack blockWardenicCrystal;
	public static ItemStack blockWardenicCrystalActivated;
	public static ItemStack blockWardenicComposite;
	public static ItemStack blockArcaneRedsolder;
	public static ItemStack blockRedbronze;
	public static ItemStack blockHardenedRedbronze;
	public static ItemStack blockFluxsteel;

	public static ItemStack blockFluxedTungsten;


	/*public static ItemStack blockVoidmetal;
	public static ItemStack blockThaumicBronze;




	*/

	public static ItemStack slabWardenicObsidian;
	public static ItemStack slabEldritch;
	public static ItemStack slabWardenicQuartz;

	public static ItemStack stairsWardenicQuartz;

	//GENERAL ITEM
	//00000-00199 GENERAL ITEMS
	public static ItemStack cotton;							//00000
	public static ItemStack cottonFiber;					//00001
	public static ItemStack cottonFabric;					//00002
	public static ItemStack cottonTreated;					//00003
	public static ItemStack cottonEnchanted;				//00004
	public static ItemStack thistleLeaf;					//00005
	public static ItemStack thistleFlower;					//00006

	public static ItemStack arcaneSingularity;				//00010
	public static ItemStack stabilizedSingularity;			//00011
	public static ItemStack animatedPiston;					//00012

	public static ItemStack aspectOrbReceptor;				//00020

	public static ItemStack eldritchCog;					//00030
	public static ItemStack eldritchKeystone;				//00031

	public static ItemStack greatwoodShaft;					//00040
	public static ItemStack greatwoodEnchanted;				//00041
	public static ItemStack greatwoodShaftEnchanted;		//00042

	public static ItemStack silverwoodDensified;			//00050
	public static ItemStack silverwoodEnchanted;			//00051
	public static ItemStack silverwoodEnchantedDensified;	//00052
	public static ItemStack silverwoodConsecrated;			//00053
	public static ItemStack silverwoodShaftEnchanted;		//00054
	public static ItemStack silverwoodShaftConsecrated;		//00055

	public static ItemStack thaumicBronzeChain;				//00060



	//public static ItemStack aluminiumHydroxide;				//00016

	//METAL ITEM
	//00000-00031 ELEMENTAL METAL INGOTS
	public static ItemStack ingotCopper;					//05000
	public static ItemStack ingotZinc;						//05001
	public static ItemStack ingotTin;						//05002
	public static ItemStack ingotNickel;					//05003
	public static ItemStack ingotSilver;					//05004
	public static ItemStack ingotLead;						//05005
	public static ItemStack ingotLutetium;					//05006
	public static ItemStack ingotTungsten;					//05007
	public static ItemStack ingotIridium;					//05008
	public static ItemStack ingotBismuth;					//05009
	public static ItemStack ingotArsenic;					//05010
	public static ItemStack ingotAntimony;					//05011
	public static ItemStack ingotNeodymium;					//05012
	public static ItemStack ingotOsmium;					//05013
	public static ItemStack ingotPalladium;					//05014
	public static ItemStack ingotAluminium;					//05015

	//00032-00063 SIMPLE ALLOY INGOTS
	public static ItemStack ingotBrass;						//05032
	public static ItemStack ingotBronze;					//05033
	public static ItemStack ingotArsenicalBronze;			//05034
	public static ItemStack ingotAntimonialBronze;			//05035
	public static ItemStack ingotBismuthBronze;				//05036
	public static ItemStack ingotMithril;					//05037
	public static ItemStack ingotAluminiumBronze;			//05038
	public static ItemStack ingotCupronickel;				//05039
	public static ItemStack ingotRiftishBronze;				//05040
	public static ItemStack ingotConstantan;				//05041
	public static ItemStack ingotInvar;						//05042
	public static ItemStack ingotElectrum;					//05043
	public static ItemStack ingotWardenicMetal;				//05044
	public static ItemStack ingotDullRedsolder;				//05045
	public static ItemStack ingotRedsolder;					//05046

	//00064-00079 SPECIAL ALLOY INGOTS
	public static ItemStack ingotThaumicElectrum;			//05064
	public static ItemStack ingotThaumicRiftishBronze;		//05065
	public static ItemStack ingotSteel;						//05066
	public static ItemStack ingotVoidbrass;					//05067
	public static ItemStack ingotVoidsteel;					//05068
	public static ItemStack ingotVoidtungsten;				//05069
	public static ItemStack ingotVoidcupronickel;			//05070

	//00080-00095 EQUIPMENT ALLOY INGOTS
	public static ItemStack ingotWardenicBronze;			//05080
	public static ItemStack ingotWardenicSteel;				//05081
	public static ItemStack ingotWardenicRiftishBronze;		//05082
	public static ItemStack ingotWardenicComposite;			//05083
	public static ItemStack ingotRedsolderArcane;			//05084
	public static ItemStack ingotRedbronze;					//05085
	public static ItemStack ingotRedbronzeHardened;			//05086
	public static ItemStack ingotFluxsteel;					//05087
	public static ItemStack ingotFluxedTungsten;			//05088
	public static ItemStack ingotMagneoturgicComposite;		//05089
	public static ItemStack ingotFluxedComposite;			//05090
	public static ItemStack ingotResonantFluxedComposite;	//05091
	public static ItemStack ingotEmpoweredVoidbrass;		//05092
	public static ItemStack ingotCrimsonThaumium;			//05093
	public static ItemStack ingotOccultVoidtungsten;		//05094

	//00096-00111 GEMS
	public static ItemStack gemPyrope;						//05096
	public static ItemStack gemDioptase;					//05097
	public static ItemStack gemFluonicSapphire;				//05098
	public static ItemStack gemFluonicPyroptase;			//05099
	public static ItemStack gemWardenicCrystal;				//05100
	public static ItemStack gemWardenicCrystalActivated;	//05101

	public static ItemStack gemWardenicQuartz;				//05108
	public static ItemStack gemRedquartz;					//05109
	
	//00112-00127 OTHER METAL INGOTS
	public static ItemStack ingotLanthanides;				//05112
	public static ItemStack ingotXenotimeJunk;				//05113
	public static ItemStack ingotIridosmium;				//05114

	public static ItemStack ingotThaumicBronze;				//05120
	public static ItemStack ingotOsLu;						//05121

	//00200-00231 ELEMENTAL METAL NUGGETS
	public static ItemStack nuggetCopper;					//05200
	public static ItemStack nuggetZinc;						//05201
	public static ItemStack nuggetTin;						//05202
	public static ItemStack nuggetNickel;					//05203
	public static ItemStack nuggetSilver;					//05204
	public static ItemStack nuggetLead;						//05205
	public static ItemStack nuggetLutetium;					//05206
	public static ItemStack nuggetTungsten;					//05207
	public static ItemStack nuggetIridium;					//05208
	public static ItemStack nuggetBismuth;					//05209
	public static ItemStack nuggetArsenic;					//05210
	public static ItemStack nuggetAntimony;					//05211
	public static ItemStack nuggetNeodymium;				//05212
	public static ItemStack nuggetOsmium;					//05213
	public static ItemStack nuggetPalladium;				//05214
	public static ItemStack nuggetAluminium;				//05215

	//00232-00263 SIMPLE ALLOY NUGGETS
	public static ItemStack nuggetBrass;					//05232
	public static ItemStack nuggetBronze;					//05233
	public static ItemStack nuggetArsenicalBronze;			//05234
	public static ItemStack nuggetAntimonialBronze;			//05235
	public static ItemStack nuggetBismuthBronze;			//05236
	public static ItemStack nuggetMithril;					//05237
	public static ItemStack nuggetAluminiumBronze;			//05238
	public static ItemStack nuggetCupronickel;				//05239
	public static ItemStack nuggetRiftishBronze;			//05240
	public static ItemStack nuggetConstantan;				//05241
	public static ItemStack nuggetInvar;					//05242
	public static ItemStack nuggetElectrum;					//05243
	public static ItemStack nuggetWardenicMetal;			//05244
	public static ItemStack nuggetDullRedsolder;			//05245
	public static ItemStack nuggetRedsolder;				//05246

	//00264-00279 SPECIAL ALLOY NUGGETS
	public static ItemStack nuggetThaumicElectrum;			//05264
	public static ItemStack nuggetThaumicRiftishBronze;		//05265
	public static ItemStack nuggetSteel;					//05266
	public static ItemStack nuggetVoidbrass;				//05267
	public static ItemStack nuggetVoidsteel;				//05268
	public static ItemStack nuggetVoidtungsten;				//05269
	public static ItemStack nuggetVoidcupronickel;			//05270

	//00280-00295 EQUIPMENT ALLOY NUGGETS
	public static ItemStack nuggetWardenicBronze;			//05280
	public static ItemStack nuggetWardenicSteel;			//05281
	public static ItemStack nuggetWardenicRiftishBronze;	//05282
	public static ItemStack nuggetWardenicComposite;		//05283
	public static ItemStack nuggetRedsolderArcane;			//05284
	public static ItemStack nuggetRedbronze;				//05285
	public static ItemStack nuggetRedbronzeHardened;		//05286
	public static ItemStack nuggetFluxsteel;				//05287
	public static ItemStack nuggetFluxedTungsten;			//05288
	public static ItemStack nuggetMagneoturgicComposite;	//05289
	public static ItemStack nuggetFluxedComposite;			//05290
	public static ItemStack nuggetResonantFluxedComposite;	//05291
	public static ItemStack nuggetEmpoweredVoidbrass;		//05292
	public static ItemStack nuggetCrimsonThaumium;			//05293
	public static ItemStack nuggetOccultVoidtungsten;		//05294

	//00296-00311 GEM SHARDS
	public static ItemStack shardPyrope;					//05296
	public static ItemStack shardDioptase;					//05297
	public static ItemStack shardFluonicSapphire;			//05298
	public static ItemStack shardFluonicPyroptase;			//05299
	public static ItemStack shardWardenicCrystal;			//05300
	public static ItemStack shardWardenicCrystalActivated;	//05301

	public static ItemStack shardWardenicQuartz;			//05308
	public static ItemStack shardRedquartz;					//05309

	//00312-00327 OTHER METAL NUGGETS
	public static ItemStack nuggetLanthanides;				//05312
	public static ItemStack nuggetXenotimeJunk;				//05313
	public static ItemStack nuggetIridosmium;				//05314

	public static ItemStack nuggetThaumicBronze;			//05320
	public static ItemStack nuggetOsLu;						//05321

	//00400-00431 ELEMENTAL METAL DUSTS
	public static ItemStack dustCopper;						//05400
	public static ItemStack dustZinc;						//05401
	public static ItemStack dustTin;						//05402
	public static ItemStack dustNickel;						//05403
	public static ItemStack dustSilver;						//05404
	public static ItemStack dustLead;						//05405
	public static ItemStack dustLutetium;					//05406
	public static ItemStack dustTungsten;					//05407
	public static ItemStack dustIridium;					//05408
	public static ItemStack dustBismuth;					//05409
	public static ItemStack dustArsenic;					//05410
	public static ItemStack dustAntimony;					//05411
	public static ItemStack dustNeodymium;					//05412
	public static ItemStack dustOsmium;						//05413
	public static ItemStack dustPalladium;					//05414
	public static ItemStack dustAluminium;					//05415

	//00432-00463 SIMPLE ALLOY DUSTS
	public static ItemStack dustBrass;						//05432
	public static ItemStack dustBronze;						//05433
	public static ItemStack dustArsenicalBronze;			//05434
	public static ItemStack dustAntimonialBronze;			//05435
	public static ItemStack dustBismuthBronze;				//05436
	public static ItemStack dustMithril;					//05437
	public static ItemStack dustAluminiumBronze;			//05438
	public static ItemStack dustCupronickel;				//05439
	public static ItemStack dustRiftishBronze;				//05440
	public static ItemStack dustConstantan;					//05441
	public static ItemStack dustInvar;						//05442
	public static ItemStack dustElectrum;					//05443
	public static ItemStack dustWardenicMetal;				//05444
	public static ItemStack dustDullRedsolder;				//05445
	public static ItemStack dustRedsolder;					//05446

	//00464-00479 SPECIAL ALLOY DUSTS
	public static ItemStack dustThaumicElectrum;			//05464
	public static ItemStack dustThaumicRiftishBronze;		//05465
	public static ItemStack dustSteel;						//05466
	public static ItemStack dustVoidbrass;					//05467
	public static ItemStack dustVoidsteel;					//05468
	public static ItemStack dustVoidtungsten;				//05469
	public static ItemStack dustVoidcupronickel;			//05470

	//00480-00495 EQUIPMENT ALLOY DUSTS
	public static ItemStack dustWardenicBronze;				//05480
	public static ItemStack dustWardenicSteel;				//05481
	public static ItemStack dustWardenicRiftishBronze;		//05482
	public static ItemStack dustWardenicComposite;			//05483
	public static ItemStack dustRedsolderArcane;			//05484
	public static ItemStack dustRedbronze;					//05485
	public static ItemStack dustRedbronzeHardened;			//05486
	public static ItemStack dustFluxsteel;					//05487
	public static ItemStack dustFluxedTungsten;				//05488
	public static ItemStack dustMagneoturgicComposite;		//05489
	public static ItemStack dustFluxedComposite;			//05490
	public static ItemStack dustResonantFluxedComposite;	//05491
	public static ItemStack dustEmpoweredVoidbrass;			//05492
	public static ItemStack dustCrimsonThaumium;			//05493
	public static ItemStack dustOccultVoidtungsten;			//05494

	//00496-00511 GEM DUSTS
	public static ItemStack dustPyrope;						//05496
	public static ItemStack dustDioptase;					//05497
	public static ItemStack dustFluonicSapphire;			//05498
	public static ItemStack dustFluonicPyroptase;			//05499
	public static ItemStack dustWardenicCrystal;			//05500
	public static ItemStack dustWardenicCrystalActivated;	//05501

	public static ItemStack dustWardenicQuartz;				//05508
	public static ItemStack dustRedquartz;					//05509

	//00512-00527 OTHER METAL DUSTS
	public static ItemStack dustLanthanides;				//05512
	public static ItemStack dustXenotimeJunk;				//05513
	public static ItemStack dustIridosmium;					//05514

	public static ItemStack dustThaumicBronze;				//05520
	public static ItemStack dustOsLu;						//05521

	//00600-00631 ELEMENTAL METAL TINY DUSTS
	public static ItemStack tinyCopper;						//05600
	public static ItemStack tinyZinc;						//05601
	public static ItemStack tinyTin;						//05602
	public static ItemStack tinyNickel;						//05603
	public static ItemStack tinySilver;						//05604
	public static ItemStack tinyLead;						//05605
	public static ItemStack tinyLutetium;					//05606
	public static ItemStack tinyTungsten;					//05607
	public static ItemStack tinyIridium;					//05608
	public static ItemStack tinyBismuth;					//05609
	public static ItemStack tinyArsenic;					//05610
	public static ItemStack tinyAntimony;					//05611
	public static ItemStack tinyNeodymium;					//05612
	public static ItemStack tinyOsmium;						//05613
	public static ItemStack tinyPalladium;					//05614
	public static ItemStack tinyAluminium;					//05615

	//00632-00663 SIMPLE ALLOY TINY DUSTS
	public static ItemStack tinyBrass;						//05632
	public static ItemStack tinyBronze;						//05633
	public static ItemStack tinyArsenicalBronze;			//05634
	public static ItemStack tinyAntimonialBronze;			//05635
	public static ItemStack tinyBismuthBronze;				//05636
	public static ItemStack tinyMithril;					//05637
	public static ItemStack tinyAluminiumBronze;			//05638
	public static ItemStack tinyCupronickel;				//05639
	public static ItemStack tinyRiftishBronze;				//05640
	public static ItemStack tinyConstantan;					//05641
	public static ItemStack tinyInvar;						//05642
	public static ItemStack tinyElectrum;					//05643
	public static ItemStack tinyWardenicMetal;				//05644
	public static ItemStack tinyDullRedsolder;				//05645
	public static ItemStack tinyRedsolder;					//05646

	//00664-00679 SPECIAL ALLOY TINY DUSTS
	public static ItemStack tinyThaumicElectrum;			//05664
	public static ItemStack tinyThaumicRiftishBronze;		//05665
	public static ItemStack tinySteel;						//05666
	public static ItemStack tinyVoidbrass;					//05667
	public static ItemStack tinyVoidsteel;					//05668
	public static ItemStack tinyVoidtungsten;				//05669
	public static ItemStack tinyVoidcupronickel;			//05670

	//00680-00695 EQUIPMENT ALLOY TINY DUSTS
	public static ItemStack tinyWardenicBronze;				//05680
	public static ItemStack tinyWardenicSteel;				//05681
	public static ItemStack tinyWardenicRiftishBronze;		//05682
	public static ItemStack tinyWardenicComposite;			//05683
	public static ItemStack tinyRedsolderArcane;			//05684
	public static ItemStack tinyRedbronze;					//05685
	public static ItemStack tinyRedbronzeHardened;			//05686
	public static ItemStack tinyFluxsteel;					//05687
	public static ItemStack tinyFluxedTungsten;				//05688
	public static ItemStack tinyMagneoturgicComposite;		//05689
	public static ItemStack tinyFluxedComposite;			//05690
	public static ItemStack tinyResonantFluxedComposite;	//05691
	public static ItemStack tinyEmpoweredVoidbrass;			//05692
	public static ItemStack tinyCrimsonThaumium;			//05693
	public static ItemStack tinyOccultVoidtungsten;			//05694

	//00696-00711 GEM TINY DUSTS
	public static ItemStack tinyPyrope;						//05696
	public static ItemStack tinyDioptase;					//05697
	public static ItemStack tinyFluonicSapphire;			//05698
	public static ItemStack tinyFluonicPyroptase;			//05699
	public static ItemStack tinyWardenicCrystal;			//05700
	public static ItemStack tinyWardenicCrystalActivated;	//05701

	public static ItemStack tinyWardenicQuartz;				//05708
	public static ItemStack tinyRedquartz;					//05709

	//00712-00727 OTHER METAL TINY DUSTS
	public static ItemStack tinyLanthanides;				//05712
	public static ItemStack tinyXenotimeJunk;				//05713
	public static ItemStack tinyIridosmium;					//05714

	public static ItemStack tinyThaumicBronze;				//05720
	public static ItemStack tinyOsLu;						//05721

	//00800-00831 ELEMENTAL METAL PLATES
	public static ItemStack plateCopper;					//05800
	public static ItemStack plateZinc;						//05801
	public static ItemStack plateTin;						//05802
	public static ItemStack plateNickel;					//05803
	public static ItemStack plateSilver;					//05804
	public static ItemStack plateLead;						//05805
	public static ItemStack plateLutetium;					//05806
	public static ItemStack plateTungsten;					//05807
	public static ItemStack plateIridium;					//05808
	public static ItemStack plateBismuth;					//05809
	public static ItemStack plateArsenic;					//05810
	public static ItemStack plateAntimony;					//05811
	public static ItemStack plateNeodymium;					//05812
	public static ItemStack plateOsmium;					//05813
	public static ItemStack platePalladium;					//05814
	public static ItemStack plateAluminium;					//05815

	//00832-00863 SIMPLE ALLOY PLATES
	public static ItemStack plateBrass;						//05832
	public static ItemStack plateBronze;					//05833
	public static ItemStack plateArsenicalBronze;			//05834
	public static ItemStack plateAntimonialBronze;			//05835
	public static ItemStack plateBismuthBronze;				//05836
	public static ItemStack plateMithril;					//05837
	public static ItemStack plateAluminiumBronze;			//05838
	public static ItemStack plateCupronickel;				//05839
	public static ItemStack plateRiftishBronze;				//05840
	public static ItemStack plateConstantan;				//05841
	public static ItemStack plateInvar;						//05842
	public static ItemStack plateElectrum;					//05843
	public static ItemStack plateWardenicMetal;				//05844
	public static ItemStack plateDullRedsolder;				//05845
	public static ItemStack plateRedsolder;					//05846

	//00864-00879 SPECIAL ALLOY PLATES
	public static ItemStack plateThaumicElectrum;			//05864
	public static ItemStack plateThaumicRiftishBronze;		//05865
	public static ItemStack plateSteel;						//05866
	public static ItemStack plateVoidbrass;					//05867
	public static ItemStack plateVoidsteel;					//05868
	public static ItemStack plateVoidtungsten;				//05869
	public static ItemStack plateVoidcupronickel;			//05870

	//00880-00895 EQUIPMENT ALLOY PLATES
	public static ItemStack plateWardenicBronze;			//05880
	public static ItemStack plateWardenicSteel;				//05881
	public static ItemStack plateWardenicRiftishBronze;		//05882
	public static ItemStack plateWardenicComposite;			//05883
	public static ItemStack plateRedsolderArcane;			//05884
	public static ItemStack plateRedbronze;					//05885
	public static ItemStack plateRedbronzeHardened;			//05886
	public static ItemStack plateFluxsteel;					//05887
	public static ItemStack plateFluxedTungsten;			//05888
	public static ItemStack plateMagneoturgicComposite;		//05889
	public static ItemStack plateFluxedComposite;			//05890
	public static ItemStack plateResonantFluxedComposite;	//05891
	public static ItemStack plateEmpoweredVoidbrass;		//05892
	public static ItemStack plateCrimsonThaumium;			//05893
	public static ItemStack plateOccultVoidtungsten;		//05894

	//00920-00927 OTHER METAL PLATES
	public static ItemStack plateThaumicBronze;				//05920
	public static ItemStack plateOsLu;						//05921

	//02000-02031 RAW SIMPLE ALLOY INGOTS
	public static ItemStack rawBrass;						//04000
	public static ItemStack rawBronze;						//04001
	public static ItemStack rawArsenicalBronze;				//04002
	public static ItemStack rawAntimonialBronze;			//04003
	public static ItemStack rawBismuthBronze;				//04004
	public static ItemStack rawMithril;						//04005
	public static ItemStack rawAluminiumBronze;				//04006
	public static ItemStack rawCupronickel;					//04007
	public static ItemStack rawRiftishBronze;				//04008
	public static ItemStack rawConstantan;					//04009
	public static ItemStack rawInvar;						//04010
	public static ItemStack rawElectrum;					//04011
	public static ItemStack rawWardenicMetal;				//04012
	public static ItemStack rawRedsolder;					//04013

	//02032-02047 RAW COMPOSITES
	public static ItemStack rawWardenicComposite;			//04035
	public static ItemStack rawMagneoturgicComposite;		//04041

	//02048-02055 RAW COMPLEX ALLOY INGOTS
	public static ItemStack rawThaumicBronze;				//04048
	public static ItemStack rawOsLu;						//04049

	//02056-02063 COMPOUND GEM BLENDS
	public static ItemStack blendFluonicPyrotase;			//04056

	//02064-02071 COATED COMPLEX ALLOY INGOTS
	public static ItemStack coatedThaumicBronze;			//02064
	public static ItemStack coatedOsLu;						//02065

	//02072-02079 FIRED COMPLEX ALLOY INGOTS
	public static ItemStack firedThaumicBronze;				//02072
	public static ItemStack firedOsLu;						//02073

	//02080-02099 SLAGS
	public static ItemStack carbonSlag;						//00780
	public static ItemStack ceramicSlag;					//00781
	public static ItemStack thaumicSlag;					//00782
	public static ItemStack fluonicSlag;					//00783

	//02100-02199 ORE CLUSTERS
	public static ItemStack clusterZinc;					//07101
	public static ItemStack clusterAluminium;				//07102
	public static ItemStack clusterNickel;					//07103
	public static ItemStack clusterPlatinum;				//07104
	public static ItemStack clusterXenotime;				//07106
	public static ItemStack clusterTungsten;				//07107
	public static ItemStack clusterIridosmium;				//07108
	public static ItemStack clusterBismuth;					//07109
	public static ItemStack clusterTennantite;				//07110
	public static ItemStack clusterTetrahedrite;			//07111

	//04000-04099 OTHER INGOTS
	//04100-04199 OTHER NUGGETS
	//04200-04299 OTHER DUST
	public static ItemStack dustIron;						//09200
	public static ItemStack dustGold;						//09201
	public static ItemStack dustThaumium;					//09202
	public static ItemStack dustVoidmetal;					//09203
	public static ItemStack dustSulfur;						//09204

	//04300-04399 OTHER TINY DUST
	public static ItemStack tinyIron;						//09300
	public static ItemStack tinyGold;						//09301
	public static ItemStack tinyThaumium;					//09302
	public static ItemStack tinyVoidmetal;					//09303
	public static ItemStack tinySulfur;						//09304

	//00890-00939 OTHER DUSTS
	public static ItemStack dustAer;						//00890
	public static ItemStack dustIgnis;						//00891
	public static ItemStack dustAqua;						//00892
	public static ItemStack dustTerra;						//00893
	public static ItemStack dustOrdo;						//00894
	public static ItemStack dustPerditio;					//00895



	//Salis Mundus
	public static ItemStack dustPrimalEssence;				//00901


	public static ItemStack dustWardenicBinder;				//00910

	//00940-00989 OTHER TINY DUSTS
	public static ItemStack tinyAer;						//00940
	public static ItemStack tinyIgnis;						//00941
	public static ItemStack tinyAqua;						//00942
	public static ItemStack tinyTerra;						//00943
	public static ItemStack tinyOrdo;						//00944
	public static ItemStack tinyPerditio;					//00945

	public static ItemStack tinySalisMundus;				//00950
	public static ItemStack tinyPrimalEssence;				//00951


	public static ItemStack tinyWardenicBinder;				//00960

	//00990-00999 SEEDS
	public static ItemStack seedExcubitura;					//00990
	public static ItemStack seedCotton;						//00991
	public static ItemStack seedThistle;					//00992
	public static ItemStack seedShimmerleaf;				//00993
	public static ItemStack seedCinderpearl;				//00994
	public static ItemStack seedShiverpearl;				//00995
	public static ItemStack seedStormpearl;					//00996
	public static ItemStack seedStonepearl;					//00997

	//01000-01199 WARDENIC ARSENAL
	public static ItemStack excubituraPetal;				//01000
	public static ItemStack excubituraPaste;				//01001
	public static ItemStack excubituraFabric;				//01002
	public static ItemStack itemWardencloth;				//01003
	public static ItemStack itemImpregnatedGreatwoodShaft;	//01004

	public static ItemStack excubituraOilUnproc;			//01035
	public static ItemStack excubituraOil;					//01036
	public static ItemStack wardenicBronzeChain;			//01037
	public static ItemStack primalBronzeChain;				//01038
	public static ItemStack itemSocketedWardenicBinding;	//01039

	public static ItemStack excubituraOilPure;				//01070
	public static ItemStack wardenicSteelChain;				//01071
	public static ItemStack oiledSteelChain;				//01072
	public static ItemStack detailedSteelPlate;				//01073
	public static ItemStack runicSteelPlate;				//01074
	public static ItemStack consecratedSteelPlate;			//01075

	public static ItemStack wardenicQuartzInf;				//01105
	public static ItemStack fittedCompositePlate;			//01106
	public static ItemStack detailedCompositePlate;			//01107
	public static ItemStack runicCompositePlate;			//01108
	public static ItemStack consecratedCompositePlate;		//01109
	public static ItemStack primalCompositePlate;			//01110

	public static ItemStack gemWardenicCrystalAwakened;		//01140
	public static ItemStack itemEssenceOfAwakening;			//01141

	public static ItemStack wardenicHardener;				//01175

	//01200-01399 MAGNEOTURGY
	//01400-01599 ELDTRICH
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
	//03300-03499 AUGMENTS
	//03500-03599 GOLEM STUFF

	//03600-03699 BACKUPS
	public static ItemStack powderBlizz;					//03600
	public static ItemStack powderBlitz;					//03601
	public static ItemStack powderBasalz;					//03602

	//public static ItemStack wardenJournal1;				//05001 //TODO: Wardenic Backstory

	//09500-09599 INTERMOD ITEMS
	//10000-19999 TINKER'S CONSTRUCT TOOL PARTS

	//30000+ TEMPORARY ITEMS
	public static ItemStack aluDenseTemp;					//30000


	public static ItemStack alum;							//00015


	public static ItemStack wardenAmulet;					//00000
	public static ItemStack loveRing;						//00001

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
	public static ShapelessOreRecipe[] recipeBismuthBronze;
	public static ShapelessOreRecipe recipeMithril;
	public static ShapelessOreRecipe recipeAlBronze;
	public static ShapelessOreRecipe recipeCupronickel;
	public static ShapelessOreRecipe recipeRiftishBronze;
	public static ShapelessOreRecipe recipeConstantan;
	public static ShapelessOreRecipe recipeInvar;
	public static ShapelessOreRecipe recipeElectrum;

	public static ShapelessOreRecipe recDustBrass;
	public static ShapelessOreRecipe recDustBronze;
	public static ShapelessOreRecipe[] recDustBismuthBronze;
	public static ShapelessOreRecipe recDustMithril;
	public static ShapelessOreRecipe recDustAlBronze;
	public static ShapelessOreRecipe recDustCupronickel;
	public static ShapelessOreRecipe recDustRiftishBronze;
	public static ShapelessOreRecipe recDustConstantan;
	public static ShapelessOreRecipe recDustInvar;
	public static ShapelessOreRecipe recDustElectrum;

	//public static ShapelessOreRecipe recipeOsLu;
	//public static ShapelessOreRecipe recCoatOsLu;

	/*public static ShapelessOreRecipe recTinyBrass;
	public static ShapelessOreRecipe recTinyBronze;
	public static ShapelessOreRecipe recTinyMithril;
	public static ShapelessOreRecipe recTinyRiftishBronze;
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

	public static InfusionRecipe recipePrimalGoggles;
	public static InfusionRecipe recipePrimalRobes;
	public static InfusionRecipe recipePrimalPants;
	public static InfusionRecipe recipePrimalBoots;

	public static ShapedArcaneRecipe recipeAniPiston;

	public static ShapelessArcaneRecipe recipeThaumicBronzeRaw;
	public static ShapelessArcaneRecipe recipeThaumicBronzeCoated;

	public static InfusionRecipe recipeThaumicRBronze;

	public static ShapedArcaneRecipe recipeThaumicBronzeChain;

	public static ShapedArcaneRecipe recipeBronzeChainHelmet;
	public static ShapedArcaneRecipe recipeBronzeChainmail;
	public static ShapedArcaneRecipe recipeBronzeChainGreaves;
	public static ShapedArcaneRecipe recipeBronzeChainBoots;

	//public static ShapedArcaneRecipe recipeRunicInfuser;
	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeArcaneSingularity;
	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeStableSingularity;

	public static /*RunicInfuserRecipe*/ ShapelessArcaneRecipe recipeEnchSilverwood;
	public static /*RunicInfuserRecipe*/ InfusionRecipe recipeConsSilverwood;

	//public static ShapedArcaneRecipe recipeDarkRunicInfuser;

	//public static InfusionRecipe recipeAlchemicalInfuser;

	//public static InfusionRecipe recipeDarkAlchemicalInfuser;

	public static CrucibleRecipe recipeTransNickel;
	public static CrucibleRecipe recipeTransAluminium;
	public static CrucibleRecipe recipeTransNeodymium;
	public static CrucibleRecipe recipeTransZinc;
	public static CrucibleRecipe recipeTransArsenic;
	public static CrucibleRecipe recipeTransAntimony;
	public static CrucibleRecipe recipeTransBismuth;
	public static CrucibleRecipe recipeTransTungsten;
	public static CrucibleRecipe recipeTransLutetium;
	public static CrucibleRecipe recipeTransPalladium;
	public static CrucibleRecipe recipeTransPlatinum;
	public static CrucibleRecipe recipeTransOsmium;
	public static CrucibleRecipe recipeTransIridium;

	public static CrucibleRecipe recipeClusterNickel;
	public static CrucibleRecipe recipeClusterAluminium;
	public static CrucibleRecipe recipeClusterXenotime;

	public static CrucibleRecipe recipeClusterZinc;

	public static CrucibleRecipe recipeClusterBismuth;
	public static CrucibleRecipe recipeClusterTennantite;
	public static CrucibleRecipe recipeClusterTertahedrite;
	public static CrucibleRecipe recipeClusterPlatinum;
	public static CrucibleRecipe recipeClusterTungsten;
	public static CrucibleRecipe recipeClusterIridosmium;

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

	public static ShapelessOreRecipe recipeAluDenseTemp;    //TODO: Add Thaumic Hammermill
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

	public static InfusionRecipe recipeWardenicCrystalAwakened;

	public static CrucibleRecipe recipeThaumicElectrum;

	/** RESEARCH **/
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

	public static ResearchItem researchAspectOrb;
	public static String keyAspectOrb = "ASPECT_ORB";

	public static ResearchItem researchCotton;
	public static String keyCotton = "ENCHCOTTON";

	public static ResearchItem researchPrimalRobes;
	public static String keyRobesPrimal = "ROBES_PRIMAL";

	public static ResearchItem researchAniPiston;
	public static String keyAniPiston = "ANIMATED_PISTON";

	public static ResearchItem researchThaumicBronze;
	public static String keyThaumicBronze = "THAUMIC_BRONZE";

	public static ResearchItem researchThaumicRBronze;
	public static String keyThaumicRBronze = "THAUMIC_TINKERS_BRONZE";

	public static ResearchItem researchBronzeChain;
	public static String keyBronzeChain = "TBRONZE_CHAIN";

	public static ResearchItem researchArmorBronzeChain;
	public static String keyArmorBronzeChain = "ARMOR_TBRONZE";

	public static ResearchItem researchRunicInfuser;
	public static String keyRunicInfuser = "RUNIC_INFUSER";

	public static ResearchItem researchEnchSilverwood;
	public static String keyEnchSilverwood = "ENCH_SILVERWOOD";


	public static ResearchItem researchTransmutationFe;
	public static String keyTransmutationFe = "TRv_TRANSMUTATION_FE";

	public static ResearchItem researchTransmutationSn;
	public static String keyTransmutationSn = "TRv_TRANSMUTATION_SN";
	
	public static ResearchItem researchTransmutationAg;
	public static String keyTransmutationAg = "TRv_TRANSMUTATION_AG";

	public static ResearchItem researchTransmutationPb;
	public static String keyTransmutationPb = "TRv_TRANSMUTATION_PB";

	public static ResearchItem researchTransmutationAu;
	public static String keyTransmutationAu = "TRv_TRANSMUTATION_AU";

	public static ResearchItem researchTransmutationNi;
	public static String keyTransmutationNi = "TRv_TRANSMUTATION_NI";

	public static ResearchItem researchTransmutationAl;
	public static String keyTransmutationAl = "TRv_TRANSMUTATION_AL";

	public static ResearchItem researchTransmutationNd;
	public static String keyTransmutationNd = "TRv_TRANSMUTATION_ND";

	public static ResearchItem researchTransmutationZn;
	public static String keyTransmutationZn = "TRv_TRANSMUTATION_ZN";

	public static ResearchItem researchTransmutationAs;
	public static String keyTransmutationAs = "TRv_TRANSMUTATION_AS";

	public static ResearchItem researchTransmutationSb;
	public static String keyTransmutationSb = "TRv_TRANSMUTATION_SB";

	public static ResearchItem researchTransmutationBi;
	public static String keyTransmutationBi = "TRv_TRANSMUTATION_BI";

	public static ResearchItem researchTransmutationW;
	public static String keyTransmutationW = "TRv_TRANSMUTATION_W";

	public static ResearchItem researchTransmutationLu;
	public static String keyTransmutationLu = "TRv_TRANSMUTATION_LU";

	public static ResearchItem researchTransmutationPd;
	public static String keyTransmutationPd = "TRv_TRANSMUTATION_PD";

	public static ResearchItem researchTransmutationPt;
	public static String keyTransmutationPt = "TRv_TRANSMUTATION_PT";

	public static ResearchItem researchTransmutationOs;
	public static String keyTransmutationOs = "TRv_TRANSMUTATION_OS";

	public static ResearchItem researchTransmutationIr;
	public static String keyTransmutationIr = "TRv_TRANSMUTATION_IR";


	public static ResearchItem researchClusterFe;
	public static String keyClusterFe = "TRv_CLUSTER_FE";

	public static ResearchItem researchClusterCu;
	public static String keyClusterCu = "TRv_CLUSTER_CU";

	public static ResearchItem researchClusterSn;
	public static String keyClusterSn = "TRv_CLUSTER_SN";

	public static ResearchItem researchClusterPb;
	public static String keyClusterPb = "TRv_CLUSTER_PB";

	public static ResearchItem researchClusterAu;
	public static String keyClusterAu = "TRv_CLUSTER_AU";

	public static ResearchItem researchClusterNi;
	public static String keyClusterNi = "TRv_CLUSTER_NI";

	public static ResearchItem researchClusterAl;
	public static String keyClusterAl = "TRv_CLUSTER_AL";

	public static ResearchItem researchClusterYPO;
	public static String keyClusterYPO = "TRv_CLUSTER_YPO";

	public static ResearchItem researchClusterZn;
	public static String keyClusterZn = "TRv_CLUSTER_ZN";

	public static ResearchItem researchClusterBi;
	public static String keyClusterBi = "TRv_CLUSTER_Bi";

	public static ResearchItem researchClusterCuAs;
	public static String keyClusterCuAs = "TRv_CLUSTER_CUAS";

	public static ResearchItem researchClusterCuSb;
	public static String keyClusterCuSb = "TRv_CLUSTER_CUSB";

	public static ResearchItem researchClusterPt;
	public static String keyClusterPt = "TRv_CLUSTER_PT";

	public static ResearchItem researchClusterW;
	public static String keyClusterW = "TRv_CLUSTER_W";

	public static ResearchItem researchClusterIrOs;
	public static String keyClusterIrOs = "TRv_CLUSTER_IROS";


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


	public static ResearchItem researchWardenCrystalAwakened;
	public static String keyWardenCrystalAwakened = "WARDEN_CRYSTAL_AWAKENED";


	public static ResearchItem researchThaumicElectrum;
	public static String keyThaumicElectrum = "THAUMIC_ELECTRUM";

	/** WORLD GENERATORS **/
	public static WorldGenPlant genExcubitura;
	public static WorldGenPlant genCotton;
	public static WorldGenPlant genThistle;
	public static WorldGenOreVein genChalcocite;
	public static WorldGenOreVein genSphalerite;
	public static WorldGenOreVein genCassiterite;
	public static WorldGenOreVein genMillerite;
	public static WorldGenOreVein genNativeSilver;
	public static WorldGenOreVein genGalena;
	public static WorldGenOreVein genXenotime;
	public static WorldGenOreVein genWolframite;
	public static WorldGenOreVein genIridosmium;
	public static WorldGenOreVein genBismuthinite;
	public static WorldGenOreVein genTennantite;
	public static WorldGenOreVein genTetrahedite;
	public static WorldGenOreVein genPyrope;
	public static WorldGenOreVein genDioptase;
	public static WorldGenOreVein genFluonicSapphire;
	public static WorldGenMixedOreVein genCopperMix;
	public static WorldGenMixedOreVein genAgPb;
	public static WorldGenMixedOreVein genWSn;
	public static WorldGenMixedOreVein genAgPbBi;

	/** BLOCK NAMES  **/
	// Migrated until v0.1.0 //

	public static final String matPrimal = "PRIMAL";
	public static final String matBronzeChain = "BRONZE_CHAIN";
	public static final String matWardencloth = "WARDENCLOTH";
	public static final String matWardenicChain = "WARDENIC_CHAIN";
	public static final String matWardenicSteel = "WARDENIC_STEEL";
	public static final String matWardenicComposite = "WARDENIC_COMPOSITE";

	/** NBT KEYS * */
	public static final String INVENTORY = "Items";
	public static final String SLOT = "Slot";
	public static final String DURABILITY = "Durability";
	public static final String REVEALING = "Revealing";
	public static final String BROKEN = "Broken";
	public static final String VISMODIFIER = "VisDiscount";

	/** CLIENT-SIDE * */
	public static int renderDecorStoneID;
	//public static int wardedChestRenderID = -1;
}
