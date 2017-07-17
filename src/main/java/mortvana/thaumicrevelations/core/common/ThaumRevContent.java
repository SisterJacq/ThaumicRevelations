package mortvana.thaumicrevelations.core.common;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.registry.GameRegistry;

import magicbees.api.MagicBeesAPI;
import mortvana.thaumicrevelations.core.block.BlockThaumicPlant;
import mortvana.thaumicrevelations.core.block.ItemBlockThaumicPlant;
import mortvana.thaumicrevelations.core.common.config.ThaumicRevelationsCoreConfig;
import mortvana.thaumicrevelations.melteddashboard.intermod.thaumcraft.research.FluxGearResearchItem;
import mortvana.thaumicrevelations.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.thaumicrevelations.melteddashboard.item.FluxGearItem;
import mortvana.thaumicrevelations.melteddashboard.registry.RegistrationWrapper;
import mortvana.thaumicrevelations.melteddashboard.util.ConfigBase;
import mortvana.thaumicrevelations.melteddashboard.util.IConfigInitialized;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;

import static thaumcraft.api.aspects.Aspect.*;
import static mortvana.thaumicrevelations.library.ThaumRevLibrary.*;

public class ThaumRevContent implements IConfigInitialized {

	public ThaumicRevelationsCoreConfig config;

	@Override
	public void setConfig(ConfigBase config) {
		if (config instanceof ThaumicRevelationsCoreConfig) {
			this.config = (ThaumicRevelationsCoreConfig) config;
		} else {
			ThaumicRevelations.logger.error("Wrong Config file passed. Reverting to defaults!");
		}
	}

    @Override
    public void preInit() {
        thaumicRevelationsTab = new FluxGearCreativeTab("Thaumic Revelations", "thaumicRevelations", wardenAmulet);

	    createBlocks();
	    createItems();
    }

    @Override
    public void init() {
        loadItems();
	    aluminiumArc();
    }

    @Override
    public void postInit() {
	    ResearchCategories.registerCategory(RESEARCH_KEY, new ResourceLocation(RESOURCE_PREFIX, "textures/items/baubles/amuletWarden.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
        loadRecipes();
	    loadThaumicRecipes();
	    loadResearch();
	    determineTempus();
    }

	public void createBlocks() {
		blockThaumicPlant = new BlockThaumicPlant();

		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class,  "blockThaumicPlant");
	}

	public void createItems() {
		generalItem = (FluxGearItem) new FluxGearItem(RESOURCE_PREFIX, thaumicRevelationsTab).setFolder("material").setUnlocalizedName("material");
	}

	public void loadItems() {
		ingotCopper = generalItem.addOreDictItem(0, "ingotCopper"); //FIX
		ingotZinc = generalItem.addOreDictItem(1, "ingotZinc"); //FIX
		ingotTin = generalItem.addOreDictItem(2, "ingotTin"); //FIX

		nuggetCopper = generalItem.addOreDictItem(10, "nuggetCopper");
		nuggetZinc = generalItem.addOreDictItem(11, "nuggetZinc");
		nuggetTin = generalItem.addOreDictItem(12, "nuggetTin");

		dustCopper = generalItem.addOreDictItem(20, "dustCopper");
		dustZinc = generalItem.addOreDictItem(21, "dustZinc");
		dustTin = generalItem.addOreDictItem(22, "dustTin");

		ingotBrass = generalItem.addOreDictItem(30, "ingotBrass");
		ingotBronze = generalItem.addOreDictItem(31, "ingotBronze"); //FIX
		ingotThaumicBronze = generalItem.addOreDictItem(32, "ingotThaumicBronze"); //FIX
		ingotSteel = generalItem.addOreDictItem(33, "ingotSteel");
		ingotVoidbrass = generalItem.addOreDictItem(34, "ingotVoidbrass");
		ingotVoidsteel = generalItem.addOreDictItem(35, "ingotVoidsteel");

		nuggetBrass = generalItem.addOreDictItem(40, "nuggetBrass");
		nuggetBronze = generalItem.addOreDictItem(41, "nuggetBronze");
		nuggetThaumicBronze = generalItem.addOreDictItem(42, "nuggetThaumicBronze");
		nuggetSteel = generalItem.addOreDictItem(43, "nuggetSteel");
		nuggetVoidbrass = generalItem.addOreDictItem(44, "nuggetVoidbrass");
		nuggetVoidsteel = generalItem.addOreDictItem(45, "nuggetVoidsteel");

		dustBrass = generalItem.addOreDictItem(50, "dustBrass"); //FIX
		dustBronze = generalItem.addOreDictItem(51, "dustBronze"); //FIX
		dustThaumicBronze = generalItem.addOreDictItem(52, "dustThaumicBronze");
		dustSteel = generalItem.addOreDictItem(53, "dustSteel");
		dustVoidbrass = generalItem.addOreDictItem(54, "dustVoidbrass");
		dustVoidsteel = generalItem.addOreDictItem(55, "dustVoidsteel");

		rawThaumicBronze = generalItem.addOreDictItem(60, "ingotThaumicBronzeRaw");
	}

	public void aluminiumArc() {
		RegistrationWrapper.registerOreDict(ItemApi.getItem("itemNugget", 5), "itemQuicksilverDrop");
		RegistrationWrapper.registerOreDict(ItemApi.getItem("itemResource", 14), "dustSalisMundus");
		RegistrationWrapper.registerOreDict(ItemApi.getItem("itemResource", 7), "itemEnchantedFabric");

		itemGoggles = ItemApi.getItem("itemGoggles", 0).getItem();
	}

	public void loadRecipes() {
		RegistrationWrapper.addSmelting(rawThaumicBronze, ingotThaumicBronze, 2.0F);
	}

	public void loadThaumicRecipes() {
		recipeThaumicBronzeRaw = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyThaumicBronze, rawThaumicBronze, new AspectList().add(ORDER, 5).add(EARTH, 5).add(FIRE, 5), "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetThaumium", "nuggetThaumium", "nuggetBrass", "itemQuicksilverDrop", "dustSalisMundus");
	}

	public void loadResearch() {
		researchThaumRev = new FluxGearResearchItem(keyThaumRev, RESEARCH_KEY, new AspectList(), 0, 0, 0, new ItemStack(Items.potato)).setRound().setSpecial().setAutoUnlock().registerResearchItem();
		researchThaumRev.setPages(new ResearchPage("0"));



		researchThaumicBronze = new FluxGearResearchItem(keyThaumicBronze, RESEARCH_KEY, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ORDER, 1), 0, -3, 1, ingotThaumicBronze).setParents(keyThaumRev, "THAUMIUM").registerResearchItem();
		researchThaumicBronze.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeRaw), new ResearchPage(rawThaumicBronze));
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

	public static Item itemGoggles;
}
