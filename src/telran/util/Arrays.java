package telran.util;

import java.util.Comparator;

public class Arrays {
	public static <T> int indexOf(T[] array, T element) {
		// T - any object
		// в java нет однобуквенных стандартных типов данных
		int index = 0;
		while (index < array.length && !equals(array[index], element)) {
			index++;
		}
		return index == array.length ? -1 : index;
	}

	private static <T> boolean equals(T elem1, T elem2) {
		return elem1 == null ? elem1 == elem2 : elem1.equals(elem2);
	}

	public static <T> T min(T[] array, Comparator<T> comp) {
		T res = null;
		if (array != null && array.length > 0) {
			res = array[0];
			for (int i = 1; i < array.length; i++) {
				if (comp.compare(res, array[i]) > 0)
					res = array[i];
			}
		}
		return res;
	}

	public static <T> void bubbleSort(T[] array, Comparator<T> comp) {
		boolean isUnsorted = true;
		int length = array.length;
		while (isUnsorted) {
			length--;
			isUnsorted = false;
			for (int i = 0; i < length; i++) {
				if (comp.compare(array[i], array[i + 1]) > 0) {
					swap(array, i, i + 1);
					isUnsorted = true;
				}
			}
		}
	}
	
	private static <T> void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	public static <T> int binarySearch(T[] array, T key, Comparator<T> comp) {
		
		//left index = 0
		//right index = array.length - 1
		//middle (left + right) / 2
		//left part - left index, right index = middle - 1
		//right part - left index = middle + 1, right index
		//while left <= right
		//returns exactly what the standard binarySearch does
		//if there are several equaled elements no guarantee that 
		// being returned index is the one to first occurrence
		return -1;
	}
}
