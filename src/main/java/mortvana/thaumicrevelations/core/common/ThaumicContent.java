package mortvana.thaumicrevelations.core.common;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.registry.GameRegistry;

import magicbees.api.MagicBeesAPI;
import mortvana.thaumicrevelations.core.block.BlockThaumicPlant;
import mortvana.thaumicrevelations.core.block.ItemBlockThaumicPlant;
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
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;

import static thaumcraft.api.aspects.Aspect.*;
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
        registerItems();
	    aluminiumArc();
    }

    @Override
    public void postInit() {
	    ResearchCategories.registerCategory(RESEARCH_KEY, new ResourceLocation(RESOURCE_PREFIX, "textures/items/baubles/amuletWarden.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
        registerCrafting();
	    registerThaumicCrafting();
	    registerResearch();
	    determineTempus();
    }

	public void createBlocks() {
		blockThaumicPlant = new BlockThaumicPlant();

		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class,  "blockThaumicPlant");
	}

	public void createItems() {
		generalItem = (FluxGearItem) new FluxGearItem(RESOURCE_PREFIX, thaumicRevelationsTab).setFolder("material").setUnlocalizedName("material");
	}

	public void registerItems() {
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

		nuggetBrass = generalItem.addOreDictItem(40, "nuggetBrass"); //FIX
		nuggetBronze = generalItem.addOreDictItem(41, "nuggetBronze");
		nuggetThaumicBronze = generalItem.addOreDictItem(42, "nuggetThaumicBronze");

		dustBrass = generalItem.addOreDictItem(50, "dustBrass");
		dustBronze = generalItem.addOreDictItem(51, "dustBronze");
		dustThaumicBronze = generalItem.addOreDictItem(52, "dustThaumicBronze");

		rawThaumicBronze = generalItem.addOreDictItem(60, "ingotThaumicBronzeRaw");
	}

	public void aluminiumArc() {
		RegistrationWrapper.registerOreDict(ItemApi.getItem("itemNugget", 5), "itemQuicksilverDrop");
		RegistrationWrapper.registerOreDict(ItemApi.getItem("itemResource", 14), "dustSalisMundus");
	}

	public void registerCrafting() {
		RegistrationWrapper.addSmelting(rawThaumicBronze, ingotThaumicBronze, 2.0F);
	}

	public void registerThaumicCrafting() {
		recipeThaumicBronzeRaw = ThaumcraftApi.addShapelessArcaneCraftingRecipe(keyThaumicBronze, rawThaumicBronze, new AspectList().add(ORDER, 5).add(EARTH, 5).add(FIRE, 5), "nuggetBronze", "nuggetBronze", "nuggetBronze", "nuggetThaumium", "nuggetThaumium", "nuggetThaumium", "nuggetBrass", "itemQuicksilverDrop", "dustSalisMundus");
	}

	public void registerResearch() {
		researchThaumRev = new FluxGearResearchItem(keyThaumRev, RESEARCH_REVELATIONS, new AspectList(), 0, 0, 0, new ItemStack(Items.potato)).setRound().setSpecial().setAutoUnlock().registerResearchItem();
		researchThaumRev.setPages(new ResearchPage("0"));

		researchThaumicBronze = new FluxGearResearchItem(keyThaumicBronze, RESEARCH_WARDEN, new AspectList().add(METAL, 4).add(MAGIC, 3).add(ORDER, 1), 0, -3, 1, ingotThaumicBronze).setParents(keyThaumRev, "THAUMIUM").registerResearchItem();
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
}
