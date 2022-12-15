package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Student;
import com.util.DBUtils;

public class StudentImpl implements StudentDAO {

	public Connection connection = null;
	public PreparedStatement preparedStatement;
	public Statement statement;
	public ResultSet resultSet;

	@Override
	public int saveItem(Student student) {
		int result = 0;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO student.dbo.student\r\n" + "(address, email, fullName)\r\n" + "VALUES(?, ?, ?)");
			preparedStatement.setString(1, student.getAddress());
			preparedStatement.setString(2, student.getEmail());
			preparedStatement.setString(3, student.getFullName());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<Student> findAll() {
		List<Student> students = new ArrayList<Student>();
		try {
			connection = DBUtils.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM student ORDER BY ID DESC");
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String address = resultSet.getString("address");
				String email = resultSet.getString("email");
				String fullName = resultSet.getString("fullName");
				Student student = new Student(id, address, email, fullName);
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return students;
	}

	@Override
	public int editItem(Student student) {
		int result = 0;
		String sql = "UPDATE student SET address = ?, email = ?, fullname = ? WHERE id = ?";
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getAddress());
			preparedStatement.setString(2, student.getEmail());
			preparedStatement.setString(3, student.getFullName());
			preparedStatement.setInt(4, student.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null && connection != null) {
				try {
					preparedStatement.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

	@Override
	public int deleteItem(Student student) {
		int result = 0;
		String sql = "DELETE FROM student WHERE id = ? ";
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, student.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null && connection != null) {
				try {
					preparedStatement.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
