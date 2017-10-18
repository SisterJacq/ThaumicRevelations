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
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.block.FluxGearBlockBase;
import mortvana.melteddashboard.util.helpers.ItemHelper;
import mortvana.melteddashboard.util.helpers.StringHelper;
import mortvana.thaumrev.util.RecipeHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockStoneDecor extends FluxGearBlockBase {

	public BlockStoneDecor() {
		super(Material.rock);
		setStepSound(soundTypeStone);
		setCreativeTab(thaumicRevelationsTab);
		setBlockName("thaumrev.stonedecor");

		setHarvestLevel("pickaxe", 0);

		setHarvestLevel("pickaxe", 3, 0);
		setHarvestLevel("pickaxe", 2, 1);

		setData(TEX_LOC_DEFAULT + "stoneDecor/", DECOR_STONE_NAMES);
		icons = new IIcon[18];
	}

	public void register() {
		wardenicObsidian = new ItemStack(this, 1, 0);
		eldritchStone = new ItemStack(this, 1, 1);
		wardenicQuartzBlock = new ItemStack(this, 1, 2);
		wardenicQuartzChiseled = new ItemStack(this, 1, 3);
		wardenicQuartzPillar = new ItemStack(this, 1, 4);

		RecipeHelper.registerOreDict(wardenicObsidian, "blockWardenicObsidian");
		RecipeHelper.registerOreDict(eldritchStone, "blockEldritchStone");
		for (int i = 2; i < 5; i++) {
			RecipeHelper.registerOreDict(new ItemStack(this, 1, i), "blockQuartzWardenic");
		}
	}

	public void recipes() {
		recipeQuartzBlock = RecipeHelper.addSquareRecipe(wardenicQuartzBlock, "gemQuartzWardenic");
		recipeQuartzChiseled = RecipeHelper.addShapedRecipe(wardenicQuartzChiseled, "X", "X", 'X', "slabQuartzWardenic");
		recipeQuartzPillar = RecipeHelper.addShapedRecipe(ItemHelper.cloneStack(wardenicQuartzPillar, 2), "X", "X", 'X', "blockQuartzWardenic");
		recipeQuartzDeblock = RecipeHelper.addDeblockingRecipe(wardenicQuartz, wardenicQuartzBlock);

		recipeQuartzResetChiseled = RecipeHelper.addShapelessRecipe(wardenicQuartzBlock, wardenicQuartzChiseled);
		recipeQuartzResetPillar = RecipeHelper.addShapelessRecipe(wardenicQuartzBlock, wardenicQuartzPillar);
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
		if (meta == 2) {
			if (side == 0) {
				return icons[16];
			} else if (side == 1) {
				return icons[17];
			}
		}
		if (meta == 3) {
			if (side == 0 || side == 1) {
				return icons[6];
			}
		}
		if (meta == 4 || meta == 5 || meta == 6) {
			if (meta == 4 && (side == 0 || side == 1)) {
				return icons[5];
			}
			if (meta == 5 && (side == 4 || side == 5)) {
				return icons[5];
			}
			if (meta == 6 && (side == 2 || side == 3)) {
				return icons[5];
			}
			return icons[4];
		}

		return super.getIcon(side, meta);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		if (meta == 4) {
			switch (side) {
				case 0:
				case 1:
					meta = 4;
					break;
				case 2:
				case 3:
					meta = 5;
					break;
				case 4:
				case 5:
					meta = 6;
			}
		}
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		addIcons(register, 0);                              //Wardenic Obsidian
		addIcons(register, 1);                              //Eldritch Stone
		addIcons(register, 2, names[2] + "_side");          //Wardenic Quartz (Side)
		addIcons(register, 3, names[3] + "_side");          //Chiseled Wardenic Quartz (Side)
		addIcons(register, 4, names[4] + "_side");          //Wardenic Quartz Pillar (Side)
		addIcons(register, 5, names[4] + "_end");           //Wardenic Quartz Pillar (Ends)
		addIcons(register, 6, names[3] + "_end");           //Chiseled Wardenic Quartz (Ends)
		addIcons(register, 16, names[2] + "_top");          //Wardenic Quartz (Top)
		addIcons(register, 17, names[2] + "_bottom");       //Wardenic Quartz (Bottom)
	}

	@Override
	public ItemStack createStackedBlock(int meta) {
		if (meta == 5 || meta == 6) {
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
		list.add(new ItemStack(item, 1, 4));
	}

	@Override
	public int damageDropped(int meta) {
		return meta != 5 && meta != 6 ? meta : 4;
	}

	@Override
	public MapColor getMapColor(int par1) {
		return MapColor.purpleColor;
	}

	private void addIcons(IIconRegister register, int index) {
		addIcons(register, index, StringHelper.camelCase(names[index]));
	}

	private void addIcons(IIconRegister register, int index, String name) {
		icons[index] = register.registerIcon(directory + name);
	}

	public static final float[] HARDNESS = { 25F, 10F, 0.8F, 0.8F, 0.8F, 0.8F, 0.8F };
	public static final float[] RESISTANCE = { 5000F, 500F, 25F, 25F, 25F, 25F, 25F };
	public static final int[] RARITY = { 2, 1, 1, 1, 1, 1, 1 };
}
