/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 *
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mortvana.melteddashboard.world.poorore;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.google.common.collect.MapMaker;

/**
 *  Taken from Railcraft with permission from the licence for Poor Ore blocks.
 *  This class was originally abstract, but did not need to be, and who wants to make 12 classes which are just a constructor.
 *
 *  @author CovertJaguar <http://www.railcraft.info>
 */
public class PoorOreGenerator {

	protected final EventType eventType;
	protected final WorldGenerator oreGen;
	protected final double scale, denseArea, fringeArea;
	protected final int yLevel, yRange, noiseSeed;

	protected final Map<World, NoiseGen> noiseMap = new MapMaker().weakKeys().makeMap();

	public PoorOreGenerator(EventType eventType, int density, int yLevel, int yRange, int noiseSeed, Block ore, int metadata) {
		this(eventType, 0.0025, 0.85, 0.65, density, yLevel, yRange, noiseSeed, ore, metadata);
	}

	public PoorOreGenerator(EventType eventType, double scale, double denseArea, double fringeArea, int density, int yLevel, int yRange, int noiseSeed, Block ore, int metadata) {
		this.eventType = eventType;
		this.scale = scale;
		this.denseArea = denseArea;
		this.fringeArea = fringeArea;
		this.yLevel = yLevel;
		this.yRange = yRange;
		this.noiseSeed = noiseSeed;
		if (density >= 4) {
			oreGen = new WorldGenMinable(ore, metadata, density, Blocks.stone);
		} else {
			oreGen = new WorldGenSmallDeposits(ore, metadata, density, Blocks.stone);
		}
	}

	@SubscribeEvent
	public void generate(OreGenEvent.Post event) {
		World world = event.world;
		Random rand = event.rand;
		int worldX = event.worldX;
		int worldZ = event.worldZ;

		if (!TerrainGen.generateOre(world, rand, oreGen, worldX, worldZ, eventType)) {
			return;
		}

		NoiseGen noise = noiseMap.get(world);
		if (noise == null) {
			long seed = world.getSeed();
			seed += world.provider.dimensionId;
			seed += noiseSeed;
			noise = new NoiseGen.NoiseGenSimplex(new Random(seed), scale);
			noiseMap.put(world, noise);
		}

		if (canGen(world, rand, worldX, worldZ)) {
			for (int i = 0; i < 32; i++) {
				int x = worldX + rand.nextInt(16);
				int z = worldZ + rand.nextInt(16);
				double strength = noise.noise(x, z);
				if (strength > denseArea || (strength > fringeArea && rand.nextFloat() > 0.7)) {
					int y = yLevel + Math.round((float) rand.nextGaussian() * yRange);
					oreGen.generate(world, rand, x, y, z);
				}
			}
		}
	}

	protected boolean canGen(World world, Random rand, int x, int z) {
		return true;
	}
}
