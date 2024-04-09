package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Objects;

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
		Integer[] expectedEven = { 100, 4, 8, 56 };
		assertArrayEquals(expectedEven, Arrays.search(numbers, a -> a % 2 == 0));
		Integer[] expectedNegative = { -3, -7 };
		assertArrayEquals(expectedNegative, Arrays.search(numbers, a -> a < 0));
	}

	@Test
	void binarySearchTest() {
		Integer[] sortedNumbers = { 10, 20, 30, 40, 50 };
		Comparator<Integer> comp = Integer::compare;
		assertEquals(0, Arrays.binarySearch(sortedNumbers, 10, comp));
		assertEquals(4, Arrays.binarySearch(sortedNumbers, 50, comp));
		assertEquals(2, Arrays.binarySearch(sortedNumbers, 30, comp));
		assertEquals(-1, Arrays.binarySearch(sortedNumbers, 5, comp));
		assertEquals(-4, Arrays.binarySearch(sortedNumbers, 35, comp));
		assertEquals(-6, Arrays.binarySearch(sortedNumbers, 55, comp));
	}

	@Test
	void removeIfTest() {
		Integer[] expectedEven = { 100, 4, 8, 56 };
		assertArrayEquals(expectedEven, Arrays.removeIf(numbers, a -> a % 2 != 0));
		Integer[] expectedNegative = { -3, -7 };
		assertArrayEquals(expectedNegative, Arrays.removeIf(numbers, a -> a > 0));
	}

	@Test
	void addTest() {
		Integer[] expected = { 100, -3, 23, 4, 8, 41, 56, -7, 150 };
		Integer[] actual = Arrays.add(numbers, 150);
		assertArrayEquals(expected, actual);
	}

	@Test
	void personsSortTest() {
		Person prs1 = new Person(123, 1985);
		Person prs2 = new Person(120, 2000);
		Person prs3 = new Person(128, 1999);
		Person[] persons = { prs1, prs2, prs3 };
		Arrays.bubbleSort(persons);
		Person[] expected = { 
				new Person(120, 2000), 
				new Person(123, 1985), 
				new Person(128, 1999) 
				};
		Person[] expectedAge = {
				new Person(120, 2000), 
				new Person(128, 1999),
				new Person(123, 1985), 
		};
		assertArrayEquals(expected, persons);
		Arrays.bubbleSort(persons, 
				(p1, p2) -> Integer.compare(p2.birthYear, p1.birthYear));
		assertArrayEquals(expectedAge, persons);
	}
}

class Person implements Comparable<Person> {
	long id;
	int birthYear;

	public Person(long id, int birthYear) {
		super();
		this.id = id;
		this.birthYear = birthYear;
	}

	@Override
	public int compareTo(Person o) {

		return Long.compare(id, o.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthYear, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return birthYear == other.birthYear && id == other.id;
	}
	

}
