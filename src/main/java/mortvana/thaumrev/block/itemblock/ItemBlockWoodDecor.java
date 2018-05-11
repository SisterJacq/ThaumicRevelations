package mortvana.thaumrev.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.block.FluxGearItemBlockBase;

import mortvana.thaumrev.block.BlockWoodDecor;

public class ItemBlockWoodDecor extends FluxGearItemBlockBase {

	public ItemBlockWoodDecor(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fluxgear.stoneDecor." + BlockWoodDecor.NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[BlockWoodDecor.RARITY[stack.getItemDamage()]];
	}
}
