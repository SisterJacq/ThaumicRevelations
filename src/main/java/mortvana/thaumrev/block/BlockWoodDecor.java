package mortvana.thaumrev.block;

import net.minecraft.block.material.Material;

import mortvana.melteddashboard.block.FluxGearBlockBase;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockWoodDecor extends FluxGearBlockBase {

	public BlockWoodDecor() {
		super(Material.wood);
		setStepSound(soundTypeWood);
		setCreativeTab(thaumicRevelationsTab);
		setBlockName("thaumrev.wood");

		setData(TEX_LOC_DEFAULT + "wood/", NAMES);
	}

	public static final String[] NAMES = { "greatwoodImpregnated", "greatwoodCorrupted", "silverwoodHardened" };

}