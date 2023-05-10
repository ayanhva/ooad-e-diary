package ooad.project.ediary.service;

import lombok.AllArgsConstructor;
import ooad.project.ediary.dao.entity.CourseEntity;
import ooad.project.ediary.dao.entity.FormClassEntity;
import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.dao.repo.CourseRepository;
import ooad.project.ediary.dao.repo.FormClassRepository;
import ooad.project.ediary.dao.repo.UserRepository;
import ooad.project.ediary.mapper.CourseMapper;
import ooad.project.ediary.mapper.FormClassMapper;
import ooad.project.ediary.model.dto.FormClassDto;
import ooad.project.ediary.model.dto.FormClassRegistrationDto;
import ooad.project.ediary.model.dto.TimetableCourseDto;
import ooad.project.ediary.model.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClassManagementService {
    private final FormClassRepository formClassRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public void registerFormClass(Long userId, FormClassRegistrationDto formClassRegistrationDto) {
        System.out.println("ActionLog.registerFormClass start.");

        UserEntity user = getUser(userId);

        UserEntity formTutor = null;
        if (formClassRegistrationDto.getFormTutor() != null) {
            formTutor = getUser(formClassRegistrationDto.getFormTutor());
        }

        FormClassEntity formClass = FormClassMapper.INSTANCE.toFormClass(formClassRegistrationDto, formTutor, user.getSchool());
        formClassRepository.save(formClass);

        System.out.println("ActionLog.registerFormClass end.");
    }

    public void changeFormTutor(Long classId, Long instructorId) {
        System.out.println("ActionLog.assignFormTutor start.");
        UserEntity instructor = getUser(instructorId);
        FormClassEntity formClass = getClass(classId);

        formClass.setFormTutor(instructor);
        instructor.setFormClass(formClass);

        userRepository.save(instructor);
        formClassRepository.save(formClass);
        System.out.println("ActionLog.assignFormTutor end.");
    }

    public void changeRoomNumber(Long classId, Long roomNumber) {
        System.out.println("ActionLog.changeRoomNumber start.");

        FormClassEntity formClass = getClass(classId);
        formClass.setRoomNumber(roomNumber);
        formClassRepository.save(formClass);

        System.out.println("ActionLog.changeRoomNumber end.");
    }

    public void addStudentToClass(Long classId, Long studentId) {
        System.out.println("ActionLog.addStudentToClass start.");

        UserEntity student = getUser(studentId);
        FormClassEntity formClass = getClass(classId);

        student.setFormClass(formClass);
        userRepository.save(student);

        System.out.println("ActionLog.addStudentToClass end.");
    }

    public void removeStudentFromClass(Long classId, Long studentId) {
        System.out.println("ActionLog.removeStudentFromClass start.");

        UserEntity student = getUser(studentId);
        student.setFormClass(null);
        userRepository.save(student);

        System.out.println("ActionLog.removeStudentFromClass end.");
    }

    public List<FormClassDto> getAllClasses(Long userId) {
        System.out.println("ActionLog.getAllClasses start.");

        UserEntity user = getUser(userId);

        List<FormClassEntity> formClasses = formClassRepository.findAllBySchool(user.getSchool());

        List<FormClassDto> formClassesDto = formClasses.stream()
                .map(fClass -> new FormClassDto(fClass.getId(), fClass.getYear(),
                        fClass.getIdentifier(), fClass.getRoomNumber()))
                .collect(Collectors.toList());

        System.out.println("ActionLog.getAllClasses end.");

        return formClassesDto;
    }

    public void enrollStudentToClass(Long classId, Long studentId) {
        System.out.println("ActionLog.enrollStudentToClass start.");

        UserEntity student = getUser(studentId);

        FormClassEntity formClass = getClass(classId);

        student.setFormClass(formClass);

        userRepository.save(student);

        System.out.println("ActionLog.enrollStudentToClass end.");
    }

    public List<TimetableCourseDto> getTimetable(Long classId) {
        System.out.println("ActionLog.getTimetable start.");

        FormClassEntity formClass = getClass(classId);
        List<CourseEntity> courses = courseRepository.findAllByFormClass(formClass);

        List<TimetableCourseDto> timetableCourseDtos = new ArrayList<>();

        courses.forEach(course -> {
            String subjectName = course.getSubject().getName();
            String yearAndIdentifier = course.getFormClass().getYear() + course.getFormClass().getIdentifier();
            timetableCourseDtos.add(CourseMapper.INSTANCE.toTimetableCourseDto(course, subjectName, yearAndIdentifier));
        });

        System.out.println("ActionLog.getTimetable end.");

        return timetableCourseDtos;
    }

    private FormClassEntity getClass(Long classId) {
        return formClassRepository.findById(classId).orElseThrow(() -> {
            System.out.println("ActionLog.getClass error class-id: " + classId);
            throw new NotFoundException("EXCEPTION.E-DIARY.FORM-CLASS-NOT-FOUND");
        });
    }

    private UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            System.out.println("ActionLog.getUser error user-id: " + userId);
            throw new NotFoundException("EXCEPTION.E-DIARY.USER-NOT-FOUND");
        });
    }
}
