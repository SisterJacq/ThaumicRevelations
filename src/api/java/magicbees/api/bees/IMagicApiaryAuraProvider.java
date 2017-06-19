package magicbees.api.bees;

/**
 *  Allows a tile entity to provide a Magic Apiary with 'aura' to boost bee production, mutation rate,
 *  or decrease bee lifespan.
 *
 *  @author MysteriousAges
 */
public interface IMagicApiaryAuraProvider {

	/**
	 *  Requests a mutation charge from the aura provider. If true is returned, the
	 *  aura provider should decrement its internal charge by one. If there are no
	 *  charges remaining, it should return false.
	 *
	 *  @return True if a charge is given; false otherwise.
	 */
	boolean getMutationCharge();

	/**
	 *  Requests a mutation charge from the aura provider. If true is returned, the
	 *  aura provider should decrement its internal charge by one. If there are no
	 *  charges remaining, it should return false.
	 *
	 *  @return True if a charge is given; false otherwise.
	 */
	boolean getDeathRateCharge();

	/**
	 *  Requests a mutation charge from the aura provider. If true is returned, the
	 *  aura provider should decrement its internal charge by one. If there are no
	 *  charges remaining, it should return false.
	 *
	 *  @return True if a charge is given; false otherwise.
	 */
	boolean getProductionCharge();

	/**
	 *  Requests a mutation charge from the aura provider. If true is returned, the
	 *  aura provider should decrement its internal charge by one. If there are no
	 *  charges remaining, it should return false.
	 *
	 *  @return True if a charge is given; false otherwise.
	 */
	boolean getCharge(AuraChargeType auraChargeType);
}