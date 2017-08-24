package mortvana.thaumrev.block;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.melteddashboard.block.FluxGearItemBlockBase;

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
