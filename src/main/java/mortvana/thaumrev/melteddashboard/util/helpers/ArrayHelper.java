package mortvana.thaumrev.melteddashboard.util.helpers;

public class ArrayHelper {

	public static boolean areAllValuesEqual(int[] values) {
		if (values.length > 2) {
			for (int i = 1; i < values.length; i++) {
				if (values[i] != values[i-1]) {
					return false;
				}
			}
			return true;
		} else {
			return values.length != 2 || values[0] == values[1];
		}
	}

	public static int getOddValue(int[] values) {
		int base = values[0];
		int ret = -1;
		boolean cycle = true;
		for (int i = 1; i < values.length; i++) {
			if (!(base == values[i])) {
				if (cycle) {
					ret = i;
					cycle = false;
				} else {
					cycle = true;
					break;
				}
			}
		}
		if (cycle) {
			base = values[1];
			for (int i = 0; i < values.length; i++) {
				if (!(base == values[i])) {
					if (cycle) {
						ret = i;
						cycle = false;
					} else {
						return -1;
					}
				}
			}
		}
		return ret;
	}
}
