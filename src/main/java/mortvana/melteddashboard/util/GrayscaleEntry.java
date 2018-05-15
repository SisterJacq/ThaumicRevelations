package mortvana.melteddashboard.util;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;

import static mortvana.melteddashboard.lib.StringLibrary.*;

@SideOnly(Side.CLIENT)
public class GrayscaleEntry {

	protected static TMap<String, GrayscaleEntry> entries = new THashMap<String, GrayscaleEntry>();

	static {
		entries.put(INGOT, new GrayscaleEntry(INGOT, GRAYSCALE_DIRECTORY + INGOT));
		entries.put(NUGGET, new GrayscaleEntry(NUGGET, GRAYSCALE_DIRECTORY + NUGGET));
		entries.put(DUST, new GrayscaleEntry(DUST, GRAYSCALE_DIRECTORY + DUST));
		entries.put(TINYDUST, new GrayscaleEntry(TINYDUST, GRAYSCALE_DIRECTORY + TINYDUST));
		entries.put(PLATE, new GrayscaleEntry(PLATE, GRAYSCALE_DIRECTORY + PLATE));
		entries.put(GEAR, new GrayscaleEntry(GEAR, GRAYSCALE_DIRECTORY + GEAR));
		entries.put(MAGICDUST, new GrayscaleEntry(MAGICDUST, GRAYSCALE_DIRECTORY + MAGICDUST));
		entries.put(QUARTZ, new GrayscaleEntry(QUARTZ, "minecraft:quartz"));
		entries.put(SHARD, new GrayscaleEntry(SHARD, GRAYSCALE_DIRECTORY + SHARD));
		entries.put(TINYMAGICDUST, new GrayscaleEntry(TINYMAGICDUST, GRAYSCALE_DIRECTORY + TINYMAGICDUST));
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
