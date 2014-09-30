package mortvana.trevelations.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IGoggles;
import thaumcraft.api.nodes.IRevealer;

public class ItemWardenHelm extends ItemWardenArmor implements IRevealer, IGoggles{

    public ItemWardenHelm() {

        super(0);
        setUnlocalizedName("itemWardenHelm");

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {

        itemIcon = register.registerIcon("trevelations:wardenhelm");

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

        return "trevelations:textures/models/warden_1.png";

    }

    @Override
    public boolean showIngamePopups(ItemStack stack, EntityLivingBase entityLivingBase) {return true;}

    @Override
    public boolean showNodes(ItemStack stack, EntityLivingBase entityLivingBase) {return true;}

}
