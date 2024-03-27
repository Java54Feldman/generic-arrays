package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		// first even numbers sorted in the ascending order
		// last odd numbers sorted in the descending order
		boolean isEven1 = o1 % 2 == 0;
		boolean isEven2 = o2 % 2 == 0;

		if (isEven1 && isEven2) {
			return o1.compareTo(o2);
		} else if (!isEven1 && !isEven2) {
			return o2.compareTo(o1);
		} else {
			return isEven1 ? -1 : 1;
		}
	}

}
