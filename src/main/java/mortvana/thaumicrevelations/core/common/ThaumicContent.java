package mortvana.thaumicrevelations.core.common;

import magicbees.api.MagicBeesAPI;
import mortvana.thaumicrevelations.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.thaumicrevelations.melteddashboard.util.ConfigBase;
import mortvana.thaumicrevelations.melteddashboard.util.IConfigInitialized;
import thaumcraft.api.aspects.Aspect;

import static mortvana.thaumicrevelations.library.ThaumicLibrary.*;

public class ThaumicContent implements IConfigInitialized {

    @Override
    public void preInit(ConfigBase config) {
        thaumicRevelationsTab = new FluxGearCreativeTab("Thaumic Revelations", "thaumic", wardenAmulet);

    }

    @Override
    public void init(ConfigBase config) {
        dummyGeneral = generalItem.addItem(0, "dummy");

    }

    @Override
    public void postInit(ConfigBase config) {
        determineTempus();
    }

    public void determineTempus() {
        // Thanks for the API hook, Myst!
        Object protoTempus = MagicBeesAPI.thaumcraftAspectTempus;
        if (protoTempus != null && protoTempus instanceof Aspect) {
            tempus = (Aspect) protoTempus;
        } else {
            tempus = Aspect.VOID;
        }
    }

    public static Aspect tempus;

}
