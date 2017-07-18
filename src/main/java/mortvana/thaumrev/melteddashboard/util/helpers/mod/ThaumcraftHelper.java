package mortvana.thaumrev.melteddashboard.util.helpers.mod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.melteddashboard.util.helpers.NBTHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static thaumcraft.api.aspects.Aspect.*;

public class ThaumcraftHelper {

	public static int getDiscountForAspect(ItemStack stack, EntityPlayer player, Aspect aspect, int baseDiscount) { //TODO: Make Aspect Sensitive
		return baseDiscount + NBTHelper.getModifierInt(stack, ThaumRevLibrary.VISMODIFIER);
	}

	public static AspectList newPrimalAspectList(int size) {
		return new AspectList().add(FIRE, size).add(WATER, size).add(EARTH, size).add(AIR, size).add(ORDER, size).add(ENTROPY, size);
	}
}
