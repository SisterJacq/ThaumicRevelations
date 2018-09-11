package mortvana.melteddashboard.item.data;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

import mortvana.thaumrev.api.item.infusion.IInfusableArmor;

public class ArmorBehaviorFlux extends ArmorBehavior {

	public static ArmorBehaviorFlux instance = new ArmorBehaviorFlux();

	public static final ArmorProperties FLUX = new ArmorProperties(0, 0.20D, Integer.MAX_VALUE);

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		//Still nothing
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack stack, DamageSource source, double damage, int slot) {
		IInfusableArmor container = (IInfusableArmor) stack.getItem();
		ItemArmor armor = (ItemArmor) stack.getItem();

		if (source.damageType.equals("flux")) {
			return FLUX;
		} else if (source.isUnblockable()) {
			int absorbMax = container.getEnergyPerDamage(stack) > 0 ? 25 * container.getEnergyStored(stack) / container.getEnergyPerDamage(stack) : 0;
			return new ArmorProperties(0, container.getData().getAbsorbRatio() * armor.getArmorMaterial().getDamageReductionAmount(armor.armorType) * 0.025, absorbMax);
		}
		int absorbMax = container.getEnergyPerDamage(stack) > 0 ? 25 * container.getEnergyStored(stack) / container.getEnergyPerDamage(stack) : 0;
		return new ArmorProperties(0, container.getData().getAbsorbRatio() * armor.getArmorMaterial().getDamageReductionAmount(armor.armorType) * 0.05, absorbMax);
	}
}
