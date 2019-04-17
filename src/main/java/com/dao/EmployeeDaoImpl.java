package com.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.entity.Employee;
import com.entity.PhoneNumber;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Employee> readEmployees(String job, Date hiredate) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Employee> employees = null;
		if (job.isEmpty() && hiredate == null) {
			employees = session.createQuery("from Employee").list();
			return employees;
		} else if (!job.isEmpty() && hiredate == null) {
			employees = session.createQuery("from Employee where job like :job").setString("job","%"+job.toUpperCase()+"%").list();
			return employees;
		} else if (job.isEmpty() && hiredate != null) {
			employees = session.createQuery("from Employee where hiredate=:hiredate").setParameter("hiredate", hiredate).list();
			return employees;
		} else if (!job.isEmpty() && hiredate != null) {
			employees = session.createQuery("from Employee where job like :job and hiredate=:hiredate")
					.setString("job","%"+job.toUpperCase()+"%").setParameter("hiredate", hiredate).list();
			return employees;
		}
		return employees;
	}

	public Employee seachEmpById(String emp_Id) {
		Session session = sessionFactory.openSession();
		Employee employee = (Employee) session.createCriteria(Employee.class, emp_Id);
		return employee;
	}

	public void updateEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(employee);
	}

	public void deleteEmployee(Integer empId) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee employee = (Employee) session.get(Employee.class, empId);
		session.delete(employee);
	}

	public void addEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(employee);
	}

	public void insertPhoneNumber(List<PhoneNumber> phoneNumber) {
		Session session = this.sessionFactory.getCurrentSession();
		int count=0;
		for (PhoneNumber item : phoneNumber) {
			session.save(item);
			count++;
			if (count % 500 == 0) {
				session.flush();
				session.clear();
			}
		}
	}
}
