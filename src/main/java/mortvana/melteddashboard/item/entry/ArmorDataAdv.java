package mortvana.melteddashboard.item.entry;

import mortvana.thaumrev.api.util.enums.EnumEquipmentType;
import net.minecraft.item.EnumRarity;

import thaumcraft.api.aspects.Aspect;

import mortvana.thaumrev.util.enums.EnumPrimalAspect;

import static mortvana.melteddashboard.util.helpers.StringHelper.*;
import static mortvana.melteddashboard.util.libraries.ColorLibrary.CLEAR;

public class ArmorDataAdv extends ArmorDataBase {

	public static final int[] NO_DISCOUNT = new int[] { 0, 0, 0, 0, 0, 0 };

	protected int[] discount;
	protected boolean goggles = false;
	protected int maxEnergy = 0;
    protected EnumEquipmentType type = EnumEquipmentType.NULL;

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative, int[] discount, boolean goggles, int flux, EnumEquipmentType type) {
		setModName(modName);
		setIcon(icon);
		setSheet(sheet);
		setRepair(repair);
		setRarity(rarity);
		setUnlocName(unlocName);
		setRegName(regName);
		setColor(color);
		setShowInCreative(showInCreative);
		setDiscount(discount);
		setGoggles(goggles);
		setMaxEnergy(flux);
		setEquipmentType(type);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative, int[] discount, boolean goggles, int flux) {
		this(modName, icon, sheet, repair, rarity, unlocName, regName, color, showInCreative, discount, goggles, flux, EnumEquipmentType.NULL);
	}

	//Basically ArmorData when using this or lower
	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative) {
		this(modName, icon, sheet, repair, rarity, unlocName, regName, color, showInCreative, NO_DISCOUNT, false, 0, EnumEquipmentType.NULL);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color) {
		this(modName, icon, sheet, repair, rarity, unlocName, regName, color, true);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, int rarity, String unlocName, String regName, int color) {
		this(modName, icon, sheet, repair, EnumRarity.values()[rarity], unlocName, regName, color, true);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName) {
		this(modName, icon, sheet, repair, rarity, unlocName, regName, CLEAR, true);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, int rarity, String unlocName, String regName) {
		this(modName, icon, sheet, repair, EnumRarity.values()[rarity], unlocName, regName, CLEAR, true);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String name) {
		this(modName, icon, sheet, repair, rarity, name, name, CLEAR, true);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, int rarity, String name) {
		this(modName, icon, sheet, repair, EnumRarity.values()[rarity], name, name, CLEAR, true);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, String name) {
		this(modName, icon, sheet, repair, EnumRarity.common, name, name, CLEAR, true);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String name) {
		this(modName, icon, sheet, "", EnumRarity.common, name, name, CLEAR, true);
	}

	public ArmorDataAdv(String icon, String sheet, String name) {
		this("fluxgear", icon, sheet, "", EnumRarity.common, name, name, CLEAR, true);
	}

    /** SPECIAL CONSTRUCTORS **/
    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int[] discount, int flux, boolean goggles) {
        this(modName, camelCase(material) + titleCase(piece), camelCase(material), camelCase(orePrefix) + titleCase(material), rarity, '.' + camelCase(material) + '.' + camelCase(piece), titleCase(material) + titleCase(piece), color, true, discount, goggles, flux, type);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int discount, int flux, boolean goggles) {
        this(modName, type, material, piece, orePrefix, color, rarity, new int[] {discount, discount, discount, discount, discount, discount}, flux, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int[] discount, int flux, boolean goggles) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, discount, flux, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int discount, int flux, boolean goggles) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, discount, flux, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int[] discount, boolean goggles) {
        this(modName, type, material, piece, orePrefix, color, rarity, discount, 0, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int discount, boolean goggles) {
        this(modName, type, material, piece, orePrefix, color, rarity, discount, 0, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int[] discount, boolean goggles) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, discount, 0, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int discount, boolean goggles) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, discount, 0, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int[] discount) {
        this(modName, type, material, piece, orePrefix, color, rarity, discount, 0, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int discount) {
        this(modName, type, material, piece, orePrefix, color, rarity, discount, 0, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int[] discount) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, discount, 0, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int discount) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, discount, 0, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity) {
        this(modName, type, material, piece, orePrefix, color, rarity, 0, 0, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, 0, 0, false);
    }

	/** SETTERS **/
	public ArmorDataAdv setModName(String modName) {
		this.modName = modName;
		return this;
	}

	public ArmorDataAdv setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public ArmorDataAdv setSheet(String texture) {
		this.sheet = texture;
		return this;
	}

	public ArmorDataAdv setRepair(String repair) {
		this.repair = repair;
		return this;
	}

	public ArmorDataAdv setRarity(EnumRarity rarity) {
		this.rarity = rarity;
		return this;
	}

	public ArmorDataAdv setRarity(int rarity) {
		this.rarity = EnumRarity.values()[rarity];
		return this;
	}

	public ArmorDataAdv setUnlocName(String unlocName) {
		this.unlocName = unlocName;
		return this;
	}

	public ArmorDataAdv setRegName(String regName) {
		this.regName = regName;
		return this;
	}

	public ArmorDataAdv setColor(int color) {
		this.color = color;
		return this;
	}

	public ArmorDataAdv setShowInCreative(boolean bool) {
		showInCreative = bool;
		return this;
	}

	public ArmorDataAdv setDiscount(int[] discount) {
		if (discount.length == 6) {
			this.discount = discount;
		}
		return this;
	}

	public ArmorDataAdv setDiscount(int discount) {
		this.discount = new int[] { discount, discount, discount, discount, discount, discount };
		return this;
	}

	public ArmorDataAdv setDiscount(int index, int discount) {
		if (index >= 0 && index < 6) {
			this.discount[index] = discount;
		}
		return this;
	}

    public ArmorDataAdv setDiscount(EnumPrimalAspect aspect, int discount) {
		this.discount[aspect.ordinal()] = discount;
		return this;
	}

	public ArmorDataAdv setDiscount(Aspect aspect, int discount) {
		if (aspect.isPrimal()) {
			this.discount[EnumPrimalAspect.getPrimal(aspect).ordinal()] = discount;
		}
		return this;
	}

	public ArmorDataAdv setGoggles(boolean bool) {
		goggles = bool;
		return this;
	}

	public ArmorDataAdv setMaxEnergy(int maxFlux) {
		maxEnergy = maxFlux;
		return this;
	}

    public ArmorDataAdv setEquipmentType(EnumEquipmentType type) {
        this.type = type;
        return this;
    }

	/** GETTERS **/
	public int[] getDiscount() {
		return discount;
	}

	public int getDiscount(int index) {
		return (index >= 0 && index < 6) ? discount[index] : 0;
	}

	public int getDiscount(EnumPrimalAspect aspect) {
		return discount[aspect.ordinal()];
	}

	public int getDiscount(Aspect aspect) {
		return aspect.isPrimal() ? discount[EnumPrimalAspect.getPrimal(aspect).ordinal()] : 0;
	}

	public boolean getGoggles() {
		return goggles;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

    public EnumEquipmentType getType() {
        return type;
    }

}
