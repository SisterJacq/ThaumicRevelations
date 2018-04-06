package mortvana.thaumrev.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.block.FluxGearItemBlockBase;

import mortvana.thaumrev.block.BlockStorageOre;
import mortvana.thaumrev.library.ThaumRevLibrary;

public class ItemBlockStorageOre extends FluxGearItemBlockBase {

	public ItemBlockStorageOre(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.thaumrev.storage." + BlockStorageOre.NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[BlockStorageOre.RARITY[stack.getItemDamage()]];
	}
}