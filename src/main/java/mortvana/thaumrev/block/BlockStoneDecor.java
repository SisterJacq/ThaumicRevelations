package mortvana.thaumrev.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import mortvana.thaumrev.melteddashboard.block.FluxGearBlockBase;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockStoneDecor extends FluxGearBlockBase {

	public BlockStoneDecor() {
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(thaumicRevelationsTab);
		setBlockName("thaumrev.stonedecor");

		setHarvestLevel("pickaxe", 0);

		setHarvestLevel("pickaxe", 3, 0);
		setHarvestLevel("pickaxe", 2, 1);

		setData(TEX_LOC_DEFAULT, DECOR_STONE_NAMES);
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return HARDNESS[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		return RESISTANCE[world.getBlockMetadata(x, y, z)];
	}

	public static final float[] HARDNESS = new float[] { 25F, 10F };
	public static final float[] RESISTANCE = new float[] { 5000F, 500F };
	public static final int[] RARITY = new int[] { 2, 1 };
}
