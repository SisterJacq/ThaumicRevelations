package mortvana.trevelations.util.wardenic.upgrade;

import mortvana.trevelations.util.DamageSourceWarden;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;

public class WardenicUpgradeArmor extends WardenicUpgrade {

    public WardenicUpgradeArmor(Aspect aspect) {super(aspect);}

    @Override
    public void onAttacked(LivingHurtEvent event) {

        super.onAttacked(event);

        if(event.source.getEntity() != null) {

            DamageSource damageSource = new DamageSourceWarden("warden", event.entity);
            event.source.getEntity().attackEntityFrom(damageSource, event.ammount / 2);

        }

    }
}
