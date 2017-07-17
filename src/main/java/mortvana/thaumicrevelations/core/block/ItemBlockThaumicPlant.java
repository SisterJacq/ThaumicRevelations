package mortvana.thaumicrevelations.core.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import mortvana.thaumicrevelations.library.ThaumRevLibrary;
import mortvana.thaumicrevelations.melteddashboard.block.FluxGearItemBlockBase;

public class ItemBlockThaumicPlant extends FluxGearItemBlockBase {

	public ItemBlockThaumicPlant(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.thaumrev.plant." + ThaumRevLibrary.PLANT_NAMES[stack.getItemDamage()] + ".name";
	}
}
