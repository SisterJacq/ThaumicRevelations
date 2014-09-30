package mortvana.trevelations.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import mortvana.trevelations.common.TRevelations;

public class ModEntities {

    public static void init() {

        EntityRegistry.registerModEntity(EntityPurity.class, "PurityOrb", 0, TRevelations.instance, 64, 10, true);

    }

}
