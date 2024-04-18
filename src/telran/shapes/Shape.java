package telran.shapes;

import java.util.Objects;

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
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		if(obj != null && obj instanceof Shape) {
			res = id == ((Shape)obj).id;
		}
		return res;
		
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
////		if(!(obj instanceof Shape)) 
////		return false;
//		Shape other = (Shape) obj;
//		return id == other.id;
	}
		
}
