package mortvana.thaumrev.block;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.melteddashboard.block.FluxGearItemBlockBase;

public class ItemBlockMetalStorage extends FluxGearItemBlockBase {

	public ItemBlockMetalStorage(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.thaumrev.storage." + ThaumRevLibrary.STORAGE_MAIN_NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[BlockMetalStorageMain.RARITY[stack.getItemDamage()]];
	}
}