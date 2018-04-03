package mortvana.thaumrev.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.block.FluxGearItemBlockBase;

import mortvana.thaumrev.block.BlockStoneSlab;
import mortvana.thaumrev.library.ThaumRevLibrary;

public class ItemBlockStoneSlab extends FluxGearItemBlockBase {

	public ItemBlockStoneSlab(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.thaumrev.stoneSlab." + ThaumRevLibrary.SLAB_STONE_NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[BlockStoneSlab.RARITY[stack.getItemDamage()]];
	}
}
