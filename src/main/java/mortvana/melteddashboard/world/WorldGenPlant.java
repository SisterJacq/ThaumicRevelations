package mortvana.melteddashboard.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import mortvana.melteddashboard.util.helpers.science.MathHelper;

public class WorldGenPlant extends WorldGenerator {

	protected Block block;
	protected int meta;
	protected int tries;
	protected int range;

	public WorldGenPlant(Block block, int meta, int tries, int range) {
		this.block = block;
		this.meta = meta;
		this.tries = tries;
		this.range = range;
	}

	public WorldGenPlant(Block block, int meta) {
		this(block, meta, 64, 8);
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		for (int i = 0; i < tries; i++) {
			int tryX = x + MathHelper.getOffsetInt(range);
			int tryY = y + MathHelper.getOffsetInt(range / 2);
			int tryZ = z + MathHelper.getOffsetInt(range);

			if (world.isAirBlock(tryX, tryY, tryZ) && (!world.provider.hasNoSky || tryY < 255) && block.canBlockStay(world, tryX, tryY, tryZ)) {
				world.setBlock(tryX, tryY, tryZ, block, meta, 3);
			}
		}

		return true;
	}
}
