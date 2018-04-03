package mortvana.thaumrev.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.melteddashboard.block.FluxGearBlockBase;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockStorageAlloy1 extends FluxGearBlockBase {

	public BlockStorageAlloy1() {
		super(Material.iron);
		setStepSound(soundTypeMetal);
		setCreativeTab(thaumicRevelationsTab);
		setBlockName("thaumrev.storageMain");
		setData(TEX_LOC_DEFAULT + "storage/", STORAGE_ALLOY_NAMES_1);
		setHarvestLevels();
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return HARDNESS[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		return RESISTANCE[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return LIGHT[world.getBlockMetadata(x, y, z)];
	}

	public void setHarvestLevels() {
		for (int i = 0; i < MINE_LVL.length; i++) {
			setHarvestLevel("pickaxe", MINE_LVL[i], i);
		}
	}

	public static final int[] MINE_LVL = { 1, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 2, 2 };
	public static final float[] HARDNESS = { 5, 5, 5, 5, 5, 5, 6, 5, 6, 5, 5, 5, 5 };
	public static final float[] RESISTANCE = { 6, 7, 8, 10, 6, 6, 12, 6, 8, 6, 6, 6, 6 };
	public static final int[] LIGHT = { 0, 0, 1, 0, 0, 0, 1, 0, 0, 4, 0, 2, 2 };
	public static final int[] RARITY = { 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 };
}
