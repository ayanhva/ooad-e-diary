package ooad.project.ediary.controller;

import lombok.AllArgsConstructor;
import ooad.project.ediary.model.dto.FormClassDto;
import ooad.project.ediary.model.dto.FormClassRegistrationDto;
import ooad.project.ediary.service.ClassManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ooad.project.ediary.model.constant.HeaderKeys.USER_ID;

@RestController
@RequestMapping("/e-diary/classes")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63343")
public class ClassManagementController {
    private final ClassManagementService classManagementService;

    @PostMapping
    public void registerFormClass(@RequestHeader(USER_ID) Long userId,
                                  @RequestBody FormClassRegistrationDto formClassRegistrationDto) {
        classManagementService.registerFormClass(userId, formClassRegistrationDto);
    }

    @PutMapping("{classId}/form-tutor/{instructorId}")
    public void changeFormTutor(@PathVariable Long classId, @PathVariable Long instructorId) {
        classManagementService.changeFormTutor(classId, instructorId);
    }

    @GetMapping
    public List<FormClassDto> getAllClasses(@RequestHeader(USER_ID) Long userId) {
        return classManagementService.getAllClasses(userId);
    }

    @PutMapping("{classId}/student/{studentId}")
    public void enrollStudentToClass(@PathVariable Long classId,
                                     @PathVariable Long studentId) {
        classManagementService.enrollStudentToClass(classId, studentId);
    }
}
