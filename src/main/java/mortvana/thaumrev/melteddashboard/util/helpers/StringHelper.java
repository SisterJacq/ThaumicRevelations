package mortvana.thaumrev.melteddashboard.util.helpers;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.input.Keyboard;

import java.util.Locale;

public class StringHelper {

	/* Color Coding */
	public static final String BLACK = "\u00a70";
	public static final String BLUE = "\u00a71";
	public static final String GREEN = "\u00a72";
	public static final String TEAL = "\u00a73";
	public static final String RED = "\u00a74";
	public static final String PURPLE = "\u00a75";
	public static final String ORANGE = "\u00a76";
	public static final String LIGHT_GRAY = "\u00a77";
	public static final String GRAY = "\u00a78";
	public static final String LIGHT_BLUE = "\u00a79";
	public static final String LIME_GREEN = "\u00a7a";
	public static final String CYAN = "\u00a7b";
	public static final String LIGHT_RED = "\u00a7c";
	public static final String PINK = "\u00a7d";
	public static final String YELLOW = "\u00a7e";
	public static final String WHITE = "\u00a7f";

	/* Text formatting */
	public static final String OBFUSCATED = "\u00a7k";
	public static final String BOLD = "\u00a7l";
	public static final String STRIKETHROUGH = "\u00a7m";
	public static final String UNDERLINE = "\u00a7n";
	public static final String ITALIC = "\u00a7o";
	public static final String END = "\u00a7r";

	public static final String[] ROMAN_NUMERAL = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

	public static boolean displayShiftForDetails = true;
	public static boolean displayStackCount = false;

	public static final String TOOLTIP = "info.fluxgear.tooltip.";
	public static final String TUTORIAL = "info.fluxgear.tutorial";

	private StringHelper() {}

