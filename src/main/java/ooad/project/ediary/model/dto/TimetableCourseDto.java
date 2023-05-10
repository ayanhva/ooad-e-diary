package ooad.project.ediary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableCourseDto {
    private Long id;
    private String crn;
    private String weekday;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long roomNumber;
    private String formClassYearAndIdentifier;
    private String subjectName;
}
