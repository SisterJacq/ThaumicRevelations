package mortvana.thaumicrevelations.warden.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import static mortvana.thaumicrevelations.library.ThaumicLibrary.*;

public class ExcubituraGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int x = chunkX * 16 + random.nextInt(128);
		int z = chunkZ * 16 + random.nextInt(128);
		int y = world.getHeightValue(x, z);

		if (world.isAirBlock(x, y, z) && blockThaumicPlant.canBlockStay(world, x, y, z) && random.nextInt(1000) <= Math.ceil(getModifier(world, x, y, z))) {
			world.setBlock(x, y, z, blockThaumicPlant, 8, 2);
		}
	}

	/**
	 *
	 *
	 *  @param world - World they plant is generating in...
	 *  @param x - X coordinate, duh.
	 *  @param y - Y coordinate, duh.
	 *  @param z - Z coordinate, duh.
	 *  @return
	 */
	public double getModifier(World world, int x, int y, int z) {
		double modifier = 5F;
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		// First off, these guys don't grow in the End, Nether, or Mushroom Biomes. They also won't grow in dead places and wastelands.
		if (BiomeDictionary.isBiomeOfType(biome, Type.END) || BiomeDictionary.isBiomeOfType(biome, Type.NETHER) || BiomeDictionary.isBiomeOfType(biome, Type.MUSHROOM) ||
				BiomeDictionary.isBiomeOfType(biome, Type.DEAD) || BiomeDictionary.isBiomeOfType(biome, Type.WASTELAND)) {
			return 0F;
		}

		// Modify the modifier for temperature.
		float temp = biome.getFloatTemperature(x, y, z);
		if (temp < 0.35F) {
			modifier -= (0.70F - (temp * 2));
		} else if (temp > 0.75F) {
			modifier -= (temp - 0.75F);
		}

		// They don't like snow...
		if (biome.getEnableSnow()) {
			modifier /= 8;
		}

		// Modify the modifier for rainfall.
		float rain = biome.rainfall;
		if (rain < 0.2F) {
			modifier -= (2F - (rain * 10));
		} else if (rain < 0.35F) {
			modifier -= (1.4F - (rain * 4));
		} else if (rain > 0.85F) {
			modifier -= ((2 * rain) - 1.70F);
		} else if (rain > 0.75F) {
			modifier -= (rain - .75F);
		}

		// They really don't like being dried out or drenched...
		if (rain > 0.1F || rain < 0.95F) {
			modifier = modifier / 10;
		}

		// They like moderate temperatures
		if (BiomeDictionary.isBiomeOfType(biome, Type.HOT) || BiomeDictionary.isBiomeOfType(biome, Type.COLD)) {
			modifier *= 0.75F;
		} else {
			modifier *= 1.1F;
		}

		// They like a decent amount of water
		if (BiomeDictionary.isBiomeOfType(biome, Type.DRY)) {
			modifier *= 0.75;
		} else if (!BiomeDictionary.isBiomeOfType(biome, Type.WET)){
			modifier *= 1.1F;
		}

		// They are just like regular plants...
		if (BiomeDictionary.isBiomeOfType(biome, Type.SPARSE)) {
			modifier *= 0.75F;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.LUSH)) {
			modifier *= 1.65F;
		}

		// They dislike jungles, and aren't fond of savannas. They do like coniferous forests though.
		if (BiomeDictionary.isBiomeOfType(biome, Type.JUNGLE)) {
			modifier *= 0.5F;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.SAVANNA)) {
			modifier *= 0.75F;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.CONIFEROUS)) {
			modifier *= 1.25F;
		}

		// They dislike saltwater, but love a good stream!
		if (BiomeDictionary.isBiomeOfType(biome, Type.BEACH) || BiomeDictionary.isBiomeOfType(biome, Type.OCEAN)) {
			modifier *= .625F;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.RIVER)) { //Something about the planet Miranda...
			modifier *= 1.375F;
		}

		// No snow! Even bigger no to deserts!
		if (BiomeDictionary.isBiomeOfType(biome, Type.SNOWY)) {
			modifier /= 4;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.SANDY)) {
			modifier /= 8;
		}

		// Take your sorry ass back to Florida...
		if (BiomeDictionary.isBiomeOfType(biome, Type.SWAMP)) {
			modifier *= 0.1875F;
		}

		// They do like some clay in the soil...
		if (BiomeDictionary.isBiomeOfType(biome, Type.MESA)) {
			modifier *= 1.05F;
		}

		// Plains are good, Forests are better!
		if (BiomeDictionary.isBiomeOfType(biome, Type.PLAINS)) {
			modifier *= 1.125F;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.FOREST)) {
			modifier *= 1.75F;
		}

		// Mountains are good, but they prefer big hills.
		if (BiomeDictionary.isBiomeOfType(biome, Type.MOUNTAIN)) {
			modifier *= 1.25F;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.HILLS)) {
			modifier *= 1.5F;
		}

		// Use Botania for the lushest hair possible. Trust me, I'm a wereporcupine.
		if (BiomeDictionary.isBiomeOfType(biome, Type.LUSH)) {
			modifier *= 2;
		}

		// 3 FOR THE PRICE OF 2 IF YOU WANT TO BELIEVE...
		if (BiomeDictionary.isBiomeOfType(biome, Type.SPOOKY)) {
			modifier *= 1.5F;
		}

		// They are a thaumic rose. They like magical biomes.
		if (BiomeDictionary.isBiomeOfType(biome, Type.MAGICAL)) {
			modifier *= 10;
		}

		return modifier;
	}


}