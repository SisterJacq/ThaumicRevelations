package mortvana.melteddashboard.util.helpers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import mortvana.thaumrev.library.ThaumRevLibrary;

public class NBTHelper {

	private static final byte[] NULL_BYTES = new byte[] {};
	private static final int[] NULL_INTS = new int[] {};

	private NBTHelper() {}

	/** NBT TAG COMPOUND GETTERS/SETTERS **/
	public static boolean hasTag(ItemStack stack) {
		return stack.hasTagCompound();
	}

	public static NBTTagCompound getTag(ItemStack stack) {
		return stack.getTagCompound();
	}

	public static boolean hasKey(ItemStack stack, String key) {
		return hasTag(stack) && getTag(stack).hasKey(key);
	}

	public static void ensureNBT(ItemStack stack) {
		if (!hasTag(stack)) {
			stack.setTagCompound(new NBTTagCompound());
		}
	}

	/** PRIMITIVE VALUE GETTERS (NBT) **/
	public static boolean getBoolean(NBTTagCompound tag, String key) { //NBT TYPE 1
		return tag.getBoolean(key);
	}

	public static byte getByte(NBTTagCompound tag, String key) { //NBT TYPE 1
		return tag.getByte(key);
	}

	public static short getShort(NBTTagCompound tag, String key) { //NBT TYPE 2
		return tag.getShort(key);
	}

	public static int getInt(NBTTagCompound tag, String key) { //NBT TYPE 3
		return tag.getInteger(key);
	}

	public static long getLong(NBTTagCompound tag, String key) { //NBT TYPE 4
		return tag.getLong(key);
	}

	public static float getFloat(NBTTagCompound tag, String key) { //NBT TYPE 5
		return tag.getFloat(key);
	}

	public static double getDouble(NBTTagCompound tag, String key) { //NBT TYPE 6
		return tag.getDouble(key);
	}

	/** OBJECT VALUE GETTERS (NBT) **/
	public static byte[] getBytes(NBTTagCompound tag, String key) { //NBT TYPE 7
		return tag.getByteArray(key);
	}

	public static String getString(NBTTagCompound tag, String key) { //NBT TYPE 8
		return tag.getString(key);
	}

	public static NBTTagCompound getSubTag(NBTTagCompound tag, String key) { //NBT TYPE 9
		return tag.getCompoundTag(key);
	}

	public static NBTTagList getList(NBTTagCompound tag, String key, int type) { //NBT TYPE 10
		return tag.getTagList(key, type);
	}

	public static int[] getInts(NBTTagCompound tag, String key) { //NBT TYPE 11
		return tag.getIntArray(key);
	}

	/** PRIMITIVE VALUE GETTERS (ITEMSTACK) **/
	public static boolean getBoolean(ItemStack stack, String key) { //NBT TYPE 1
		return hasKey(stack, key) && getBoolean(getTag(stack), key);
	}

	public static byte getByte(ItemStack stack, String key) { //NBT TYPE 1
		return hasKey(stack, key) ? getByte(getTag(stack), key) : 0;
	}

	public static short getShort(ItemStack stack, String key) { //NBT TYPE 2
		return hasKey(stack, key) ? getShort(getTag(stack), key) : 0;
	}

	public static int getInt(ItemStack stack, String key) { //NBT TYPE 3
		return hasKey(stack, key) ? getInt(getTag(stack), key) : 0;
	}

	public static long getLong(ItemStack stack, String key) { //NBT TYPE 4
		return hasKey(stack, key) ? getLong(getTag(stack), key) : 0;
	}

	public static float getFloat(ItemStack stack, String key) { //NBT TYPE 5
		return hasKey(stack, key) ? getFloat(getTag(stack), key) : 0;
	}

	public static double getDouble(ItemStack stack, String key) { //NBT TYPE 6
		return hasKey(stack, key) ? getDouble(getTag(stack), key) : 0;
	}

	/** OBJECT VALUE GETTERS (ITEMSTACK) **/
	public static byte[] getBytes(ItemStack stack, String key) { //NBT TYPE 7
		return hasKey(stack, key) ? getBytes(getTag(stack), key) : NULL_BYTES;
	}

	public static String getString(ItemStack stack, String key) { //NBT TYPE 8
		return hasKey(stack, key) ? getString(getTag(stack), key) : null;
	}

	public static NBTTagCompound getSubTag(ItemStack stack, String key) { //NBT TYPE 9
		return hasKey(stack, key) ? getSubTag(getTag(stack), key) : null;
	}

	public static NBTTagList getList(ItemStack stack, String key, int type) { //NBT TYPE 10
		return hasKey(stack, key) ? getList(getTag(stack), key, type) : null;
	}

	public static int[] getInts(ItemStack stack, String key) { //NBT TYPE 11
		return hasKey(stack, key) ? getInts(getTag(stack), key) : NULL_INTS;
	}

	/** STACK GETTERS **/
	public static ItemStack getItemStack(ItemStack stack, String key) {
		return hasKey(stack, key) ? ItemStack.loadItemStackFromNBT(getSubTag(stack, key)) : null;
	}

	public static FluidStack getFluidStack(ItemStack stack, String key) {
		if (hasKey(stack, key)) {
			NBTTagCompound nbt = getSubTag(stack, key);
			String name = getString(nbt, "fluid");
			if (FluidRegistry.getFluid(name) != null) {
				return new FluidStack(FluidRegistry.getFluid(name), getInt(nbt, "amount"));
			}
		}
		return null;
	}

