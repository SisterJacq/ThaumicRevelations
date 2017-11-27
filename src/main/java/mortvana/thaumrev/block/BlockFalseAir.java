package mortvana.thaumrev.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.block.FluxGearBlockBase;

import mortvana.thaumrev.library.ThaumRevLibrary;

import static mortvana.thaumrev.library.ThaumRevLibrary.FALSE_AIR_NAMES;
import static mortvana.thaumrev.library.ThaumRevLibrary.TEX_LOC_DEFAULT;

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
