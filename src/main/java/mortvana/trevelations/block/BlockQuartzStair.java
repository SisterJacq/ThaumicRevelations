package mortvana.trevelations.block;

import net.minecraft.block.BlockStairs;

import mortvana.trevelations.common.ModContent;
import mortvana.trevelations.common.TRevelations;

public class BlockQuartzStair extends BlockStairs {


	public BlockQuartzStair() {

		super(ModContent.blockInfusedQuartzNormal, 0);
		setBlockName("blockInfusedQuartzStair");
		setCreativeTab(TRevelations.tabTRevelations);
		setLightOpacity(0);

	}
}
