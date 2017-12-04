package mortvana.thaumrev.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

import mortvana.melteddashboard.util.WeightedBlock;
import mortvana.melteddashboard.util.WeightedBlockList;
import mortvana.melteddashboard.world.*;

import mortvana.thaumrev.util.ContentHelper;

import static net.minecraft.init.Blocks.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class ThaumRevWorldGenerator implements IWorldGenerator {

	public ThaumRevWorldGenerator() {
		init();
	}

	protected void init() {
		genExcubitura = new WorldGenPlant(blockThaumicPlant, 0, 16, 8);
		genCotton = new WorldGenPlant(blockThaumicPlant, 1, 96, 8);
		genThistle = new WorldGenPlant(blockThaumicPlant, 2);

		genChalcocite = new WorldGenOreVein(blockOre, 0, stone, 0, 10, 4, 35, 80);
		genSphalerite = new WorldGenOreVein(blockOre, 1, stone, 0, 9, 8, 20, 65);
		genCassiterite = new WorldGenOreVein(blockOre, 2, stone, 0, 9, 8, 15, 60);
		genMillerite = new WorldGenOreVein(blockOre, 3, netherrack, 0, 4, 3, 5, 125);
		genNativeSilver = new WorldGenOreVein(blockOre, 4, stone, 0, 7, 4, 5, 35);
		genGalena = new WorldGenOreVein(blockOre, 5, stone, 0, 8, 2, 10, 40);
		genXenotime = new WorldGenOreVein(blockOre, 6, stone, 0, 6, 4, 5, 30);
		genWolframite = new WorldGenOreVein(blockOre, 7, stone, 0, 2, 2, 5, 25);
		genIridosmium = new WorldGenOreVein(blockOre, 8, netherrack, 0, 6, 2, 5, 125);
		genBismuthinite = new WorldGenOreVein(blockOre, 9, stone, 0, 9, 2, 10, 55);
		genTennantite = new WorldGenOreVein(blockOre, 10, stone, 0, 6, 4, 30, 75);
		genTetrahedite = new WorldGenOreVein(blockOre, 11, stone, 0, 6, 4, 30, 75);
		genPyrope = new WorldGenOreVein(blockOre, 12, netherrack, 0, 4, 4, 5, 40);
		genDioptase = new WorldGenOreVein(blockOre, 13, stone, 0, 4, 2, 7, 11);
		genFluonicSapphire = new WorldGenOreVein(blockOre, 14, stone, 0, 4, 2, 7, 11);

		/*WeightedBlockList cu = new WeightedBlockList(new WeightedBlock(blockOre, 0, 30), new WeightedBlock(blockOre, 10, 35), new WeightedBlock(blockOre, 11, 35));
		genCopperMix = new WorldGenMixedOreVein(cu, stone, 0, 8, 12, 25, 70);
		WeightedBlockList pb = new WeightedBlockList(new WeightedBlock(blockOre, 4, 45), new WeightedBlock(blockOre, 5, 55));
		genAgPb = new WorldGenMixedOreVein(pb, stone, 0, 17, 4, 5, 35);
		WeightedBlockList bi = new WeightedBlockList(new WeightedBlock(blockOre, 9, 65), new WeightedBlock(blockOre, 5, 30), new WeightedBlock(blockOre, 4, 5));
		genAgPbBi = new WorldGenMixedOreVein(bi, stone, 0, 12, 4, 10, 40);
		WeightedBlockList w = new WeightedBlockList(new WeightedBlock(blockOre, 2, 40), new WeightedBlock(blockOre, 7, 60));
		genWSn = new WorldGenMixedOreVein(w, stone, 0, 8, 2, 10, 25);*/
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

	public void generateOres(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		genChalcocite.generateOres(world, chunkX, chunkZ, random);
		genSphalerite.generateOres(world, chunkX, chunkZ, random);
		genCassiterite.generateOres(world, chunkX, chunkZ, random);
		genNativeSilver.generateOres(world, chunkX, chunkZ, random);
		genGalena.generateOres(world, chunkX, chunkZ, random);
		genXenotime.generateOres(world, chunkX, chunkZ, random);
		genWolframite.generateOres(world, chunkX, chunkZ, random);
		genBismuthinite.generateOres(world, chunkX, chunkZ, random);
		genTennantite.generateOres(world, chunkX, chunkZ, random);
		genTetrahedite.generateOres(world, chunkX, chunkZ, random);
		genDioptase.generateOres(world, chunkX, chunkZ, random);
		genFluonicSapphire.generateOres(world, chunkX, chunkZ, random);

		/*genCopperMix.generateOres(world, chunkX, chunkZ, random);
		genAgPb.generateOres(world, chunkX, chunkZ, random);
		genAgPbBi.generateOres(world, chunkX, chunkZ, random);
		genWSn.generateOres(world, chunkX, chunkZ, random);*/
	}

	public void generatePlants(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		int y = world.getHeightValue(x, z);
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

		if (random.nextInt(1000) <= Math.ceil(ContentHelper.getExcubituraModifier(world, x, y, z))) {
			genExcubitura.generate(world, random, x, y, z);
		}

		if (random.nextInt(40) == 0 && ContentHelper.isGoodClimate(biome, 0.15F, 1.0F, 0.25F, 2.0F)) {
			genCotton.generate(world, random, x, y, z);
		}

		if (random.nextInt(75) == 0 && ContentHelper.isGoodClimate(biome, 0.10F, 1.0F, 0.10F, 2.0F)) {
			genThistle.generate(world, random, x, y, z);
		}
	}

	public void generateNether(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		genMillerite.generateOres(world, chunkX, chunkZ, random);
		genIridosmium.generateOres(world, chunkX, chunkZ, random);
		genPyrope.generateOres(world, chunkX, chunkZ, random);
	}
}
