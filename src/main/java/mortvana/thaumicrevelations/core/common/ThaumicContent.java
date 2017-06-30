package mortvana.thaumicrevelations.core.common;

import cpw.mods.fml.common.registry.GameRegistry;

import magicbees.api.MagicBeesAPI;
import mortvana.thaumicrevelations.core.block.BlockThaumicPlant;
import mortvana.thaumicrevelations.core.block.ItemBlockThaumicPlant;
import mortvana.thaumicrevelations.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.thaumicrevelations.melteddashboard.item.FluxGearItem;
import mortvana.thaumicrevelations.melteddashboard.util.ConfigBase;
import mortvana.thaumicrevelations.melteddashboard.util.IConfigInitialized;
import thaumcraft.api.aspects.Aspect;

import static mortvana.thaumicrevelations.library.ThaumicLibrary.*;

public class ThaumicContent implements IConfigInitialized {

	@Override
	public void setConfig(ConfigBase config) {

	}

    @Override
    public void preInit() {
        thaumicRevelationsTab = new FluxGearCreativeTab("Thaumic Revelations", "thaumicRevelations", wardenAmulet);

	    createBlocks();
	    createItems();
    }

    @Override
    public void init() {
        dummyGeneral = generalItem.addItem(0, "dummy");
    }

    @Override
    public void postInit() {
        determineTempus();
    }

	public void createBlocks() {
		blockThaumicPlant = new BlockThaumicPlant();

		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class,  "blockThaumicPlant");
	}

	public void createItems() {
		generalItem = (FluxGearItem) new FluxGearItem(RESOURCE_PREFIX, thaumicRevelationsTab).setFolder("material").setUnlocalizedName("material");
	}

    public void determineTempus() {
        // Thanks for the API hook, Myst!
        Object protoTempus = MagicBeesAPI.thaumcraftAspectTempus;
        if (protoTempus != null && protoTempus instanceof Aspect) {
            tempus = (Aspect) protoTempus;
        } else {
            tempus = Aspect.VOID;
        }
    }

    public static Aspect tempus;
}
