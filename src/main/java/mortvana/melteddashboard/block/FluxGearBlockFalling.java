package mortvana.melteddashboard.block;

import java.util.List;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.util.helpers.StringHelper;

/**
 *  Basically an extended form of block designed for use with metadata.
 *
 *  @author Mortvana
 */
public class FluxGearBlockFalling extends BlockFalling implements IItemBlockProvider {

	public String[] names;
	public String[] textures;
	@SideOnly(Side.CLIENT)
	public IIcon[] icons;
	public String directory;

	public FluxGearBlockFalling(Material material) {
		super(material);
	}

	public FluxGearBlockFalling setNames(String... names) {
		this.names = names;
		return this;
	}

	public FluxGearBlockFalling setTextures(String... textures) {
		this.textures = textures;
		return this;
	}

	public FluxGearBlockFalling setDirectory(String directory) {
		this.directory = directory;
		return this;
	}

	public FluxGearBlockFalling setData(String directory, String... names) {
		this.directory = directory;
		this.names = names;
		textures = names;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		if (icons == null) {
			icons = new IIcon[textures.length];
		}
		for (int i = 0; i < textures.length; i++) {
			icons[i] = register.registerIcon(directory + StringHelper.camelCase(names[i]));
		}
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int damageDropped(int i) {
		return i;
	}

	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName();
	}

	public int getRarity(int metadata) {
		return 0;
	}
}
