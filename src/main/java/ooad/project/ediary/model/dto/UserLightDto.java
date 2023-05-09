package ooad.project.ediary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLightDto {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
}
