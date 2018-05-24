package mortvana.melteddashboard.block;

import net.minecraft.item.ItemStack;

public interface IItemBlockProvider {

	public String getUnlocalizedName(ItemStack stack);

	public int getRarity(int metadata);

}
