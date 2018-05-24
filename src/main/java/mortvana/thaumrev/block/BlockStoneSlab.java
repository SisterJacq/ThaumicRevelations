package mortvana.thaumrev.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.block.FluxGearBlockSlab;
import mortvana.melteddashboard.lib.StringLibrary;

import static mortvana.melteddashboard.lib.ColorLibrary.CLEAR;
import static mortvana.melteddashboard.lib.ColorLibrary.COLOR_FLUX;
import static mortvana.melteddashboard.lib.ColorLibrary.COLOR_WQRZ;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockStoneSlab extends FluxGearBlockSlab {

	public BlockStoneSlab() {
		this(false, Material.rock);
	}

	public BlockStoneSlab(Block slab) {
		this(true, Material.rock);
		this.slab = slab;
	}

	private BlockStoneSlab(boolean fullBlock, Material material) {
		super(fullBlock, material);
		setStepSound(soundTypeStone);
		if (!fullBlock) {
			setCreativeTab(generalTab);
			setBlockName("thaumrev.stoneSlab");
		} else {
			setBlockName("thaumrev.stoneSlabDouble");
		}

		setHarvestLevel("pickaxe", 0);

		setHarvestLevel("pickaxe", 3, 0);
		setHarvestLevel("pickaxe", 2, 1);

		setData(StringLibrary.DIR_DEFAULT  + "stoneDecor/", NAMES);
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
	public MapColor getMapColor(int par1) {
		return MapColor.purpleColor;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		if (!field_150004_a) {
			list.add(new ItemStack(item, 1, 0));
			list.add(new ItemStack(item, 1, 1));
			list.add(new ItemStack(item, 1, 2));
			list.add(new ItemStack(item, 1, 3));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		icons = new IIcon[10];
		setIcon(0);         //Wardenic Obsidian
		setIcon(1);         //Eldritch Stone
		setIcon(2, 2);		//Chiseled Wardenic Quartz (Side)
		setIcon(3, 6);		//Chiseled Redquartz (Side)
		//setIcon(4, 10);		//Thaumic Stone
		//setIcon(5, 11);		//Infernal Blast Furnace Brick
		//setIcon(6, 12);		//Shadowforge Brick
		setIcon(8, 2);		//Chiseled Wardenic Quartz (End)
		setIcon(9, 6);		//Chiseled Redquartz (End)

		//setIcon(2);         //Wardenic Quartz (Side)
		//setIcon(8, 16);     //Wardenic Quartz (Top)
		//setIcon(9, 17);     //Wardenic Quartz (Bottom)
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if ((meta == 2 || meta == 10) && (side == 0 || side == 1)) {
			return icons[8];
		}
		if ((meta == 3 || meta == 11) && (side == 0 || side == 1)) {
			return icons[9];
		}

		return super.getIcon(side, meta);
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return getRenderColor(world.getBlockMetadata(x, y, z));
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		switch (meta) {
			case 2:
			case 10:
				return ((BlockStoneDecor) blockStoneDecor).quartzColor[0] ? COLOR_WQRZ : CLEAR;
			case 3:
			case 11:
				return ((BlockStoneDecor) blockStoneDecor).quartzColor[2] ? COLOR_FLUX : CLEAR;
			default:
				return CLEAR;
		}
	}

	public void setIcon(int meta) {
		icons[meta] = ((BlockStoneDecor) blockStoneDecor).icons[meta];
	}

	public void setIcon(int meta, int index) {
		icons[meta] = ((BlockStoneDecor) blockStoneDecor).icons[index];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fluxgear.stoneSlab." + NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public int getRarity(int meta) {
		return RARITY[meta];
	}

	public static final String[] NAMES = { "obsidianWardenic", "stoneEldritch", "wardenicQuartzChiseled", "redquartzChiseled" };
	public static final float[] HARDNESS = { 25F, 10F, 0.8F, 0.8F };
	public static final float[] RESISTANCE = { 5000F, 500F, 25F, 25F };
	public static final int[] RARITY = { 2, 1, 1, 1 };
}
