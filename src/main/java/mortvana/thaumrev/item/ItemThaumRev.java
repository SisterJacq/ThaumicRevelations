package mortvana.thaumrev.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import mortvana.melteddashboard.item.FluxGearItemInteractive;
import mortvana.melteddashboard.lib.StringLibrary;

import mortvana.thaumrev.entity.EntitySingularity;

import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class ItemThaumRev extends FluxGearItemInteractive {

	public ItemThaumRev() {
		super(StringLibrary.RESOURCE_PREFIX, generalTab);
		setFolder("material");
		setUnlocalizedName("material");
	}

	@Override
	public boolean rightClickActions(int meta, ItemStack stack, World world, EntityPlayer player, boolean used) {
		//TODO: Fix crashing
		if (meta == arcaneSingularity.getItemDamage()) {
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
}
