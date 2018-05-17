package mortvana.thaumrev.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.lib.StringLibrary;

public class ItemBlockStorageLinked extends ItemBlockStorage {

	public int[] links;

	public ItemBlockStorageLinked(Block block, String[] names, int[] rarities, int[] links) {
		super(block, names, rarities);
		this.links = links;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fluxgear.storage." + StringLibrary.BLOCK + names[links[stack.getItemDamage()]] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[rarities[links[stack.getItemDamage()]]];
	}
}
