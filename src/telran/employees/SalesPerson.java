package telran.employees;

public class SalesPerson extends WageEmployee {
	private float percent;
	private long sales;
	//Constructor of class SalesPerson must take additional parametres (see UML schema)
	public SalesPerson(long id, int basicSalary, String department, int hours, int wage, float percent, long sales) {
		super(id, basicSalary, department, hours, wage);
		this.percent = percent;
		this.sales = sales;
	}
	
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	public long getSales() {
		return sales;
	}
	public void setSales(long sales) {
		this.sales = sales;
	}
	
	@Override
	public int computeSalary() {
		return Math.round(super.computeSalary() + this.sales * this.percent / 100);		
	}

}
