package mortvana.melteddashboard.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GrayscaleEntry {

	public static List<GrayscaleEntry> entries = new ArrayList<GrayscaleEntry>();

	static {
		entries.add(new GrayscaleEntry("ingot", "fluxgear:grayscale/ingot"));
	}

	public String name;
	public String texture;
	public IIcon icon;

	public GrayscaleEntry(String name, String texture) {
		this.name = name;
		this.texture = texture;
		if (!contains(name)) {
			entries.add(this);
		}
	}

	public static boolean contains(String name) {
		for (GrayscaleEntry entry : entries) {
			if (name.equals(entry.name)) {
				return true;
			}
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void setIcon(IIcon icon) {
		this.icon = icon;
	}

	@SideOnly(Side.CLIENT)
	public static IIcon getIcon(String template) {
		for (GrayscaleEntry entry : entries) {
			if (template.equals(entry.name)) {
				return entry.icon;
			}
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
	public static String getTexture(String template) {
		for (GrayscaleEntry entry : entries) {
			if (template.equals(entry.name)) {
				return entry.texture;
			}
		}
		return null;
	}

	public static void registerIcons(IIconRegister register) {
		for (GrayscaleEntry entry : entries) {
			entry.icon = register.registerIcon(entry.texture);
		}
	}

}
