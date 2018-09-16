package mortvana.thaumrev.world;

import java.util.*;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import thaumcraft.common.config.Config;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

import mortvana.melteddashboard.util.WeightedRandomBlock;
import mortvana.melteddashboard.util.helpers.LoadedHelper;
import mortvana.melteddashboard.world.*;
import mortvana.melteddashboard.world.poorore.PoorOreGenerator;

import mortvana.thaumrev.common.*;

import static mortvana.thaumrev.common.ThaumRevConfigWorld.*;
import static net.minecraft.init.Blocks.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;

public class ThaumRevWorldGenerator implements IWorldGenerator {

	public ThaumRevWorldGenerator() {
		MinecraftForge.TERRAIN_GEN_BUS.register(this);
		init();
	}

	protected void init() {
		genExcubitura = new WorldGenPlant(blockThaumicPlant, 0, 16, 8);
		genCotton = new WorldGenPlant(blockThaumicPlant, 1, 96, 8);
		genThistle = new WorldGenPlant(blockThaumicPlant, 2);
		genShiverpearl = new WorldGenPlant(blockThaumicPlant, 5, 18, 8);
		genStormypearl = new WorldGenPlant(blockThaumicPlant, 6, 36, 8);
		genStonypearl = new WorldGenPlant(blockThaumicPlant, 7, 27, 8);

		genChalcocite = new WorldGenOreVein(blockOre, 0, stone, 0, 10, 4, 35, 80);
		genSphalerite = new WorldGenOreVein(blockOre, 1, stone, 0, 9, 8, 20, 65);
		genCassiterite = new WorldGenOreVein(blockOre, 2, stone, 0, 9, 8, 15, 60);
		genMillerite = new WorldGenOreVein(blockOre, 3, netherrack, 0, 4, 3, 5, 125);
		genNativeSilver = new WorldGenOreVein(blockOre, 4, stone, 0, 7, 4, 5, 35);
		genGalena = new WorldGenOreVein(blockOre, 5, stone, 0, 8, 2, 10, 40);
		genXenotime = new WorldGenOreVein(blockOre, 6, stone, 0, 6, 4, 5, 30);
		genWolframite = new WorldGenOreVein(blockOre, 7, stone, 0, 2, 2, 5, 25);
		genIridosmium = new WorldGenOreVein(blockOre, 8, netherrack, 0, 2, 2, 5, 125);
		genBismuthinite = new WorldGenOreVein(blockOre, 9, stone, 0, 9, 2, 10, 55);
		genTennantite = new WorldGenOreVein(blockOre, 10, stone, 0, 6, 4, 30, 75);
		genTetrahedite = new WorldGenOreVein(blockOre, 11, stone, 0, 6, 4, 30, 75);
		genPyrope = new WorldGenOreVein(blockOre, 12, netherrack, 0, 4, 4, 5, 40);
		genDioptase = new WorldGenOreVein(blockOre, 13, stone, 0, 4, 2, 7, 11);
		genFluonicSapphire = new WorldGenOreVein(blockOre, 14, stone, 0, 4, 2, 7, 11);

		List<WeightedRandomBlock> cu = new ArrayList<WeightedRandomBlock>();
		cu.add(new WeightedRandomBlock(blockOre, 0, 30));
		cu.add(new WeightedRandomBlock(blockOre, 10, 35));
		cu.add(new WeightedRandomBlock(blockOre, 11, 35));
		genCopperMix = new WorldGenMixedOreVein(cu, stone, 8, 8, 25, 70);

		List<WeightedRandomBlock> pb = new ArrayList<WeightedRandomBlock>();
		pb.add(new WeightedRandomBlock(blockOre, 4, 45));
		pb.add(new WeightedRandomBlock(blockOre, 5, 55));
		genAgPb = new WorldGenMixedOreVein(pb, stone, 17, 4, 5, 35);

		List<WeightedRandomBlock> bi = new ArrayList<WeightedRandomBlock>();
		bi.add(new WeightedRandomBlock(blockOre, 9, 65));
		bi.add(new WeightedRandomBlock(blockOre, 5, 30));
		bi.add(new WeightedRandomBlock(blockOre, 4, 5));
		genAgPbBi = new WorldGenMixedOreVein(bi, stone, 12, 2, 10, 40);

		List<WeightedRandomBlock> w = new ArrayList<WeightedRandomBlock>();
		w.add(new WeightedRandomBlock(blockOre, 2, 35));
		w.add(new WeightedRandomBlock(blockOre, 7, 60));
		w.add(new WeightedRandomBlock(Blocks.iron_ore, 0, 5));
		genWSn = new WorldGenMixedOreVein(w, stone, 6, 3, 10, 25);

		genGravelChalcocite = new SurfaceOreGen(blockGravelOre, 0, 12, true);
		genGravelSphalerite = new SurfaceOreGen(blockGravelOre, 1, 12, true);
		genGravelCassiterite = new SurfaceOreGen(blockGravelOre, 2, 12, true);
		genGravelMillerite = new SurfaceOreGen(blockGravelOre, 3, 12, true);
		genGravelNativeSilver = new SurfaceOreGen(blockGravelOre, 4, 16, true);
		genGravelGalena = new SurfaceOreGen(blockGravelOre, 5, 16, true);
		genGravelXenotime = new SurfaceOreGen(blockGravelOre, 6, 15, true);
		genGravelWolframite = new SurfaceOreGen(blockGravelOre, 7, 17, true);
		genGravelIridosmium = new SurfaceOreGen(blockGravelOre, 8, 13, true);
		genGravelBismuthinite = new SurfaceOreGen(blockGravelOre, 9, 12, true);
		genGravelTennantite = new SurfaceOreGen(blockGravelOre, 10, 10, true);
		genGravelTetrahedite = new SurfaceOreGen(blockGravelOre, 11, 10, true);
	}

