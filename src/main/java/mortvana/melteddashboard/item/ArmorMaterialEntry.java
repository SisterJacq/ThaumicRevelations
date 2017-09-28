package mortvana.melteddashboard.item;

public class ArmorMaterialEntry {

	public int durability;
	public int[] protection;
	public int enchant;

	public ArmorMaterialEntry(int durability, int[] protection, int enchant) {
		this.durability = durability;
		this.protection = protection;
		this.enchant = enchant;
	}

	/** SETTERS **/
	public void setDurability(int durability) {
		this.durability = durability;
	}

	public void setProtectionValues(int[] protection) {
		this.protection = protection;
	}

	public void setEnchantability(int enchant) {
		this.enchant = enchant;
	}

	public void setProtectionForSlot(int type, int amount) {
		protection[type] = amount;
	}

	public void setHelmProtection(int amount) {
		protection[0] = amount;
	}

	public void setChestProtection(int amount) {
		protection[1] = amount;
	}

	public void getLegsProtection(int amount) {
		protection[2] = amount;
	}

	public void setBootProtection(int amount) {
		protection[3] = amount;
	}

	/** GETTERS **/
	public int getDurability() {
		return durability;
	}

	public int[] getProtectionValues() {
		return protection;
	}

	public int getEnchantability() {
		return enchant;
	}

	public int getProtectionForSlot(int type) {
		return protection[type];
	}

	public int getHelmProtection() {
		return protection[0];
	}

	public int getChestProtection() {
		return protection[1];
	}

	public int getLegsProtection() {
		return protection[2];
	}

	public int getBootProtection() {
		return protection[3];
	}

}
