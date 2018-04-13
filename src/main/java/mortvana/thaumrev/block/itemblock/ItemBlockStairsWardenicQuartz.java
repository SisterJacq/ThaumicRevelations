package mortvana.thaumrev.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.*;

import mortvana.melteddashboard.block.FluxGearItemBlockBase;

public class ItemBlockStairsWardenicQuartz extends FluxGearItemBlockBase {

	public ItemBlockStairsWardenicQuartz(Block block) {
		super(block);
	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.uncommon;
	}
}
