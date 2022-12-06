package com.example.micro.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Students {
	private long entryID;
	private String studentName;
	private String address;
	
	public Students() {
		
	}
	
	public Students(String studentName, String address) {
		this.studentName = studentName;
		this.address = address;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getEntryId() {
		return entryID;
	}
	public void setEntryId(long entryID) {
		this.entryID = entryID;
	}
	
	@Column(name = "first_name", nullable = false)
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + entryID + ", studentName=" + studentName + ", address=" + address + "]";
	}
}
