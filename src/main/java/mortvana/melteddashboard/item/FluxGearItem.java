package mortvana.melteddashboard.item;

import java.util.ArrayList;
import java.util.List;

import mortvana.melteddashboard.item.entry.*;
import mortvana.melteddashboard.util.libraries.ColorLibrary;

import mortvana.thaumrev.common.ThaumicRevelations;

import mortvana.melteddashboard.client.texture.GrayscaleEntry;
import mortvana.melteddashboard.util.helpers.TextureHelper;

import net.minecraft.client.renderer.texture.*;
import net.minecraft.creativetab.CreativeTabs;
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

import mortvana.melteddashboard.registry.RegistrationWrapper;

/**
 *	Modified and Augmented version of Item, based on ItemBase from CoFH Core by King Lemming
 *
 *	@author Mortvana
 */
public class FluxGearItem extends Item {

	/**
	 * 	A HashMap of all sub-items in the form of instances of ItemEntry based on metadata.
	 */
    protected TMap<Integer, ItemEntry> itemMap = new THashMap<Integer, ItemEntry>();

   /**
	* 	A list of all metadata used, which using along with a HashMap is actually more memory efficient than using a
	* 	LinkedHashMap, according to Lemming's comment in the code for ItemBase.
	*/
    protected List<Integer> itemList = new ArrayList<Integer>();

    //public TMap<Integer, String> tooltipMap = new THashMap<Integer, String>(); //TODO: Tooltips, although that should be in ItemEntry

	/**
	 *	Whether the item should register textures when registerIcons(...) is called. Basically leave this as true unless
	 *	you've got a reason. You don't need to know exactly what you've doing, but have a reason.
	 */
    public boolean hasTextures = true;

