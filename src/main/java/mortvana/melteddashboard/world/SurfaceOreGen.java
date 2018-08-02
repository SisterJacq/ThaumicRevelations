package mortvana.melteddashboard.world;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.common.BiomeDictionary;

import mortvana.melteddashboard.util.helpers.science.MathHelper;

import mortvana.thaumrev.common.ThaumRevConfig;

public class SurfaceOreGen extends WorldGenerator {

	/** The block of the ore to be placed using this generator. */
	public Block minableBlock;
	public int minableBlockMeta = 0;

	/** The number of blocks to generate. */
	public int numberOfBlocks;
	public Block[] replaceBlocks;
	public boolean alterSize;

	public static List<Block> sourceDefault = new ArrayList<Block>();
	public static List<Block> sourceMountain = new ArrayList<Block>();
	public static List<Block> replaceDefault = new ArrayList<Block>();


	static {
		sourceDefault.add(Blocks.grass);
		sourceDefault.add(Blocks.dirt);
		sourceMountain.add(Blocks.stone);
		sourceMountain.add(Blocks.gravel);
		replaceDefault.add(Blocks.stone);
		replaceDefault.add(Blocks.grass);
		replaceDefault.add(Blocks.dirt);
		replaceDefault.add(Blocks.water);
		replaceDefault.add(Blocks.sand);
		replaceDefault.add(Blocks.gravel);
		replaceDefault.add(Blocks.snow);
	}

	public SurfaceOreGen(Block block, int meta, int number, boolean changeSize) {
		this(block, meta, number, changeSize, getReplaceable());
	}

	public SurfaceOreGen(Block block, int meta, int number, boolean changeSize, Block... target) {
		minableBlock = block;
		numberOfBlocks = number;
		replaceBlocks = target;
		alterSize = changeSize;
		minableBlockMeta = meta;
	}

	public int findGround (World world, int x, int y, int z) {
		boolean mount = BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(x / 16, z / 16), BiomeDictionary.Type.MOUNTAIN);
		int returnHeight = -1;
		Block block = world.getBlock(x, y - 1, z);
		if (!world.getBlock(x, y, z).isOpaqueCube() && (sourceDefault.contains(block) || (mount && sourceMountain.contains(block)))) {
			return y;
		}
		int height = ThaumRevConfig.seaLevel + 64;
		do {
			if (height < ThaumRevConfig.seaLevel - 30) {
				break;
			}
			Block b = world.getBlock(x, height, z);
			if (sourceDefault.contains(b) || (mount && sourceMountain.contains(b))) {
				if (!world.getBlock(x, height + 1, z).isOpaqueCube()) {
					returnHeight = height + 1;
				}
				break;
			}
			height--;
		} while (height > 0);
		return returnHeight;
	}

	@Override
	public boolean generate (World world, Random random, int startX, int startY, int startZ) {
		if (alterSize) {
			startY = findGround(world, startX, startY, startZ);
			if (startY == -1) {
				return false;
			}
		}

		float f = random.nextFloat() * (float) Math.PI;
		int blockNumber = numberOfBlocks;
		if (alterSize) {
			blockNumber = numberOfBlocks * 2 / 5 + random.nextInt(numberOfBlocks * 3 / 5);
		}
		double xPlus = ((float) (startX + 8) + MathHelper.sin(f) * (float) blockNumber / 8.0F);
		double xMinus = ((float) (startX + 8) - MathHelper.sin(f) * (float) blockNumber / 8.0F);
		double zPlus = ((float) (startZ + 8) + MathHelper.cos(f) * (float) blockNumber / 8.0F);
		double zMinus = ((float) (startZ + 8) - MathHelper.cos(f) * (float) blockNumber / 8.0F);
		double yPlus = (double) (startY + random.nextInt(3) - 2);
		double yMinus = (double) (startY + random.nextInt(3) - 2);

		for (int i = 0; i <= blockNumber; i++) {
			double mult = (double) i / (double) blockNumber;
			double valX = xPlus + (xMinus - xPlus) * mult;
			double valY = yPlus + (yMinus - yPlus) * mult;
			double valZ = zPlus + (zMinus - zPlus) * mult;
			double mod = (MathHelper.sin((float) i * (float) Math.PI / (float) blockNumber) + 1.0F) * (random.nextDouble() * (double) blockNumber / 16.0D) + 1.0D;

			int x0 = (int) Math.floor(valX - mod / 2.0D);
			int y0 = (int) Math.floor(valY - mod / 2.0D);
			int z0 = (int) Math.floor(valZ - mod / 2.0D);
			int x1 = (int) Math.floor(valX + mod / 2.0D);
			int y1 = (int) Math.floor(valY + mod / 2.0D);
			int z1 = (int) Math.floor(valZ + mod / 2.0D);

			for (int a = x0; a <= x1; a++) {
				double x2 = ((double) a + 0.5D - valX) / (mod / 2.0D);

				if (x2 * x2 < 1.0D) {
					for (int b = y0; b <= y1; b++) {
						double y2 = ((double) b + 0.5D - valY) / (mod / 2.0D);

						if (x2 * x2 + y2 * y2 < 1.0D) {
							for (int c = z0; c <= z1; c++) {
								double z2 = ((double) c + 0.5D - valZ) / (mod / 2.0D);

								Block block = world.getBlock(a, b, c);
								if (x2 * x2 + y2 * y2 + z2 * z2 < 1.0D) {
									if (block == null || !world.getBlock(a, b, c).isOpaqueCube()) {
										world.setBlock(a, b, c, minableBlock, minableBlockMeta, 2);
									} else {
										for (Block replace : replaceBlocks) {
											if (world.getBlock(a, b, c).isReplaceableOreGen(world, a, b, c, replace)) {
												world.setBlock(a, b, c, minableBlock, minableBlockMeta, 2);
												break;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	public static Block[] getReplaceable() {
		Block[] ret = new Block[replaceDefault.size()];
		replaceDefault.toArray(ret);
		return ret;
	}
}