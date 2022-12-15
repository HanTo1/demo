package com.ViewModel;

import java.util.List;

import javax.swing.ListModel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;

import com.dao.StudentDAO;
import com.dao.StudentImpl;
import com.model.Student;

import bsh.This;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentViewModel {
	private Student newStudent, selectedStudent = new Student();
	private String fullName, email, address;
	private StudentDAO dao = new StudentImpl();

	public List<Student> getStudents() {
		return dao.findAll();
	}

	@Command("add")
	@NotifyChange("students")
	public void add() {
		Student student = new Student(address, email, fullName);
//		System.out.println(this.newStudent.getEmail());
		dao.saveItem(student); 
	}
	
	@Command("update")
	@NotifyChange("students")
	public void update() {
		dao.editItem(this.selectedStudent);
	}
	
	@Command("delete")
	@NotifyChange({"students", "selectedStudent"})
	public void delete() {
		if(this.selectedStudent != null) {
			dao.deleteItem(this.selectedStudent);
			this.selectedStudent = null;
		}
	}
}
