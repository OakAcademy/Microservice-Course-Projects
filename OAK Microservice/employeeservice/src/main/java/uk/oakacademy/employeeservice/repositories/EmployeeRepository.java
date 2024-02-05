package uk.oakacademy.employeeservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.oakacademy.employeeservice.entities.Employee;

public interface EmployeeRepository extends MongoRepository<Employee,String> {
}
