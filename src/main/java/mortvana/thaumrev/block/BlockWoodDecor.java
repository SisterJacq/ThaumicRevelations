package mortvana.thaumrev.block;

import net.minecraft.block.material.Material;

import mortvana.melteddashboard.block.FluxGearBlockBase;
import mortvana.melteddashboard.lib.StringLibrary;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockWoodDecor extends FluxGearBlockBase {

	public BlockWoodDecor() {
		super(Material.wood);
		setStepSound(soundTypeWood);
		setCreativeTab(generalTab);
		setBlockName("thaumrev.wood");

		setData(StringLibrary.DIR_DEFAULT  + "wood/", NAMES);
	}

	public static final String[] NAMES = { "greatwoodImpregnated", "greatwoodCorrupted", "silverwoodHardened" };

	public static final int[] RARITY = { 1, 1, 1 };

}