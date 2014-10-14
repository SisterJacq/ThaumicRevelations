package mortvana.trevelations.util.wardenic.upgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import thaumcraft.api.aspects.Aspect;

public class WardenicUpgradeFire extends WardenicUpgrade {

	public WardenicUpgradeFire(Aspect aspect) {super(aspect);}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {

		super.onAttack(stack, player, entity);

		entity.setFire(5);

	}

	@Override
	public void onTick(World world, EntityPlayer player, ItemStack stack) {

		super.onTick(world, player, stack);

		if (player.isBurning()) {

			player.extinguish();

		}

	}

	@Override
	public void onAttacked(LivingHurtEvent event) {

		super.onAttacked(event);

		if (event.source.getEntity() != null) {

			event.source.getEntity().setFire(2);

		}

	}
}
