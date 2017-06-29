package mortvana.thaumicrevelations.core.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.thaumicrevelations.library.ThaumicLibrary;
import mortvana.thaumicrevelations.melteddashboard.intermod.baubles.item.FluxGearItemBauble;

public class ItemThaumicBauble extends FluxGearItemBauble {

	public ItemThaumicBauble() {
		super(ThaumicLibrary.RESOURCE_PREFIX, ThaumicLibrary.thaumicRevelationsTab);
        setFolder("/baubles");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (itemstack.getItemDamage() == 1) {
			world.playSoundAtEntity(player, ThaumicLibrary.RESOURCE_PREFIX + ":abderp", 1, 1);
		}
		return super.onItemRightClick(itemstack, world, player);
	}
}