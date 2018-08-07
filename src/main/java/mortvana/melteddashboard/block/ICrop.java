package mortvana.melteddashboard.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public interface ICrop extends IPlantable, IGrowable {

	int getStartGrowth(int meta);

	int getMaxGrowth(int meta);

	float getGrowthRate(World world, int x, int y, int z, int meta, int light);

	int requiredSun(int meta);

	/**
	 * 	Checks if a crop is considered crowded, not to be confused with checking to see if a crop is Crowded House.
	 *
	 * 	@param world - The world the block is in.
	 * 	@param x - The X coordinate
	 * 	@param y - The Y coordinate
	 * 	@param z - The Z coordinate
	 * 	@return - Is the crop considered crowded (not Crowded House, crops can't be Crowded House)
	 */
	boolean isCrowded(IBlockAccess world, int x, int y, int z);

	boolean isSameCrop(IBlockAccess world, int x, int y, int z, int meta);

	boolean isValidSoil(IBlockAccess world, int x, int y, int z);

	ArrayList<ItemStack> getProduce(IBlockAccess world, int x, int y, int z, int meta, int fortune);

	ItemStack getSeed(IBlockAccess world, int x, int y, int z, int meta, int fortune);

	ItemStack getSeedItem(int meta);

	void spawnProduce(World world, int x, int y, int z, EntityPlayer player, int meta);

	int getHarvestMeta(int meta);
}
