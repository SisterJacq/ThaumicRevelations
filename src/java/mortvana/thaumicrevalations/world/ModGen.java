package mortvana.thaumicrevalations.world;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModGen {

    public static void init() {

        GameRegistry.registerWorldGenerator(new GenExubitura(), 1);

    }

}