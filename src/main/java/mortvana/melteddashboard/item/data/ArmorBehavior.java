package mortvana.melteddashboard.item.data;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

/**
 *	Allows using our armor system with custom behaviors, which can in theory be easily modified through in-game
 *	mechanics. For a default implementation, see ArmorBehaviorBase.
 *
 *	@Author - Mortvana
 */
public abstract class ArmorBehavior {

	/**
	 *	Allows for implementations of onArmorTick, for things like auto-repair or whatever else you think of.
	 *
	 *	@param world
	 *	@param player
	 *	@param armor
	 */
	public abstract void onArmorTick(World world, EntityPlayer player, ItemStack armor);

	public abstract ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot);
}
