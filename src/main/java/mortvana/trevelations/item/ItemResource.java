package mortvana.trevelations.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.trevelations.common.TRevelations;

public class ItemResource extends Item {

	public static final String[] RESOURCE_ICON = {"wardenpetal", "wardenstone", "wardenquartz"};
	private IIcon[] icons;

	public ItemResource() {

		super();
		setUnlocalizedName("itemResource");
		setCreativeTab(TRevelations.tabTRevelations);
		setHasSubtypes(true);

		icons = new IIcon["itemResource".length()];

	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {

		return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();

	}

	@Override
	public void getSubItems(Item par1Item, CreativeTabs par2Tab, List par3List) {

		for (int i = 0; i < RESOURCE_ICON.length; i++) {

			par3List.add(new ItemStack(par1Item, 1, i));

		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage) {

		return icons[damage];

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {

		for (int i = 0; i < RESOURCE_ICON.length; i++) {

			icons[i] = register.registerIcon("trevelations:" + RESOURCE_ICON[i]);

		}

	}

}
