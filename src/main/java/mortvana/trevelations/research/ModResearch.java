package mortvana.trevelations.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import mortvana.trevelations.common.ModContent;
import mortvana.trevelations.crafting.ModRecipes;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ModResearch {

	public static final Aspect EXUBITOR = new Aspect("exubitor", 0x3CD4FC, new Aspect[] {Aspect.ELDRITCH, Aspect.DEATH}, new ResourceLocation("trevelations", "textures/aspects/exubitor.png"), 771);
	public static final Aspect CITRUS = new Aspect("citrus", 0xFF6E00, new Aspect[] {Aspect.PLANT, Aspect.SENSES}, new ResourceLocation("trevelations", "textures/aspects/citrus.png"), 771);
	public static final Aspect MAGNET = new Aspect("magnes", 0x515970, new Aspect[] {Aspect.METAL, Aspect.ENERGY}, new ResourceLocation("trevelations", "textures/aspects/magnes.png"), 771);
	public static final Aspect FLUX = new Aspect("fluctuatio", 0xAD0200, new Aspect[] {MAGNET, Aspect.MECHANISM}, new ResourceLocation("trevelations", "textures/aspects/fluctuatio.png"), 771);
	public static final Aspect REVELATIONS = new Aspect("revelatio", 0x3971AD, new Aspect[] {Aspect.ELDRITCH, Aspect.MIND}, new ResourceLocation("trevelations", "textures/aspects/revelatiofez.png"), 771);

	public static ResearchItem researchTWarden;
	public static ResearchItem researchExubitura;
	public static ResearchItem researchQuartz;
	public static ResearchItem researchCrystal;
	public static ResearchItem researchWardenArmor;
	public static ResearchItem researchWardenWeapon;
	public static ResearchItem researchWaslieHammer;
	public static ResearchItem researchLore1;
	public static ResearchItem researchLore2;
	public static ResearchItem researchLore3;
	public static ResearchItem researchLore4;
	public static ResearchItem researchIllumination;

	public static void init() {

		initCategory();
		initResearch();
		initAspects();

	}

	public static void initCategory() {

		ResearchCategories.registerCategory("trevelations", new ResourceLocation("trevelations", "textures/items/wardenamulet.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));

	}

	public static void initResearch() {

		researchTWarden = new ModResearchItem("TREVELATIONS", "trevelations", new AspectList(), 0, 0, 0, new ItemStack(ModContent.itemWardenAmulet)).setRound().setSpecial().setAutoUnlock().registerResearchItem();
		researchTWarden.setPages(new ResearchPage("0"));
		researchExubitura = new ModResearchItem("EXUBITURA", "trevelations", new AspectList(), 0, -2, 0, new ItemStack(ModContent.blockExubitura)).setParents("TREVELATIONS").setAutoUnlock().registerResearchItem();
		researchExubitura.setPages(new ResearchPage("0"));
		researchQuartz = new ModResearchItem("QUARTZ", "trevelations", new AspectList().add(EXUBITOR, 4).add(Aspect.CRYSTAL, 4), 2, 0, 2, new ItemStack(ModContent.itemResource, 0, 2)).setParents("TREVELATIONS").setRound().registerResearchItem();
		researchQuartz.setPages(new ResearchPage("0"), new ResearchPage(ModRecipes.recipeQuartz));
		researchCrystal = new ModResearchItem("CRYSTAL", "trevelations", new AspectList().add(EXUBITOR, 4).add(Aspect.CRYSTAL, 4), -2, 0, 2, new ItemStack(ModContent.itemResource, 0, 1)).setParents("TREVELATIONS").setSecondary().registerResearchItem();
		researchCrystal.setPages(new ResearchPage("0"), new ResearchPage(ModRecipes.recipeCrystal));
		researchWardenArmor = new ModResearchItem("WARDENARMOR", "trevelations", new AspectList().add(EXUBITOR, 4).add(Aspect.CRYSTAL, 4).add(Aspect.ARMOR, 4), 6, 2, 3, new ItemStack(ModContent.itemWardenChest)).setParents("WASLIEHAMMER").setRound().setSpecial().registerResearchItem();
		researchWardenArmor.setPages(new ResearchPage("0"), new ResearchPage(ModRecipes.recipeWardenHelm), new ResearchPage(ModRecipes.recipeWardenChest), new ResearchPage(ModRecipes.recipeWardenLegs), new ResearchPage(ModRecipes.recipeWardenBoots));
		researchWardenWeapon = new ModResearchItem("WARDENWEAPON", "trevelations", new AspectList().add(EXUBITOR, 4).add(Aspect.CRYSTAL, 4).add(Aspect.WEAPON, 4), 6, 6, 3, new ItemStack(ModContent.itemWardenSword)).setParents("WASLIEHAMMER").setRound().setSpecial().registerResearchItem();
		researchWardenWeapon.setPages(new ResearchPage("0"), new ResearchPage(ModRecipes.recipeWardenSword));
		researchWaslieHammer = new ModResearchItem("WASLIEHAMMER", "trevelations", new AspectList().add(EXUBITOR, 4).add(Aspect.CRYSTAL, 4), 4, 4, 3, new ItemStack(ModContent.itemWaslieHammer)).setParentsHidden("CRYSTAL", "QUARTZ").setParents("LORE4").setRound().setSpecial().registerResearchItem();
		researchWaslieHammer.setPages(new ResearchPage("0"), new ResearchPage(ModRecipes.recipeWaslieHammer));

		researchIllumination = new ModResearchItem("ILLUMINATION", "trevelations", new AspectList().add(Aspect.AIR, 2).add(Aspect.FIRE, 2), 0, 4, 1, new ItemStack(ModContent.itemFocusIllumination)).setRound().setParentsHidden("FOCUSFIRE").registerResearchItem();
		researchIllumination.setPages(new ResearchPage("0"), new ResearchPage(ModRecipes.recipeFocusIllumination));

		researchLore1 = new ModResearchItem("LORE1", "trevelations", new AspectList().add(EXUBITOR, 4).add(Aspect.MIND, 4), 0, 2, 3, new ItemStack(ModContent.itemWardenAmulet)).setParents("TREVELATIONS").setRound().setSpecial().registerResearchItem();
		researchLore1.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"));
		researchLore2 = new ModResearchItem("LORE2", "trevelations", new AspectList().add(EXUBITOR, 4).add(Aspect.MIND, 4), -2, 4, 3, new ItemStack(ModContent.itemWardenAmulet)).setParents("LORE1").setRound().setSpecial().registerResearchItem();
		researchLore2.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"));
		researchLore3 = new ModResearchItem("LORE3", "trevelations", new AspectList().add(EXUBITOR, 4).add(Aspect.MIND, 4), 0, 6, 3, new ItemStack(ModContent.itemWardenAmulet)).setParents("LORE2").setRound().setSpecial().registerResearchItem();
		researchLore3.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4"), new ResearchPage("5"));
		researchLore4 = new ModResearchItem("LORE4", "trevelations", new AspectList().add(EXUBITOR, 4).add(Aspect.MIND, 4), 2, 4, 3, new ItemStack(ModContent.itemWardenAmulet)).setParents("LORE3").setRound().setSpecial().registerResearchItem();
		researchLore4.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"));

	}

	public static void initAspects() {

		ThaumcraftApi.registerObjectTag(new ItemStack(ModContent.itemResource, 0, 0), new AspectList().add(ModResearch.EXUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModContent.itemResource, 0, 1), new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.ELDRITCH, 4).add(ModResearch.EXUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModContent.itemResource, 0, 2), new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.ELDRITCH, 4).add(ModResearch.EXUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModContent.itemWardenHelm), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(ModResearch.EXUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModContent.itemWardenChest), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(ModResearch.EXUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModContent.itemWardenLegs), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(ModResearch.EXUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModContent.itemWardenBoots), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(ModResearch.EXUBITOR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModContent.itemWardenSword), new AspectList().add(Aspect.WEAPON, 4).add(Aspect.ELDRITCH, 4).add(ModResearch.EXUBITOR, 1));

	}

}
