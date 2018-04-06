package mortvana.thaumrev.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.block.FluxGearItemBlockBase;

import mortvana.thaumrev.block.BlockThaumicPlant;
import mortvana.thaumrev.library.ThaumRevLibrary;

public class ItemBlockThaumicPlant extends FluxGearItemBlockBase {

	public ItemBlockThaumicPlant(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.thaumrev.plant." + BlockThaumicPlant.NAMES[stack.getItemDamage()] + ".name";
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return field_150939_a.getIcon(2, meta);
	}

}
