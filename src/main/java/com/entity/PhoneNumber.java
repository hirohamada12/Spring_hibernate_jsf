package com.entity;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "phoneNumber")
@ManagedBean(name = "PhoneNumber")
public class PhoneNumber {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Phone_SEQUENCE")
	@SequenceGenerator(sequenceName = "cli_phoneNumber", name = "Phone_SEQUENCE", allocationSize = 1)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "phoneNumber")
	private String phone;

	public PhoneNumber(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}

	public PhoneNumber() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
