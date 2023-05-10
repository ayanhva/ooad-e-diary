package ooad.project.ediary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ooad.project.ediary.model.enums.GradeType;
import ooad.project.ediary.model.enums.TaskType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTaskDto {
    private Long id;
    private GradeType grade;
    private Long taskId;
    private Long studentId;
}
