package mortvana.trevelations.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mortvana.trevelations.common.TRevelations;

public class ItemWardenAmulet extends Item implements IBauble {

	public ItemWardenAmulet() {

		super();
		setUnlocalizedName("itemWardenAmulet");
		setCreativeTab(TRevelations.tabTRevelations);

	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {return EnumRarity.rare;}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {

		itemIcon = register.registerIcon("trevelations:wardenamulet");

	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {return BaubleType.AMULET;}

	@Override
	public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {

	}

	@Override
	public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

	@Override
	public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

	@Override
	public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {return true;}

	@Override
	public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {return true;}

}
