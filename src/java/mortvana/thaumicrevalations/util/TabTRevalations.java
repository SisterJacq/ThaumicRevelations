package mortvana.thaumicrevalations.util;

import mortvana.thaumicrevalations.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabTRevalations extends CreativeTabs {

    public TabTRevalations(String label) {

        super(label);

    }

    @Override
    public Item getTabIconItem() {

        return ModItems.itemWardenAmulet;

    }

}
