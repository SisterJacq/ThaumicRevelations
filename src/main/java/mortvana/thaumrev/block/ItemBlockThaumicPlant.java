package mortvana.thaumrev.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.block.FluxGearItemBlockBase;

import mortvana.thaumrev.library.ThaumRevLibrary;

public class ItemBlockThaumicPlant extends FluxGearItemBlockBase {

	public ItemBlockThaumicPlant(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.thaumrev.plant." + ThaumRevLibrary.PLANT_NAMES[stack.getItemDamage()] + ".name";
	}
}
