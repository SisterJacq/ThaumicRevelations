package mortvana.trevelations.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.trevelations.common.TRevelations;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLoveRing extends Item implements IBauble {

    public ItemLoveRing() {

        super();
        setUnlocalizedName("itemLoveRing");
        setCreativeTab(TRevelations.tabTRevelations);

    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {return EnumRarity.epic;}

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {

        itemIcon = register.registerIcon("trevelations:lovering");

    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        world.playSoundAtEntity(player, "trevelations:abderp", 1, 1);

        return super.onItemRightClick(stack, world, player);

    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {return BaubleType.RING;}

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {return true;}

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {return false;}

}
