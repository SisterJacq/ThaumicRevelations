package mortvana.melteddashboard.intermod.baubles.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

import mortvana.melteddashboard.intermod.baubles.util.BaubleData;
import mortvana.melteddashboard.item.FluxGearItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class FluxGearItemBauble extends FluxGearItem implements IBauble {

	public Map<Integer, BaubleData> baubleData = new HashMap<Integer, BaubleData>();

	public FluxGearItemBauble() {
		super();
	}

	public FluxGearItemBauble(String modName) {
		super(modName);
	}

	public FluxGearItemBauble(String modName, CreativeTabs tab) {
		super(modName, tab);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (canEquip(itemstack, player)) {
			InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
			for (int i = 0; i < baubles.getSizeInventory(); i++) {
				if (baubles.isItemValidForSlot(i, itemstack)) {
					ItemStack slotStack = baubles.getStackInSlot(i);
					if (slotStack == null || ((IBauble) slotStack.getItem()).canUnequip(slotStack, player)) {
						if (!world.isRemote) {
							baubles.setInventorySlotContents(i, itemstack.copy());
							if (!player.capabilities.isCreativeMode) {
								player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
							}
						}
						if (slotStack != null) {
							((IBauble) slotStack.getItem()).onUnequipped(slotStack, player);
							return slotStack.copy();
						}
						break;
					}
				}
			}
		}
		return itemstack;
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return metaBaubleExists(itemstack) ? baubleData.get(itemstack.getItemDamage()).getType() : null;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		if (metaBaubleExists(itemstack)){
				baubleData.get(itemstack.getItemDamage()).onWornTick(itemstack, player);
		}
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		if (metaBaubleExists(itemstack)){
			baubleData.get(itemstack.getItemDamage()).onEquipped(itemstack, player);
		}
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		if (metaBaubleExists(itemstack)){
			baubleData.get(itemstack.getItemDamage()).onUnequipped(itemstack, player);
		}
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		return !metaBaubleExists(itemstack) || baubleData.get(itemstack.getItemDamage()).canEquip();
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		return !metaBaubleExists(itemstack) || baubleData.get(itemstack.getItemDamage()).canUnequip();
	}

	public boolean metaBaubleExists(ItemStack bauble) {
		return metaBaubleExists(bauble.getItemDamage());
	}

	public boolean metaBaubleExists(int metadata) {
		return baubleData.containsKey(metadata);
	}

	public ItemStack addMetaBauble(int metadata, String name, BaubleData data) {
		baubleData.put(metadata, data);
		return addItem(metadata, name);
	}

	public ItemStack addMetaBauble(int metadata, String name, BaubleData data, int rarity) {
		baubleData.put(metadata, data);
		return addItem(metadata, name, rarity);
	}

}
