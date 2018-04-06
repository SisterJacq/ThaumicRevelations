package mortvana.thaumrev.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.block.FluxGearItemBlockBase;

import mortvana.thaumrev.block.BlockStoneDecor;
import mortvana.thaumrev.library.ThaumRevLibrary;

public class ItemBlockStoneDecor extends FluxGearItemBlockBase {

	public ItemBlockStoneDecor(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.thaumrev.stoneDecor." + BlockStoneDecor.NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[BlockStoneDecor.RARITY[stack.getItemDamage()]];
	}
}
