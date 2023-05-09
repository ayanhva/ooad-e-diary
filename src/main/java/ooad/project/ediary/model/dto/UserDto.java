package ooad.project.ediary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ooad.project.ediary.model.enums.UserType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private UserType type;
}
