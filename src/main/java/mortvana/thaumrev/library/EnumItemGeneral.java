package mortvana.thaumrev.library;

import net.minecraft.item.ItemStack;

import mortvana.thaumrev.melteddashboard.util.IEnumItem;

public enum EnumItemGeneral implements IEnumItem {
	ingotCopper(0, "ingotCopper"),
	ingotZinc(1, "ingotZinc"),
	ingotTin(2, "ingotTin"),
	ingotSilver(3, "ingotSilver"),
	
	nuggetCopper(10, "nuggetCopper"),
	nuggetZinc(11, "nuggetZinc"),
	nuggetTin(12, "nuggetTin"),
	nuggetSilver(13, "nuggetSilver"),
	
	dustCopper(20, "dustCopper"),
	dustZinc(21, "dustZinc"),
	dustTin(22, "dustTin"),
	dustSilver(23, "dustSilver"),
	
	ingotBrass(30, "ingotBrass"),
	ingotBronze(31, "ingotBronze"),
	ingotThaumicBronze(32, "ingotThaumicBronze"),
	ingotSteel(33, "ingotSteel"),
	ingotVoidbrass(34, "ingotVoidbrass"),
	ingotVoidsteel(35, "ingotVoidsteel"),
	ingotElectrum(36, "ingotElectrum"),
	
	nuggetBrass(40, "nuggetBrass"),
	nuggetBronze(41, "nuggetBronze"),
	nuggetThaumicBronze(42, "nuggetThaumicBronze"),
	nuggetSteel(43, "nuggetSteel"),
	nuggetVoidbrass(44, "nuggetVoidbrass"),
	nuggetVoidsteel(45, "nuggetVoidsteel"),
	nuggetElectrum(46, "nuggetElectrum"),

	dustBrass(50, "dustBrass"),
	dustBronze(51, "dustBronze"),
	dustThaumicBronze(52, "dustThaumicBronze"),
	dustSteel(53, "dustSteel"),
	dustVoidbrass(54, "dustVoidbrass"),
	dustVoidsteel(55, "dustVoidsteel"),
	dustElectrum(56, "dustElectrum"),

	rawBrass(60, "ingotBrassRaw"),
	rawBronze(61, "ingotBronzeRaw"),
	rawThaumicBronze(62, "ingotThaumicBronzeRaw"),
	rawElectrum(66, "ingotElectrumRaw"),

	dustSalisMundusTiny(70, "dustSalisMundusTiny"),

	coatedThaumicBronze(72, "ingotThaumicBronzeCoated"),

	ceramicSlag(80, "itemSlagCeramic"),
	thaumicSlag(81, "itemSlagThaumic"),
	arcaneSingularity(82, "itemArcaneSingularity"),
	stabilizedSingularity(83, "itemStabilizedSingularity"),

	thaumicBronzeChain(92, "thaumicBronzeChain", "itemChainThaumicBronze"),

	excubituraPetal(100, "excubituraPetal", "itemExcubituraPetal"),

	excubituraPaste(110, "excubituraPaste", "itemExcubituraPaste"),
	excubituraFabric(111, "excubituraFabric", "itemExcubituraFabric"),
	itemWardencloth(112, "wardencloth", "itemWardencloth"),

	excubituraOilUnproc(120, "excubituraOilUnproc", "itemExcubituraOilUnprocessed"),
	excubituraOil(121, "excubituraOil", "itemExcubituraOil"),
	wardenicBronzeChain(122, "wardenicBronzeChain", "itemChainWardenicBronze"),
	primalBronzeChain(123, "primalBronzeChain", "itemChainPrimalBronze"),
	wardenicBronzePlate(124, "wardenicBronzePlate", "itemPlateWardenicBronze"),

	excubituraOilPure(130, "excubituraOilPure", "itemExcubituraOilPure"),
	wardenicSteelChain(131, "wardenicSteelChain", "itemChainWardenicSteel"),
	wardenicSteelPlate(132, "wardenicSteelPlate", "itemPlateWardenicSteel"),
	detailedSteelPlate(133, "detailedSteelPlate", "itemPlateWardenicSteelDetailed"),
	runicSteelPlate(134, "runicSteelPlate", "itemPlateWardenicSteelRunic"),

	wardenicQuartz(140, "wardenicQuartz", "gemQuartzWardenic"),
	wardenicCrystal(141, "wardenicCrystal", "crystalWardenic"),
	dustWardenicQuartz(142, "dustWardenicQuartz"),
	wardenicCrystalCrushed(143, "dustWardenicCrystal", "dustWardenic"),
	binderWardenic(145, "binderWardenic", "itemBinderWardenic"),
	ingotWardenicAlloy(146, "ingotWardenicComposite"),
	plateWardenic(147, "itemPlateWardenicComposite"),

	excubituraCrystalAwakened(150, "wardenicCrystalAwakened", "crystalWardenicAwakened"),

	ingotWardenicSteel(500, "ingotWardenicSteel"),
	ingotThaumicElectrum(501, "ingotThaumicElectrum"),

	nuggetWardenicSteel(510, "nuggetWardenicSteel"),
	nuggetThaumicElectrum(511, "nuggetThaumicElectrum"),

	dustWardenicSteel(520, "dustWardenicSteel"),
	dustThaumicElectrum(521, "dustThaumicElectrum"),

	wardenicHardener(1050, "itemWardenicHardener"),

	firedThaumicBronze(1102, "ingotThaumicBronzeFired"),

	seedExcubitura(1200, "seedExcubitura"),
	seedCotton(1201, "seedCotton");


	private EnumItemGeneral(int meta, String name) {
		this(meta, name, name);
	}
	
	private EnumItemGeneral(int meta, String name, String oreDict) {
		this.meta = meta;
		this.name = name;
		this.oreDict = oreDict;
	}
	
	private int meta;
	private String name;
	private String oreDict;
	private ItemStack stack;
	
	public int getMetadata() {
		return meta;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOreDict() {
		return oreDict;
	}

	public static EnumItemGeneral getValueForMeta(int meta) {
		for (EnumItemGeneral item : EnumItemGeneral.values()) {
			if (item.getMetadata() == meta) {
				return item;
			}
		}
		return null;
	}

	public ItemStack getStack() {
		if (stack == null) {
			stack = new ItemStack(ThaumRevLibrary.generalItem, 1, meta);
		}
		return stack;
	}
}
