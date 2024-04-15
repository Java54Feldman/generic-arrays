package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.shapes.exceptions.ShapeAlreadyExistsExeption;
import telran.util.Arrays;

public class Canvas extends Shape implements Iterable<Shape> {
	protected Shape[] shapes = new Shape[0];

	public Canvas(long id) {
		super(id);
	}

	public Shape[] addShape(Shape shape) {
		if(Arrays.indexOf(shapes, shape) > -1) {
			throw new ShapeAlreadyExistsExeption(shape.getId());
		}
		shapes = Arrays.add(shapes, shape);
		return Arrays.copy(shapes);
	}

	public Shape[] removeShape(long id) {
		//FIXME see UML diagram
		//найти ...
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
