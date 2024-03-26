package telran.util;

import java.util.Comparator;

public class Arrays {
	public static <T> int indexOf(T[] array, T element) {
		// T - any object
		// в java нет однобуквенных стандартных типов данных
		int index = 0;
		while(index < array.length && !equals(array[index], element) ) {
			index++;
		}
		return index == array.length ? -1 : index ;
	}

	private static <T> boolean equals(T elem1, T elem2) {
		
		return elem1 == null ? elem1 == elem2 : elem1.equals(elem2);
	}
	public static <T> T min(T[] array, Comparator<T> comp) {
		T res = null;
		if(array != null && array.length > 0) {
			res = array[0];
			for(int i = 1; i < array.length; i++) {
				if(comp.compare(res, array[i]) > 0) res = array[i];
			}
		}
		return res;
	}
	public static <T> void bubbleSort(T[] array, Comparator<T> comp) {
		//TODO
	}
}
