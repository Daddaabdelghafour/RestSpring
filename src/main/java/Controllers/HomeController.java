package Controllers;

import Beans.Student;
import Exceptions.StudentNotFoundException;
import Services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class HomeController {

    @Autowired
    StudentService studentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> hello() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") int id) {
        Student std =  studentService.getStudentById(id);
        if(std == null){
            throw new StudentNotFoundException(id);
        }
        return std;
    }

    @GetMapping("/name/{name}")
    public Student getStudentByName(@PathVariable("name") String name) {
        List<Student> students = studentService.getAllStudents();
        for (Student student : students) {
            if (student.getFirstName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        throw new StudentNotFoundException(0);
    }






    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> addStudent(@Valid  @RequestBody Student student , BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(null);
        }
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@PathVariable("id") int id , @RequestBody Student student){
        studentService.updateStudent(id, student);
        return student;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
    }













}
