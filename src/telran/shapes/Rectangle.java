package telran.shapes;

public class Rectangle extends Shape {
	private int width;
	private int height;

	public Rectangle(long id, int width, int height) {
		super(id);
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public int square() {
		return this.width * this.height;
	}

	@Override
	public int perimeter() {
		return (this.width + this.height) * 2;
	}

}
