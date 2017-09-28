package mortvana.melteddashboard.util.helpers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import mortvana.thaumrev.library.ThaumRevLibrary;

public class NBTHelper {

	public static boolean hasKey(ItemStack stack, String key) {
		return stack.hasTagCompound() && stack.getTagCompound().hasKey(key);
	}

	public static boolean stackHasBooleanKey(ItemStack stack, String key) {
		return hasKey(stack, key) && stack.getTagCompound().getBoolean(key);
	}

	public static int getModifierInt(ItemStack stack, String key) {
		return hasKey(stack, key) ? stack.getTagCompound().getInteger(key) : 0;
	}

	public static boolean isRevealingGoggles(ItemStack stack, EntityLivingBase entity) {
		return (entity instanceof EntityPlayer && stack.getItem() instanceof ItemArmor && ((ItemArmor) stack.getItem()).armorType == 0 && stackHasBooleanKey(stack, ThaumRevLibrary.REVEALING));
	}

	public static boolean isBroken(ItemStack stack) {
		return stackHasBooleanKey(stack, ThaumRevLibrary.BROKEN);
	}

	public static ItemStack setBroken(ItemStack stack, boolean bool) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setBoolean(ThaumRevLibrary.BROKEN, bool);
		return stack;
	}

}
