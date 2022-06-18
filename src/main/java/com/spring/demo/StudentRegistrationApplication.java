package com.spring.demo;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.spring.demo.config.ClassScanner;
import com.spring.demo.config.Custom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class StudentRegistrationApplication implements CommandLineRunner {

	private final ClassScanner classScanner;

	public static void main(String[] args) {
		SpringApplication.run(StudentRegistrationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Set<String> annotatedClasses = classScanner.findAnnotatedClasses(Custom.class, "com.spring.demo");
		annotatedClasses.forEach(it -> {
			Class<?> clzz;
			try {
				clzz = Class.forName(it);
				if (clzz.isAnnotationPresent(Custom.class)) {
					var annotaion = clzz.getAnnotation(Custom.class);
					var custom = (Custom) annotaion;
					log.info("[name ={} className = {}]", custom.name(), clzz.getSimpleName());
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		});

	}

}
