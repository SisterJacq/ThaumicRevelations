package mortvana.thaumrev.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

import mortvana.melteddashboard.world.WorldGenPlant;

import mortvana.thaumrev.util.ContentHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class ThaumRevWorldGenerator implements IWorldGenerator {

	public ThaumRevWorldGenerator() {
		init();
	}

	protected void init() {
		genExcubitura = new WorldGenPlant(blockThaumicPlant, 0, 16, 8);
		genCotton = new WorldGenPlant(blockThaumicPlant, 1, 128, 8);
		genThistle = new WorldGenPlant(blockThaumicPlant, 2);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
			case -1:
				break;
			case 1:
				break;
			default:
				generateOres(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
				generatePlants(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}

	}

	public void generateOres(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {	}

	public void generatePlants(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		int y = world.getHeightValue(x, z);
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

		if (random.nextInt(1000) <= Math.ceil(ContentHelper.getExcubituraModifier(world, x, y, z))) {
			genExcubitura.generate(world, random, x, y, z);
		}

		if (random.nextInt(25) == 0 && ContentHelper.isGoodClimate(biome, 0.15F, 1.0F, 0.25F, 2.0F)) {
			genCotton.generate(world, random, x, y, z);
		}

		/*if (random.nextInt(40) == 0 && ContentHelper.isGoodClimate(biome, 0.10F, 1.0F, 0.10F, 2.0F)) {
			genThistle.generate(world, random, x, y, z);
		}*/
	}
}
