package mortvana.melteddashboard.util;

import java.io.Serializable;
import java.util.*;

import mortvana.melteddashboard.util.helpers.science.MathHelper;

public class WeightedBlockList implements Serializable {

	protected List<WeightedBlock> blocks = new ArrayList<WeightedBlock>();

	public WeightedBlockList(WeightedBlock... blocks) {
		addBlocks(blocks);
	}

	public WeightedBlockList addBlocks(WeightedBlock... blocks) {
		Collections.addAll(this.blocks, blocks);
		return this;
	}

	public WeightedBlockList addBlock(WeightedBlock block) {
		blocks.add(block);
		return this;
	}

	public int getTotalWeight() {
		int chance = 0;
		for (WeightedBlock block : blocks) {
			chance += block.getWeight();
		}
		return chance;
	}

	public WeightedBlock getBlock() {
		int total = getTotalWeight();
		if (total != 0) {
			int val = MathHelper.RANDOM.nextInt(total);
			for (WeightedBlock block : blocks) {
				val -= block.getWeight();
				if (val >=0) {
					return block;
				}
			}

		}
		return null;
	}

	public List<WeightedBlock> getValues() {
		return blocks;
	}
}
