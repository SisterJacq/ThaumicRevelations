package mortvana.thaumicrevelations.library;

import mortvana.thaumicrevelations.melteddashboard.MaterialHardAir;
import mortvana.thaumicrevelations.melteddashboard.intermod.baubles.item.FluxGearItemBauble;
import mortvana.thaumicrevelations.melteddashboard.item.FluxGearItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

import static thaumcraft.api.aspects.Aspect.*;

public class ThaumicLibrary {

    /** MOD CONSTANTS **/
    public static final String MOD_ID = "ThaumicRevelations";
	public static final String MOD_NAME = "Thaumic Revelations";
	public static final String MOD_VERSION = "vPO.TA.TO.RANDOM-DEV";
	public static final String MOD_DEPENDENCIES = "required-after:Thaumcraft; after:MagicBees[2.3.0)";
	public static final String API_NAME = "thaumicrevelationsapi";
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

	/** ARMOR MATERIALS **/
	public static ArmorMaterial materialWardencloth;


	/** ARMOR ARRAYS **/
	public int[] discountWardencloth = new int[] {5, 2, 2, 1};


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

    public static ItemStack excubituraPetal;                //01100
	public static ItemStack excubituraPaste;                //01101
	public static ItemStack excubituraOil;                  //01102
	public static ItemStack excubituraOilPure;              //01103
	public static ItemStack excubituraCrystal;              //01104
	public static ItemStack excubituraCrystalAwakened;      //01105

	public static ItemStack excubituraCloth;

    public static ItemStack wardenicQuartz;                 //01114

	public static ItemStack wardencloth;                    //01121



    public static ItemStack wardenAmulet;                   //00000
    public static ItemStack loveRing;                       //00001

    /** RENDER IDs **/
    public static int wardedChestRenderID = -1;

	/** NBT KEYS **/
	public static final String DURABILITY = "DURABILITY";
	public static final String REVEALING = "REVEALING";
	public static final String BROKEN = "BROKEN";
}
