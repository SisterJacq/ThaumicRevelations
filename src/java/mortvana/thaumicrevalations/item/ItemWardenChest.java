package mortvana.thaumicrevalations.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.thaumicrevalations.TRevalations;
import mortvana.thaumicrevalations.lib.ItemLib;
import mortvana.thaumicrevalations.lib.ModLib;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ItemWardenChest extends ItemWardenArmor {

    public ItemWardenChest() {

        super(1);
        setUnlocalizedName(ItemLib.WARDEN_CHEST_NAME);
        setCreativeTab(TRevalations.tabTWarden);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {

        itemIcon = register.registerIcon(ModLib.ID.toLowerCase() + ":" + "wardenchest");

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

        return "thaumicrevalations:textures/models/warden_1.png";

    }

}
