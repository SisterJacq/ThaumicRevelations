package mortvana.melteddashboard.util;

import net.minecraft.util.IIcon;

public enum EnumGrayscaleItems {
    INGOT("ingot"),
    NUGGET("nugget"),
    DUST("dust"),
    GEAR("gear"),
    NULL("null");

    private String name;
    private IIcon icon;

    private EnumGrayscaleItems(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public IIcon getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(IIcon icon) {
        this.icon = icon;
    }

    public static EnumGrayscaleItems getValue(String name) {
        for (EnumGrayscaleItems item : values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return NULL;
    }
}