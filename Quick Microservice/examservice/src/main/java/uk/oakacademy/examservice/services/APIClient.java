package uk.oakacademy.examservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uk.oakacademy.examservice.entities.Student;
@FeignClient(url = "http://localhost:8081",value = "studentservice")
public interface APIClient {

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getById(@PathVariable int id);
}
