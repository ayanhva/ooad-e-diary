package ooad.project.ediary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ooad.project.ediary.model.enums.AttendanceMark;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecordDto {
    private AttendanceMark mark;
    private Long studentId;
    private String note;
}
