package ooad.project.ediary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ooad.project.ediary.model.enums.GradeType;
import ooad.project.ediary.model.enums.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRegistrationDto {
    private String name;
    private String description;
    private TaskType type;
    private LocalDateTime deadline;
    private Long courseId;

}
