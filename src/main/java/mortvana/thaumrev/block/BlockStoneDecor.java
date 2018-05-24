package mortvana.thaumrev.block;

import java.util.List;

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

import mortvana.melteddashboard.block.FluxGearBlockBase;
import mortvana.melteddashboard.lib.ColorLibrary;
import mortvana.melteddashboard.lib.StringLibrary;
import mortvana.melteddashboard.util.helpers.StringHelper;
import mortvana.melteddashboard.util.helpers.TextureHelper;

import static mortvana.melteddashboard.lib.ColorLibrary.*;
import static mortvana.melteddashboard.lib.ColorLibrary.CLEAR;
import static mortvana.melteddashboard.util.helpers.TextureHelper.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockStoneDecor extends FluxGearBlockBase {

	public boolean[] quartzColor = new boolean[] { true, true, true, true };

	public BlockStoneDecor() {
		super(Material.rock);
		setStepSound(soundTypeStone);
		setCreativeTab(generalTab);
		setBlockName("thaumrev.stonedecor");

		setHarvestLevel("pickaxe", 0);

		setHarvestLevel("pickaxe", 3, 0);
		setHarvestLevel("pickaxe", 2, 1);

		setData(StringLibrary.DIR_DEFAULT + "stoneDecor/", NAMES);
		icons = new IIcon[16];
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
	public IIcon getIcon(int side, int meta) {
		/*if (meta == 2) {
			if (side == 0) {
				return icons[16];
			} else if (side == 1) {
				return icons[17];
			}
		}*/
		if (meta == 2) {
			if (side == 0 || side == 1) {
				return icons[4];
			}
		}
		if (meta == 3 || meta == 4 || meta == 5) {
			if (meta == 3 && (side == 0 || side == 1)) {
				return icons[5];
			}
			if (meta == 4 && (side == 4 || side == 5)) {
				return icons[5];
			}
			if (meta == 5 && (side == 2 || side == 3)) {
				return icons[5];
			}
			return icons[3];
		}
		if (meta == 6) {
			if (side == 0 || side == 1) {
				return icons[8];
			}
		}
		if (meta == 7 || meta == 8 || meta == 9) {
			if (meta == 7 && (side == 0 || side == 1)) {
				return icons[9];
			}
			if (meta == 8 && (side == 4 || side == 5)) {
				return icons[9];
			}
			if (meta == 8 && (side == 2 || side == 3)) {
				return icons[9];
			}
			return icons[7];
		}

		return super.getIcon(side, meta);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		if (meta == 3) {
			switch (side) {
				case 0:
				case 1:
					meta = 3;
					break;
				case 2:
				case 3:
					meta = 4;
					break;
				case 4:
				case 5:
					meta = 5;
			}
		} else if (meta == 7) {
			switch (side) {
				case 0:
				case 1:
					meta = 7;
					break;
				case 2:
				case 3:
					meta = 8;
					break;
				case 4:
				case 5:
					meta = 9;
			}
		}
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		addIcons(register, 0);                              //Wardenic Obsidian
		addIcons(register, 1);                              //Eldritch Stone
		//Skip 2-8
		//10 - Thaumic Stone
		//11 - Infernal Blast Furnace Brick
		//12 - Shadowforge Brick
		handleQuartz(register);

		/*addIcons(register, 2, names[2] + "_side");          //Wardenic Quartz (Side)
		addIcons(register, 16, names[2] + "_top");          //Wardenic Quartz (Top)
		addIcons(register, 17, names[2] + "_bottom");       //Wardenic Quartz (Bottom)*/

	}

	public void handleQuartz(IIconRegister register) {
		if (blockTextureExists(directory + names[3] + "_side") && blockTextureExists(directory + names[3] + "_end")) {
			quartzColor[0] = false;
		}
		if (blockTextureExists(directory + names[4] + "_side") && blockTextureExists(directory + names[4] + "_end")) {
			quartzColor[1] = false;
		}
		if (blockTextureExists(directory + names[5] + "_side") && blockTextureExists(directory + names[5] + "_end")) {
			quartzColor[2] = false;
		}
		if (blockTextureExists(directory + names[6] + "_side") && blockTextureExists(directory + names[6] + "_end")) {
			quartzColor[3] = false;
		}

		addQuartzIcons(register, 2, names[2] + "_side", quartzColor[0], "quartzChiseled_side", "quartz_block_chiseled");    //Chiseled Wardenic Quartz (Side)
		addQuartzIcons(register, 4, names[2] + "_end", quartzColor[0], "quartzChiseled_end", "quartz_block_chiseled_top");  //Chiseled Wardenic Quartz (Ends)
		addQuartzIcons(register, 3, names[3] + "_side", quartzColor[1], "quartzPillar_side", "quartz_block_lines");       	//Wardenic Quartz Pillar (Side)
		addQuartzIcons(register, 5, names[3] + "_end", quartzColor[1], "quartzPillar_end", "quartz_block_lines_top");       //Wardenic Quartz Pillar (Ends)
		addQuartzIcons(register, 6, names[6] + "_side", quartzColor[2], "quartzChiseled_side", "quartz_block_chiseled");    //Chiseled Redquartz (Side)
		addQuartzIcons(register, 8, names[6] + "_end", quartzColor[2], "quartzChiseled_end", "quartz_block_chiseled_top");  //Chiseled Redquartz (Ends)
		addQuartzIcons(register, 7, names[7] + "_side", quartzColor[3], "quartzPillar_side", "quartz_block_lines");       	//Redquartz Pillar (Side)
		addQuartzIcons(register, 9, names[7] + "_end", quartzColor[3], "quartzPillar_end", "quartz_block_lines_top");       //Redquartz Pillar (Ends)
	}

	@Override
	public ItemStack createStackedBlock(int meta) {
		if (meta == 4 || meta == 5) {
			return super.createStackedBlock(4);
		} else if (meta == 8 || meta == 9) {
			return super.createStackedBlock(4);
		} else {
			return super.createStackedBlock(meta);
		}
	}

	/*@Override //TODO: Fix Renderer
	public int getRenderType() {
		return ThaumRevLibrary.renderDecorStoneID;
	}*/

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
		list.add(new ItemStack(item, 1, 2));
		list.add(new ItemStack(item, 1, 3));
		list.add(new ItemStack(item, 1, 6));
		list.add(new ItemStack(item, 1, 7));
	}

	@Override
	public int damageDropped(int meta) {
		return meta != 5 && meta != 6 ? meta : 4;
	}

	@Override
	public MapColor getMapColor(int par1) {
		return MapColor.purpleColor;
	}

	protected void addIcons(IIconRegister register, int index) {
		addIcons(register, index, StringHelper.camelCase(names[index]));
	}

	protected void addIcons(IIconRegister register, int index, String name) {
		icons[index] = register.registerIcon(directory + name);
	}

	protected void addQuartzIcons(IIconRegister register, int index, String precolored, boolean skipFirst, String localIcon, String vanillaIcon) {
		if (!skipFirst) {
			addIcons(register, index, precolored);
		} else if (blockTextureExists(localIcon)) {
			addIcons(register, index, localIcon);
		} else {
			icons[index] = register.registerIcon(vanillaIcon);
		}
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return getRenderColor(world.getBlockMetadata(x, y, z));
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		switch (meta) {
			case 2:
				return quartzColor[0] ? COLOR_WQRZ : CLEAR;
			case 3:
			case 4:
			case 5:
				return quartzColor[1] ? COLOR_WQRZ : CLEAR;
			case 6:
				return quartzColor[2] ? COLOR_FLUX : CLEAR;
			case 7:
			case 8:
			case 9:
				return quartzColor[3] ? COLOR_FLUX : CLEAR;
			default:
				return CLEAR;
		}
	}


	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fluxgear.stoneDecor." + NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public int getRarity(int meta) {
		return RARITY[meta];
	}

	public static final String[] NAMES = { "obsidianWardenic", "stoneEldritch", "wardenicQuartzChiseled", "wardenicQuartzPillar", "wardenicQuartzPillar", "wardenicQuartzPillar", "redquartzChiseled", "redquartzPillar", "redquartzPillar", "redquartzPillar"/*, "thaumicStone", "infernalBlastBrick", "shadowforgeBrick"*/ };
	public static final float[] HARDNESS = {25F, 10F, 0.8F, 0.8F, 0.8F, 0.8F, 0.8F, 0.8F, 0.8F, 0.8F};
	public static final float[] RESISTANCE = {5000F, 500F, 25F, 25F, 25F, 25F, 25F, 25F, 25F, 25F};
	public static final int[] RARITY = {2, 1, 1, 1, 1, 1, 1, 1, 1, 1};
}
