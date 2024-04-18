package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import telran.employees.*;
import telran.util.Arrays;

class CompanyTest {
	private static final long ID1 = 123;
	private static final long ID2 = 120;
	private static final long ID3 = 125;
	private static final long ID4 = 151;
	private static final long ID5 = 147;
	private static final long ID6 = 131;
	private static final int SALARY1 = 1000;
	private static final String DEPARTMENT1 = "Development";
	private static final int SALARY2 = 2000;
	private static final int SALARY3 = 3000;
	private static final String DEPARTMENT2 = "QA";
	private static final int SALARY4 = 2500;
	private static final String DEPARTMENT3 = "R&D";
	private static final int HOURS_EMPL4 = 182;
	private static final int WAGE_EMPL4 = 100;
	private static final String DEPARTMENT4 = "Managment";
	private static final float FACTOR_EMPL5 = 1.5f;
	private static final int HOURS_EMPL6 = 190;
	private static final int WAGE_EMPL6 = 95;
	private static final float PERCENT_EMPL6 = 2.5f;
	private static final long SALES_EMPL6 = 10_000;
	Employee empl1 = new Employee(ID1, SALARY1, DEPARTMENT1);
	Employee empl2 = new Employee(ID2, SALARY2, DEPARTMENT1);
	Employee empl3 = new Employee(ID3, SALARY3, DEPARTMENT2);
	//there should be at least one object for all classes (WageEmployee, Manager, SalesPerson)
	WageEmployee empl4Wage = new WageEmployee(ID4, SALARY4, DEPARTMENT3, HOURS_EMPL4, WAGE_EMPL4);
	Manager empl5Manager = new Manager(ID5, SALARY1, DEPARTMENT4, FACTOR_EMPL5);
	SalesPerson empl6Sales = new SalesPerson(ID6, SALARY2, DEPARTMENT2, HOURS_EMPL6, WAGE_EMPL6, PERCENT_EMPL6, SALES_EMPL6); 
	Company company;

	@BeforeEach
	void setCompany() {
		// before each test there will be create new object company
		// with array of the given employee objects
		company = new Company(new Employee[] { empl1, empl2, empl3, empl4Wage, empl5Manager, empl6Sales });
	}

	@Test
	@DisplayName("Test of the method AddEmployee")
	void testAddEmployee() {
		Employee newEmployee = new Employee(130, 4000, DEPARTMENT2);
		company.addEmployee(newEmployee);
		Employee[] expected = new Employee[] { empl2, empl1, empl3, newEmployee, empl6Sales, empl5Manager, empl4Wage }; // all employees in the ascending order of the ID values
		Employee[] actual = new Employee[expected.length];
		assertArrayEquals(expected, toArrayFromIterable(actual, company));
		assertEquals(7, company.getEmployeesCount()); 
		assertTrue(company.containsEmployee(newEmployee));
		assertThrowsExactly(IllegalStateException.class, () -> company.addEmployee(empl1));
	}

	@Test
	@DisplayName("Test of the method GetEmployee")
	void testGetEmployee() {
		Employee employee = company.getEmployee(ID1);
		assertNotNull(employee);
		assertEquals(ID1, employee.getId());
		assertTrue(empl1.equals(employee));
		assertNull(company.getEmployee(999));
	}

	@Test
	@DisplayName("Test of the method RemoveEmployee")
	void testRemoveEmployee() {
		Employee removedEmployee = company.removeEmployee(ID2);
		assertEquals(empl2, removedEmployee);
		assertNull(company.getEmployee(ID2)); 
	    assertEquals(5, company.getEmployeesCount()); 
	    assertThrowsExactly(NoSuchElementException.class, () -> company.removeEmployee(999)); 
	}

	@Test
	@DisplayName("Test of the method GetDepartmentBudget")
	void testGetDepartmentBudget() {
		int budget = company.getDepartmentBudget(DEPARTMENT4);
		int salEmpl5 = (int) (SALARY1 * FACTOR_EMPL5);
		assertEquals(salEmpl5, budget);
		int salEmpl6 = (int) (SALARY2 + HOURS_EMPL6 * WAGE_EMPL6 + SALES_EMPL6 * PERCENT_EMPL6 / 100);
		assertEquals(SALARY3 + salEmpl6, company.getDepartmentBudget(DEPARTMENT2));
		assertEquals(0, company.getDepartmentBudget("Security"));
	    assertEquals(0, company.getDepartmentBudget(null));
	}

	@Test
	@DisplayName("Test of the Iterator")
	void testIterator() {
	    Employee[] expected = {empl2, empl1, empl3, empl6Sales, empl5Manager, empl4Wage};
	    Iterator<Employee> it = company.iterator();
	    int index = 0;
	    while (it.hasNext()) {
	        assertEquals(expected[index++], it.next());
	    }
	    assertEquals(expected.length, index); // в данном случае эта проверка обязательна
	    assertThrowsExactly(NoSuchElementException.class, () -> it.next());
	}
	
	@Test
	@DisplayName("Test of the method GetDepartments")
	void testGetDepartments() {
		String[] actual = company.getDepartments();
		String[] expected = { "Development", "QA", "Managment", "R&D" };
		assertArrayEquals(actual, expected);
	}
	
	@Test
	@DisplayName("Test of the method GetManagersWithMostFactor")
	void testGetManagersWithMostFactor() {
		Manager manager1 = new Manager(1, 1000, "Managment", 1.5f);
		Manager manager2 = new Manager(2, 1000, "Managment", 1.7f);
		Manager manager3 = new Manager(3, 1000, "Managment", 1.7f);
		Manager manager4 = new Manager(4, 1000, "Managment", 1.2f);
		Company companyTest = new Company(new Employee[] {empl1, manager1, manager2, manager3, manager4, empl4Wage, empl6Sales});
		Manager[] expected = { manager2, manager3 };
		assertArrayEquals(expected, companyTest.getManagersWithMostFactor());
		
		Company companyNoManager = new Company(new Employee[] {empl1, empl4Wage, empl6Sales});
		Manager[] expectedEmpty = new Manager[0];
		assertArrayEquals(expectedEmpty, companyNoManager.getManagersWithMostFactor());
	}

	protected <T> T[] toArrayFromIterable(T[] array, Iterable<T> iterable) {
		int index = 0;
		for (T obj : iterable) {
			array[index++] = obj;
		}
		return array;
	}

}
