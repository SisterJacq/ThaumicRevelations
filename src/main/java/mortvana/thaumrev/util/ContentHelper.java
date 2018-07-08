package mortvana.thaumrev.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;

import mortvana.melteddashboard.util.helpers.ItemHelper;

import mortvana.thaumrev.common.ThaumRevConfig;

import static mortvana.melteddashboard.util.helpers.ItemHelper.*;
import static mortvana.melteddashboard.util.libraries.StringLibrary.*;
import static mortvana.melteddashboard.util.libraries.ThaumcraftLibrary.*;
import static mortvana.thaumrev.common.ThaumRevConfig.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;
import static mortvana.thaumrev.util.RecipeHelper.*;
import static mortvana.thaumrev.util.RecipeHelper.addArcaneCraftingRecipe;
import static thaumcraft.api.aspects.Aspect.ORDER;

public class ContentHelper {

	public static AspectList monorder = new AspectList().add(Aspect.ORDER, 1);

	public static void addOreRecipes() {
		addSmelting(oreChalcocite, ingotCopper, 0.85F);
		addSmelting(oreSphalerite, ingotZinc, 0.95F);
		addSmelting(oreCassiterite, ingotTin, 0.975F);
		addSmelting(oreMillerite, ingotNickel, 1.2F);
		addSmelting(oreNativeSilver, ingotSilver, 1.5F);
		addSmelting(oreGalena, ingotLead, 1.0F);
		addSmelting(oreXenotime, ingotLanthanides, 1.0F); //Rare Earths mock your primitive furnace-based attempts at separating them, but will smelt.
		//Tungsten laughs at your mundane smelting
		//Refractory Alloys mock your simple furnace
		addSmelting(oreBismuthinite, ingotBismuth, 1.15F);
		addSmelting(oreTennantite, ingotArsenicalBronze, 1.35F);
		addSmelting(oreTetrahedrite, ingotAntimonialBronze, 1.35F);
		addSmelting(orePyrope, gemPyrope, 1.0F);
		addSmelting(oreDioptase, gemDioptase, 1.0F);
		addSmelting(oreFluonicSapphire, gemFluonicSapphire, 1.0F);

		addSmelting(orePoorChalcocite, cloneStack(nuggetCopper, 2), 0.085F);
		addSmelting(orePoorSphalerite, cloneStack(nuggetZinc, 2), 0.095F);
		addSmelting(orePoorCassiterite, cloneStack(nuggetTin, 2), 0.0975F);
		addSmelting(orePoorMillerite, cloneStack(nuggetNickel, 2), 0.12F);
		addSmelting(orePoorNativeSilver, cloneStack(nuggetSilver, 2), 0.15F);
		addSmelting(orePoorGalena, cloneStack(nuggetLead, 2), 0.1F);
		addSmelting(orePoorXenotime, cloneStack(nuggetLanthanides, 2), 0.1F); //Rare Earths mock your primitive furnace-based attempts at separating them, but will smelt.
		//Tungsten laughs at your mundane smelting
		//Refractory Alloys mock your simple furnace
		addSmelting(orePoorBismuthinite, cloneStack(nuggetBismuth, 2), 0.115F);
		addSmelting(orePoorTennantite, cloneStack(nuggetArsenicalBronze, 2), 0.135F);
		addSmelting(orePoorTetrahedrite, cloneStack(nuggetAntimonialBronze, 2), 0.135F);

		addSmelting(oreGravelChalcocite, ingotCopper, 0.85F);
		addSmelting(oreGravelSphalerite, ingotZinc, 0.95F);
		addSmelting(oreGravelCassiterite, ingotTin, 0.975F);
		addSmelting(oreGravelMillerite, ingotNickel, 1.2F);
		addSmelting(oreGravelNativeSilver, ingotSilver, 1.5F);
		addSmelting(oreGravelGalena, ingotLead, 1.0F);
		addSmelting(oreGravelXenotime, ingotLanthanides, 1.0F); //Rare Earths mock your primitive furnace-based attempts at separating them, but will smelt.
		//Tungsten laughs at your mundane smelting
		//Refractory Alloys mock your simple furnace
		addSmelting(oreGravelBismuthinite, ingotBismuth, 1.15F);
		addSmelting(oreGravelTennantite, ingotArsenicalBronze, 1.35F);
		addSmelting(oreGravelTetrahedrite, ingotAntimonialBronze, 1.35F);

		if (Loader.isModLoaded("ThermalExpansion")) {
			addPulverizerOreRecipe(oreSphalerite, dustZinc, dustLead);
			addPulverizerOreRecipe(oreXenotime, dustLanthanides, dustArsenic);
			addPulverizerOreRecipe(oreWolframite, dustTungsten, dustIron);
			addPulverizerRecipe(4800, oreIridosmium, cloneStack(dustIridosmium, 2), dustIron, 15);
			addPulverizerOreRecipe(oreBismuthinite, dustBismuth, dustLead);
			addPulverizerOreRecipe(oreTennantite, dustArsenicalBronze, dustSilver);
			addPulverizerRecipe(4000, oreTetrahedrite, cloneStack(dustAntimonialBronze, 2), itemQuicksilverDrop);
			addPulverizerRecipe(4000, oreDioptase, cloneStack(gemDioptase, 2));
			addPulverizerRecipe(4000, orePyrope, cloneStack(gemPyrope, 2));
			addPulverizerRecipe(4000, oreFluonicSapphire, cloneStack(gemFluonicSapphire, 2));

			addInductionOreRecipes("Zinc", ingotLead);
			addInductionOreRecipes("Xenotime", "XenotimeLanthanides", dustArsenic);
			addInductionOreRecipes("Bismuth", ingotLead);
			addInductionOreRecipes("Tennantite", "ArsenicalBronze", ingotSilver);
			addInductionOreRecipes("Tetrahedrite", "AntimonialBronze", quicksilver);
		}
	}

	public static void addElementalMetalRecipes() {
		addStorageRecipe(blockCopper, "ingotCopper");
		addStorageRecipe(ingotCopper, "nuggetCopper");
		addReverseStorageRecipe(ingotCopper, "blockCopper");
		addReverseStorageRecipe(nuggetCopper, "ingotCopper");
		addStorageRecipe(dustCopper, "dustTinyCopper");
		addReverseStorageRecipe(tinyCopper, "dustCopper");
		addSmelting(dustCopper, ingotCopper);
		addSmelting(plateCopper, ingotCopper);
		addArcaneCraftingRecipe(keyAlumentum, plateCopper, monorder, "A", "I", 'I', "ingotCopper", 'A', "itemAlumentum");
		addGrindingRecipes(ingotCopper, dustCopper);
		addGrindingRecipes(plateCopper, dustCopper);

		addStorageRecipe(blockZinc, "ingotZinc");
		addStorageRecipe(ingotZinc, "nuggetZinc");
		addReverseStorageRecipe(ingotZinc, "blockZinc");
		addReverseStorageRecipe(nuggetZinc, "ingotZinc");
		addStorageRecipe(dustZinc, "dustTinyZinc");
		addReverseStorageRecipe(tinyZinc, "dustZinc");
		addSmelting(dustZinc, ingotZinc);
		addSmelting(plateZinc, ingotZinc);
		addArcaneCraftingRecipe(keyAlumentum, plateZinc, monorder, "A", "I", 'I', "ingotZinc", 'A', "itemAlumentum");
		addGrindingRecipes(ingotZinc, dustZinc);
		addGrindingRecipes(plateZinc, dustZinc);

		addStorageRecipe(blockTin, "ingotTin");
		addStorageRecipe(ingotTin, "nuggetTin");
		addReverseStorageRecipe(ingotTin, "blockTin");
		addReverseStorageRecipe(nuggetTin, "ingotTin");
		addStorageRecipe(dustTin, "dustTinyTin");
		addReverseStorageRecipe(tinyTin, "dustTin");
		addSmelting(dustTin, ingotTin);
		addSmelting(plateTin, ingotTin);
		addArcaneCraftingRecipe(keyAlumentum, plateTin, monorder, "A", "I", 'I', "ingotTin", 'A', "itemAlumentum");
		addGrindingRecipes(ingotTin, dustTin);
		addGrindingRecipes(plateTin, dustTin);

		addStorageRecipe(blockNickel, "ingotNickel");
		addStorageRecipe(ingotNickel, "nuggetNickel");
		addReverseStorageRecipe(ingotNickel, "blockNickel");
		addReverseStorageRecipe(nuggetNickel, "ingotNickel");
		addStorageRecipe(dustNickel, "dustTinyNickel");
		addReverseStorageRecipe(tinyNickel, "dustNickel");
		addSmelting(dustNickel, ingotNickel);
		addSmelting(plateNickel, ingotNickel);
		addArcaneCraftingRecipe(keyAlumentum, plateNickel, monorder, "A", "I", 'I', "ingotNickel", 'A', "itemAlumentum");
		addGrindingRecipes(ingotNickel, dustNickel);
		addGrindingRecipes(plateNickel, dustNickel);

		addStorageRecipe(blockSilver, "ingotSilver");
		addStorageRecipe(ingotSilver, "nuggetSilver");
		addReverseStorageRecipe(ingotSilver, "blockSilver");
		addReverseStorageRecipe(nuggetSilver, "ingotSilver");
		addStorageRecipe(dustSilver, "dustTinySilver");
		addReverseStorageRecipe(tinySilver, "dustSilver");
		addSmelting(dustSilver, ingotSilver);
		addSmelting(plateSilver, ingotSilver);
		addArcaneCraftingRecipe(keyAlumentum, plateSilver, monorder, "A", "I", 'I', "ingotSilver", 'A', "itemAlumentum");
		addGrindingRecipes(ingotSilver, dustSilver);
		addGrindingRecipes(plateSilver, dustSilver);

		addStorageRecipe(blockLead, "ingotLead");
		addStorageRecipe(ingotLead, "nuggetLead");
		addReverseStorageRecipe(ingotLead, "blockLead");
		addReverseStorageRecipe(nuggetLead, "ingotLead");
		addStorageRecipe(dustLead, "dustTinyLead");
		addReverseStorageRecipe(tinyLead, "dustLead");
		addSmelting(dustLead, ingotLead);
		addSmelting(plateLead, ingotLead);
		addArcaneCraftingRecipe(keyAlumentum, plateLead, monorder, "A", "I", 'I', "ingotLead", 'A', "itemAlumentum");
		addGrindingRecipes(ingotLead, dustLead);
		addGrindingRecipes(plateLead, dustLead);

		addStorageRecipe(blockLutetium, "ingotLutetium");
		addStorageRecipe(ingotLutetium, "nuggetLutetium");
		addReverseStorageRecipe(ingotLutetium, "blockLutetium");
		addReverseStorageRecipe(nuggetLutetium, "ingotLutetium");
		addStorageRecipe(dustLutetium, "dustTinyLutetium");
		addReverseStorageRecipe(tinyLutetium, "dustLutetium");
		addSmelting(dustLutetium, ingotLutetium);
		addSmelting(plateLutetium, ingotLutetium);
		addArcaneCraftingRecipe(keyAlumentum, plateLutetium, monorder, "A", "I", 'I', "ingotLutetium", 'A', "itemAlumentum");
		addGrindingRecipes(ingotLutetium, dustLutetium);
		addGrindingRecipes(plateLutetium, dustLutetium);

		addStorageRecipe(blockTungsten, "ingotTungsten");
		addStorageRecipe(ingotTungsten, "nuggetTungsten");
		addReverseStorageRecipe(ingotTungsten, "blockTungsten");
		addReverseStorageRecipe(nuggetTungsten, "ingotTungsten");
		addStorageRecipe(dustTungsten, "dustTinyTungsten");
		addReverseStorageRecipe(tinyTungsten, "dustTungsten");
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateTungsten, monorder, "A", "I", "A", 'I', "ingotTungsten", 'A', "itemAlumentum");
		addGrindingRecipes(ingotTungsten, dustTungsten);
		addGrindingRecipes(plateTungsten, dustTungsten);

