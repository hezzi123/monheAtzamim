package Ex1;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Ex1.Monom> {

	public Monom_Comperator() {;}
	public int compare(Ex1.Monom o1, Ex1.Monom o2) {
		int dp = o2.get_power() - o1.get_power();
		return dp;
	}

	// ******** add your code below *********

}
