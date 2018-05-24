package mortvana.melteddashboard.item.entry;

import net.minecraft.util.IIcon;

public class ItemEntryColorizedOverlay extends ItemEntryColorized {

	public String textureOverlay;
	public IIcon iconOverlay;

	public ItemEntryColorizedOverlay(String name, int rarity, int maxDamage) {
		super(name, rarity, maxDamage);
	}

	public ItemEntryColorizedOverlay(String name, int rarity) {
		super(name, rarity);
	}

	public ItemEntryColorizedOverlay(String name) {
		super(name);
	}

	public void setTextureOverlay(String textureOverlay) {
		this.textureOverlay = textureOverlay;
	}

	public void setIconOverlay(IIcon iconOverlay) {
		this.iconOverlay = iconOverlay;
	}

	public String getTextureOverlay() {
		return textureOverlay;
	}

	public IIcon getIconOverlay() {
		return iconOverlay;
	}
}
