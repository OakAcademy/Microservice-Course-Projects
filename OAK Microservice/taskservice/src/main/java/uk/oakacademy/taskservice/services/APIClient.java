package uk.oakacademy.taskservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uk.oakacademy.taskservice.dto.EmployeeDTO;

@FeignClient(url = "http://localhost:7202",value = "employeeservice")
public interface APIClient {

    @GetMapping("/employees/{id}")
    public EmployeeDTO getById(@PathVariable String id);
}
