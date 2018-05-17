package mortvana.thaumrev.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.block.FluxGearItemBlockBase;
import mortvana.melteddashboard.lib.ColorLibrary;
import mortvana.melteddashboard.lib.StringLibrary;

public class ItemBlockStorage extends FluxGearItemBlockBase {

	public String[] names;
	public int[] rarities;

	public ItemBlockStorage(Block block, String[] names, int[] rarities) {
		super(block);
		this.names = names;
		this.rarities = rarities;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fluxgear.storage." + StringLibrary.BLOCK + names[stack.getItemDamage()] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[rarities[stack.getItemDamage()]];
	}

}