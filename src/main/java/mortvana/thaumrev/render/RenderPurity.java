package mortvana.thaumrev.render;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPurity extends Render {

	public RenderPurity() {
		this.shadowSize = 0.1F;
	}

	public void renderEntityAt(Entity entity, double x, double y, double z, float freq, float pTicks) {}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		renderEntityAt(entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return AbstractClientPlayer.locationStevePng;
	}
}
