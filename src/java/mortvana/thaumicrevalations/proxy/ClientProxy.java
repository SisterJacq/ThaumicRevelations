package mortvana.thaumicrevalations.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import mortvana.thaumicrevalations.entity.EntityPurity;
import mortvana.thaumicrevalations.client.renderer.RendererPurity;

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderers() {

        RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RendererPurity());

    }

}
