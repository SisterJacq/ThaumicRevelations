package mortvana.thaumrev.block;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

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

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fluxgear.woodDecor." + NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public int getRarity(int meta) {
		return 1;
	}

	public static final String[] NAMES = { "greatwoodImpregnated", "greatwoodCorrupted", "silverwoodHardened" };

	//public static final int[] RARITY = { 1, 1, 1 };

}