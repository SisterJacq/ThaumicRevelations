package mortvana.thaumicrevelations.melteddashboard.util.helpers;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import gnu.trove.map.TMap;
import mortvana.thaumicrevelations.api.item.infusion.*;
import mortvana.thaumicrevelations.api.util.AspectInfusionBase;
import mortvana.thaumicrevelations.api.util.enums.EnumEquipmentType;
import mortvana.thaumicrevelations.api.util.enums.EnumInfusionType;
import mortvana.thaumicrevelations.api.util.slot.SlotInfusion;
import thaumcraft.api.aspects.Aspect;

public class AspectInfusionHelper {

	public TMap<Aspect, AspectInfusionBase> infusions;

	public static void applyInfusions(World world, EntityPlayer player, ItemStack stack, EnumEquipmentType type) {
		if (stack.getItem() instanceof IInfusableItem) {
			for (int i = 0; i < getSlotsTotal(stack); i++) {
				applyInfusionForStack(world, player, stack, type, getSlotContents(stack, i));
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

	public static void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {}

	public static void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {}

	/**
	 *  Returns the number of locked slots on an item. Locked slots come with pre-assigned aspect infusions.
	 *
	 *  @return - Number of locked slots
	 */
	public static int getSlotsUnlocked(ItemStack stack) {
		return 0; //TODO
	}

	/**
	 *  Returns the number of unlocked slots on an item. Unlocked slots are the standard slots which are taken up by
	 *  players placing IInfusionItems in them.
	 *
	 *  @return - Number of unlocked slots
	 */
	public static int getSlotsLocked(ItemStack stack) {
		return 0; //TODO
	}

	/**
	 *  Returns the total number of slots for infusions on an item. This means both innate equipment infusions, and
	 *  player-chosen infusions.
	 *
	 *  @return - Total number of slots
	 */
	public static int getSlotsTotal(ItemStack stack) {
		return 0; //TODO
	}

	public static void setNumberSlots(ItemStack stack, int unlocked, int locked) {
		//TODO
	}

	/**
	 *  Returns the ItemStack of an Item implementing IInfusionItem in a given slot.
	 *
	 *  @param stack - ItemStack of item implementing IInfusionItem
	 *  @param slot - The numeric ID of the slot being queried
	 *  @return - The ItemStack of an Item implementing IInfusionItem in the queried slot
	 */
	public static ItemStack getSlotContents(ItemStack stack, int slot) {
		return null; //TODO
	}

	public static void setSlotContents(ItemStack stack, int slot) {}

	public static void setLockedSlotContents(ItemStack stack, int slot) {}

	/**
	 *  Returns whether or not a slot is locked. Returns true if it is unlocked, and false if it is locked.
	 *
	 *  @param stack - ItemStack of item implementing IInfusionItem
	 *  @param slot - The numeric ID of the slot being queried
	 *  @return - If the slot is unlocked
	 */
	public static boolean isSlotUnlocked(ItemStack stack, int slot) {
		return true; //TODO
	}

	public static void updateData(ItemStack stack) {}
}
