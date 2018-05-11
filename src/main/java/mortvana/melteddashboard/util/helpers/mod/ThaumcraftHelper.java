package mortvana.melteddashboard.util.helpers.mod;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.melteddashboard.util.helpers.*;
import mortvana.thaumrev.util.enums.EnumPrimalAspect;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.entities.monster.*;

import static thaumcraft.api.aspects.Aspect.*;

public class ThaumcraftHelper {

	public static final Aspect[] PRIMALS = new Aspect[] { AIR, FIRE, WATER, EARTH, ORDER, ENTROPY };

	/** VIS DISCOUNTS **/
	public static int getDiscountForAspect(ItemStack stack, EntityPlayer player, Aspect aspect, int baseDiscount) {
		return baseDiscount + NBTHelper.getModifierInt(stack, ThaumRevLibrary.VISMODIFIER + EnumPrimalAspect.getString(aspect));
	}

	public static AspectList newPrimalAspectList(int size) {
		return new AspectList().add(AIR, size).add(FIRE, size).add(WATER, size).add(EARTH, size).add(ORDER, size).add(ENTROPY, size);
	}

	public static AspectList newPrimalAspectList(int aer, int ignis, int aqua, int terra, int ordo, int perditio) {
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

	/** PURITY **/
	public static boolean isTainted(Entity entity) {
		return entity instanceof ITaintedMob;
	}

	public static boolean isTainted(MovingObjectPosition mop) {
		return mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mop.entityHit != null && isTainted(mop.entityHit);
	}

	public static void purifyEntity(Entity toPurify) {
		if (toPurify != null) {
			World world = toPurify.worldObj;
			if (isTainted(toPurify) && !world.isRemote) {
				Entity purified = getPureState(toPurify);
				purified.setPositionAndRotation(toPurify.posX, toPurify.posY, toPurify.posZ, toPurify.rotationYaw, toPurify.rotationPitch);

				toPurify.setDead();
				world.spawnEntityInWorld(purified);
			}
		}
	}

	public static void checkAndPurify(MovingObjectPosition mop) {
		if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
			purifyEntity(mop.entityHit);
		}
	}

	public static Entity getPureState(Entity entity) {
		if (entity instanceof EntityTaintChicken) {
			return new EntityChicken(entity.worldObj);
		}
		if (entity instanceof EntityTaintCow) {
			return new EntityCow(entity.worldObj);
		}
		if (entity instanceof EntityTaintCreeper) {
			return new EntityCreeper(entity.worldObj);
		}
		if (entity instanceof EntityTaintPig) {
			return new EntityPig(entity.worldObj);
		}
		if (entity instanceof EntityTaintSheep) {
			return new EntitySheep(entity.worldObj);
		}
		if (entity instanceof EntityTaintSpider) {
			return new EntitySpider(entity.worldObj);
		}
		if (entity instanceof EntityTaintVillager) {
			return new EntityVillager(entity.worldObj);
		}

		return entity;
	}
}
