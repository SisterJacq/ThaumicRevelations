package mortvana.thaumrev.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.melteddashboard.block.FluxGearBlockBase;
import mortvana.melteddashboard.lib.ThaumcraftLibrary;

import mortvana.thaumrev.util.RecipeHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockMetalStorageMain extends FluxGearBlockBase {

	public BlockMetalStorageMain() {
		super(Material.iron);
		setStepSound(soundTypeMetal);
		setCreativeTab(thaumicRevelationsTab);
		setBlockName("thaumrev.storageMain");

		setData(TEX_LOC_DEFAULT + "storage/", STORAGE_MAIN_NAMES);
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
		for (int i = 0; i < MINE_LVL.length; i++) {
			setHarvestLevel("pickaxe", MINE_LVL[i], i);
		}
	}

	public void register() {
		for (int i = 0; i < STORAGE_MAIN_NAMES.length; i++) {
			RecipeHelper.registerOreDict(new ItemStack(this, 1, i), STORAGE_MAIN_NAMES[i]);
		}

		blockCopper = new ItemStack(blockStorageMain, 1, 0);
		blockZinc = new ItemStack(blockStorageMain, 1, 1);
		blockTin = new ItemStack(blockStorageMain, 1, 2);
		blockSilver = new ItemStack(blockStorageMain, 1, 3);
		blockBrass = new ItemStack(blockStorageMain, 1, 4);
		blockBronze = new ItemStack(blockStorageMain, 1, 5);
		blockThaumicBronze = new ItemStack(blockStorageMain, 1, 6);
		blockSteel = new ItemStack(blockStorageMain, 1, 7);
		blockVoidbrass = new ItemStack(blockStorageMain, 1, 8);
		blockVoidsteel = new ItemStack(blockStorageMain, 1, 9);
		blockElectrum = new ItemStack(blockStorageMain, 1, 10);
		blockWardenicSteel = new ItemStack(blockStorageMain, 1, 11);
		blockThaumicElectrum = new ItemStack(blockStorageMain, 1, 12);
		blockVoidmetal = new ItemStack(blockStorageMain, 1, 13);
		blockWardenicMetal = new ItemStack(blockStorageMain, 1, 14);
		blockWardenicComposite = new ItemStack(blockStorageMain, 1, 15);
	}

	public void recipes() {
		ItemStack[] ingots = {ingotCopper, ingotZinc, ingotTin, ingotSilver, ingotBrass, ingotBronze, ingotThaumicBronze, ingotSteel, ingotVoidbrass, ingotVoidsteel, ingotElectrum, ingotWardenicSteel, ingotThaumicElectrum, ThaumcraftLibrary.ingotVoidmetal, ingotWardenicMetal, wardenicCompositePlate};
		for (int i = 0; i < STORAGE_MAIN_NAMES.length; i++) {
			RecipeHelper.addStorageRecipe(new ItemStack(this, 1, i), INGOT_NAMES[i]);
			RecipeHelper.addReverseStorageRecipe(ingots[i], STORAGE_MAIN_NAMES[i]);
		}
	}


	public static final int[] MINE_LVL = {1, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2, 2, 1, 1, 3};
	public static final float[] HARDNESS = {5, 5, 5, 5, 5, 5, 6, 8, 6, 10, 5, 11, 6, 6, 6, 25};
	public static final float[] RESISTANCE = {6, 6, 6, 6, 6, 7, 10, 13, 12, 25, 6, 25, 8, 10, 8, 50};
	public static final int[] LIGHT = {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 4, 1, 6, 0, 0, 2};
	public static final int[] RARITY = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 2};
	public static final String[] INGOT_NAMES = {"ingotCopper", "ingotZinc", "ingotTin", "ingotSilver", "ingotBrass", "ingotBronze", "ingotThaumicBronze", "ingotSteel", "ingotVoidbrass", "ingotVoidsteel", "ingotElectrum", "ingotWardenicSteel", "ingotThaumicElectrum", "ingotVoid", "ingotWardenicMetal", "itemPlateWardenicComposite"};
}
