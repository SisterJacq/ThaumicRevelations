package mortvana.thaumicrevelations.melteddashboard.item;

import net.minecraft.util.IIcon;

public class ItemEntry {

    public String name;
    public IIcon icon;
    public int rarity = 0;
    public int maxDamage = 0;

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

    public void setIcon(IIcon icon) {
        this.icon = icon;
    }
}