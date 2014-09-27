package mortvana.thaumicrevalations.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceWarden extends EntityDamageSource {

    public DamageSourceWarden(String par1Str, Entity par2Entity) {

        super(par1Str, par2Entity);
        setDamageBypassesArmor();
        setDamageIsAbsolute();

    }
}
