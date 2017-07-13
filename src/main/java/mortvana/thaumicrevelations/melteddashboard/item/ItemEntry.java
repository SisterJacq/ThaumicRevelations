package mortvana.thaumicrevelations.melteddashboard.item;

import net.minecraft.util.IIcon;

/**
 *  Class of an Object used for storing data relating to metadata based items.
 *
 *  @author Mortvana
 */
public class ItemEntry {

    public String name, template, texture;
    public IIcon icon;
    public int rarity = 0;
    public int maxDamage = 0;
	public int color = 0xFFFFFF;

    public ItemEntry(String name, int rarity, int maxDamage) {
        this.name = name;
        this.rarity = rarity;
        this.maxDamage = maxDamage;
	    setTexture(name);
    }

    public ItemEntry(String name, int rarity) {
        this.name = name;
        this.rarity = rarity;
	    setTexture(name);
    }

    public ItemEntry(String name) {
        this.name = name;
	    setTexture(name);
    }

    public ItemEntry setIcon(IIcon icon) {
        this.icon = icon;
	    return this;
    }

	/**
	 * @param template - Grayscale version of the texture, which is colorized if the colorized texture is not found.
	 * @param texture - Colorized version of the texture, which is used if found, for items with custom sprites.
	 * @param color - Hexadecimal RGB color to colorize the template with.
	 */
	public ItemEntry setColorData(String template, String texture, int color) {
		this.template = template;
		this.texture = texture;
		this.color = color;
		return this;
	}

	public ItemEntry setTexture(String texture) {
		this.texture = texture;
		return this;
	}

}