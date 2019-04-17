package com.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "dept")
@Component
public class Dept {
	@Id
	@Column(name = "deptno")
	private int deptno;
	@Column(name = "dname")
	private String dName;
	@Column(name = "loc")
	private String loc;
	@OneToMany(mappedBy = "dept")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Employee> employees;

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}

	public Dept(int deptno, String dName, String loc) {
		super();
		this.deptno = deptno;
		this.dName = dName;
		this.loc = loc;
	}

	public Dept() {
		super();
	}
	

}
