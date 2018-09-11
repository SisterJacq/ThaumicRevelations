package mortvana.melteddashboard.item.data;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

import mortvana.thaumrev.api.item.infusion.IInfusableArmor;
import mortvana.thaumrev.api.item.infusion.IInfusableItem;

public class ArmorBehaviorBase extends ArmorBehavior {

	public static ArmorBehaviorBase instance = new ArmorBehaviorBase();

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		//By default does absolutely nothing!
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, ((IInfusableArmor) armor.getItem()).getArmorValue() / 25D, 20);
	}
}
