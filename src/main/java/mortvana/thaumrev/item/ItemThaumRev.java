package mortvana.thaumrev.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.melteddashboard.item.FluxGearItemInteractive;

public class ItemThaumRev extends FluxGearItemInteractive {

	public ItemThaumRev() {
		modName = ThaumRevLibrary.RESOURCE_PREFIX;
		setCreativeTab(ThaumRevLibrary.thaumicRevelationsTab);
		setFolder("material");
		setUnlocalizedName("material");
	}

	@Override
	public boolean rightClickActions(int meta, ItemStack stack, World world, EntityPlayer player, boolean used){
		return used;
	}

	@Override
	public void playSound(int metadata, World world, EntityPlayer player) {
		world.playSoundAtEntity(player, "stone", 1.0F, 1.0F);
	}

	@Override
	public Entity createEntity(World world, Entity entityItem, ItemStack stack) {
		if (stack.getItemDamage() == 275) {
			entityItem.isImmuneToFire = true;
		}
		return super.createEntity(world, entityItem, stack);
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if (entityItem.getEntityItem().getItemDamage() == 275) {
			int x = MathHelper.floor_double(entityItem.posX);
			int y = MathHelper.floor_double(entityItem.posY + 0.1F);
			int z = MathHelper.floor_double(entityItem.posZ);
			Block block = entityItem.worldObj.getBlock(x, y, z);

			if (block == Blocks.lava) {
				entityItem.worldObj.setBlock(x, y, z, ThaumRevLibrary.blockStoneDecor, 0, 3);
				entityItem.setDead();
			}
		}
		return false;
	}
}
