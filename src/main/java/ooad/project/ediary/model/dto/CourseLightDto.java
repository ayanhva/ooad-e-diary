package ooad.project.ediary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseLightDto {
    private Long id;
    private String crn;
    private String weekday;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long roomNumber;
}
