package ooad.project.ediary.service;

import lombok.AllArgsConstructor;
import ooad.project.ediary.dao.entity.AttendanceEntity;
import ooad.project.ediary.dao.entity.CourseEntity;
import ooad.project.ediary.dao.entity.FormClassEntity;
import ooad.project.ediary.dao.entity.SubjectEntity;
import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.dao.repo.AttendanceRepository;
import ooad.project.ediary.dao.entity.TaskEntity;
import ooad.project.ediary.dao.repo.CourseRepository;
import ooad.project.ediary.dao.repo.FormClassRepository;
import ooad.project.ediary.dao.repo.SubjectRepository;
import ooad.project.ediary.dao.repo.UserRepository;
import ooad.project.ediary.mapper.AttendanceMapper;
import ooad.project.ediary.dao.repo.TaskRepository;
import ooad.project.ediary.mapper.CourseMapper;
import ooad.project.ediary.model.dto.AttendanceRecordDto;
import ooad.project.ediary.model.dto.CourseLightDto;
import ooad.project.ediary.mapper.TaskMapper;
import ooad.project.ediary.model.dto.CourseRegistrationDto;
import ooad.project.ediary.model.dto.TaskRegistrationDto;
import ooad.project.ediary.model.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseManagementService {
    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;
    private final FormClassRepository formClassRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

    public void registerCourse(CourseRegistrationDto courseRegistrationDto) {
        System.out.println("ActionLog.registerCourse start.");

        SubjectEntity subjectEntity = getSubject(courseRegistrationDto.getSubjectId());

        FormClassEntity formClass = null;

        if (courseRegistrationDto.getFormClassId() != null){
            formClass = formClassRepository.findById(
                    courseRegistrationDto.getFormClassId()).orElseThrow(() -> {
                throw new NotFoundException("EXCEPTION.E-DIARY.FORM-CLASS-NOT-FOUND");
            });
        }

        CourseEntity courseEntity = CourseMapper.INSTANCE.toCourseEntity(courseRegistrationDto, subjectEntity, formClass);

        courseRepository.save(courseEntity);

        System.out.println("ActionLog.registerCourse end.");
    }

    public List<CourseLightDto> getAllCourses(Long userId, Long subjectId) {
        System.out.println("ActionLog.getAllCourses start.");

        UserEntity user = getUser(userId);
        SubjectEntity subject = getSubject(subjectId);


        System.out.println("ActionLog.getAllCourses end.");

        return List.of(new CourseLightDto(1L, "", "", LocalTime.now(), LocalTime.now(), 2L));
    }

    public void recordAttendance(Long courseId, List<AttendanceRecordDto> attendanceRecords) {
        System.out.println("ActionLog.recordAttendance start.");

        CourseEntity course = getCourse(courseId);

        ArrayList<AttendanceEntity> attendanceEntities = new ArrayList<>();

        attendanceRecords.forEach(
                attendanceRecord -> {
                    UserEntity student = getUser(attendanceRecord.getStudentId());
                    attendanceEntities.add(AttendanceMapper.INSTANCE.toAttendanceEntity(attendanceRecord, course, student));
                }
        );

        attendanceRepository.saveAll(attendanceEntities);

        System.out.println("ActionLog.recordAttendance end.");
    }

    private CourseEntity getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> {
            throw new NotFoundException("EXCEPTION.E-DIARY.COURSE-NOT-FOUND");
        });
    }

    public void addTask(TaskRegistrationDto taskRegistrationDto) {
        System.out.println("ActionLog.addTask start.");

        CourseEntity courseEntity = getCourse(taskRegistrationDto.getCourseId());

        TaskEntity taskEntity = TaskMapper.INSTANCE.toTaskEntity(taskRegistrationDto, courseEntity);

        taskRepository.save(taskEntity);

        System.out.println("ActionLog.addTask end.");
    }

    private SubjectEntity getSubject(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow(() -> {
            throw new NotFoundException("EXCEPTION.E-DIARY.SUBJECT-NOT-FOUND");
        });
    }

    private UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new NotFoundException("EXCEPTION.E-DIARY.USER-NOT-FOUND");
        });
    }
}
