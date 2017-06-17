package mortvana.thaumicrevelations.library;

import mortvana.thaumicrevelations.melteddashboard.MaterialHardAir;
import mortvana.thaumicrevelations.melteddashboard.intermod.baubles.item.FluxGearItemBauble;
import mortvana.thaumicrevelations.melteddashboard.item.FluxGearItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

import static thaumcraft.api.aspects.Aspect.*;

public class ThaumicLibrary {

    /** MOD CONSTANTS **/
    public static final String RESOURCE_PREFIX = "thaumrev";
    public static final String TEX_LOC_DEFAULT = "thaumrev:";

    /** RESEARCH CATEGORIES **/
    public static final String CATERGORY_WARDEN = "WARDEN";
    public static final String CATERGORY_MAGNEOTURGY = "MAGNEOTURGY";
    public static final String CATEGORY_REVELATIONS = "REVELATIONS";

    /** ASPECTS **/
    public static final Aspect WARDEN = new Aspect("excubitor", 0x3CD4FC, new Aspect[] { ELDRITCH, DEATH }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/exubitor.png"), 771);
    public static final Aspect CITRUS = new Aspect("citrus", 0xFF6E00, new Aspect[] { TREE, SENSES }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/citrus.png"), 771);
    public static final Aspect MAGNET = new Aspect("magnes", 0x515970, new Aspect[] { METAL, ENERGY }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/magnes.png"), 771);
    public static final Aspect FLUX = new Aspect("fluxus", 0xAD0200, new Aspect[] { MAGNET, MECHANISM }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/fluxus.png"), 771);
    public static final Aspect REVELATIONS = new Aspect("patefactio", 0x3971AD, new Aspect[] { TRAVEL, MIND }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/revelatiofez.png"), 771);


    /** ENCHANTMENTS **/
    public static Enchantment enchantStabilizing;

    /** CREATIVE TABS **/
    public static CreativeTabs thaumicRevelationsTab; //= new FluxGearCreativeTab("PFG-Thaumic", "fluxgear.thaumicTab", wardenAmulet);

    /** MATERIALS **/
    public static Material materialHardAir = new MaterialHardAir();

    /** BLOCKS **/
    public static Block blockThaumicPlant;
    public static Block blockFakeAir;


    /** ITEMS **/
    public static FluxGearItem generalItem;
    public static FluxGearItemBauble thaumicBauble;


    /** ITEMSTACKS **/
    public static ItemStack dummyGeneral;                   //00000

    public static ItemStack exubituraPetal;                 //01000
    public static ItemStack wardenicCrystal;                //01001
    public static ItemStack wardenicQuartz;                 //01002

    public static ItemStack wardenAmulet;                   //00000
    public static ItemStack loveRing;                       //00001

    /* RENDER IDs */
    public static int wardedChestRenderID = -1;
}
