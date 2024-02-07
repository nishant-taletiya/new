package com.RestApi.RestApi.Service;

import com.RestApi.RestApi.Student;
import com.RestApi.RestApi.repo.StudentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo sr;

    public StudentService(StudentRepo sr) {
        this.sr = sr;
    }
    public ResponseEntity<Student> saveStudent(@RequestBody Student stud)
    {
        Student newstud=sr.save(stud);
        return ResponseEntity.ok(newstud);
    }
    public ResponseEntity<List<Student>> fetchAllStudent()
    {
        return ResponseEntity.ok(sr.findAll());
    }
    public ResponseEntity<String> deleteStudent(Long id)
    {
        sr.deleteById(id);
        return ResponseEntity.ok("student deleted");
    }
    public ResponseEntity<Optional<Student>> findStudent(Long id)
    {
        Optional<Student> newstud=sr.findById(id);
        return ResponseEntity.ok(newstud);
    }
    public ResponseEntity<String> updateStudent(Long id,Student stud)
    {
        if(id==null)
        {
            return ResponseEntity.ok("Please provide a id");
        }
        Optional<Student> exstud=sr.findById(id);
        if(exstud.isPresent())
        {
            Student newstud=exstud.get();
            if(stud.getName()!=null)
            {
                newstud.setName(stud.getName());
            }
            if(stud.getAge()>0)
            {
                newstud.setAge(stud.getAge());
            }
            sr.save(newstud);
            return ResponseEntity.ok("data updated");
        }
        else
        {
            return ResponseEntity.ok("data not found");
        }
    }
}