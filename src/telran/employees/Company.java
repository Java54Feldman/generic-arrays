package telran.employees;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;

//So far we don't consider optimization
public class Company implements Iterable {
	private Employee[] employees;

	public Company(Employee[] employees) {
		this.employees = Arrays.copy(employees);
		Arrays.bubbleSort(this.employees); // all employees in the ascending order of the ID values
	}

	public int getEmployeesCount() {
		return employees.length;
	}

	public void addEmployee(Employee empl) {
		// adds new Employee to array of employees
		// if an employee with id equaled to id of employee exists, then to throw
		// IllegalStateException
		if (getEmployee(empl.getId()) != null) {
			throw new IllegalStateException("Employee with id " + empl.getId() + " already exists");
		}
		;
		employees = Arrays.insertSorted(employees, empl, Comparator.naturalOrder());
	}

	public Employee getEmployee(long id) {
		// data about an employee with a given id value
		// if the company doesn't have such employee, then return null
		int index = Arrays.binarySearch(employees, new Employee(id, 0, null), 
				Comparator.naturalOrder()); // naturalOrder
//				(a, b) -> a.compareTo(b)); // same
//				Comparable::compareTo);		//same
		return index >= 0 ? employees[index] : null;
	}

	public Employee removeEmployee(long id) {
		// removes from the company an employee with a given id
		// if such employee doesn't exist, throw NoSuchElementException
		// returns reference to being removed employee
		int index = Arrays.binarySearch(employees, new Employee(id, 0, null), Comparator.naturalOrder());
		if (index < 0) {
			throw new NoSuchElementException("Employee with id " + id + " not found");
		}
		Employee removedEmployee = employees[index];
		employees = Arrays.removeIf(employees, e -> e.getId() == id);
		return removedEmployee;
	}

	public int getDepartmentBudget(String department) {
		// returns sum of salary values for all employees of a given department
		// if employees of a given department don't exist, returns 0
		Employee[] departmentEmployees = Arrays.search(employees, e -> e.department.equals(department));
		int budget = 0;
		for (Employee employee : departmentEmployees) {
			budget += employee.computeSalary();
		}
		return budget;
	}

	public boolean containsEmployee(Employee employee) {
		int index = Arrays.binarySearch(employees, employee, (e1, e2) -> Long.compare(e1.getId(), e2.getId()));
		return index >= 0;
	}

	public String[] getDepartments() {
		// write method returning all departments
		// не может быть одного служащего в 2 отделах
		String[] allDepartments = new String[employees.length];
		int index = 0;
		for (Employee empl : employees) {
			allDepartments[index++] = empl.department;
		}
		String[] uniqueArray = new String[employees.length];
		int uniqueCount = 0;
		for (String element : allDepartments) {
			boolean isUnique = true;
			for (int i = 0; (i < uniqueCount && isUnique); i++) {
				if (uniqueArray[i].equals(element)) {
					isUnique = false;
				}
			}
			if (isUnique) {
				uniqueArray[uniqueCount++] = element;
			}
		}
		String[] result = new String[uniqueCount];
		System.arraycopy(uniqueArray, 0, result, 0, uniqueCount);
		return result;
	}

	@Override
	public Iterator iterator() {
		return new CompanyIterator();
	}

	private class CompanyIterator implements Iterator<Employee> {
		int index = 0;

		@Override
		public boolean hasNext() {
			return employees != null ? index < employees.length : false;
			// in case
		}

		@Override
		public Employee next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return employees[index++];

		}
	}

}
