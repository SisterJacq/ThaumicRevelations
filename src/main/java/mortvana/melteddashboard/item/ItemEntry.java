package mortvana.melteddashboard.item;

import net.minecraft.util.IIcon;

import mortvana.melteddashboard.client.texture.GradientNode;

/**
 *  Class of an Object used for storing data relating to metadata based items.
 *
 *  @author Mortvana
 */
public class ItemEntry {

    protected String name, template, texture;
    protected IIcon icon;
    protected int rarity = 0;
    protected int maxDamage = 0;
	protected GradientNode[] gradients = new GradientNode[0];
	protected boolean altName = false;
	protected boolean enchanted = false;
	protected boolean textureFound;
	protected boolean disabled;

    public ItemEntry(String name, int rarity, int maxDamage) {
        this.name = name;
        this.rarity = rarity;
        this.maxDamage = maxDamage;
    }

    public ItemEntry(String name, int rarity) {
        this.name = name;
        this.rarity = rarity;
    }

    public ItemEntry(String name) {
        this.name = name;
    }

    public ItemEntry setIcon(IIcon icon) {
        this.icon = icon;
	    return this;
    }

	/**
	 * @param template - Grayscale version of the texture, which is colorized if the colorized texture is not found.
	 * @param texture - Colorized version of the texture, which is used if found, for items with custom sprites.
	 * @param gradients - Hexadecimal RGB color to colorize the template with.
	 */
	public ItemEntry setColorData(String template, String texture, GradientNode[] gradients) {
		this.template = template;
		this.texture = texture;
		this.gradients = gradients;
		return this;
	}

	public ItemEntry setTexture(String texture) {
		this.texture = texture;
		return this;
	}

	public ItemEntry setEnchanted(boolean enchanted) {
		this.enchanted = enchanted;
		return this;
	}

	public boolean isDisabled() {
		return disabled;
	}
}