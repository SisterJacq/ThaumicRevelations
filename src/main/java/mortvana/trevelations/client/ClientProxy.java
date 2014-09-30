package mortvana.trevelations.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import mortvana.trevelations.common.CommonProxy;
import mortvana.trevelations.entity.EntityPurity;
import mortvana.trevelations.client.renderer.RendererPurity;

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderers() {

        RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RendererPurity());

    }

}
