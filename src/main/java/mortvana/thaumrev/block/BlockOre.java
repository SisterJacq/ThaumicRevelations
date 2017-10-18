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
		setBlockName("thaumrev.stonedecor");

		setData(TEX_LOC_DEFAULT + "ore/", ORE_NAMES);

		setHardness(3.0F);
		setResistance(5.0F);

		setHarvestLevel("pickaxe", 1);
		setHarvestLevel("pickaxe", 2, 5);
		setHarvestLevel("pickaxe", 3, 6);
		setHarvestLevel("pickaxe", 3, 7);
		setHarvestLevel("pickaxe", 3, 9);
		setHarvestLevel("pickaxe", 3, 10);
		setHarvestLevel("pickaxe", 3, 11);
		setHarvestLevel("pickaxe", 2, 12);
	}

	public void register() {
		oreChalcocite = new ItemStack(this, 1, 0);
		oreSphalerite = new ItemStack(this, 1, 1);
		oreCassiterite = new ItemStack(this, 1, 2);
		oreTetrahedrite = new ItemStack(this, 1, 3);
		oreTennantite = new ItemStack(this, 1, 4);
		oreNativeSilver = new ItemStack(this, 1, 5);
		oreIridosmium = new ItemStack(this, 1, 6);
		oreWolframite = new ItemStack(this, 1, 7);
		oreBismuthinite = new ItemStack(this, 1, 8);
		orePyrope = new ItemStack(this, 1, 9);
		oreDioptase = new ItemStack(this, 1, 10);
		oreFluonicSapphire = new ItemStack(this, 1, 11);
		oreXenotime = new ItemStack(this, 1, 12);

		RecipeHelper.registerOreDict(oreChalcocite, "oreCopper", "oreChalcocite");
		RecipeHelper.registerOreDict(oreSphalerite, "oreZinc", "oreSphalerite");
		RecipeHelper.registerOreDict(oreCassiterite, "oreTin", "oreCassiterite");
		RecipeHelper.registerOreDict(oreTetrahedrite, "oreTetrahedrite");
		RecipeHelper.registerOreDict(oreTennantite, "oreTennantite");
		RecipeHelper.registerOreDict(oreNativeSilver, "oreSilver", "oreNativeSilver");
		RecipeHelper.registerOreDict(oreIridosmium, "oreIridosmium");
		RecipeHelper.registerOreDict(oreWolframite, "oreTungsten", "oreWolframite");
		RecipeHelper.registerOreDict(oreBismuthinite, "oreBismuth", "oreBismuthinite");
		RecipeHelper.registerOreDict(orePyrope, "orePyrope");
		RecipeHelper.registerOreDict(oreDioptase, "oreDioptase");
		RecipeHelper.registerOreDict(oreFluonicSapphire, "oreFluonicSapphire");
		RecipeHelper.registerOreDict(oreXenotime, "oreXenotime");
	}

	public void recipes() {
		RecipeHelper.addSmelting(oreChalcocite, ingotCopper, 0.85F);
		RecipeHelper.addSmelting(oreSphalerite, ingotZinc, 0.95F);
		RecipeHelper.addSmelting(oreCassiterite, ingotTin, 0.975F);
		RecipeHelper.addSmelting(oreTetrahedrite, ingotAntimonialBronze, 1.35F);
		RecipeHelper.addSmelting(oreTennantite, ingotArsenicalBronze, 1.35F);
		RecipeHelper.addSmelting(oreNativeSilver, ingotSilver, 1.5F);
		//Refractory Alloys mock your furnace
		//Tungsten laughs at your mundane smelting
		RecipeHelper.addSmelting(oreBismuthinite, ingotBismuth, 1.15F);
		RecipeHelper.addSmelting(orePyrope, gemPyrope, 1.0F);
		RecipeHelper.addSmelting(oreDioptase, gemDioptase, 1.0F);
		RecipeHelper.addSmelting(oreFluonicSapphire, gemFluonicSapphire, 1.0F);
		//Rare Earths mock your primitive furnace-based attempts at separating them
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		switch (world.getBlockMetadata(x, y, z)) {
			case 5:
				return 4;
			case 8:
			case 12:
				return 2;
			case 9:
			case 10:
			case 11:
				return 8;
			default:
				return 0;
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();

		switch (meta) {
			case 9:
				list.add(ItemHelper.cloneStack(gemPyrope, getStackSize(fortune)));

			case 10:
				list.add(ItemHelper.cloneStack(gemDioptase, getStackSize(fortune)));

			case 11:
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
		if (meta == 9 || meta == 10 || meta == 11) {
			return MathHelper.RANDOM.nextInt(6) + 4;
		} else {
			return 0;
		}
	}

	public static final int[] RARITY = { 0, 0, 0, 0, 0, 0, 2, 1, 0, 2, 2, 2, 1 };
}
