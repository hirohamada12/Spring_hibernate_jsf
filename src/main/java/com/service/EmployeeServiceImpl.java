package com.service;

import java.util.Date;
import java.util.List;

///import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.EmployeeDao;
import com.entity.Employee;
import com.entity.PhoneNumber;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	@Transactional
	public List<Employee> readEmployees(String job,Date hiredate) {
		return employeeDao.readEmployees(job,hiredate);
	}

	@Transactional
	public Employee seachEmpById(String emp_Id) {
		return employeeDao.seachEmpById(emp_Id);
	}

	@Transactional
	public void addEmployee(Employee employee) {
		employeeDao.addEmployee(employee);

	}

	@Transactional
	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}

	@Transactional
	public void deleteEmployee(Integer empId) {
		employeeDao.deleteEmployee(empId);
	}

	@Transactional
	public void insertPhoneNumber(List<PhoneNumber> phoneNumber) {
		employeeDao.insertPhoneNumber(phoneNumber);
		
	}
	@Transactional
	@Override
	public List<Object[]> getJob() {
		return employeeDao.getJob();
	}

}
