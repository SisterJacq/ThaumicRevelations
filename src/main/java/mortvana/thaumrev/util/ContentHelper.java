package mortvana.thaumrev.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.oredict.OreDictionary;

import thaumcraft.api.research.ResearchPage;

import mortvana.thaumrev.common.ThaumRevConfig;

import static mortvana.thaumrev.common.ThaumRevConfig.*;
import static mortvana.thaumrev.library.ThaumRevLibrary.*;

public class ContentHelper {
	public static ResearchPage[] getMaterialPages() {
		List<ResearchPage> list = new ArrayList<ResearchPage>(8);
		list.add(new ResearchPage("0"));

		List<ItemStack> ores = new ArrayList<ItemStack>(9);
		//if (ThaumRevConfig.enableSphalerite) {
		//list.add(new ResearchPage(oreSphalerite));
		//}

		//.addPages(, )
		//.addPages(ThaumRevConfig.enableBronze, new ResearchPage(recipeBronze), new ResearchPage(rawBronze))
		//.addPages(ThaumRevConfig.enableElectrum, new ResearchPage(recipeElectrum), new ResearchPage(rawElectrum))
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
			list.add(new ResearchPage(recipeBrass));
			smelting.add(new ResearchPage(rawBrass));
			dusts.add(recDustBrass);
		}
		if (enableBronze) {
			if (!start) {
				list.add(new ResearchPage("a"));
				start = true;
			} else {
				list.add(new ResearchPage("1"));
			}
			list.add(new ResearchPage(recipeBronze));
			smelting.add(new ResearchPage(rawBronze));
			dusts.add(recDustBronze);
		}
		if (enableBismuthBronze) {
			if (!start) {
				list.add(new ResearchPage("b"));
				start = true;
			} else {
				list.add(new ResearchPage("2"));
			}
			list.add(new ResearchPage(recipeBismuthBronze));
			smelting.add(new ResearchPage(rawBismuthBronze));
			dusts.add(recDustBismuthBronze[0]);
			dusts.add(recDustBismuthBronze[1]);
		}
		if (enableMithril) {
			if (!start) {
				list.add(new ResearchPage("c"));
				start = true;
			} else {
				list.add(new ResearchPage("3"));
			}
			list.add(new ResearchPage(recipeMithril));
			smelting.add(new ResearchPage(rawMithril));
			dusts.add(recDustMithril);
		}
		if (enableAlBronze) {
			if (!start) {
				list.add(new ResearchPage("d"));
				start = true;
			} else {
				list.add(new ResearchPage("4"));
			}
			list.add(new ResearchPage(recipeAlBronze));
			smelting.add(new ResearchPage(rawAluminiumBronze));
			dusts.add(recDustAlBronze);
		}
		if (enableCupronickel) {
			if (!start) {
				list.add(new ResearchPage("e"));
				start = true;
			} else {
				list.add(new ResearchPage("5"));
			}
			list.add(new ResearchPage(recipeCupronickel));
			smelting.add(new ResearchPage(rawCupronickel));
			dusts.add(recDustCupronickel);
		}
		if (enableRiftishBronze) {
			if (!start) {
				list.add(new ResearchPage("f"));
				start = true;
			} else {
				list.add(new ResearchPage("6"));
			}
			list.add(new ResearchPage(recipeRiftishBronze));
			smelting.add(new ResearchPage(rawRiftishBronze));
			dusts.add(recDustRiftishBronze);
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
			list.add(new ResearchPage(recipeConstantan));
			smelting.add(new ResearchPage(rawConstantan));
			dusts.add(recDustConstantan);
		}
		if (enableInvar) {
			list.add(new ResearchPage(recipeInvar));
			smelting.add(new ResearchPage(rawInvar));
			dusts.add(recDustInvar);
		}
		if (enableElectrum) {
			list.add(new ResearchPage(recipeElectrum));
			smelting.add(new ResearchPage(rawElectrum));
			dusts.add(recDustElectrum);
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
