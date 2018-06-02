package mortvana.melteddashboard.item.entry;

import net.minecraft.util.IIcon;

public class ItemEntryColorizedOverlay extends ItemEntryColorized {

	private String textureOverlay;
	private IIcon iconOverlay;

	public ItemEntryColorizedOverlay(String name, int rarity, int maxDamage) {
		super(name, rarity, maxDamage);
	}

	public ItemEntryColorizedOverlay(String name, int rarity) {
		super(name, rarity);
	}

	public ItemEntryColorizedOverlay(String name) {
		super(name);
	}

	public ItemEntryColorizedOverlay setTextureOverlay(String textureOverlay) {
		this.textureOverlay = textureOverlay;
		return this;
	}

	public ItemEntryColorizedOverlay setIconOverlay(IIcon iconOverlay) {
		this.iconOverlay = iconOverlay;
		return this;
	}

	public String getTextureOverlay() {
		return textureOverlay;
	}

	public IIcon getIconOverlay() {
		return iconOverlay;
	}
}
