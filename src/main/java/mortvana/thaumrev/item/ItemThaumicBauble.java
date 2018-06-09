package mortvana.thaumrev.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.melteddashboard.intermod.baubles.item.FluxGearItemBauble;
import mortvana.melteddashboard.util.libraries.StringLibrary;

import mortvana.thaumrev.library.ThaumRevLibrary;

public class ItemThaumicBauble extends FluxGearItemBauble {

	public ItemThaumicBauble() {
		super(StringLibrary.RESOURCE_PREFIX, ThaumRevLibrary.generalTab);
		setFolder("baubles");
		setUnlocalizedName("bauble");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (itemstack.getItemDamage() == 1) {
			world.playSoundAtEntity(player, StringLibrary.RESOURCE_PREFIX + ":abderp", 1, 1);
		}
		return super.onItemRightClick(itemstack, world, player);
	}
}