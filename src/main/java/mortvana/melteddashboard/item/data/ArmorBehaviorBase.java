package mortvana.melteddashboard.item.data;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ArmorBehaviorBase extends ArmorBehavior {

	public static ArmorBehaviorBase instance = new ArmorBehaviorBase();

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		//By default does absolutely nothing!
	}
}
