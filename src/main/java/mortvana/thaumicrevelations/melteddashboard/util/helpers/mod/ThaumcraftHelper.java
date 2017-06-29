package mortvana.thaumicrevelations.melteddashboard.util.helpers.mod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import mortvana.thaumicrevelations.library.ThaumicLibrary;
import mortvana.thaumicrevelations.melteddashboard.util.helpers.NBTHelper;
import thaumcraft.api.aspects.Aspect;

public class ThaumcraftHelper {

	public static int getDiscountForAspect(ItemStack stack, EntityPlayer player, Aspect aspect, int baseDiscount) {
		return baseDiscount + NBTHelper.getModifierInt(stack, ThaumicLibrary.VISMODIFIER);
	}
}
