package mortvana.thaumrev.block;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.lib.ColorLibrary;
import mortvana.melteddashboard.lib.StringLibrary;
import mortvana.melteddashboard.util.helpers.StringHelper;
import mortvana.melteddashboard.util.helpers.TextureHelper;

import static mortvana.melteddashboard.util.helpers.TextureHelper.blockTextureExists;

public class BlockStorageGem extends BlockStorageLinked {

	public BlockStorageGem(String name, CreativeTabs tab, String directory, String unlocName, int[] rarity, String[] names, int[] mine, float[] hardness, float[] resist, int[] light, int[] color, int[] indices, int[] meta) {
		super(name, tab, directory, unlocName, rarity, names, mine, hardness, resist, light, color, indices, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		grayscale = register.registerIcon(directory + "grayscale");
		icons = new IIcon[textures.length + 4];
		color = colorBackup;
		for (int i = 0; i < textures.length - 2; i++) {
			if (TextureHelper.blockTextureExists(directory + StringLibrary.BLOCK + StringHelper.camelCase(names[i]))) {
				icons[i] = register.registerIcon(directory + StringLibrary.BLOCK + StringHelper.camelCase(names[i]));
			} else {
				icons[i] = null;
			}
		}

		quartzIcons(register, 7);
		quartzIcons(register, 8);
	}

	public void quartzIcons(IIconRegister register, int index) {
		if (blockTextureExists(directory + StringLibrary.BLOCK + StringHelper.camelCase(names[index] + quartz[0])) &&
			blockTextureExists(directory + StringLibrary.BLOCK + StringHelper.camelCase(names[index] + quartz[1])) &&
			blockTextureExists(directory + StringLibrary.BLOCK + StringHelper.camelCase(names[index] + quartz[2]))) {
			color[index] = ColorLibrary.CLEAR;
		}

		for (int i = 0; i < quartz.length; i++) {
			if (color[index] == ColorLibrary.CLEAR) {
				icons[index + (2 * i)] = register.registerIcon(directory + StringLibrary.BLOCK + names[index] + quartz[i]);
			} else if (blockTextureExists(directory + StringLibrary.BLOCK + "Quartz")) {
				icons[index + (2 * i)] = register.registerIcon(directory + StringLibrary.BLOCK + "Quartz" + quartz[i]);
			} else {
				icons[index + (2 * i)] = register.registerIcon("quartz_block" + quartz[i]);
			}
		}
	}

	public static String[] quartz = new String[] { "_side", "_top", "_bottom" };
}
