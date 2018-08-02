package mortvana.thaumrev.item;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.items.baubles.ItemAmuletVis;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileVisRelay;

import mortvana.melteddashboard.intermod.baubles.item.FluxGearItemBauble;
import mortvana.melteddashboard.item.FluxGearItem;
import mortvana.melteddashboard.util.helpers.StringHelper;
import mortvana.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import mortvana.melteddashboard.util.libraries.StringLibrary;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import mortvana.thaumrev.library.ThaumRevLibrary;

public class ItemThaumicBauble extends ItemAmuletVis {

	public ItemThaumicBauble() {
		setCreativeTab(ThaumRevLibrary.generalTab);
		GameRegistry.registerItem(this, "bauble");
		setUnlocalizedName(StringLibrary.RESOURCE_PREFIX + ".bauble");
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
		if (itemstack.getItemDamage() == 1) {
			world.playSoundAtEntity(player, StringLibrary.RESOURCE_PREFIX + ":abderp", 1, 1);
		}
		return super.onItemRightClick(itemstack, world, player);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + StringLibrary.RESOURCE_PREFIX + ".baubles." + names[stack.getItemDamage()];
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return stack.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.epic;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int metadata) {
		return metadata >= icons.length ? FluxGearItem.nullIcon : icons[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for (int i = 0; i < names.length; i++) {
			icons[i] = register.registerIcon(StringLibrary.DIR_DEFAULT + "baubles/" + names[i]);
		}
	}

	DecimalFormat formatter = new DecimalFormat("#######.##");

	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (stack.getItemDamage() == 2) {
			list.add(StringHelper.CYAN + StringHelper.localize("fluxgear.tooltip.visRegenAll"));
		}
		if (getMaxVis(stack) != 0) {
			list.add(StringHelper.YELLOW + StringHelper.localize("item.capacity.text") + " " + getMaxVis(stack) / 100);
		}
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			for (Aspect aspect : Aspect.getPrimalAspects()) {
				if (nbt.hasKey(aspect.getTag())) {
					list.add(" §" + aspect.getChatcolor() + aspect.getName() + "§r x " + formatter.format(nbt.getInteger(aspect.getTag()) / 100.0F));
				}
			}
		}
	}

	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		return stack.getItemDamage() == 1 ? BaubleType.RING : BaubleType.AMULET;
	}

	@Override
	public boolean canEquip(ItemStack stack, EntityLivingBase player) {
		return true;
		//return stack.getItemDamage() != 2 || ResearchManager.isResearchComplete(player.getCommandSenderName(), ThaumRevLibrary.keyPrimalPendant);
	}

	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase player) {
		if (stack.getItemDamage() == 2 && !player.worldObj.isRemote) {
			if (player.ticksExisted % 5 == 0){
				chargeItems(stack, player);
				chargeBauble(stack, player);
			}
			generateVis(stack, player);
		}
	}

	public void chargeItems(ItemStack stack, EntityLivingBase player) {
		if (player.getHeldItem() != null && isChargeable(player.getHeldItem(), player)) {
			chargeItem(player.getHeldItem(), player, stack);
		} else if (player instanceof EntityPlayer) {
			InventoryPlayer inv = ((EntityPlayer) player).inventory;
			boolean charged = false;
			for (ItemStack toCharge : inv.armorInventory) {
				if (toCharge != null && isChargeable(toCharge, player)) {
					chargeItem(toCharge, player, stack);
					charged = true;
					break;
				}
			}
			if (!charged) {
				for (ItemStack toCharge : inv.mainInventory) {
					if (toCharge != null && isChargeable(toCharge, player)) {
						chargeItem(toCharge, player, stack);
						break;
					}
				}
			}
			//TODO: Baubles? TiC Knapsack? TiC Belt? Just Ask!
		}
	}

	public void chargeBauble(ItemStack stack, EntityLivingBase player) {
		if (TileVisRelay.nearbyPlayers.containsKey(player.getEntityId())) {
			if (((WeakReference) TileVisRelay.nearbyPlayers.get(player.getEntityId())).get() != null && ((TileVisRelay) ((WeakReference) TileVisRelay.nearbyPlayers.get(player.getEntityId())).get()).getDistanceFrom(player.posX, player.posY, player.posZ) < 26.0D) {
				AspectList list = getAspectsWithRoom(stack);
				for (Aspect aspect : list.getAspects()) {
					if (aspect != null) {
						int amount = ((TileVisRelay) ((WeakReference) TileVisRelay.nearbyPlayers.get(player.getEntityId())).get()).consumeVis(aspect, Math.min(10, getMaxVis(stack) - getVis(stack, aspect)));
						if (amount > 0) {
							addRealVis(stack, aspect, amount, true);
							((TileVisRelay) ((WeakReference) TileVisRelay.nearbyPlayers.get(player.getEntityId())).get()).triggerConsumeEffect(aspect);
						}
					}
				}
			} else {
				TileVisRelay.nearbyPlayers.remove(player.getEntityId());
			}
		}
	}

	public void generateVis(ItemStack stack, EntityLivingBase player) {
		int r = itemRand.nextInt(20000);
		if (r < 80) {
			Aspect aspect = ThaumcraftHelper.getRandomPrimal();
			if (getVis(stack, aspect) < getMaxVis(stack)) {
				storeVis(stack, aspect, Math.min(100, getMaxVis(stack) - getVis(stack, aspect)));
			} else {
				EntityAspectOrb orb = new EntityAspectOrb(player.worldObj, player.posX, player.posY, player.posZ, aspect, 1);
				player.worldObj.spawnEntityInWorld(orb);
			}
		}
	}

	@Override
	public int getMaxVis(ItemStack stack) {
		return stack.getItemDamage() == 2 ? 50000 : 0;
	}

	public boolean isChargeable(ItemStack stack, EntityLivingBase player) {
		return stack.getItem() instanceof ItemWandCasting;
	}

	public void chargeItem(ItemStack stack, EntityLivingBase player, ItemStack pendant) {
		if (stack.getItem() instanceof ItemWandCasting) {
			ItemWandCasting wand = (ItemWandCasting) stack.getItem();
			AspectList list = wand.getAspectsWithRoom(stack);
			for (Aspect aspect : list.getAspects()) {
				if (aspect != null && getVis(pendant, aspect) > 0) {
					int amount = Math.min(10, wand.getMaxVis(stack) - wand.getVis(stack, aspect));
					amount = Math.min(amount, getVis(pendant, aspect));
					storeVis(pendant, aspect, getVis(pendant, aspect) - amount);
					wand.storeVis(stack, aspect, getVis(stack, aspect) + amount);
					if (getVis(pendant, aspect) == 0) {
						pendant.getTagCompound().removeTag(aspect.getTag());
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public static IIcon[] icons = new IIcon[3];
	public static String[] names = { "amuletWarden", "ringLove", "pendantPrimal" };
}