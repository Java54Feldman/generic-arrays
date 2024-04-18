package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.shapes.exceptions.NoCanvasException;
import telran.shapes.exceptions.ShapeAlreadyExistsException;
import telran.shapes.exceptions.ShapeNotFoundException;
import telran.util.*;

public class Page implements Iterable<Shape> {
	// инициализация поля, чтобы избежать NullPointerException
	// объект нужно создать, примитив необязательно инициализировать
	private Shape[] shapes = new Shape[0];

	public void addShape(Shape shape) {
		for(Shape sh : shapes) {
			if(sh.getId() == shape.getId()) {
				throw new ShapeAlreadyExistsException(shape.getId());
			}
		}
//		if (Arrays.indexOf(shapes, shape) > -1) {
//			throw new ShapeAlreadyExistsException(shape.getId());
//		}
		shapes = Arrays.add(shapes, shape);
	}

	public void addShape(Long[] canvasIds, Shape shape) {
		Canvas canvas = getCanvas(canvasIds);
		canvas.addShape(shape);
	}

	private Canvas getCanvas(Long[] canvasIds) {
		Canvas canvas = getCanvasById(shapes, canvasIds[0]);
		for (int i = 1; i < canvasIds.length; i++) {
			canvas = getCanvasById(canvas.shapes, canvasIds[i]);
		}
		return canvas;
	}

	private Canvas getCanvasById(Shape[] shapes, Long id) {
		int index = Arrays.indexOf(shapes, new Canvas(id));
		if (index < 0)
			throw new ShapeNotFoundException(id);
		Shape shape = shapes[index];
		Canvas result = null;
		if (shape instanceof Canvas)
			result = (Canvas) shape;
		else
			throw new NoCanvasException(id);

		return result;
	}
	private Shape getShapeById (Shape[] shapes, long id) {
		for(Shape shape : shapes) {
			if(shape.getId() == id) {
				return shape;
			}
		}
		return null;
	}

	public Shape removeShape(long id) {
//		int index = Arrays.indexOf(shapes, new Canvas(id));
//		if (index < 0)
//			throw new ShapeNotFoundException(id);
//		Shape shape = shapes[index];
//		shapes = Arrays.removeIf(shapes, s -> s == shape);
//		return shape;
		Shape removedShape = getShapeById(shapes, id);
		if(removedShape != null) {
			shapes = Arrays.removeIf(shapes, s -> s.getId() == id );
		}
		return removedShape;
	}

	public Shape removeShape(Long[] canvasIds, long id) {
		Canvas canvas = getCanvas(canvasIds);
		Shape removed = null;
		for (int i = 0; i < canvas.shapes.length && removed == null; i++) {
			if(canvas.shapes[i].getId() == id) {
				removed = canvas.shapes[i];
			}
		}
		if(removed == null) throw new ShapeNotFoundException(id);
		else canvas.removeShape(id);
		return removed;
	}

	@Override
	public Iterator<Shape> iterator() {
		return new PageIterator();
	}

	private class PageIterator implements Iterator<Shape> {
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
