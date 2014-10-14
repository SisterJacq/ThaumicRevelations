package mortvana.trevelations.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;

public class WardenicUpgradeHeal extends WardenicUpgrade {

	public WardenicUpgradeHeal(Aspect aspect) {super(aspect);}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {

		super.onAttack(stack, player, entity);

		player.heal(2);

	}

}
