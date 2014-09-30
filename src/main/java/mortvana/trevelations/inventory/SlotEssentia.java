package mortvana.trevelations.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.IEssentiaContainerItem;

public class SlotEssentia extends Slot {

    public SlotEssentia(IInventory inv, int id, int x, int y) {super(inv, id, x, y);}

    @Override
    public boolean isItemValid(ItemStack stack) {if(stack.getItem() instanceof IEssentiaContainerItem) {return true;} else {return false;}}

}
