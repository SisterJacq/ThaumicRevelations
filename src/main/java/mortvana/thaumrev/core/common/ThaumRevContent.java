package mortvana.thaumrev.core.common;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

import magicbees.api.MagicBeesAPI;
import mortvana.thaumrev.core.block.BlockThaumicPlant;
import mortvana.thaumrev.core.block.ItemBlockThaumicPlant;
import mortvana.thaumrev.core.common.config.ThaumRevCoreConfig;
import mortvana.thaumrev.core.item.ItemArmorPrimal;
import mortvana.thaumrev.core.item.ItemThaumRev;
import mortvana.thaumrev.melteddashboard.item.FluxGearItemInteractive;
import mortvana.thaumrev.melteddashboard.intermod.thaumcraft.research.FluxGearResearchItem;
import mortvana.thaumrev.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.thaumrev.melteddashboard.registry.RegistrationWrapper;
import mortvana.thaumrev.melteddashboard.util.IInitialized;
import mortvana.thaumrev.melteddashboard.util.helpers.ItemHelper;
import mortvana.thaumrev.melteddashboard.util.helpers.mod.ThaumcraftHelper;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;

import static thaumcraft.api.aspects.Aspect.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;
import static mortvana.thaumrev.melteddashboard.lib.ThaumcraftLibrary.*;

public class ThaumRevContent implements IInitialized {

    @Override
    public void preInit() {
        thaumicRevelationsTab = new FluxGearCreativeTab("Thaumic Revelations", "thaumicRevelations", wardenAmulet);

	    createBlocks();
	    createItems();
	    setResearchLevel();
    }

    @Override
    public void init() {
        loadItems();
    }

