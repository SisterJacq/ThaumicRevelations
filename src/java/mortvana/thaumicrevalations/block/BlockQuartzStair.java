package mortvana.thaumicrevalations.block;

import mortvana.thaumicrevalations.TRevalations;
import mortvana.thaumicrevalations.lib.BlockLib;
import net.minecraft.block.BlockStairs;

public class BlockQuartzStair extends BlockStairs {


    protected BlockQuartzStair() {

        super(ModBlocks.blockInfusedQuartzNormal, 0);
        setBlockName(BlockLib.QUARTZ_STAIR_NAME);
        setCreativeTab(TRevalations.tabTWarden);
        setLightOpacity(0);

    }
}
