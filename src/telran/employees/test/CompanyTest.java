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
	private static final int SALARY1 = 1000;
	private static final String DEPARTMENT1 = "Development";
	private static final long ID2 = 120;
	private static final int SALARY2 = 2000;
	private static final long ID3 = 125;
	private static final int SALARY3 = 3000;
	private static final String DEPARTMENT2 = "QA";
	Employee empl1 = new Employee(ID1, SALARY1, DEPARTMENT1);
	Employee empl2 = new Employee(ID2, SALARY2, DEPARTMENT1);
	Employee empl3 = new Employee(ID3, SALARY3, DEPARTMENT2);
	Company company;

	@BeforeEach
	void setCompany() {
		// before each test there will be create new object company
		// with array of the given employee objects
		company = new Company(new Employee[] { empl1, empl2, empl3 });
	}

	@Test
	@DisplayName("Test of the method AddEmployee")
	void testAddEmployee() {
		Employee newEmployee = new Employee(130, 4000, DEPARTMENT2);
		company.addEmployee(newEmployee);
		Employee[] expected = new Employee[] { empl2, empl1, empl3, newEmployee }; // all employees in the ascending order of the ID values
		Employee[] actual = new Employee[expected.length];
		assertArrayEquals(expected, toArrayFromIterable(actual, company));
		assertEquals(4, company.getEmployeesCount()); 
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
	    assertEquals(2, company.getEmployeesCount()); 
	    assertThrowsExactly(NoSuchElementException.class, () -> company.removeEmployee(999)); 
	}

	@Test
	@DisplayName("Test of the method GetDepartmentBudget")
	void testGetDepartmentBudget() {
		int budget = company.getDepartmentBudget(DEPARTMENT1);
		assertEquals(SALARY1 + SALARY2, budget);
		assertEquals(SALARY3, company.getDepartmentBudget(DEPARTMENT2));
		assertEquals(0, company.getDepartmentBudget("Security"));
	    assertEquals(0, company.getDepartmentBudget(null));
	}

	@Test
	@DisplayName("Test of the Iterator")
	void testIterator() {
	    Employee[] expected = {empl2, empl1, empl3};
	    Iterator<Employee> iterator = company.iterator();
	    int index = 0;
	    while (iterator.hasNext()) {
	        Employee actual = iterator.next();
	        assertEquals(expected[index++], actual);
	    }
	    assertThrowsExactly(NoSuchElementException.class, () -> iterator.next());
	}

	protected <T> T[] toArrayFromIterable(T[] array, Iterable<T> iterable) {
		int index = 0;
		for (T obj : iterable) {
			array[index++] = obj;
		}
		return array;
	}

}
