package mortvana.melteddashboard.item.data;

public class ItemEntryColorized extends ItemEntry {

	protected String template;
	protected int color = 0xFFFFFF;

	public ItemEntryColorized(String name, int rarity, int maxDamage) {
		super(name, rarity, maxDamage);
	}

	public ItemEntryColorized(String name, int rarity) {
		super(name, rarity);
	}

	public ItemEntryColorized(String name) {
		super(name);
	}

	/**
	 * @param template - Grayscale version of the texture, which is colorized if the colorized texture is not found.
	 * @param texture - Colorized version of the texture, which is used if found, for items with custom sprites.
	 * @param color - The (A)RGB color used to colorize the template with the vanilla colorizer.
	 */
	public ItemEntryColorized setColorData(String template, String texture, int color) {
		this.template = template;
		this.texture = texture;
		this.color = color;
		return this;
	}

	public String getTemplate() {
		return template;
	}

	public int getColor() {
		return color;
	}
}
