package mortvana.thaumrev.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.melteddashboard.block.FluxGearBlockPlant;
import mortvana.melteddashboard.util.helpers.ItemHelper;
import mortvana.melteddashboard.util.helpers.science.MathHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockThaumicPlant extends FluxGearBlockPlant {

	public BlockThaumicPlant() {
		super(Material.plants);
		setBlockName("blockThaumicPlant");
		setCreativeTab(thaumicRevelationsTab);
		setStepSound(Block.soundTypeGrass);
		setData(TEX_LOC_DEFAULT + "plant/", PLANT_NAMES);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();

		switch (meta) {
			case 0:
				list.add(ItemHelper.cloneStack(excubituraPetal, 1 + ((int) (0.5F + (((float) MathHelper.random(1 + fortune)) / 2.0F)))));
				if (((int) ((((float) (MathHelper.random(8 - fortune))) / 2.0F))) == 0) {
					list.add(seedExcubitura);
				}
			case 1:
				list.add(ItemHelper.cloneStack(cotton, 2 + MathHelper.random(1 + fortune)));
				list.add(ItemHelper.cloneStack(seedCotton, 1 + ((int) (((float) MathHelper.random(2 + fortune)) / 2.0F))));
			case 2:
				list.add(thistleFlower);
				list.add(ItemHelper.cloneStack(thistleLeaf, 2 + MathHelper.random(1 + fortune)));
				list.add(ItemHelper.cloneStack(seedThistle, 1 + ((int) (((float) MathHelper.random(2 + fortune)) / 2.0F))));
			default:
				list.add(new ItemStack(blockThaumicPlant, 1, meta));
		}

		return list;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return LIGHT[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		switch (world.getBlockMetadata(x, y, z)) {
			case 8:
				return EnumPlantType.Desert;
			default:
				return EnumPlantType.Plains;
		}
	}

	@Override //TODO: Make Metadata Specific
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return 100;
	}

	@Override //TODO: Make Metadata Specific
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return 60;
	}

	//TODO: Particles
	/*@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {}*/

	public static final int[] LIGHT = { 4, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8 };
}
