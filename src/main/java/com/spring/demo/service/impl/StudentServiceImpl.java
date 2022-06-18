package com.spring.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.demo.config.Custom;
import com.spring.demo.entity.Student;
import com.spring.demo.repository.StudentRepository;
import com.spring.demo.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
@Custom(name = "service")
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	@Override
	public void create(Student student) {
		Student stu = Student.builder().name(student.getName()).age(student.getAge()).build();
		studentRepository.save(stu);
	}

	@Override
	public void update(int id, Student student) throws Exception {
		Student stu = this.findById(id);
		stu.setName(student.getName());
		stu.setAge(student.getAge());
		studentRepository.save(stu);

	}

	private Student findById(int id) throws Exception {
		return this.studentRepository.findById(id).orElseThrow(() -> new Exception("Student is not Found."));
	}

	@Override
	public void delete(int id) {
		this.studentRepository.deleteById(id);

	}

	@Override
	public List<Student> getAllStudent() {
		return this.studentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

	@Override
	public Optional<Student> findStudentById(int id) {
		return this.studentRepository.findById(id);
	}

}
