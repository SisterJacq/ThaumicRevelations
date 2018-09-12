package mortvana.melteddashboard.item.data;

import mortvana.thaumrev.api.util.enums.EnumEquipmentType;
import net.minecraft.item.EnumRarity;

import thaumcraft.api.aspects.Aspect;

import mortvana.thaumrev.util.enums.EnumPrimalAspect;

import static mortvana.melteddashboard.util.helpers.StringHelper.*;
import static mortvana.melteddashboard.util.libraries.ColorLibrary.CLEAR;

public class ArmorDataAdv extends ArmorDataBase {

	public static final int[] NO_DISCOUNT = new int[] { 0, 0, 0, 0, 0, 0 };

	protected int absorbtion;
	protected int[] discount;
	protected boolean goggles;
	protected boolean fluxArmor; //Use RF instead of durability?
	protected int maxEnergy; //How much RF
	protected int transfer; //RF/t charge rate
	protected double absorbRatio; //0.9 in RSA
	protected boolean charge; //True to charge armor via flux damage, like RSA
	protected int chargeDamage; //160 is that of RSA Armor
	protected boolean unbreakable = false;
    protected EnumEquipmentType type;
	protected ArmorBehavior behavior = ArmorBehaviorBase.instance;

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative, int absorbtion, int[] discount, boolean goggles, boolean fluxArmor, int flux, int transfer, double absorb, boolean charge, int factor, EnumEquipmentType type) {
		setModName(modName);
		setIcon(icon);
		setSheet(sheet);
		setRepair(repair);
		setRarity(rarity);
		setUnlocName(unlocName);
		setRegName(regName);
		setColor(color);
		setShowInCreative(showInCreative);
		setAbsorbtion(absorbtion);
		setDiscount(discount);
		setGoggles(goggles);
		setFluxArmor(fluxArmor);
		setMaxEnergy(flux);
		setMaxTransfer(transfer);
		setAbsorbRatio(absorb);
		setFluxCharge(charge);
		setChargeDamage(factor);
		setEquipmentType(type);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative, int absorbtion, int[] discount, boolean goggles, EnumEquipmentType type) {
		this(modName, icon, sheet, repair, rarity, unlocName, regName, color, showInCreative, absorbtion, discount, goggles, false, 0, 0 , 0.0D, false, 0, type);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative, int absorbtion, int[] discount, boolean goggles) {
		this(modName, icon, sheet, repair, rarity, unlocName, regName, color, showInCreative, absorbtion, discount, goggles, EnumEquipmentType.NULL);
	}

	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative, int[] discount, boolean goggles) {
		this(modName, icon, sheet, repair, rarity, unlocName, regName, color, showInCreative, -1, discount, goggles, false, 0, 0 , 0.0D, false, 0, EnumEquipmentType.NULL);
	}


	//Basically ArmorData when using this or lower
	public ArmorDataAdv(String modName, String icon, String sheet, String repair, EnumRarity rarity, String unlocName, String regName, int color, boolean showInCreative) {
		this(modName, icon, sheet, repair, rarity, unlocName, regName, color, showInCreative, NO_DISCOUNT, false);
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
	public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int[] discount, boolean goggles, int absorbtion) {
		this(modName, camelCase(material) + titleCase(piece), camelCase(material), camelCase(orePrefix) + titleCase(material), rarity, '.' + camelCase(material) + '.' + camelCase(piece), titleCase(material) + titleCase(piece), color, true, absorbtion, discount, goggles, type);
	}

	public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int[] discount, boolean goggles) {
		this(modName, type, material, piece, orePrefix, color, rarity, discount, goggles, -1);
	}

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int discount, boolean goggles) {
        this(modName, type, material, piece, orePrefix, color, rarity, new int[] {discount, discount, discount, discount, discount, discount}, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int[] discount, boolean goggles) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, discount, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int discount, boolean goggles) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, discount, goggles);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int[] discount) {
        this(modName, type, material, piece, orePrefix, color, rarity, discount, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity, int discount) {
        this(modName, type, material, piece, orePrefix, color, rarity, discount, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int[] discount) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, discount, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity, int discount) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, discount, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, int color, EnumRarity rarity) {
        this(modName, type, material, piece, orePrefix, color, rarity, 0, false);
    }

    public ArmorDataAdv(String modName, EnumEquipmentType type, String material, String piece, String orePrefix, EnumRarity rarity) {
        this(modName, type, material, piece, orePrefix, CLEAR, rarity, 0, false);
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

	public ArmorDataAdv setAbsorbtion(int absorbtion) {
		this.absorbtion = absorbtion;
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

	public ArmorDataAdv setFluxArmor(boolean fluxArmor) {
		this.fluxArmor = fluxArmor;
		return this;
	}

	public ArmorDataAdv setMaxEnergy(int maxFlux) {
		maxEnergy = maxFlux;
		return this;
	}

	public ArmorDataAdv setMaxTransfer(int transfer) {
		this.transfer = transfer;
		return this;
	}

	public ArmorDataAdv setAbsorbRatio(double absorb) {
		absorbRatio = absorb;
		return this;
	}

	public ArmorDataAdv setFluxCharge(boolean charge) {
		this.charge = charge;
		return this;
	}

	public ArmorDataAdv setChargeDamage(int factor) {
		chargeDamage = factor;
		return this;
	}

	public ArmorDataAdv setUnbreakable(boolean bool) {
		unbreakable = bool;
		return this;
	}

    public ArmorDataAdv setEquipmentType(EnumEquipmentType type) {
        this.type = type;
        return this;
    }

	public ArmorDataAdv setBehavior(ArmorBehavior behavior) {
		this.behavior = behavior;
		return this;
	}

	public ArmorDataAdv setFluxData(int maxFlux, int transfer, double absorb, boolean charge, int chargeFactor) {
		setFluxArmor(true);
		setMaxEnergy(maxFlux);
		setMaxTransfer(transfer);
		setAbsorbRatio(absorb);
		setFluxCharge(charge);
		setChargeDamage(chargeFactor);
		return this;
	}

	/** GETTERS **/
	public int getAbsorbtion() {
		return absorbtion;
	}

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

	public boolean getFluxArmor() {
		return fluxArmor;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public int getMaxTransfer() {
		return transfer;
	}

	public double getAbsorbRatio() {
		return absorbRatio;
	}

	public boolean getFluxCharge() {
		return charge;
	}

	public int getChargeDamage() {
		return chargeDamage;
	}

	public boolean getUnbreakable() {
		return unbreakable;
	}

    public EnumEquipmentType getType() {
        return type;
    }

	public ArmorBehavior getBehavior() {
		return behavior;
	}

}