    @Override
    public void postInit() {
	    ResearchCategories.registerCategory(RESEARCH_KEY, new ResourceLocation(RESOURCE_PREFIX, "textures/items/baubles/amuletWarden.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
	    aluminiumArc();
	    addLoot();
	    determineTempus();
	    loadRecipes();
	    loadThaumicRecipes();
	    loadResearch();
	    initResearch();
	    setPages();
    }

	public void createBlocks() {
		blockThaumicPlant = new BlockThaumicPlant();

		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class, "blockThaumicPlant");
	}

	public void createItems() {
		generalItem = new ItemThaumRev();

		primalGoggles = new ItemArmorPrimal(0, "primal.goggles", "primalGoggles").register("goggles", "PrimalGoggles");
		primalRobes = new ItemArmorPrimal(1, "primal.robes", "primalRobes").register("robes", "PrimalRobes");
		primalPants = new ItemArmorPrimal(2, "primal.pants", "primalPants").register("pants", "PrimalPants");
		primalBoots = new ItemArmorPrimal(3, "primal.boots", "primalBoots").register("boots", "PrimalBoots");
	}

	public void setResearchLevel() {
		/*int lvl = ThaumRevCoreConfig.researchLevel;
		if (lvl < -1) {
			ThaumicRevelations.logger.error("Someone manually set our difficulty to " + lvl + "! Value should be between -1 and 2, inclusive. SETTING IT TO -1 FOR THIS LAUNCH!");
			lvl = -1;
		} else if (lvl > 2) {
			ThaumicRevelations.logger.error("Someone manually set our difficulty to " + lvl + "! I know challenges are fun, but the value should be between -1 and 2, inclusive. SETTING IT TO 2 FOR THIS LAUNCH!");
			lvl = 2;
		}
		if (lvl == -1) {
			try {
				Object obj = Class.forName("thaumcraft.common.config.Config").getField("researchDifficulty").get(null);
				if (obj instanceof Integer) {
					int temp = (Integer) obj;
					if (temp < 2 && temp > -2) {
						lvl = temp + 1;
					} else {
						ThaumicRevelations.logger.error("Thaumcraft's config data was not a value it should be! RESORTING TO DEFAULT OF 1!");
						lvl = 1;
					}
				} else {
					ThaumicRevelations.logger.error("Thaumcraft's config data was not a type it should be! RESORTING TO DEFAULT OF 1!");
					lvl = 1;
				}
			} catch (Exception ex) {
				ThaumicRevelations.logger.error("Thaumic Revelations couldn't find Thaumcraft's config to set our research information through hacky reflection! RESORTING TO DEFAULT OF 1!");
				lvl = 1;
			}
		}*/
		researchLevel = 2;//lvl;
	}

	public void loadItems() {
		ingotCopper = generalItem.addOreDictItem(0, "ingotCopper");
		ingotZinc = generalItem.addOreDictItem(1, "ingotZinc");
		ingotTin = generalItem.addOreDictItem(2, "ingotTin");

		nuggetCopper = generalItem.addOreDictItem(10, "nuggetCopper");
		nuggetZinc = generalItem.addOreDictItem(11, "nuggetZinc");
		nuggetTin = generalItem.addOreDictItem(12, "nuggetTin");

		dustCopper = generalItem.addOreDictItem(20, "dustCopper");
		dustZinc = generalItem.addOreDictItem(21, "dustZinc");
		dustTin = generalItem.addOreDictItem(22, "dustTin");

		ingotBrass = generalItem.addOreDictItem(30, "ingotBrass");
		ingotBronze = generalItem.addOreDictItem(31, "ingotBronze");
		ingotThaumicBronze = generalItem.addOreDictItem(32, "ingotThaumicBronze");
		ingotSteel = generalItem.addOreDictItem(33, "ingotSteel");
		ingotVoidbrass = generalItem.addOreDictItem(34, "ingotVoidbrass");
		ingotVoidsteel = generalItem.addOreDictItem(35, "ingotVoidsteel");

		nuggetBrass = generalItem.addOreDictItem(40, "nuggetBrass");
		nuggetBronze = generalItem.addOreDictItem(41, "nuggetBronze");
		nuggetThaumicBronze = generalItem.addOreDictItem(42, "nuggetThaumicBronze");
		nuggetSteel = generalItem.addOreDictItem(43, "nuggetSteel");
		nuggetVoidbrass = generalItem.addOreDictItem(44, "nuggetVoidbrass");
		nuggetVoidsteel = generalItem.addOreDictItem(45, "nuggetVoidsteel");

		dustBrass = generalItem.addOreDictItem(50, "dustBrass");
		dustBronze = generalItem.addOreDictItem(51, "dustBronze");
		dustThaumicBronze = generalItem.addOreDictItem(52, "dustThaumicBronze");
		dustSteel = generalItem.addOreDictItem(53, "dustSteel");
		dustVoidbrass = generalItem.addOreDictItem(54, "dustVoidbrass");
		dustVoidsteel = generalItem.addOreDictItem(55, "dustVoidsteel");

		rawThaumicBronze = generalItem.addOreDictItem(62, "ingotThaumicBronzeRaw");

		coatedThaumicBronze = generalItem.addOreDictItem(72, "ingotThaumicBronzeCoated");

		ceramicSlag = generalItem.addOreDictItem(80, "itemSlagCeramic");
		thaumicSlag = generalItem.addOreDictItem(81, "itemSlagThaumic");

		thaumicBronzeChain = generalItem.addOreDictItem(90, "thaumicBronzeChain", "itemChainThaumicBronze");

		firedThaumicBronze = generalItem.addOreDictItem(1102, "ingotThaumicBronzeFired");
	}

	public void aluminiumArc() {
		RegistrationWrapper.registerOreDict(new ItemStack(Items.clay_ball), "itemClay");
		RegistrationWrapper.registerOreDict(new ItemStack(Items.glass_bottle), "itemBottle");
		RegistrationWrapper.registerOreDict(dustSalisMundus, "dustSalisMundus");
		RegistrationWrapper.registerOreDict(itemEnchantedFabric, "itemEnchantedFabric");
		RegistrationWrapper.registerOreDict(itemQuicksilverDrop, "itemQuicksilverDrop");
		RegistrationWrapper.registerOreDict(shardBalanced, "shardBalanced");
	}

	public void addLoot() {
		generalItem.addLoot(1102, ingotThaumicBronze, thaumicSlag);
	}

	public void loadRecipes() {
		RegistrationWrapper.addSmelting(coatedThaumicBronze, firedThaumicBronze, 2.0F);
	}

	public void loadThaumicRecipes() {
		recipeThaumicBronzeRaw = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyThaumicBronze, rawThaumicBronze, new AspectList().add(ORDER, 5).add(EARTH, 5).add(FIRE, 5), "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetThaumium", "nuggetThaumium", "nuggetBrass");
		recipeThaumicBronzeCoated = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyThaumicBronze, coatedThaumicBronze, new AspectList().add(EARTH, 5).add(WATER, 5), "ingotThaumicBronzeRaw", "itemQuicksilverDrop", "dustSalisMundus", "itemClay");

