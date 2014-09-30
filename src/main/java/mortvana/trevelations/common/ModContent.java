package mortvana.trevelations.common;

import cpw.mods.fml.common.registry.GameRegistry;
import mortvana.trevelations.block.*;
import mortvana.trevelations.block.tile.TileWitor;
import mortvana.trevelations.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ModContent {

    public static ItemArmor.ArmorMaterial materialWarden = EnumHelper.addArmorMaterial("WARDEN", 50, new int[]{3, 8, 6, 3}, 0);

    public static Item itemResource = new ItemResource();
    public static Item itemWardenAmulet = new ItemWardenAmulet();
    public static Item itemWardenSword = new ItemWardenWeapon();
    public static Item itemFocusPurity = new ItemFocusPurity();
    public static Item itemWardenHelm = new ItemWardenHelm();
    public static Item itemWardenChest = new ItemWardenChest();
    public static Item itemWardenLegs = new ItemWardenLegs();
    public static Item itemWardenBoots = new ItemWardenBoots();
    public static Item itemLoveRing = new ItemLoveRing();
    public static Item itemWaslieHammer = new ItemWaslieHammer();
    public static Item itemFocusIllumination = new ItemFocusIllumination();

    public static Block blockExubitura = new BlockExubitura();
    public static Block blockInfusedQuartzNormal = new BlockQuartzNormal();
    public static Block blockInfusedQuartzChiseled = new BlockQuartzChiseled();
    public static Block blockInfusedQuartzPillar = new BlockQuartzPillar();
    public static Block blockInfusedQuartzSlab = new BlockQuartzSlab();
    public static Block blockInfusedQuartzStair = new BlockQuartzStair();
    public static Block blockWitor = new BlockWitor();

    public static void blockInit() {

        GameRegistry.registerBlock(blockExubitura, "blockExubitura");
        GameRegistry.registerBlock(blockInfusedQuartzNormal, "blockInfusedQuartzNormal");
        GameRegistry.registerBlock(blockInfusedQuartzChiseled, "blockInfusedQuartzChiseled");
        GameRegistry.registerBlock(blockInfusedQuartzPillar, "blockInfusedQuartzPillar");
        GameRegistry.registerBlock(blockInfusedQuartzSlab, "blockInfusedQuartzSlab");
        GameRegistry.registerBlock(blockInfusedQuartzStair, "blockInfusedQuartzStair");
        GameRegistry.registerBlock(blockWitor, "blockWitor");

        GameRegistry.registerTileEntity(TileWitor.class, "tileWitor");

    }

    public static void itemInit() {

        GameRegistry.registerItem(itemResource, "itemResource");
        GameRegistry.registerItem(itemFocusPurity, "itemFocusPurity");
        GameRegistry.registerItem(itemWardenSword, "itemWardenWeapon");
        GameRegistry.registerItem(itemWardenAmulet, "itemWardenAmulet");
        GameRegistry.registerItem(itemWardenHelm, "itemWardenHelm");
        GameRegistry.registerItem(itemWardenChest, "itemWardenChest");
        GameRegistry.registerItem(itemWardenLegs, "itemWardenLegs");
        GameRegistry.registerItem(itemWardenBoots, "itemWardenBoots");
        GameRegistry.registerItem(itemLoveRing, "itemLoveRing");
        GameRegistry.registerItem(itemWaslieHammer, "itemWaslieHammer");
        GameRegistry.registerItem(itemFocusIllumination, "itemFocusIllumination");

    }
}
