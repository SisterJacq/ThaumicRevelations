package mortvana.thaumrev.common;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import mortvana.melteddashboard.util.ConfigBase;

import static mortvana.melteddashboard.util.libraries.StringLibrary.*;

public class ThaumRevConfigWorld extends ConfigBase {

	public static Configuration config;

	public ThaumRevConfigWorld(FMLPreInitializationEvent event, String location) {
		super(event, location);
	}

	@Override
	public void loadConfig(File file) {
		ThaumicRevelations.logger.info("Loading Thaumic Revelations World Config");
		config = new Configuration(file);
		config.load();

		generateChalcocite = config.get(enblC, getEnable(cu), true, getEnableText("Chalcocite (Copper Sulfide)", CU)).getBoolean(true);
		generateSphalerite = config.get(enblC, getEnable(zn), true, getEnableText("Sphalerite (Zinc Sulfide)", ZN)).getBoolean(true);
		generateCassiterite = config.get(enblC, getEnable(sn), true, getEnableText("Sphalerite (Tin Oxide)", SN)).getBoolean(true);
		generateMillerite = config.get(enblC, getEnable(ni), true, getEnableText("Millerite (Nickel Sulfide)", "Nickel (and Palladium)")).getBoolean(true);
		generateNativeSilver = config.get(enblC, getEnable(ag), true, getEnableText("Native Silver Ore", AG)).getBoolean(true);
		generateGalena = config.get(enblC, getEnable(pb), true, getEnableText("Galena (Lead Sulfide)", PB)).getBoolean(true);
		generateXenotime = config.get(enblC, getEnable(YPO), true, getEnableText("Xenotime (Rare Earth Phosphate)", "Neodymium and Lutetium")).getBoolean(true);
		generateWolframite = config.get(enblC, getEnable(wfe), true, getEnableText("Wolframite (Iron-Tungsten Oxide)", W)).getBoolean(true);
		generateIridosmium = config.get(enblC, getEnable(IROS), true, getEnableText("Iridosmium (Natural Iridium-Osmium Alloy)", "Nether", OS + " and " + IR)).getBoolean(true);
		generateBismuthinite = config.get(enblC, getEnable(bi), true, getEnableText("Bismuthinite (Bismuth Sulfide)", BI)).getBoolean(true);
		generateTennantite = config.get(enblC, getEnable(cuas), true, getEnableText("Tennantite (Copper Arsenic Sulfide)", AS)).getBoolean(true);
		generateTetrahedrite = config.get(enblC, getEnable(cusb), true, getEnableText("Tetrahedrite (Copper Antimony Sulfide)", SB)).getBoolean(true);
		generatePyrope = config.get(enblC, getEnable(PYRP), true, getEnableText(PYRP, "Nether", PYRP)).getBoolean(true);
		generateDioptase = config.get(enblC, getEnable(DIOP), true, getEnableText(DIOP, DIOP)).getBoolean(true);
		generateFluonicSapphire = config.get(enblC, getEnable(FSPH), true, getEnableText(FSPH, FSPH)).getBoolean(true);

		generateCopperMix = config.get(enblC, "Enable Mixed Copper Veins", true, "Adds mixed veins of " + cu + ", " + cuas + ", and " + cusb + " to the Overworld. These veins are in addition to non-mixed ones.").getBoolean(true);
		generateAgPb = config.get(enblC, "Enable Native Silver-Galena Veins", true, "Adds mixed veins of " + ag + " and " + pb + " to the Overworld. These veins are in addition to non-mixed ones.").getBoolean(true);
		generateAgPbBi = config.get(enblC, "Enable Native Silver-Galena-Bismuthinite Veins", true, "Adds mixed veins of " + ag + ", " + pb + ", and " + bi + " to the Overworld. These veins are in addition to non-mixed ones.").getBoolean(true);
		generateWSn = config.get(enblC, "Enable Cassiterite-Wolframite Veins", true, "Adds mixed veins of " + wfe + ", " + sn + ", and Iron to the Overworld. These veins are in addition to non-mixed ones.").getBoolean(true);

		generatePoorChalcocite = config.get(enblC, getEnable(cu , poor), 2, getEnablePoor(cu)).getInt(2);
		generatePoorSphalerite = config.get(enblC, getEnable(zn , poor), 2, getEnablePoor(zn)).getInt(2);
		generatePoorCassiterite = config.get(enblC, getEnable(sn , poor), 2, getEnablePoor(sn)).getInt(2);
		generatePoorMillerite = config.get(enblC, getEnable(ni , poor), 2, getEnablePoor(ni)).getInt(2);
		generatePoorNativeSilver = config.get(enblC, getEnable(ag , poor), 2, getEnablePoor(ag)).getInt(2);
		generatePoorGalena = config.get(enblC, getEnable(pb , poor), 2, getEnablePoor(pb)).getInt(2);
		generatePoorXenotime = config.get(enblC, getEnable(YPO , poor), 2, getEnablePoor(YPO)).getInt(2);
		generatePoorWolframite = config.get(enblC, getEnable(wfe , poor), 2, getEnablePoor(wfe)).getInt(2);
		generatePoorIridosmium = config.get(enblC, getEnable(IROS , poor), 2, getEnablePoor(IROS)).getInt(2);
		generatePoorBismuthinite = config.get(enblC, getEnable(bi , poor), 2, getEnablePoor(bi)).getInt(2);
		generatePoorTennantite = config.get(enblC, getEnable(cuas , poor), 2, getEnablePoor(cuas)).getInt(2);
		generatePoorTetrahedrite = config.get(enblC, getEnable(cusb , poor), 2, getEnablePoor(cusb)).getInt(2);

		generateGravelChalcocite = config.get(enblC, getEnable(cu , gravel), 2, getEnableGravel(cu)).getInt(2);
		generateGravelSphalerite = config.get(enblC, getEnable(zn , gravel), 2, getEnableGravel(zn)).getInt(2);
		generateGravelCassiterite = config.get(enblC, getEnable(sn , gravel), 2, getEnableGravel(sn)).getInt(2);
		generateGravelMillerite = config.get(enblC, getEnable(ni , gravel), 2, getEnableGravel(ni)).getInt(2);
		generateGravelNativeSilver = config.get(enblC, getEnable(ag , gravel), 2, getEnableGravel(ag)).getInt(2);
		generateGravelGalena = config.get(enblC, getEnable(pb , gravel), 2, getEnableGravel(pb)).getInt(2);
		generateGravelXenotime = config.get(enblC, getEnable(YPO , gravel), 2, getEnableGravel(YPO)).getInt(2);
		generateGravelWolframite = config.get(enblC, getEnable(wfe , gravel), 2, getEnableGravel(wfe)).getInt(2);
		generateGravelIridosmium = config.get(enblC, getEnable(IROS , gravel), 2, getEnableGravel(IROS)).getInt(2);
		generateGravelBismuthinite = config.get(enblC, getEnable(bi , gravel), 2, getEnableGravel(bi)).getInt(2);
		generateGravelTennantite = config.get(enblC, getEnable(cuas , gravel), 2, getEnableGravel(cuas)).getInt(2);
		generateGravelTetrahedrite = config.get(enblC, getEnable(cusb , gravel), 2, getEnableGravel(cusb)).getInt(2);

		if (config.hasChanged()) {
			config.save();
		}
		ThaumicRevelations.logger.info("Thaumic Revelations World Config Loaded");
	}

