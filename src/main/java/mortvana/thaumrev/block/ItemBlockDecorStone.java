package mortvana.thaumrev.block;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.melteddashboard.block.FluxGearItemBlockBase;

public class ItemBlockDecorStone extends FluxGearItemBlockBase {

	public ItemBlockDecorStone(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.thaumrev.stoneDecor." + ThaumRevLibrary.DECOR_STONE_NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[BlockStoneDecor.RARITY[stack.getItemDamage()]];
	}
}
