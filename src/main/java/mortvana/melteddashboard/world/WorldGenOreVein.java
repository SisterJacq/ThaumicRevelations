package mortvana.melteddashboard.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import mortvana.melteddashboard.util.helpers.science.MathHelper;

public class WorldGenOreVein extends WorldGenerator {

	protected Block ore;
	protected int meta;
	protected Block stone;
	protected int stoneMeta;
	protected int size;
	protected int veins;
	protected int minY;
	protected int maxY;

	public WorldGenOreVein(Block ore, int meta, Block stone, int stoneMeta, int size, int veins, int minY, int maxY) {
		this.ore = ore;
		this.meta = meta;
		this.stone = stone;
		this.stoneMeta = stoneMeta;
		this.size = size;
		this.veins = veins;
		this.minY = minY;
		this.maxY = maxY;
	}

	public WorldGenOreVein(Block ore, int meta, int size, int veins) {
		this(ore, meta, Blocks.stone, 0, size, veins, 0, 255);
	}

	public WorldGenOreVein(Block ore, int meta, int size) {
		this(ore, meta, Blocks.stone, 0, size, 1, 0, 255);
	}

	public WorldGenOreVein setOre(Block ore) {
		this.ore = ore;
		return this;
	}

	public WorldGenOreVein setMeta(int meta) {
		this.meta = meta;
		return this;
	}

	public WorldGenOreVein setStone(Block stone) {
		this.stone = stone;
		return this;
	}

	public WorldGenOreVein setStoneMeta(int stoneMeta) {
		this.stoneMeta = stoneMeta;
		return this;
	}

	public WorldGenOreVein setSize(int size) {
		this.size = size;
		return this;
	}

	public WorldGenOreVein setVeins(int veins) {
		this.veins = veins;
		return this;
	}

	public WorldGenOreVein setMinY(int minY) {
		this.minY = minY;
		return this;
	}

	public WorldGenOreVein setMaxY(int maxY) {
		this.maxY = maxY;
		return this;
	}

	public WorldGenOreVein setConstraints(int minY, int maxY) {
		this.minY = minY;
		this.maxY = maxY;
		return this;
	}

	public void generateOres(World world, int chunkX, int chunkZ, Random random) {
		for (int i = 0; i < veins; i++) {
			int x = chunkX * 16 + random.nextInt(16);
			int y = minY + random.nextInt(maxY - minY);
			int z = chunkZ * 16 + random.nextInt(16);

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

			int x0 = (int) Math.floor(valX - mod);
			int y0 = (int) Math.floor(valY - mod);
			int z0 = (int) Math.floor(valZ - mod);
			int x1 = (int) Math.floor(valX + mod);
			int y1 = (int) Math.floor(valY + mod);
			int z1 = (int) Math.floor(valZ + mod);

			for (int a = x0; a <= x1; a++) {
				double x2 = ((double) a + 0.5D - valX) / mod;

				if (x2 * x2 < 1.0D) {
					for (int b = y0; b <= y1; b++) {
						double y2 = ((double) b + 0.5D - valY)/ mod;

						if (x2 * x2 + y2 * y2 < 1.0D) {
							for (int c = z0; c <= z1; c++) {
								double z2 = ((double) c + 0.5D - valZ) / mod;

								if (x2 * x2 + y2 * y2 + z2 * z2 < 1.0D && world.getBlock(a, b, c).isReplaceableOreGen(world, a, b, c, stone)) {
									world.setBlock(a, b, c, ore, meta, 2);
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
