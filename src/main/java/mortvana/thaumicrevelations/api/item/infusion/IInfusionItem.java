package mortvana.thaumicrevelations.api.item.infusion;

import net.minecraft.item.ItemStack;

import mortvana.thaumicrevelations.api.util.enums.EnumInfusionType;
import thaumcraft.api.aspects.Aspect;

public interface IInfusionItem {

	Aspect getAspect(ItemStack stack);
	IInfusionAspect getInfusionAspect(ItemStack stack);
	float getPotency(ItemStack stack);
	EnumInfusionType getInfusionType(ItemStack stack);
	int getStorage(ItemStack stack);
	int getDrain(ItemStack stack);
}
