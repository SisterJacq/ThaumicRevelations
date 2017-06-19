package magicbees.api.bees;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 *  Class to control plugin logic for the Transmutation bee effect. Turns blocks
 *  into other blocks based on biome, time of day, or whatever is deemed
 *  appropriate at the time.
 *
 *  @author MysteriousAges
 */
public interface ITransmutationEffectLogic {
	/**
	 *  Allows an external mod to add transmutation logic to the Transmuting bee effect.
	 *  Called from a list of ITransmutationEffectLogic until one returns true. You can
	 *  implement more than one transmutation effect in a single implementation of the
	 *  interface. Function checks for a series of conditions based on block ID/meta, or
	 *  biome information, or world information.
	 *
	 *  @param world World object
	 *  @param biome Biome information for current coordinates
	 *  @param sourceBlock ItemStack representing the block at current coordinates
	 *  @param x Current X coordinate to attempt a transmute
	 *  @param y Current Y coordinate to attempt a transmute
	 *  @param z Current Z coordinate to attempt a transmute
	 *  @return true if a block was changed.
	 */
	public boolean tryTransmutation(World world, BiomeGenBase biome, ItemStack sourceBlock, int x, int y, int z);
}