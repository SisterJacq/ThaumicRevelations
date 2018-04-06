package mortvana.thaumrev.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.melteddashboard.block.FluxGearBlockBase;
import mortvana.melteddashboard.lib.ThaumcraftLibrary;

import mortvana.thaumrev.util.RecipeHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockStorageOre extends FluxGearBlockBase {

	public BlockStorageOre() {
		super(Material.iron);
		setStepSound(soundTypeMetal);
		setCreativeTab(thaumicRevelationsTab);
		setBlockName("thaumrev.storageMain");

		setData(TEX_LOC_DEFAULT + "storage/", NAMES);
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

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return LIGHT[world.getBlockMetadata(x, y, z)];
	}

	public void setHarvestLevels() {
		for (int i = 0; i < MINE_LVL.length; i++) {
			setHarvestLevel("pickaxe", MINE_LVL[i], i);
		}
	}

	/*public void recipes() {
		ItemStack[] ingots = {ingotCopper, ingotZinc, ingotTin, ingotSilver, ingotBrass, ingotBronze, ingotThaumicBronze, ingotSteel, ingotVoidbrass, ingotVoidsteel, ingotElectrum, ingotWardenicSteel, ingotThaumicElectrum, ThaumcraftLibrary.ingotVoidmetal, ingotWardenicMetal, plateWardenicComposite};
		for (int i = 0; i < STORAGE_ORE_NAMES.length; i++) {
			RecipeHelper.addStorageRecipe(new ItemStack(this, 1, i), INGOT_NAMES[i]);
			RecipeHelper.addReverseStorageRecipe(ingots[i], STORAGE_ORE_NAMES[i]);
		}
	}*/

	public static final String[] NAMES = { "blockCopper", "blockZinc", "blockTin", "blockNickel", "blockSilver", "blockLead", "blockXenotimeLanthanides", "blockTungsten", "blockIridium", "blockBismuth", "blockArsenicalBronze", "blockAntimonialBronze", "blockPyrope", "blockDioptase", "blockFluonicSapphire", "blockOsmium" };
	public static final int[] MINE_LVL = { 1, 1, 1, 2, 2, 2, 2, 3, 3, 1, 1, 1, 3, 3, 3, 2 };
	public static final float[] HARDNESS = { 5, 5, 5, 7, 5, 4, 5, 10, 13, 5, 6, 6, 7, 7, 7, 11 };
	public static final float[] RESISTANCE = { 6, 6, 6, 6, 6, 12, 6, 25, 16, 8, 8, 8, 13, 13, 13, 13 };
	public static final int[] LIGHT = { 0, 0, 0, 0, 4, 0, 0, 0, 4, 2, 0, 0, 4, 4, 8, 1 };
	public static final int[] RARITY = 	{ 0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 2, 2, 2, 1 };
	//public static final String[] INGOT_NAMES = {"ingotCopper", "ingotZinc", "ingotTin", "ingotSilver", "ingotBrass", "ingotBronze", "ingotThaumicBronze", "ingotSteel", "ingotVoidbrass", "ingotVoidsteel", "ingotElectrum", "ingotWardenicSteel", "ingotThaumicElectrum", "ingotVoid", "ingotWardenicMetal", "itemPlateWardenicComposite"};
	//public static final int[] MINE_LVL = 		{ 2,  1  };
	//public static final float[] HARDNESS = 	{ 6,  6  };
	//public static final float[] RESISTANCE = 	{ 10, 10 };
	//public static final int[] LIGHT = 		{ 0,  0  };
	//public static final int[] RARITY = 		{ 0,  0  };
	//public static final String[] INGOT_NAMES = { "ingotThaumicBronze", "ingotVoid" };

}
