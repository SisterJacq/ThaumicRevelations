package mortvana.thaumrev.melteddashboard.item;

import java.util.ArrayList;
import java.util.List;

import mortvana.thaumrev.core.common.ThaumicRevelations;
import mortvana.thaumrev.melteddashboard.util.helpers.StringHelper;
import mortvana.thaumrev.melteddashboard.util.helpers.TextureHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;

import mortvana.thaumrev.melteddashboard.registry.RegistrationWrapper;

/** Slightly Modified and Augmented Version of ItemBase from CoFH Core */
public class FluxGearItem extends Item {
    public TMap<Integer, ItemEntry> itemMap = new THashMap<Integer, ItemEntry>();
    // This is actually more memory efficient than a LinkedHashMap, according to Lemming.
    public List<Integer> itemList = new ArrayList<Integer>();
    public TMap<Integer, String> tooltipMap = new THashMap<Integer, String>();

    public boolean hasTextures = true;
    public String modName = "fluxgear";
    public String folder = "";
    public boolean registryItem = false;

    public FluxGearItem() {
        setHasSubtypes(true);
        setMaxDamage(0);
        setMaxStackSize(64);
    }

    public FluxGearItem(String modName) {
        this();
        this.modName = modName;
    }

    public FluxGearItem(String modName, CreativeTabs tab) {
        this(modName);
        setCreativeTab(tab);
    }

    // addItem(...) {}
    public ItemStack addItem(int metadata, String name, int rarity, boolean register) {
        if (itemList.contains(metadata)) {
	        ThaumicRevelations.logger.warn("Someone registered a meta-item in an item from " + modName + ", with a metadata of " + metadata + ", called " + name + "! Don't do this! Skipping Entry!");
            return null;
        } else {
            itemMap.put(metadata, new ItemEntry(name, rarity).setTexture(name));
            itemList.add(metadata);

            ItemStack stack = new ItemStack(this, 1, metadata);
            if (register) {
                GameRegistry.registerCustomItemStack(name, stack);
            }
            return stack;
        }
    }

    public ItemStack addItem(int metadata, String name, int rarity) {
        return addItem(metadata, name, rarity, true);
    }

    public ItemStack addItem(int metadata, String name) {
        return addItem(metadata, name, 0, true);
    }

    // addColorizedItem(...) {}
    public ItemStack addColorizedItem(int metadata, String name, int rarity, boolean register, String template, String texture, int color) {
        ItemStack stack = addItem(metadata, name, rarity, register);
        if (stack != null) {
            itemMap.get(metadata).setColorData(template, texture, color);
        }
        return stack;
    }

    public ItemStack addColorizedItem(int metadata, String name, int rarity, String texture, String template, int color) {
        return addColorizedItem(metadata, name, rarity, true, texture, template, color);
    }

    public ItemStack addColorizedItem(int metadata, String name, String texture, String template, int color) {
        return addColorizedItem(metadata, name, 0, true, texture, template, color);
    }

    // addOreDictItem(...) {}
    public ItemStack addOreDictItem(int metadata, String name, int rarity, boolean register, String... oreDict) {
        ItemStack stack = addItem(metadata, name, rarity, register);
        RegistrationWrapper.registerOreDict(stack, (oreDict.length == 0 ? new String[] { name } : oreDict));
        return stack;
    }

    public ItemStack addOreDictItem(int metadata, String name, int rarity, String... oreDict) {
        return addOreDictItem(metadata, name, rarity, true, oreDict);
    }

    public ItemStack addOreDictItem(int metadata, String name, String... oreDict) {
        return addOreDictItem(metadata, name, 0, true, oreDict);
    }

    // addColorizedOreDictItem(...) {}
    public ItemStack addColorizedOreDictItem(int metadata, String name, int rarity, boolean register, String template, String texture, int color, String... oreDict) {
        ItemStack stack = addColorizedItem(metadata, name, rarity, register, texture, template, color);
        RegistrationWrapper.registerOreDict(stack, oreDict);
        return stack;
    }

    public ItemStack addColorizedOreDictItem(int metadata, String name, int rarity, String template, String texture, int color, String... oreDict) {
        return addColorizedOreDictItem(metadata, name, rarity, true, texture, template, color, oreDict);
    }

    public ItemStack addColorizedOreDictItem(int metadata, String name, String template, String texture, int color, String... oreDict) {
        return addColorizedOreDictItem(metadata, name, 0, true, template, texture, color, oreDict);
    }

