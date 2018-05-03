package mortvana.melteddashboard.item;

import java.util.ArrayList;
import java.util.List;

import mortvana.melteddashboard.ColorLibrary;

import mortvana.thaumrev.common.ThaumRevConfig;
import mortvana.thaumrev.common.ThaumicRevelations;

import mortvana.melteddashboard.client.texture.GradientTexture;
import mortvana.melteddashboard.util.GrayscaleEntry;
import mortvana.melteddashboard.util.helpers.StringHelper;
import mortvana.melteddashboard.util.helpers.TextureHelper;
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

import net.minecraftforge.client.event.TextureStitchEvent;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;

import mortvana.melteddashboard.registry.RegistrationWrapper;
import mortvana.melteddashboard.util.EnumGrayscaleItems;

/**
 *	Modified and Augmented version of Item, based on ItemBase from CoFH Core by King Lemming
 *
 *	@author Mortvana
 */
public class FluxGearItem extends Item {

	/**
	 *	A list of all instances of FluxGearItem, used for the gradient system.
	 */
	public static List<FluxGearItem> items = new ArrayList<FluxGearItem>();

	/**
	 * 	A list of all
	 */

	/**
	 * 	A HashMap of all sub-items in the form of instances of ItemEntry based on metadata.
	 */
    public TMap<Integer, ItemEntry> itemMap = new THashMap<Integer, ItemEntry>();

   /**
	* 	A list of all metadata used, which using along with a HashMap is actually more memory efficient than using a
	* 	LinkedHashMap, according to Lemming's comment in the code for ItemBase.
	*/
    public List<Integer> itemList = new ArrayList<Integer>();

    //public TMap<Integer, String> tooltipMap = new THashMap<Integer, String>(); //TODO: Tooltips

	/**
	 *	Whether the item should register textures when registerIcons(...) is called. Basically leave this as true unless
	 *	you've got a reason. You don't need to know exactly what you've doing, but have a reason.
	 */
    public boolean hasTextures = true;

	/**
	 *	The resource directory for textures and unlocalized names. Defaults to "fluxgear", just as ItemBase defaults to
	 *	"cofh".	I recommend using the constructor that sets the name, the FluxGearItem(String name) one, since you're
	 *	probably not me. If you are, then either I cloned myself and made a time machine, or Krieger cloned me from a
	 *	cup I drank soda from, either way.
	 */
    public String modName = "fluxgear";

	/**
	 *	The directory within the resource directory that textures are gotten from, unless they are grayscaled versions.
	 *	Defaults to an empty string for modname:whatever, but should probably be set for something like
	 *	modname:folder/whatever, so for example setting to material for say fluxgear:material/gemDioptase.
	 */
    public String folder = "";

	/**
	 *	Whether you are using some kind of automatic item registry, like the one provided with Melted Dashboard Core.
	 *	Basically leave this false if you have no clue what that means, or if you're not doing that kind of thing.
	 */
    public boolean registryItem = false;

	/**
	 *	Whether Melted Dashboard Core should register (some of) this items textures using gradients.
	 *	Drullkus put me on to the whole gradient thing, but it's both nifty and useful, so...
	 */
    public boolean gradient = false; //TODO: Finish Gradients

