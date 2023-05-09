package ooad.project.ediary.controller;

import ooad.project.ediary.model.dto.CourseRegistrationDto;
import ooad.project.ediary.model.dto.SubjectDto;
import ooad.project.ediary.model.dto.SubjectRegistrationDto;
import ooad.project.ediary.model.dto.UserLightDto;
import ooad.project.ediary.service.SubjectManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ooad.project.ediary.model.constant.HeaderKeys.USER_ID;

@RestController
@RequestMapping("/e-diary/subjects")
public class SubjectManagementController {
    private final SubjectManagementService managementService;

    public SubjectManagementController(SubjectManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping
    public void registerSubject(@RequestBody SubjectRegistrationDto subjectRegistrationDto) {
        managementService.registerSubject(subjectRegistrationDto);
    }

    @GetMapping("/instructors")
    public List<SubjectDto> getAllSubjects() {
        return managementService.getAllSubjects();
    }

    @PostMapping("/course")
    public void registerCourse(@RequestBody CourseRegistrationDto courseRegistrationDto) {
        managementService.registerCourse(courseRegistrationDto);
    }
}