	public static void registerPoorOres() {
		Class[] classes = new Class[0];
		Object[] objects = new Object[0];

		if (getPoor(generatePoorChalcocite)) {
			eventPoorChalcocite = EnumHelper.addEnum(EventType.class, "TRv_POOR_CHALCOCITE", classes, objects);
			genPoorChalcocite = new PoorOreGenerator(eventPoorChalcocite, 12, 60, 5, 682, blockPoorOre, 0);
			MinecraftForge.ORE_GEN_BUS.register(genPoorChalcocite);
		}
		if (getPoor(generatePoorSphalerite)) {
			eventPoorSphalerite = EnumHelper.addEnum(EventType.class, "TRv_POOR_SPHALERITE", classes, objects);
			genPoorSphalerite = new PoorOreGenerator(eventPoorSphalerite, 10, 55, 4, 324, blockPoorOre, 1);
			MinecraftForge.ORE_GEN_BUS.register(genPoorSphalerite);
		}
		if (getPoor(generatePoorCassiterite)) {
			eventPoorCassiterite = EnumHelper.addEnum(EventType.class, "TRv_POOR_CASSITERITE", classes, objects);
			genPoorCassiterite = new PoorOreGenerator(eventPoorCassiterite, 10, 50, 4, 526, blockPoorOre, 2);
			MinecraftForge.ORE_GEN_BUS.register(genPoorCassiterite);
		}
		if (getPoor(generatePoorMillerite)) {
			eventPoorMillerite = EnumHelper.addEnum(EventType.class, "TRv_POOR_MILLERITE", classes, objects);
			genPoorMillerite = new PoorOreGenerator(eventPoorMillerite, 7, 35, 3, 706, blockPoorOre, 3);
			MinecraftForge.ORE_GEN_BUS.register(genPoorMillerite);
		}
		if (getPoor(generatePoorNativeSilver)) {
			eventPoorNativeSilver = EnumHelper.addEnum(EventType.class, "TRv_POOR_NATIVE_SILVER", classes, objects);
			genPoorNativeSilver = new PoorOreGenerator(eventPoorNativeSilver, 7, 25, 2, 334, blockPoorOre, 4);
			MinecraftForge.ORE_GEN_BUS.register(genPoorNativeSilver);
		}
		if (getPoor(generatePoorGalena)) {
			eventPoorGalena = EnumHelper.addEnum(EventType.class, "TRv_POOR_GALENA", classes, objects);
			genPoorGalena = new PoorOreGenerator(eventPoorGalena, 8, 30, 3, 261, blockPoorOre, 5);
			MinecraftForge.ORE_GEN_BUS.register(genPoorGalena);
		}
		if (getPoor(generatePoorXenotime)) {
			eventPoorXenotime = EnumHelper.addEnum(EventType.class, "TRv_POOR_XENOTIME", classes, objects);
			genPoorXenotime = new PoorOreGenerator(eventPoorXenotime, 6, 30, 3, 636, blockPoorOre, 6);
			MinecraftForge.ORE_GEN_BUS.register(genPoorXenotime);
		}
		if (getPoor(generatePoorWolframite)) {
			eventPoorWolframite = EnumHelper.addEnum(EventType.class, "TRv_POOR_WOLFRAMITE", classes, objects);
			genPoorWolframite = new PoorOreGenerator(eventPoorWolframite, 2, 15, 2, 297, blockPoorOre, 7);
			MinecraftForge.ORE_GEN_BUS.register(genPoorWolframite);
		}
		if (getPoor(generatePoorIridosmium)) {
			eventPoorIridosmium = EnumHelper.addEnum(EventType.class, "TRv_POOR_IRIDOSMIUM", classes, objects);
			genPoorIridosmium = new PoorOreGenerator(eventPoorIridosmium, 1, 10, 2, 932, blockPoorOre, 8);
			MinecraftForge.ORE_GEN_BUS.register(genPoorIridosmium);
		}
		if (getPoor(generatePoorBismuthinite)) {
			eventPoorBismuthinite = EnumHelper.addEnum(EventType.class, "TRv_POOR_BISMUTHINITE", classes, objects);
			genPoorBismuthinite = new PoorOreGenerator(eventPoorBismuthinite, 10, 45, 4, 282, blockPoorOre, 9);
			MinecraftForge.ORE_GEN_BUS.register(genPoorBismuthinite);
		}
		if (getPoor(generatePoorTennantite)) {
			eventPoorTennantite = EnumHelper.addEnum(EventType.class, "TRv_POOR_TENNANTITE", classes, objects);
			genPoorTennantite = new PoorOreGenerator(eventPoorTennantite, 7, 50, 4, 499, blockPoorOre, 10);
			MinecraftForge.ORE_GEN_BUS.register(genPoorTennantite);
		}
		if (getPoor(generatePoorTetrahedrite)) {
			eventPoorTetrahedite = EnumHelper.addEnum(EventType.class, "TRv_POOR_TETRAHEDRITE", classes, objects);
			genPoorTetrahedite = new PoorOreGenerator(eventPoorTetrahedite, 7, 45, 4, 948, blockPoorOre, 11);
			MinecraftForge.ORE_GEN_BUS.register(genPoorTetrahedite);
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.isHellWorld) {
			generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		} else if (world.provider.dimensionId == 1) {
			//Maybe?
		} else {
			generateOres(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
			generatePlants(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
	}

	@SubscribeEvent
	public void onDecorationEvent(DecorateBiomeEvent.Decorate event) {
		if (event.type != DecorateBiomeEvent.Decorate.EventType.SAND) {
			return;
		}

		BiomeGenBase biome = event.world.getWorldChunkManager().getBiomeGenAt(event.chunkX, event.chunkZ);
		int iterations = BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MOUNTAIN) ? 2 : 1;
		for (int i = 0; i < iterations; i++) {
			generateGravelOres(event.rand, event.chunkX, event.chunkZ, event.world);
		}
	}

	public void generateOres(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (generateChalcocite) {
			genChalcocite.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateSphalerite) {
			genSphalerite.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateCassiterite) {
			genCassiterite.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateNativeSilver) {
			genNativeSilver.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateGalena) {
			genGalena.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateXenotime) {
			genXenotime.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateWolframite) {
			genWolframite.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateBismuthinite) {
			genBismuthinite.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateTennantite) {
			genTennantite.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateTetrahedrite) {
			genTetrahedite.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateDioptase) {
			genDioptase.generateOres(world, chunkX, chunkZ, random);
		}
		if (generatePyrope) {
			genFluonicSapphire.generateOres(world, chunkX, chunkZ, random);
		}

		if (generateCopperMix) {
			genCopperMix.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateAgPb) {
			genAgPb.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateAgPbBi) {
			genAgPbBi.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateWSn) {
			genWSn.generateOres(world, chunkX, chunkZ, random);
		}
	}

	public void generatePlants(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		int y = world.getHeightValue(x, z);
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		if (y > world.getActualHeight()) {
			return;
		}

		if (random.nextInt(1000) <= Math.ceil(ThaumRevContent.getExcubituraModifier(world, x, y, z))) {
			genExcubitura.generate(world, random, x, y, z);
		}

		if (random.nextInt(40) == 0 && ThaumRevContent.isGoodClimate(biome, 0.15F, 1.0F, 0.25F, 2.0F)) {
			genCotton.generate(world, random, x, y, z);
		}

		if (random.nextInt(75) == 0 && ThaumRevContent.isGoodClimate(biome, 0.10F, 1.0F, 0.10F, 2.0F)) {
			genThistle.generate(world, random, x, y, z);
		}

		if (ThaumcraftWorldGenerator.getDimBlacklist(world.provider.dimensionId) == -1 && Config.genTrees && !world.getWorldInfo().getTerrainType().getWorldTypeName().startsWith("flat")) {
			if (biome.getEnableSnow() && biome.topBlock == Blocks.grass /*&& world.getBlock(x, y - 1, z) == Blocks.grass*/ && biome.temperature <= 0.0F && random.nextInt(30) == 0) {
				genShiverpearl.generate(world, random, x, y, z);
				ThaumicRevelations.logger.warn("Generating Shiverpearl at " + x + ", " + y + ", " + z);
			}
			if (y >= 96 && world.getBlock(x, y - 1, z) == Blocks.grass && random.nextInt(3) == 0) {
				genStormypearl.generate(world, random, x, y, z);
				ThaumicRevelations.logger.warn("Generating Stormypearl at " + x + ", " + y + ", " + z);
			}
			if (biome.temperature >= 0.0F && biome.temperature <= 0.5F && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MOUNTAIN) || BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.HILLS)) && world.canBlockSeeTheSky(x, y, z) && random.nextInt(10) == 0) {
				genStonypearl.generate(world, random, x, y, z);
				ThaumicRevelations.logger.warn("Generating Stonypearl at " + x + ", " + y + ", " + z);
			}
		}
	}

	public void generateNether(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (generateMillerite) {
			genMillerite.generateOres(world, chunkX, chunkZ, random);
		}
		if (generateIridosmium) {
			genIridosmium.generateOres(world, chunkX, chunkZ, random);
		}
		if (generatePyrope) {
			genPyrope.generateOres(world, chunkX, chunkZ, random);
		}
	}

	public void generateGravelOres(Random random, int xChunk, int zChunk, World world) {
		if (random == null) {
			return;
		}

		if (getGravel(generateGravelChalcocite) && random.nextInt(125) == 0) {
			genGravelChalcocite.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}
		if (getGravel(generateGravelSphalerite) && random.nextInt(145) == 0) {
			genGravelSphalerite.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}
		if (getGravel(generateGravelCassiterite) && random.nextInt(145) == 0) {
			genGravelCassiterite.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}
		if (getGravel(generateGravelMillerite) && random.nextInt(550) == 0) {
			genGravelMillerite.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}
		if (getGravel(generateGravelNativeSilver) && random.nextInt(475) == 0) {
			genGravelNativeSilver.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}
		if (getGravel(generateGravelGalena) && random.nextInt(450) == 0) {
			genGravelGalena.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}
		if (getGravel(generateGravelXenotime) && random.nextInt(1325) == 0) {
			genGravelXenotime.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}
		if (getGravel(generateGravelWolframite) && random.nextInt(1450) == 0) {
			genGravelWolframite.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}
		if (getGravel(generateGravelIridosmium) && random.nextInt(1725) == 0) {
			genGravelIridosmium.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}
		if (getGravel(generateGravelBismuthinite) && random.nextInt(150) == 0) {
			genGravelBismuthinite.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}
		if (getGravel(generateGravelTennantite) && random.nextInt(135) == 0) {
			genGravelTennantite.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}
		if (getGravel(generateGravelTetrahedrite) && random.nextInt(135) == 0) {
			genGravelTetrahedite.generate(world, random, xChunk + random.nextInt(16), 64 + ThaumRevConfig.seaLevel, zChunk + random.nextInt(16));
		}

	}

	public static boolean getPoor(int config) {
		return config == 0 || (config == 2 && LoadedHelper.isRailcraftLoaded && ThaumRevConfig.poorOre);
	}

	public static boolean getGravel(int config) {
		return config == 0 || (config == 2 && LoadedHelper.isTinkersLoaded && ThaumRevConfig.gravelOre);
	}
}
