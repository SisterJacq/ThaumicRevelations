package mortvana.thaumrev.core.common;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.registry.GameRegistry;

import magicbees.api.MagicBeesAPI;
import mortvana.thaumrev.core.block.BlockThaumicPlant;
import mortvana.thaumrev.core.block.ItemBlockThaumicPlant;
import mortvana.thaumrev.core.common.config.ThaumicRevelationsCoreConfig;
import mortvana.thaumrev.core.item.ItemArmorPrimal;
import mortvana.thaumrev.melteddashboard.intermod.thaumcraft.research.FluxGearResearchItem;
import mortvana.thaumrev.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.thaumrev.melteddashboard.item.FluxGearItem;
import mortvana.thaumrev.melteddashboard.registry.RegistrationWrapper;
import mortvana.thaumrev.melteddashboard.util.ConfigBase;
import mortvana.thaumrev.melteddashboard.util.IConfigInitialized;
import mortvana.thaumrev.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;

import static thaumcraft.api.aspects.Aspect.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;
import static mortvana.thaumrev.melteddashboard.lib.ThaumcraftLibrary.*;

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
    }

    @Override
    public void postInit() {
	    ResearchCategories.registerCategory(RESEARCH_KEY, new ResourceLocation(RESOURCE_PREFIX, "textures/items/baubles/amuletWarden.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
	    aluminiumArc();
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

		primalGoggles = new ItemArmorPrimal(0, "primal.goggles", "primalGoggles").register("goggles", "PrimalGoggles");
		primalRobes = new ItemArmorPrimal(1, "primal.robes", "primalRobes").register("robes", "PrimalRobes");
		primalPants = new ItemArmorPrimal(2, "primal.pants", "primalPants").register("pants", "PrimalPants");
		primalBoots = new ItemArmorPrimal(3, "primal.boots", "primalBoots").register("boots", "PrimalBoots");
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
		RegistrationWrapper.registerOreDict(dustSalisMundus, "dustSalisMundus");
		RegistrationWrapper.registerOreDict(itemEnchantedFabric, "itemEnchantedFabric");
		RegistrationWrapper.registerOreDict(itemQuicksilverDrop, "itemQuicksilverDrop");
	}

	public void loadRecipes() {
		RegistrationWrapper.addSmelting(rawThaumicBronze, ingotThaumicBronze, 2.0F);
	}

	public void loadThaumicRecipes() {
		recipeThaumicBronzeRaw = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyThaumicBronze, rawThaumicBronze, new AspectList().add(ORDER, 5).add(EARTH, 5).add(FIRE, 5), "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetThaumium", "nuggetThaumium", "nuggetBrass", "itemQuicksilverDrop", "dustSalisMundus");

		recipePrimalGoggles = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalGoggles, 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackGoggles, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, stackThaumometer });
		recipePrimalRobes = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalRobes, 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackChestRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, ingotThaumium });
		recipePrimalPants = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalPants, 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackLegsRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, itemNitor });
		recipePrimalBoots = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalBoots, 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackBootsRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, planksGreatwood });
	}

	public void loadResearch() {
		researchThaumRev = new FluxGearResearchItem(keyThaumRev, RESEARCH_KEY, new AspectList(), 0, 0, 0, new ItemStack(Items.potato)).setRound().setSpecial().setAutoUnlock().registerResearchItem();
		researchThaumRev.setPages(new ResearchPage("0"));

		researchThaumicBronze = new FluxGearResearchItem(keyThaumicBronze, RESEARCH_KEY, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ORDER, 1), 0, 3, 1, ingotThaumicBronze).setParents(keyThaumRev, "THAUMIUM").registerResearchItem();
		researchThaumicBronze.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeRaw), new ResearchPage(rawThaumicBronze));

		researchPrimalRobes = new FluxGearResearchItem(keyRobesPrimal, RESEARCH_KEY, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(ENERGY, 2).add(SENSES, 2).add(ThaumcraftHelper.newPrimalAspectList(1)), 1, 5, 3, new ItemStack(Items.potato)/*primalRobes.stack*/).setParents(keyThaumRev, "THAUMIUM", "ENCHFABRIC", "GOGGLES", "NITOR", "INFUSION", "INFUSIONENCHANTMENT").registerResearchItem();
		researchPrimalRobes.setPages(new ResearchPage("0")/*, new ResearchPage(recipePrimalGoggles), new ResearchPage(recipePrimalRobes), new ResearchPage(recipePrimalPants), new ResearchPage(recipePrimalBoots)*/);
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
