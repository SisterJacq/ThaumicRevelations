package mortvana.thaumrev.network;

import cpw.mods.fml.client.registry.RenderingRegistry;

import mortvana.thaumrev.entity.EntityPurity;
import mortvana.thaumrev.library.ThaumRevLibrary;
import mortvana.thaumrev.render.RenderPurity;

public class ClientProxy extends CommonProxy {

	@Override
	public void initRenderers() {
		ThaumRevLibrary.renderDecorStoneID = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RenderPurity());
		/* LEGACY
		RenderingRegistry.registerEntityRenderingHandler(FleshGolem.class, new RenderFleshGolem());
		RenderingRegistry.registerEntityRenderingHandler(EntityFleshProjectile.class, new RenderSnowball(Items.rotten_flesh));*/
	}
}
