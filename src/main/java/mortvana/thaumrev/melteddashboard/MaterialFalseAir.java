package mortvana.thaumrev.melteddashboard;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialFalseAir extends Material {

    public MaterialFalseAir() {
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
