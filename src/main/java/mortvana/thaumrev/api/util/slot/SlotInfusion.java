package mortvana.thaumrev.api.util.slot;

import net.minecraft.item.ItemStack;

public class SlotInfusion {

	protected ItemStack contents;

	//TODO: Everything

	public ItemStack getContents() {
		return contents;
	}

	public SlotInfusion setContents(ItemStack stack) {
		contents = stack;
		return this;
	}

}
