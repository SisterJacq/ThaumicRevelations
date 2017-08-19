package mortvana.thaumrev.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import mortvana.thaumrev.entity.EntityPurity;
import mortvana.thaumrev.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import mortvana.thaumrev.util.item.ItemFocusBase;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemFocusPurity extends ItemFocusBase {

	public ItemFocusPurity() {
		super("Purity", 0x6698FF, true);
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack stack, World world, EntityPlayer player, MovingObjectPosition mop) {

		ItemWandCasting wand = (ItemWandCasting) stack.getItem();
		EntityPurity purityOrb = new EntityPurity(world, player);
		if (!world.isRemote) {
			if (wand.consumeAllVis(stack, player, getVisCost(stack), true, false)) {
				world.spawnEntityInWorld(purityOrb);
				world.playSoundAtEntity(purityOrb, "thaumcraft:ice", 0.3F, 0.8F + world.rand.nextFloat() * 0.1F);
			}
		}
		player.swingItem();
		return stack;
	}

	@Override
	public AspectList getVisCost(ItemStack stack) {
		return ThaumcraftHelper.newPrimalAspectList(500, 750, 500, 750, 2500, 500);
	}

	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack stack, int i) {
		return new FocusUpgradeType[0];
	}
}
