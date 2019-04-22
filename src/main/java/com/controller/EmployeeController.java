package com.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.ExportExcel;
import com.entity.Employee;
import com.service.EmployeeService;

@Component(value = "controllerEmp")
@ViewScoped
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	private StreamedContent file;
	// @Autowired
	// private FileDownload fileDownload;

	@Autowired
	private ExportExcel exportExcel;
	
	@Autowired
	ServletContext context;

	private List<Employee> listEmp;
	private String job = "";
	private Date hiredate = null;
	private List<Object[]> listJob;

	public List<Object[]> getListJob() {
		return listJob;
	}

	public void setListJob(List<Object[]> listJob) {
		this.listJob = listJob;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
	public StreamedContent getFile() {
		return file;
	}

	@PostConstruct
	public void init() {
		listEmp = employeeService.readEmployees(job, hiredate);
		listJob=employeeService.getJob();
	}

	public List<Employee> getListEmp() {
		return listEmp;
	}

	public void filterEmp() {
		listEmp = employeeService.readEmployees(job, hiredate);
		System.out.println("=========> Đã check");
		System.out.println("=========>" + job);
		for (Employee emp : listEmp) {
			System.out.println("=======>" + emp.getEname());
			System.out.println("=======>" + emp.getJob());
			System.out.println("=======>" + emp.getMgr());
		}
	}

	public void deleteEmp(Integer empId) {
		employeeService.deleteEmployee(empId);
		listEmp = employeeService.readEmployees("", null);
	}

	public void addEmp(Employee employee) {
		employeeService.addEmployee(employee);
		listEmp = employeeService.readEmployees("", null);
	}

	public void updateEmp(Employee employee) {
		employeeService.updateEmployee(employee);
		listEmp = employeeService.readEmployees("", null);
	}

	public void exportFileExelAndDown() throws IOException {
		if (listEmp.size() > 0) {
			exportExcel.writeExcel(listEmp, "E:/demo/employee.xls");
			FacesMessage message = new FacesMessage("Succesful file is export. FILE : E:/demo/employee.xls ");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

}