    public FluxGearItem() {
        setHasSubtypes(true);
        setMaxDamage(0);
        setMaxStackSize(64);
		items.add(this);
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
	public ItemStack addItem(int metadata, ItemEntry entry, boolean register) {
		if (itemList.contains(metadata)) {
			ThaumicRevelations.logger.warn("Someone registered a meta-item in an item from " + modName + ", with a metadata of " + metadata + ", called " + entry.name + "! Don't do this! Skipping Entry!");
			return null;
		} else {
			itemMap.put(metadata, entry);
			itemList.add(metadata);

			ItemStack stack = new ItemStack(this, 1, metadata);
			if (register) {
				GameRegistry.registerCustomItemStack(entry.name, stack);
			}
			return stack;
		}
	}

	public ItemStack addItem(int metadata, ItemEntry entry) {
		return addItem(metadata, entry, true);
	}

    public ItemStack addItem(int metadata, String name, int rarity, boolean register) {
       return addItem(metadata, new ItemEntry(name, rarity).setTexture(name), register);
    }

    public ItemStack addItem(int metadata, String name, int rarity) {
        return addItem(metadata, name, rarity, true);
    }

    public ItemStack addItem(int metadata, String name) {
        return addItem(metadata, name, 0, true);
    }

    // addColorizedItem(...) {}
    /*public ItemStack addColorizedItem(int metadata, String name, int rarity, boolean register, String template, String texture, int color) {
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
    }*/

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

	// addOreDictItemWithEffect(...) {}
	public ItemStack addOreDictItemWithEffect(int metadata, String name, String texture, int rarity, boolean register, String... oreDict) {
		ItemStack itemstack = addOreDictItem(metadata, name, rarity, register, oreDict);
		itemMap.get(metadata).setTexture(texture).setEnchanted(true);
		return itemstack;
	}
	public ItemStack addOreDictItemWithEffect(int metadata, String name, String texture, int rarity, String... oreDict) {
		return addOreDictItemWithEffect(metadata, name, texture, rarity, true, oreDict);
	}
	public ItemStack addOreDictItemWithEffect(int metadata, String name, String texture, String... oreDict) {
		return addOreDictItemWithEffect(metadata, name, texture, 0, true, oreDict);
	}
	public ItemStack addOreDictItemWithEffect(int metadata, String name, String texture) {
		return addOreDictItemWithEffect(metadata, name, texture, 0, true, name);
	}
	public ItemStack addOreDictItemWithEffect(int metadata, String name) {
		return addOreDictItemWithEffect(metadata, name, name, 0, true, name);
	}

    // addColorizedOreDictItem(...) {}
    /*public ItemStack addColorizedOreDictItem(int metadata, String name, int rarity, boolean register, String template, String texture, int color, String... oreDict) {
        ItemStack stack = addColorizedItem(metadata, name, rarity, register, texture, template, color);
	    if (oreDict.length == 0) {
		    RegistrationWrapper.registerOreDict(stack, name);
	    } else {
		    RegistrationWrapper.registerOreDict(stack, oreDict);
	    }
        return stack;
    }

    public ItemStack addColorizedOreDictItem(int metadata, String name, int rarity, String template, String texture, int color, String... oreDict) {
        return addColorizedOreDictItem(metadata, name, rarity, true, texture, template, color, oreDict);
    }

    public ItemStack addColorizedOreDictItem(int metadata, String name, String template, String texture, int color, String... oreDict) {
        return addColorizedOreDictItem(metadata, name, 0, true, template, texture, color, oreDict);
    }

    public ItemStack addColorizedOreDictItem(int metadata, String name, String template, int color, String... oreDict) {
        return addColorizedOreDictItem(metadata, name, 0, true, template, name, color, oreDict);
    }*/

    /*public ItemStack addColorizedOreDictItem(int metadata, String name, EnumGrayscaleItems template, int color, String... oreDict) {
        return addColorizedOreDictItem(metadata, name, 0, true, template, name, color, oreDict);
    }*/


	/**
	 * 	Just adds all non-disabled items to the creative tab that the item is set to go in.
	 *
	 * 	@param item - The Item supplied, should be an instance of FluxGearItem, but it doesn't matter.
	 * 	@param tab - The creative tab that the items are in. You should have set this during construction or with
	 * 	              setCreativeTab(...)
	 * 	@param list - The list of items that we add to.
	 */
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int meta : itemList) {
			if (itemMap.containsKey(meta) || !itemMap.get(meta).disabled) {
				list.add(new ItemStack(item, 1, meta));
			}
        }
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

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getItemDamage();
		return "item." + modName + (folder == null ? "" : "." + folder) + (itemMap.containsKey(meta) ? "." + itemMap.get(meta).name + (itemMap.get(meta).altName ? "Alt" : "") : ".invalid");
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




