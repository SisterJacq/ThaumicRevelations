package mortvana.thaumicrevalations.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.thaumicrevalations.TRevalations;
import mortvana.thaumicrevalations.lib.ItemLib;
import mortvana.thaumicrevalations.lib.ModLib;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemWardenAmulet extends Item implements IBauble {

    public ItemWardenAmulet() {

        super();
        setUnlocalizedName(ItemLib.WARDEN_AMULET_NAME);
        setCreativeTab(TRevalations.tabTWarden);

    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {return EnumRarity.rare;}

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {

        itemIcon = register.registerIcon(ModLib.ID.toLowerCase() + ":" + "wardenamulet");

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
