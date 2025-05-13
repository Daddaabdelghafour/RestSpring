package Services;

import Beans.Student;
import Exceptions.StudentNotFoundException;
import Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Method to get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Method to get a student by ID
    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    // Method to add a new student
    @Transactional
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Method to update an existing student
    @Transactional
    public Student updateStudent(int id, Student updatedStudent) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        updatedStudent.setId(id);
        return studentRepository.save(updatedStudent);
    }

    // Method to delete a student by ID
    @Transactional
    public void deleteStudent(int id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    // Method to find a student by email
    public Student findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // Method to find students by age
    public List<Student> findStudentsByAge(int age) {
        return studentRepository.findByAge(age);
    }

    // Method to find students by first name
    public List<Student> findStudentsByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }
}