	/**
	 *	The resource directory for textures and unlocalized names. Defaults to "fluxgear", just as ItemBase defaults to
	 *	"cofh".	I recommend using the constructor that sets the name, the FluxGearItem(String name) one, since you're
	 *	probably not me. If you are, then either I cloned myself and made a time machine, or "Doctor" Algernop Krieger
	 *	cloned me from a cup I drank soda from, either way.
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
	 *	A copy of the null Icon, since Mojang's code is unfriendly.
	 */
	private static IIcon nullIcon;

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
	public ItemStack addItem(int metadata, ItemEntry entry, boolean register) {
		if (itemList.contains(metadata)) {
			ThaumicRevelations.logger.warn("Someone registered a meta-item in an item from " + modName + ", with a metadata of " + metadata + ", called " + entry.getName() + "! Don't do this! Skipping Entry!");
			return null;
		} else {
			itemMap.put(metadata, entry);
			itemList.add(metadata);

			ItemStack stack = new ItemStack(this, 1, metadata);
			if (register) {
				GameRegistry.registerCustomItemStack(entry.getName(), stack);
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
	public ItemStack addColorizedItem(int metadata, String name, String template, String texture, int color, int rarity, boolean register) {
		return addItem(metadata, new ItemEntryColorized(name, rarity).setColorData(template, texture, color), register);
	}

	public ItemStack addColorizedItem(int metadata, String name, String template, String texture, int color, int rarity) {
		return addColorizedItem(metadata, name, template, texture, color, rarity, true);
	}

	public ItemStack addColorizedItem(int metadata, String name, String template, String texture, int color) {
		return addColorizedItem(metadata, name, template, texture, color, 0, true);
	}

	public ItemStack addColorizedItem(int metadata, String name, String template, int color) {
		return addColorizedItem(metadata, name, template, name, color);
	}

	public ItemStack addColorizedItem(int metadata, String name, int color) {
		return addColorizedItem(metadata, name, name, name, color);
	}

    // addGradientItem(...) {}
	/*public ItemStack addGradientItem(int metadata, String name, String template, String texture, GradientNode[] colors, int rarity, boolean register) {
		return addItem(metadata, new ItemEntryGradient(name, rarity).setColorData(template, texture, colors), register);
	}

	public ItemStack addGradientItem(int metadata, String name, String template, String texture, GradientNode[] colors, int rarity) {
		return addGradientItem(metadata, name, template, texture, colors, rarity, true);
	}

	public ItemStack addGradientItem(int metadata, String name, String template, String texture, GradientNode[] colors) {
		return addGradientItem(metadata, name, template, texture, colors, 0, true);
	}

	public ItemStack addGradientItem(int metadata, String name, String template, String texture, GradientNode color) {
		return addGradientItem(metadata, name, template, texture, new GradientNode[] {color});
	}

	public ItemStack addGradientItem(int metadata, String name, String template, GradientNode[] colors) {
		return addGradientItem(metadata, name, template, name, colors);
	}

	public ItemStack addGradientItem(int metadata, String name, String template, GradientNode color) {
		return addGradientItem(metadata, name, template, name, color);
	}

	public ItemStack addGradientItem(int metadata, String name, GradientNode[] colors) {
		return addGradientItem(metadata, name, name, name, colors);
	}

	public ItemStack addGradientItem(int metadata, String name, GradientNode color) {
		return addGradientItem(metadata, name, name, name, color);
	}*/

    // addOreDictItem(...) {}
	public ItemStack addOreDictItem(int metadata, ItemEntry entry, boolean register, String... oreDict) {
		ItemStack stack = addItem(metadata, entry, register);
		RegistrationWrapper.registerOreDict(stack, (oreDict.length == 0 ? new String[] { entry.getName() } : oreDict));
		return stack;
	}

	public ItemStack addOreDictItem(int metadata, ItemEntry entry, String... oreDict) {
		return addOreDictItem(metadata, entry, true, oreDict);
	}

	public ItemStack addOreDictItem(int metadata, String name, int rarity, boolean register, String... oreDict) {
		return addOreDictItem(metadata, new ItemEntry(name, rarity).setTexture(name), register, oreDict);
	}

	public ItemStack addOreDictItem(int metadata, String name, int rarity, String... oreDict) {
		return addOreDictItem(metadata, name, rarity, true, oreDict);
	}

	public ItemStack addOreDictItem(int metadata, String name, String... oreDict) {
		return addOreDictItem(metadata, name, 0, true, oreDict);
	}

	// addOreDictItemWithEffect(...) {}
	public ItemStack addOreDictItemWithEffect(int metadata, String name, String texture, int rarity, boolean register, String... oreDict) {
		return addOreDictItem(metadata, new ItemEntry(name, rarity).setTexture(texture).setEnchanted(true), register, oreDict);
	}

	public ItemStack addOreDictItemWithEffect(int metadata, String name, int rarity, boolean register, String... oreDict) {
		return addOreDictItemWithEffect(metadata, name, name, rarity, register, oreDict);
	}

	public ItemStack addOreDictItemWithEffect(int metadata, String name, String texture, int rarity, String... oreDict) {
		return addOreDictItemWithEffect(metadata, name, texture, rarity, true, oreDict);
	}

	public ItemStack addOreDictItemWithEffect(int metadata, String name, int rarity, String... oreDict) {
		return addOreDictItemWithEffect(metadata, name, name, rarity, true, oreDict);
	}

	public ItemStack addOreDictItemWithEffect(int metadata, String name, String texture, String... oreDict) {
		return addOreDictItemWithEffect(metadata, name, texture, 0, true, oreDict);
	}

	public ItemStack addOreDictItemWithEffect(int metadata, String name) {
		return addOreDictItemWithEffect(metadata, name, name, 0, true, name);
	}

	// addColorizedOreDictItem(...) {}
	public ItemStack addColorizedOreDictItem(int metadata, String name, String template, String texture, int color, int rarity, boolean register, String... oreDict) {
		return addOreDictItem(metadata, new ItemEntryColorized(name, rarity).setColorData(template, texture, color), register, oreDict);
	}

	public ItemStack addColorizedOreDictItem(int metadata, String name, String template, String texture, int color, int rarity, String... oreDict) {
		return addColorizedOreDictItem(metadata, name, template, texture, color, rarity, true, oreDict);
	}

	public ItemStack addColorizedOreDictItem(int metadata, String name, String template, String texture, int color, String... oreDict) {
		return addColorizedOreDictItem(metadata, name, template, texture, color, 0, true, oreDict);
	}

	public ItemStack addColorizedOreDictItem(int metadata, String name, String template, int color, int rarity, String... oreDict) {
		return addColorizedOreDictItem(metadata, name, template, name, color, rarity, true, oreDict);
	}

	public ItemStack addColorizedOreDictItem(int metadata, String name, String template, int color, String... oreDict) {
		return addColorizedOreDictItem(metadata, name, template, name, color, oreDict);
	}

	public ItemStack addColorizedOreDictItem(int metadata, String name, int color, String... oreDict) {
		return addColorizedOreDictItem(metadata, name, name, name, color, oreDict);
	}

	public ItemStack addColorizedOreDictItemWithEffect(int metadata, String name, String template, String texture, int color, int rarity, boolean register, String... oreDict) {
		return addOreDictItem(metadata, new ItemEntryColorized(name, rarity).setColorData(template, texture, color).setEnchanted(true), register, oreDict);
	}

	public ItemStack addColorizedOreDictItemWithEffect(int metadata, String name, String template, String texture, int color, int rarity, String... oreDict) {
		return addColorizedOreDictItemWithEffect(metadata, name, template, texture, color, rarity, true, oreDict);
	}

	public ItemStack addColorizedOreDictItemWithEffect(int metadata, String name, String template, String texture, int color, String... oreDict) {
		return addColorizedOreDictItemWithEffect(metadata, name, template, texture, color, 0, true, oreDict);
	}

	public ItemStack addColorizedOreDictItemWithEffect(int metadata, String name, String template, int color, int rarity, String... oreDict) {
		return addColorizedOreDictItemWithEffect(metadata, name, template, name, color, rarity, true, oreDict);
	}

	public ItemStack addColorizedOreDictItemWithEffect(int metadata, String name, String template, int color, String... oreDict) {
		return addColorizedOreDictItemWithEffect(metadata, name, template, name, color, oreDict);
	}

	public ItemStack addColorizedOreDictItemWithEffect(int metadata, String name, int color, String... oreDict) {
		return addColorizedOreDictItemWithEffect(metadata, name, name, name, color, oreDict);
	}

	// addGradientOreDictItem(...) {}
	/*public ItemStack addGradientOreDictItem(int metadata, String name, String template, String texture, GradientNode[] colors, int rarity, boolean register, String... oreDict) {
		return addOreDictItem(metadata, new ItemEntryGradient(name, rarity).setColorData(template, texture, colors), register, oreDict);
	}

	public ItemStack addGradientOreDictItem(int metadata, String name, String template, String texture, GradientNode[] colors, int rarity, String... oreDict) {
		return addGradientOreDictItem(metadata, name, template, texture, colors, rarity, true, oreDict);
	}

	public ItemStack addGradientOreDictItem(int metadata, String name, String template, String texture, GradientNode[] colors, String... oreDict) {
		return addGradientOreDictItem(metadata, name, template, texture, colors, 0, true, oreDict);
	}

	public ItemStack addGradientOreDictItem(int metadata, String name, String template, String texture, GradientNode color, String... oreDict) {
		return addGradientOreDictItem(metadata, name, template, texture, new GradientNode[] {color}, oreDict);
	}

	public ItemStack addGradientOreDictItem(int metadata, String name, String template, GradientNode[] colors, String... oreDict) {
		return addGradientOreDictItem(metadata, name, template, name, colors, 0, true, oreDict);
	}

	public ItemStack addGradientOreDictItem(int metadata, String name, String template, GradientNode color, String... oreDict) {
		return addGradientOreDictItem(metadata, name, template, name, color, oreDict);
	}

	public ItemStack addGradientOreDictItem(int metadata, String name, GradientNode[] colors, String... oreDict) {
		return addGradientOreDictItem(metadata, name, name, name, colors, oreDict);
	}

	public ItemStack addGradientOreDictItem(int metadata, String name, GradientNode color, String... oreDict) {
		return addGradientOreDictItem(metadata, name, name, name, color, oreDict);
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
			if (!itemMap.get(meta).getDisabled()) {
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
		return "item." + modName + (folder == null ? "" : "." + folder) + (itemList.contains(meta) ? "." + itemMap.get(meta).getName() + (itemMap.get(meta).getAltName() ? "Alt" : "") : ".invalid");
	}

	@Override
	public String getItemStackDisplayName (ItemStack stack) {
		int meta = stack.getItemDamage();
		if (itemList.contains(meta) && registryItem) {
			String itemName = itemMap.get(meta).getName();
			if (StatCollector.canTranslate("item." + modName + "." + itemMap.get(meta).getName() + ".name")) { //Custom Name
				return StatCollector.translateToLocal("item." + modName + "." + itemMap.get(meta).getName() + ".name");
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
		return itemList.contains(meta) ? EnumRarity.values()[itemMap.get(meta).getRarity()] : EnumRarity.common;
	}

    public String getInternalName(ItemStack itemstack) {
        int meta = itemstack.getItemDamage();
        return itemList.contains(meta) ? itemMap.get(meta).getName() : "invalid";
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
	public int getRenderPasses(int metadata) {
		return (itemList.contains(metadata) && itemMap.get(metadata) instanceof ItemEntryColorizedOverlay) ? 3 : 2;
	}

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata) {
        return itemList.contains(metadata) ? itemMap.get(metadata).getIcon() : null;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int metadata, int pass) {
		if (itemList.contains(metadata)) {
			if (pass == 0) {
				return itemMap.get(metadata).getIcon();
			} else if (itemMap.get(metadata) instanceof ItemEntryColorizedOverlay) {
				return ((ItemEntryColorizedOverlay) itemMap.get(metadata)).getIconOverlay();
			}
		}
		return null;
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
		ThaumicRevelations.logger.info("Registering Icons");
		ItemEntry entry;
		//if (!GrayscaleEntry.initialized) {
		GrayscaleEntry.registerIcons(register); //TODO: Can't find a reliable way to only do this when needed short of splicing into FluxGearItem, and I don't want to this any more complicated.
		//}
		nullIcon = register.registerIcon("missingno");
        if (hasTextures) {
			//TextureMap map = (TextureMap) register;
			IIcon icon;
			//String template;
			for (int meta : itemList) {
				if (itemList.contains(meta) && !itemMap.get(meta).getDisabled()) {
					entry = itemMap.get(meta);
					if (entry instanceof ItemEntryColorized) {
						icon = GrayscaleEntry.getIcon(((ItemEntryColorized) entry).getTemplate());
						entry.setIcon(icon != null ? icon : register.registerIcon(getIconFromMeta(meta)));
						if (entry instanceof ItemEntryColorizedOverlay) {
							((ItemEntryColorizedOverlay) entry).setIconOverlay(register.registerIcon(modName + ":" + folder + (folder == null ? "" : "/") + ((ItemEntryColorizedOverlay) entry).getTextureOverlay()));
						}
					/*} else if (entry instanceof ItemEntryGradient) { //TODO: Re-enable gradients, when I get them working. Now that I have a working colorizer, it is low priority.
						icon = map.getTextureExtry(getIconFromMeta(meta));

						if (icon == null) {
							template = GrayscaleEntry.getTexture(((ItemEntryGradient) entry).template);
							if (template == null) {
								ThaumicRevelations.logger.info("Icon null for" + entry.name);
							}*/
							//template = TextureHelper.itemTextureExists(template) ? /*TextureHelper.getItemTexture(*/template/*)*/ : (getIconFromMeta(meta) + ".png");

							/*icon = new GradientTexture(getIconFromMeta(meta) + ".png", template, false, ((ItemEntryGradient) entry).gradients);
							if (map.setTextureEntry(icon.getIconName(), (TextureAtlasSprite) icon)) {
								entry.icon = icon;
							} else {
								ThaumicRevelations.logger.error("Registration of " + getIconFromMeta(meta) + " failed! I don't know why.");
								entry.icon = register.registerIcon(getIconFromMeta(meta));
							}
						} else {
							ThaumicRevelations.logger.error("Registration of " + getIconFromMeta(meta) + " failed! Said icon already exists!");
						}*/
					} else {
						entry.setIcon(register.registerIcon(getIconFromMeta(meta)));
					}
				}
			}
        }
    }

    @Override
    public IIcon getIcon(ItemStack stack, int renderPass) {
		int meta = stack.getItemDamage();
		if (itemList.contains(meta)) {
			if (renderPass == 0 || renderPass == 1) {
				return itemMap.get(meta).getIcon();
			} else if (renderPass == 2 && itemMap.get(meta) instanceof ItemEntryColorizedOverlay) {
				return ((ItemEntryColorizedOverlay) itemMap.get(meta)).getIconOverlay();
			}
		}
		return nullIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (renderPass == 1 && itemList.contains(stack.getItemDamage())) {
			ItemEntry entry = itemMap.get(stack.getItemDamage());
			if (!entry.getDisabled() && entry instanceof ItemEntryColorized) {
				return ((ItemEntryColorized) entry).getColor() & ColorLibrary.CLEAR;
			}
		}
        return ColorLibrary.CLEAR;
    }

    public String getIconFromMeta(int metadata) {
    	return modName + ":" + folder + (folder == null ? "" : "/") + itemMap.get(metadata).getTexture();
    }

    public boolean hasTexture(int metadata) {
        return TextureHelper.itemTextureExists(getIconFromMeta(metadata));
    }

	public void setEnchanted(int metadata, boolean bool) {
		if (itemList.contains(metadata)) {
			itemMap.get(metadata).setEnchanted(bool);
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
			if (itemList.contains(meta)) {
				return itemMap.get(meta).getEnchanted();
			}
		}
		return false;
	}

/**    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        int meta = stack.getItemDamage();
        if (itemList.contains(meta)) {
            if (tooltipMap.containsKey(meta)){
                //TODO: String separation
                list.add(tooltipMap.get(meta));
            }
        }
    }*/
}
