package ooad.project.ediary.controller;

import lombok.AllArgsConstructor;
import ooad.project.ediary.model.dto.CourseRegistrationDto;
import ooad.project.ediary.service.CourseManagementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/e-diary/courses")
@AllArgsConstructor
public class CourseManagementController {

    private final CourseManagementService service;

    @PostMapping
    public void registerCourse(@RequestBody CourseRegistrationDto courseRegistrationDto) {
        service.registerCourse(courseRegistrationDto);
    }
}