		addStorageRecipe(blockIridium, "ingotIridium");
		addStorageRecipe(ingotIridium, "nuggetIridium");
		addReverseStorageRecipe(ingotIridium, "blockIridium");
		addReverseStorageRecipe(nuggetIridium, "ingotIridium");
		addStorageRecipe(dustIridium, "dustTinyIridium");
		addReverseStorageRecipe(tinyIridium, "dustIridium");
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateIridium, monorder, "A", "I", "A", 'I', "ingotIridium", 'A', "itemAlumentum");
		addGrindingRecipes(ingotIridium, dustIridium);
		addGrindingRecipes(plateIridium, dustIridium);

		addStorageRecipe(blockBismuth, "ingotBismuth");
		addStorageRecipe(ingotBismuth, "nuggetBismuth");
		addReverseStorageRecipe(ingotBismuth, "blockBismuth");
		addReverseStorageRecipe(nuggetBismuth, "ingotBismuth");
		addStorageRecipe(dustBismuth, "dustTinyBismuth");
		addReverseStorageRecipe(tinyBismuth, "dustBismuth");
		addSmelting(dustBismuth, ingotBismuth);
		addSmelting(plateBismuth, ingotBismuth);
		addArcaneCraftingRecipe(keyAlumentum, plateBismuth, monorder, "A", "I", 'I', "ingotBismuth", 'A', "itemAlumentum");
		addGrindingRecipes(ingotBismuth, dustBismuth);
		addGrindingRecipes(plateBismuth, dustBismuth);

		addStorageRecipe(blockArsenic, "ingotArsenic");
		addStorageRecipe(ingotArsenic, "nuggetArsenic");
		addReverseStorageRecipe(ingotArsenic, "blockArsenic");
		addReverseStorageRecipe(nuggetArsenic, "ingotArsenic");
		addStorageRecipe(dustArsenic, "dustTinyArsenic");
		addReverseStorageRecipe(tinyArsenic, "dustArsenic");
		addSmelting(dustArsenic, ingotArsenic);
		addSmelting(plateArsenic, ingotArsenic);
		addArcaneCraftingRecipe(keyAlumentum, plateArsenic, monorder, "A", "I", 'I', "ingotArsenic", 'A', "itemAlumentum");
		addGrindingRecipes(ingotArsenic, dustArsenic);
		addGrindingRecipes(plateArsenic, dustArsenic);

		addStorageRecipe(blockAntimony, "ingotAntimony");
		addStorageRecipe(ingotAntimony, "nuggetAntimony");
		addReverseStorageRecipe(ingotAntimony, "blockAntimony");
		addReverseStorageRecipe(nuggetAntimony, "ingotAntimony");
		addStorageRecipe(dustAntimony, "dustTinyAntimony");
		addReverseStorageRecipe(tinyAntimony, "dustAntimony");
		addSmelting(dustAntimony, ingotAntimony);
		addSmelting(plateAntimony, ingotAntimony);
		addArcaneCraftingRecipe(keyAlumentum, plateAntimony, monorder, "A", "I", 'I', "ingotAntimony", 'A', "itemAlumentum");
		addGrindingRecipes(ingotAntimony, dustAntimony);
		addGrindingRecipes(plateAntimony, dustAntimony);

		addStorageRecipe(blockNeodymium, "ingotNeodymium");
		addStorageRecipe(ingotNeodymium, "nuggetNeodymium");
		addReverseStorageRecipe(ingotNeodymium, "blockNeodymium");
		addReverseStorageRecipe(nuggetNeodymium, "ingotNeodymium");
		addStorageRecipe(dustNeodymium, "dustTinyNeodymium");
		addReverseStorageRecipe(tinyNeodymium, "dustNeodymium");
		addSmelting(dustNeodymium, ingotNeodymium);
		addSmelting(plateNeodymium, ingotNeodymium);
		addArcaneCraftingRecipe(keyAlumentum, plateNeodymium, monorder, "A", "I", 'I', "ingotNeodymium", 'A', "itemAlumentum");
		addGrindingRecipes(ingotNeodymium, dustNeodymium);
		addGrindingRecipes(plateNeodymium, dustNeodymium);

		addStorageRecipe(blockOsmium, "ingotOsmium");
		addStorageRecipe(ingotOsmium, "nuggetOsmium");
		addReverseStorageRecipe(ingotOsmium, "blockOsmium");
		addReverseStorageRecipe(nuggetOsmium, "ingotOsmium");
		addStorageRecipe(dustOsmium, "dustTinyOsmium");
		addReverseStorageRecipe(tinyOsmium, "dustOsmium");
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateOsmium, monorder, " A ", " I ", "A A", 'I', "ingotOsmium", 'A', "itemAlumentum");
		addGrindingRecipes(ingotOsmium, dustOsmium);
		addGrindingRecipes(plateOsmium, dustOsmium);

		addStorageRecipe(blockPalladium, "ingotPalladium");
		addStorageRecipe(ingotPalladium, "nuggetPalladium");
		addReverseStorageRecipe(ingotPalladium, "blockPalladium");
		addReverseStorageRecipe(nuggetPalladium, "ingotPalladium");
		addStorageRecipe(dustPalladium, "dustTinyPalladium");
		addReverseStorageRecipe(tinyPalladium, "dustPalladium");
		addSmelting(dustPalladium, ingotPalladium);
		addSmelting(platePalladium, ingotPalladium);
		addArcaneCraftingRecipe(keyAlumentum, platePalladium, monorder, "A", "I", 'I', "ingotPalladium", 'A', "itemAlumentum");
		addGrindingRecipes(ingotPalladium, dustPalladium);
		addGrindingRecipes(platePalladium, dustPalladium);

		addStorageRecipe(blockAluminium, "ingotAluminium");
		addStorageRecipe(ingotAluminium, "nuggetAluminium");
		addReverseStorageRecipe(ingotAluminium, "blockAluminium");
		addReverseStorageRecipe(nuggetAluminium, "ingotAluminium");
		addStorageRecipe(dustAluminium, "dustTinyAluminium");
		addReverseStorageRecipe(tinyAluminium, "dustAluminium");
		addSmelting(dustAluminium, ingotAluminium);
		addSmelting(plateAluminium, ingotAluminium);
		addArcaneCraftingRecipe(keyAlumentum, plateAluminium, monorder, "A", "I", 'I', "ingotAluminium", 'A', "itemAlumentum");
		addGrindingRecipes(ingotAluminium, dustAluminium);
		addGrindingRecipes(plateAluminium, dustAluminium);
	}

	public static void addSimpleAlloyMetalRecipes() {
		addStorageRecipe(blockBrass, "ingotBrass");
		addStorageRecipe(ingotBrass, "nuggetBrass");
		addReverseStorageRecipe(ingotBrass, "blockBrass");
		addReverseStorageRecipe(nuggetBrass, "ingotBrass");
		addStorageRecipe(dustBrass, "dustTinyBrass");
		addReverseStorageRecipe(tinyBrass, "dustBrass");
		addSmelting(dustBrass, ingotBrass);
		addSmelting(plateBrass, ingotBrass);
		addArcaneCraftingRecipe(keyAlumentum, plateBrass, monorder, "A", "I", 'I', "ingotBrass", 'A', "itemAlumentum");
		addGrindingRecipes(ingotBrass, dustBrass);
		addGrindingRecipes(plateBrass, dustBrass);

		addStorageRecipe(blockBronze, "ingotBronze");
		addStorageRecipe(ingotBronze, "nuggetBronze");
		addReverseStorageRecipe(ingotBronze, "blockBronze");
		addReverseStorageRecipe(nuggetBronze, "ingotBronze");
		addStorageRecipe(dustBronze, "dustTinyBronze");
		addReverseStorageRecipe(tinyBronze, "dustBronze");
		addSmelting(dustBronze, ingotBronze);
		addSmelting(plateBronze, ingotBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateBronze, monorder, "A", "I", 'I', "ingotBronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotBronze, dustBronze);
		addGrindingRecipes(plateBronze, dustBronze);

		addStorageRecipe(blockArsenicalBronze, "ingotArsenicalBronze");
		addStorageRecipe(ingotArsenicalBronze, "nuggetArsenicalBronze");
		addReverseStorageRecipe(ingotArsenicalBronze, "blockArsenicalBronze");
		addReverseStorageRecipe(nuggetArsenicalBronze, "ingotArsenicalBronze");
		addStorageRecipe(dustArsenicalBronze, "dustTinyArsenicalBronze");
		addReverseStorageRecipe(tinyArsenicalBronze, "dustArsenicalBronze");
		addSmelting(dustArsenicalBronze, ingotArsenicalBronze);
		addSmelting(plateArsenicalBronze, ingotArsenicalBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateArsenicalBronze, monorder, "A", "I", 'I', "ingotArsenicalBronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotArsenicalBronze, dustArsenicalBronze);
		addGrindingRecipes(plateArsenicalBronze, dustArsenicalBronze);

		addStorageRecipe(blockAntimonialBronze, "ingotAntimonialBronze");
		addStorageRecipe(ingotAntimonialBronze, "nuggetAntimonialBronze");
		addReverseStorageRecipe(ingotAntimonialBronze, "blockAntimonialBronze");
		addReverseStorageRecipe(nuggetAntimonialBronze, "ingotAntimonialBronze");
		addStorageRecipe(dustAntimonialBronze, "dustTinyAntimonialBronze");
		addReverseStorageRecipe(tinyAntimonialBronze, "dustAntimonialBronze");
		addSmelting(dustAntimonialBronze, ingotAntimonialBronze);
		addSmelting(plateAntimonialBronze, ingotAntimonialBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateAntimonialBronze, monorder, "A", "I", 'I', "ingotAntimonialBronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotAntimonialBronze, dustAntimonialBronze);
		addGrindingRecipes(plateAntimonialBronze, dustAntimonialBronze);

		addStorageRecipe(blockBismuthBronze, "ingotBismuthBronze");
		addStorageRecipe(ingotBismuthBronze, "nuggetBismuthBronze");
		addReverseStorageRecipe(ingotBismuthBronze, "blockBismuthBronze");
		addReverseStorageRecipe(nuggetBismuthBronze, "ingotBismuthBronze");
		addStorageRecipe(dustBismuthBronze, "dustTinyBismuthBronze");
		addReverseStorageRecipe(tinyBismuthBronze, "dustBismuthBronze");
		addSmelting(dustBismuthBronze, ingotBismuthBronze);
		addSmelting(plateBismuthBronze, ingotBismuthBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateBismuthBronze, monorder, "A", "I", 'I', "ingotBismuthBronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotBismuthBronze, dustBismuthBronze);
		addGrindingRecipes(plateBismuthBronze, dustBismuthBronze);

		addStorageRecipe(blockMithril, "ingotArsenoAntimonialBronze");
		addStorageRecipe(ingotMithril, "nuggetArsenoAntimonialBronze");
		addReverseStorageRecipe(ingotMithril, "blockArsenoAntimonialBronze");
		addReverseStorageRecipe(nuggetMithril, "ingotArsenoAntimonialBronze");
		addStorageRecipe(dustMithril, "dustTinyArsenoAntimonialBronze");
		addReverseStorageRecipe(tinyMithril, "dustArsenoAntimonialBronze");
		addSmelting(dustMithril, ingotMithril);
		addSmelting(plateMithril, ingotMithril);
		addArcaneCraftingRecipe(keyAlumentum, plateMithril, monorder, "A", "I", 'I', "ingotArsenoAntimonialBronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotMithril, dustMithril);
		addGrindingRecipes(plateMithril, dustMithril);
		
		addStorageRecipe(blockAlumiuiumBronze, "ingotAluminiumBronze");
		addStorageRecipe(ingotAluminiumBronze, "nuggetAluminiumBronze");
		addReverseStorageRecipe(ingotAluminiumBronze, "blockAluminiumBronze");
		addReverseStorageRecipe(nuggetAluminiumBronze, "ingotAluminiumBronze");
		addStorageRecipe(dustAluminiumBronze, "dustTinyAluminiumBronze");
		addReverseStorageRecipe(tinyAluminiumBronze, "dustAluminiumBronze");
		addSmelting(dustAluminiumBronze, ingotAluminiumBronze);
		addSmelting(plateAluminiumBronze, ingotAluminiumBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateAluminiumBronze, monorder, "A", "I", 'I', "ingotAluminiumBronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotAluminiumBronze, dustAluminiumBronze);
		addGrindingRecipes(plateAluminiumBronze, dustAluminiumBronze);

		addStorageRecipe(blockCupronickel, "ingotCupronickel");
		addStorageRecipe(ingotCupronickel, "nuggetCupronickel");
		addReverseStorageRecipe(ingotCupronickel, "blockCupronickel");
		addReverseStorageRecipe(nuggetCupronickel, "ingotCupronickel");
		addStorageRecipe(dustCupronickel, "dustTinyCupronickel");
		addReverseStorageRecipe(tinyCupronickel, "dustCupronickel");
		addSmelting(dustCupronickel, ingotCupronickel);
		addSmelting(plateCupronickel, ingotCupronickel);
		addArcaneCraftingRecipe(keyAlumentum, plateCupronickel, monorder, "A", "I", 'I', "ingotCupronickel", 'A', "itemAlumentum");
		addGrindingRecipes(ingotCupronickel, dustCupronickel);
		addGrindingRecipes(plateCupronickel, dustCupronickel);

		addStorageRecipe(blockRiftishBronze, "ingotRiftishBronze");
		addStorageRecipe(ingotRiftishBronze, "nuggetRiftishBronze");
		addReverseStorageRecipe(ingotRiftishBronze, "blockRiftishBronze");
		addReverseStorageRecipe(nuggetRiftishBronze, "ingotRiftishBronze");
		addStorageRecipe(dustRiftishBronze, "dustTinyRiftishBronze");
		addReverseStorageRecipe(tinyRiftishBronze, "dustRiftishBronze");
		addSmelting(dustRiftishBronze, ingotRiftishBronze);
		addSmelting(plateRiftishBronze, ingotRiftishBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateRiftishBronze, monorder, "A", "I", 'I', "ingotRiftishBronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotRiftishBronze, dustRiftishBronze);
		addGrindingRecipes(plateRiftishBronze, dustRiftishBronze);

		addStorageRecipe(blockConstantan, "ingotConstantan");
		addStorageRecipe(ingotConstantan, "nuggetConstantan");
		addReverseStorageRecipe(ingotConstantan, "blockConstantan");
		addReverseStorageRecipe(nuggetConstantan, "ingotConstantan");
		addStorageRecipe(dustConstantan, "dustTinyConstantan");
		addReverseStorageRecipe(tinyConstantan, "dustConstantan");
		addSmelting(dustConstantan, ingotConstantan);
		addSmelting(plateConstantan, ingotConstantan);
		addArcaneCraftingRecipe(keyAlumentum, plateConstantan, monorder, "A", "I", 'I', "ingotConstantan", 'A', "itemAlumentum");
		addGrindingRecipes(ingotConstantan, dustConstantan);
		addGrindingRecipes(plateConstantan, dustConstantan);

		addStorageRecipe(blockInvar, "ingotInvar");
		addStorageRecipe(ingotInvar, "nuggetInvar");
		addReverseStorageRecipe(ingotInvar, "blockInvar");
		addReverseStorageRecipe(nuggetInvar, "ingotInvar");
		addStorageRecipe(dustInvar, "dustTinyInvar");
		addReverseStorageRecipe(tinyInvar, "dustInvar");
		addSmelting(dustInvar, ingotInvar);
		addSmelting(plateInvar, ingotInvar);
		addArcaneCraftingRecipe(keyAlumentum, plateInvar, monorder, "A", "I", 'I', "ingotInvar", 'A', "itemAlumentum");
		addGrindingRecipes(ingotInvar, dustInvar);
		addGrindingRecipes(plateInvar, dustInvar);

		addStorageRecipe(blockElectrum, "ingotElectrum");
		addStorageRecipe(ingotElectrum, "nuggetElectrum");
		addReverseStorageRecipe(ingotElectrum, "blockElectrum");
		addReverseStorageRecipe(nuggetElectrum, "ingotElectrum");
		addStorageRecipe(dustElectrum, "dustTinyElectrum");
		addReverseStorageRecipe(tinyElectrum, "dustElectrum");
		addSmelting(dustElectrum, ingotElectrum);
		addSmelting(plateElectrum, ingotElectrum);
		addArcaneCraftingRecipe(keyAlumentum, plateElectrum, monorder, "A", "I", 'I', "ingotElectrum", 'A', "itemAlumentum");
		addGrindingRecipes(ingotElectrum, dustElectrum);
		addGrindingRecipes(plateElectrum, dustElectrum);

		addStorageRecipe(blockWardenicMetal, "ingotWardenicMetal");
		addStorageRecipe(ingotWardenicMetal, "nuggetWardenicMetal");
		addReverseStorageRecipe(ingotWardenicMetal, "blockWardenicMetal");
		addReverseStorageRecipe(nuggetWardenicMetal, "ingotWardenicMetal");
		addStorageRecipe(dustWardenicMetal, "dustTinyWardenicMetal");
		addReverseStorageRecipe(tinyWardenicMetal, "dustWardenicMetal");
		addSmelting(dustWardenicMetal, ingotWardenicMetal);
		addSmelting(plateWardenicMetal, ingotWardenicMetal);
		addArcaneCraftingRecipe(keyAlumentum, plateWardenicMetal, monorder, "A", "I", 'I', "ingotWardenicMetal", 'A', "itemAlumentum");
		addGrindingRecipes(ingotWardenicMetal, dustWardenicMetal);
		addGrindingRecipes(plateWardenicMetal, dustWardenicMetal);

		addStorageRecipe(blockDullRedsolder, "ingotDullRedsolder");
		addStorageRecipe(ingotDullRedsolder, "nuggetDullRedsolder");
		addReverseStorageRecipe(ingotDullRedsolder, "blockDullRedsolder");
		addReverseStorageRecipe(nuggetDullRedsolder, "ingotDullRedsolder");
		addStorageRecipe(dustDullRedsolder, "dustTinyDullRedsolder");
		addReverseStorageRecipe(tinyDullRedsolder, "dustDullRedsolder");
		addSmelting(dustDullRedsolder, ingotDullRedsolder);
		addSmelting(plateDullRedsolder, ingotDullRedsolder);
		addArcaneCraftingRecipe(keyAlumentum, plateDullRedsolder, monorder, "A", "I", 'I', "ingotDullRedsolder", 'A', "itemAlumentum");
		addGrindingRecipes(ingotDullRedsolder, dustDullRedsolder);
		addGrindingRecipes(plateDullRedsolder, dustDullRedsolder);

		addStorageRecipe(blockRedsolder, "ingotRedsolder");
		addStorageRecipe(ingotRedsolder, "nuggetRedsolder");
		addReverseStorageRecipe(ingotRedsolder, "blockRedsolder");
		addReverseStorageRecipe(nuggetRedsolder, "ingotRedsolder");
		addStorageRecipe(dustRedsolder, "dustTinyRedsolder");
		addReverseStorageRecipe(tinyRedsolder, "dustRedsolder");
		addSmelting(dustRedsolder, ingotRedsolder);
		addSmelting(plateRedsolder, ingotRedsolder);
		addArcaneCraftingRecipe(keyAlumentum, plateRedsolder, monorder, "A", "I", 'I', "ingotRedsolder", 'A', "itemAlumentum");
		addGrindingRecipes(ingotRedsolder, dustRedsolder);
		addGrindingRecipes(plateRedsolder, dustRedsolder);
	}

	public static void addSpecialAlloyMetalRecipes() {
		addStorageRecipe(blockThaumicElectrum, "ingotThaumicElectrum");
		addStorageRecipe(ingotThaumicElectrum, "nuggetThaumicElectrum");
		addReverseStorageRecipe(ingotThaumicElectrum, "blockThaumicElectrum");
		addReverseStorageRecipe(nuggetThaumicElectrum, "ingotThaumicElectrum");
		addStorageRecipe(dustThaumicElectrum, "dustTinyThaumicElectrum");
		addReverseStorageRecipe(tinyThaumicElectrum, "dustThaumicElectrum");
		addSmelting(dustThaumicElectrum, ingotThaumicElectrum);
		addSmelting(plateThaumicElectrum, ingotThaumicElectrum);
		addArcaneCraftingRecipe(keyAlumentum, plateThaumicElectrum, monorder, "A", "I", 'I', "ingotThaumicElectrum", 'A', "itemAlumentum");
		addGrindingRecipes(ingotThaumicElectrum, dustThaumicElectrum);
		addGrindingRecipes(plateThaumicElectrum, dustThaumicElectrum);

		addStorageRecipe(blockThaumicRiftishBronze, "ingotThaumicRiftishBronze");
		addStorageRecipe(ingotThaumicRiftishBronze, "nuggetThaumicRiftishBronze");
		addReverseStorageRecipe(ingotThaumicRiftishBronze, "blockThaumicRiftishBronze");
		addReverseStorageRecipe(nuggetThaumicRiftishBronze, "ingotThaumicRiftishBronze");
		addStorageRecipe(dustThaumicRiftishBronze, "dustTinyThaumicRiftishBronze");
		addReverseStorageRecipe(tinyThaumicRiftishBronze, "dustThaumicRiftishBronze");
		addSmelting(dustThaumicRiftishBronze, ingotThaumicRiftishBronze);
		addSmelting(plateThaumicRiftishBronze, ingotThaumicRiftishBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateThaumicRiftishBronze, monorder, "A", "I", "A", 'I', "ingotThaumicRiftishBronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotThaumicRiftishBronze, dustThaumicRiftishBronze);
		addGrindingRecipes(plateThaumicRiftishBronze, dustThaumicRiftishBronze);

		addStorageRecipe(blockSteel, "ingotSteel");
		addStorageRecipe(ingotSteel, "nuggetSteel");
		addReverseStorageRecipe(ingotSteel, "blockSteel");
		addReverseStorageRecipe(nuggetSteel, "ingotSteel");
		addStorageRecipe(dustSteel, "dustTinySteel");
		addReverseStorageRecipe(tinySteel, "dustSteel");
		addSmelting(dustSteel, ingotSteel);
		addSmelting(plateSteel, ingotSteel);
		addArcaneCraftingRecipe(keyAlumentum, plateSteel, monorder, "A", "I", "A", 'I', "ingotSteel", 'A', "itemAlumentum");
		addGrindingRecipes(ingotSteel, dustSteel);
		addGrindingRecipes(plateSteel, dustSteel);

		addStorageRecipe(blockThaumicSteel, "ingotThaumicSteel");
		addStorageRecipe(ingotThaumicSteel, "nuggetThaumicSteel");
		addReverseStorageRecipe(ingotThaumicSteel, "blockThaumicSteel");
		addReverseStorageRecipe(nuggetThaumicSteel, "ingotThaumicSteel");
		addStorageRecipe(dustThaumicSteel, "dustTinyThaumicSteel");
		addReverseStorageRecipe(tinyThaumicSteel, "dustThaumicSteel");
		addSmelting(dustThaumicSteel, ingotThaumicSteel);
		addSmelting(plateThaumicSteel, ingotThaumicSteel);
		addArcaneCraftingRecipe(keyAlumentum, plateThaumicSteel, monorder, "A", "I", "A", 'I', "ingotThaumicSteel", 'A', "itemAlumentum");
		addGrindingRecipes(ingotThaumicSteel, dustThaumicSteel);
		addGrindingRecipes(plateThaumicSteel, dustThaumicSteel);

		addStorageRecipe(blockVoidbrass, "ingotVoidbrass");
		addStorageRecipe(ingotVoidbrass, "nuggetVoidbrass");
		addReverseStorageRecipe(ingotVoidbrass, "blockVoidbrass");
		addReverseStorageRecipe(nuggetVoidbrass, "ingotVoidbrass");
		addStorageRecipe(dustVoidbrass, "dustTinyVoidbrass");
		addReverseStorageRecipe(tinyVoidbrass, "dustVoidbrass");
		addSmelting(dustVoidbrass, ingotVoidbrass);
		addSmelting(plateVoidbrass, ingotVoidbrass);
		addArcaneCraftingRecipe(keyAlumentum, plateVoidbrass, monorder, "A", "I", "A", 'I', "ingotVoidbrass", 'A', "itemAlumentum");
		addGrindingRecipes(ingotVoidbrass, dustVoidbrass);
		addGrindingRecipes(plateVoidbrass, dustVoidbrass);

		addStorageRecipe(blockVoidsteel, "ingotVoidsteel");
		addStorageRecipe(ingotVoidsteel, "nuggetVoidsteel");
		addReverseStorageRecipe(ingotVoidsteel, "blockVoidsteel");
		addReverseStorageRecipe(nuggetVoidsteel, "ingotVoidsteel");
		addStorageRecipe(dustVoidsteel, "dustTinyVoidsteel");
		addReverseStorageRecipe(tinyVoidsteel, "dustVoidsteel");
		addSmelting(dustVoidsteel, ingotVoidsteel);
		addSmelting(plateVoidsteel, ingotVoidsteel);
		addArcaneCraftingRecipe(keyAlumentum, plateVoidsteel, monorder, " A ", " I ", "A A", 'I', "ingotVoidsteel", 'A', "itemAlumentum");
		addGrindingRecipes(ingotVoidsteel, dustVoidsteel);
		addGrindingRecipes(plateVoidsteel, dustVoidsteel);

		addStorageRecipe(blockVoidtungsten, "ingotVoidtungsten");
		addStorageRecipe(ingotVoidtungsten, "nuggetVoidtungsten");
		addReverseStorageRecipe(ingotVoidtungsten, "blockVoidtungsten");
		addReverseStorageRecipe(nuggetVoidtungsten, "ingotVoidtungsten");
		addStorageRecipe(dustVoidtungsten, "dustTinyVoidtungsten");
		addReverseStorageRecipe(tinyVoidtungsten, "dustVoidtungsten");
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateVoidtungsten, monorder, "AAA", "AIA", "AAA", 'I', "ingotVoidtungsten", 'A', "itemAlumentum");
		addGrindingRecipes(ingotVoidtungsten, dustVoidtungsten);
		addGrindingRecipes(plateVoidtungsten, dustVoidtungsten);

		addStorageRecipe(blockVoidcupronickel, "ingotVoidcupronickel");
		addStorageRecipe(ingotVoidcupronickel, "nuggetVoidcupronickel");
		addReverseStorageRecipe(ingotVoidcupronickel, "blockVoidcupronickel");
		addReverseStorageRecipe(nuggetVoidcupronickel, "ingotVoidcupronickel");
		addStorageRecipe(dustVoidcupronickel, "dustTinyVoidcupronickel");
		addReverseStorageRecipe(tinyVoidcupronickel, "dustVoidcupronickel");
		addSmelting(dustVoidcupronickel, ingotVoidcupronickel);
		addSmelting(plateVoidcupronickel, ingotVoidcupronickel);
		addArcaneCraftingRecipe(keyAlumentum, plateVoidcupronickel, monorder, "A", "I", 'I', "ingotVoidcupronickel", 'A', "itemAlumentum");
		addGrindingRecipes(ingotVoidcupronickel, dustVoidcupronickel);
		addGrindingRecipes(plateVoidcupronickel, dustVoidcupronickel);
	}

	public static void addEquipmentAlloyMetalRecipes() {
		addStorageRecipe(blockWardenicBronze, "ingotWardenicBronze");
		addStorageRecipe(ingotWardenicBronze, "nuggetWardenicBronze");
		addReverseStorageRecipe(ingotWardenicBronze, "blockWardenicBronze");
		addReverseStorageRecipe(nuggetWardenicBronze, "ingotWardenicBronze");
		addStorageRecipe(dustWardenicBronze, "dustTinyWardenicBronze");
		addReverseStorageRecipe(tinyWardenicBronze, "dustWardenicBronze");
		addSmelting(dustWardenicBronze, ingotWardenicBronze);
		addSmelting(plateWardenicBronze, ingotWardenicBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateWardenicBronze, monorder, "A", "I", 'I', "ingotWardenicBronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotWardenicBronze, dustWardenicBronze);
		addGrindingRecipes(plateWardenicBronze, dustWardenicBronze);

		addStorageRecipe(blockWardenicSteel, "ingotWardenicSteel");
		addStorageRecipe(ingotWardenicSteel, "nuggetWardenicSteel");
		addReverseStorageRecipe(ingotWardenicSteel, "blockWardenicSteel");
		addReverseStorageRecipe(nuggetWardenicSteel, "ingotWardenicSteel");
		addStorageRecipe(dustWardenicSteel, "dustTinyWardenicSteel");
		addReverseStorageRecipe(tinyWardenicSteel, "dustWardenicSteel");
		addSmelting(dustWardenicSteel, ingotWardenicSteel);
		addSmelting(plateWardenicSteel, ingotWardenicSteel);
		recipeWardenSteelPlate = addArcaneCraftingRecipe(keyWardenPlate, plateWardenicSteel, monorder, " A ", "AIA", " A ", 'A', itemAlumentum, 'I', "ingotWardenicSteel"); //Special
		addGrindingRecipes(ingotWardenicSteel, dustWardenicSteel);
		addGrindingRecipes(plateWardenicSteel, dustWardenicSteel);

		addStorageRecipe(blockWardenicRiftishBronze, "ingotWardenicRiftishBronze");
		addStorageRecipe(ingotWardenicRiftishBronze, "nuggetWardenicRiftishBronze");
		addReverseStorageRecipe(ingotWardenicRiftishBronze, "blockWardenicRiftishBronze");
		addReverseStorageRecipe(nuggetWardenicRiftishBronze, "ingotWardenicRiftishBronze");
		addStorageRecipe(dustWardenicRiftishBronze, "dustTinyWardenicRiftishBronze");
		addReverseStorageRecipe(tinyWardenicRiftishBronze, "dustWardenicRiftishBronze");
		addSmelting(dustWardenicRiftishBronze, ingotWardenicRiftishBronze);
		addSmelting(plateWardenicRiftishBronze, ingotWardenicRiftishBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateWardenicRiftishBronze, monorder, " A ", " I ", "A A", 'A', itemAlumentum, 'I', "ingotWardenicRiftishBronze");
		addGrindingRecipes(ingotWardenicRiftishBronze, dustWardenicRiftishBronze);
		addGrindingRecipes(plateWardenicRiftishBronze, dustWardenicRiftishBronze);

		addStorageRecipe(blockWardenicComposite, "ingotWardenicComposite");
		addStorageRecipe(ingotWardenicComposite, "nuggetWardenicComposite");
		addReverseStorageRecipe(ingotWardenicComposite, "blockWardenicComposite");
		addReverseStorageRecipe(nuggetWardenicComposite, "ingotWardenicComposite");
		addStorageRecipe(dustWardenicComposite, "dustTinyWardenicComposite");
		addReverseStorageRecipe(tinyWardenicComposite, "dustWardenicComposite");
		addSmelting(dustWardenicComposite, smeltedWardenicComposite);
		addSmelting(plateWardenicComposite, smeltedWardenicComposite);
		recipeWardenicCompositePlate = addArcaneCraftingRecipe(keyWardenCompositePlate, plateWardenicComposite, new AspectList().add(ORDER, 1), " A ", "AIA", " A ", 'A', aluDenseTemp, 'I', "ingotWardenicComposite"); //Special
		addGrindingRecipes(ingotWardenicComposite, dustWardenicComposite);
		addGrindingRecipes(plateWardenicComposite, dustWardenicComposite);

		addStorageRecipe(blockArcaneRedsolder, "ingotArcaneRedsolder");
		addStorageRecipe(ingotArcaneRedsolder, "nuggetArcaneRedsolder");
		addReverseStorageRecipe(ingotArcaneRedsolder, "blockArcaneRedsolder");
		addReverseStorageRecipe(nuggetArcaneRedsolder, "ingotArcaneRedsolder");
		addStorageRecipe(dustArcaneRedsolder, "dustTinyArcaneRedsolder");
		addReverseStorageRecipe(tinyArcaneRedsolder, "dustArcaneRedsolder");
		addSmelting(dustArcaneRedsolder, ingotArcaneRedsolder);
		addSmelting(plateArcaneRedsolder, ingotArcaneRedsolder);
		addArcaneCraftingRecipe(keyAlumentum, plateArcaneRedsolder, monorder, "A", "I", 'I', "ingotArcaneRedsolder", 'A', "itemAlumentum"); //TODO
		addGrindingRecipes(ingotArcaneRedsolder, dustArcaneRedsolder);
		addGrindingRecipes(plateArcaneRedsolder, dustArcaneRedsolder);

		addStorageRecipe(blockRedbronze, "ingotRedbronze");
		addStorageRecipe(ingotRedbronze, "nuggetRedbronze");
		addReverseStorageRecipe(ingotRedbronze, "blockRedbronze");
		addReverseStorageRecipe(nuggetRedbronze, "ingotRedbronze");
		addStorageRecipe(dustRedbronze, "dustTinyRedbronze");
		addReverseStorageRecipe(tinyRedbronze, "dustRedbronze");
		addSmelting(dustRedbronze, ingotRedbronze);
		addSmelting(plateRedbronze, ingotRedbronze);
		addArcaneCraftingRecipe(keyAlumentum, plateRedbronze, monorder, "A", "I", "A", 'I', "ingotRedbronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotRedbronze, dustRedbronze);
		addGrindingRecipes(plateRedbronze, dustRedbronze);

		addStorageRecipe(blockHardenedRedbronze, "ingotHardenedRedbronze");
		addStorageRecipe(ingotHardenedRedbronze, "nuggetHardenedRedbronze");
		addReverseStorageRecipe(ingotHardenedRedbronze, "blockHardenedRedbronze");
		addReverseStorageRecipe(nuggetHardenedRedbronze, "ingotHardenedRedbronze");
		addStorageRecipe(dustHardenedRedbronze, "dustTinyHardenedRedbronze");
		addReverseStorageRecipe(tinyHardenedRedbronze, "dustHardenedRedbronze");
		addSmelting(dustHardenedRedbronze, ingotHardenedRedbronze);
		addSmelting(plateHardenedRedbronze, ingotHardenedRedbronze);
		addArcaneCraftingRecipe(keyAlumentum, plateHardenedRedbronze, monorder, " A ", "AIA", " A ", 'I', "ingotHardenedRedbronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotHardenedRedbronze, dustHardenedRedbronze);
		addGrindingRecipes(plateHardenedRedbronze, dustHardenedRedbronze);

		addStorageRecipe(blockFluxsteel, "ingotFluxsteel");
		addStorageRecipe(ingotFluxsteel, "nuggetFluxsteel");
		addReverseStorageRecipe(ingotFluxsteel, "blockFluxsteel");
		addReverseStorageRecipe(nuggetFluxsteel, "ingotFluxsteel");
		addStorageRecipe(dustFluxsteel, "dustTinyFluxsteel");
		addReverseStorageRecipe(tinyFluxsteel, "dustFluxsteel");
		addSmelting(dustFluxsteel, ingotFluxsteel);
		addSmelting(plateFluxsteel, ingotFluxsteel);
		addArcaneCraftingRecipe(keyAlumentum, plateFluxsteel, monorder, "A A", "AIA", " A ", 'I', "ingotFluxsteel", 'A', "itemAlumentum");
		addGrindingRecipes(ingotFluxsteel, dustFluxsteel);
		addGrindingRecipes(plateFluxsteel, dustFluxsteel);

		addStorageRecipe(blockFluxedTungsten, "ingotFluxedTungsten");
		addStorageRecipe(ingotFluxedTungsten, "nuggetFluxedTungsten");
		addReverseStorageRecipe(ingotFluxedTungsten, "blockFluxedTungsten");
		addReverseStorageRecipe(nuggetFluxedTungsten, "ingotFluxedTungsten");
		addStorageRecipe(dustFluxedTungsten, "dustTinyFluxedTungsten");
		addReverseStorageRecipe(tinyFluxedTungsten, "dustFluxedTungsten");
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateFluxedTungsten, monorder, "AAA", "AIA", "AAA", 'I', "ingotFluxedTungsten", 'A', "itemAlumentum");
		addGrindingRecipes(ingotFluxedTungsten, dustFluxedTungsten);
		addGrindingRecipes(plateFluxedTungsten, dustFluxedTungsten);

		addStorageRecipe(blockMagneoturgicComposite, "ingotMagneoturgicComposite");
		addStorageRecipe(ingotMagneoturgicComposite, "nuggetMagneoturgicComposite");
		addReverseStorageRecipe(ingotMagneoturgicComposite, "blockMagneoturgicComposite");
		addReverseStorageRecipe(nuggetMagneoturgicComposite, "ingotMagneoturgicComposite");
		addStorageRecipe(dustMagneoturgicComposite, "dustTinyMagneoturgicComposite");
		addReverseStorageRecipe(tinyMagneoturgicComposite, "dustMagneoturgicComposite");
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateMagneoturgicComposite, monorder, " A ", " I ", "A A", 'I', "ingotMagneoturgicComposite", 'A', aluDenseTemp);
		addGrindingRecipes(ingotMagneoturgicComposite, dustMagneoturgicComposite);
		addGrindingRecipes(plateMagneoturgicComposite, dustMagneoturgicComposite);

		addStorageRecipe(blockFluxedComposite, "ingotFluxedComposite");
		addStorageRecipe(ingotFluxedComposite, "nuggetFluxedComposite");
		addReverseStorageRecipe(ingotFluxedComposite, "blockFluxedComposite");
		addReverseStorageRecipe(nuggetFluxedComposite, "ingotFluxedComposite");
		addStorageRecipe(dustFluxedComposite, "dustTinyFluxedComposite");
		addReverseStorageRecipe(tinyFluxedComposite, "dustFluxedComposite");
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateFluxedComposite, monorder, "A A", "AIA", " A ", 'I', "ingotFluxedComposite", 'A', aluDenseTemp);
		addGrindingRecipes(ingotFluxedComposite, dustFluxedComposite);
		addGrindingRecipes(plateFluxedComposite, dustFluxedComposite);

		addStorageRecipe(blockResonantFluxedComposite, "ingotResonantFluxedComposite");
		addStorageRecipe(ingotResonantFluxedComposite, "nuggetResonantFluxedComposite");
		addReverseStorageRecipe(ingotResonantFluxedComposite, "blockResonantFluxedComposite");
		addReverseStorageRecipe(nuggetResonantFluxedComposite, "ingotResonantFluxedComposite");
		addStorageRecipe(dustResonantFluxedComposite, "dustTinyResonantFluxedComposite");
		addReverseStorageRecipe(tinyResonantFluxedComposite, "dustResonantFluxedComposite");
		//Too high melting point for regular furnace;
		addArcaneCraftingRecipe(keyAlumentum, plateResonantFluxedComposite, monorder, "AAA", "AIA", "AAA", 'I', "ingotResonantFluxedComposite", 'A', aluDenseTemp);
		addGrindingRecipes(ingotResonantFluxedComposite, dustResonantFluxedComposite);
		addGrindingRecipes(plateResonantFluxedComposite, dustResonantFluxedComposite);

		addStorageRecipe(blockEmpoweredVoidbrass, "ingotEmpoweredVoidbrass");
		addStorageRecipe(ingotEmpoweredVoidbrass, "nuggetEmpoweredVoidbrass");
		addReverseStorageRecipe(ingotEmpoweredVoidbrass, "blockEmpoweredVoidbrass");
		addReverseStorageRecipe(nuggetEmpoweredVoidbrass, "ingotEmpoweredVoidbrass");
		addStorageRecipe(dustEmpoweredVoidbrass, "dustTinyEmpoweredVoidbrass");
		addReverseStorageRecipe(tinyEmpoweredVoidbrass, "dustEmpoweredVoidbrass");
		addSmelting(dustEmpoweredVoidbrass, ingotEmpoweredVoidbrass);
		addSmelting(plateEmpoweredVoidbrass, ingotEmpoweredVoidbrass);
		addArcaneCraftingRecipe(keyAlumentum, plateEmpoweredVoidbrass, monorder, "A", "I", "A", 'I', "ingotEmpoweredVoidbrass", 'A', "itemAlumentum");
		addGrindingRecipes(ingotEmpoweredVoidbrass, dustEmpoweredVoidbrass);
		addGrindingRecipes(plateEmpoweredVoidbrass, dustEmpoweredVoidbrass);

		addStorageRecipe(blockCrimsonThaumium, "ingotCrimsonThaumium");
		addStorageRecipe(ingotCrimsonThaumium, "nuggetCrimsonThaumium");
		addReverseStorageRecipe(ingotCrimsonThaumium, "blockCrimsonThaumium");
		addReverseStorageRecipe(nuggetCrimsonThaumium, "ingotCrimsonThaumium");
		addStorageRecipe(dustCrimsonThaumium, "dustTinyCrimsonThaumium");
		addReverseStorageRecipe(tinyCrimsonThaumium, "dustCrimsonThaumium");
		addSmelting(dustCrimsonThaumium, ingotCrimsonThaumium);
		addSmelting(plateCrimsonThaumium, ingotCrimsonThaumium);
		addArcaneCraftingRecipe(keyAlumentum, plateCrimsonThaumium, monorder, " A ", "AIA", " A ", 'I', "ingotCrimsonThaumium", 'A', "itemAlumentum");
		addGrindingRecipes(ingotCrimsonThaumium, dustCrimsonThaumium);
		addGrindingRecipes(plateCrimsonThaumium, dustCrimsonThaumium);

		addStorageRecipe(blockOccultVoidtungsten, "ingotOccultVoidtungsten");
		addStorageRecipe(ingotOccultVoidtungsten, "nuggetOccultVoidtungsten");
		addReverseStorageRecipe(ingotOccultVoidtungsten, "blockOccultVoidtungsten");
		addReverseStorageRecipe(nuggetOccultVoidtungsten, "ingotOccultVoidtungsten");
		addStorageRecipe(dustOccultVoidtungsten, "dustTinyOccultVoidtungsten");
		addReverseStorageRecipe(tinyOccultVoidtungsten, "dustOccultVoidtungsten");
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateOccultVoidtungsten, monorder, " A ", "AIA", " A ", 'I', "ingotOccultVoidtungsten", 'A', aluDenseTemp);
		addGrindingRecipes(ingotOccultVoidtungsten, dustOccultVoidtungsten);
		addGrindingRecipes(plateOccultVoidtungsten, dustOccultVoidtungsten);
	}

	public static void addGemRecipes() {
		addStorageRecipe(blockPyrope, "gemPyrope");
		addStorageRecipe(gemPyrope, "nuggetPyrope");
		addReverseStorageRecipe(gemPyrope, "blockPyrope");
		addReverseStorageRecipe(shardPyrope, "gemPyrope");
		addStorageRecipe(dustPyrope, "dustTinyPyrope");
		addReverseStorageRecipe(tinyPyrope, "dustPyrope");
		addSmelting(dustPyrope, gemPyrope);
		addGrindingRecipes(gemPyrope, dustPyrope);

		addStorageRecipe(blockDioptase, "gemDioptase");
		addStorageRecipe(gemDioptase, "nuggetDioptase");
		addReverseStorageRecipe(gemDioptase, "blockDioptase");
		addReverseStorageRecipe(shardDioptase, "gemDioptase");
		addStorageRecipe(dustDioptase, "dustTinyDioptase");
		addReverseStorageRecipe(tinyDioptase, "dustDioptase");
		addSmelting(dustDioptase, gemDioptase);
		addGrindingRecipes(gemDioptase, dustDioptase);

		addStorageRecipe(blockFluonicSapphire, "gemFluonicSapphire");
		addStorageRecipe(gemFluonicSapphire, "nuggetFluonicSapphire");
		addReverseStorageRecipe(gemFluonicSapphire, "blockFluonicSapphire");
		addReverseStorageRecipe(shardFluonicSapphire, "gemFluonicSapphire");
		addStorageRecipe(dustFluonicSapphire, "dustTinyFluonicSapphire");
		addReverseStorageRecipe(tinyFluonicSapphire, "dustFluonicSapphire");
		addSmelting(dustFluonicSapphire, gemFluonicSapphire);
		addGrindingRecipes(gemFluonicSapphire, dustFluonicSapphire);

		addStorageRecipe(blockFluonicPyroptase, "gemFluonicPyroptase");
		addStorageRecipe(gemFluonicPyroptase, "nuggetFluonicPyroptase");
		addReverseStorageRecipe(gemFluonicPyroptase, "blockFluonicPyroptase");
		addReverseStorageRecipe(shardFluonicPyroptase, "gemFluonicPyroptase");
		addStorageRecipe(dustFluonicPyroptase, "dustTinyFluonicPyroptase");
		addReverseStorageRecipe(tinyFluonicPyroptase, "dustFluonicPyroptase");
		addSmelting(dustFluonicPyroptase, gemFluonicPyroptase);
		addGrindingRecipes(gemFluonicPyroptase, dustFluonicPyroptase, 8);

		addStorageRecipe(blockWardenicCrystal, "gemWardenicCrystal");
		addStorageRecipe(gemWardenicCrystal, "nuggetWardenicCrystal");
		addReverseStorageRecipe(gemWardenicCrystal, "blockWardenicCrystal");
		addReverseStorageRecipe(shardWardenicCrystal, "gemWardenicCrystal");
		addStorageRecipe(dustWardenicCrystal, "dustTinyWardenicCrystal");
		addReverseStorageRecipe(tinyWardenicCrystal, "dustWardenicCrystal");
		addSmelting(dustWardenicCrystal, gemWardenicCrystal);
		addGrindingRecipes(gemWardenicCrystal, dustWardenicCrystal, 8);

		addStorageRecipe(blockActivatedWardenicCrystal, "gemActivatedWardenicCrystal");
		addStorageRecipe(gemActivatedWardenicCrystal, "nuggetActivatedWardenicCrystal");
		addReverseStorageRecipe(gemActivatedWardenicCrystal, "blockActivatedWardenicCrystal");
		addReverseStorageRecipe(shardActivatedWardenicCrystal, "gemActivatedWardenicCrystal");
		addStorageRecipe(dustActivatedWardenicCrystal, "dustTinyActivatedWardenicCrystal");
		addReverseStorageRecipe(tinyActivatedWardenicCrystal, "dustActivatedWardenicCrystal");
		addSmelting(dustActivatedWardenicCrystal, gemActivatedWardenicCrystal);
		addGrindingRecipes(gemActivatedWardenicCrystal, dustActivatedWardenicCrystal, 8);

		addStorageRecipe(blockAwakenedWardenicCrystal, "gemAwakenedWardenicCrystal");
		addStorageRecipe(gemAwakenedWardenicCrystal, "nuggetAwakenedWardenicCrystal");
		addReverseStorageRecipe(gemAwakenedWardenicCrystal, "blockAwakenedWardenicCrystal");
		addReverseStorageRecipe(shardAwakenedWardenicCrystal, "gemAwakenedWardenicCrystal");
		addStorageRecipe(dustAwakenedWardenicCrystal, "dustTinyAwakenedWardenicCrystal");
		addReverseStorageRecipe(tinyAwakenedWardenicCrystal, "dustAwakenedWardenicCrystal");
		addSmelting(dustAwakenedWardenicCrystal, gemAwakenedWardenicCrystal);
		addGrindingRecipes(gemAwakenedWardenicCrystal, dustAwakenedWardenicCrystal, 10);

		recipeQuartzBlock = addSquareRecipe(blockWardenicQuartz, "gemWardenicQuartz");
		addStorageRecipe(gemWardenicQuartz, "nuggetWardenicQuartz");
		recipeQuartzDeblock = addDeblockingRecipe(gemWardenicQuartz, blockWardenicQuartz); //Special
		addReverseStorageRecipe(shardWardenicQuartz, "gemWardenicQuartz");
		addStorageRecipe(dustWardenicQuartz, "dustTinyWardenicQuartz");
		addReverseStorageRecipe(tinyWardenicQuartz, "dustWardenicQuartz");
		addSmelting(dustWardenicQuartz, gemWardenicQuartz);
		addGrindingRecipes(gemWardenicQuartz, dustWardenicQuartz, 4);

		addSquareRecipe(blockInfusedQuartz, "gemInfusedQuartz");
		addStorageRecipe(gemInfusedQuartz, "nuggetInfusedQuartz");
		addDeblockingRecipe(gemInfusedQuartz, blockInfusedQuartz); //Special
		addReverseStorageRecipe(shardInfusedQuartz, "gemInfusedQuartz");
		addStorageRecipe(dustInfusedQuartz, "dustTinyInfusedQuartz");
		addReverseStorageRecipe(tinyInfusedQuartz, "dustInfusedQuartz");
		addSmelting(dustInfusedQuartz, gemInfusedQuartz);
		addGrindingRecipes(gemInfusedQuartz, dustInfusedQuartz, 5);

		addSquareRecipe(blockRedquartz, "gemRedquartzThaumic");
		addStorageRecipe(gemRedquartz, "nuggetRedquartzThaumic");
		addDeblockingRecipe(gemRedquartz, blockRedquartz); //Special
		addReverseStorageRecipe(shardRedquartz, "gemRedquartzThaumic");
		addStorageRecipe(dustRedquartz, "dustTinyRedquartzThaumic");
		addReverseStorageRecipe(tinyRedquartz, "dustRedquartzThaumic");
		addSmelting(dustRedquartz, gemRedquartz);
		addGrindingRecipes(gemRedquartz, dustRedquartz, 4);
	}
	
	public static void addMiscMetalRecipes() {
		addStorageRecipe(blockLanthanides, "ingotXenotimeLanthanides");
		addStorageRecipe(ingotLanthanides, "nuggetXenotimeLanthanides");
		addReverseStorageRecipe(ingotLanthanides, "blockXenotimeLanthanides");
		addReverseStorageRecipe(nuggetLanthanides, "ingotXenotimeLanthanides");
		addStorageRecipe(dustLanthanides, "dustTinyXenotimeLanthanides");
		addReverseStorageRecipe(tinyLanthanides, "dustXenotimeLanthanides");
		addSmelting(dustLanthanides, ingotLanthanides);
		addGrindingRecipes(ingotLanthanides, dustLanthanides);

		addStorageRecipe(blockXenotimeJunk, "ingotXenotimeExtras");
		addStorageRecipe(ingotXenotimeJunk, "nuggetXenotimeExtras");
		addReverseStorageRecipe(ingotXenotimeJunk, "blockXenotimeExtras");
		addReverseStorageRecipe(nuggetXenotimeJunk, "ingotXenotimeExtras");
		addStorageRecipe(dustXenotimeJunk, "dustTinyXenotimeExtras");
		addReverseStorageRecipe(tinyXenotimeJunk, "dustXenotimeExtras");
		addSmelting(dustXenotimeJunk, ingotXenotimeJunk);
		addGrindingRecipes(ingotXenotimeJunk, dustXenotimeJunk);
		
		addStorageRecipe(blockIridosmium, "ingotIridosmium");
		addStorageRecipe(ingotIridosmium, "nuggetIridosmium");
		addReverseStorageRecipe(ingotIridosmium, "blockIridosmium");
		addReverseStorageRecipe(nuggetIridosmium, "ingotIridosmium");
		addStorageRecipe(dustIridosmium, "dustTinyIridosmium");
		addReverseStorageRecipe(tinyIridosmium, "dustIridosmium");
		//Too high melting point for regular furnace
		addGrindingRecipes(ingotIridosmium, dustIridosmium);

		addStorageRecipe(blockThaumicBronze, "ingotThaumicBronze");
		addStorageRecipe(ingotThaumicBronze, "nuggetThaumicBronze");
		addReverseStorageRecipe(ingotThaumicBronze, "blockThaumicBronze");
		addReverseStorageRecipe(nuggetThaumicBronze, "ingotThaumicBronze");
		addStorageRecipe(dustThaumicBronze, "dustTinyThaumicBronze");
		addReverseStorageRecipe(tinyThaumicBronze, "dustThaumicBronze");
		addSmelting(dustThaumicBronze, ingotThaumicBronze);
		addSmelting(plateThaumicBronze, ingotThaumicBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateThaumicBronze, monorder, "A", "I", 'I', "ingotThaumicBronze", 'A', "itemAlumentum");
		addGrindingRecipes(ingotThaumicBronze, dustThaumicBronze);
		addGrindingRecipes(plateThaumicBronze, dustThaumicBronze);

		addStorageRecipe(blockOsmiumLutetium, "ingotOsmiumLutetium");
		addStorageRecipe(ingotOsmiumLutetium, "nuggetOsmiumLutetium");
		addReverseStorageRecipe(ingotOsmiumLutetium, "blockOsmiumLutetium");
		addReverseStorageRecipe(nuggetOsmiumLutetium, "ingotOsmiumLutetium");
		addStorageRecipe(dustOsmiumLutetium, "dustTinyOsmiumLutetium");
		addReverseStorageRecipe(tinyOsmiumLutetium, "dustOsmiumLutetium");
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateOsmiumLutetium, monorder, "A A", "AIA", " A ", 'I', "ingotOsmiumLutetium", 'A', "itemAlumentum");
		addGrindingRecipes(ingotOsmiumLutetium, dustOsmiumLutetium);
		addGrindingRecipes(plateOsmiumLutetium, dustOsmiumLutetium);
		
				/*RecipeHelper.addStorageRecipe(block$Mineral, "ingot$MineralOre");
		RecipeHelper.addStorageRecipe(ingot$Mineral, "nugget$MineralOre");
		RecipeHelper.addReverseStorageRecipe(ingot$Mineral, "block$MineralOre");
		RecipeHelper.addReverseStorageRecipe(nugget$Mineral, "ingot$MineralOre");
		RecipeHelper.addStorageRecipe(dust$Mineral, "dustTiny$MineralOre");
		RecipeHelper.addReverseStorageRecipe(tiny$Mineral, "dust$MineralOre");
		RecipeHelper.addSmelting(dust$Mineral, ingot$Mineral);
		RecipeHelper.addSmelting(plate$Mineral, ingot$Mineral);
		RecipeHelper.addArcaneCraftingRecipe(keyAlumentum, plate$Mineral, monorder, "A", "I", 'I', "ingot$MineralOre", "itemAlumentum");
		RecipeHelper.addGrindingRecipes(ingot$Mineral, dust$Mineral);
		RecipeHelper.addGrindingRecipes(plate$Mineral, dust$Mineral);*/
	}

	public static void addAlloyingRecipes() {
		recipeCuZnBi = new ShapelessOreRecipe[2];
		recipeWardenMetal = new ShapelessOreRecipe[2];

		recipeCuZn = addAlloyRecipe(rawBrass, 4, INGOT, CU, CU, CU, ZN);
		recipeCuSn = addAlloyRecipe(rawBronze, 4, INGOT, CU, CU, CU, SN);
		recipeCuAs = addAlloyRecipe(rawArsenicalBronze, 4, INGOT, CU, CU, CU, AS);
		recipeCuSb = addAlloyRecipe(rawAntimonialBronze, 4, INGOT, CU, CU, CU, SB);
		recipeCuZnBi[0] = addAlloyRecipe(rawBismuthBronze, 8, INGOT, CUZN, CUZN, CUZN, CUZN, CU, CU, CU, BI);
		recipeCuZnBi[1] = addAlloyRecipe(rawBismuthBronze, 8, INGOT, CU, CU, CU, CU, CU, CU, ZN, BI);
		recipeCuAsSb = addAlloyRecipe(rawMithril, 2, INGOT, CUAS, CUSB);
		if (ThaumRevConfig.backwardsAlBronze) {
			recipeCuAl = addAlloyRecipe(rawAluminiumBronze, 4, INGOT, CU, AL, AL, AL);
		} else {
			recipeCuAl = addAlloyRecipe(rawAluminiumBronze, 4, INGOT, CU, CU, CU, AL);
		}
		recipeCuNi = addAlloyRecipe(rawCupronickel, 4, INGOT, CU, CU, CU, NI);
		recipeRBrz = addAlloyRecipe(rawRiftishBronze, 9, INGOT, MTHR, MTHR, MTHR, MTHR, CUBI, CUBI, CUSN, CUNI, CUAL);
		recipeCnst = addAlloyRecipe(rawConstantan, 2, INGOT, CU, NI);
		recipeFeNi = addAlloyRecipe(rawInvar, 3, INGOT, FE, FE, NI);
		recipeAuAg = addAlloyRecipe(rawElectrum, 2, INGOT, AU, AG);
		recipeWardenMetal[0] = addShapelessRecipe(cloneStack(rawWardenicMetal, 9), INGOT + THMM, INGOT + THMM, INGOT + THMM, INGOT + PD, "dustWardenicBindingCompound", INGOT + CUZN, INGOT + ELCT, INGOT + ZN, "quicksilver");
		//Dull Redsolder
		//Redsolder

		recDustCuZnBi = new ShapelessOreRecipe[2];

		recDustCuZn = addAlloyRecipe(dustBrass, 4, DUST, CU, CU, CU, ZN);
		recDustCuSn = addAlloyRecipe(dustBronze, 4, DUST, CU, CU, CU, SN);
		recDustCuAs = addAlloyRecipe(dustArsenicalBronze, 4, DUST, CU, CU, CU, AS);
		recDustCuSb = addAlloyRecipe(dustAntimonialBronze, 4, DUST, CU, CU, CU, SB);
		recDustCuZnBi[0] = addAlloyRecipe(dustBismuthBronze, 8, DUST, CUZN, CUZN, CUZN, CUZN, CU, CU, CU, BI);
		recDustCuZnBi[1] = addAlloyRecipe(dustBismuthBronze, 8, DUST, CU, CU, CU, CU, CU, CU, ZN, BI);
		recDustCuAsSb = addAlloyRecipe(dustMithril, 2, DUST, CUAS, CUSB);
		if (ThaumRevConfig.backwardsAlBronze) {
			recDustCuAl = addAlloyRecipe(dustAluminiumBronze, 4, DUST, CU, AL, AL, AL);
		} else {
			recDustCuAl = addAlloyRecipe(dustAluminiumBronze, 4, DUST, CU, CU, CU, AL);
		}
		recDustCuNi = addAlloyRecipe(dustCupronickel, 4, DUST, CU, CU, CU, NI);
		recDustRBrz = addAlloyRecipe(dustRiftishBronze, 9, DUST, MTHR, MTHR, MTHR, MTHR, CUBI, CUBI, CUSN, CUNI, CUAL);
		recDustCnst = addAlloyRecipe(dustConstantan, 2, DUST, CU, NI);
		recDustFeNi = addAlloyRecipe(dustInvar, 3, DUST, FE, FE, NI);
		recDustAuAg = addAlloyRecipe(dustElectrum, 2, DUST, AU, AG);
		recipeWardenMetal[1] = addShapelessRecipe(cloneStack(dustWardenicMetal, 9), DUST + THMM, DUST + THMM, DUST + THMM, DUST + PD, "dustWardenicBindingCompound", DUST + CUZN, DUST + ELCT, DUST + ZN, "quicksilver");
		//Dull Redsolder
		//Redsolder

		addAlloyRecipe(rawRiftishBronze, 1, NUGGET, MTHR, MTHR, MTHR, MTHR, CUBI, CUBI, CUSN, CUNI, CUAL);

		addAlloyRecipe(tinyBrass, 4, TINY_DUST, CU, CU, CU, ZN);
		addAlloyRecipe(tinyBronze, 4, TINY_DUST, CU, CU, CU, SN);
		addAlloyRecipe(tinyArsenicalBronze, 4, TINY_DUST, CU, CU, CU, AS);
		addAlloyRecipe(tinyAntimonialBronze, 4, TINY_DUST, CU, CU, CU, SB);
		addAlloyRecipe(tinyBismuthBronze, 8, TINY_DUST, CUZN, CUZN, CUZN, CUZN, CU, CU, CU, BI);
		addAlloyRecipe(tinyBismuthBronze, 8, TINY_DUST, CU, CU, CU, CU, CU, CU, ZN, BI);
		addAlloyRecipe(tinyMithril, 2, TINY_DUST, CUAS, CUSB);
		if (ThaumRevConfig.backwardsAlBronze) {
			addAlloyRecipe(tinyAluminiumBronze, 4, TINY_DUST, CU, AL, AL, AL);
		} else {
			addAlloyRecipe(tinyAluminiumBronze, 4, TINY_DUST, CU, CU, CU, AL);
		}
		addAlloyRecipe(tinyCupronickel, 4, TINY_DUST, CU, CU, CU, NI);
		addAlloyRecipe(dustRiftishBronze, 1, TINY_DUST, MTHR, MTHR, MTHR, MTHR, CUBI, CUBI, CUSN, CUNI, CUAL);
		addAlloyRecipe(tinyConstantan, 2, TINY_DUST, CU, NI);
		addAlloyRecipe(tinyInvar, 3, TINY_DUST, FE, FE, NI);
		addAlloyRecipe(tinyElectrum, 2, TINY_DUST, AU, AG);
		addShapelessRecipe(dustWardenicMetal, TINY_DUST + THMM, TINY_DUST + THMM, TINY_DUST + THMM, TINY_DUST + PD, "dustTinyWardenicBindingCompound", TINY_DUST + CUZN, TINY_DUST + ELCT, TINY_DUST + ZN, "itemDropQuicksilver");
		//Dull Redsolder
		//Redsolder

		if (Loader.isModLoaded("ThermalExpansion")) {
			addInductionAlloyRecipe("Copper", 3, "Zinc", 1, ingotBrass);
			addInductionAlloyRecipe("Copper", 3, "Arsenic", 1, ingotArsenicalBronze);
			addInductionAlloyRecipe("Copper", 3, "Antimony", 1, ingotAntimonialBronze);
			addInductionAlloyRecipe("ArsenicalBronze", 1, "AntimonialBronze", 1, ingotMithril);
			addInductionAlloyRecipe("Copper", ThaumRevConfig.backwardsAlBronze ? 1 : 3, "Aluminium", ThaumRevConfig.backwardsAlBronze ? 3 : 1, ingotAluminiumBronze);
			addInductionAlloyRecipe("Copper", 3, "Nickel", 1, ingotCupronickel);
		}
	}

	public static ResearchPage[] getMaterialPages() {
		List<ResearchPage> list = new ArrayList<ResearchPage>(8);
		list.add(new ResearchPage("0"));

		List<ItemStack> ores = new ArrayList<ItemStack>(9);
		//if (ThaumRevConfig.enableSphalerite) {
		//list.add(new ResearchPage(oreSphalerite));
		//}

		//.addPages(, )
		list.add(new ResearchPage(recipeSalisTiny));
		list.add(new ResearchPage(recipeSalis));

		ResearchPage[] pages = new ResearchPage[list.size()];
		list.toArray(pages);
		return pages;
	}

	public static ResearchPage[] getAlloyPages() {
		List<ResearchPage> list = new ArrayList<ResearchPage>(8);
		List<ResearchPage> smelting = new ArrayList<ResearchPage>(8);
		List<IRecipe> dusts = new ArrayList<IRecipe>(8);
		List<IRecipe> tiny = new ArrayList<IRecipe>(8);

		boolean start = false;

		if (enableBrass) {
			start = true;
			list.add(new ResearchPage("0"));
			list.add(new ResearchPage(recipeCuZn));
			smelting.add(new ResearchPage(rawBrass));
			dusts.add(recDustCuZn);
		}
		if (enableBronze) {
			if (!start) {
				list.add(new ResearchPage("a"));
				start = true;
			} else {
				list.add(new ResearchPage("1"));
			}
			list.add(new ResearchPage(recipeCuSn));
			smelting.add(new ResearchPage(rawBronze));
			dusts.add(recDustCuSn);
		}
		if (enableBismuthBronze) {
			if (!start) {
				list.add(new ResearchPage("b"));
				start = true;
			} else {
				list.add(new ResearchPage("2"));
			}
			list.add(new ResearchPage(recipeCuZnBi));
			smelting.add(new ResearchPage(rawBismuthBronze));
			dusts.add(recDustCuZnBi[0]);
			dusts.add(recDustCuZnBi[1]);
		}
		if (enableMithril) {
			if (!start) {
				list.add(new ResearchPage("c"));
				start = true;
			} else {
				list.add(new ResearchPage("3"));
			}
			list.add(new ResearchPage(recipeCuAsSb));
			smelting.add(new ResearchPage(rawMithril));
			dusts.add(recDustCuAsSb);
		}
		if (enableAlBronze) {
			if (!start) {
				list.add(new ResearchPage("d"));
				start = true;
			} else {
				list.add(new ResearchPage("4"));
			}
			list.add(new ResearchPage(recipeCuAl));
			smelting.add(new ResearchPage(rawAluminiumBronze));
			dusts.add(recDustCuAl);
		}
		if (enableCupronickel) {
			if (!start) {
				list.add(new ResearchPage("e"));
				start = true;
			} else {
				list.add(new ResearchPage("5"));
			}
			list.add(new ResearchPage(recipeCuNi));
			smelting.add(new ResearchPage(rawCupronickel));
			dusts.add(recDustCuNi);
		}
		if (enableRiftishBronze) {
			if (!start) {
				list.add(new ResearchPage("f"));
				start = true;
			} else {
				list.add(new ResearchPage("6"));
			}
			list.add(new ResearchPage(recipeRBrz));
			smelting.add(new ResearchPage(rawRiftishBronze));
			dusts.add(recDustRBrz);
		}
		if (enableConstantan || enableInvar || enableElectrum) {
			char val = getCIEValue();
			if (val == '*') {
				if (!start) {
					list.add(new ResearchPage("g"));
				} else {
					list.add(new ResearchPage("7"));
				}
			} else {
				if (!start) {
					list.add(new ResearchPage("g" + val));
				} else {
					list.add(new ResearchPage("7" + val));
				}
			}
		}
		if (enableConstantan) {
			list.add(new ResearchPage(recipeCnst));
			smelting.add(new ResearchPage(rawConstantan));
			dusts.add(recDustCnst);
		}
		if (enableInvar) {
			list.add(new ResearchPage(recipeFeNi));
			smelting.add(new ResearchPage(rawInvar));
			dusts.add(recDustFeNi);
		}
		if (enableElectrum) {
			list.add(new ResearchPage(recipeAuAg));
			smelting.add(new ResearchPage(rawElectrum));
			dusts.add(recDustAuAg);
		}

        /*if (ThaumRevConfig.enableOsLu) {
            list.add(new ResearchPage(recipeOsLu));
            list.add(new ResearchPage(recCoatOsLu));
            smelting.add(new ResearchPage(coatedOsLu));
        }*/

		if (!dusts.isEmpty()) {
			list.add(new ResearchPage("dust"));
			IRecipe[] dust = new IRecipe[dusts.size()];
			dusts.toArray(dust);
			list.add(new ResearchPage(dust));
		}
	    /*if (!tiny.isEmpty()) {
            list.add(new ResearchPage("tiny"));
            list.add(new ResearchPage(tiny));
        }*/

		if (ThaumRevConfig.thaumonomiconAlloySmelt) {
			list.add(new ResearchPage("smelt"));
			list.addAll(smelting);
		}

		ResearchPage[] pages = new ResearchPage[list.size()];
		list.toArray(pages);
		return pages;
	}

	public static boolean enableAlloys() {
		return enableBrass || enableBronze || enableBismuthBronze || enableMithril || enableAlBronze || enableCupronickel || enableRiftishBronze || enableConstantan || enableInvar || enableElectrum;
	}

	public static char getCIEValue() {
		if (enableConstantan && enableInvar && enableElectrum) {
			return '*';
		} else if (enableConstantan) {
			if (enableInvar) {
				return 'c';
			} else if (enableElectrum) {
				return 'b';
			} else {
				return '1';
			}
		} else if (enableInvar) {
			if (enableElectrum) {
				return 'a';
			} else {
				return '2';
			}
		} else if (enableElectrum) {
			return '3';
		} else {
			return '0';
		}
	}

	public static ItemStack[] getPlatinumTriggers() {
		List<ItemStack> triggers = new ArrayList<ItemStack>();
		triggers.addAll(OreDictionary.getOres("ingotPlatinum"));
		triggers.addAll(OreDictionary.getOres("nuggetPlatinum"));
		triggers.addAll(OreDictionary.getOres("dustPlatinum"));
		triggers.addAll(OreDictionary.getOres("blockPlatinum"));
		triggers.addAll(OreDictionary.getOres("dustPlatinumTiny"));

		ItemStack[] ret = new ItemStack[triggers.size()];
		triggers.toArray(ret);
		return ret;
	}

	public static boolean isGoodClimate(BiomeGenBase biome, float minTemp, float maxTemp, float minRain, float maxRain) {
		float temp = biome.temperature;
		float rain = biome.rainfall;

		return minTemp <= temp && temp <= maxTemp && minRain <= rain && rain <= maxRain;
	}

	/**
	 * @param world - World the Excubitura Rose is generating in...
	 * @param x     - X coordinate, duh.
	 * @param y     - Y coordinate, duh.
	 * @param z     - Z coordinate, duh.
	 *
	 * @return - A modifier for generation of Excubitura Roses. Also used to determine growth speed.
	 */
	public static double getExcubituraModifier(World world, int x, int y, int z) {
		double modifier = 5D;
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		// First off, these guys don't grow in the End, Nether, or Mushroom Biomes. They also won't grow in dead places and wastelands.
		if (BiomeDictionary.isBiomeOfType(biome, Type.END) || BiomeDictionary.isBiomeOfType(biome, Type.NETHER) || BiomeDictionary.isBiomeOfType(biome, Type.MUSHROOM) ||
				BiomeDictionary.isBiomeOfType(biome, Type.DEAD) || BiomeDictionary.isBiomeOfType(biome, Type.WASTELAND)) {
			return 0D;
		}

		// Modify the modifier for temperature.
		double temp = (double) biome.getFloatTemperature(x, y, z);
		if (temp < 0.35D) {
			modifier -= (0.70D - (temp * 2));
		} else if (temp > 0.75D) {
			modifier -= (temp - 0.75D);
		}

		// They don't like snow...
		if (biome.getEnableSnow()) {
			modifier /= 8;
		}

		// Modify the modifier for rainfall.
		double rain = (double) biome.rainfall;
		if (rain < 0.2D) {
			modifier -= (2D - (rain * 10));
		} else if (rain < 0.35D) {
			modifier -= (1.4D - (rain * 4));
		} else if (rain > 0.85D) {
			modifier -= ((2 * rain) - 1.70D);
		} else if (rain > 0.75D) {
			modifier -= (rain - .75D);
		}

		// They really don't like being dried out or drenched...
		if (rain > 0.1D || rain < 0.95D) {
			modifier /= 10.0D;
		}

		// They like moderate temperatures
		if (BiomeDictionary.isBiomeOfType(biome, Type.HOT) || BiomeDictionary.isBiomeOfType(biome, Type.COLD)) {
			modifier *= 0.75D;
		} else {
			modifier *= 1.1D;
		}

		// They like a decent amount of water
		if (BiomeDictionary.isBiomeOfType(biome, Type.DRY)) {
			modifier *= 0.7D;
		} else if (!BiomeDictionary.isBiomeOfType(biome, Type.WET)) {
			modifier *= 1.1D;
		}

		// They are just like regular plants...
		if (BiomeDictionary.isBiomeOfType(biome, Type.SPARSE)) {
			modifier *= 0.75D;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.LUSH)) {
			modifier *= 1.65D;
		}

		// They dislike jungles, and aren't fond of savannas. They do like coniferous forests though.
		if (BiomeDictionary.isBiomeOfType(biome, Type.JUNGLE)) {
			modifier *= 0.5D;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.SAVANNA)) {
			modifier *= 0.75D;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.CONIFEROUS)) {
			modifier *= 1.25D;
		}

		// They dislike saltwater, but love a good stream!
		if (BiomeDictionary.isBiomeOfType(biome, Type.BEACH) || BiomeDictionary.isBiomeOfType(biome, Type.OCEAN)) {
			modifier *= .625D;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.RIVER)) { //Something about the planet Miranda...
			modifier *= 1.375D;
		}

		// No snow! Even bigger no to deserts!
		if (BiomeDictionary.isBiomeOfType(biome, Type.SNOWY)) {
			modifier /= 4.0D;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.SANDY)) {
			modifier /= 8.0D;
		}

		// Take your sorry ass back to Florida...
		if (BiomeDictionary.isBiomeOfType(biome, Type.SWAMP)) {
			modifier *= 0.1875D;
		}

		// They do like some clay in the soil...
		if (BiomeDictionary.isBiomeOfType(biome, Type.MESA)) {
			modifier *= 1.05D;
		}

		// Plains are good, Forests are better!
		if (BiomeDictionary.isBiomeOfType(biome, Type.PLAINS)) {
			modifier *= 1.125D;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.FOREST)) {
			modifier *= 1.75D;
		}

		// Mountains are good, but they prefer big hills.
		if (BiomeDictionary.isBiomeOfType(biome, Type.MOUNTAIN)) {
			modifier *= 1.25D;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.HILLS)) {
			modifier *= 1.5D;
		}

		// Use Botania for the lushest hair possible. Trust me, I'm a wereporcupine.
		if (BiomeDictionary.isBiomeOfType(biome, Type.LUSH)) {
			modifier *= 2.0D;
		}

		// 3 FOR THE PRICE OF 2 IF YOU WANT TO BELIEVE...
		if (BiomeDictionary.isBiomeOfType(biome, Type.SPOOKY)) {
			modifier *= 1.5D;
		}

		// They are a thaumic rose. They like magical biomes.
		if (BiomeDictionary.isBiomeOfType(biome, Type.MAGICAL)) {
			modifier *= 10.0D;
		}

		return modifier * 2.0D;
	}
}
