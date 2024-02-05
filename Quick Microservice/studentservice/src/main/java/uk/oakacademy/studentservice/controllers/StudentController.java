package uk.oakacademy.studentservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.oakacademy.studentservice.entities.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    public List<Student> students = new ArrayList<>();

    public StudentController() {
        List<Student> _students = new ArrayList<>();
        _students.add(new Student(1, "Bernard Lewis", "bernard@gmail.com"));
        _students.add(new Student(2, "John Verdon", "verdon@gmail.com"));
        _students.add(new Student(3, "Charles Dickens", "charles@gmail.com"));
        _students.add(new Student(4, "James Joyce", "james@gmail.com"));
        this.students = _students;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable int id) {
        Student result = new Student();
//        for (int i = 0; i < students.size(); i++) {
//
//            if (students.get(i).getId() == id) {
//                result = students.get(i);
//            }
//        }
        result=students.stream().filter(x->x.getId()==id).findFirst().orElseThrow();
        if (result != null)
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.ok(new Student());
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student)
    {
        Student maxId=students.stream().max(Comparator.comparing(Student::getId)).orElse(null);
        student.setId(maxId.getId()+1);
        students.add(student);
        return ResponseEntity.ok(student);
    }
@PutMapping
    public ResponseEntity<Student > update(@RequestBody Student student)
{
    Student oldvalue=students.stream().filter(x->x.getId()==student.getId()).findFirst().orElseThrow();
    students.remove(oldvalue);
    students.add(student);
    return ResponseEntity.ok(student);
}

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(@PathVariable int id)
    {
        Student deletedvalue=students.stream().filter(x->x.getId()==id).findFirst().orElseThrow();
        students.remove(deletedvalue);
        return ResponseEntity.ok(deletedvalue);
    }











}
