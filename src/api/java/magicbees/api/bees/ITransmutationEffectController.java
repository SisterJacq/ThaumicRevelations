package magicbees.api.bees;

/**
 *  Manages adding custom ITransmutationEffectLogic instances into the bee effect system.
 *
 *  @author MysteriousAges
 */
public interface ITransmutationEffectController {
	public void addEffectLogic(ITransmutationEffectLogic logic);

}