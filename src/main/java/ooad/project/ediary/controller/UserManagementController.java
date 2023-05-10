package ooad.project.ediary.controller;

import ooad.project.ediary.model.dto.UserLightDto;
import ooad.project.ediary.model.dto.UserRegistrationDto;
import ooad.project.ediary.model.enums.UserType;
import ooad.project.ediary.service.UserManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ooad.project.ediary.model.constant.HeaderKeys.USER_ID;


@RestController
@RequestMapping("/e-diary/users")
@CrossOrigin(origins = "http://localhost:63342")
public class UserManagementController {
    private final UserManagementService managementService;

    public UserManagementController(UserManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping
    public void registerUser(@RequestHeader(USER_ID) Long userId,
                             @RequestBody UserRegistrationDto userRegistrationDto) {
        managementService.registerUser(userId, userRegistrationDto);
    }

    @GetMapping("/instructors")
    public List<UserLightDto> getAllInstructors(@RequestHeader(USER_ID) Long userId) {
        return managementService.getAllInstructors(userId);
    }

    @GetMapping("/type")
    public UserType getUserType(@RequestHeader(USER_ID) Long userId) {
        return managementService.getUserType(userId);
    }
}
