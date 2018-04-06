package mortvana.thaumrev.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.block.FluxGearItemBlockBase;

import mortvana.thaumrev.block.BlockStorageAlloy1;
import mortvana.thaumrev.library.ThaumRevLibrary;

public class ItemBlockStorageAlloy1 extends FluxGearItemBlockBase {

	public ItemBlockStorageAlloy1(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.thaumrev.storage." + BlockStorageAlloy1.NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[BlockStorageAlloy1.RARITY[stack.getItemDamage()]];
	}
}