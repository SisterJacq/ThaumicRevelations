package mortvana.thaumrev.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.melteddashboard.block.FluxGearBlockPlant;
import mortvana.melteddashboard.util.libraries.StringLibrary;
import mortvana.melteddashboard.util.helpers.ItemHelper;
import mortvana.melteddashboard.util.helpers.science.MathHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockThaumicPlant extends FluxGearBlockPlant {

	public BlockThaumicPlant() {
		super(Material.plants);
		setBlockName("blockThaumicPlant");
		setCreativeTab(generalTab);
		setStepSound(Block.soundTypeGrass);
		setData(StringLibrary.DIR_DEFAULT  + "plant/", NAMES);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();

		switch (meta) {
			case 0:
				list.add(ItemHelper.cloneStack(itemExcubituraPetal, 1 + ((int) (0.5F + (((float) MathHelper.random(1 + fortune)) / 2.0F)))));
				if (((int) ((((float) (MathHelper.random(8 - fortune))) / 2.0F))) == 0) {
					list.add(seedExcubitura);
				}
			case 1:
				list.add(ItemHelper.cloneStack(itemCotton, 2 + MathHelper.random(1 + fortune)));
				list.add(ItemHelper.cloneStack(seedCotton, 1 + ((int) (((float) MathHelper.random(2 + fortune)) / 2.0F))));
			case 2:
				list.add(itemThistleFlower);
				list.add(ItemHelper.cloneStack(itemThistleLeaf, 2 + MathHelper.random(1 + fortune)));
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
			case 7:
				return EnumPlantType.Cave;
			case 8:
				return EnumPlantType.Desert;
			default:
				return EnumPlantType.Plains;
		}
	}

	/*@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 7) {
			return isStonypearlBlock(world, x, y - 1, z);
		} else {
			return super.canBlockStay(world, x, y, z);
		}
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
		int metadata = plantable.getPlantMetadata(world, x, y + 1, z);

		if
	}*/

	@Override
	public int getDamageValue (World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override //TODO: Make Metadata Specific
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return 100;
	}

	@Override //TODO: Make Metadata Specific
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return 60;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fluxgear.plant." + NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
		list.add(new ItemStack(item, 1, 2));
		list.add(new ItemStack(item, 1, 5));
		list.add(new ItemStack(item, 1, 6));
		list.add(new ItemStack(item, 1, 7));
	}

	//TODO: Rarity

	//TODO: Particles
	/*@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {}*/

	/*public static boolean isStonypearlBlock(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		return block == Blocks.stone || block == Blocks.gravel;
	}*/

	public static final String[] NAMES = { "excubitura", "cotton_wild", "thistle_wild", "", "", "shiverpearl", "stormypearl", "stonypearl", "blazereed", "blizzreed", "blitzreed", "basalzreed" };
	public static final int[] LIGHT = { 4, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8 };

}
