package mortvana.melteddashboard.block;

import net.minecraft.block.Block;
import net.minecraft.item.*;

import mortvana.melteddashboard.util.helpers.StringHelper;

/**
 *  Basically an extended form of block designed for use with metadata.
 *
 *  @author Mortvana
 */
public class FluxGearItemBlock extends ItemBlock {

	public FluxGearItemBlock(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return StringHelper.localize(getUnlocalizedName(stack));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return field_150939_a instanceof IItemBlockProvider ? ((IItemBlockProvider) field_150939_a).getUnlocalizedName(stack) : getUnlocalizedName();

	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return field_150939_a instanceof IItemBlockProvider ? EnumRarity.values()[((IItemBlockProvider) field_150939_a).getRarity(stack.getItemDamage())] : EnumRarity.common;
	}
}