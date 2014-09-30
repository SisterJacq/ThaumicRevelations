package mortvana.trevelations.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ItemWardenBoots extends ItemWardenArmor {

    public ItemWardenBoots() {

        super(3);
        setUnlocalizedName("itemWardenBoots");

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {

        itemIcon = register.registerIcon("trevelations:wardenboots");

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

        return "trevelations:textures/models/warden_1.png";

    }

}
