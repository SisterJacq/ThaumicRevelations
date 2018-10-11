package mortvana.thaumrev.block;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.melteddashboard.block.FluxGearBlockBase;
import mortvana.melteddashboard.util.libraries.StringLibrary;
import mortvana.melteddashboard.util.helpers.ItemHelper;
import mortvana.melteddashboard.util.helpers.science.MathHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockOre extends FluxGearBlockBase {

	public BlockOre() {
		super(Material.rock);
		setStepSound(soundTypeGravel);
		setCreativeTab(generalTab);
		setBlockName("thaumrev.ore");

		setData(StringLibrary.DIR_DEFAULT  + "ore/", NAMES);

		setHardness(3.0F);
		setResistance(5.0F);

		for (int i = 0; i < HARVEST.length; i++) {
			setHarvestLevel("pickaxe", HARVEST[i], i);
		}
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
				break;
			case 13:
				list.add(ItemHelper.cloneStack(gemDioptase, getStackSize(fortune)));
				break;
			case 14:
				list.add(ItemHelper.cloneStack(gemFluonicSapphire, getStackSize(fortune)));
				break;
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

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fluxgear.ore." + NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public int getRarity(int meta) {
		return RARITY[meta];
	}

	public static final String[] NAMES = { "oreChalcocite", "oreSphalerite", "oreCassiterite", "oreMillerite", "oreNativeSilver", "oreGalena", "oreXenotime", "oreWolframite", "oreIridosmium", "oreBismuthinite", "oreTennantite", "oreTetrahedrite", "orePyrope", "oreDioptase", "oreFluonicSapphire" };
	public static final int[] HARVEST = {1, 1, 1, 2, 2, 2, 2, 3, 3, 1, 1, 1, 3, 3, 3};
	public static final int[] RARITY = {0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 2, 2, 2};
}
