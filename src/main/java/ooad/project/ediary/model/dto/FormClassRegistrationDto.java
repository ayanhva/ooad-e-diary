package ooad.project.ediary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormClassRegistrationDto {
    private Long year;
    private String identifier;
    private Long formTutor;
    private Long roomNumber;
}