    public String getInternalName(ItemStack itemstack) {
        int meta = itemstack.getItemDamage();
        return itemMap.containsKey(meta) ? itemMap.get(meta).name : "invalid";
    }





    public FluxGearItem setRegistryItem(boolean isRegistryItem) {
        registryItem = isRegistryItem;
        return this;
    }



    /*@Override
    public boolean hasCustomEntity(ItemStack itemstack) {
        return SecurityHelper.isSecure(itemstack);
    }

    @Override
    public Entity createEntity(World world, Entity entity, ItemStack itemstack) {
        if (SecurityHelper.isSecure(itemstack)) {
            entity.invulnerable = true;
            entity.isImmuneToFire = true;
            ((EntityItem) entity).lifespan = Integer.MAX_VALUE;
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
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata) {
        return itemMap.containsKey(metadata) ? itemMap.get(metadata).icon : null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
		ItemEntry entry;
        /*if (grayscaled) {
            for (EnumGrayscaleItems item : EnumGrayscaleItems.values()) {
                item.setIcon(iconRegister.registerIcon("fluxgear:grayscale/" + item.getName()));
            }
        }*/
        if (hasTextures) {
			for (int meta : itemList) {
				if (itemMap.containsKey(meta) && !itemMap.get(meta).isDisabled()) {
					entry = itemMap.get(meta);
					//if (entry.gradients.length == 0 /*|| (!MeltedDashboardConfig.minimalRegistry && hasTexture(meta))*/) {
						entry.icon = iconRegister.registerIcon(getIconFromMeta(meta));
					//} else if (entry.gradients.length == 1) {
					//	entry.icon = GrayscaleEntry.getIcon(entry.template);
					//}
				} /*else {
					entry.icon = iconRegister.registerIcon(modName + ":" + getUnlocalizedName().replace("item." + modName + ".", "") + "/" + StringHelper.camelCase(entry.name));
                }*/
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
        return renderPass == 1 && (itemMap.containsKey(stack.getItemDamage()) && !itemMap.get(stack.getItemDamage()).disabled && itemMap.get(stack.getItemDamage()).gradients.length == 1) ? itemMap.get(stack.getItemDamage()).gradients[0].color : ColorLibrary.CLEAR;
    }

    public String getIconFromMeta(int metadata) {
    	return modName + ":" + folder + (folder == null ? "" : "/") + itemMap.get(metadata).texture;
    }

    public boolean hasTexture(int metadata) {
        return TextureHelper.itemTextureExists(getIconFromMeta(metadata));
    }

	public void setEnchanted(int metadata, boolean bool) {
		if (itemMap.containsKey(metadata)) {
			itemMap.get(metadata).enchanted = bool;
		}
	}

	public void setEnchanted(int metadata) {
		setEnchanted(metadata, true);
	}

	public void setEnchanted(boolean bool, int... metadata) {
		for (int i : metadata) {
			setEnchanted(i, bool);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemstack, int pass) {
		if (itemstack != null) {
			int meta = itemstack.getItemDamage();
			if (itemMap.containsKey(meta)) {
				return itemMap.get(meta).enchanted;
			}
		}
		return false;
	}

/*    @Override
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
    //}

	@SideOnly(Side.CLIENT)
	public static void handleGradients(TextureStitchEvent.Pre event) {
		ItemEntry entry;

		for (FluxGearItem item : items) {
			if (item.gradient) {
				for (int meta : item.itemList) {
					if (item.itemMap.containsKey(meta)) {
						entry = item.itemMap.get(meta);
						if ((!entry.textureFound /*|| MeltedDashboradConfig.alwaysUseGradients*/) && entry.gradients.length >= 2) {
							event.map.setTextureEntry(GrayscaleEntry.getTexture(entry.template), new GradientTexture(item.getIconFromMeta(meta), entry.template, true, entry.gradients));
						}
					}
				}
			}
		}
	}
}
