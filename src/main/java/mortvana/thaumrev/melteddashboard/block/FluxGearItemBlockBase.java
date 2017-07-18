package mortvana.thaumrev.melteddashboard.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import mortvana.thaumrev.melteddashboard.util.helpers.StringHelper;

/**
 *  Basically an extended form of block designed for use with metadata.
 *
 *  @author Mortvana
 */
public abstract class FluxGearItemBlockBase extends ItemBlock {

	public FluxGearItemBlockBase(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return StringHelper.localize(getUnlocalizedName(stack));
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}
