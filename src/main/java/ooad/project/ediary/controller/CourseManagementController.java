package ooad.project.ediary.controller;

import ooad.project.ediary.model.dto.FormClassDto;
import ooad.project.ediary.service.CourseManagementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/e-diary/courses")
public class CourseManagementController {
    private final CourseManagementService courseManagementService;

    public CourseManagementController(CourseManagementService courseManagementService) {
        this.courseManagementService = courseManagementService;
    }

    @PostMapping
    public void registerFormClass(@RequestBody FormClassDto formClassDto) {
        courseManagementService.registerFormClass(formClassDto);
    }
}
