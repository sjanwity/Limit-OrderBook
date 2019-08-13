package domain;

import java.util.Comparator;

public class BuyOrderComparator implements Comparator<Order>{

	/**
	 * Sorts by price highest first. If price is the same, the order with the lower timestamp is ranked higher (earlier order). 
	 */
	public int compare(Order o1, Order o2) {
		if (o1.getPrice() < o2.getPrice())
			return 1;
		else if (o1.getPrice() == o2.getPrice())
			return 0;
		else return -1;

	}
}