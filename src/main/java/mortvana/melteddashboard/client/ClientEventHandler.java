package mortvana.melteddashboard.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;

import mortvana.melteddashboard.client.texture.GrayscaleEntry;

public class ClientEventHandler {

	@SubscribeEvent
	public void textureStitch(TextureStitchEvent.Pre event) {
		GrayscaleEntry.registerIcons(event.map);
	}
}
