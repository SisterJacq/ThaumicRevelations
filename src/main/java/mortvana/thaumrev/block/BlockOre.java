package mortvana.thaumrev.block;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.melteddashboard.block.FluxGearBlockBase;
import mortvana.melteddashboard.util.helpers.ItemHelper;
import mortvana.melteddashboard.util.helpers.science.MathHelper;

import mortvana.thaumrev.util.RecipeHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockOre extends FluxGearBlockBase {

	public BlockOre() {
		super(Material.rock);
		setStepSound(soundTypeMetal);
		setCreativeTab(thaumicRevelationsTab);
		setBlockName("thaumrev.ore");

		setData(TEX_LOC_DEFAULT + "ore/", ORE_NAMES);

		setHardness(3.0F);
		setResistance(5.0F);

		setHarvestLevels();
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		switch (world.getBlockMetadata(x, y, z)) {
			case 4:
				return 4;
			case 6:
			case 9:
				return 2;
			case 12:
			case 13:
			case 14:
				return 8;
			default:
				return 0;
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();

		switch (meta) {
			case 12:
				list.add(ItemHelper.cloneStack(gemPyrope, getStackSize(fortune)));

			case 13:
				list.add(ItemHelper.cloneStack(gemDioptase, getStackSize(fortune)));

			case 14:
				list.add(ItemHelper.cloneStack(gemFluonicSapphire, getStackSize(fortune)));

			default:
				list.add(new ItemStack(this, 1, meta));
		}

		return list;
	}

	//Simplified form of the Diamond drop algorithm
	protected static int getStackSize(int fortune) {
		if (fortune == 0) {
			return 1;
		} else {
			return MathHelper.clampLowerInt(MathHelper.RANDOM.nextInt(fortune + 2) - 1, 0) + 1;
		}
	}

	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune) {
		if (meta == 12 || meta == 13 || meta == 14) {
			return MathHelper.RANDOM.nextInt(6) + 4;
		} else {
			return 0;
		}
	}

	public void setHarvestLevels() {
		for (int i = 0; i < HARVEST.length; i++) {
			setHarvestLevel("pickaxe", HARVEST[i], i);
		}
	}

	public static final int[] HARVEST = {1, 1, 1, 2, 2, 2, 2, 3, 3, 1, 1, 1, 3, 3, 3};
	public static final int[] RARITY = {0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 2, 2, 2};
}
