package mortvana.trevelations.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.trevelations.common.TRevelations;
import mortvana.trevelations.util.DamageSourceWarden;
import mortvana.trevelations.util.wardenic.WardenicChargeHelper;

public class ItemWardenWeapon extends Item {

	public ItemWardenWeapon() {

		super();
		setUnlocalizedName("itemWardenWeapon");
		setCreativeTab(TRevelations.tabTRevelations);
		setMaxStackSize(1);

		setFull3D();

	}

	@Override
	public boolean getShareTag() {return true;}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {return false;}

	@Override
	public int getMaxDamage(ItemStack stack) {return 50;}

	@Override
	public boolean isDamageable() {return false;}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {return EnumRarity.epic;}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {return EnumAction.block;}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {return 72000;}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

		par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tooltip.wardenic.charge") + ": " + (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()) + "/" + par1ItemStack.getMaxDamage());
		par3List.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(par1ItemStack).getQuote());

		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);

	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {

		if (stack.getItemDamage() != stack.getMaxDamage()) {

			DamageSource damageSource = new DamageSourceWarden("warden", player);

			entity.attackEntityFrom(damageSource, 5);

			WardenicChargeHelper.getUpgrade(stack).onAttack(stack, player, entity);

			stack.setItemDamage(stack.getItemDamage() + 1);

		}

		return super.onLeftClickEntity(stack, player, entity);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {

		itemIcon = register.registerIcon("trevelations:wardensword");

	}

}