	/** PRIMITIVE VALUE SETTERS (NBT)**/
	public static void setBoolean(NBTTagCompound tag, String key, boolean value) { //NBT TYPE 1
		tag.setBoolean(key, value);
	}

	public static void setByte(NBTTagCompound tag, String key, byte value) { //NBT TYPE 1
		tag.setByte(key, value);
	}

	public static void setShort(NBTTagCompound tag, String key, short value) { //NBT TYPE 2
		tag.setShort(key, value);
	}

	public static void setInt(NBTTagCompound tag, String key, int value) { //NBT TYPE 3
		tag.setInteger(key, value);
	}

	public static void setLong(NBTTagCompound tag, String key, long value) { //NBT TYPE 4
		tag.setLong(key, value);
	}

	public static void setFloat(NBTTagCompound tag, String key, float value) { //NBT TYPE 5
		tag.setFloat(key, value);
	}

	public static void setDouble(NBTTagCompound tag, String key, double value) { //NBT TYPE 6
		tag.setDouble(key, value);
	}

	/** OBJECT VALUE SETTERS (NBT) **/
	public static void setBytes(NBTTagCompound tag, String key, byte[] value) { //NBT TYPE 7
		tag.setByteArray(key, value);
	}

	public static void setString(NBTTagCompound tag, String key, String value) { //NBT TYPE 8
		tag.setString(key, value);
	}

	public static void setSubTag(NBTTagCompound tag, String key, NBTBase value) { //NBT TYPE 9/10
		tag.setTag(key, value);
	}

	public static void setInts(NBTTagCompound tag, String key, int[] value) { //NBT TYPE 11
		tag.setIntArray(key, value);
	}

	/** PRIMITIVE VALUE SETTERS (ITEMSTACK) **/
	public static void setBoolean(ItemStack stack, String key, boolean value) { //NBT TYPE 1
		if (hasKey(stack, key)) {
			setBoolean(getTag(stack), key, value);
		}
	}

	public static void setByte(ItemStack stack, String key, byte value) { //NBT TYPE 1
		if (hasKey(stack, key)) {
			setByte(getTag(stack), key, value);
		}
	}

	public static void setShort(ItemStack stack, String key, short value) { //NBT TYPE 2
		if (hasKey(stack, key)) {
			setShort(getTag(stack), key, value);
		}
	}

	public static void setInt(ItemStack stack, String key, int value) { //NBT TYPE 3
		if (hasKey(stack, key)) {
			setInt(getTag(stack), key, value);
		}
	}

	public static void setLong(ItemStack stack, String key, long value) { //NBT TYPE 4
		if (hasKey(stack, key)) {
			setLong(getTag(stack), key, value);
		}
	}

	public static void setFloat(ItemStack stack, String key, float value) { //NBT TYPE 5
		if (hasKey(stack, key)) {
			setFloat(getTag(stack), key, value);
		}
	}

	public static void setDouble(ItemStack stack, String key, double value) { //NBT TYPE 6
		if (hasKey(stack, key)) {
			setDouble(getTag(stack), key, value);
		}
	}

	/** OBJECT VALUE SETTERS (ITEMSTACK) **/
	public static void setBytes(ItemStack stack, String key, byte[] value) { //NBT TYPE 7
		if (hasKey(stack, key)) {
			setBytes(getTag(stack), key, value);
		}
	}

	public static void setString(ItemStack stack, String key, String value) { //NBT TYPE 8
		if (hasKey(stack, key)) {
			setString(getTag(stack), key, value);
		}
	}

	public static void setSubTag(ItemStack stack, String key, NBTBase value) { //NBT TYPE 9/10
		if (hasKey(stack, key)) {
			setSubTag(getTag(stack), key, value);
		}
	}

	public static void setInts(ItemStack stack, String key, int[] value) { //NBT TYPE 11
		if (hasKey(stack, key)) {
			setInts(getTag(stack), key, value);
		}
	}

	/** STACK SETTERS **/
	public static void setItemStack(ItemStack stack, String key, ItemStack value) {
		setSubTag(stack, key, value.writeToNBT(new NBTTagCompound()));
	}

	public static void setFluidStack(ItemStack stack, String key, FluidStack value) {
		if (value != null && value.getFluid() != null) {
			NBTTagCompound nbt = getSubTag(stack, key);
			setString(nbt, "fluid", value.getFluid().getName());
			setInt(nbt, "amount", value.amount);
			setSubTag(stack, key, nbt);
		} else {
			remove(stack, key);
		}
	}

	/** OTHER GENERAL SETTERS **/
	public static void remove(ItemStack stack, String key) {
		if (hasKey(stack, key)) {
			getTag(stack).removeTag(key);
			if (getTag(stack).hasNoTags()) {
				stack.setTagCompound(null);
			}
		}
	}

	//public static
	//



	public static boolean isRevealingGoggles(ItemStack stack, EntityLivingBase entity) {
		return (entity instanceof EntityPlayer && stack.getItem() instanceof ItemArmor && ((ItemArmor) stack.getItem()).armorType == 0 && getBoolean(stack, ThaumRevLibrary.REVEALING));
	}

	public static boolean isBroken(ItemStack stack) {
		return getBoolean(stack, ThaumRevLibrary.BROKEN);
	}

	public static ItemStack setBroken(ItemStack stack, boolean bool) {
		ensureNBT(stack);
		setBoolean(stack, ThaumRevLibrary.BROKEN, bool);
		return stack;
	}

}
