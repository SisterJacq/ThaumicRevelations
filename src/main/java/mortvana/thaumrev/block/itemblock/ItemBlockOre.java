package mortvana.thaumrev.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.block.FluxGearItemBlockBase;

import mortvana.thaumrev.block.BlockOre;
import mortvana.thaumrev.library.ThaumRevLibrary;

public class ItemBlockOre extends FluxGearItemBlockBase {

	public ItemBlockOre(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.fluxgear.ore." + BlockOre.NAMES[stack.getItemDamage()] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[BlockOre.RARITY[stack.getItemDamage()]];
	}
}
