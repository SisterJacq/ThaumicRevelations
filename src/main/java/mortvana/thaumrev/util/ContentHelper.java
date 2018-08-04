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

import mortvana.melteddashboard.util.helpers.LoadedHelper;

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

		if (LoadedHelper.isThermalExpansionLoaded) {
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

			addInductionOreRecipes(ZN, ingotLead);
			addInductionOreRecipes(YPO, LNTH, dustArsenic);
			addInductionOreRecipes(BI, ingotLead);
			addInductionOreRecipes(CAS, CUAS, ingotSilver);
			addInductionOreRecipes(CSB, CUSB, quicksilver);
		}
	}

	public static void addElementalMetalRecipes() {
		addStorageRecipe(blockCopper, INGOT + CU);
		addStorageRecipe(ingotCopper, NUGGET + CU);
		addReverseStorageRecipe(ingotCopper, BLOCK + CU);
		addReverseStorageRecipe(nuggetCopper, INGOT + CU);
		addStorageRecipe(dustCopper, TINY_DUST + CU);
		addReverseStorageRecipe(tinyCopper, DUST + CU);
		addSmelting(dustCopper, ingotCopper);
		addSmelting(plateCopper, ingotCopper);
		addArcaneCraftingRecipe(keyAlumentum, plateCopper, monorder, "A", "I", 'I', INGOT + CU, 'A', ALMNT);
		addGrindingRecipes(ingotCopper, dustCopper);
		addGrindingRecipes(plateCopper, dustCopper);

		addStorageRecipe(blockZinc, INGOT + ZN);
		addStorageRecipe(ingotZinc, NUGGET + ZN);
		addReverseStorageRecipe(ingotZinc, BLOCK + ZN);
		addReverseStorageRecipe(nuggetZinc, INGOT + ZN);
		addStorageRecipe(dustZinc, TINY_DUST + ZN);
		addReverseStorageRecipe(tinyZinc, DUST + ZN);
		addSmelting(dustZinc, ingotZinc);
		addSmelting(plateZinc, ingotZinc);
		addArcaneCraftingRecipe(keyAlumentum, plateZinc, monorder, "A", "I", 'I', INGOT + ZN, 'A', ALMNT);
		addGrindingRecipes(ingotZinc, dustZinc);
		addGrindingRecipes(plateZinc, dustZinc);

		addStorageRecipe(blockTin, INGOT + SN);
		addStorageRecipe(ingotTin, NUGGET + SN);
		addReverseStorageRecipe(ingotTin, BLOCK + SN);
		addReverseStorageRecipe(nuggetTin, INGOT + SN);
		addStorageRecipe(dustTin, TINY_DUST + SN);
		addReverseStorageRecipe(tinyTin, DUST + SN);
		addSmelting(dustTin, ingotTin);
		addSmelting(plateTin, ingotTin);
		addArcaneCraftingRecipe(keyAlumentum, plateTin, monorder, "A", "I", 'I', INGOT + SN, 'A', ALMNT);
		addGrindingRecipes(ingotTin, dustTin);
		addGrindingRecipes(plateTin, dustTin);

		addStorageRecipe(blockNickel, INGOT + NI);
		addStorageRecipe(ingotNickel, NUGGET + NI);
		addReverseStorageRecipe(ingotNickel, BLOCK + NI);
		addReverseStorageRecipe(nuggetNickel, INGOT + NI);
		addStorageRecipe(dustNickel, TINY_DUST + NI);
		addReverseStorageRecipe(tinyNickel, DUST + NI);
		addSmelting(dustNickel, ingotNickel);
		addSmelting(plateNickel, ingotNickel);
		addArcaneCraftingRecipe(keyAlumentum, plateNickel, monorder, "A", "I", 'I', INGOT + NI, 'A', ALMNT);
		addGrindingRecipes(ingotNickel, dustNickel);
		addGrindingRecipes(plateNickel, dustNickel);

		addStorageRecipe(blockSilver, INGOT + AG);
		addStorageRecipe(ingotSilver, NUGGET + AG);
		addReverseStorageRecipe(ingotSilver, BLOCK + AG);
		addReverseStorageRecipe(nuggetSilver, INGOT + AG);
		addStorageRecipe(dustSilver, TINY_DUST + AG);
		addReverseStorageRecipe(tinySilver, DUST + AG);
		addSmelting(dustSilver, ingotSilver);
		addSmelting(plateSilver, ingotSilver);
		addArcaneCraftingRecipe(keyAlumentum, plateSilver, monorder, "A", "I", 'I', INGOT + AG, 'A', ALMNT);
		addGrindingRecipes(ingotSilver, dustSilver);
		addGrindingRecipes(plateSilver, dustSilver);

		addStorageRecipe(blockLead, INGOT + PB);
		addStorageRecipe(ingotLead, NUGGET + PB);
		addReverseStorageRecipe(ingotLead, BLOCK + PB);
		addReverseStorageRecipe(nuggetLead, INGOT + PB);
		addStorageRecipe(dustLead, TINY_DUST + PB);
		addReverseStorageRecipe(tinyLead, DUST + PB);
		addSmelting(dustLead, ingotLead);
		addSmelting(plateLead, ingotLead);
		addArcaneCraftingRecipe(keyAlumentum, plateLead, monorder, "A", "I", 'I', INGOT + PB, 'A', ALMNT);
		addGrindingRecipes(ingotLead, dustLead);
		addGrindingRecipes(plateLead, dustLead);

		addStorageRecipe(blockLutetium, INGOT + LU);
		addStorageRecipe(ingotLutetium, NUGGET + LU);
		addReverseStorageRecipe(ingotLutetium, BLOCK + LU);
		addReverseStorageRecipe(nuggetLutetium, INGOT + LU);
		addStorageRecipe(dustLutetium, TINY_DUST + LU);
		addReverseStorageRecipe(tinyLutetium, DUST + LU);
		addSmelting(dustLutetium, ingotLutetium);
		addSmelting(plateLutetium, ingotLutetium);
		addArcaneCraftingRecipe(keyAlumentum, plateLutetium, monorder, "A", "I", 'I', INGOT + LU, 'A', ALMNT);
		addGrindingRecipes(ingotLutetium, dustLutetium);
		addGrindingRecipes(plateLutetium, dustLutetium);

		addStorageRecipe(blockTungsten, INGOT + W);
		addStorageRecipe(ingotTungsten, NUGGET + W);
		addReverseStorageRecipe(ingotTungsten, BLOCK + W);
		addReverseStorageRecipe(nuggetTungsten, INGOT + W);
		addStorageRecipe(dustTungsten, TINY_DUST + W);
		addReverseStorageRecipe(tinyTungsten, DUST + W);
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateTungsten, monorder, "A", "I", "A", 'I', INGOT + W, 'A', ALMNT);
		addGrindingRecipes(ingotTungsten, dustTungsten);
		addGrindingRecipes(plateTungsten, dustTungsten);

		addStorageRecipe(blockIridium, INGOT + IR);
		addStorageRecipe(ingotIridium, NUGGET + IR);
		addReverseStorageRecipe(ingotIridium, BLOCK + IR);
		addReverseStorageRecipe(nuggetIridium, INGOT + IR);
		addStorageRecipe(dustIridium, TINY_DUST + IR);
		addReverseStorageRecipe(tinyIridium, DUST + IR);
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateIridium, monorder, "A", "I", "A", 'I', INGOT + IR, 'A', ALMNT);
		addGrindingRecipes(ingotIridium, dustIridium);
		addGrindingRecipes(plateIridium, dustIridium);

		addStorageRecipe(blockBismuth, INGOT + BI);
		addStorageRecipe(ingotBismuth, NUGGET + BI);
		addReverseStorageRecipe(ingotBismuth, BLOCK + BI);
		addReverseStorageRecipe(nuggetBismuth, INGOT + BI);
		addStorageRecipe(dustBismuth, TINY_DUST + BI);
		addReverseStorageRecipe(tinyBismuth, DUST + BI);
		addSmelting(dustBismuth, ingotBismuth);
		addSmelting(plateBismuth, ingotBismuth);
		addArcaneCraftingRecipe(keyAlumentum, plateBismuth, monorder, "A", "I", 'I', INGOT + BI, 'A', ALMNT);
		addGrindingRecipes(ingotBismuth, dustBismuth);
		addGrindingRecipes(plateBismuth, dustBismuth);

		addStorageRecipe(blockArsenic, INGOT + AS);
		addStorageRecipe(ingotArsenic, NUGGET + AS);
		addReverseStorageRecipe(ingotArsenic, BLOCK + AS);
		addReverseStorageRecipe(nuggetArsenic, INGOT + AS);
		addStorageRecipe(dustArsenic, TINY_DUST + AS);
		addReverseStorageRecipe(tinyArsenic, DUST + AS);
		addSmelting(dustArsenic, ingotArsenic);
		addSmelting(plateArsenic, ingotArsenic);
		addArcaneCraftingRecipe(keyAlumentum, plateArsenic, monorder, "A", "I", 'I', INGOT + AS, 'A', ALMNT);
		addGrindingRecipes(ingotArsenic, dustArsenic);
		addGrindingRecipes(plateArsenic, dustArsenic);

		addStorageRecipe(blockAntimony, INGOT + SB);
		addStorageRecipe(ingotAntimony, NUGGET + SB);
		addReverseStorageRecipe(ingotAntimony, BLOCK + SB);
		addReverseStorageRecipe(nuggetAntimony, INGOT + SB);
		addStorageRecipe(dustAntimony, TINY_DUST + SB);
		addReverseStorageRecipe(tinyAntimony, DUST + SB);
		addSmelting(dustAntimony, ingotAntimony);
		addSmelting(plateAntimony, ingotAntimony);
		addArcaneCraftingRecipe(keyAlumentum, plateAntimony, monorder, "A", "I", 'I', INGOT + SB, 'A', ALMNT);
		addGrindingRecipes(ingotAntimony, dustAntimony);
		addGrindingRecipes(plateAntimony, dustAntimony);

		addStorageRecipe(blockNeodymium, INGOT + ND);
		addStorageRecipe(ingotNeodymium, NUGGET + ND);
		addReverseStorageRecipe(ingotNeodymium, BLOCK + ND);
		addReverseStorageRecipe(nuggetNeodymium, INGOT + ND);
		addStorageRecipe(dustNeodymium, TINY_DUST + ND);
		addReverseStorageRecipe(tinyNeodymium, DUST + ND);
		addSmelting(dustNeodymium, ingotNeodymium);
		addSmelting(plateNeodymium, ingotNeodymium);
		addArcaneCraftingRecipe(keyAlumentum, plateNeodymium, monorder, "A", "I", 'I', INGOT + ND, 'A', ALMNT);
		addGrindingRecipes(ingotNeodymium, dustNeodymium);
		addGrindingRecipes(plateNeodymium, dustNeodymium);

		addStorageRecipe(blockOsmium, INGOT + OS);
		addStorageRecipe(ingotOsmium, NUGGET + OS);
		addReverseStorageRecipe(ingotOsmium, BLOCK + OS);
		addReverseStorageRecipe(nuggetOsmium, INGOT + OS);
		addStorageRecipe(dustOsmium, TINY_DUST + OS);
		addReverseStorageRecipe(tinyOsmium, DUST + OS);
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateOsmium, monorder, " A ", " I ", "A A", 'I', INGOT + OS, 'A', ALMNT);
		addGrindingRecipes(ingotOsmium, dustOsmium);
		addGrindingRecipes(plateOsmium, dustOsmium);

		addStorageRecipe(blockPalladium, INGOT + PD);
		addStorageRecipe(ingotPalladium, NUGGET + PD);
		addReverseStorageRecipe(ingotPalladium, BLOCK + PD);
		addReverseStorageRecipe(nuggetPalladium, INGOT + PD);
		addStorageRecipe(dustPalladium, TINY_DUST + PD);
		addReverseStorageRecipe(tinyPalladium, DUST + PD);
		addSmelting(dustPalladium, ingotPalladium);
		addSmelting(platePalladium, ingotPalladium);
		addArcaneCraftingRecipe(keyAlumentum, platePalladium, monorder, "A", "I", 'I', INGOT + PD, 'A', ALMNT);
		addGrindingRecipes(ingotPalladium, dustPalladium);
		addGrindingRecipes(platePalladium, dustPalladium);

		addStorageRecipe(blockAluminium, INGOT + AL);
		addStorageRecipe(ingotAluminium, NUGGET + AL);
		addReverseStorageRecipe(ingotAluminium, BLOCK + AL);
		addReverseStorageRecipe(nuggetAluminium, INGOT + AL);
		addStorageRecipe(dustAluminium, TINY_DUST + AL);
		addReverseStorageRecipe(tinyAluminium, DUST + AL);
		addSmelting(dustAluminium, ingotAluminium);
		addSmelting(plateAluminium, ingotAluminium);
		addArcaneCraftingRecipe(keyAlumentum, plateAluminium, monorder, "A", "I", 'I', INGOT + AL, 'A', ALMNT);
		addGrindingRecipes(ingotAluminium, dustAluminium);
		addGrindingRecipes(plateAluminium, dustAluminium);
	}

	public static void addSimpleAlloyMetalRecipes() {
		addStorageRecipe(blockBrass, INGOT + CUZN);
		addStorageRecipe(ingotBrass, NUGGET + CUZN);
		addReverseStorageRecipe(ingotBrass, BLOCK + CUZN);
		addReverseStorageRecipe(nuggetBrass, INGOT + CUZN);
		addStorageRecipe(dustBrass, TINY_DUST + CUZN);
		addReverseStorageRecipe(tinyBrass, DUST + CUZN);
		addSmelting(dustBrass, ingotBrass);
		addSmelting(plateBrass, ingotBrass);
		addArcaneCraftingRecipe(keyAlumentum, plateBrass, monorder, "A", "I", 'I', INGOT + CUZN, 'A', ALMNT);
		addGrindingRecipes(ingotBrass, dustBrass);
		addGrindingRecipes(plateBrass, dustBrass);

		addStorageRecipe(blockBronze, INGOT + CUSN);
		addStorageRecipe(ingotBronze, NUGGET + CUSN);
		addReverseStorageRecipe(ingotBronze, BLOCK + CUSN);
		addReverseStorageRecipe(nuggetBronze, INGOT + CUSN);
		addStorageRecipe(dustBronze, TINY_DUST + CUSN);
		addReverseStorageRecipe(tinyBronze, DUST + CUSN);
		addSmelting(dustBronze, ingotBronze);
		addSmelting(plateBronze, ingotBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateBronze, monorder, "A", "I", 'I', INGOT + CUSN, 'A', ALMNT);
		addGrindingRecipes(ingotBronze, dustBronze);
		addGrindingRecipes(plateBronze, dustBronze);

		addStorageRecipe(blockArsenicalBronze, INGOT + CUAS);
		addStorageRecipe(ingotArsenicalBronze, NUGGET + CUAS);
		addReverseStorageRecipe(ingotArsenicalBronze, BLOCK + CUAS);
		addReverseStorageRecipe(nuggetArsenicalBronze, INGOT + CUAS);
		addStorageRecipe(dustArsenicalBronze, TINY_DUST + CUAS);
		addReverseStorageRecipe(tinyArsenicalBronze, DUST + CUAS);
		addSmelting(dustArsenicalBronze, ingotArsenicalBronze);
		addSmelting(plateArsenicalBronze, ingotArsenicalBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateArsenicalBronze, monorder, "A", "I", 'I', INGOT + CUAS, 'A', ALMNT);
		addGrindingRecipes(ingotArsenicalBronze, dustArsenicalBronze);
		addGrindingRecipes(plateArsenicalBronze, dustArsenicalBronze);

		addStorageRecipe(blockAntimonialBronze, INGOT + CUSB);
		addStorageRecipe(ingotAntimonialBronze, NUGGET + CUSB);
		addReverseStorageRecipe(ingotAntimonialBronze, BLOCK + CUSB);
		addReverseStorageRecipe(nuggetAntimonialBronze, INGOT + CUSB);
		addStorageRecipe(dustAntimonialBronze, TINY_DUST + CUSB);
		addReverseStorageRecipe(tinyAntimonialBronze, DUST + CUSB);
		addSmelting(dustAntimonialBronze, ingotAntimonialBronze);
		addSmelting(plateAntimonialBronze, ingotAntimonialBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateAntimonialBronze, monorder, "A", "I", 'I', INGOT + CUSB, 'A', ALMNT);
		addGrindingRecipes(ingotAntimonialBronze, dustAntimonialBronze);
		addGrindingRecipes(plateAntimonialBronze, dustAntimonialBronze);

		addStorageRecipe(blockBismuthBronze, INGOT + CUBI);
		addStorageRecipe(ingotBismuthBronze, NUGGET + CUBI);
		addReverseStorageRecipe(ingotBismuthBronze, BLOCK + CUBI);
		addReverseStorageRecipe(nuggetBismuthBronze, INGOT + CUBI);
		addStorageRecipe(dustBismuthBronze, TINY_DUST + CUBI);
		addReverseStorageRecipe(tinyBismuthBronze, DUST + CUBI);
		addSmelting(dustBismuthBronze, ingotBismuthBronze);
		addSmelting(plateBismuthBronze, ingotBismuthBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateBismuthBronze, monorder, "A", "I", 'I', INGOT + CUBI, 'A', ALMNT);
		addGrindingRecipes(ingotBismuthBronze, dustBismuthBronze);
		addGrindingRecipes(plateBismuthBronze, dustBismuthBronze);

		addStorageRecipe(blockMithril, INGOT + MTHR);
		addStorageRecipe(ingotMithril, NUGGET + MTHR);
		addReverseStorageRecipe(ingotMithril, BLOCK + MTHR);
		addReverseStorageRecipe(nuggetMithril, INGOT + MTHR);
		addStorageRecipe(dustMithril, TINY_DUST + MTHR);
		addReverseStorageRecipe(tinyMithril, DUST + MTHR);
		addSmelting(dustMithril, ingotMithril);
		addSmelting(plateMithril, ingotMithril);
		addArcaneCraftingRecipe(keyAlumentum, plateMithril, monorder, "A", "I", 'I', INGOT + MTHR, 'A', ALMNT);
		addGrindingRecipes(ingotMithril, dustMithril);
		addGrindingRecipes(plateMithril, dustMithril);
		
		addStorageRecipe(blockAlumiuiumBronze, INGOT + CUAL);
		addStorageRecipe(ingotAluminiumBronze, NUGGET + CUAL);
		addReverseStorageRecipe(ingotAluminiumBronze, BLOCK + CUAL);
		addReverseStorageRecipe(nuggetAluminiumBronze, INGOT + CUAL);
		addStorageRecipe(dustAluminiumBronze, TINY_DUST + CUAL);
		addReverseStorageRecipe(tinyAluminiumBronze, DUST + CUAL);
		addSmelting(dustAluminiumBronze, ingotAluminiumBronze);
		addSmelting(plateAluminiumBronze, ingotAluminiumBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateAluminiumBronze, monorder, "A", "I", 'I', INGOT + CUAL, 'A', ALMNT);
		addGrindingRecipes(ingotAluminiumBronze, dustAluminiumBronze);
		addGrindingRecipes(plateAluminiumBronze, dustAluminiumBronze);

		addStorageRecipe(blockCupronickel, INGOT + CUNI);
		addStorageRecipe(ingotCupronickel, NUGGET + CUNI);
		addReverseStorageRecipe(ingotCupronickel, BLOCK + CUNI);
		addReverseStorageRecipe(nuggetCupronickel, INGOT + CUNI);
		addStorageRecipe(dustCupronickel, TINY_DUST + CUNI);
		addReverseStorageRecipe(tinyCupronickel, DUST + CUNI);
		addSmelting(dustCupronickel, ingotCupronickel);
		addSmelting(plateCupronickel, ingotCupronickel);
		addArcaneCraftingRecipe(keyAlumentum, plateCupronickel, monorder, "A", "I", 'I', INGOT + CUNI, 'A', ALMNT);
		addGrindingRecipes(ingotCupronickel, dustCupronickel);
		addGrindingRecipes(plateCupronickel, dustCupronickel);

		addStorageRecipe(blockRiftishBronze, INGOT + RBRZ);
		addStorageRecipe(ingotRiftishBronze, NUGGET + RBRZ);
		addReverseStorageRecipe(ingotRiftishBronze, BLOCK + RBRZ);
		addReverseStorageRecipe(nuggetRiftishBronze, INGOT + RBRZ);
		addStorageRecipe(dustRiftishBronze, TINY_DUST + RBRZ);
		addReverseStorageRecipe(tinyRiftishBronze, DUST + RBRZ);
		addSmelting(dustRiftishBronze, ingotRiftishBronze);
		addSmelting(plateRiftishBronze, ingotRiftishBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateRiftishBronze, monorder, "A", "I", 'I', INGOT + RBRZ, 'A', ALMNT);
		addGrindingRecipes(ingotRiftishBronze, dustRiftishBronze);
		addGrindingRecipes(plateRiftishBronze, dustRiftishBronze);

		addStorageRecipe(blockConstantan, INGOT + CNST);
		addStorageRecipe(ingotConstantan, NUGGET + CNST);
		addReverseStorageRecipe(ingotConstantan, BLOCK + CNST);
		addReverseStorageRecipe(nuggetConstantan, INGOT + CNST);
		addStorageRecipe(dustConstantan, TINY_DUST + CNST);
		addReverseStorageRecipe(tinyConstantan, DUST + CNST);
		addSmelting(dustConstantan, ingotConstantan);
		addSmelting(plateConstantan, ingotConstantan);
		addArcaneCraftingRecipe(keyAlumentum, plateConstantan, monorder, "A", "I", 'I', INGOT + CNST, 'A', ALMNT);
		addGrindingRecipes(ingotConstantan, dustConstantan);
		addGrindingRecipes(plateConstantan, dustConstantan);

		addStorageRecipe(blockInvar, INGOT + FENI);
		addStorageRecipe(ingotInvar, NUGGET + FENI);
		addReverseStorageRecipe(ingotInvar, BLOCK + FENI);
		addReverseStorageRecipe(nuggetInvar, INGOT + FENI);
		addStorageRecipe(dustInvar, TINY_DUST + FENI);
		addReverseStorageRecipe(tinyInvar, DUST + FENI);
		addSmelting(dustInvar, ingotInvar);
		addSmelting(plateInvar, ingotInvar);
		addArcaneCraftingRecipe(keyAlumentum, plateInvar, monorder, "A", "I", 'I', INGOT + FENI, 'A', ALMNT);
		addGrindingRecipes(ingotInvar, dustInvar);
		addGrindingRecipes(plateInvar, dustInvar);

		addStorageRecipe(blockElectrum, INGOT + AUAG);
		addStorageRecipe(ingotElectrum, NUGGET + AUAG);
		addReverseStorageRecipe(ingotElectrum, BLOCK + AUAG);
		addReverseStorageRecipe(nuggetElectrum, INGOT + AUAG);
		addStorageRecipe(dustElectrum, TINY_DUST + AUAG);
		addReverseStorageRecipe(tinyElectrum, DUST + AUAG);
		addSmelting(dustElectrum, ingotElectrum);
		addSmelting(plateElectrum, ingotElectrum);
		addArcaneCraftingRecipe(keyAlumentum, plateElectrum, monorder, "A", "I", 'I', INGOT + AUAG, 'A', ALMNT);
		addGrindingRecipes(ingotElectrum, dustElectrum);
		addGrindingRecipes(plateElectrum, dustElectrum);

		addStorageRecipe(blockWardenicMetal, INGOT + WRDM);
		addStorageRecipe(ingotWardenicMetal, NUGGET + WRDM);
		addReverseStorageRecipe(ingotWardenicMetal, BLOCK + WRDM);
		addReverseStorageRecipe(nuggetWardenicMetal, INGOT + WRDM);
		addStorageRecipe(dustWardenicMetal, TINY_DUST + WRDM);
		addReverseStorageRecipe(tinyWardenicMetal, DUST + WRDM);
		addSmelting(dustWardenicMetal, ingotWardenicMetal);
		addSmelting(plateWardenicMetal, ingotWardenicMetal);
		addArcaneCraftingRecipe(keyAlumentum, plateWardenicMetal, monorder, "A", "I", 'I', INGOT + WRDM, 'A', ALMNT);
		addGrindingRecipes(ingotWardenicMetal, dustWardenicMetal);
		addGrindingRecipes(plateWardenicMetal, dustWardenicMetal);

		addStorageRecipe(blockDullRedsolder, INGOT + DRDS);
		addStorageRecipe(ingotDullRedsolder, NUGGET + DRDS);
		addReverseStorageRecipe(ingotDullRedsolder, BLOCK + DRDS);
		addReverseStorageRecipe(nuggetDullRedsolder, INGOT + DRDS);
		addStorageRecipe(dustDullRedsolder, TINY_DUST + DRDS);
		addReverseStorageRecipe(tinyDullRedsolder, DUST + DRDS);
		addSmelting(dustDullRedsolder, ingotDullRedsolder);
		addSmelting(plateDullRedsolder, ingotDullRedsolder);
		addArcaneCraftingRecipe(keyAlumentum, plateDullRedsolder, monorder, "A", "I", 'I', INGOT + DRDS, 'A', ALMNT);
		addGrindingRecipes(ingotDullRedsolder, dustDullRedsolder);
		addGrindingRecipes(plateDullRedsolder, dustDullRedsolder);

		addStorageRecipe(blockRedsolder, INGOT + RDSR);
		addStorageRecipe(ingotRedsolder, NUGGET + RDSR);
		addReverseStorageRecipe(ingotRedsolder, BLOCK + RDSR);
		addReverseStorageRecipe(nuggetRedsolder, INGOT + RDSR);
		addStorageRecipe(dustRedsolder, TINY_DUST + RDSR);
		addReverseStorageRecipe(tinyRedsolder, DUST + RDSR);
		addSmelting(dustRedsolder, ingotRedsolder);
		addSmelting(plateRedsolder, ingotRedsolder);
		addArcaneCraftingRecipe(keyAlumentum, plateRedsolder, monorder, "A", "I", 'I', INGOT + RDSR, 'A', ALMNT);
		addGrindingRecipes(ingotRedsolder, dustRedsolder);
		addGrindingRecipes(plateRedsolder, dustRedsolder);
	}

	public static void addSpecialAlloyMetalRecipes() {
		addStorageRecipe(blockThaumicElectrum, INGOT + TELC);
		addStorageRecipe(ingotThaumicElectrum, NUGGET + TELC);
		addReverseStorageRecipe(ingotThaumicElectrum, BLOCK + TELC);
		addReverseStorageRecipe(nuggetThaumicElectrum, INGOT + TELC);
		addStorageRecipe(dustThaumicElectrum, TINY_DUST + TELC);
		addReverseStorageRecipe(tinyThaumicElectrum, DUST + TELC);
		addSmelting(dustThaumicElectrum, ingotThaumicElectrum);
		addSmelting(plateThaumicElectrum, ingotThaumicElectrum);
		addArcaneCraftingRecipe(keyAlumentum, plateThaumicElectrum, monorder, "A", "I", 'I', INGOT + TELC, 'A', ALMNT);
		addGrindingRecipes(ingotThaumicElectrum, dustThaumicElectrum);
		addGrindingRecipes(plateThaumicElectrum, dustThaumicElectrum);

		addStorageRecipe(blockThaumicRiftishBronze, INGOT + TRBR);
		addStorageRecipe(ingotThaumicRiftishBronze, NUGGET + TRBR);
		addReverseStorageRecipe(ingotThaumicRiftishBronze, BLOCK + TRBR);
		addReverseStorageRecipe(nuggetThaumicRiftishBronze, INGOT + TRBR);
		addStorageRecipe(dustThaumicRiftishBronze, TINY_DUST + TRBR);
		addReverseStorageRecipe(tinyThaumicRiftishBronze, DUST + TRBR);
		addSmelting(dustThaumicRiftishBronze, ingotThaumicRiftishBronze);
		addSmelting(plateThaumicRiftishBronze, ingotThaumicRiftishBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateThaumicRiftishBronze, monorder, "A", "I", "A", 'I', INGOT + TRBR, 'A', ALMNT);
		addGrindingRecipes(ingotThaumicRiftishBronze, dustThaumicRiftishBronze);
		addGrindingRecipes(plateThaumicRiftishBronze, dustThaumicRiftishBronze);

		addStorageRecipe(blockSteel, INGOT + STEL);
		addStorageRecipe(ingotSteel, NUGGET + STEL);
		addReverseStorageRecipe(ingotSteel, BLOCK + STEL);
		addReverseStorageRecipe(nuggetSteel, INGOT + STEL);
		addStorageRecipe(dustSteel, TINY_DUST + STEL);
		addReverseStorageRecipe(tinySteel, DUST + STEL);
		addSmelting(dustSteel, ingotSteel);
		addSmelting(plateSteel, ingotSteel);
		addArcaneCraftingRecipe(keyAlumentum, plateSteel, monorder, "A", "I", "A", 'I', INGOT + STEL, 'A', ALMNT);
		addGrindingRecipes(ingotSteel, dustSteel);
		addGrindingRecipes(plateSteel, dustSteel);

		addStorageRecipe(blockThaumicSteel, INGOT + TSTL);
		addStorageRecipe(ingotThaumicSteel, NUGGET + TSTL);
		addReverseStorageRecipe(ingotThaumicSteel, BLOCK + TSTL);
		addReverseStorageRecipe(nuggetThaumicSteel, INGOT + TSTL);
		addStorageRecipe(dustThaumicSteel, TINY_DUST + TSTL);
		addReverseStorageRecipe(tinyThaumicSteel, DUST + TSTL);
		addSmelting(dustThaumicSteel, ingotThaumicSteel);
		addSmelting(plateThaumicSteel, ingotThaumicSteel);
		addArcaneCraftingRecipe(keyAlumentum, plateThaumicSteel, monorder, "A", "I", "A", 'I', INGOT + TSTL, 'A', ALMNT);
		addGrindingRecipes(ingotThaumicSteel, dustThaumicSteel);
		addGrindingRecipes(plateThaumicSteel, dustThaumicSteel);

		addStorageRecipe(blockVoidbrass, INGOT + VBRS);
		addStorageRecipe(ingotVoidbrass, NUGGET + VBRS);
		addReverseStorageRecipe(ingotVoidbrass, BLOCK + VBRS);
		addReverseStorageRecipe(nuggetVoidbrass, INGOT + VBRS);
		addStorageRecipe(dustVoidbrass, TINY_DUST + VBRS);
		addReverseStorageRecipe(tinyVoidbrass, DUST + VBRS);
		addSmelting(dustVoidbrass, ingotVoidbrass);
		addSmelting(plateVoidbrass, ingotVoidbrass);
		addArcaneCraftingRecipe(keyAlumentum, plateVoidbrass, monorder, "A", "I", "A", 'I', INGOT + VBRS, 'A', ALMNT);
		addGrindingRecipes(ingotVoidbrass, dustVoidbrass);
		addGrindingRecipes(plateVoidbrass, dustVoidbrass);

		addStorageRecipe(blockVoidsteel, INGOT + VSTL);
		addStorageRecipe(ingotVoidsteel, NUGGET + VSTL);
		addReverseStorageRecipe(ingotVoidsteel, BLOCK + VSTL);
		addReverseStorageRecipe(nuggetVoidsteel, INGOT + VSTL);
		addStorageRecipe(dustVoidsteel, TINY_DUST + VSTL);
		addReverseStorageRecipe(tinyVoidsteel, DUST + VSTL);
		addSmelting(dustVoidsteel, ingotVoidsteel);
		addSmelting(plateVoidsteel, ingotVoidsteel);
		addArcaneCraftingRecipe(keyAlumentum, plateVoidsteel, monorder, " A ", " I ", "A A", 'I', INGOT + VSTL, 'A', ALMNT);
		addGrindingRecipes(ingotVoidsteel, dustVoidsteel);
		addGrindingRecipes(plateVoidsteel, dustVoidsteel);

		addStorageRecipe(blockVoidtungsten, INGOT + VDWT);
		addStorageRecipe(ingotVoidtungsten, NUGGET + VDWT);
		addReverseStorageRecipe(ingotVoidtungsten, BLOCK + VDWT);
		addReverseStorageRecipe(nuggetVoidtungsten, INGOT + VDWT);
		addStorageRecipe(dustVoidtungsten, TINY_DUST + VDWT);
		addReverseStorageRecipe(tinyVoidtungsten, DUST + VDWT);
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateVoidtungsten, monorder, "AAA", "AIA", "AAA", 'I', INGOT + VDWT, 'A', ALMNT);
		addGrindingRecipes(ingotVoidtungsten, dustVoidtungsten);
		addGrindingRecipes(plateVoidtungsten, dustVoidtungsten);

		addStorageRecipe(blockVoidcupronickel, INGOT + VCPN);
		addStorageRecipe(ingotVoidcupronickel, NUGGET + VCPN);
		addReverseStorageRecipe(ingotVoidcupronickel, BLOCK + VCPN);
		addReverseStorageRecipe(nuggetVoidcupronickel, INGOT + VCPN);
		addStorageRecipe(dustVoidcupronickel, TINY_DUST + VCPN);
		addReverseStorageRecipe(tinyVoidcupronickel, DUST + VCPN);
		addSmelting(dustVoidcupronickel, ingotVoidcupronickel);
		addSmelting(plateVoidcupronickel, ingotVoidcupronickel);
		addArcaneCraftingRecipe(keyAlumentum, plateVoidcupronickel, monorder, "A", "I", 'I', INGOT + VCPN, 'A', ALMNT);
		addGrindingRecipes(ingotVoidcupronickel, dustVoidcupronickel);
		addGrindingRecipes(plateVoidcupronickel, dustVoidcupronickel);
	}

	public static void addEquipmentAlloyMetalRecipes() {
		addStorageRecipe(blockWardenicBronze, INGOT + WBRZ);
		addStorageRecipe(ingotWardenicBronze, NUGGET + WBRZ);
		addReverseStorageRecipe(ingotWardenicBronze, BLOCK + WBRZ);
		addReverseStorageRecipe(nuggetWardenicBronze, INGOT + WBRZ);
		addStorageRecipe(dustWardenicBronze, TINY_DUST + WBRZ);
		addReverseStorageRecipe(tinyWardenicBronze, DUST + WBRZ);
		addSmelting(dustWardenicBronze, ingotWardenicBronze);
		addSmelting(plateWardenicBronze, ingotWardenicBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateWardenicBronze, monorder, "A", "I", 'I', INGOT + WBRZ, 'A', ALMNT);
		addGrindingRecipes(ingotWardenicBronze, dustWardenicBronze);
		addGrindingRecipes(plateWardenicBronze, dustWardenicBronze);

		addStorageRecipe(blockWardenicSteel, INGOT + WDST);
		addStorageRecipe(ingotWardenicSteel, NUGGET + WDST);
		addReverseStorageRecipe(ingotWardenicSteel, BLOCK + WDST);
		addReverseStorageRecipe(nuggetWardenicSteel, INGOT + WDST);
		addStorageRecipe(dustWardenicSteel, TINY_DUST + WDST);
		addReverseStorageRecipe(tinyWardenicSteel, DUST + WDST);
		addSmelting(dustWardenicSteel, ingotWardenicSteel);
		addSmelting(plateWardenicSteel, ingotWardenicSteel);
		recipeWardenSteelPlate = addArcaneCraftingRecipe(keyWardenPlate, plateWardenicSteel, monorder, " A ", "AIA", " A ", 'A', itemAlumentum, 'I', INGOT + WDST); //Special
		addGrindingRecipes(ingotWardenicSteel, dustWardenicSteel);
		addGrindingRecipes(plateWardenicSteel, dustWardenicSteel);

		addStorageRecipe(blockWardenicRiftishBronze, INGOT + WRBR);
		addStorageRecipe(ingotWardenicRiftishBronze, NUGGET + WRBR);
		addReverseStorageRecipe(ingotWardenicRiftishBronze, BLOCK + WRBR);
		addReverseStorageRecipe(nuggetWardenicRiftishBronze, INGOT + WRBR);
		addStorageRecipe(dustWardenicRiftishBronze, TINY_DUST + WRBR);
		addReverseStorageRecipe(tinyWardenicRiftishBronze, DUST + WRBR);
		addSmelting(dustWardenicRiftishBronze, ingotWardenicRiftishBronze);
		addSmelting(plateWardenicRiftishBronze, ingotWardenicRiftishBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateWardenicRiftishBronze, monorder, " A ", " I ", "A A", 'A', itemAlumentum, 'I', INGOT + WRBR);
		addGrindingRecipes(ingotWardenicRiftishBronze, dustWardenicRiftishBronze);
		addGrindingRecipes(plateWardenicRiftishBronze, dustWardenicRiftishBronze);

		addStorageRecipe(blockWardenicComposite, INGOT + WCMP);
		addStorageRecipe(ingotWardenicComposite, NUGGET + WCMP);
		addReverseStorageRecipe(ingotWardenicComposite, BLOCK + WCMP);
		addReverseStorageRecipe(nuggetWardenicComposite, INGOT + WCMP);
		addStorageRecipe(dustWardenicComposite, TINY_DUST + WCMP);
		addReverseStorageRecipe(tinyWardenicComposite, DUST + WCMP);
		addSmelting(dustWardenicComposite, smeltedWardenicComposite);
		addSmelting(plateWardenicComposite, smeltedWardenicComposite);
		recipeWardenicCompositePlate = addArcaneCraftingRecipe(keyWardenCompositePlate, plateWardenicComposite, new AspectList().add(ORDER, 1), " A ", "AIA", " A ", 'A', aluDenseTemp, 'I', INGOT + WCMP); //Special
		addGrindingRecipes(ingotWardenicComposite, dustWardenicComposite);
		addGrindingRecipes(plateWardenicComposite, dustWardenicComposite);

		addStorageRecipe(blockArcaneRedsolder, INGOT + ARDS);
		addStorageRecipe(ingotArcaneRedsolder, NUGGET + ARDS);
		addReverseStorageRecipe(ingotArcaneRedsolder, BLOCK + ARDS);
		addReverseStorageRecipe(nuggetArcaneRedsolder, INGOT + ARDS);
		addStorageRecipe(dustArcaneRedsolder, TINY_DUST + ARDS);
		addReverseStorageRecipe(tinyArcaneRedsolder, DUST + ARDS);
		addSmelting(dustArcaneRedsolder, ingotArcaneRedsolder);
		addSmelting(plateArcaneRedsolder, ingotArcaneRedsolder);
		addArcaneCraftingRecipe(keyAlumentum, plateArcaneRedsolder, monorder, "A", "I", 'I', INGOT + ARDS, 'A', ALMNT); //TODO
		addGrindingRecipes(ingotArcaneRedsolder, dustArcaneRedsolder);
		addGrindingRecipes(plateArcaneRedsolder, dustArcaneRedsolder);

		addStorageRecipe(blockRedbronze, INGOT + RDBR);
		addStorageRecipe(ingotRedbronze, NUGGET + RDBR);
		addReverseStorageRecipe(ingotRedbronze, BLOCK + RDBR);
		addReverseStorageRecipe(nuggetRedbronze, INGOT + RDBR);
		addStorageRecipe(dustRedbronze, TINY_DUST + RDBR);
		addReverseStorageRecipe(tinyRedbronze, DUST + RDBR);
		addSmelting(dustRedbronze, ingotRedbronze);
		addSmelting(plateRedbronze, ingotRedbronze);
		addArcaneCraftingRecipe(keyAlumentum, plateRedbronze, monorder, "A", "I", "A", 'I', INGOT + RDBR, 'A', ALMNT);
		addGrindingRecipes(ingotRedbronze, dustRedbronze);
		addGrindingRecipes(plateRedbronze, dustRedbronze);

		addStorageRecipe(blockHardenedRedbronze, INGOT + HRBR);
		addStorageRecipe(ingotHardenedRedbronze, NUGGET + HRBR);
		addReverseStorageRecipe(ingotHardenedRedbronze, BLOCK + HRBR);
		addReverseStorageRecipe(nuggetHardenedRedbronze, INGOT + HRBR);
		addStorageRecipe(dustHardenedRedbronze, TINY_DUST + HRBR);
		addReverseStorageRecipe(tinyHardenedRedbronze, DUST + HRBR);
		addSmelting(dustHardenedRedbronze, ingotHardenedRedbronze);
		addSmelting(plateHardenedRedbronze, ingotHardenedRedbronze);
		addArcaneCraftingRecipe(keyAlumentum, plateHardenedRedbronze, monorder, " A ", "AIA", " A ", 'I', INGOT + HRBR, 'A', ALMNT);
		addGrindingRecipes(ingotHardenedRedbronze, dustHardenedRedbronze);
		addGrindingRecipes(plateHardenedRedbronze, dustHardenedRedbronze);

		addStorageRecipe(blockFluxsteel, INGOT + FSTL);
		addStorageRecipe(ingotFluxsteel, NUGGET + FSTL);
		addReverseStorageRecipe(ingotFluxsteel, BLOCK + FSTL);
		addReverseStorageRecipe(nuggetFluxsteel, INGOT + FSTL);
		addStorageRecipe(dustFluxsteel, TINY_DUST + FSTL);
		addReverseStorageRecipe(tinyFluxsteel, DUST + FSTL);
		addSmelting(dustFluxsteel, ingotFluxsteel);
		addSmelting(plateFluxsteel, ingotFluxsteel);
		addArcaneCraftingRecipe(keyAlumentum, plateFluxsteel, monorder, "A A", "AIA", " A ", 'I', INGOT + FSTL, 'A', ALMNT);
		addGrindingRecipes(ingotFluxsteel, dustFluxsteel);
		addGrindingRecipes(plateFluxsteel, dustFluxsteel);

		addStorageRecipe(blockFluxedTungsten, INGOT + FLXW);
		addStorageRecipe(ingotFluxedTungsten, NUGGET + FLXW);
		addReverseStorageRecipe(ingotFluxedTungsten, BLOCK + FLXW);
		addReverseStorageRecipe(nuggetFluxedTungsten, INGOT + FLXW);
		addStorageRecipe(dustFluxedTungsten, TINY_DUST + FLXW);
		addReverseStorageRecipe(tinyFluxedTungsten, DUST + FLXW);
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateFluxedTungsten, monorder, "AAA", "AIA", "AAA", 'I', INGOT + FLXW, 'A', ALMNT);
		addGrindingRecipes(ingotFluxedTungsten, dustFluxedTungsten);
		addGrindingRecipes(plateFluxedTungsten, dustFluxedTungsten);

		addStorageRecipe(blockMagneoturgicComposite, INGOT + MCMP);
		addStorageRecipe(ingotMagneoturgicComposite, NUGGET + MCMP);
		addReverseStorageRecipe(ingotMagneoturgicComposite, BLOCK + MCMP);
		addReverseStorageRecipe(nuggetMagneoturgicComposite, INGOT + MCMP);
		addStorageRecipe(dustMagneoturgicComposite, TINY_DUST + MCMP);
		addReverseStorageRecipe(tinyMagneoturgicComposite, DUST + MCMP);
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateMagneoturgicComposite, monorder, " A ", " I ", "A A", 'I', INGOT + MCMP, 'A', aluDenseTemp);
		addGrindingRecipes(ingotMagneoturgicComposite, dustMagneoturgicComposite);
		addGrindingRecipes(plateMagneoturgicComposite, dustMagneoturgicComposite);

		addStorageRecipe(blockFluxedComposite, INGOT + FCMP);
		addStorageRecipe(ingotFluxedComposite, NUGGET + FCMP);
		addReverseStorageRecipe(ingotFluxedComposite, BLOCK + FCMP);
		addReverseStorageRecipe(nuggetFluxedComposite, INGOT + FCMP);
		addStorageRecipe(dustFluxedComposite, TINY_DUST + FCMP);
		addReverseStorageRecipe(tinyFluxedComposite, DUST + FCMP);
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateFluxedComposite, monorder, "A A", "AIA", " A ", 'I', INGOT + FCMP, 'A', aluDenseTemp);
		addGrindingRecipes(ingotFluxedComposite, dustFluxedComposite);
		addGrindingRecipes(plateFluxedComposite, dustFluxedComposite);

		addStorageRecipe(blockResonantFluxedComposite, INGOT + RCMP);
		addStorageRecipe(ingotResonantFluxedComposite, NUGGET + RCMP);
		addReverseStorageRecipe(ingotResonantFluxedComposite, BLOCK + RCMP);
		addReverseStorageRecipe(nuggetResonantFluxedComposite, INGOT + RCMP);
		addStorageRecipe(dustResonantFluxedComposite, TINY_DUST + RCMP);
		addReverseStorageRecipe(tinyResonantFluxedComposite, DUST + RCMP);
		//Too high melting point for regular furnace;
		addArcaneCraftingRecipe(keyAlumentum, plateResonantFluxedComposite, monorder, "AAA", "AIA", "AAA", 'I', INGOT + RCMP, 'A', aluDenseTemp);
		addGrindingRecipes(ingotResonantFluxedComposite, dustResonantFluxedComposite);
		addGrindingRecipes(plateResonantFluxedComposite, dustResonantFluxedComposite);

		addStorageRecipe(blockEmpoweredVoidbrass, INGOT + EVBS);
		addStorageRecipe(ingotEmpoweredVoidbrass, NUGGET + EVBS);
		addReverseStorageRecipe(ingotEmpoweredVoidbrass, BLOCK + EVBS);
		addReverseStorageRecipe(nuggetEmpoweredVoidbrass, INGOT + EVBS);
		addStorageRecipe(dustEmpoweredVoidbrass, TINY_DUST + EVBS);
		addReverseStorageRecipe(tinyEmpoweredVoidbrass, DUST + EVBS);
		addSmelting(dustEmpoweredVoidbrass, ingotEmpoweredVoidbrass);
		addSmelting(plateEmpoweredVoidbrass, ingotEmpoweredVoidbrass);
		addArcaneCraftingRecipe(keyAlumentum, plateEmpoweredVoidbrass, monorder, "A", "I", "A", 'I', INGOT + EVBS, 'A', ALMNT);
		addGrindingRecipes(ingotEmpoweredVoidbrass, dustEmpoweredVoidbrass);
		addGrindingRecipes(plateEmpoweredVoidbrass, dustEmpoweredVoidbrass);

		addStorageRecipe(blockCrimsonThaumium, INGOT + CTHM);
		addStorageRecipe(ingotCrimsonThaumium, NUGGET + CTHM);
		addReverseStorageRecipe(ingotCrimsonThaumium, BLOCK + CTHM);
		addReverseStorageRecipe(nuggetCrimsonThaumium, INGOT + CTHM);
		addStorageRecipe(dustCrimsonThaumium, TINY_DUST + CTHM);
		addReverseStorageRecipe(tinyCrimsonThaumium, DUST + CTHM);
		addSmelting(dustCrimsonThaumium, ingotCrimsonThaumium);
		addSmelting(plateCrimsonThaumium, ingotCrimsonThaumium);
		addArcaneCraftingRecipe(keyAlumentum, plateCrimsonThaumium, monorder, " A ", "AIA", " A ", 'I', INGOT + CTHM, 'A', ALMNT);
		addGrindingRecipes(ingotCrimsonThaumium, dustCrimsonThaumium);
		addGrindingRecipes(plateCrimsonThaumium, dustCrimsonThaumium);

		addStorageRecipe(blockOccultVoidtungsten, INGOT + OCVW);
		addStorageRecipe(ingotOccultVoidtungsten, NUGGET + OCVW);
		addReverseStorageRecipe(ingotOccultVoidtungsten, BLOCK + OCVW);
		addReverseStorageRecipe(nuggetOccultVoidtungsten, INGOT + OCVW);
		addStorageRecipe(dustOccultVoidtungsten, TINY_DUST + OCVW);
		addReverseStorageRecipe(tinyOccultVoidtungsten, DUST + OCVW);
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateOccultVoidtungsten, monorder, " A ", "AIA", " A ", 'I', INGOT + OCVW, 'A', aluDenseTemp);
		addGrindingRecipes(ingotOccultVoidtungsten, dustOccultVoidtungsten);
		addGrindingRecipes(plateOccultVoidtungsten, dustOccultVoidtungsten);
	}

	public static void addGemRecipes() {
		addStorageRecipe(blockPyrope, GEM + PYRP);
		addStorageRecipe(gemPyrope, NUGGET + PYRP);
		addReverseStorageRecipe(gemPyrope, BLOCK + PYRP);
		addReverseStorageRecipe(shardPyrope, GEM + PYRP);
		addStorageRecipe(dustPyrope, TINY_DUST + PYRP);
		addReverseStorageRecipe(tinyPyrope, DUST + PYRP);
		addSmelting(dustPyrope, gemPyrope);
		addGrindingRecipes(gemPyrope, dustPyrope);

		addStorageRecipe(blockDioptase, GEM + DIOP);
		addStorageRecipe(gemDioptase, NUGGET + DIOP);
		addReverseStorageRecipe(gemDioptase, BLOCK + DIOP);
		addReverseStorageRecipe(shardDioptase, GEM + DIOP);
		addStorageRecipe(dustDioptase, TINY_DUST + DIOP);
		addReverseStorageRecipe(tinyDioptase, DUST + DIOP);
		addSmelting(dustDioptase, gemDioptase);
		addGrindingRecipes(gemDioptase, dustDioptase);

		addStorageRecipe(blockFluonicSapphire, GEM + FSPH);
		addStorageRecipe(gemFluonicSapphire, NUGGET + FSPH);
		addReverseStorageRecipe(gemFluonicSapphire, BLOCK + FSPH);
		addReverseStorageRecipe(shardFluonicSapphire, GEM + FSPH);
		addStorageRecipe(dustFluonicSapphire, TINY_DUST + FSPH);
		addReverseStorageRecipe(tinyFluonicSapphire, DUST + FSPH);
		addSmelting(dustFluonicSapphire, gemFluonicSapphire);
		addGrindingRecipes(gemFluonicSapphire, dustFluonicSapphire);

		addStorageRecipe(blockFluonicPyroptase, GEM + FPRT);
		addStorageRecipe(gemFluonicPyroptase, NUGGET + FPRT);
		addReverseStorageRecipe(gemFluonicPyroptase, BLOCK + FPRT);
		addReverseStorageRecipe(shardFluonicPyroptase, GEM + FPRT);
		addStorageRecipe(dustFluonicPyroptase, TINY_DUST + FPRT);
		addReverseStorageRecipe(tinyFluonicPyroptase, DUST + FPRT);
		addSmelting(dustFluonicPyroptase, gemFluonicPyroptase);
		addGrindingRecipes(gemFluonicPyroptase, dustFluonicPyroptase, 8);

		addStorageRecipe(blockWardenicCrystal, GEM + WCRS);
		addStorageRecipe(gemWardenicCrystal, NUGGET + WCRS);
		addReverseStorageRecipe(gemWardenicCrystal, BLOCK + WCRS);
		addReverseStorageRecipe(shardWardenicCrystal, GEM + WCRS);
		addStorageRecipe(dustWardenicCrystal, TINY_DUST + WCRS);
		addReverseStorageRecipe(tinyWardenicCrystal, DUST + WCRS);
		addSmelting(dustWardenicCrystal, gemWardenicCrystal);
		addGrindingRecipes(gemWardenicCrystal, dustWardenicCrystal, 8);

		addStorageRecipe(blockActivatedWardenicCrystal, GEM + AWCR);
		addStorageRecipe(gemActivatedWardenicCrystal, NUGGET + AWCR);
		addReverseStorageRecipe(gemActivatedWardenicCrystal, BLOCK + AWCR);
		addReverseStorageRecipe(shardActivatedWardenicCrystal, GEM + AWCR);
		addStorageRecipe(dustActivatedWardenicCrystal, TINY_DUST + AWCR);
		addReverseStorageRecipe(tinyActivatedWardenicCrystal, DUST + AWCR);
		addSmelting(dustActivatedWardenicCrystal, gemActivatedWardenicCrystal);
		addGrindingRecipes(gemActivatedWardenicCrystal, dustActivatedWardenicCrystal, 8);

		addStorageRecipe(blockAwakenedWardenicCrystal, GEM + WWCR);
		addStorageRecipe(gemAwakenedWardenicCrystal, NUGGET + WWCR);
		addReverseStorageRecipe(gemAwakenedWardenicCrystal, BLOCK + WWCR);
		addReverseStorageRecipe(shardAwakenedWardenicCrystal, GEM + WWCR);
		addStorageRecipe(dustAwakenedWardenicCrystal, TINY_DUST + WWCR);
		addReverseStorageRecipe(tinyAwakenedWardenicCrystal, DUST + WWCR);
		addSmelting(dustAwakenedWardenicCrystal, gemAwakenedWardenicCrystal);
		addGrindingRecipes(gemAwakenedWardenicCrystal, dustAwakenedWardenicCrystal, 10);

		recipeQuartzBlock = addSquareRecipe(blockWardenicQuartz, GEM + WQRZ);
		addStorageRecipe(gemWardenicQuartz, NUGGET + WQRZ);
		recipeQuartzDeblock = addDeblockingRecipe(gemWardenicQuartz, blockWardenicQuartz); //Special
		addReverseStorageRecipe(shardWardenicQuartz, GEM + WQRZ);
		addStorageRecipe(dustWardenicQuartz, TINY_DUST + WQRZ);
		addReverseStorageRecipe(tinyWardenicQuartz, DUST + WQRZ);
		addSmelting(dustWardenicQuartz, gemWardenicQuartz);
		addGrindingRecipes(gemWardenicQuartz, dustWardenicQuartz, 4);

		addSquareRecipe(blockInfusedQuartz, GEM + IQRZ);
		addStorageRecipe(gemInfusedQuartz, NUGGET + IQRZ);
		addDeblockingRecipe(gemInfusedQuartz, blockInfusedQuartz); //Special
		addReverseStorageRecipe(shardInfusedQuartz, GEM + IQRZ);
		addStorageRecipe(dustInfusedQuartz, TINY_DUST + IQRZ);
		addReverseStorageRecipe(tinyInfusedQuartz, DUST + IQRZ);
		addSmelting(dustInfusedQuartz, gemInfusedQuartz);
		addGrindingRecipes(gemInfusedQuartz, dustInfusedQuartz, 5);

		addSquareRecipe(blockRedquartz, GEM + RQZT);
		addStorageRecipe(gemRedquartz, NUGGET + RQZT);
		addDeblockingRecipe(gemRedquartz, blockRedquartz); //Special
		addReverseStorageRecipe(shardRedquartz, GEM + RQZT);
		addStorageRecipe(dustRedquartz, TINY_DUST + RQZT);
		addReverseStorageRecipe(tinyRedquartz, DUST + RQZT);
		addSmelting(dustRedquartz, gemRedquartz);
		addGrindingRecipes(gemRedquartz, dustRedquartz, 4);
	}
	
	public static void addMiscMetalRecipes() {
		addStorageRecipe(blockLanthanides, INGOT + LNTH);
		addStorageRecipe(ingotLanthanides, NUGGET + LNTH);
		addReverseStorageRecipe(ingotLanthanides, BLOCK + LNTH);
		addReverseStorageRecipe(nuggetLanthanides, INGOT + LNTH);
		addStorageRecipe(dustLanthanides, TINY_DUST + LNTH);
		addReverseStorageRecipe(tinyLanthanides, DUST + LNTH);
		addSmelting(dustLanthanides, ingotLanthanides);
		addGrindingRecipes(ingotLanthanides, dustLanthanides);

		addStorageRecipe(blockXenotimeJunk, INGOT + YPOJ);
		addStorageRecipe(ingotXenotimeJunk, NUGGET + YPOJ);
		addReverseStorageRecipe(ingotXenotimeJunk, BLOCK + YPOJ);
		addReverseStorageRecipe(nuggetXenotimeJunk, INGOT + YPOJ);
		addStorageRecipe(dustXenotimeJunk, TINY_DUST + YPOJ);
		addReverseStorageRecipe(tinyXenotimeJunk, DUST + YPOJ);
		addSmelting(dustXenotimeJunk, ingotXenotimeJunk);
		addGrindingRecipes(ingotXenotimeJunk, dustXenotimeJunk);
		
		addStorageRecipe(blockIridosmium, INGOT + IROS);
		addStorageRecipe(ingotIridosmium, NUGGET + IROS);
		addReverseStorageRecipe(ingotIridosmium, BLOCK + IROS);
		addReverseStorageRecipe(nuggetIridosmium, INGOT + IROS);
		addStorageRecipe(dustIridosmium, TINY_DUST + IROS);
		addReverseStorageRecipe(tinyIridosmium, DUST + IROS);
		//Too high melting point for regular furnace
		addGrindingRecipes(ingotIridosmium, dustIridosmium);

		addStorageRecipe(blockThaumicBronze, INGOT + TBRZ);
		addStorageRecipe(ingotThaumicBronze, NUGGET + TBRZ);
		addReverseStorageRecipe(ingotThaumicBronze, BLOCK + TBRZ);
		addReverseStorageRecipe(nuggetThaumicBronze, INGOT + TBRZ);
		addStorageRecipe(dustThaumicBronze, TINY_DUST + TBRZ);
		addReverseStorageRecipe(tinyThaumicBronze, DUST + TBRZ);
		addSmelting(dustThaumicBronze, ingotThaumicBronze);
		addSmelting(plateThaumicBronze, ingotThaumicBronze);
		addArcaneCraftingRecipe(keyAlumentum, plateThaumicBronze, monorder, "A", "I", 'I', INGOT + TBRZ, 'A', ALMNT);
		addGrindingRecipes(ingotThaumicBronze, dustThaumicBronze);
		addGrindingRecipes(plateThaumicBronze, dustThaumicBronze);

		addStorageRecipe(blockOsmiumLutetium, INGOT + OSLU);
		addStorageRecipe(ingotOsmiumLutetium, NUGGET + OSLU);
		addReverseStorageRecipe(ingotOsmiumLutetium, BLOCK + OSLU);
		addReverseStorageRecipe(nuggetOsmiumLutetium, INGOT + OSLU);
		addStorageRecipe(dustOsmiumLutetium, TINY_DUST + OSLU);
		addReverseStorageRecipe(tinyOsmiumLutetium, DUST + OSLU);
		//Too high melting point for regular furnace
		addArcaneCraftingRecipe(keyAlumentum, plateOsmiumLutetium, monorder, "A A", "AIA", " A ", 'I', INGOT + OSLU, 'A', ALMNT);
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
		RecipeHelper.addArcaneCraftingRecipe(keyAlumentum, plate$Mineral, monorder, "A", "I", 'I', "ingot$MineralOre", ALMNT);
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
		recipeWardenMetal[0] = addShapelessRecipe(cloneStack(rawWardenicMetal, 9), INGOT + THMM, INGOT + THMM, INGOT + THMM, INGOT + PD, DUST + WDBC, INGOT + CUZN, INGOT + AUAG, INGOT + ZN, HG_TC);
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
		recipeWardenMetal[1] = addShapelessRecipe(cloneStack(dustWardenicMetal, 9), DUST + THMM, DUST + THMM, DUST + THMM, DUST + PD, DUST + WDBC, DUST + CUZN, DUST + AUAG, DUST + ZN, HG_TC);
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
		addShapelessRecipe(dustWardenicMetal, TINY_DUST + THMM, TINY_DUST + THMM, TINY_DUST + THMM, TINY_DUST + PD, TINY_DUST + WDBC, TINY_DUST + CUZN, TINY_DUST + AUAG, TINY_DUST + ZN, "itemDropQuicksilver");
		//Dull Redsolder
		//Redsolder

		if (LoadedHelper.isThermalExpansionLoaded) {
			addInductionAlloyRecipe(CU, 3, ZN, 1, ingotBrass);
			addInductionAlloyRecipe(CU, 3, AS, 1, ingotArsenicalBronze);
			addInductionAlloyRecipe(CU, 3, SB, 1, ingotAntimonialBronze);
			addInductionAlloyRecipe(CUAS, 1, CUSB, 1, ingotMithril);
			addInductionAlloyRecipe(CU, ThaumRevConfig.backwardsAlBronze ? 1 : 3, AL, ThaumRevConfig.backwardsAlBronze ? 3 : 1, ingotAluminiumBronze);
			addInductionAlloyRecipe(CU, 3, NI, 1, ingotCupronickel);
		}
	}

	public static ResearchPage[] getMaterialPages() {
		List<ResearchPage> list = new ArrayList<ResearchPage>(8);
		list.add(new ResearchPage("0"));

		List<ItemStack> ores = new ArrayList<ItemStack>(9);
		//if (ThaumRevConfig.generateSphalerite) {
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
		triggers.addAll(OreDictionary.getOres(INGOT + PT));
		triggers.addAll(OreDictionary.getOres(NUGGET + PT));
		triggers.addAll(OreDictionary.getOres(DUST + PT));
		triggers.addAll(OreDictionary.getOres(BLOCK + PT));
		triggers.addAll(OreDictionary.getOres(TINY_DUST + PT));

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

		// They like a decent amount of water, but not too much
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
