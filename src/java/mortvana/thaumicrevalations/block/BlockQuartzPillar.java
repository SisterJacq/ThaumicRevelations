package mortvana.thaumicrevalations.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.thaumicrevalations.TRevalations;
import mortvana.thaumicrevalations.lib.BlockLib;
import mortvana.thaumicrevalations.lib.ModLib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockQuartzPillar extends BlockRotatedPillar {

    public IIcon topIcon;
    public IIcon sideIcon;

    protected BlockQuartzPillar() {

        super(Material.rock);
        setBlockName(BlockLib.QUARTZ_PILLAR_NAME);
        setCreativeTab(TRevalations.tabTWarden);
        setStepSound(Block.soundTypeStone);
        setHardness(0.8F);

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {

        topIcon = register.registerIcon(ModLib.ID.toLowerCase() + ":" + "infusedquartzpillartop" );
        sideIcon = register.registerIcon(ModLib.ID.toLowerCase() + ":" + "infusedquartzpillarside" );

    }

    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int par) {

        return sideIcon;

    }

    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int par) {

        return topIcon;

    }

}
