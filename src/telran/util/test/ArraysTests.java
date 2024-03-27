package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.Arrays;

class ArraysTests {
Integer[] numbers = {100, -3, 23, 4, 8, 41, 56, -7};
String[] strings = {"abc", "lmn", "123", null, "a"};
String[] stringsMin = {"abc", "lmn", "123", "y"};
	@Test
	void indexOfTest() {
		assertEquals(1, Arrays.indexOf(strings, "lmn"));
		assertEquals(3, Arrays.indexOf(strings, null));
		assertEquals(-1, Arrays.indexOf(numbers, 150));
		assertEquals(4, Arrays.indexOf(numbers, 8));
	}
	@Test
	void minValueTest() {
		assertEquals("y", Arrays.min(stringsMin, new StringLengthComparator()));
		assertEquals("123", Arrays.min(stringsMin, new StringsComparator()));
	}
	@Test
	void bubbleSortTest() {
		Integer [] expected = {4, 8, 56, 100, 41, 23, -3, -7};
		Integer [] numbersCopy = java.util.Arrays.copyOf(numbers, numbers.length);
		// java.util.Arrays полное имя, потому что import telran.util.Arrays;
		Arrays.bubbleSort(numbersCopy, new EvenOddComparator());
		assertArrayEquals(expected, numbersCopy);
	}
}
