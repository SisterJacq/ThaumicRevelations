package mortvana.thaumicrevalations.block;

import mortvana.thaumicrevalations.TRevalations;
import mortvana.thaumicrevalations.item.ModItems;
import mortvana.thaumicrevalations.lib.BlockLib;
import mortvana.thaumicrevalations.lib.ModLib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

public class BlockExubitura extends BlockBush{

    protected BlockExubitura() {

        super(Material.plants);
        setBlockName(BlockLib.EXUBITURA_NAME);
        setCreativeTab(TRevalations.tabTWarden);
        setStepSound(Block.soundTypeGrass);

    }

    @Override
    public Item getItemDropped(int par1, Random random, int par2) {

        return ModItems.itemResource;

    }

    @Override
    public int damageDropped(int par1) {

        return 0;

    }

    @Override
    public boolean canBlockStay(World p_149718_1_, int x, int y, int z) {return true;}

    @Override
    public void registerBlockIcons(IIconRegister register) {

        blockIcon = register.registerIcon(ModLib.ID.toLowerCase() + ":" + "exubitura");

    }

}
