package mortvana.thaumrev.api.item.infusion;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;

import mortvana.thaumrev.api.util.enums.EnumInfusionType;

public interface IInfusionItem {

	Aspect getAspect(ItemStack stack);

	IInfusionAspect getInfusionAspect(ItemStack stack);

	float getPotency(ItemStack stack);

	EnumInfusionType getInfusionType(ItemStack stack);

	int getStorage(ItemStack stack);

	int getDrain(ItemStack stack);
}
