package mortvana.thaumrev.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.melteddashboard.item.FluxGearItemInteractive;
import mortvana.melteddashboard.util.libraries.StringLibrary;

import mortvana.thaumrev.entity.EntitySingularity;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class ItemThaumRev extends FluxGearItemInteractive implements IPlantable {

	public ItemThaumRev() {
		super(StringLibrary.RESOURCE_PREFIX, generalTab);
		setFolder("material");
		setUnlocalizedName("material");
	}

	@Override
	public boolean onItemUse (ItemStack stack, EntityPlayer player, World world, int xPos, int yPos, int zPos, int side, float xClick, float yClick, float zClick) {
		int meta = stack.getItemDamage();
		if (isSeed(meta) && side == 1 && player.canPlayerEdit(xPos, yPos, zPos, side, stack) && player.canPlayerEdit(xPos, yPos + 1, zPos, side, stack)) {
			Block soil =  world.getBlock(xPos, yPos, zPos);

			if (soil != null && soil.canSustainPlant(world, xPos, yPos, zPos, ForgeDirection.UP, this) && world.isAirBlock(xPos, yPos + 1, zPos)) {
				world.setBlock(xPos, yPos + 1, zPos, getCropBlock(meta), getCropMeta(meta), 3);
				stack.stackSize--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean rightClickActions(int meta, ItemStack stack, World world, EntityPlayer player, boolean used) {
		//TODO: Fix crashing
		if (meta == itemArcaneSingularity.getItemDamage()) {
	        world.playSoundAtEntity(player, "random.bow", 0.3F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
                world.spawnEntityInWorld(new EntitySingularity(world, player)); //TODO: Make more than extra-strength Alumentum
            }
            return true;
        }
		return used;
	}

	@Override
	public void playSound(int metadata, World world, EntityPlayer player) {
		world.playSoundAtEntity(player, "stone", 1.0F, 1.0F);
	}

	@Override
	public Entity createEntity(World world, Entity entityItem, ItemStack stack) {
		if (stack.getItemDamage() == wardenicHardener.getItemDamage()) {
			entityItem.isImmuneToFire = true;
		}
		return super.createEntity(world, entityItem, stack);
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if (entityItem.getEntityItem().getItemDamage() == wardenicHardener.getItemDamage()) {
			int x = MathHelper.floor_double(entityItem.posX);
			int y = MathHelper.floor_double(entityItem.posY + 0.1F);
			int z = MathHelper.floor_double(entityItem.posZ);
			Block block = entityItem.worldObj.getBlock(x, y, z);

			if (block == Blocks.lava) {
				entityItem.worldObj.setBlock(x, y, z, blockStoneDecor, 0, 3);
				entityItem.setDead();
			}
		}
		return false;
	}

	public static boolean isSeed(int meta) {
		return meta == 951 || meta == 952;//meta <= 950 && meta >= 957;
	}

	public static Block getCropBlock(int meta) {
		return blockMundaneCrop;
	}

	public static int getCropMeta(int meta) {
		return meta == 952 ? 8 : 0;
	}

	//TODO
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	//TODO
	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return null;
	}

	//TODO
	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}
}
