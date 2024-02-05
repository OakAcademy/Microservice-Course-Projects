package uk.oakacademy.examservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEmail implements Serializable {
    private int id;
    private String nameSurname;
    private String lesson;
    private int score;
    private String email;
}
