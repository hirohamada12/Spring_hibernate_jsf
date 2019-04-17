package com.service;

import java.util.Date;
import java.util.List;

import com.entity.Employee;
import com.entity.PhoneNumber;

public interface EmployeeService {
	List<Employee> readEmployees(String job, Date hiredate);

	Employee seachEmpById(String emp_Id);
	
	void addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployee(Integer empId);
	
	void insertPhoneNumber(List<PhoneNumber> phoneNumber);


}
