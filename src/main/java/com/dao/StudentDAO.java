package com.dao;

import java.util.List;

import com.model.Student;

public interface StudentDAO {
	public int saveItem(Student student);
	public List<Student> findAll();
	public int editItem(Student student);
	public int deleteItem(Student student);
}
	