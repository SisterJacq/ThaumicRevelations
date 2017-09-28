package mortvana.thaumrev.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.melteddashboard.block.FluxGearBlockBase;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockFalseAir extends FluxGearBlockBase {

	public BlockFalseAir() {
		super(ThaumRevLibrary.materialFalseAir);
		setStepSound(Block.soundTypeCloth);
		setBlockName("thaumrev.falseAir");

		setData(TEX_LOC_DEFAULT, FALSE_AIR_NAMES);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		icons[0] = register.registerIcon("thaumcraft:blank");
	}


}
