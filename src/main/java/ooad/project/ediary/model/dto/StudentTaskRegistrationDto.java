package ooad.project.ediary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ooad.project.ediary.model.enums.GradeType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTaskRegistrationDto {
    private GradeType grade;
    private Long taskId;
    private Long studentId;
}
