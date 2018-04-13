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

	//00200-00224 BASE METAL INGOTS/NATURAL GEMS
	public static ItemStack ingotCopper;					//00200
	public static ItemStack ingotZinc;						//00201
	public static ItemStack ingotTin;						//00202
	public static ItemStack ingotNickel;					//00203
	public static ItemStack ingotSilver;					//00204
	public static ItemStack ingotLead;						//00205
	public static ItemStack ingotLanthanides;				//00206
	public static ItemStack ingotTungsten;					//00207
	public static ItemStack ingotIridium;					//00208
	public static ItemStack ingotBismuth;					//00209
	public static ItemStack ingotArsenicalBronze;			//00210
	public static ItemStack ingotAntimonialBronze;			//00211
	public static ItemStack gemPyrope;						//00212
	public static ItemStack gemDioptase;					//00213
	public static ItemStack gemFluonicSapphire;				//00214
	public static ItemStack ingotOsmium;					//00215
	public static ItemStack ingotNeodymium;					//00216
	public static ItemStack ingotLutetium;					//00217
	public static ItemStack ingotPalladium;					//00218
	public static ItemStack ingotIridosmium;				//00219
	public static ItemStack ingotAluminium;					//00220
	public static ItemStack ingotXenotimeJunk;				//00221

	//00225-00249 SIMPLE ALLOY INGOTS
	public static ItemStack ingotBrass;						//00225
	public static ItemStack ingotBronze;					//00226
	public static ItemStack ingotBismuthBronze;				//00227
	public static ItemStack ingotMithril;					//00228
	public static ItemStack ingotAluminiumBronze;			//00229
	public static ItemStack ingotCupronickel;				//00230
	public static ItemStack ingotRiftishBronze;				//00231
	public static ItemStack ingotConstantan;				//00232
	public static ItemStack ingotInvar;						//00233
	public static ItemStack ingotElectrum;					//00234
	public static ItemStack ingotWardenicMetal;				//00235
	public static ItemStack ingotDullRedsolder;				//00236
	public static ItemStack ingotRedsolder;					//00237

	//00250-00259 COMPLEX ALLOY INGOTS/COMPOUND GEMS/DUST METAL INGOTS
	public static ItemStack ingotThaumicBronze;				//00250
	public static ItemStack ingotOsLu;						//00251
	public static ItemStack gemFluonicPyroptase;			//00252

	public static ItemStack ingotArsenic;					//00255
	public static ItemStack ingotAntimony;					//00256

	//00260-00299 SPECIAL ALLOY INGOTS
	public static ItemStack ingotThaumicElectrum;			//00260
	public static ItemStack ingotThaumicRiftishBronze;		//00261
	public static ItemStack ingotSteel;						//00262
	public static ItemStack ingotVoidbrass;					//00263
	public static ItemStack ingotVoidsteel;					//00264
	public static ItemStack ingotVoidtungsten;				//00265

	public static ItemStack ingotWardenicBronze;			//00270
	public static ItemStack ingotWardenicSteel;				//00271
	public static ItemStack ingotWardenicRiftishBronze;		//00272
	public static ItemStack gemWardenicQuartz;				//00273
	public static ItemStack gemWardenicCrystal;				//00274
	public static ItemStack gemWardenicCrystalActivated;	//00275
	public static ItemStack ingotWardenicComposite;			//00276

	public static ItemStack ingotRedsolderArcane;			//00280
	public static ItemStack ingotRedbronze;					//00281
	public static ItemStack ingotRedbronzeHardened;			//00282
	public static ItemStack ingotFluxsteel;					//00283
	public static ItemStack gemRedquartz;					//00284
	public static ItemStack ingotFluxedTungsten;			//00285
	public static ItemStack ingotMagneoturgicComposite;		//00286
	public static ItemStack ingotFluxedComposite;			//00287
	public static ItemStack ingotResonantFluxedComposite;	//00288

	//00300-00324 BASE METAL NUGGETS/NATURAL GEM SHARDS
	public static ItemStack nuggetCopper;					//00300
	public static ItemStack nuggetZinc;						//00301
	public static ItemStack nuggetTin;						//00302
	public static ItemStack nuggetNickel;					//00303
	public static ItemStack nuggetSilver;					//00304
	public static ItemStack nuggetLead;						//00305
	public static ItemStack nuggetLanthanides;				//00306
	public static ItemStack nuggetTungsten;					//00307
	public static ItemStack nuggetIridium;					//00308
	public static ItemStack nuggetBismuth;					//00309
	public static ItemStack nuggetArsenicalBronze;			//00310
	public static ItemStack nuggetAntimonialBronze;			//00311
	public static ItemStack shardPyrope;					//00312
	public static ItemStack shardDioptase;					//00313
	public static ItemStack shardFluonicSapphire;			//00314
	public static ItemStack nuggetOsmium;					//00315
	public static ItemStack nuggetNeodymium;				//00316
	public static ItemStack nuggetLutetium;					//00317
	public static ItemStack nuggetPalladium;				//00318
	public static ItemStack nuggetIridosmium;				//00319
	public static ItemStack nuggetAluminium;				//00320
	public static ItemStack nuggetXenotimeJunk;				//00321

	//00325-00349 SIMPLE ALLOY NUGGETS
	public static ItemStack nuggetBrass;					//00325
	public static ItemStack nuggetBronze;					//00326
	public static ItemStack nuggetBismuthBronze;			//00327
	public static ItemStack nuggetMithril;					//00328
	public static ItemStack nuggetAluminiumBronze;			//00329
	public static ItemStack nuggetCupronickel;				//00330
	public static ItemStack nuggetRiftishBronze;			//00331
	public static ItemStack nuggetConstantan;				//00332
	public static ItemStack nuggetInvar;					//00333
	public static ItemStack nuggetElectrum;					//00334
	public static ItemStack nuggetWardenicMetal;			//00335
	public static ItemStack nuggetDullRedsolder;			//00336
	public static ItemStack nuggetRedsolder;				//00337

	//00350-00359 COMPLEX ALLOY NUGGETS/COMPOUND GEM SHARDS/DUST METAL NUGGETS
	public static ItemStack nuggetThaumicBronze;			//00350
	public static ItemStack nuggetOsLu;						//00351
	public static ItemStack shardFluonicPyroptase;			//00352

	public static ItemStack nuggetArsenic;					//00355
	public static ItemStack nuggetAntimony;					//00356

	//00360-00399 SPECIAL ALLOY NUGGETS
	public static ItemStack nuggetThaumicElectrum;			//00360
	public static ItemStack nuggetThaumicRiftishBronze;		//00361
	public static ItemStack nuggetSteel;					//00362
	public static ItemStack nuggetVoidbrass;				//00363
	public static ItemStack nuggetVoidsteel;				//00364
	public static ItemStack nuggetVoidtungsten;				//00365

	public static ItemStack nuggetWardenicBronze;			//00370
	public static ItemStack nuggetWardenicSteel;			//00371
	public static ItemStack nuggetWardenicRiftishBronze;	//00372
	public static ItemStack shardWardenicQuartz;			//00373
	public static ItemStack shardWardenicCrystal;			//00374
	public static ItemStack shardWardenicCrystalActivated;	//00375
	public static ItemStack nuggetWardenicComposite;		//00376

	public static ItemStack nuggetRedsolderArcane;			//00380
	public static ItemStack nuggetRedbronze;				//00381
	public static ItemStack nuggetRedbronzeHardened;		//00382
	public static ItemStack nuggetFluxsteel;				//00383
	public static ItemStack shardRedquartz;					//00384
	public static ItemStack nuggetFluxedTungsten;			//00385
	public static ItemStack nuggetMagneoturgicComposite;	//00386
	public static ItemStack nuggetFluxedComposite;			//00387
	public static ItemStack nuggetResonantFluxedComposite;	//00388

	//00400-00424 BASE METAL DUSTS/NATURAL GEM DUSTS
	public static ItemStack dustCopper;						//00400
	public static ItemStack dustZinc;						//00401
	public static ItemStack dustTin;						//00402
	public static ItemStack dustNickel;						//00403
	public static ItemStack dustSilver;						//00404
	public static ItemStack dustLead;						//00405
	public static ItemStack dustLanthanides;				//00406
	public static ItemStack dustTungsten;					//00407
	public static ItemStack dustIridium;					//00408
	public static ItemStack dustBismuth;					//00409
	public static ItemStack dustArsenicalBronze;			//00410
	public static ItemStack dustAntimonialBronze;			//00411
	public static ItemStack dustPyrope;						//00412
	public static ItemStack dustDioptase;					//00413
	public static ItemStack dustFluonicSapphire;			//00414
	public static ItemStack dustOsmium;						//00415
	public static ItemStack dustNeodymium;					//00416
	public static ItemStack dustLutetium;					//00417
	public static ItemStack dustPalladium;					//00418
	public static ItemStack dustIridosmium;					//00419
	public static ItemStack dustAluminium;					//00420
	public static ItemStack dustXenotimeJunk;				//00421

	//00425-00449 SIMPLE ALLOY DUSTS
	public static ItemStack dustBrass;						//00425
	public static ItemStack dustBronze;						//00426
	public static ItemStack dustBismuthBronze;				//00427
	public static ItemStack dustMithril;					//00428
	public static ItemStack dustAluminiumBronze;			//00429
	public static ItemStack dustCupronickel;				//00430
	public static ItemStack dustRiftishBronze;				//00431
	public static ItemStack dustConstantan;					//00432
	public static ItemStack dustInvar;						//00433
	public static ItemStack dustElectrum;					//00434
	public static ItemStack dustWardenicMetal;				//00435
	public static ItemStack dustDullRedsolder;				//00436
	public static ItemStack dustRedsolder;					//00437

	//00450-00459 COMPLEX ALLOY DUSTS/COMPOUND GEM DUSTS/DUST METAL DUSTS
	public static ItemStack dustThaumicBronze;				//00450
	public static ItemStack dustOsLu;						//00451
	public static ItemStack dustFluonicPyroptase;			//00452

	public static ItemStack dustArsenic;					//00455
	public static ItemStack dustAntimony;					//00456

	//00460-00499 SPECIAL ALLOY DUSTS
	public static ItemStack dustThaumicElectrum;			//00460
	public static ItemStack dustThaumicRiftishBronze;		//00461
	public static ItemStack dustSteel;						//00462
	public static ItemStack dustVoidbrass;					//00463
	public static ItemStack dustVoidsteel;					//00464
	public static ItemStack dustVoidtungsten;				//00465

	public static ItemStack dustWardenicBronze;				//00470
	public static ItemStack dustWardenicSteel;				//00471
	public static ItemStack dustWardenicRiftishBronze;		//00472
	public static ItemStack dustWardenicQuartz;				//00473
	public static ItemStack dustWardenicCrystal;			//00474
	public static ItemStack dustWardenicCrystalActivated;	//00475
	public static ItemStack dustWardenicComposite;			//00476

	public static ItemStack dustRedsolderArcane;			//00480
	public static ItemStack dustRedbronze;					//00481
	public static ItemStack dustRedbronzeHardened;			//00482
	public static ItemStack dustFluxsteel;					//00483
	public static ItemStack dustRedquartz;					//00484
	public static ItemStack dustFluxedTungsten;				//00485
	public static ItemStack dustMagneoturgicComposite;		//00486
	public static ItemStack dustFluxedComposite;			//00487
	public static ItemStack dustResonantFluxedComposite;	//00488

	//00500-00524 BASE METAL TINY DUSTS/NATURAL GEM TINY DUSTS
	public static ItemStack tinyCopper;						//00500
	public static ItemStack tinyZinc;						//00501
	public static ItemStack tinyTin;						//00502
	public static ItemStack tinyNickel;						//00503
	public static ItemStack tinySilver;						//00504
	public static ItemStack tinyLead;						//00505
	public static ItemStack tinyLanthanides;				//00506
	public static ItemStack tinyTungsten;					//00507
	public static ItemStack tinyIridium;					//00508
	public static ItemStack tinyBismuth;					//00509
	public static ItemStack tinyArsenicalBronze;			//00510
	public static ItemStack tinyAntimonialBronze;			//00511
	public static ItemStack tinyPyrope;						//00512
	public static ItemStack tinyDioptase;					//00513
	public static ItemStack tinyFluonicSapphire;			//00514
	public static ItemStack tinyOsmium;						//00515
	public static ItemStack tinyNeodymium;					//00516
	public static ItemStack tinyLutetium;					//00517
	public static ItemStack tinyPalladium;					//00518
	public static ItemStack tinyIridosmium;					//00519
	public static ItemStack tinyAluminium;					//00520
	public static ItemStack tinyXenotimeJunk;				//00521

	//00525-00549 SIMPLE ALLOY TINY DUSTS
	public static ItemStack tinyBrass;						//00525
	public static ItemStack tinyBronze;						//00526
	public static ItemStack tinyBismuthBronze;				//00527
	public static ItemStack tinyMithril;					//00528
	public static ItemStack tinyAluminiumBronze;			//00529
	public static ItemStack tinyCupronickel;				//00530
	public static ItemStack tinyRiftishBronze;				//00531
	public static ItemStack tinyConstantan;					//00532
	public static ItemStack tinyInvar;						//00533
	public static ItemStack tinyElectrum;					//00534
	public static ItemStack tinyWardenicMetal;				//00535
	public static ItemStack tinyDullRedsolder;				//00536
	public static ItemStack tinyRedsolder;					//00537

	//00550-00559 COMPLEX ALLOY TINY DUSTS/COMPOUND GEM TINY DUSTS/DUST METAL TINY DUSTS
	public static ItemStack tinyThaumicBronze;				//00550
	public static ItemStack tinyOsLu;						//00551
	public static ItemStack tinyFluonicPyroptase;			//00552

	public static ItemStack tinyArsenic;					//00555
	public static ItemStack tinyAntimony;					//00556

	//00560-00599 SPECIAL ALLOY TINY DUSTS
	public static ItemStack tinyThaumicElectrum;			//00560
	public static ItemStack tinyThaumicRiftishBronze;		//00561
	public static ItemStack tinySteel;						//00562
	public static ItemStack tinyVoidbrass;					//00563
	public static ItemStack tinyVoidsteel;					//00564
	public static ItemStack tinyVoidtungsten;				//00565

	public static ItemStack tinyWardenicBronze;				//00570
	public static ItemStack tinyWardenicSteel;				//00571
	public static ItemStack tinyWardenicRiftishBronze;		//00572
	public static ItemStack tinyWardenicQuartz;				//00573
	public static ItemStack tinyWardenicCrystal;			//00574
	public static ItemStack tinyWardenicCrystalActivated;	//00575
	public static ItemStack tinyWardenicComposite;			//00576

	public static ItemStack tinyRedsolderArcane;			//00580
	public static ItemStack tinyRedbronze;					//00581
	public static ItemStack tinyRedbronzeHardened;			//00582
	public static ItemStack tinyFluxsteel;					//00583
	public static ItemStack tinyRedquartz;					//00584
	public static ItemStack tinyFluxedTungsten;				//00585
	public static ItemStack tinyMagneoturgicComposite;		//00586
	public static ItemStack tinyFluxedComposite;			//00587
	public static ItemStack tinyResonantFluxedComposite;	//00588

	//00600-00624 BASE METAL PLATES
	public static ItemStack plateCopper;					//00600
	public static ItemStack plateZinc;						//00601
	public static ItemStack plateTin;						//00602
	public static ItemStack plateNickel;					//00603
	public static ItemStack plateSilver;					//00604
	public static ItemStack plateLead;						//00605

	public static ItemStack plateTungsten;					//00607
	public static ItemStack plateIridium;					//00608
	public static ItemStack plateBismuth;					//00609
	public static ItemStack plateArsenicalBronze;			//00610
	public static ItemStack plateAntimonialBronze;			//00611

	public static ItemStack plateOsmium;					//00615
	public static ItemStack plateNeodymium;					//00616
	public static ItemStack plateLutetium;					//00617
	public static ItemStack platePalladium;					//00618
	public static ItemStack plateIridosmium;				//00619
	public static ItemStack plateAluminium;					//00620

	//00625-00649 SIMPLE ALLOY PLATES
	public static ItemStack plateBrass;						//00625
	public static ItemStack plateBronze;					//00626
	public static ItemStack plateBismuthBronze;				//00627
	public static ItemStack plateMithril;					//00628
	public static ItemStack plateAluminiumBronze;			//00629
	public static ItemStack plateCupronickel;				//00630
	public static ItemStack plateRiftishBronze;				//00631
	public static ItemStack plateConstantan;				//00632
	public static ItemStack plateInvar;						//00633
	public static ItemStack plateElectrum;					//00634
	public static ItemStack plateWardenicMetal;				//00635
	public static ItemStack plateDullRedsolder;				//00636
	public static ItemStack plateRedsolder;					//00637

	//00650-00659 COMPLEX ALLOY PLATES
	public static ItemStack plateThaumicBronze;				//00650
	public static ItemStack plateOsLu;						//00651

	//00660-00699 SPECIAL ALLOY PLATES
	public static ItemStack plateThaumicElectrum;			//00660
	public static ItemStack plateThaumicRiftishBronze;		//00661
	public static ItemStack plateSteel;						//00662
	public static ItemStack plateVoidbrass;					//00663
	public static ItemStack plateVoidsteel;					//00664
	public static ItemStack plateVoidtungsten;				//00665

	public static ItemStack plateWardenicBronze;			//00670
	public static ItemStack plateWardenicSteel;				//00671
	public static ItemStack plateWardenicRiftishBronze;		//00672
	public static ItemStack plateWardenicComposite;			//00676

	public static ItemStack plateRedsolderArcane;			//00680
	public static ItemStack plateRedbronze;					//00681
	public static ItemStack plateRedbronzeHardened;			//00682
	public static ItemStack plateFluxsteel;					//00683
	public static ItemStack plateFluxedTungsten;			//00685
	public static ItemStack plateMagneoturgicComposite;		//00686
	public static ItemStack plateFluxedComposite;			//00687
	public static ItemStack plateResonantFluxedComposite;	//00688

	//00700-00729 OTHER PLATES

	//00730-00754 RAW SIMPLE ALLOY INGOTS
	public static ItemStack rawBrass;						//00740
	public static ItemStack rawBronze;						//00741
	public static ItemStack rawBismuthBronze;				//00742
	public static ItemStack rawMithril;						//00743
	public static ItemStack rawAluminiumBronze;				//00744
	public static ItemStack rawCupronickel;					//00745
	public static ItemStack rawRiftishBronze;				//00746
	public static ItemStack rawConstantan;					//00747
	public static ItemStack rawInvar;						//00748
	public static ItemStack rawElectrum;					//00749
	public static ItemStack rawWardenicMetal;				//00750
	public static ItemStack rawRedsolder;					//00751

	//00755-00759 RAW COMPLEX ALLOY INGOTS/COMPOUND GEM DUSTS
	public static ItemStack rawThaumicBronze;				//00755
	public static ItemStack rawOsLu;						//00756
	public static ItemStack blendFluonicPyrotase;			//00757

	//00760-00769 RAW COMPOSITES
	public static ItemStack rawWardenicComposite;			//00760
	public static ItemStack rawMagneoturgicComposite;		//00761

	//00770-00774 COATED COMPLEX ALLOY INGOTS
	public static ItemStack coatedThaumicBronze;			//00770
	public static ItemStack coatedOsLu;						//00771

	//00775-00779 FIRED COMPLEX ALLOY INGOTS
	public static ItemStack firedThaumicBronze;				//00775
	public static ItemStack firedOsLu;						//00776

	//00780-00799 SLAGS
	public static ItemStack carbonSlag;						//00780
	public static ItemStack ceramicSlag;					//00781
	public static ItemStack thaumicSlag;					//00782
	public static ItemStack fluonicSlag;					//00783

	//00800-00839 ORE CLUSTERS
	public static ItemStack clusterZinc;					//00801
	public static ItemStack clusterAluminium;				//00802
	public static ItemStack clusterNickel;					//00803
	public static ItemStack clusterPlatinum;				//00804
	public static ItemStack clusterXenotime;				//00806
	public static ItemStack clusterTungsten;				//00807
	public static ItemStack clusterIridosmium;				//00808
	public static ItemStack clusterBismuth;					//00809
	public static ItemStack clusterTennantite;				//00810
	public static ItemStack clusterTetrahedrite;			//00811

	//00840-00889 OTHER NUGGETS

	//00890-00939 OTHER DUSTS
	public static ItemStack dustAer;						//00890
	public static ItemStack dustIgnis;						//00891
	public static ItemStack dustAqua;						//00892
	public static ItemStack dustTerra;						//00893
	public static ItemStack dustOrdo;						//00894
	public static ItemStack dustPerditio;					//00895
	public static ItemStack dustIron;						//00896
	public static ItemStack dustGold;						//00897
	public static ItemStack dustThaumium;					//00898
	public static ItemStack dustVoidmetal;					//00899
	//Salis Mundus
	public static ItemStack dustPrimalEssence;				//00901
	public static ItemStack dustSulfur;						//00902

	public static ItemStack dustWardenicBinder;				//00910

	//00940-00989 OTHER TINY DUSTS
	public static ItemStack tinyAer;						//00940
	public static ItemStack tinyIgnis;						//00941
	public static ItemStack tinyAqua;						//00942
	public static ItemStack tinyTerra;						//00943
	public static ItemStack tinyOrdo;						//00944
	public static ItemStack tinyPerditio;					//00945
	public static ItemStack tinyIron;						//00946
	public static ItemStack tinyGold;						//00947
	public static ItemStack tinyThaumium;					//00948
	public static ItemStack tinyVoidmetal;					//00949
	public static ItemStack tinySalisMundus;				//00950
	public static ItemStack tinyPrimalEssence;				//00951
	public static ItemStack tinySulfur;						//00952

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
	public static final String DURABILITY = "DURABILITY";
	public static final String REVEALING = "REVEALING";
	public static final String BROKEN = "BROKEN";
	public static final String VISMODIFIER = "VISDISCOUNT";

	/** CLIENT-SIDE * */
	public static int renderDecorStoneID;
	//public static int wardedChestRenderID = -1;
}
