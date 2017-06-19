package mortvana.thaumicrevelations.melteddashboard.util.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.thaumicrevelations.api.item.infusion.*;
import mortvana.thaumicrevelations.api.util.enums.EnumEquipmentType;
import mortvana.thaumicrevelations.api.util.enums.EnumInfusionType;
import mortvana.thaumicrevelations.api.util.slot.SlotInfusion;

public class AspectInfusionHelper {

	public static void applyInfusions(World world, EntityPlayer player, ItemStack stack, EnumEquipmentType type) {
		if (stack.getItem() instanceof IInfusableItem) {
			for (int i = 0; i < ((IInfusableItem) stack.getItem()).getNumberSlotsTotal(stack); i++) {
				applyInfusionForStack(world, player, stack, type, ((IInfusableItem) stack.getItem()).getSlotContents(i));
			}
		}
	}

	public static void applyInfusionForStack(World world, EntityPlayer player, ItemStack holder, EnumEquipmentType type, ItemStack infusion) {
		applyInfusionForItem(world, player, holder, type, infusion, (IInfusionItem) infusion.getItem());
	}

	public static void applyInfusionForItem(World world, EntityPlayer player, ItemStack holder, EnumEquipmentType type, ItemStack infusion, IInfusionItem item) {
		applyInfusion(world, player, holder, type, infusion, item.getInfusionAspect(infusion), item.getPotency(infusion), item.getInfusionType(infusion));
	}

	public static void applyInfusion(World world, EntityPlayer player, ItemStack holder, EnumEquipmentType type, ItemStack infusion, IInfusionAspect aspect, float potency, EnumInfusionType infusionType) {
		//TODO
	}

	public static int getEnergyCapacity(SlotInfusion[] slots) {
		int flux = 0;
		for (SlotInfusion slot : slots) {
			ItemStack contents = slot.getContents();
			if (contents.getItem() instanceof IInfusionItem && ((IInfusionItem) contents.getItem()).getInfusionType(contents) == EnumInfusionType.FLUX) {
				flux += ((IInfusionItem) contents.getItem()).getStorage(contents);
			}
		}
		return flux;
	}

	public static IInfusionAspect getSlotAspect(SlotInfusion slot) {
		return getStackAspect(slot.getContents());
	}

	public static IInfusionAspect getStackAspect(ItemStack stack) {
		//TODO
		return null;
	}

	public static Item getSlotItem(SlotInfusion slot) {
		return null; //TODO
	}

	public static int getSlotPotency(SlotInfusion slot) {
		return 0; ///TODO
	}
}
