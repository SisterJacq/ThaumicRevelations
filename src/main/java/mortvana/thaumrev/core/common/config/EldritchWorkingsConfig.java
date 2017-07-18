package mortvana.thaumrev.core.common.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mortvana.thaumrev.core.common.ThaumicRevelations;
import mortvana.thaumrev.melteddashboard.util.ConfigBase;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class EldritchWorkingsConfig extends ConfigBase {

    public static Configuration config;

    public EldritchWorkingsConfig(FMLPreInitializationEvent event, String location) {
        super(event, location);
    }

    @Override
    public void loadConfig(File file) {
        ThaumicRevelations.logger.info("Loading Thaumic Revelations - Eldritch Workings Config");
        config = new Configuration(file);
        config.load();

        if (config.hasChanged()) {
            config.save();
        }
        ThaumicRevelations.logger.info("Thaumic Revelations - Eldritch Workings Config Loaded");
    }
}
