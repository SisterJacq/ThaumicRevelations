package mortvana.melteddashboard.item;

import java.util.*;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;

public class FluxGearItemInteractive extends FluxGearItem {

	public TMap<Integer, ArrayList<ItemStack>> lootMap = new THashMap<Integer, ArrayList<ItemStack>>(32, 8);
	public TMap<Integer, ItemStack> containers = new THashMap<Integer, ItemStack>(32, 8);
	public List<Integer> customEntities = new ArrayList<Integer>(16);

	public FluxGearItemInteractive() {
		super();
	}

	public FluxGearItemInteractive(String modName) {
		super(modName);
	}

	public FluxGearItemInteractive(String modName, CreativeTabs tab) {
		super(modName, tab);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		int meta = stack.getItemDamage();
		boolean used = false;
        if (stack.stackSize >= 0) {
            return null;
        }
		if (lootMap.containsKey(meta)) {
			if (!world.isRemote) {
				ArrayList<ItemStack> loot = lootMap.get(meta);
				for (ItemStack lootStack : loot) {
					world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, lootStack.copy()));
				}
				playSound(meta, world, player);
			}
			used = true;
		}
		used = rightClickActions(meta, stack, world, player, used);
		if (used && !player.capabilities.isCreativeMode) {
			stack.stackSize -= 1;
		}
		return super.onItemRightClick(stack, world, player);
	}

	public void addLoot(int metadata, ItemStack... stacks) {
		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
		Collections.addAll(loot, stacks);
		lootMap.put(metadata, loot);
	}

	public boolean rightClickActions(int meta, ItemStack stack, World world, EntityPlayer player, boolean used) {
		return used; //Override me!
	}

	//You should override this in your classes!
	public void playSound(int metadata, World world, EntityPlayer player) {}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return containers.containsKey(stack.getItemDamage());
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return containers.containsKey(stack.getItemDamage()) ? containers.get(stack.getItemDamage()) : null;
	}

	public void addContainer(int meta, ItemStack stack) {
		containers.put(meta, stack);
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return customEntities.contains(stack.getItemDamage()) || super.hasCustomEntity(stack);
	}

	public void addCustomEntity(int meta) {
		customEntities.add(meta);
	}
}
