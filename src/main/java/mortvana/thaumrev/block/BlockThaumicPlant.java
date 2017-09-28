package mortvana.thaumrev.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.melteddashboard.block.FluxGearBlockPlant;

public class BlockThaumicPlant extends FluxGearBlockPlant {

	public BlockThaumicPlant() {
		super(Material.plants);
		setBlockName("blockThaumicPlant");
		setCreativeTab(ThaumRevLibrary.thaumicRevelationsTab);
		setStepSound(Block.soundTypeGrass);
		setData(ThaumRevLibrary.TEX_LOC_DEFAULT + "plant/", ThaumRevLibrary.PLANT_NAMES);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();

		/*switch (meta) {
			case 0:*/
				list.add(ThaumRevLibrary.excubituraPetal);
		//}

		return list;
	}
}
