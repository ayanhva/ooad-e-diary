package ooad.project.ediary.controller;

import ooad.project.ediary.model.dto.CourseRegistrationDto;
import ooad.project.ediary.model.dto.SubjectDto;
import ooad.project.ediary.model.dto.SubjectRegistrationDto;
import ooad.project.ediary.model.dto.UserLightDto;
import ooad.project.ediary.service.SubjectManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/e-diary/subjects")
@CrossOrigin(origins = "http://localhost:63342")
public class SubjectManagementController {
    private final SubjectManagementService managementService;

    public SubjectManagementController(SubjectManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping
    public void registerSubject(@RequestBody SubjectRegistrationDto subjectRegistrationDto) {
        managementService.registerSubject(subjectRegistrationDto);
    }

    @GetMapping
    public List<SubjectDto> getAllSubjects() {
        return managementService.getAllSubjects();
    }
}