		recipeThaumicBronzeChain = ThaumcraftApi.addArcaneCraftingRecipe(keyBronzeChain, ItemHelper.cloneStack(thaumicBronzeChain, 12), new AspectList().add(ORDER, 10).add(FIRE, 5), " X ", "X X", 'X', "ingotThaumicBronze");

/*		recipeBronzeChainHelmet = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainHelmet.stack, new AspectList().add(ORDER, 10).add(EARTH, 5).add(FIRE, 5), "XXX", "X X", 'X', "itemChainThaumicBronze");
		recipeBronzeMail = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeMail.stack, new AspectList().add(ORDER, 25).add(EARTH, 12).add(FIRE, 12), "X X", "XXX", "XXX", 'X', "itemChainThaumicBronze");
		recipeBronzeChainGreaves = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainGreaves.stack, new AspectList().add(ORDER, 20).add(EARTH, 10).add(FIRE, 10), "XXX", "X X", "X X", 'X', "itemChainThaumicBronze");
		recipeBronzeChainBoots = ThaumcraftApi.addArcaneCraftingRecipe(keyArmorBronzeChain, bronzeChainBoots.stack, new AspectList().add(ORDER, 5).add(EARTH, 3).add(FIRE, 3), "X X", "X X", 'X', "itemChainThaumicBronze");
*/
		recipePrimalGoggles = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalGoggles, 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackGoggles, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, stackThaumometer });
		recipePrimalRobes = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalRobes, 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackChestRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, ingotThaumium });
		recipePrimalPants = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalPants, 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackLegsRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, itemNitor });
		recipePrimalBoots = ThaumcraftApi.addInfusionCraftingRecipe(keyRobesPrimal, primalBoots, 5, new AspectList().add(MAGIC, 12).add(CRAFT, 16).add(ORDER, 16), stackBootsRobe, new ItemStack[] { itemEnchantedFabric, itemEnchantedFabric, dustSalisMundus, dustSalisMundus, itemPrimalCharm, planksGreatwood });
	}

	public void loadResearch() {
		researchThaumRev = new FluxGearResearchItem(keyThaumRev, RESEARCH_KEY, new AspectList(), 0, 0, 0, new ItemStack(Items.potato));
		researchThaumicBronze = new FluxGearResearchItem(keyThaumicBronze, RESEARCH_KEY, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ORDER, 1), 0, 3, 1, ingotThaumicBronze);
		researchBronzeChain = new FluxGearResearchItem(keyBronzeChain, RESEARCH_KEY, new AspectList().add(METAL, 4).add(MAGIC, 2).add(CRAFT, 3), -2, 4, 1, thaumicBronzeChain);
		researchPrimalRobes = new FluxGearResearchItem(keyRobesPrimal, RESEARCH_KEY, new AspectList().add(MAGIC, 4).add(CLOTH, 4).add(ARMOR, 3).add(ENERGY, 2).add(SENSES, 2).add(ThaumcraftHelper.newPrimalAspectList(1)), 1, 5, 3, new ItemStack(Items.potato)/*primalRobes.stack*/);
	}

	public void initResearch() {
		researchThaumRev.setRound().setSpecial().setAutoUnlock().registerResearchItem();
		researchPrimalRobes.setParents(keyThaumRev, "THAUMIUM", "ENCHFABRIC", "GOGGLES", "NITOR", "INFUSION", "INFUSIONENCHANTMENT").registerResearchItem();


		if (researchLevel == 0) { //EASY-MODE
			researchThaumicBronze.setParents(keyThaumRev, "THAUMIUM").setSiblings(keyBronzeChain).registerResearchItem();
			researchBronzeChain.setParents(keyThaumicBronze).setSecondary().registerResearchItem();
		} else if (researchLevel == 2) { //HARD-MODE
			researchThaumicBronze.setParents(keyThaumRev, "THAUMIUM").registerResearchItem();
			researchBronzeChain.setParents(keyThaumicBronze).setSecondary().registerResearchItem();
		} else { //NORMAL-MODE
			researchThaumicBronze.setParents(keyThaumRev, "THAUMIUM").registerResearchItem();
			researchBronzeChain.setParents(keyThaumicBronze).setSecondary().registerResearchItem();
		}
	}

	public void setPages() {
		researchThaumRev.setPages(new ResearchPage("0"));
		researchThaumicBronze.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeRaw), new ResearchPage(recipeThaumicBronzeCoated), new ResearchPage(coatedThaumicBronze), new ResearchPage("1"));
		researchBronzeChain.setPages(new ResearchPage("0"), new ResearchPage(recipeThaumicBronzeChain));
		researchPrimalRobes.setPages(new ResearchPage("0")/*, new ResearchPage(recipePrimalGoggles), new ResearchPage(recipePrimalRobes), new ResearchPage(recipePrimalPants), new ResearchPage(recipePrimalBoots)*/);
	}

    public void determineTempus() {
        // Thanks for the API hook, Myst!
        Object protoTempus = MagicBeesAPI.thaumcraftAspectTempus;
        if (protoTempus != null && protoTempus instanceof Aspect) {
            tempus = (Aspect) protoTempus;
        } else {
            tempus = new Aspect("tempus", 0xB68CFF, new Aspect[] { Aspect.VOID, Aspect.ORDER }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/tempus.png"), 1);
	        if (Loader.isModLoaded("Forestry")) {
		        ThaumicRevelations.logger.info("What's the matter? What's with the lack of Magic Bees? You not like bees? DO YOU NOT LIKE THE BEES!?!? HUH!?!? YOU SOME APIPHOBIC LOSER!?!?");
	        }
	        timeyWimey();
        }
    }

	public void timeyWimey() {
		//FRESH COPY-PASTA FROM MAGIC BEES FOR THOSE CRAZY PEOPLE WHO DON'T USE MAGIC BEES!!!!
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.clock), new AspectList(new ItemStack(Items.clock)).add(tempus, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(Items.repeater), new AspectList(new ItemStack(Items.repeater)).add(tempus, 2));
	}

    public static Aspect tempus;
}
