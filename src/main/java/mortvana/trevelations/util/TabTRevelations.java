package mortvana.trevelations.util;

import mortvana.trevelations.common.ModContent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabTRevelations extends CreativeTabs {

    public TabTRevelations(String label) {

        super(label);

    }

    @Override
    public Item getTabIconItem() {

        return ModContent.itemWardenAmulet;

    }

}
