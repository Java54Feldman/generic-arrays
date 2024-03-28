package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import telran.util.Arrays;

class ArraysTests {
	Integer[] numbers = { 100, -3, 23, 4, 8, 41, 56, -7 };
	String[] strings = { "abc", "lmn", "123", null, "a" };
	String[] stringsMin = { "abc", "lmn", "123", "y" };

	@Test
	void indexOfTest() {
		assertEquals(1, Arrays.indexOf(strings, "lmn"));
		assertEquals(3, Arrays.indexOf(strings, null));
		assertEquals(-1, Arrays.indexOf(numbers, 150));
		assertEquals(4, Arrays.indexOf(numbers, 8));
	}

	@Test
	void minValueTest() {
		Comparator<String> compLength = (s1, s2) -> s1.length() - s2.length();
		assertEquals("y", Arrays.min(stringsMin, compLength));
		Comparator<String> compNative = (s1, s2) -> s1.compareTo(s2);
		assertEquals("123", Arrays.min(stringsMin, compNative));
	}

	@Test
	void bubbleSortTest() {
		Integer[] expected = { 4, 8, 56, 100, 41, 23, -3, -7 };
		Integer[] numbersCopy = java.util.Arrays.copyOf(numbers, numbers.length);
		/* lambda expression example */
//		Comparator<Integer> evenOddComp = (o1, o2) -> evenOddComparator(o1, o2);
//		Arrays.bubbleSort(numbersCopy, evenOddComp);
		/*********************************************/
		/* method reference */		
		Comparator<Integer> evenOddComp = ArraysTests::evenOddComparator;
		/*********************************************/
		Arrays.bubbleSort(numbersCopy, evenOddComp);
		assertArrayEquals(expected, numbersCopy);
	}
	static int evenOddComparator(Integer o1, Integer o2) {
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
			res = -1;
		}
		return res;
	};
	@Test
	void searchTest() {
		Integer[] expectedEven = {100, 4, 8, 56};
		assertArrayEquals(expectedEven, Arrays.search(numbers, 
				a -> a % 2 == 0));
		Integer[] expectedNegative = {-3, -7};
		assertArrayEquals(expectedNegative, Arrays.search(numbers,
				a -> a < 0));
	}
	@Test
	void binarySearchTest() {
		//TODO
	}
	@Test
	void removeIfTest() {
		//TODO
	}
}