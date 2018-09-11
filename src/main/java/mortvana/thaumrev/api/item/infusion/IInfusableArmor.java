package mortvana.thaumrev.api.item.infusion;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ISpecialArmor;

import mortvana.melteddashboard.item.data.ArmorDataAdv;

public interface IInfusableArmor extends IInfusableItem, ISpecialArmor {

	public abstract int getArmorValue();

	public abstract int getEnergyPerDamage(ItemStack stack);

	public abstract ArmorDataAdv getData();
}
