package uk.oakacademy.examservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    private int id;
    private int studentId;
    private String lesson;
    private int score;
}
