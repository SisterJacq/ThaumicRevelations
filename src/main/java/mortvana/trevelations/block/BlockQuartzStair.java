package mortvana.trevelations.block;

import mortvana.trevelations.common.ModContent;
import mortvana.trevelations.common.TRevelations;
import net.minecraft.block.BlockStairs;

public class BlockQuartzStair extends BlockStairs {


    public BlockQuartzStair() {

        super(ModContent.blockInfusedQuartzNormal, 0);
        setBlockName("blockInfusedQuartzStair");
        setCreativeTab(TRevelations.tabTRevelations);
        setLightOpacity(0);

    }
}
