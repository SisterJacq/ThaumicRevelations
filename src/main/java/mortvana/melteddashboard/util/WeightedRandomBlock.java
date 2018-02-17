package mortvana.melteddashboard.util;

import java.util.Collection;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;

/*
 *	The same thing as WeightRandomBlock from CoFHLib
 */
public class WeightedRandomBlock extends WeightedRandom.Item{

	public final Block block;
	public final int meta;

	public WeightedRandomBlock(Block block, int meta, int weight) {
		super(weight);
		this.block = block;
		this.meta = meta;
	}

	public WeightedRandomBlock(Block block, int meta) {
		this(block, meta, 100);
	}

	public WeightedRandomBlock(Block ore) {
		this(ore, 0, 100); // some blocks do not have associated items
	}

	public WeightedRandomBlock(ItemStack stack, int weight) {
		this(Block.getBlockFromItem(stack.getItem()), stack.getItemDamage(), weight);
	}

	public WeightedRandomBlock(ItemStack stack) {
		this(stack, 100);
	}

	public static boolean isBlockContained(Block block, int meta, Collection<WeightedRandomBlock> list) {
		for (WeightedRandomBlock rb : list) {
			if (block.equals(rb.block) && (meta == -1 || rb.meta == -1 || rb.meta == meta)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isBlockContained(Block block, int meta, WeightedRandomBlock[] list) {
		for (WeightedRandomBlock rb : list) {
			if (block.equals(rb.block) && (meta == -1 || rb.meta == -1 || rb.meta == meta)) {
				return true;
			}
		}
		return false;
	}

	public Block getBlock() {
		return block;
	}

	public int getMeta() {
		return meta;
	}

	public int getWeight() {
		return itemWeight;
	}
}
