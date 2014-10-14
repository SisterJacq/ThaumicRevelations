package mortvana.trevelations.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWardenLegs extends ItemWardenArmor {

	public ItemWardenLegs() {

		super(2);
		setUnlocalizedName("itemWardenLegs");

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {

		itemIcon = register.registerIcon("trevelations:wardenlegs");

	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

		return "trevelations:textures/models/warden_2.png";

	}

}
