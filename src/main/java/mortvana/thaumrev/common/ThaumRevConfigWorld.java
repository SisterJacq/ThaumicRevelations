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

		generateChalcocite = config.get(enblC, getEnable(CUS), true, getEnableText(CUS + " (Copper Sulfide)", CU)).getBoolean(true);
		generateSphalerite = config.get(enblC, getEnable(ZNS), true, getEnableText(ZNS + " (Zinc Sulfide)", ZN)).getBoolean(true);
		generateCassiterite = config.get(enblC, getEnable(SNO), true, getEnableText(SNO + " (Tin Oxide)", SN)).getBoolean(true);
		generateMillerite = config.get(enblC, getEnable(NIS), true, getEnableText(NIS + " (Nickel Sulfide)", NI + " (and " + PD + ')')).getBoolean(true);
		generateNativeSilver = config.get(enblC, getEnable(NAG), true, getEnableText(NAG, AG)).getBoolean(true);
		generateGalena = config.get(enblC, getEnable(PBS), true, getEnableText(PBS + " (Lead Sulfide)", PB)).getBoolean(true);
		generateXenotime = config.get(enblC, getEnable(YPO), true, getEnableText(YPO + " (Rare Earth Phosphate)", ND + " and " + LU)).getBoolean(true);
		generateWolframite = config.get(enblC, getEnable(WFE), true, getEnableText(WFE + " (Iron-Tungsten Oxide)", W)).getBoolean(true);
		generateIridosmium = config.get(enblC, getEnable(IROS), true, getEnableText(IROS + " (Natural Iridium-Osmium Alloy)", "Nether", OS + " and " + IR)).getBoolean(true);
		generateBismuthinite = config.get(enblC, getEnable(BIS), true, getEnableText(BIS + " (Bismuth Sulfide)", BI)).getBoolean(true);
		generateTennantite = config.get(enblC, getEnable(CAS), true, getEnableText(CAS + " (Copper Arsenic Sulfide)", AS)).getBoolean(true);
		generateTetrahedrite = config.get(enblC, getEnable(CSB), true, getEnableText(CSB + " (Copper Antimony Sulfide)", SB)).getBoolean(true);
		generatePyrope = config.get(enblC, getEnable(PYRP), true, getEnableText(PYRP, "Nether", PYRP)).getBoolean(true);
		generateDioptase = config.get(enblC, getEnable(DIOP), true, getEnableText(DIOP, DIOP)).getBoolean(true);
		generateFluonicSapphire = config.get(enblC, getEnable(FSPH), true, getEnableText(FSPH, FSPH)).getBoolean(true);

		generateCopperMix = config.get(enblC, "Enable Mixed Copper Veins", true, "Adds mixed veins of " + CUS + ", " + CAS + ", and " + CSB + " to the Overworld. These veins are in addition to non-mixed ones.").getBoolean(true);
		generateAgPb = config.get(enblC, "Enable Native Silver-Galena Veins", true, "Adds mixed veins of " + NAG + " and " + PBS + " to the Overworld. These veins are in addition to non-mixed ones.").getBoolean(true);
		generateAgPbBi = config.get(enblC, "Enable Native Silver-Galena-Bismuthinite Veins", true, "Adds mixed veins of " + NAG + ", " + PBS + ", and " + BIS + " to the Overworld. These veins are in addition to non-mixed ones.").getBoolean(true);
		generateWSn = config.get(enblC, "Enable Cassiterite-Wolframite Veins", true, "Adds mixed veins of " + WFE + ", " + SNO + ", and Iron to the Overworld. These veins are in addition to non-mixed ones.").getBoolean(true);

		generatePoorChalcocite = config.get(enblC, getEnable(CUS , POOR), 2, getEnablePoor(CUS)).getInt(2);
		generatePoorSphalerite = config.get(enblC, getEnable(ZNS , POOR), 2, getEnablePoor(ZNS)).getInt(2);
		generatePoorCassiterite = config.get(enblC, getEnable(SNO , POOR), 2, getEnablePoor(SNO)).getInt(2);
		generatePoorMillerite = config.get(enblC, getEnable(NIS , POOR), 2, getEnablePoor(NIS)).getInt(2);
		generatePoorNativeSilver = config.get(enblC, getEnable(NAG , POOR), 2, getEnablePoor(NAG)).getInt(2);
		generatePoorGalena = config.get(enblC, getEnable(PBS , POOR), 2, getEnablePoor(PBS)).getInt(2);
		generatePoorXenotime = config.get(enblC, getEnable(YPO , POOR), 2, getEnablePoor(YPO)).getInt(2);
		generatePoorWolframite = config.get(enblC, getEnable(WFE , POOR), 2, getEnablePoor(WFE)).getInt(2);
		generatePoorIridosmium = config.get(enblC, getEnable(IROS , POOR), 2, getEnablePoor(IROS)).getInt(2);
		generatePoorBismuthinite = config.get(enblC, getEnable(BIS , POOR), 2, getEnablePoor(BIS)).getInt(2);
		generatePoorTennantite = config.get(enblC, getEnable(CAS , POOR), 2, getEnablePoor(CAS)).getInt(2);
		generatePoorTetrahedrite = config.get(enblC, getEnable(CSB , POOR), 2, getEnablePoor(CSB)).getInt(2);

		generateGravelChalcocite = config.get(enblC, getEnable(CUS , GRAVEL), 2, getEnableGravel(CUS)).getInt(2);
		generateGravelSphalerite = config.get(enblC, getEnable(ZNS , GRAVEL), 2, getEnableGravel(ZNS)).getInt(2);
		generateGravelCassiterite = config.get(enblC, getEnable(SNO , GRAVEL), 2, getEnableGravel(SNO)).getInt(2);
		generateGravelMillerite = config.get(enblC, getEnable(NIS , GRAVEL), 2, getEnableGravel(NIS)).getInt(2);
		generateGravelNativeSilver = config.get(enblC, getEnable(NAG , GRAVEL), 2, getEnableGravel(NAG)).getInt(2);
		generateGravelGalena = config.get(enblC, getEnable(PBS , GRAVEL), 2, getEnableGravel(PBS)).getInt(2);
		generateGravelXenotime = config.get(enblC, getEnable(YPO , GRAVEL), 2, getEnableGravel(YPO)).getInt(2);
		generateGravelWolframite = config.get(enblC, getEnable(WFE , GRAVEL), 2, getEnableGravel(WFE)).getInt(2);
		generateGravelIridosmium = config.get(enblC, getEnable(IROS , GRAVEL), 2, getEnableGravel(IROS)).getInt(2);
		generateGravelBismuthinite = config.get(enblC, getEnable(BIS , GRAVEL), 2, getEnableGravel(BIS)).getInt(2);
		generateGravelTennantite = config.get(enblC, getEnable(CAS , GRAVEL), 2, getEnableGravel(CAS)).getInt(2);
		generateGravelTetrahedrite = config.get(enblC, getEnable(CSB , GRAVEL), 2, getEnableGravel(CSB)).getInt(2);

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
