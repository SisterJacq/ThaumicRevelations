package mortvana.thaumrev.util.enums;

import thaumcraft.api.aspects.Aspect;

import static thaumcraft.api.aspects.Aspect.*;

public enum EnumPrimalAspect {
	AQUA(WATER),
	IGNIS(FIRE),
	AER(AIR),
	TERRA(EARTH),
	ORDO(ORDER),
	PERDITIO(ENTROPY);

	private Aspect aspect;

	private EnumPrimalAspect(Aspect primal) {
		aspect = primal;
	}

	public static Aspect getAspect(EnumPrimalAspect aspect) {
		return aspect.aspect;
	}

	public static EnumPrimalAspect getPrimal(Aspect aspect) {
		if (aspect.isPrimal()) {
			if (aspect.equals(WATER)) {
				return AQUA;
			} else if (aspect.equals(FIRE)) {
				return IGNIS;
			} else if (aspect.equals(AIR)) {
				return AER;
			} else if (aspect.equals(EARTH)) {
				return TERRA;
			} else if (aspect.equals(ORDER)) {
				return ORDO;
			} else if (aspect.equals(ENTROPY)) {
				return PERDITIO;
			}
		}
		return null;
	}

	public static String  getString(Aspect aspect) {
		if (aspect.isPrimal()) {
			if (aspect.equals(WATER)) {
				return "Aqua";
			} else if (aspect.equals(FIRE)) {
				return "Ignis";
			} else if (aspect.equals(AIR)) {
				return "Aer";
			} else if (aspect.equals(EARTH)) {
				return "Terra";
			} else if (aspect.equals(ORDER)) {
				return "Ordo";
			} else if (aspect.equals(ENTROPY)) {
				return "Perditio";
			}
		}
		return null;
	}
}
