package mortvana.thaumrev.melteddashboard;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialHardAir extends Material {

    public MaterialHardAir() {
        super(MapColor.airColor);
        setNoPushMobility();
        setRequiresTool();
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isReplaceable() {
        return false;
    }
}
