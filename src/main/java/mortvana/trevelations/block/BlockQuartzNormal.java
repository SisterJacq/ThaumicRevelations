package mortvana.trevelations.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.trevelations.common.TRevelations;

public class BlockQuartzNormal extends Block {

	public IIcon topIcon;
	public IIcon botIcon;
	public IIcon sideIcon;

	public BlockQuartzNormal() {

		super(Material.rock);
		setBlockName("blockInfusedQuartzNormal");
		setCreativeTab(TRevelations.tabTRevelations);
		setStepSound(Block.soundTypeStone);
		setHardness(0.8F);

	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {

		topIcon = register.registerIcon("trevelations:infusedquartztop");
		botIcon = register.registerIcon("trevelations:infusedquartzbot");
		sideIcon = register.registerIcon("trevelations:infusedquartzside");

	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {

		if (side == 0) {

			return botIcon;

		} else
			if (side == 1) {

				return topIcon;

			} else {

				return sideIcon;

			}

	}

}
