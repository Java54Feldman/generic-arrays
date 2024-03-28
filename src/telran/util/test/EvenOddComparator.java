package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		// first even numbers sorted in the ascending order
		// last odd numbers sorted in the descending order
		int res = 1;
		boolean isEven1 = o1 % 2 == 0;
		boolean isEven2 = o2 % 2 == 0;

		if (isEven1 && isEven2) {
			res = Integer.compare(o1, o2);
		} else if (!isEven1 && !isEven2) {
			res = Integer.compare(o2, o1);
		} else if (isEven1) {
			res =  -1;
		}
		return res;
	}

}
