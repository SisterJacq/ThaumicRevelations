package mortvana.melteddashboard.util.helpers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import mortvana.thaumrev.api.item.IContainerItem;

import static mortvana.thaumrev.library.ThaumRevLibrary.INVENTORY;
import static mortvana.thaumrev.library.ThaumRevLibrary.SLOT;

public class InventoryHelper {

	public static int getInternalSlots(ItemStack container) { //TODO: CoFH/IE/Forestry
		Item item = container.getItem();
		if (item instanceof IContainerItem) {
			return ((IContainerItem) item).getInternalSlots(container);
		} else {
			return 0;
		}
	}

	public static ItemStack[] getInventory(ItemStack container) {
		ItemStack[] inventory = new ItemStack[getInternalSlots(container)];
		if (container.hasTagCompound()) {
			NBTTagList inv = container.getTagCompound().getTagList(INVENTORY, 10);
			NBTTagCompound nbt;
			for (int i = 0; i < inv.tagCount(); i++) {
				nbt = inv.getCompoundTagAt(i);
				int slot = nbt.getByte(SLOT) & 0xFF;
				if (slot >= 0 && slot < inventory.length) {
					inventory[slot] = ItemStack.loadItemStackFromNBT(nbt);
				}
			}
		}
		return inventory;
	}

	public static void setInventory(ItemStack container, ItemStack[] inventory) {
		NBTTagList inv = new NBTTagList();
		NBTTagCompound nbt;
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null) {
				nbt = new NBTTagCompound();
				nbt.setByte(SLOT, (byte) i);
				inventory[i].writeToNBT(nbt);
				inv.appendTag(nbt);
			}
		}
		NBTHelper.ensureNBT(container);
		container.getTagCompound().setTag(INVENTORY, inv);
	}

	public static ItemStack getItemInSlot(ItemStack container, int slot) {
		if (container.hasTagCompound()) {
			NBTTagList inv = container.getTagCompound().getTagList(INVENTORY, 10);
			NBTTagCompound nbt;
			for (int i = 0; i < inv.tagCount(); i++) {
				nbt = inv.getCompoundTagAt(i);
				if (slot == (nbt.getByte(SLOT) & 0xFF) && slot >= 0 && slot < getInternalSlots(container)) {
					return ItemStack.loadItemStackFromNBT(nbt);
				}
			}
		}
		return null;
	}

	public static void setItemInSlot(ItemStack container, ItemStack itemstack, int slot) {
		if (container.hasTagCompound() && container.getTagCompound().hasKey(INVENTORY)) {
			NBTTagList inv = container.getTagCompound().getTagList(INVENTORY, 10);
			NBTTagCompound nbt;
			for (int i = 0; i < inv.tagCount(); i++) {
				nbt = inv.getCompoundTagAt(i);
				if (slot == (nbt.getByte(SLOT) & 0xFF) && slot >= 0 && slot < getInternalSlots(container)) {
					inv.removeTag(i);
					nbt = new NBTTagCompound();
					nbt.setByte(SLOT, (byte) slot);
					itemstack.writeToNBT(nbt);
					inv.appendTag(nbt);
					container.getTagCompound().setTag(INVENTORY, inv);
					return;
				}
			}
		} else {
			NBTHelper.ensureNBT(container);
			if (slot >= 0 && slot < getInternalSlots(container)) {
				NBTTagList inv = new NBTTagList();
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setByte(SLOT, (byte) slot);
				itemstack.writeToNBT(nbt);
				inv.appendTag(nbt);
				container.getTagCompound().setTag(INVENTORY, inv);
			}
		}
	}
}
