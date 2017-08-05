package mortvana.thaumrev.melteddashboard.util.helpers.mod;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.melteddashboard.util.helpers.*;
import mortvana.thaumrev.util.enums.EnumPrimalAspect;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static thaumcraft.api.aspects.Aspect.*;

public class ThaumcraftHelper {

	public static final Aspect[] PRIMALS = new Aspect[] { AIR, FIRE, WATER, EARTH, ORDER, ENTROPY };

	public static int getDiscountForAspect(ItemStack stack, EntityPlayer player, Aspect aspect, int baseDiscount) { //TODO: Make Aspect Sensitive
		return baseDiscount + NBTHelper.getModifierInt(stack, ThaumRevLibrary.VISMODIFIER + EnumPrimalAspect.getString(aspect));
	}

	public static AspectList newPrimalAspectList(int size) {
		return new AspectList().add(AIR, size).add(FIRE, size).add(WATER, size).add(EARTH, size).add(ORDER, size).add(ENTROPY, size);
	}

	public static AspectList newPrimalAspectList(int aer, int ignis, int aqua, int terra,  int ordo, int perditio) {
		AspectList list = new AspectList();
		if (aer > 0) {
			list.add(AIR, aer);
		}
		if (ignis > 0) {
			list.add(FIRE, ignis);
		}
		if (aqua > 0) {
			list.add(WATER, aqua);
		}
		if (terra > 0) {
			list.add(EARTH, terra);
		}
		if (ordo > 0) {
			list.add(ORDER, ordo);
		}
		if (perditio > 0) {
			list.add(ENTROPY, perditio);
		}

		return list;
	}

	public static void addDiscountInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (stack.getItem() instanceof IVisDiscountGear) {
			int[] discounts = new int[6];
			IVisDiscountGear gear = (IVisDiscountGear) stack.getItem();
			for (int i = 0; i < 6; i++) {
				discounts[i] = gear.getVisDiscount(stack, player, PRIMALS[i]);
			}
			if (ArrayHelper.areAllValuesEqual(discounts)) {
				if (discounts[0] != 0) {
					list.add(visDiscount(discounts[0]));
				}
				return;
			}
			int spec = ArrayHelper.getOddValue(discounts);
			if (spec != -1) {
				list.add(visDiscount(spec == 0 ? discounts[1] : discounts[0]));
				list.add(visDiscount(discounts[spec], PRIMALS[spec]));
			} else {
				for (int i = 0; i < 6; i++) {
					list.add(visDiscount(discounts[i], PRIMALS[i]));
				}
			}
		}
	}

	public static String visDiscount(int discount) {
		return StringHelper.PURPLE + StringHelper.localize("tc.visdiscount") + ": " + discount + "%";
	}

	public static String visDiscount(int discount, Aspect aspect) {
		if (aspect == null) {
			return StringHelper.PURPLE + StringHelper.localize("tc.visdiscount") + ": " + discount + "%";
		} else if (aspect.isPrimal()) {
			return StringHelper.PURPLE + StringHelper.localize("tc.visdiscount") + " (" + EnumPrimalAspect.getString(aspect) + "): " + discount + "%";
		} else {
			return null;
		}
	}
}
