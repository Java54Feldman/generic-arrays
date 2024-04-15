package telran.shapes.exceptions;

@SuppressWarnings("serial")
public class ShapeAlreadyExistsExeption extends IllegalStateException {
	public ShapeAlreadyExistsExeption(long id) {
		super(String.format("Shape with id %d already exists", id));
	}
}
