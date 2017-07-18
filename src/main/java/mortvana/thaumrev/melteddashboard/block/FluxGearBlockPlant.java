package mortvana.thaumrev.melteddashboard.block;

import java.util.List;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.thaumrev.melteddashboard.util.helpers.StringHelper;

public class FluxGearBlockPlant extends BlockBush {

	public String[] names;
	public String[] textures;
	public IIcon[] icons;
	public String directory;

	public FluxGearBlockPlant(Material material) {
		super(material);
		setTickRandomly(true);
		setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
	}

	public FluxGearBlockPlant() {
		this(Material.plants);
	}

	public FluxGearBlockPlant setNames(String... names) {
		this.names = names;
		return this;
	}

	public FluxGearBlockPlant setTextures(String... textures) {
		this.textures = textures;
		return this;
	}

	public FluxGearBlockPlant setDirectory(String directory) {
		this.directory = directory;
		return this;
	}

	public FluxGearBlockPlant setData(String directory, String... names) {
		this.directory = directory;
		this.names = names;
		textures = names;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return icons[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		icons = new IIcon[textures.length];
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
}
