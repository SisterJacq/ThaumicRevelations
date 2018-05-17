package mortvana.thaumrev.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.block.FluxGearBlockBase;
import mortvana.melteddashboard.lib.StringLibrary;

import mortvana.thaumrev.library.ThaumRevLibrary;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockFalseAir extends FluxGearBlockBase {

	public BlockFalseAir() {
		super(ThaumRevLibrary.materialFalseAir);
		setStepSound(Block.soundTypeCloth);
		setBlockName("thaumrev.falseAir");

		setData(StringLibrary.DIR_DEFAULT , NAMES);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		icons[0] = register.registerIcon("thaumcraft:blank");
	}

	public static final String[] NAMES = { "witor" };

}
