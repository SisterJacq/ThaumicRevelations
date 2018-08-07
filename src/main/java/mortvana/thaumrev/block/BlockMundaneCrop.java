package mortvana.thaumrev.block;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.melteddashboard.block.FluxGearBlockCrop;
import mortvana.melteddashboard.util.libraries.StringLibrary;
import mortvana.melteddashboard.util.helpers.ItemHelper;
import mortvana.melteddashboard.util.helpers.science.MathHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockMundaneCrop extends FluxGearBlockCrop {

	public BlockMundaneCrop() {
		super();
		setBlockName("thaumrev.cropMundane");
		setDirectory(StringLibrary.DIR_DEFAULT + "plant/");
		setNames("cotton", "thistle");
		setTextures("cotton_0", "cotton_1", "cotton_2", "cotton_3", "cotton_4", "cotton_5", "cotton_6", "cotton_7", "thistle_0", "thistle_1", "thistle_2", "thistle_3", "thistle_4", "thistle_5", "thistle_6", "thistle_7");
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		int i = world.getBlockMetadata(x, y, z);
		return (i > 3 && i < 8) || i > 11 ? 0.5F : 0.0F;
	}

	@Override
	public ArrayList<ItemStack> getProduce(IBlockAccess world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		switch (meta) {
			case 7:
				list.add(ItemHelper.cloneStack(itemCotton, 2 + MathHelper.random(2)));
				break;
			case 15:
				list.add(ItemHelper.cloneStack(itemThistleLeaf, 2 + MathHelper.random(3)));
				list.add(itemThistleFlower.copy());
				break;
		}
		return list;
	}

	@Override
	public ItemStack getSeed(IBlockAccess world, int x, int y, int z, int meta, int fortune) {
		int i = 1;
		switch (meta) {
			case 4:
			case 5:
			case 12:
			case 13:
				i += ((int) (((float) MathHelper.random(5)) / 4.0F));
				break;
			case 6:
			case 14:
				i += ((int) (((float) MathHelper.random(3)) / 2.0F));
				break;
			case 7:
			case 15:
				i += ((int) (((float) MathHelper.random(4 + fortune)) + 1 / 2.0F));
				break;
		}
		return ItemHelper.cloneStack(getSeedItem(meta), i);
	}

	@Override
	public ItemStack getSeedItem(int meta) {
		return meta < 8 ? seedCotton : seedThistle;
	}

	@Override
	public int getHarvestMeta(int meta) {
		return meta < 8 ? 4 : 8;
	}
}
