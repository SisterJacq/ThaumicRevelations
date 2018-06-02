package mortvana.melteddashboard.util.helpers;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldHelper {

    public static World getWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    public static EntityPlayer getPlayer() {
        return FMLClientHandler.instance().getClient().thePlayer;
    }

    public static Block getBlock(IBlockAccess world, int x, int y, int z) {
        return world.getBlock(x, y, z);
    }

    public static int getXOnSide(int x, ForgeDirection side) {
        return x + side.offsetX;
    }

    public static int getYOnSide(int y, ForgeDirection side) {
        return y + side.offsetY;
    }

    public static int getZOnSide(int z, ForgeDirection side) {
        return z + side.offsetZ;
    }

	public static int getSunlight(World world, int x, int y, int z) {
		return !world.provider.hasNoSky ? (15 - world.skylightSubtracted) : 0;
	}

    /* *=-=-=-=* Tile Entity Retrieval *=-=-=-=* */
    public static TileEntity getAdjacentTileEntity(World world, int x, int y, int z, ForgeDirection direction) {
        x = getXOnSide(x, direction);
        y = getYOnSide(y, direction);
        z = getZOnSide(z, direction);

        return world == null || !world.blockExists(x, y, z) ? null : world.getTileEntity(x, y, z);
    }

    public static TileEntity getAdjacentTileEntity(TileEntity tile, ForgeDirection direction) {
        return tile == null ? null : getAdjacentTileEntity(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord, direction);
    }


    public static TileEntity getAdjacentTileEntity(TileEntity tile, int side) {
        return tile == null ? null : getAdjacentTileEntity(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord, ForgeDirection.values()[side]);
    }

    public static boolean isBlockAdjacent(int offsetX, int offsetY, int offsetZ, ForgeDirection side) {
        return offsetX == side.offsetX && offsetY == side.offsetY && offsetZ == side.offsetZ;
    }
}
