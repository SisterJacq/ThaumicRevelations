package mortvana.thaumrev.block;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.block.FluxGearBlockFalling;
import mortvana.melteddashboard.util.libraries.StringLibrary;

import static mortvana.thaumrev.library.ThaumRevLibrary.generalTab;

public class BlockGravelOre extends FluxGearBlockFalling {
	
	public BlockGravelOre() {
		super(Material.rock);
		setStepSound(soundTypeMetal);
		setCreativeTab(generalTab);
		setBlockName("thaumrev.ore");

		setData(StringLibrary.DIR_DEFAULT  + "ore/", NAMES);

		setHardness(3.0F);
		setResistance(5.0F);

		for (int i = 0; i < HARVEST.length; i++) {
			setHarvestLevel("shovel", HARVEST[i], i);
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
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fluxgear.ore." + NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public int getRarity(int meta) {
		return RARITY[meta];
	}

	public static final String[] NAMES = { "oreGravelChalcocite", "oreGravelSphalerite", "oreGravelCassiterite", "oreGravelMillerite", "oreGravelNativeSilver", "oreGravelGalena", "oreGravelXenotime", "oreGravelWolframite", "oreGravelIridosmium", "oreGravelBismuthinite", "oreGravelTennantite", "oreGravelTetrahedrite" };
	public static final int[] HARVEST = {1, 1, 1, 2, 2, 2, 2, 3, 3, 1, 1, 1};
	public static final int[] RARITY = {0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0};
}
