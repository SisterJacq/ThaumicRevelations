package mortvana.thaumrev.melteddashboard.util.helpers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

/**
 *  Stolen from Tinker's Construct, where Boni stole it from CoFH Lib...
 */
public final class TextureHelper {

	private TextureHelper() { /* Oh my gosh he's wearing a singleton */ }

	@SideOnly(Side.CLIENT)
	public static boolean textureExists(ResourceLocation texture) {
		try {
			Minecraft.getMinecraft().getResourceManager().getAllResources(texture);
			return true;
		} catch (Throwable t) { // Pokemon!
			return false;
		}
	}

	@SideOnly(Side.CLIENT)
	public static boolean textureExists(String texture) {
		return textureExists(new ResourceLocation(texture));
	}

	@SideOnly(Side.CLIENT)
	public static boolean blockTextureExists(String texture) {
		return typedTextureExists(texture, "blocks");
	}

	@SideOnly(Side.CLIENT)
	public static boolean itemTextureExists(String texture) {
		return typedTextureExists(texture, "items");
	}

	@SideOnly(Side.CLIENT)
	public static boolean typedTextureExists(String texture, String type) {
		int i = texture.indexOf(':');

		if (i > 0) {
			texture = texture.substring(0, i) + ":textures/" + type + "/" + texture.substring(i + 1,  texture.length());
		} else {
			texture = "textures/" + type + "/" + texture;
		}
		return textureExists(texture + ".png");
	}
}
