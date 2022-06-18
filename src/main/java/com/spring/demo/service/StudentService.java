package com.spring.demo.service;

import java.util.List;
import java.util.Optional;

import com.spring.demo.entity.Student;

public interface StudentService {

	void create(Student student);

	void update(int id, Student student) throws Exception;

	void delete(int id);

	List<Student> getAllStudent();

	Optional<Student> findStudentById(int id);

}
