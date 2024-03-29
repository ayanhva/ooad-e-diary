package ooad.project.ediary.controller;

import lombok.AllArgsConstructor;
import ooad.project.ediary.model.dto.AttendanceRecordDto;
import ooad.project.ediary.model.dto.CourseLightDto;
import ooad.project.ediary.model.dto.CourseRegistrationDto;
import ooad.project.ediary.model.dto.StudentTaskRegistrationDto;
import ooad.project.ediary.model.dto.TaskRegistrationDto;
import ooad.project.ediary.service.CourseManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ooad.project.ediary.model.constant.HeaderKeys.COURSE_ID;
import static ooad.project.ediary.model.constant.HeaderKeys.SUBJECT_ID;
import static ooad.project.ediary.model.constant.HeaderKeys.USER_ID;

@RestController
@RequestMapping("/e-diary/courses")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63343")
public class CourseManagementController {

    private final CourseManagementService service;

    @PostMapping
    public void registerCourse(@RequestHeader(USER_ID) Long userId,
                               @RequestBody CourseRegistrationDto courseRegistrationDto) {
        service.registerCourse(userId, courseRegistrationDto);
    }

    @PostMapping("/task")
    public void addTask(@RequestBody TaskRegistrationDto taskRegistrationDto) {
        service.addTask(taskRegistrationDto);
    }

    @PostMapping("/task/grade")
    public void gradeTask(@RequestBody StudentTaskRegistrationDto studentTaskRegistrationDto) {
        service.gradeTask(studentTaskRegistrationDto);
    }

    @GetMapping
    public List<CourseLightDto> getAllCourses(@RequestHeader(USER_ID) Long userId, @RequestHeader(SUBJECT_ID) Long subjectId) {
        return service.getAllCourses(userId, subjectId);
    }

    @PostMapping("/attendance")
    public void recordAttendance(@RequestHeader(COURSE_ID) Long courseId,
                                 @RequestBody List<AttendanceRecordDto> attendanceRecords) {
        service.recordAttendance(courseId, attendanceRecords);
    }
}
