package mortvana.thaumrev.melteddashboard.util;

import net.minecraft.item.ItemStack;

public interface IEnumItem {
	public int getMetadata();
	public String getName();
	public String getOreDict();
	public ItemStack getStack();
}