	public static String getEnable(String str) {
		return "Enable " + str + " Ore";
	}

	public static String getEnable(String str, String type) {
		return "Enable " + str + " " + type + " Ore";
	}

	public static String getEnableText(String ore, String dim, String metal) {
		return "Adds " + ore + " Ore to the " + dim + ". Disabling without other mods add a source of " + metal + "will break stuff.";
	}

	public static String getEnableText(String ore, String metal) {
		return getEnableText(ore, "Overworld", metal);
	}

	public static String getEnablePoor(String ore) {
		return "Adds Poor " + ore + " Ore to the Overworld. 0 is enabled, 1 is disabled, 2 is automatic (Only with Railcraft)";
	}

	public static String getEnableGravel(String ore) {
		return "Adds " + ore + " Gravel Ore to the Overworld. 0 is enabled, 1 is disabled, 2 is automatic (Only with Tinker's Construct)";
	}

	public static String enblC = "World Gen Enablers";

	public static String poor = "Poor";
	public static String gravel = "Gravel";
	
	public static String cu = "Chalcocite";
	public static String zn = "Sphalerite";
	public static String sn = "Cassiterite";
	public static String ni = "Millerite";
	public static String ag = "Native Silver";
	public static String pb = "Galena";
	public static String wfe = "Wolframite";
	public static String bi = "Bismuthinite";
	public static String cuas = "Tennantite";
	public static String cusb = "Tetrahedrite";


	public static boolean generateChalcocite;
	public static boolean generateSphalerite;
	public static boolean generateCassiterite;
	public static boolean generateMillerite;
	public static boolean generateNativeSilver;
	public static boolean generateGalena;
	public static boolean generateXenotime;
	public static boolean generateWolframite;
	public static boolean generateIridosmium;
	public static boolean generateBismuthinite;
	public static boolean generateTennantite;
	public static boolean generateTetrahedrite;
	public static boolean generatePyrope;
	public static boolean generateDioptase;
	public static boolean generateFluonicSapphire;

	public static boolean generateCopperMix;
	public static boolean generateAgPb;
	public static boolean generateAgPbBi;
	public static boolean generateWSn;

	public static int generatePoorChalcocite;
	public static int generatePoorSphalerite;
	public static int generatePoorCassiterite;
	public static int generatePoorMillerite;
	public static int generatePoorNativeSilver;
	public static int generatePoorGalena;
	public static int generatePoorXenotime;
	public static int generatePoorWolframite;
	public static int generatePoorIridosmium;
	public static int generatePoorBismuthinite;
	public static int generatePoorTennantite;
	public static int generatePoorTetrahedrite;

	public static int generateGravelChalcocite;
	public static int generateGravelSphalerite;
	public static int generateGravelCassiterite;
	public static int generateGravelMillerite;
	public static int generateGravelNativeSilver;
	public static int generateGravelGalena;
	public static int generateGravelXenotime;
	public static int generateGravelWolframite;
	public static int generateGravelIridosmium;
	public static int generateGravelBismuthinite;
	public static int generateGravelTennantite;
	public static int generateGravelTetrahedrite;
}
