package mortvana.thaumrev.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.thaumrev.melteddashboard.block.FluxGearBlockBase;
import mortvana.thaumrev.melteddashboard.lib.ThaumcraftLibrary;
import mortvana.thaumrev.util.RecipeHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockMetalStorage extends FluxGearBlockBase {

	public BlockMetalStorage() {
		super(Material.iron);
		setBlockName("thaumrev.storage");

		setData(TEX_LOC_DEFAULT + "storage/", STORAGE_NAMES);
		setHarvestLevels();
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return HARDNESS[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		return RESISTANCE[world.getBlockMetadata(x, y, z)];
	}

	public void setHarvestLevels() {
		for (int i =0; i < MINE_LVL.length; i++) {
			setHarvestLevel("pickaxe", MINE_LVL[i], i);
		}
	}

	public void register() {
		for (int i = 0; i < STORAGE_NAMES.length; i++) {
			RecipeHelper.registerOreDict(new ItemStack(this, 1, i), STORAGE_NAMES[i]);
			RecipeHelper.addStorageRecipe(new ItemStack(this, 1, i), INGOT_NAMES[i]);
			RecipeHelper.addReverseStorageRecipe(INGOT_STACKS[i], STORAGE_NAMES[i]);
		}
	}


	public static final int[] MINE_LVL = { 1, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2, 2, 1 };
	public static final float[] HARDNESS = { 5, 5, 5, 5, 5, 5, 6, 8, 6, 10, 5, 11, 6, 6 };
	public static final float[] RESISTANCE = { 6, 6, 6, 6, 6, 7, 10, 13, 12, 25, 6, 25, 8, 10 };
	public static final int[] LIGHT = { 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 4, 1, 6, 0 };
	public static final int[] RARITY = { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0 };
	public static final String[] INGOT_NAMES = {
			"ingotCopper",
			"ingotZinc",
			"ingotTin",
			"ingotSilver",
			"ingotBrass",
			"ingotBronze",
			"ingotThaumicBronze",
			"ingotSteel",
			"ingotVoidbrass",
			"ingotVoidsteel",
			"ingotElectrum",
			"ingotWardenicSteel",
			"ingotThaumicElectrum",
			"ingotVoid"
	};
	public static final ItemStack[] INGOT_STACKS = {
			ingotCopper,
			ingotZinc,
			ingotTin,
			ingotSilver,
			ingotBrass,
			ingotBronze,
			ingotThaumicBronze,
			ingotSteel,
			ingotVoidbrass,
			ingotVoidsteel,
			ingotElectrum,
			ingotWardenicSteel,
			ingotThaumicElectrum,
			ThaumcraftLibrary.ingotVoidmetal
	};
}
