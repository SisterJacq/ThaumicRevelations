package mortvana.thaumrev.util.item;

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

import mortvana.melteddashboard.util.helpers.StringHelper;

import mortvana.thaumrev.library.ThaumRevLibrary;

public abstract class ItemFocusBase extends ItemFocusBasic {

	@SideOnly(Side.CLIENT)
	public IIcon icons;
	@SideOnly(Side.CLIENT)
	public IIcon depth;
	@SideOnly(Side.CLIENT)
	public IIcon orn;
	public String name;
	public int color = 0x6698FF;
	public boolean ornament;

	public ItemFocusBase(String name, int color, boolean ornament) {
		super();
		this.name = name;
		this.color = color;
		this.ornament = ornament;
		setUnlocalizedName("thaumrev.focus" + name);
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
		icons = register.registerIcon(ThaumRevLibrary.TEX_LOC_DEFAULT + "focus" + name);
		depth = register.registerIcon(ThaumRevLibrary.TEX_LOC_DEFAULT + "depth" + name);

		if (ornament) {
			icons = register.registerIcon(ThaumRevLibrary.TEX_LOC_DEFAULT + "orn" + name);
		} else {
			orn = null;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getFocusDepthLayerIcon(ItemStack stack) {
		return depth;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getOrnament(ItemStack stack) {
		return orn;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFocusColor(ItemStack stack) {
		return color;
	}
}
