package mortvana.thaumrev.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.melteddashboard.item.FluxGearItemInteractive;

public class ItemThaumRev extends FluxGearItemInteractive {

	public ItemThaumRev() {
		modName = ThaumRevLibrary.RESOURCE_PREFIX;
		setCreativeTab(ThaumRevLibrary.thaumicRevelationsTab);
		setFolder("material");
		setUnlocalizedName("material");
	}

	@Override
	public boolean rightClickActions(int meta, ItemStack stack, World world, EntityPlayer player, boolean used){
		return used;
	}

	@Override
	public void playSound(int metadata, World world, EntityPlayer player) {
		world.playSoundAtEntity(player, "stone", 1.0F, 1.0F);
	}
}
