package mortvana.thaumrev.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;

import mortvana.melteddashboard.util.libraries.StringLibrary;
import mortvana.melteddashboard.util.helpers.StringHelper;

import mortvana.thaumrev.library.ThaumRevLibrary;

public abstract class ItemFocusBase extends ItemFocusBasic {

	@SideOnly(Side.CLIENT)
	public IIcon[] icons = new IIcon[3]; //0=icon, 1=depth, 2=orn
	public String name;
	public int color = 0xFFFFFF;
	public boolean ornament;

	public ItemFocusBase(String name, int color, boolean ornament) {
		super();
		this.name = name;
		this.color = color;
		this.ornament = ornament;
		setUnlocalizedName("fluxgear.focus" + StringHelper.titleCase(name));
		setCreativeTab(ThaumRevLibrary.generalTab);
	}

	@Override
	public abstract ItemStack onFocusRightClick(ItemStack stack, World world, EntityPlayer player, MovingObjectPosition mop);

	@Override
	public String getSortingHelper(ItemStack stack) {
		return StringHelper.upperCase(name);
	}

	@Override
	public abstract AspectList getVisCost(ItemStack stack);

	@Override
	public abstract FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack stack, int i);

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		icons[0] = register.registerIcon(StringLibrary.DIR_DEFAULT + "focus/focus" + name);
		icons[1] = register.registerIcon(StringLibrary.DIR_DEFAULT + "focus/focus" + name  + "_depth");

		if (ornament) {
			icons[2] = register.registerIcon(StringLibrary.DIR_DEFAULT + "focus/focus" + name + "_orn");
		} else {
			icons[2] = null;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getFocusDepthLayerIcon(ItemStack stack) {
		return icons[1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getOrnament(ItemStack stack) {
		return icons[2];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFocusColor(ItemStack stack) {
		return color;
	}
}
