package ooad.project.ediary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistrationDto {
    private String crn;
    private String weekday;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long roomNumber;
    private Long subjectId;
    private Long formClassId;
}
