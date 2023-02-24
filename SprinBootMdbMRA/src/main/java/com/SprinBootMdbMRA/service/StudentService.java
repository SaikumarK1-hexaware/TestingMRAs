package com.SprinBootMdbMRA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SprinBootMdbMRA.entity.Student;
import com.SprinBootMdbMRA.exception.EntityNotFoundException;
import com.SprinBootMdbMRA.repository.StudentRepository;

@Service  
public class StudentService {

  @Autowired
  private StudentRepository studentRepo;

  public List<Student> fetchAll() {
    return studentRepo.findAll();
  }

  public Student fetchById(final String id) {
    Optional<Student> student = studentRepo.findById(id);

		if (!student.isPresent()){
			throw new EntityNotFoundException("id-" + id);
    }
		return student.get();
  }

  public void delete(final String id) {
    studentRepo.deleteById(id);
  }

  public Student create(final Student student) {
    return studentRepo.save(student);
  }

  public ResponseEntity<Object> update(final Student student, final String id) {
    Optional<Student> studentOptional = studentRepo.findById(id);
		if (!studentOptional.isPresent()) {
			return ResponseEntity.notFound().build();
    }
		student.setId(id);
		studentRepo.save(student);
		return ResponseEntity.noContent().build();
  }

}
