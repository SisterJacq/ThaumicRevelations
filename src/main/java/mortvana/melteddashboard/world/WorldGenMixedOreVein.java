package mortvana.melteddashboard.world;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import mortvana.melteddashboard.util.WeightedRandomBlock;

public class WorldGenMixedOreVein extends WorldGenerator {

	protected final List<WeightedRandomBlock> ores;
	protected final WeightedRandomBlock[] stones;
	protected final int size;
	protected int veins;
	protected int minY;
	protected int maxY;

	public WorldGenMixedOreVein(List<WeightedRandomBlock> resource, List<WeightedRandomBlock> block, int size, int veins, int minY, int maxY) {
		ores = resource;
		stones = block.toArray(new WeightedRandomBlock[block.size()]);
		this.size = size > 32 ? 32 : size;
		this.veins = veins;
		this.minY = minY;
		this.maxY = maxY;
	}

	public WorldGenMixedOreVein(List<WeightedRandomBlock> resource, Block block, int size, int veins, int minY, int maxY) {
		this(resource, makeList(block), size, veins, minY, maxY);
	}

	public WorldGenMixedOreVein(List<WeightedRandomBlock> resource, List<WeightedRandomBlock> block, int size) {
		this(resource, block, size, -1, 0, 255);
	}

	public WorldGenMixedOreVein(List<WeightedRandomBlock> resource, Block block, int size) {
		this(resource, makeList(block), size);
	}

	public WorldGenMixedOreVein(WeightedRandomBlock resource, Block block, int size) {
		this(makeList(resource), block, size);
	}

	public WorldGenMixedOreVein(ItemStack ore, Block block, int size) {
		this(new WeightedRandomBlock(ore, 1), block, size);
	}

	public WorldGenMixedOreVein(List<WeightedRandomBlock> ores, int size) {
		this(ores, Blocks.stone, size);
	}

	public WorldGenMixedOreVein(WeightedRandomBlock ore, int size) {
		this(makeList(ore), size);
	}

	public WorldGenMixedOreVein(ItemStack ore, int clusterSize) {
		this(new WeightedRandomBlock(ore), clusterSize);
	}

	public static final List<WeightedRandomBlock> makeList(WeightedRandomBlock resource) {
		List<WeightedRandomBlock> list = new ArrayList<WeightedRandomBlock>();
		list.add(resource);
		return list;
	}

	public static final List<WeightedRandomBlock> makeList(Block resource) {
		List<WeightedRandomBlock> list = new ArrayList<WeightedRandomBlock>();
		list.add(new WeightedRandomBlock(new ItemStack(resource, 1, 0)));
		return list;
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
		int blocks = size;
		if (blocks < 4) { // HACK: at 1 and 2 no ores are ever generated. at 3 only 1/3 veins generate
			return generateTiny(world, random, x, y, z);
		}
		float f = random.nextFloat() * (float) Math.PI;
		// despite naming, these are not exactly min/max. more like direction
		float xMin = x + 8 + (MathHelper.sin(f) * blocks) / 8F;
		float xMax = x + 8 - (MathHelper.sin(f) * blocks) / 8F;
		float zMin = z + 8 + (MathHelper.cos(f) * blocks) / 8F;
		float zMax = z + 8 - (MathHelper.cos(f) * blocks) / 8F;
		float yMin = (y + random.nextInt(3)) - 2;
		float yMax = (y + random.nextInt(3)) - 2;

		// optimization so this subtraction doesn't occur every time in the loop
		xMax -= xMin;
		yMax -= yMin;
		zMax -= zMin;

		boolean r = false;
		for (int i = 0; i <= blocks; i++) {

			float xCenter = xMin + (xMax * i) / blocks;
			float yCenter = yMin + (yMax * i) / blocks;
			float zCenter = zMin + (zMax * i) / blocks;

			// preserved as nextDouble to ensure the rand gets ticked the same amount
			float size = ((float) random.nextDouble() * blocks) / 16f;

			float hMod = ((MathHelper.sin((i * (float) Math.PI) / blocks) + 1f) * size + 1f) * .5f;
			float vMod = ((MathHelper.sin((i * (float) Math.PI) / blocks) + 1f) * size + 1f) * .5f;

			int xStart = MathHelper.floor_float(xCenter - hMod);
			int yStart = MathHelper.floor_float(yCenter - vMod);
			int zStart = MathHelper.floor_float(zCenter - hMod);

			int xStop = MathHelper.floor_float(xCenter + hMod);
			int yStop = MathHelper.floor_float(yCenter + vMod);
			int zStop = MathHelper.floor_float(zCenter + hMod);

			for (int blockX = xStart; blockX <= xStop; blockX++) {
				float xDistSq = ((blockX + .5f) - xCenter) / hMod;
				xDistSq *= xDistSq;
				if (xDistSq >= 1f) {
					continue;
				}

				for (int blockY = yStart; blockY <= yStop; blockY++) {
					float yDistSq = ((blockY + .5f) - yCenter) / vMod;
					yDistSq *= yDistSq;
					float xyDistSq = yDistSq + xDistSq;
					if (xyDistSq >= 1f) {
						continue;
					}

					for (int blockZ = zStart; blockZ <= zStop; blockZ++) {
						float zDistSq = ((blockZ + .5f) - zCenter) / hMod;
						zDistSq *= zDistSq;
						if (zDistSq + xyDistSq >= 1f) {
							continue;
						}

						r |= generateBlock(world, blockX, blockY, blockZ, stones, ores);
					}
				}
			}
		}

		return r;
	}

	public boolean generateTiny(World world, Random random, int x, int y, int z) {

		boolean r = false;
		// not <=; generating up to clusterSize blocks
		for (int i = 0; i < size; i++) {
			int d0 = x + random.nextInt(2);
			int d1 = y + random.nextInt(2);
			int d2 = z + random.nextInt(2);

			r |= generateBlock(world, d0, d1, d2, stones, ores);
		}
		return r;
	}

	public static boolean canGenerateInBlock(World world, int x, int y, int z, WeightedRandomBlock[] mat) {
		if (mat == null || mat.length == 0) {
			return true;
		}

		Block block = world.getBlock(x, y, z);
		for (int j = 0, e = mat.length; j < e; ++j) {
			WeightedRandomBlock genBlock = mat[j];
			if ((-1 == genBlock.meta || genBlock.meta == world.getBlockMetadata(x, y, z)) && (block.isReplaceableOreGen(world, x, y, z, genBlock.block) || block.isAssociatedBlock(genBlock.block))) {
				return true;
			}
		}
		return false;
	}

	public static boolean generateBlock(World world, int x, int y, int z, WeightedRandomBlock[] mat, List<WeightedRandomBlock> o) {
		if (mat == null || mat.length == 0) {
			return generateBlock(world, x, y, z, o);
		}

		if (canGenerateInBlock(world, x, y, z, mat)) {
			return generateBlock(world, x, y, z, o);
		}
		return false;
	}

	public static boolean generateBlock(World world, int x, int y, int z, List<WeightedRandomBlock> o) {
		WeightedRandomBlock ore = selectBlock(world, o);
		if (ore == null) {
			return false;
		}
		return world.setBlock(x, y, z, ore.block, ore.meta, 2);
	}

	public static WeightedRandomBlock selectBlock(World world, List<WeightedRandomBlock> o) {
		int size = o.size();
		if (size == 0) {
			return null;
		}
		if (size > 1) {
			return (WeightedRandomBlock) WeightedRandom.getRandomItem(world.rand, o);
		}
		return o.get(0);
	}
}
