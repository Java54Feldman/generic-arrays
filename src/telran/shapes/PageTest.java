package telran.shapes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.shapes.exceptions.*;

class PageTest {
	private static final long ID11 = 11;
	private static final int WIDTH1 = 2;
	private static final int HEIGHT1 = 3;
	private static final long ID21 = 21;
	private static final int SIZE1 = 2;
	private static final long ID12 = 12;
	private static final int SIZE2 = 3;
	private static final long ID22 = 22;
	private static final int SIZE3 = 4;
	private static final long ID31 = 31;
	Shape shape11 = new Rectangle(ID11, WIDTH1, HEIGHT1);
	Shape shape21 = new Square(ID21, SIZE1);
	Shape shape12 = new Square(ID12, SIZE2);
	Shape shape22 = new Square(ID22, SIZE3);
	Shape shape31 = new Square(ID31, SIZE3);
	Canvas canvas1;
	Canvas canvas2;
	Canvas canvas3;
	Shape[] shapes1 = { shape11, shape12 };
	Shape[] shapes2;
	Shape[] shapes3;
	Page page = new Page();

	@BeforeEach
	void setUp() {
		canvas1 = getCanvas(1, shapes1);
		shapes2 = new Shape[] { shape22, canvas1 };
		canvas2 = getCanvas(2, shapes2);
		shapes3 = new Shape[] { canvas2, shape21 };
		canvas3 = getCanvas(3, shapes3);
		page.addShape(canvas3);
	}

	private Canvas getCanvas(long id, Shape[] shapes) {
		Canvas result = new Canvas(id);
		for (Shape shape : shapes) {
			result.addShape(shape);
		}
		return result;
	}

	@Test
	void testAddShapeShape() {
		Shape[] expected = { canvas3, shape31, shape11 };
		page.addShape(shape31);
		page.addShape(shape11);
		Shape[] actual = new Shape[3];
		assertArrayEquals(expected, toArrayFromIterable(actual, page));
		assertThrowsExactly(ShapeAlreadyExistsException.class, () -> page.addShape(canvas3));
	}

	@Test
	void testAddShapeLongArrayShape() {
		Long[] path = { canvas3.getId(), canvas2.getId(), canvas1.getId() };
		Shape[] expected = { shape11, shape12, shape31 }; //canvas1 + shape31
		page.addShape(path, shape31);
		Shape[] actual = new Shape[3];
		assertArrayEquals(expected, toArrayFromIterable(actual, canvas1));
		
		Long[] path2 = { 33l };
		assertThrowsExactly(ShapeNotFoundException.class, () -> page.addShape(path2, new Square(44, SIZE1)));

		Long[] path3 = { canvas3.getId(), shape21.getId() };
	    assertThrowsExactly(NoCanvasException.class, () -> page.addShape(path3, new Square(44, SIZE1)));
	}

	@Test
	void testRemoveShapeLong() {
		page.addShape(shape31);
		page.addShape(shape11);
	    Shape removedShape = page.removeShape(shape31.getId());
	    assertEquals(shape31, removedShape);
	    Shape[] expected = { canvas3, shape11 };
	    Shape[] actual = new Shape[2];
	    assertArrayEquals(expected, toArrayFromIterable(actual, page));
	    assertNull(page.removeShape(99l));
	}

	@Test
	void testRemoveShapeLongArrayLong() {
		Long[] path = { canvas3.getId(), canvas2.getId(), canvas1.getId() };
		Shape[] expected = { shape11 }; //canvas1 - shape12
		Shape removedShape = page.removeShape(path, shape12.getId());
		assertEquals(shape12, removedShape);
		Shape[] actual = new Shape[1];
		assertArrayEquals(expected, toArrayFromIterable(actual, canvas1));
		assertThrowsExactly(ShapeNotFoundException.class, () -> page.removeShape(path, 99l));
	}

	@Test
	void testIterator() {
		page.addShape(canvas1);
		page.addShape(shape11);
		page.addShape(shape12);
		page.addShape(shape22);
		Shape[] expected = { canvas3, canvas1, shape11, shape12, shape22 };
		
	    Iterator<Shape> it = page.iterator();
	    int index = 0;
	    while (it.hasNext()) {
	        assertEquals(expected[index++], it.next());
	    }
	    assertEquals(expected.length, index); // в данном случае эта проверка обязательна
	    assertThrowsExactly(NoSuchElementException.class, () -> it.next());
	}

	protected <T> T[] toArrayFromIterable(T[] array, Iterable<T> iterable) {
		int index = 0;
		for (T obj : iterable) {
			array[index++] = obj;
		}
		return array;
	}

}