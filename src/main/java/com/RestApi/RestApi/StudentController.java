package com.RestApi.RestApi;

import com.RestApi.RestApi.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    private final StudentService ss;

    public StudentController(StudentService ss) {
        this.ss = ss;
    }

    @PostMapping("/addstudent")
    public ResponseEntity<Student> saveStudent(@RequestBody Student stud) {
        ResponseEntity<Student> newStudentResponse = ss.saveStudent(stud);
        return newStudentResponse;
    }

    @GetMapping("/getstudent")
    public ResponseEntity<List<Student>> getStudent()
    {
        return ss.fetchAllStudent();
    }
    @GetMapping("/deleteStudent")
    public ResponseEntity<String> deleteStudent(Long id)
    {
        ss.deleteStudent(id);
        return ResponseEntity.ok("deleted");
    }

    @GetMapping("/findStudent")
    public ResponseEntity<Optional<Student>> findStudent(Long id)
    {
        return ss.findStudent(id);
    }
    @PostMapping("/updateStudent/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student stud) {
        return ss.updateStudent(id, stud);
    }

    @RequestMapping("/check")
    @ResponseBody
    public String check()
    {
        return "Working";
    }
}