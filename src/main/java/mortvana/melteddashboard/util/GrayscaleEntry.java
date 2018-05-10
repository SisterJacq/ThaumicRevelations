package mortvana.melteddashboard.util;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.thaumrev.common.ThaumicRevelations;

@SideOnly(Side.CLIENT)
public class GrayscaleEntry {

	protected static TMap<String, GrayscaleEntry> entries = new THashMap<String, GrayscaleEntry>();

	public static final String DEFAULT_DIRECTORY = "fluxgear:grayscale/";
	public static final String INGOT = "ingot";
	public static final String NUGGET = "nugget";
	public static final String DUST = "dust";
	public static final String DUSTTINY = "dustTiny";
	public static final String PLATE = "plate";
	public static final String GEAR = "gear";

	static {
		entries.put(INGOT, new GrayscaleEntry(INGOT, DEFAULT_DIRECTORY + INGOT));
		entries.put(NUGGET, new GrayscaleEntry(NUGGET, DEFAULT_DIRECTORY + NUGGET));
		entries.put(DUST, new GrayscaleEntry(DUST, DEFAULT_DIRECTORY + DUST));
		entries.put(DUSTTINY, new GrayscaleEntry(DUSTTINY, DEFAULT_DIRECTORY + DUSTTINY));
		entries.put(PLATE, new GrayscaleEntry(PLATE, DEFAULT_DIRECTORY + PLATE));
		entries.put(GEAR, new GrayscaleEntry(GEAR, DEFAULT_DIRECTORY + GEAR));
	}

	public String name;
	public String texture;
	public IIcon icon;

	public GrayscaleEntry(String name, String texture) {
		this.name = name;
		this.texture = texture;
		if (!entries.containsKey(name)) {
			entries.put(name, this);
		}
	}

	public static boolean contains(String name) {
		return entries.containsKey(name);
	}

	@SideOnly(Side.CLIENT)
	public void setIcon(IIcon icon) {
		this.icon = icon;
	}

	@SideOnly(Side.CLIENT)
	public static IIcon getIcon(String template) {
		 return entries.containsKey(template) ? entries.get(template).icon : null;
	}

	@SideOnly(Side.CLIENT)
	public static String getTexture(String template) {
		return entries.containsKey(template) ? entries.get(template).texture : null;
	}

	public static void registerIcons(IIconRegister register) {
		for (GrayscaleEntry entry : entries.values()) {
			entry.icon = register.registerIcon(entry.texture);
			//ThaumicRevelations.logger.info("Registered grayscale texture \"" + entry.texture + "\" with texture from \"" + entry.icon.getIconName() + "\"!");
		}
	}

}
