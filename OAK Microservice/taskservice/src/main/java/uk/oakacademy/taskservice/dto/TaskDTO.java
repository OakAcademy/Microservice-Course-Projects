package uk.oakacademy.taskservice.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import uk.oakacademy.taskservice.entities.PriorityType;
import uk.oakacademy.taskservice.entities.TaskStatus;

import java.sql.Timestamp;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {


    private String id;
    private String taskTitle;
    private String taskDescription;
    private String notes;
    //EmployeeId
    private String assignee;
    private Timestamp taskStartDate;
    private String taskStatus;
    private String priorityType;
}
