package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import com.example.demo.student.Student;

@Service
public class StudentService {

   private final StudentRepository studentRepository;

   @Autowired
   public StudentService(StudentRepository studentRepository) {
      this.studentRepository = studentRepository;
   }


   public List<Student> getStudent() {
      return studentRepository.findAll();
   }

   public void addNewStudent(Student student) {
      //System.out.println(student);
      Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

      if (studentOptional.isPresent()) {
         throw new IllegalStateException("email taken");
      }
      else {
         studentRepository.save(student);
      }
   }

   public void deleteStudent(Long studentId) {
      //studentRepository.findById(studentId)
      boolean exists = studentRepository.existsById(studentId);
      if (!exists) {
         throw new IllegalStateException("Student with id: " + studentId + " does not exist" );
      }
      else {
         studentRepository.deleteById(studentId);
      }

   }

   @Transactional
   public void updateStudent(Long studentId, String name, String email) {
      boolean exist = studentRepository.existsById(studentId);

      //System.out.println(student);

      if (!exist) {
         throw new IllegalStateException("Student with id " + studentId + " does not exist");
      }
      else {
         Optional student = studentRepository.findById(studentId);
         System.out.println(student);
         //It is necessary to finish this part

      }




   }
}
