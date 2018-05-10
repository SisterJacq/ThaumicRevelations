package mortvana.melteddashboard.item;

import mortvana.melteddashboard.client.texture.GradientNode;

public class ItemEntryGradient extends ItemEntry{

	protected GradientNode[] gradients = new GradientNode[0];

	public ItemEntryGradient(String name, int rarity, int maxDamage) {
		super(name, rarity, maxDamage);
	}

	public ItemEntryGradient(String name, int rarity) {
		super(name, rarity);
	}

	public ItemEntryGradient(String name) {
		super(name);
	}

	/**
	 * @param template - Grayscale version of the texture, which is colorized if the colorized texture is not found.
	 * @param texture - Colorized version of the texture, which is used if found, for items with custom sprites.
	 * @param gradients - The GradientNode array to use for gradients. If provided with just one entry, that color will
	 *                  be used to colorize the template with the vanilla colorizer.
	 */
	public ItemEntry setColorData(String template, String texture, GradientNode[] gradients) {
		this.template = template;
		this.texture = texture;
		this.gradients = gradients;
		return this;
	}
}
