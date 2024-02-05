package uk.oakacademy.employeeservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
public class EmployeeDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String job;
    private int age;


}
