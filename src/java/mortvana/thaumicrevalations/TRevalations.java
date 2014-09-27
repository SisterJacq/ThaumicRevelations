package mortvana.thaumicrevalations;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mortvana.thaumicrevalations.block.ModBlocks;
import mortvana.thaumicrevalations.crafting.ModRecipes;
import mortvana.thaumicrevalations.entity.ModEntities;
import mortvana.thaumicrevalations.item.ModItems;
import mortvana.thaumicrevalations.lib.ModLib;
import mortvana.thaumicrevalations.client.gui.GuiHandler;
import mortvana.thaumicrevalations.proxy.CommonProxy;
import mortvana.thaumicrevalations.research.ModResearch;
import mortvana.thaumicrevalations.util.TabTRevalations;
import mortvana.thaumicrevalations.util.wardenic.WardenicChargeEvents;
import mortvana.thaumicrevalations.util.wardenic.WardenicUpgrades;
import mortvana.thaumicrevalations.world.ModGen;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = ModLib.ID, name = ModLib.NAME, version = ModLib.VERSION, dependencies = ModLib.DEPENDENCIES)

public class TRevalations {

    @Instance(ModLib.ID)
    public static TRevalations instance;

    @SidedProxy(serverSide = ModLib.COMMONPROXY, clientSide = ModLib.CLIENTPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabTWarden = new TabTRevalations(ModLib.ID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        proxy.initRenderers();
        GuiHandler.init();

        WardenicChargeEvents.init();
        WardenicUpgrades.init();

        ModItems.init();
        ModBlocks.init();
        ModEntities.init();
        ModGen.init();

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        ModRecipes.init();
        ModResearch.init();

    }

}
