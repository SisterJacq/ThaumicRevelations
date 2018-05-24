package mortvana.thaumrev.block;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.block.BlockStorageBase;
import mortvana.melteddashboard.lib.ColorLibrary;
import mortvana.melteddashboard.lib.StringLibrary;
import mortvana.melteddashboard.util.helpers.StringHelper;
import mortvana.melteddashboard.util.helpers.TextureHelper;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class BlockStorageLinked extends BlockStorageBase {

	public int[] indices; //An array of the index to use for a given metadata;
	public int[] meta;
	public String[] locNames;

	public BlockStorageLinked(String name, CreativeTabs tab, int[] indices, int[] meta) {
		super(name, tab);
		this.indices = indices;
		this.meta = meta;
	}

	public BlockStorageLinked(String name, CreativeTabs tab, String directory, String[] names, int[] mine, float[] hardness, float[] resist, int[] light, int[] indices, int[] meta) {
		this(name, tab, indices, meta);
		setData(directory, names, mine, hardness, resist, light);
	}

	public BlockStorageLinked(String name, CreativeTabs tab, String directory, String unlocName, int[] rarity, String[] names, int[] mine, float[] hardness, float[] resist, int[] light, int[] indices, int[] meta) {
		this(name, tab, indices, meta);
		setData(directory, names, mine, hardness, resist, light, unlocName, rarity);
	}

	public BlockStorageLinked(String name, CreativeTabs tab, String directory, String[] names, int[] mine, float[] hardness, float[] resist, int[] light, int[] color, int[] indices, int[] meta) {
		this(name, tab, indices, meta);
		setData(directory, names, mine, hardness, resist, light);
		this.color = color;
		colorBackup = color;
	}

	public BlockStorageLinked(String name, CreativeTabs tab, String directory, String unlocName, int[] rarity, String[] names, int[] mine, float[] hardness, float[] resist, int[] light, int[] color, int[] indices, int[] meta) {
		this(name, tab, indices, meta);
		setData(directory, names, mine, hardness, resist, light, unlocName, rarity);
		this.color = color;
		colorBackup = color;
	}

	public BlockStorageLinked(String name, CreativeTabs tab, String directory, String[] names, int[] mine, float[] hardness, float[] resist, int[] light, int[] color, int[] signal, int[] indices, int[] meta) {
		this(name, tab, indices, meta);
		setData(directory, names, mine, hardness, resist, light);
		this.color = color;
		colorBackup = color;
		this.signal = signal;
	}

	public BlockStorageLinked(String name, CreativeTabs tab, String directory, String unlocName, int[] rarity, String[] names, int[] mine, float[] hardness, float[] resist, int[] light, int[] color, int[] signal, int[] indices, int[] meta) {
		this(name, tab, indices, meta);
		setData(directory, names, mine, hardness, resist, light, unlocName, rarity);
		this.color = color;
		colorBackup = color;
		this.signal = signal;
	}

	@Override
	public BlockStorageBase setData(String directory, String[] names, int[] mine, float[] hardness, float[] resist, int[] light) {
		setData(directory, names);
		this.hardness = hardness;
		this.resist = resist;
		this.light = light;

		for (int i = 0; i < mine.length; i++) {
			setHarvestLevel("pickaxe", mine[i], meta[i]);
		}

		return this;
	}

	@Override
	public BlockStorageBase setData(String directory, String[] names, int[] mine, float[] hardness, float[] resist, int[] light, String unlocName, int[] rarity) {
		setData(directory, names, mine, hardness, resist, light);
		this.unlocName = unlocName;
		this.rarity = rarity;
		return this;
	}

	@Override
	public BlockStorageBase setSignal(int meta, int val) {
		if (signal == null) {
			signal = new int[names.length];
		}
		signal[indices[meta]] = val;
		return this;
	}

	public BlockStorageLinked setCustomNames(String[] names) {
		locNames = names;
		return this;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return hardness[indices[world.getBlockMetadata(x, y, z)]];
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		return resist[indices[world.getBlockMetadata(x, y, z)]];
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return light[indices[world.getBlockMetadata(x, y, z)]];
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		return signal != null ? signal[indices[world.getBlockMetadata(x, y, z)]] : 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return (icons != null && icons[indices[meta]] != null) ? icons[indices[meta]] : grayscale;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		grayscale = register.registerIcon(directory + "grayscale");
		icons = new IIcon[textures.length];
		color = colorBackup;
		for (int i = 0; i < textures.length; i++) {
			if (TextureHelper.blockTextureExists(directory + StringLibrary.BLOCK + StringHelper.camelCase(names[i]))) {
				icons[i] = register.registerIcon(directory + StringLibrary.BLOCK + StringHelper.camelCase(names[i]));
				if (color != null) {
					color[i] = ColorLibrary.CLEAR;
				}
			} else {
				icons[i] = null;
			}
		}
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i : meta) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		return color != null ? color[indices[meta]] & ColorLibrary.CLEAR : ColorLibrary.CLEAR;
	}

	public String getUnlocalizedName(ItemStack stack) {
		return unlocName != null ? unlocName + StringLibrary.BLOCK + (locNames != null ? locNames[indices[stack.getItemDamage()]] : names[indices[stack.getItemDamage()]]) + ".name" : getUnlocalizedName();
	}

	@Override
	public int getRarity(int meta) {
		return (rarity != null && meta < rarity.length && meta >= 0) ? rarity[indices[meta]] : 0;
	}
}
