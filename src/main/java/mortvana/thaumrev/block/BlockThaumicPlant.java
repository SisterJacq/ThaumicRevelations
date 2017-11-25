package mortvana.thaumrev.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.melteddashboard.block.FluxGearBlockPlant;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockThaumicPlant extends FluxGearBlockPlant {

	public BlockThaumicPlant() {
		super(Material.plants);
		setBlockName("blockThaumicPlant");
		setCreativeTab(thaumicRevelationsTab);
		setStepSound(Block.soundTypeGrass);
		setData(TEX_LOC_DEFAULT + "plant/", PLANT_NAMES);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();

		switch (meta) {
			case 0:
				list.add(excubituraPetal);
            case 1:
                list.add(cotton);
		}

		return list;
	}

    public void register() {
        excubituraRose = new ItemStack(blockThaumicPlant, 1, 0);
        wildCotton = new ItemStack(blockThaumicPlant, 1, 1);
        wildThistle = new ItemStack(blockThaumicPlant, 1, 2);
    }
}
