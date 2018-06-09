import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCrops extends BlockBush implements IGrowable {

	@SideOnly(Side.CLIENT)
	private IIcon[] field_149867_a;

	protected BlockCrops() {
		setTickRandomly(true);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
		setCreativeTab(null);
		setHardness(0.0F);
		setStepSound(soundTypeGrass);
		disableStats();
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		checkAndDropBlock(world, x, y, z);

		if (world.getBlockLightValue(x, y + 1, z) >= 9) {
			int meta = world.getBlockMetadata(x, y, z);

			if (meta < 7) {
				float f = growthRate(world, x, y, z);

				if (random.nextInt((int)(25.0F / f) + 1) == 0) {
					meta++;
					world.setBlockMetadataWithNotify(x, y, z, meta, 2);
				}
			}
		}
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType() {
		return 6;
	}

	/**
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return this.getSeed();
	}

	/** Drops **/
	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float par6, int fortune) {
		super.dropBlockAsItemWithChance(world, x, y, z, metadata, par6, 0);
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return meta == 7 ? getProduce() : getSeed();
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

		if (metadata >= 7) {
			for (int i = 0; i < 3 + fortune; ++i) {
				if (world.rand.nextInt(15) <= metadata) {
					ret.add(new ItemStack(getSeed(), 1, 0));
				}
			}
		}

		return ret;
	}

	/** BlockBush **/
	@Override
	protected boolean canPlaceBlockOn(Block block) {
		return block == Blocks.farmland;
	}

	/** Texture Stuff **/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		field_149867_a = new IIcon[8];

		for (int i = 0; i < field_149867_a.length; ++i) {
			field_149867_a[i] = register.registerIcon(getTextureName() + "_stage_" + i);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta < 0 || meta > 7) {
			meta = 7;
		}

		return field_149867_a[meta];
	}

	/** IGrowable **/
	@Override //isNotFullyGrown
	public boolean func_149851_a(World world, int x, int y, int z, boolean isRemote) {
		return world.getBlockMetadata(x, y, z) != 7;
	}

	@Override //canUseBonemeal
	public boolean func_149852_a(World world, Random random, int x, int y, int z) {
		return true;
	}

	@Override //onUseBonemeal
	public void func_149853_b(World world, Random random, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z) + world.rand.nextInt(4) + 2;

		if (meta > 7) {
			meta = 7;
		}

		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}

	private float growthRate(World world, int x, int y, int z) {
		float f = 1.0F;
		Block block = world.getBlock(x, y, z - 1);
		Block block1 = world.getBlock(x, y, z + 1);
		Block block2 = world.getBlock(x - 1, y, z);
		Block block3 = world.getBlock(x + 1, y, z);
		Block block4 = world.getBlock(x - 1, y, z - 1);
		Block block5 = world.getBlock(x + 1, y, z - 1);
		Block block6 = world.getBlock(x + 1, y, z + 1);
		Block block7 = world.getBlock(x - 1, y, z + 1);
		boolean flag = block2 == this || block3 == this;
		boolean flag1 = block == this || block1 == this;
		boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

		for (int l = x - 1; l <= x + 1; ++l) {
			for (int i1 = z - 1; i1 <= z + 1; ++i1) {
				float f1 = 0.0F;

				if (world.getBlock(l, y - 1, i1).canSustainPlant(world, l, y - 1, i1, ForgeDirection.UP, this)) {
					f1 = 1.0F;

					if (world.getBlock(l, y - 1, i1).isFertile(world, l, y - 1, i1)) {
						f1 = 3.0F;
					}
				}

				if (l != x || i1 != z) {
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		if (flag2 || flag && flag1) {
			f /= 2.0F;
		}

		return f;
	}

	protected Item getSeed() {
		return Items.wheat_seeds;
	}

	protected Item getProduce() {
		return Items.wheat;
	}


}