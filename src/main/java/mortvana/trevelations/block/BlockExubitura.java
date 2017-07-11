package mortvana.trevelations.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import mortvana.trevelations.common.ModContent;
import mortvana.trevelations.common.TRevelations;

public class BlockExubitura extends BlockBush {

	public BlockExubitura() {

		super(Material.plants);
		setBlockName("blockExubitura");
		setCreativeTab(TRevelations.tabTRevelations);
		setStepSound(Block.soundTypeGrass);

	}

	@Override
	public Item getItemDropped(int par1, Random random, int par2) {

		return ModContent.itemResource;

	}

	@Override
	public int damageDropped(int par1) {

		return 0;

	}

	@Override
	public boolean canBlockStay(World p_149718_1_, int x, int y, int z) {return true;}

	@Override
	public void registerBlockIcons(IIconRegister register) {

		blockIcon = register.registerIcon("trevelations:exubitura");

	}

}
