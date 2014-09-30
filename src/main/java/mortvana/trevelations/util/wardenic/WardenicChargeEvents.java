package mortvana.trevelations.util.wardenic;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mortvana.trevelations.item.ItemWardenArmor;
import mortvana.trevelations.item.ItemWardenWeapon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.Random;

public class WardenicChargeEvents {

    private Random random = new Random();

    public static void init() {

        MinecraftForge.EVENT_BUS.register(new WardenicChargeEvents());

    }

    @SubscribeEvent
    public void onTick(LivingUpdateEvent event) {

        if(event.entity instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer)event.entity;

            for(int i = 0; i < 5; i++) {

                if(player.getEquipmentInSlot(i) != null) {

                    if(player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor || player.getEquipmentInSlot(i).getItem() instanceof ItemWardenWeapon) {

                        if(player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage()) {

                            if(random.nextInt(50) == 49) {

                                player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() - 1);

                            }

                        }

                    }

                }

            }

        }

    }

    @SubscribeEvent
    public void onHurt(LivingHurtEvent event) {

        if(event.entity instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer)event.entity;

            for(int i = 1; i < 5; i++) {

                if(player.getEquipmentInSlot(i) != null) {

                    if(player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor) {

                        if(player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage()) {

                            player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() + 1);
                            WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(i)).onAttacked(event);

                        }

                    }

                }

            }

        }

    }

}
