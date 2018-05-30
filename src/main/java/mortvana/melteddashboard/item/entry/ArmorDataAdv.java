package mortvana.melteddashboard.item.entry;

import mortvana.thaumrev.api.util.enums.EnumEquipmentType;
import net.minecraft.item.EnumRarity;

import thaumcraft.api.aspects.Aspect;

import mortvana.thaumrev.util.enums.EnumPrimalAspect;

import static mortvana.melteddashboard.util.helpers.StringHelper.*;

public class ArmorDataAdv extends ArmorData {
	protected int[] discount = {0, 0, 0, 0, 0, 0};
	protected boolean goggles = false;
	protected int maxEnergy = 0;
    protected EnumEquipmentType type = EnumEquipmentType.NULL;

    public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative, int[] discount, boolean goggles, int flux, EnumEquipmentType type) {
        super(modName, icon, sheet, repair, rarity, unlocName, regName, color, showInCreative);
        setDiscount(discount);
        setGoggles(goggles);
        setMaxEnergy(flux);
        setEquipmentType(type);
    }

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative, int[] discount, boolean goggles, int flux) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName, color, showInCreative);
        setDiscount(discount);
        setGoggles(goggles);
        setMaxEnergy(flux);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName, color, showInCreative);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName, color);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, int rarity, String unlocName, String regName, int color) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName, color);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, int rarity, String unlocName, String regName) {
		super(modName, icon, sheet, repair, rarity, unlocName, regName);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, int rarity, String name) {
		super(modName, icon, sheet, repair, rarity, name);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String name) {
		super(modName, icon, sheet, repair, rarity, name);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, String name) {
		super(modName, icon, sheet, repair, name);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String name) {
		super(modName, icon, sheet, name);
	}

	public ArmorDataAdv(String icon, String sheet, String name) {
		super(icon, sheet, name);

	}

    /** SPECIAL CONSTRUCTORS **/
    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int[] discount, int flux, boolean goggles) {
        this(modName, camelCase(material) + titleCase(piece), camelCase(material), camelCase(orePrefix) + titleCase(material), rarity, '.' + camelCase(material) + '.' + camelCase(piece), titleCase(material) + titleCase(piece), color, true, discount, goggles, flux, type);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int discount, int flux, boolean goggles) {
        this(modName, type, material, piece, orePrefix, color, rarity, new int[] {discount, discount, discount, discount, discount, discount}, flux, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int[] discount, int flux, boolean goggles) {
        this(modName, type, material, piece, orePrefix, 0, rarity, discount, flux, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int discount, int flux, boolean goggles) {
        this(modName, type, material, piece, orePrefix, 0, rarity, discount, flux, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int[] discount, boolean goggles) {
        this(modName, type, material, piece, orePrefix, color, rarity, discount, 0, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int discount, boolean goggles) {
        this(modName, type, material, piece, orePrefix, color, rarity, discount, 0, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int[] discount, boolean goggles) {
        this(modName, type, material, piece, orePrefix, 0, rarity, discount, 0, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int discount, boolean goggles) {
        this(modName, type, material, piece, orePrefix, 0, rarity, discount, 0, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int[] discount) {
        this(modName, type, material, piece, orePrefix, color, rarity, discount, 0, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int discount) {
        this(modName, type, material, piece, orePrefix, color, rarity, discount, 0, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int[] discount) {
        this(modName, type, material, piece, orePrefix, 0, rarity, discount, 0, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int discount) {
        this(modName, type, material, piece, orePrefix, 0, rarity, discount, 0, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity) {
        this(modName, type, material, piece, orePrefix, color, rarity, 0, 0, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity) {
        this(modName, type, material, piece, orePrefix, 0, rarity, 0, 0, false);
    }

	/** SETTERS **/
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
