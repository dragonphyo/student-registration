package com.spring.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.spring.demo.config.Custom;
import com.spring.demo.entity.Student;
import com.spring.demo.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Custom(name = "schedular")
public class SchedularService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedularService.class);
	private final StudentRepository studentRepository;

	@Scheduled(cron = "*/60 * * * * *")
	public void addStudent() {
		var student = Student.builder().name("User" + new Random().nextInt(999)).age(26).build();
		studentRepository.save(student);
		System.out.println("add service call in : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

	@Scheduled(cron = "0 0 8-10 * * *")
	public void getStudents() {

		var students = studentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		System.out.println("get service call in : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		LOGGER.info("students : {}", students);
	}

}
