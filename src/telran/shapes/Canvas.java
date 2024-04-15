package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;

public class Canvas extends Shape implements Iterable {
	Shape[] shapes;

	public Canvas(long id) {
		super(id);
		shapes = new Shape[0];
	}

	public Shape[] addShape(Shape shape) {
		shapes = Arrays.add(shapes, shape);
		return Arrays.copy(shapes);
	}

	public Shape[] removeShape(long id) {
		shapes = Arrays.removeIf(this.shapes, e -> e.getId() == id);
		return Arrays.copy(shapes);
	}

	@Override
	public int square() {
		int res = 0;
		for (Shape shape : shapes) {
			res += shape.square();
		}
		return res;
	}

	@Override
	public int perimeter() {
		int res = 0;
		for (Shape shape : shapes) {
			res += shape.perimeter();
		}
		return res;
	}

	@Override
	public Iterator iterator() {
		return new CanvasIterator();
	}

	private class CanvasIterator implements Iterator<Shape> {
		int index = 0;

		@Override
		public boolean hasNext() {
			return index < shapes.length;
		}

		@Override
		public Shape next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return shapes[index++];
		}
	}

}
