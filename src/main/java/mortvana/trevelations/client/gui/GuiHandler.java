package mortvana.trevelations.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

import mortvana.trevelations.common.TRevelations;
import mortvana.trevelations.inventory.ContainerHammer;

public class GuiHandler implements IGuiHandler {

	public static void init() {

		NetworkRegistry.INSTANCE.registerGuiHandler(TRevelations.instance, new GuiHandler());

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch (ID) {
			case 0:
				return new ContainerHammer(player);
		}

		return null;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch (ID) {
			case 0:
				return new GuiWaslieHammer(player);
		}

		return null;

	}

}