	/** Keyboard Helpers **/
	public static boolean isAltKeyDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU);
	}

	public static boolean isControlKeyDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
	}

	public static boolean isShiftKeyDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
	}

	/** Formatting **/
	public static String lowerCase(String string) {
		return string.toLowerCase(Locale.US);
	}

	public static String upperCase(String string) {
		return string.toUpperCase(Locale.US);
	}

	public static String camelCase(String string) {
		return string.substring(0, 1).toLowerCase(Locale.US) + string.substring(1);
	}

	public static String titleCase(String string) {
		return string.substring(0, 1).toUpperCase(Locale.US) + string.substring(1);
	}

	public static String localize(String string) {
		return StatCollector.translateToLocal(string);
	}

	public static int getSplitStringHeight(FontRenderer renderer, String string, int width) {
		return renderer.listFormattedStringToWidth(string, width).size() * renderer.FONT_HEIGHT;
	}

	public static String geyKeyName(int key) {
		return key < 0 ? StatCollector.translateToLocalFormatted("key.mouseButton", key + 101) : Keyboard.getKeyName(key);
	}

	public static String stripPrefixes(String string) {
		String oreDict = string;
		boolean cycle = true;
		while (cycle) {
			cycle = false;
			if (oreDict.startsWith("block")) {
				oreDict = oreDict.replaceFirst("block", "");
				cycle = true;
			}
			if (oreDict.startsWith("ore")) {
				oreDict = oreDict.replaceFirst("ore", "");
				cycle = true;
			}
			if (oreDict.startsWith("ingot")) {
				oreDict = oreDict.replaceFirst("ingot", "");
				cycle = true;
			}
			if (oreDict.startsWith("dust")) {
				oreDict = oreDict.replaceFirst("dust", "");
				cycle = true;
			}
			if (oreDict.startsWith("nugget")) {
				oreDict = oreDict.replaceFirst("nugget", "");
				cycle = true;
			}
			if (oreDict.startsWith("log")) {
				oreDict = oreDict.replaceFirst("log", "");
				cycle = true;
			}
			if (oreDict.startsWith("item")) {
				oreDict = oreDict.replaceFirst("item", "");
				cycle = true;
			}
			if (oreDict.startsWith("gem")) {
				oreDict = oreDict.replaceFirst("gem", "");
				cycle = true;
			}
			if (oreDict.startsWith("crystal")) {
				oreDict = oreDict.replaceFirst("crystal", "");
				cycle = true;
			}
		}

		return oreDict;
	}

	public static String generalizeOreDictSting(String name) {
		return '\u1F18' + titleCase(stripPrefixes(name));
	}

	public static String[] generalizeOreDictStingArray(String... names) {
		String[] newNames = new String[names.length];
		for (int i = 0; i < names.length; i++) {
			newNames[i] = generalizeOreDictSting(names[i]);
		}
		return newNames;
	}

	public static String createOreDictString(String prefix, String name) {
		return prefix + name.replaceAll("\u1F18", "");
	}

	public static String[] createOreDictStringArray(String prefix, String... names) {
		String[] newNames = new String[names.length];
		for (int i = 0; i < names.length; i++) {
			newNames[i] = createOreDictString(prefix, names[i]);
		}
		return newNames;
	}

	public static String createSimpleOreDictString(String prefix, String name) {
		return prefix + titleCase(stripPrefixes(name));
	}

	public static String getRarity(int rarity) {
		switch (rarity) {
			case 1:
				return YELLOW;
			case 2:
				return CYAN;
			case 3:
				return PINK;
			default:
				return "";
		}
	}

	public static String getAbsoluteRarity(int rarity) {
		switch (rarity) {
			case 1:
				return YELLOW;
			case 2:
				return CYAN;
			case 3:
				return PINK;
			default:
				return WHITE;
		}
	}

	public static String getRarity(EnumRarity rarity) {
		return getRarity(rarity.ordinal());
	}

	public static String getAbsoluteRarity(EnumRarity rarity) {
		return getAbsoluteRarity(rarity.ordinal());
	}

	public static String getItemName(ItemStack stack) {
		return END + getRarity(stack.getRarity()) + stack.getDisplayName() + END;
	}

	public static String getFluidName(FluidStack stack) {
		Fluid fluid = stack.getFluid();
		return END + getRarity(fluid.getRarity()) + fluid.getLocalizedName(stack) + END;
	}

	public static String getItemName(ItemStack stack, String defaultName) {
		return stack == null ? defaultName : getItemName(stack);
	}

	public static String getFluidName(FluidStack stack, String defaultName) {
		return stack == null ? defaultName : getFluidName(stack);
	}

	//public static String getScaledNumber(long number, int )

	//TODO
	public static String getScaledNumber(long number) {
		return getScaledNumber(number, 2);
	}

	//TODO
	public static String getScaledNumber(long number, int var2) {
		String var3;
		int var4 = 10 ^ var2;
		if (number > (long) (1e8 * var4)) {
			var3 = number / 1000000000L + "G";
		} else if (number > (long)(1e5 * var4)) {
			var3 = number / 1000000L + "M";
		} else if (number > (long)(1e2 * var4)) {
			var3 = number / 100L + "k";
		} else {
			var3 = String.valueOf(number);
		}

		return var3;
	}

	//TODO
	public static String getScaledNumber_(long number) {
		if (number >= 1e9) {
			return number / 1e9 + "." + (number % 1e9 / 1e7) + "G";
		} else if (number >= 1e6) {
			return number / 1e6 + "." + (number % 1e6 / 1e4) + "M";
		} else if (number >= 1e3) {
			return number / 1e3 + "." + (number % 1e3 / 1e1) + "k";
		} else {
			return String.valueOf(number);
		}
	}

	//TODO
	public static String formatNumber(double value) {
		if (value < 1.0e3D) {
			return String.valueOf(value);
		} else if (value < 1.0e6D) {
			return (double) Math.round(value) / 1.0e3D + "k";
		} else if (value < 1.0e9D) {
			return(double) Math.round(value) / 1.0e6D + "M";
		} else if (value < 1.0e12D) {
			return (double) Math.round(value) / 1.0e9D + "G";
		} else if (value < 1.0e15D) {
			return (double) Math.round(value) / 1.0e12D + "T";
		}
		return String.valueOf(value);
	}

	public static String toRomanNumerals(short value) {
		if (value < 11) {
			return ROMAN_NUMERAL[value];
		} else {
			StringBuilder str = new StringBuilder();
			int i = value;
			if (i < 0) {
				i = -i;
				str.append('-');
			}
			for (RomanNumeral numeral : RomanNumeral.values()) {
				for (int q = i / numeral.value; q-- > 0; str.append(numeral.name)); //Intended not to loop.
				i %= numeral.value;
			}
			return str.toString();
		}
	}

    public static int countOf(String string, char glyph) {
        int count = 0;
        char[] array = string.toCharArray();
        for (char character : array) {
            if (character == glyph) {
                count++;
            }
        }
        return count;
    }

	/* public static String[] breakString(String string) {
        String[] strings;

    }*/

	/** Tooltips **/

	public static String getActivationText(String string) {
		return CYAN + localize(string) + END;
	}

	public static String getDeactivationText(String string) {
		return YELLOW + localize(string) + END;
	}

	public static String getInfoText(String string) {
		return LIME_GREEN + localize(string) + END;
	}

	public static String getNoticeText(String string) {
		return ORANGE + localize(string) + END;
	}

	public static String getFlavorText(String string) {
		return LIGHT_GRAY + localize(string) + END;
	}

	public static String holdShiftForDetails() {
		return LIGHT_GRAY + localize(TOOLTIP + "hold") + " " + TEAL + ITALIC + localize(TOOLTIP + "shift") + " " + END + LIGHT_GRAY + localize(TOOLTIP + "details") + END;
	}

	public static String visDiscount(int discount) {
		return PURPLE + localize("tc.visdicount" + ": " + discount + "%");
	}

	/** Tutorial Tabs **/
	public static String tutorialTabAugment() {
		return localize(TUTORIAL + "tabAugment");
	}

	public static String tutorialTabConfigurationMain() {
		return localize(TUTORIAL + "tabConfiguration.main");
	}

	public static String tutorialTabConfigurationFlux() {
		return localize(TUTORIAL + "tabConfiguration.flux");
	}

	public static String tutorialTabConfigurationOperation() {
		return localize(TUTORIAL + "tabConfiguration.operation");
	}

	public static String tutorialTabOperation() {
		return localize(TUTORIAL + "tabOperation");
	}

	public static String tutorialTabRedstone() {
		return localize(TUTORIAL + "tabRedstone");
	}

	public static String tutorialTabSecurity() {
		return localize(TUTORIAL + "tabSecurity");
	}

	public static String tutorialTabFluxRequired() {
		return localize(TUTORIAL + "fluxRequired");
	}

	public static enum RomanNumeral {
		M(1000),
		CM(900),
		D(500),
		CD(400),
		C(100),
		XC(90),
		L(50),
		XL(40),
		X(10),
		IX(9),
		V(5),
		IV(4),
		I(1);

		public final String name = name();
		public final int value;

		private RomanNumeral(int value) {
			this.value = value;
		}
	}
}
