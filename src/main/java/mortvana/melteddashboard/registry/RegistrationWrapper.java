package mortvana.melteddashboard.registry;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import mortvana.melteddashboard.util.helpers.ItemHelper;

public class RegistrationWrapper {

    /*public static Block registerBlock(Block block, Class<? extends FluxGearItemBlock> itemblock, String name, String[] names, int[] rarities, String unlocalizedName) {
        return GameRegistry.registerBlock(block, itemblock, name, names, rarities, unlocalizedName);
    }

    public static void registerOres(OreDataSet... oreSets) {
        for (OreDataSet data : oreSets) {
            registerBlock(data.block, data.itemblock, data.name, data.names, data.rarities, data.unlocalizedName);
            for (int i = 0; i < data.entries.length; i++) {
                OreDataEntry entry = data.entries[i];
                data.block.setHarvestLevel(data.toolType, entry.miningLevel, i);
                registerWithHandlers(entry.oreStack, data.names[i], entry.oreDict);
            }
        }
    }*/

    public static void registerWithHandlers(ItemStack itemstack, String name, String... oreDict) {
        GameRegistry.registerCustomItemStack(name, itemstack);
        registerFMP(itemstack);
        registerOreDict(itemstack, oreDict);
    }

    public static void registerFMP(Block block, int amount) {
        for (int i = 0; i < amount; i++) {
            registerFMP(new ItemStack(block, 1, i));
        }
    }

    public static void registerFMP(ItemStack itemstack) {
        FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", itemstack);
    }

    public static void registerOreDict(ItemStack itemstack, String... oreDict) {
        for (String name : oreDict) {
            OreDictionary.registerOre(name, itemstack);
        }
    }

    public static void addSmelting(ItemStack input, ItemStack output, float experience) {
        FurnaceRecipes.smelting().func_151394_a(input, output, experience);
    }

	public static IRecipe registerShapedRecipe(ItemStack output, Object... stuff) {
		return GameRegistry.addShapedRecipe(output, stuff);
	}

    public static IRecipe registerSquareRecipe(ItemStack input, ItemStack output) {
        return GameRegistry.addShapedRecipe(output, "##", "##", '#', input);
    }

	public static ShapelessOreRecipe registerShapelessSizedOreRecipe(ItemStack input, int modifier, String... stuff) {
		ShapelessOreRecipe r = new ShapelessOreRecipe(ItemHelper.cloneStack(input, stuff.length + modifier), stuff);
		GameRegistry.addRecipe(r);
		return r;
	}
}
