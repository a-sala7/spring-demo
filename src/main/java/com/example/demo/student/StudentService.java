package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    
    private final StudentRepository studentRepository;
    
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
		return studentRepository.findAll();
	}

    public Student saveStudent(Student s) {
        if(studentRepository.findStudentByEmail(s.getEmail()).isPresent()){
            throw new IllegalStateException("Email Taken");
        }
        return studentRepository.save(s);
    }

    public void deleteStudent(Long id){
        boolean studentExists = studentRepository.existsById(id);
        if(studentExists){
            studentRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Student with ID " + id + " not found");
        }
    }

    @Transactional
    public void updateStudent(Long id, String name, String email){
        Optional<Student> studentInDb = studentRepository.findById(id);
        if(studentInDb.isPresent()){
            Student s = studentInDb.get();
            boolean changed = false;
            if (name != null && !s.getName().equals(name)){
                changed = true;
                s.setName(name);
            }
            if(email != null && !s.getEmail().equals(email)){
                changed = true;
                s.setEmail(email);
            }
            if(changed){
                studentRepository.save(s);
            }
        } else {
            throw new IllegalStateException("Student with ID " + id + " not found");
        }
    }
}
