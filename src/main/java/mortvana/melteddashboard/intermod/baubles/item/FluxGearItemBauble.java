package mortvana.melteddashboard.intermod.baubles.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mortvana.melteddashboard.intermod.baubles.util.BaubleData;
import mortvana.melteddashboard.item.FluxGearItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

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