    @Override
    //@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int meta : itemList) {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    public String getInternalName(ItemStack itemstack) {
        int meta = itemstack.getItemDamage();
        return itemMap.containsKey(meta) ? itemMap.get(meta).name : "invalid";
    }

    @Override
    public Item setUnlocalizedName(String name) {
        GameRegistry.registerItem(this, name);
        return super.setUnlocalizedName(modName + "." + name);
    }

    public Item setUnlocalizedName(String textureName, String registrationName) {
        GameRegistry.registerItem(this, registrationName);
        return super.setUnlocalizedName(modName + "." + textureName);
    }

    public FluxGearItem setRegistryItem(boolean isRegistryItem) {
        registryItem = isRegistryItem;
        return this;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getItemDamage();
        return "item." + modName + (folder == null ? "" : ".") + folder + (itemMap.containsKey(meta) ? "." + itemMap.get(meta).name : ".invalid");
    }

    @Override
    public String getItemStackDisplayName (ItemStack stack) {
        int meta = stack.getItemDamage();
        if (itemList.contains(meta) && registryItem) {
            String itemName = itemMap.get(meta).name;
            if (StatCollector.canTranslate("item." + modName + "." + itemMap.get(meta).name + ".name")) { //Custom Name
                return StatCollector.translateToLocal("item." + modName + "." + itemMap.get(meta).name + ".name");
            } else  if (itemName.contains(".")) { //General Name
                String[] split = itemName.replace('.', ':').split(":");
                String form = split[0];
                String material = split[1];
                /*if (StatCollector.canTranslate("pfgregistry.form." + form + ".name")) {
                    return StringHelper.localize("pfgregistry.form." + form + ".name").replace("%%material", StatCollector.canTranslate("pfgregistry.material." + material + ".name") ? StringHelper.localize("pfgregistry.material." + material + ".name") : StringHelper.titleCase(material));
                }*/
            }
        }
        return super.getItemStackDisplayName(stack);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        int meta = stack.getItemDamage();
        return itemMap.containsKey(meta) ? EnumRarity.values()[itemMap.get(meta).rarity] : EnumRarity.common;
    }

    /*@Override
    public boolean hasCustomEntity(ItemStack itemstack) {
        return SecurityHelper.isSecure(itemstack);
    }

    @Override
    public Entity createEntity(World world, Entity itemEntity, ItemStack stack) {
        if (SecurityHelper.isSecure(stack)) {
            itemEntity.invulnerable = true;
            itemEntity.isImmuneToFire = true;
            ((EntityItem) itemEntity).lifespan = Integer.MAX_VALUE;
        }
        return null;
    }*/

    public FluxGearItem setHasTextures(boolean hasTextures) {
        this.hasTextures = hasTextures;
        return this;
    }

    public FluxGearItem setFolder(String folder) {
        this.folder = folder;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata) {
        return itemList.contains(metadata) ? itemMap.get(metadata).icon : null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        if (hasTextures) {
            for (int i = 0; i < itemList.size(); i++) {
                ItemEntry entry = itemMap.get(itemList.get(i));
                if (itemMap.containsKey(i)) {
                    if (/*!MeltedDashboardConfig.minimalRegistry && */hasTexture(i)) {
                        entry.icon = iconRegister.registerIcon(getIconFromMeta(i));
                    } //else {
                        //entry.icon = iconRegister.registerIcon(modName + ":grayscale/" + itemMap.get(i).template);
                    //}
                } else {
                    entry.icon = iconRegister.registerIcon(modName + ":" + getUnlocalizedName().replace("item." + modName + ".", "") + "/" + StringHelper.camelCase(entry.name));
                }
            }
        }
    }

    @Override
    public IIcon getIcon(ItemStack stack, int renderPass) {
        int metadata = stack.getItemDamage();
        //if (renderPass == 1) {
        return itemMap.containsKey(metadata) ? itemMap.get(metadata).icon : null;
        //} else {
        //	return itemMap.containsKey(metadata) ? itemMap.get(metadata).icon : null;
        //}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return renderPass == 1 && itemMap.containsKey(stack.getItemDamage()) ? getColor(stack) : /*ColorLibrary.CLEAR*/0xFFFFFF;
    }

    public int getColor(ItemStack stack) {
        return itemMap.containsKey(stack.getItemDamage()) ? itemMap.get(stack.getItemDamage()).color : /*ColorLibrary.CLEAR*/0xFFFFFF;
    }

    public String getIconFromMeta(int metadata) {
        return modName + ":" + folder + (folder == null ? "" : ".") + itemMap.get(metadata).texture;
    }

    public boolean hasTexture(int metadata) {
        return TextureHelper.itemTextureExists(getIconFromMeta(metadata));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        int meta = stack.getItemDamage();
        if (itemList.contains(meta)) {
            if (tooltipMap.containsKey(meta)){
                //TODO: String separation
                list.add(tooltipMap.get(meta));
            }
        } /*else {
            list.add(ThaumRevLibrary.NULL_TOOLTIP);
        }*/
    }
}
