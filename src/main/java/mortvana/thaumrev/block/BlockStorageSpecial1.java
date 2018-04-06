package mortvana.thaumrev.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.melteddashboard.block.FluxGearBlockBase;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockStorageSpecial1 extends FluxGearBlockBase {
	public BlockStorageSpecial1() {
		super(Material.iron);
		setStepSound(soundTypeMetal);
		setCreativeTab(thaumicRevelationsTab);
		setBlockName("thaumrev.storageSpecial1");

		setData(TEX_LOC_DEFAULT + "storage/", NAMES);
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

	public static final String[] NAMES = { "blockThaumicElectrum", "blockThaumicRiftishBronze", "blockSteel", "blockVoidbrass", "blockVoidsteel", "blockVoidtungsten", "blockWardenicBronze", "blockWardenicSteel", "blockWardenicRiftishBronze", "blockWardenicCrystal", "blockWardenicCrystalActivated", "blockWardenicComposite", "blockArcaneRedsolder", "blockRedbronze", "blockHardenedRedbronze", "blockFluxsteel" };
	public static final int[] MINE_LVL = { 2, 2, 2, 1, 2, 3, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2 };
	public static final float[] HARDNESS = { 6, 7, 8, 6, 10, 20, 8, 11, 10, 12, 16, 25, 5, 8, 10, 12 };
	public static final float[] RESISTANCE = { 8, 16, 13, 12, 25, 40, 12, 25, 24, 20, 30, 50, 6, 20, 25, 30 };
	public static final int[] LIGHT = { 6, 1, 0, 0, 0, 0, 0, 1, 1, 4, 8, 2, 2, 1, 0, 2 };
	public static final int[] RARITY = { 1, 1, 1, 1, 1, 2, 0, 1, 1, 2, 2, 2, 0, 0, 1, 1 };
}
