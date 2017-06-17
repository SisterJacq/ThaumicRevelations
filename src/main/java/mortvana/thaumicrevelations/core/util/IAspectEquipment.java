package mortvana.thaumicrevelations.core.util;

import thaumcraft.api.aspects.Aspect;

public interface IAspectEquipment {
    Aspect getAspect(int slot);
    int getPotency(int slot);
    void setAspect(int slot, Aspect aspect, int potency);
}
