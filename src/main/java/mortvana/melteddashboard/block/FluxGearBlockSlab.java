package mortvana.melteddashboard.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.util.helpers.StringHelper;

public class FluxGearBlockSlab extends BlockSlab {

	public String[] names;
	public String[] textures;
	@SideOnly(Side.CLIENT)
	public IIcon[] icons;
	public String directory;
	public Block slab;

	public FluxGearBlockSlab(boolean fullBlock, Material material) {
		super(fullBlock, material);
	}

	@Override
	public String func_150002_b(int par1) {
		if (par1 < 0 || par1 >= names.length) {
			par1 = 0;
		}

		return super.getUnlocalizedName() + "." + names[par1];
	}

	public FluxGearBlockSlab setNames(String... names) {
		this.names = names;
		return this;
	}

	public FluxGearBlockSlab setTextures(String... textures) {
		this.textures = textures;
		return this;
	}

	public FluxGearBlockSlab setDirectory(String directory) {
		this.directory = directory;
		return this;
	}

	public FluxGearBlockSlab setData(String directory, String... names) {
		this.directory = directory;
		this.names = names;
		textures = names;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return icons[metadata & 7];
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
		if (!field_150004_a) {
			for (int i = 0; i < names.length; i++) {
				list.add(new ItemStack(item, 1, i));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return !field_150004_a ? Item.getItemFromBlock(this) : slab != null ? Item.getItemFromBlock(slab) : Items.potato;
	}

	@Override
	public ItemStack createStackedBlock(int meta) {
		return !field_150004_a ? new ItemStack(this, 1, meta) : slab != null ? new ItemStack(slab, 1, meta) : new ItemStack(Items.potato);
	}

	/*public static boolean isSlab(Block block) {
		return block instanceof BlockSlab && !((BlockSlab) block).field_150004_a;
	}*/

	@Override
	public Item getItemDropped(int par1, Random par2, int par3) {
		return !field_150004_a ? Item.getItemFromBlock(this) : slab != null ? Item.getItemFromBlock(slab) : Items.potato;
	}
}
