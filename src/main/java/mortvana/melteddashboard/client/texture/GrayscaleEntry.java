package mortvana.melteddashboard.client.texture;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;

import static mortvana.melteddashboard.util.libraries.StringLibrary.*;

@SideOnly(Side.CLIENT)
public class GrayscaleEntry {

	protected static TMap<String, GrayscaleEntry> entries = new THashMap<String, GrayscaleEntry>();

	static {
		entries.put(INGOT, new GrayscaleEntry(INGOT, DIR_GRAYSCALE + INGOT));
		entries.put(NUGGET, new GrayscaleEntry(NUGGET, DIR_GRAYSCALE + NUGGET));
		entries.put(DUST, new GrayscaleEntry(DUST, DIR_GRAYSCALE + DUST));
		entries.put(TINY_DUST, new GrayscaleEntry(TINY_DUST, DIR_GRAYSCALE + TINY_DUST));
		entries.put(PLATE, new GrayscaleEntry(PLATE, DIR_GRAYSCALE + PLATE));
		entries.put(GEAR, new GrayscaleEntry(GEAR, DIR_GRAYSCALE + GEAR));
		entries.put(MAGIC_DUST, new GrayscaleEntry(MAGIC_DUST, DIR_GRAYSCALE + MAGIC_DUST));
		entries.put(QUARTZ, new GrayscaleEntry(QUARTZ, DIR_GRAYSCALE + QUARTZ));
		entries.put(SHARD_GEM, new GrayscaleEntry(SHARD_GEM, DIR_GRAYSCALE + SHARD_GEM));
		entries.put(SHARD_QUARTZ, new GrayscaleEntry(SHARD_QUARTZ, DIR_GRAYSCALE + SHARD_QUARTZ));
		entries.put(TINY_MAGIC_DUST, new GrayscaleEntry(TINY_MAGIC_DUST, DIR_GRAYSCALE + TINY_MAGIC_DUST));
		entries.put(CHAIN, new GrayscaleEntry(CHAIN, DIR_GRAYSCALE + CHAIN));
		entries.put(POWDER, new GrayscaleEntry(POWDER, DIR_GRAYSCALE + POWDER));
		entries.put(ROD, new GrayscaleEntry(ROD, DIR_GRAYSCALE + ROD));
	}

	protected String name;
	protected String texture;
	protected IIcon icon;

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
