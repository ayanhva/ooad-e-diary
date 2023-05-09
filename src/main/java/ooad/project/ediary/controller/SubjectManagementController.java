package ooad.project.ediary.controller;

import ooad.project.ediary.model.dto.SubjectRegistrationDto;
import ooad.project.ediary.service.SubjectManagementService;
import org.springframework.web.bind.annotation.*;

import static ooad.project.ediary.model.constant.HeaderKeys.USER_ID;

@RestController
@RequestMapping("/e-diary/subjects")
public class SubjectManagementController {
    private final SubjectManagementService managementService;

    public SubjectManagementController(SubjectManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping
    public void registerUser(@RequestBody SubjectRegistrationDto subjectRegistrationDto) {
        managementService.registerSubject(subjectRegistrationDto);
    }
}
