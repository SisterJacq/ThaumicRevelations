package mortvana.thaumrev.util;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.*;

import mortvana.thaumrev.melteddashboard.util.helpers.ItemHelper;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.*;

public class RecipeHelper {

	public static void addRecipe(IRecipe recipe) {
		GameRegistry.addRecipe(recipe);
	}

	//Shaped Crafting
	public static IRecipe addShapedRecipe(ItemStack output, Object... stuff) {
		ShapedOreRecipe r = new ShapedOreRecipe(output, stuff);
		GameRegistry.addRecipe(r);
		return r;
	}

	public static IRecipe addSquareRecipe(ItemStack input, ItemStack output) {
		return addShapedRecipe(output, "##", "##", '#', input);
	}

	//Shapeless Crafting
	public static ShapelessOreRecipe addShapelessRecipe(ItemStack output, String... stuff) {
		ShapelessOreRecipe r = new ShapelessOreRecipe(output, stuff);
		GameRegistry.addRecipe(r);
		return r;
	}

	public static ShapelessOreRecipe addShapelessSizedOreRecipe(ItemStack input, int modifier, String... stuff) {
		return addShapelessRecipe(ItemHelper.cloneStack(input, stuff.length + modifier), stuff);
	}

	public static ShapelessOreRecipe generateShapelessSizedOreRecipe(ItemStack input, int modifier, String... stuff) {
		return new ShapelessOreRecipe(ItemHelper.cloneStack(input, stuff.length + modifier), stuff);
	}

	public static ShapelessOreRecipe addStorageRecipe(ItemStack result, String oreDict) {
		return addShapelessRecipe(result, oreDict, oreDict, oreDict, oreDict, oreDict, oreDict, oreDict, oreDict, oreDict);
	}

	public static ShapelessOreRecipe addReverseStorageRecipe(ItemStack result, String oreDict) {
		return addShapelessRecipe(ItemHelper.cloneStack(result, 9), oreDict);
	}

	//Smelting
	public static void addSmelting(ItemStack input, ItemStack output, float experience) {
		FurnaceRecipes.smelting().func_151394_a(input, output, experience);
	}

	//OreDicting
	public static void registerOreDict(ItemStack itemstack, String... oreDict) {
		for (String name : oreDict) {
			OreDictionary.registerOre(name, itemstack);
		}
	}

	//FMP
	public static void registerFMP(Block block, int maxMeta) {
		for (int i = 0; i < maxMeta; i++) {
			registerFMP(new ItemStack(block, 1, i));
		}
	}

	public static void registerFMP(ItemStack itemstack) {
		FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", itemstack);
	}

	//Thaumcraft
	public static ShapedArcaneRecipe addArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object ... recipe) {
		return ThaumcraftApi.addArcaneCraftingRecipe(research, result, aspects, recipe);
	}

	public static ShapelessArcaneRecipe addShapelessArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object ... recipe){
		return ThaumcraftApi.addShapelessArcaneCraftingRecipe(research, result, aspects, recipe);
	}

	public static InfusionRecipe addInfusionCraftingRecipe(String research, Object result, int instability, AspectList aspects, ItemStack input, ItemStack[] recipe) {
		return ThaumcraftApi.addInfusionCraftingRecipe(research, result, instability, aspects, input, recipe);
	}

	public static InfusionRecipe[] addInfusionCraftingRecipes(String research, Object result, int instability, AspectList aspects, String input, ItemStack[] recipe) {
		ArrayList<InfusionRecipe> recipes = new ArrayList<InfusionRecipe>();
		for (ItemStack stack : OreDictionary.getOres(input)) {
			recipes.add(addInfusionCraftingRecipe(research, result, instability, aspects, stack, recipe));
		}
		InfusionRecipe[] r = new InfusionRecipe[recipes.size()];
		recipes.toArray(r);
		return r;
	}

	public static CrucibleRecipe addCrucibleRecipe(String key, ItemStack result, Object catalyst, AspectList tags) {
		return ThaumcraftApi.addCrucibleRecipe(key, result, catalyst, tags);
	}

	public static AspectList addAspects(ItemStack stack, AspectStack... aspects) {
		AspectList list = new AspectList(stack);
		for (AspectStack aspect : aspects) {
			list.add(aspect.getAspect(), aspect.getSize());
		}
		ThaumcraftApi.registerObjectTag(stack, list);
		return list;
	}

	//Others
	public static void registerWithHandlers(ItemStack itemstack, String name, String... oreDict) {
		GameRegistry.registerCustomItemStack(name, itemstack);
		registerFMP(itemstack);
		registerOreDict(itemstack, oreDict);
	}

}
