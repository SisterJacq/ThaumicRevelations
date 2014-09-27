package mortvana.thaumicrevalations.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import mortvana.thaumicrevalations.TRevalations;

public class ModEntities {

    public static void init() {

        EntityRegistry.registerModEntity(EntityPurity.class, "PurityOrb", 0, TRevalations.instance, 64, 10, true);

    }

}
