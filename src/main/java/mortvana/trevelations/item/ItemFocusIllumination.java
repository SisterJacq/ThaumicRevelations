package mortvana.trevelations.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import mortvana.trevelations.common.ModContent;
import mortvana.trevelations.common.TRevelations;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemFocusIllumination extends ItemFocusBasic {

	private IIcon depth, orn;

	public ItemFocusIllumination() {

		super();
		setUnlocalizedName("itemFocusIllumination");
		setCreativeTab(TRevelations.tabTRevelations);

	}

	@Override
	public void registerIcons(IIconRegister register) {

		icon = register.registerIcon("trevelations:purityfocus");
		depth = register.registerIcon("trevelations:puritydepth");
		orn = register.registerIcon("trevelations:purityorn");

	}

	@Override
	public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {return depth;}

	@Override
	public IIcon getOrnament(ItemStack itemstack) {return orn;}

	@Override
	public int getFocusColor(ItemStack itemstack) {return 0x6698FF;}


	public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
		ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
		if (mop != null) {
			if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				if (!world.isRemote) {
					if (wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, false)) {

						int x = mop.blockX;
						int y = mop.blockY;
						int z = mop.blockZ;

						if (mop.sideHit == 0) {
							y--;
						}
						if (mop.sideHit == 1) {
							y++;
						}
						if (mop.sideHit == 2) {
							z--;
						}
						if (mop.sideHit == 3) {
							z++;
						}
						if (mop.sideHit == 4) {
							x--;
						}
						if (mop.sideHit == 5) {
							x++;
						}
						world.setBlock(x, y, z, ModContent.blockWitor, 0, 2);
					}
				}
			}
		}
		player.swingItem();
		return itemstack;
	}

	@Override
	public String getSortingHelper(ItemStack itemStack) {return "ILLUMINATION";}

	@Override
	public AspectList getVisCost(ItemStack itemstack) {

		return new AspectList().add(Aspect.AIR, 50).add(Aspect.FIRE, 50);

	}

	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int i) {
		return new FocusUpgradeType[0];
	}


}
