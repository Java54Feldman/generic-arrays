package telran.shapes;

public abstract class Shape {
	protected long id;
	
	public Shape(long id) {
		this.id = id;
	}
	public abstract int square();
	public abstract int perimeter();
	
	public long getId() {
		return id;
	}
		
}
