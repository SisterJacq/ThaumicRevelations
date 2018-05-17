package mortvana.melteddashboard.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.lib.ColorLibrary;
import mortvana.melteddashboard.util.helpers.StringHelper;
import mortvana.melteddashboard.util.helpers.TextureHelper;

public class BlockStorageBase extends FluxGearBlockBase {

	public IIcon grayscale;
	public float[] hardness;
	public float[] resist;
	public int[] light;
	public int[] color = null;
	public int[] colorBackup = null;
	public int[] signal = null;

	public BlockStorageBase(String name, CreativeTabs tab) {
		super(Material.iron);
		setStepSound(soundTypeMetal);
		setCreativeTab(tab);
		setBlockName(name);
	}

	public BlockStorageBase(String name, CreativeTabs tab, String directory, String[] names, int[] mine, float[] hardness, float[] resist, int[] light) {
		this(name, tab);
		setData(directory, names, mine, hardness, resist, light);
	}

	public BlockStorageBase(String name, CreativeTabs tab, String directory, String[] names, int[] mine, float[] hardness, float[] resist, int[] light, int[] color) {
		this(name, tab);
		setData(directory, names, mine, hardness, resist, light);
		this.color = color;
		colorBackup = color;
	}

	public BlockStorageBase(String name, CreativeTabs tab, String directory, String[] names, int[] mine, float[] hardness, float[] resist, int[] light, int[] color, int[] signal) {
		super(Material.iron);
		setStepSound(soundTypeMetal);
		setCreativeTab(tab);
		setBlockName(name);
		setData(directory, names, mine, hardness, resist, light);
		this.color = color;
		colorBackup = color;
		this.signal = signal;
	}

	public BlockStorageBase setData(String directory, String[] names, int[] mine, float[] hardness, float[] resist, int[] light) {
		setData(directory, names);
		this.hardness = hardness;
		this.resist = resist;
		this.light = light;

		for (int i = 0; i < mine.length; i++) {
			setHarvestLevel("pickaxe", mine[i], i);
		}

		return this;
	}

	public BlockStorageBase setSignal(int meta, int val) {
		if (signal == null) {
			signal = new int[names.length];
		}
		signal[meta] = val;
		return this;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return hardness[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		return resist[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return light[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		return signal != null ? signal[world.getBlockMetadata(x, y, z)] : 0;
	}

	@Override
	public boolean canProvidePower() {
		return true;
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return false;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		return world.getBlock(x, y, z) == this;
	}

	@Override
	public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return (icons != null && icons[meta] != null) ? icons[meta] : grayscale;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		grayscale = register.registerIcon(directory + "grayscale");
		icons = new IIcon[textures.length];
		color = colorBackup;
		for (int i = 0; i < textures.length; i++) {
			if (TextureHelper.blockTextureExists(directory + StringHelper.camelCase(names[i]))) {
				icons[i] = register.registerIcon(directory + StringHelper.camelCase(names[i]));
				if (color != null) {
					color[i] = ColorLibrary.CLEAR;
				}
			} else {
				icons[i] = null;
			}
		}
	}

	/**
	 * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
	 * when first determining what to render.
	 */
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return getRenderColor(world.getBlockMetadata(x, y, z));
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		return color != null ? color[meta] & ColorLibrary.CLEAR : ColorLibrary.CLEAR;
	}
}
