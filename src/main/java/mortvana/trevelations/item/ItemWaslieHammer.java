package mortvana.trevelations.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.trevelations.common.TRevelations;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWaslieHammer extends Item {

    public ItemWaslieHammer() {

        super();
        setUnlocalizedName("itemWaslieHammer");
        setCreativeTab(TRevelations.tabTRevelations);
        setMaxStackSize(1);
        canRepair = false;

    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {

        return EnumRarity.rare;

    }

    @Override
    public boolean isItemTool(ItemStack stack) {

        return true;

    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

        par3EntityPlayer.openGui(TRevelations.instance, 0, par2World, 0, 0, 0);

        return par1ItemStack;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {

        itemIcon = register.registerIcon("trevelations:wasliehammer");

    }

}
