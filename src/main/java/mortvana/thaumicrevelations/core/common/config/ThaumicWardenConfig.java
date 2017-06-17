package mortvana.thaumicrevelations.core.common.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mortvana.thaumicrevelations.core.common.ThaumicRevelations;
import mortvana.thaumicrevelations.melteddashboard.util.ConfigBase;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ThaumicWardenConfig extends ConfigBase {

    public static Configuration config;

    public ThaumicWardenConfig(FMLPreInitializationEvent event, String location) {
        super(event, location);
    }

    @Override
    public void loadConfig(File file) {
        ThaumicRevelations.logger.info("Loading Thaumic Revelations - Thaumic Warden Config");
        config = new Configuration(file);
        config.load();

        if (config.hasChanged()) {
            config.save();
        }
        ThaumicRevelations.logger.info("Thaumic Revelations - Thaumic Warden Config Loaded");
    }
}
