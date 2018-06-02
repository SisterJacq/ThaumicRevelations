package mortvana.thaumrev.block;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.melteddashboard.block.FluxGearBlockBase;
import mortvana.melteddashboard.lib.StringLibrary;
import mortvana.melteddashboard.util.helpers.ItemHelper;
import mortvana.melteddashboard.util.helpers.science.MathHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockPoorOre extends FluxGearBlockBase {

	public BlockPoorOre() {
		super(Material.rock);
		setStepSound(soundTypeMetal);
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
			default:
				return 0;
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fluxgear.orePoor." + NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public int getRarity(int meta) {
		return RARITY[meta];
	}

	public static final String[] NAMES = { "oreChalcocite", "oreSphalerite", "oreCassiterite", "oreMillerite", "oreNativeSilver", "oreGalena", "oreXenotime", "oreWolframite", "oreIridosmium", "oreBismuthinite", "oreTennantite", "oreTetrahedrite" };
	public static final int[] HARVEST = {1, 1, 1, 2, 2, 2, 2, 3, 3, 1, 1, 1};
	public static final int[] RARITY = {0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0};
}