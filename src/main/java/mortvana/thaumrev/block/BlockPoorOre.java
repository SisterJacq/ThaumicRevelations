package mortvana.thaumrev.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.block.FluxGearBlockBase;
import mortvana.melteddashboard.util.libraries.StringLibrary;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockPoorOre extends FluxGearBlockBase {

	public BlockPoorOre() {
		super(Material.rock);
		setStepSound(soundTypeMetal);
		setCreativeTab(generalTab);
		setBlockName("thaumrev.orePoor");

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

	public static final String[] NAMES = { "orePoorChalcocite", "orePoorSphalerite", "orePoorCassiterite", "orePoorMillerite", "orePoorNativeSilver", "orePoorGalena", "orePoorXenotime", "orePoorWolframite", "orePoorIridosmium", "orePoorBismuthinite", "orePoorTennantite", "orePoorTetrahedrite" };
	public static final int[] HARVEST = {1, 1, 1, 2, 2, 2, 2, 3, 3, 1, 1, 1};
	public static final int[] RARITY = {0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0};
}