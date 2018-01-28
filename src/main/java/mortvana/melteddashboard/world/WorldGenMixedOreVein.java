package mortvana.melteddashboard.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import mortvana.melteddashboard.util.WeightedBlock;
import mortvana.melteddashboard.util.WeightedBlockList;
import mortvana.melteddashboard.util.helpers.science.MathHelper;

import mortvana.thaumrev.common.ThaumicRevelations;

public class WorldGenMixedOreVein extends WorldGenerator {

	protected WeightedBlockList ores;
	protected Block stone;
	protected int stoneMeta;
	protected int size;
	protected int veins;
	protected int minY;
	protected int maxY;

	public WorldGenMixedOreVein(WeightedBlockList ores, Block stone, int stoneMeta, int size, int veins, int minY, int maxY) {
		this.ores = ores;
		this.stone = stone;
		this.stoneMeta = stoneMeta;
		this.size = size;
		this.veins = veins;
		this.minY = minY;
		this.maxY = maxY;
	}

	public WorldGenMixedOreVein(WeightedBlockList ores, int size, int veins) {
		this(ores, Blocks.stone, 0, size, veins, 0, 255);
	}

	public WorldGenMixedOreVein(WeightedBlockList ores, int size) {
		this(ores, Blocks.stone, 0, size, 1, 0, 255);
	}

	public WorldGenMixedOreVein setOres(WeightedBlockList ores) {
		this.ores = ores;
		return this;
	}

	public WorldGenMixedOreVein setStone(Block stone) {
		this.stone = stone;
		return this;
	}

	public WorldGenMixedOreVein setStoneMeta(int stoneMeta) {
		this.stoneMeta = stoneMeta;
		return this;
	}

	public WorldGenMixedOreVein setSize(int size) {
		this.size = size;
		return this;
	}

	public WorldGenMixedOreVein setVeins(int veins) {
		this.veins = veins;
		return this;
	}

	public WorldGenMixedOreVein setMinY(int minY) {
		this.minY = minY;
		return this;
	}

	public WorldGenMixedOreVein setMaxY(int maxY) {
		this.maxY = maxY;
		return this;
	}

	public WorldGenMixedOreVein setConstraints(int minY, int maxY) {
		this.minY = minY;
		this.maxY = maxY;
		return this;
	}

	public void generateOres(World world, int chunkX, int chunkZ, Random random) {
		for (int i = 0; i < veins; i++) {
			int x = chunkX * 16 + random.nextInt(16);
			int y = minY + random.nextInt(maxY - minY);
			int z = chunkZ * 16 + random.nextInt(16);

			for (WeightedBlock block : ores.getValues()) {
				ThaumicRevelations.logger.debug(block.getBlock());
			}
			generate(world, random, x, y, z);
		}
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		float f = random.nextFloat() * (float) Math.PI;
		float s = (float) size / 8.0F;
		double baseX = (float) (x + 8) + (MathHelper.sin(f) * s);
		double addX = (float) (2.0F * MathHelper.sin(f) * s);
		double baseZ = (float) (z + 8) + (MathHelper.cos(f) * s);
		double addZ = (float) (2.0F * (MathHelper.cos(f) * s));
		double ya = (double) (y + random.nextInt(3) - 2);
		double yb = (double) (y + random.nextInt(3) - 2);

		for (int i = 0; i <= size; i++) {
			double mult = (double) i / (double) size;
			double valX = baseX + addX * mult;
			double valY = ya + (yb - ya) * mult;
			double valZ = baseZ + addZ * mult;
			double mod = (MathHelper.sin((float) i * (float) Math.PI / (float) size + 1.0F) * (random.nextDouble() * size / 16.0D) + 1.0D) / 2.0D;

			int x1 = (int) Math.floor(valX - mod);
			int y1 = (int) Math.floor(valY - mod);
			int z1 = (int) Math.floor(valZ - mod);
			int x2 = (int) Math.floor(valX + mod);
			int y2 = (int) Math.floor(valY + mod);
			int z2 = (int) Math.floor(valZ + mod);

			for (int a = x1; a <= x2; a++) {
				double x3 = ((double) a + 0.5D - valX) / mod;

				if (x3 * x3 < 1.0D) {
					for (int b = y1; b <= y2; b++) {
						double y3 = ((double) b + 0.5D - valY)/ mod;

						if (x3 * x3 + y3 * y3 < 1.0D) {
							for (int c = z1; c <= z2; c++) {
								double z3 = ((double) c + 0.5D - valZ) / mod;

								if (x3 * x3 + y3 * y3 + z3 * z3 < 1.0D && world.getBlock(a, b, c).isReplaceableOreGen(world, a, b, c, stone)) {
									WeightedBlock block = ores.getBlock();
									world.setBlock(a, b, c, block.getBlock(), block.getMeta(), 2);
								}
							}
						}
					}
				}
			}
		}

		return true;
	}
}
