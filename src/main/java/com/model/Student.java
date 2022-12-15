package com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	private int id;
	private String address;
	private String email;
	private String fullName;
	
	public Student(String address, String email, String fullName) {
		super();
		this.address = address;
		this.email = email;
		this.fullName = fullName;
	}
}


