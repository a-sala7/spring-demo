package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
			Student mariam = new Student(
				"Asder",
				"asder.jamal@gmail.com",
				LocalDate.of(2000, Month.JANUARY, 5)
			);
			Student ahmed = new Student(
				"Ahmed",
				"svp@test.com",
				LocalDate.of(1996, Month.JUNE, 16)
			);

            studentRepository.saveAll(List.of(mariam, ahmed));
            
        };
    }
}
