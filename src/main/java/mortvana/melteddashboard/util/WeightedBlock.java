package mortvana.melteddashboard.util;

import net.minecraft.block.Block;

public class WeightedBlock {

	protected Block block;
	protected int meta;
	protected int weight;

	public WeightedBlock(Block block, int meta, int weight) {
		this.block = block;
		this.meta = meta;
		this.weight = weight;
	}

	public WeightedBlock(Block block, int weight) {
		this(block, 0, weight);
	}

	public WeightedBlock setBlock(Block block) {
		this.block = block;
		return this;
	}

	public WeightedBlock setMetadata(int meta) {
		this.meta = meta;
		return this;
	}

	public WeightedBlock setWeight(int weight) {
		this.weight = weight;
		return this;
	}

	public Block getBlock() {
		return block;
	}

	public int getMeta() {
		return meta;
	}

	public int getWeight() {
		return weight;
	}
}
