package magicbees.api;

import magicbees.api.bees.ITransmutationEffectController;

public class MagicBeesAPI {
	// Use this object during or after the Mod.Init phase to add your logic to the Transmutation bee effect.
	public static ITransmutationEffectController transmutationEffectController;

	// This is a reference to the Tempus aspect. Initialized after MagicBees's post init phase. 
	// Ensure it is not null, and cast it to thaumcraft.api.aspects.Aspect.
	public static Object thaumcraftAspectTempus;
}