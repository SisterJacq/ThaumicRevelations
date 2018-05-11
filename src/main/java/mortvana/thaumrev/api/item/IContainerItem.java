package mortvana.thaumrev.api.item;

import net.minecraft.item.ItemStack;

public interface IContainerItem {

	public abstract int getInternalSlots(ItemStack container);

	public ItemStack[] getInventory(ItemStack container);

	public void setInventory(ItemStack container, ItemStack[] inventory);

	public ItemStack getItemInSlot(ItemStack container, int slot);

	public void setItemInSlot(ItemStack container, ItemStack itemstack, int slot);

}
