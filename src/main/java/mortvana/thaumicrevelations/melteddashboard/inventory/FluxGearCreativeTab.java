package mortvana.thaumicrevelations.melteddashboard.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 *  A simple extension of net.minecraft.creativetabs.CreativeTabs to allow for simple creative tabs. No more needing to
 *  make a class for each tab!
 *
 *  @author Mortvana
 */
public class FluxGearCreativeTab extends CreativeTabs {


	public FluxGearCreativeTab(String internalName, String label, ItemStack icon) {
		super(internalName);
		name = label;
		display = icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return display == null ? new ItemStack(Items.potato) : display;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return display.getItem() == null ? Items.potato : display.getItem();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTabLabel() {
		return name;
	}

	private ItemStack display;
	private String name;
}
