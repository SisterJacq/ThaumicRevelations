package mortvana.thaumicrevalations.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.thaumicrevalations.lib.ItemLib;
import mortvana.thaumicrevalations.lib.ModLib;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ItemWardenLegs extends ItemWardenArmor {

    public ItemWardenLegs() {

        super(2);
        setUnlocalizedName(ItemLib.WARDEN_LEGS_NAME);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {

        itemIcon = register.registerIcon(ModLib.ID.toLowerCase() + ":" + "wardenlegs");

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

        return "thaumicrevalations:textures/models/warden_2.png";

    }

}
