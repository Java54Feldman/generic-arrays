package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.shapes.*;

class ShapeTest {
	Rectangle rect1 = new Rectangle(100, 5, 3);
	Rectangle rect2 = new Rectangle(101, 4, 8);
	Square square1 = new Square(200, 3);
	Square square2 = new Square(201, 7);
	Canvas canvas1 = new Canvas(300);
	Canvas canvas2 = new Canvas(301);
	Canvas canvas3 = new Canvas(302);

	@Test
	void addShapeTest() {
		canvas1.addShape(rect1);
		canvas1.addShape(rect2);
		canvas1.addShape(square1);
		Shape[] expected = { rect1, rect2, square1 };
		Shape[] actual = new Shape[expected.length];
		assertArrayEquals(expected, toArrayFromIterable(actual, canvas1));
	}
	
	@Test
	void removeShapeTest() {
		canvas2.addShape(rect1);
		canvas2.addShape(rect2);
		canvas2.addShape(square1);
		canvas2.addShape(square2);

		Shape[] expected = { rect1, square1, square2 };
		Shape[] actual = canvas2.removeShape(rect2.getId());

		assertArrayEquals(expected, toArrayFromIterable(actual, canvas2));
	}
	
	@Test
	void squareAndPerimeterTest() {
		canvas1.addShape(rect1);
		canvas1.addShape(rect2);
		canvas1.addShape(square1);
		assertEquals(5*3 + 4*8 + 3*3, canvas1.square());
		assertEquals((5+3 + 4+8 + 3+3)*2, canvas1.perimeter()); 
		
		canvas3.addShape(rect1);
		canvas3.addShape(rect2);
		canvas3.addShape(square1);
		canvas3.addShape(canvas1);
		assertEquals((5*3 + 4*8 + 3*3)*2, canvas3.square());
		assertEquals((5+3 + 4+8 + 3+3)*2*2, canvas3.perimeter());
		
		canvas2.addShape(square2);
		assertEquals(7*7, canvas2.square());
		assertEquals((7+7)*2, canvas2.perimeter()); 
	}

	protected <T> T[] toArrayFromIterable(T[] array, Iterable<T> iterable) {
		int index = 0;
		for (T obj : iterable) {
			array[index++] = obj;
		}
		return array;
	}
}
