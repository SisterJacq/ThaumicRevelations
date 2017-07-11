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
        ingotCopper = generalItem.addOreDictItem(0, "ingotCopper", "ingotCopper"); //FIX
	    ingotZinc = generalItem.addOreDictItem(1, "ingotZinc", "ingotZinc"); //FIX
	    ingotTin = generalItem.addOreDictItem(2, "ingotTin", "ingotTin"); //FIX

	    nuggetCopper = generalItem.addOreDictItem(10, "nuggetCopper", "nuggetCopper");
	    nuggetZinc = generalItem.addOreDictItem(11, "nuggetZinc", "nuggetZinc");
	    nuggetTin = generalItem.addOreDictItem(12, "nuggetTin", "nuggetTin");

	    dustCopper = generalItem.addOreDictItem(20, "dustCopper", "dustCopper");
	    dustZinc = generalItem.addOreDictItem(21, "dustZinc", "dustZinc");
	    dustTin = generalItem.addOreDictItem(22, "dustTin", "dustTin");

	    ingotBrass = generalItem.addOreDictItem(30, "ingotBrass", "ingotBrass");
	    ingotBronze = generalItem.addOreDictItem(31, "ingotBronze", "ingotBronze"); //FIX
	    ingotThaumicBronze = generalItem.addOreDictItem(32, "ingotThaumicBronze", "ingotThaumicBronze"); //FIX

	    nuggetBrass = generalItem.addOreDictItem(40, "nuggetBrass", "nuggetBrass"); //FIX
	    nuggetBronze = generalItem.addOreDictItem(41, "nuggetBronze", "nuggetBronze");
	    nuggetThaumicBronze = generalItem.addOreDictItem(42, "nuggetThaumicBronze", "nuggetThaumicBronze");

	    dustBrass = generalItem.addOreDictItem(50, "dustBrass", "dustBrass");
	    dustBronze = generalItem.addOreDictItem(51, "dustBronze", "dustBronze");
	    dustThaumicBronze = generalItem.addOreDictItem(52, "dustThaumicBronze", "dustThaumicBronze");

	    rawThaumicBronze = generalItem.addOreDictItem(60, "ingotThaumicBronzeRaw", "ingotThaumicBronzeRaw");
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
