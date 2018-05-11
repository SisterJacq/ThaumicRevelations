package mortvana.melteddashboard.client.texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.*;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.thaumrev.common.ThaumicRevelations;

@SideOnly(Side.CLIENT)
public class GradientTexture extends TextureAtlasSprite {

	protected String icon;
	protected ResourceLocation texture;
	protected GradientNode[] gradients;
	protected boolean stretchy;

	public GradientTexture(String name, String icon, boolean stretch, GradientNode[] gradients) {
		super(name);
		this.icon = icon;
		stretchy = stretch;
		this.gradients = gradients;
	}

	@Override
	public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
		return true;
	}

	@Override
	public boolean load(IResourceManager manager, ResourceLocation location) {
		try {
			//All variables and simple setting
			GameSettings settings = Minecraft.getMinecraft().gameSettings;
			int h, w, i, q;
			q = icon.indexOf(':');
			String domain = icon.substring(0, q);
			texture = new ResourceLocation(domain, "textures/items/" + icon.substring(q + 1, icon.length()) + ".png");
			IResource resource = manager.getResource(texture);

			int mipmap = settings.mipmapLevels;
			boolean mipError = false;

			BufferedImage[] images = new BufferedImage[mipmap + 1];
			images[0] = ImageIO.read(resource.getInputStream());

			//Basically resetSprite()
			animationMetadata = null;
			setFramesTextureData(new ArrayList());
			frameCounter = 0;
			tickCounter = 0;

			useAnisotropicFiltering = settings.anisotropicFiltering > 1.0F;
			h = images[0].getHeight();
			w = images[0].getWidth();
			height = h + (useAnisotropicFiltering ? 16 : 0);
			width = w + (useAnisotropicFiltering ? 16 : 0);

			int[][] data = new int[images.length][];
			TextureMetadataSection section = (TextureMetadataSection) resource.getMetadata("texture");
			AnimationMetadataSection animation = (AnimationMetadataSection) resource.getMetadata("animation");
			BufferedImage image;

			// Mipmap stuff from Texture map that is skipped by using a custom loader.
			if (section != null) {
				List mips = section.getListMipmaps();
				if (!mips.isEmpty()) {
					h = images[0].getHeight();
					w = images[0].getWidth();
					if ((MathHelper.roundUpToPowerOfTwo(h) != h) || (MathHelper.roundUpToPowerOfTwo(w) != w)) {
						throw new RuntimeException("Unable to load extra miplevels, source-texture is not power of two! You'd better fix that!");
					}
				}
				for (Object obj : mips) {
					i = (Integer) obj;
					if ((i > 0) && (i < images.length - 1) && (images[i] == null)) {
						ResourceLocation mip = new ResourceLocation(domain, "textures/items/mipmaps/" + location.getResourcePath() + "." + i + ".png");
						try {
							images[i] = ImageIO.read(manager.getResource(mip).getInputStream());
						} catch (IOException ex) {
							ThaumicRevelations.logger.error("Unable to load miplevel {} from: {}", i, mip, ex);
							mipError = true;
						}
					}
				}
			}

			// Reimplementation of loadSprite(...) for animations
			for (i = 0; i < images.length; i++) {
				image = images[i];

				if (image != null) {
					if (i > 0 && (image.getHeight() != h >> i || image.getWidth() != w >> i)) {
						throw new RuntimeException("Unable to load miplevel " + i + ", image is " + image.getWidth() + " by " + image.getHeight() + ", but was expected to be " + (w >> i) + " by " + (h >> i) + " instead. " + (mipError ? "That \"Unable to load extra miplevels\" error is probably the source of this." : "Since we didn't throw a \"Unable to load extra miplevels\" error, something funny is going on."));
					}

					data[i] = new int[image.getHeight() * image.getWidth()];
					image.getRGB(0, 0, image.getWidth(), image.getHeight(), data[i], 0, image.getWidth());
				}
			}


			if (animation == null) {
				if (h != w) {
					throw new RuntimeException("Broken aspect ratio and not an animated texture!");
				}
				framesTextureData.add(prepareAnisotropicFiltering(data, w, h));
			} else {
				i = h / w;
				height = width;

				if (animation.getFrameCount() > 0) {
					for (Object obj : animation.getFrameIndexSet()) {
						q = (Integer) obj;

						if (q >= i) {
							throw new RuntimeException("Invalid frame index: " + q);
						}

						allocateFrameTextureData(q);
						framesTextureData.set(q, prepareAnisotropicFiltering(getFrameTextureData(data, w, w, q), w, w));
					}
					animationMetadata = animation;
				} else {
					List<AnimationFrame> frames = new ArrayList<AnimationFrame>();

					for (q = 0; q < i; q++) {
						framesTextureData.add(prepareAnisotropicFiltering(getFrameTextureData(data, w, w, q), w, w));
						frames.add(new AnimationFrame(q, -1));
					}

					animationMetadata = new AnimationMetadataSection(frames, width, height, animation.getFrameTime());
				}
			}

			ThaumicRevelations.logger.info("Debug Start Checkpoint 0-0");
			// Gradient Colorizer!
			int [][] sprite;
			int l;
			Object obj;
			//TODO: Map Optimization
			if (stretchy) {
				float min = 0.0F, max = 255.0F;

				for (i = 0; i < data[0].length; i++) {
					q = data[0][i];
					if ((q >> 24 & 0xFF) == 0) continue;

					min = Math.min(min, getPerceptualBrightness(q));
					max = Math.max(max, getPerceptualBrightness(q));
				}

				if (min <= max) {
					min /= 255.0F;
					max /= 255.0F;
				} else {
					float temp = min;
					min = max / 255.0F;
					max = temp / 255.0F;
				}

				for (i = 0; i < framesTextureData.size(); i++) {
					obj = framesTextureData.get(i);
					if (obj instanceof int[][]) {
						sprite = (int[][]) obj;

						/*for (*/l = 0; /*l < sprite.length; l++) {*/
							for (i = 0; i < sprite[l].length; i++) {
								sprite[l][i] = getGradient(sprite[l][i], gradients, min, max);
							}
						//}
					} else {
						throw new IOException("Well, actually we probably have a texture, \"framesTextureData\" just returned an object that isn't an \"int[][]\", so we can't do colorizing stuff. This is actually an upside of more modern version of Minecraft, they finally learned to type their Collections!");
					}
				}
			} else {
				for (i = 0; i < framesTextureData.size(); i++) {

					obj = framesTextureData.get(i);
					if (obj instanceof int[][]) {
						sprite = (int[][]) obj;

						//for (l = 0; l < mipmap + 1; l++) {
							for (i = 0; i < sprite[0].length; i++) {
								ThaumicRevelations.logger.info("Checkpoint 1-" + i);
								sprite[0][i] = getGradient(sprite[0][i], gradients, 0.0F, 1.0F);
							}
						//}
						// TODO: Set? No, that gives a Runtime Exception...
						//framesTextureData.set(i, sprite);
					} else {
						throw new IOException("Well, actually we probably have a texture, \"framesTextureData\" just returned an object that isn't an \"int[][]\", so we can't do colorizing stuff. This is actually an upside of more modern version of Minecraft, they finally learned to type their Collections!");
					}
				}
			}
		} catch (RuntimeException ex) {
			FMLClientHandler.instance().trackBrokenTexture(location, ex.getMessage());
			ThaumicRevelations.logger.error("Someone forgot that textures need to be a power of two in height and width!", ex); //TODO: Switch to Melted Dashboard Core logger.
		} catch (IOException ex) {
			ThaumicRevelations.logger.error("Missing texture for " + icon + ", Skipping!", ex); //TODO: Switch to Melted Dashboard Core logger.
			return false;
		}
		return true;
	}

	public static int getGradient(int color, GradientNode[] gradients, float min, float max) {
		int a = color >> 24 & 0xFF, r, g, b , i;

		if (a == 0) { //There is no alpha to the gradient, just skip it
			return color;
		}

		float gray = getBalancedValue(getPerceptualBrightness(color) / 255.0F, min, max);
		if (gray <= gradients[getLowestValue(gradients)].shade) {
			i = gradients[getLowestValue(gradients)].color;
			r = getRed(i);
			g = getGreen(i);
			b = getBlue(i);
		} else if (gray >= gradients[getHighestValue(gradients)].shade) {
			i = gradients[getHighestValue(gradients)].color;
			r = getRed(i);
			g = getGreen(i);
			b = getBlue(i);
		} else if (gray == gradients[getClosestLowValue(gray, gradients)].shade) {
			i = gradients[getClosestLowValue(gray, gradients)].color;
			r = getRed(i);
			g = getGreen(i);
			b = getBlue(i);
		} else {
			int l = getClosestLowValue(gray, gradients);
			int h = getClosestHighValue(gray, gradients);
			float q = getBalancedValue(gray, gradients[l].shade, gradients[h].shade);

			r = getIntBetween(q, getRed(gradients[l].color), getRed(gradients[h].color));
			g = getIntBetween(q, getGreen(gradients[l].color), getGreen(gradients[h].color));
			b = getIntBetween(q, getBlue(gradients[l].color), getBlue(gradients[h].color));
		}
		return (a << 24) | (r << 16) | (g << 8) | b;
	}

	/**public int getBiasedAverage(float shade) {
		if (shade >= gradients[getLowestValue(gradients)].shade) {
			return gradients[getLowestValue(gradients)].color;
		} else if (shade <= gradients[getHighestValue(gradients)].shade) {
			return gradients[getHighestValue(gradients)].color;
		} else if (shade == gradients[getClosestLowValue(shade, gradients)].shade) {
			return gradients[getClosestLowValue(shade, gradients)].color;
		} else {
			return biasedAverage(shade, gradients[getClosestLowValue(shade, gradients)], gradients[getClosestHighValue(shade, gradients)]);
		}
	}

	public static int getBiasedAverage(float pos, GradientNode low, GradientNode high) {
		if (pos <= low.shade) {
			return low.color;
		} else if (pos >= high.shade) {
			return high.color;
		} else {
			return biasedAverage(pos, low, high);
		}
	}*/

	public static int getLowestValue(GradientNode[] nodes) {
		int v = 0;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].shade < nodes[v].shade) {
				v = i;
			}
		}
		return v;
	}

	public static int getHighestValue(GradientNode[] nodes) {
		int v = 0;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].shade > nodes[v].shade) {
				v = i;
			}
		}
		return v;
	}

	public static int getClosestLowValue(float value, GradientNode[] nodes) {
		int v = 0;
		float d = 1.0F;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].shade <= value) {
				if ((value - nodes[i].shade) < d) {
					v = i;
					d = value - nodes[i].shade;
				}
			}
		}
		return v;
	}

	public static int getClosestHighValue(float value, GradientNode[] nodes) {
		int v = 0;
		float d = 1.0F;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].shade >= value) {
				if ((nodes[i].shade - value) < d) {
					v = i;
					d = nodes[i].shade - value;
				}
			}
		}
		return v;
	}

	/**protected static int biasedAverage(float pos, GradientNode lowNode, GradientNode highNode) {
		float range = getBalancedValue(pos, lowNode.shade, highNode.shade);

		int a = getAlpha(lowNode.color); //Shortcut, because how often do we use Alpha anyway?
		if (getAlpha(lowNode.color) != getAlpha(highNode.color)) {
			a = (int) ((getAlpha(lowNode.color) * (1 - range)) + (getAlpha(highNode.color) * range));
		}

		int r = (int) ((getRed(lowNode.color) * (1 - range)) + (getRed(highNode.color) * range));
		int g = (int) ((getGreen(lowNode.color) * (1 - range)) + (getGreen(highNode.color) * range));
		int b = (int) ((getBlue(lowNode.color) * (1 - range)) + (getBlue(highNode.color) * range));

		return (a << 24) | (r << 16) | (g << 8) | b;
	}*/

	public static int getAlpha(int color) {
		return (color >> 24) & 0xFF;
	}

	public static int getRed(int color) {
		return (color >> 16) & 0xFF;
	}

	public static int getGreen(int color) {
		return (color >> 8) & 0xFF;
	}

	public static int getBlue(int color) {
		return color & 0xFF;
	}

	public static int getPerceptualBrightness(int color) {
		double r = getRed(color) / 255.0D;
		double g = getGreen(color) / 255.0D;
		double b = getBlue(color) / 255.0D;

		return (int) (Math.sqrt(0.241 * r * r + 0.691 * g * g + 0.068 * b * b) * 255);
	}

	public static float getBalancedValue(float val, float min, float max) {
		return (val - min) / (max - min);
	}

	public static int getIntBetween(float place, float val1, float val2) {
		return (int) Math.sqrt(((val1 * val1) * (1.0F * place)) + ((val2 * val2) * place));
	}
}
