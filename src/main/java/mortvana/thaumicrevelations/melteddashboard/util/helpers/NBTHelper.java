package mortvana.thaumicrevelations.melteddashboard.util.helpers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import mortvana.thaumicrevelations.library.ThaumicLibrary;

public class NBTHelper {

	public static boolean isRevealingGoggles(ItemStack stack, EntityLivingBase entity) {
		return (entity instanceof EntityPlayer && stack.getItem() instanceof ItemArmor && ((ItemArmor) stack.getItem()).armorType == 0 && stack.hasTagCompound() && stack.getTagCompound().hasKey(ThaumicLibrary.REVEALING) && stack.getTagCompound().getBoolean(ThaumicLibrary.REVEALING));
	}

	public static boolean isBroken(ItemStack stack) {
		return (stack.getTagCompound().hasKey(ThaumicLibrary.BROKEN) && stack.getTagCompound().getBoolean(ThaumicLibrary.BROKEN));
	}

	public static ItemStack setBroken(ItemStack stack, boolean bool) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setBoolean(ThaumicLibrary.BROKEN, bool);
		return stack;
	}

}
