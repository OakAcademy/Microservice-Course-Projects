package uk.oakacademy.examservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.oakacademy.examservice.entities.Exam;
import uk.oakacademy.examservice.entities.Student;
import uk.oakacademy.examservice.entities.StudentEmail;
import uk.oakacademy.examservice.producer.ExamEmailProducer;
import uk.oakacademy.examservice.services.APIClient;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable int id) {
        return ResponseEntity.ok("getById method returns" + id);
    }

    @GetMapping
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok("getall method ");
    }

    @PutMapping
    public ResponseEntity<String> update() {
        return ResponseEntity.ok("update method ");
    }

    @DeleteMapping
    public ResponseEntity<String> delete() {
        return ResponseEntity.ok("delete method ");
    }

private final ExamEmailProducer examEmailProducer;
    @PostMapping
    public ResponseEntity<String> add(@RequestBody Exam exam) throws JsonProcessingException {
        Student student = apiClient.getById(exam.getStudentId()).getBody();
        StudentEmail email=new StudentEmail();
        email.setEmail(student.getEmail());
        email.setScore(exam.getScore());
        email.setLesson(exam.getLesson());
        email.setNameSurname(student.getNameSurname());
        examEmailProducer.SendToQueue(email);
        return ResponseEntity.ok("add method ");
    }

    private final APIClient apiClient;

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Student student = apiClient.getById(id).getBody();
        return ResponseEntity.ok(student);

    }


}
