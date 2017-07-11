package mortvana.trevelations.client;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import cpw.mods.fml.client.registry.RenderingRegistry;

//import mortvana.trevelations.client.render.RenderFleshGolem;
import mortvana.trevelations.client.render.RenderPurity;
import mortvana.trevelations.common.CommonProxy;
//import mortvana.trevelations.entity.EntityFleshProjectile;
import mortvana.trevelations.entity.EntityPurity;
//import mortvana.trevelations.entity.FleshGolem;

public class ClientProxy extends CommonProxy {

	//public static final TRParticle PARTICLE_HANDLER = new TRParticle();

	@Override
	public void initRenderers() {

		RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RenderPurity());
		//RenderingRegistry.registerEntityRenderingHandler(FleshGolem.class, new RenderFleshGolem());
		//RenderingRegistry.registerEntityRenderingHandler(EntityFleshProjectile.class, new RenderSnowball(Items.rotten_flesh));

	}

}
