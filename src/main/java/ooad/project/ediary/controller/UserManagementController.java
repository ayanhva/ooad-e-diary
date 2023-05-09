package ooad.project.ediary.controller;

import ooad.project.ediary.model.dto.UserDto;
import ooad.project.ediary.service.UserManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/e-diary/user")
public class UserManagementController {
    private final UserManagementService managementService;

    public UserManagementController(UserManagementService managementService) {
        this.managementService = managementService;
    }

    @PostConstruct
    public void registerUser(@RequestHeader("SCHOOL_ID") Long schoolId, @RequestBody UserDto userDto) {
        managementService.registerUser(schoolId, userDto);
    }

    @GetMapping
    public Long returnAnyNum() {
        return 5L;
    }
}
