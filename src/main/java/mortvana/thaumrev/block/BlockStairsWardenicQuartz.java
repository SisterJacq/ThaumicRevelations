package mortvana.thaumrev.block;

import net.minecraft.block.BlockStairs;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockStairsWardenicQuartz extends BlockStairs {

	public BlockStairsWardenicQuartz() {
		super(blockStoneDecor, 2);
		setBlockName("thaumrev.blockWardenicQuartzStair");
		setCreativeTab(generalTab);
		setLightOpacity(0);
	}
}
