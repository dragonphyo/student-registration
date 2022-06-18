package com.spring.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.config.Custom;
import com.spring.demo.entity.Student;
import com.spring.demo.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("students")
@Custom(name = "controller")
public class StudentController {

	private final StudentService studentService;

	@GetMapping
	public ResponseEntity<?> getAll() {

		var output = this.studentService.getAllStudent();
		if (null != output) {
			return new ResponseEntity<>(output, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id) {

		var output = this.studentService.findStudentById(id);
		if (output.isPresent()) {
			return new ResponseEntity<>(output, HttpStatus.OK);
		}
		return new ResponseEntity<>("Student not found with id :" + id, HttpStatus.BAD_REQUEST);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Student student) throws Exception {

		try {
			this.studentService.create(student);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@PatchMapping("{id}")
	public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody Student student) throws Exception {

		try {
			this.studentService.update(id, student);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@DeleteMapping("admin/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) throws Exception {

		try {
			this.studentService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
