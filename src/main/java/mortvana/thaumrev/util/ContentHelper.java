package mortvana.thaumrev.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

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
		if (enableTinkersBronze) {
			if (!start) {
				list.add(new ResearchPage("f"));
				start = true;
			} else {
				list.add(new ResearchPage("6"));
			}
			list.add(new ResearchPage(recipeTinkersBronze));
			smelting.add(new ResearchPage(rawTinkersBronze));
			dusts.add(recDustTinkersBronze);
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
		return enableBrass || enableBronze || enableBismuthBronze || enableMithril || enableAlBronze || enableCupronickel || enableTinkersBronze || enableConstantan || enableInvar || enableElectrum;
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
}
