package ooad.project.ediary.service;

import lombok.AllArgsConstructor;
import ooad.project.ediary.dao.entity.*;
import ooad.project.ediary.dao.repo.*;
import ooad.project.ediary.mapper.AttendanceMapper;
import ooad.project.ediary.mapper.CourseMapper;
import ooad.project.ediary.mapper.StudentTaskMapper;
import ooad.project.ediary.model.dto.*;
import ooad.project.ediary.mapper.TaskMapper;
import ooad.project.ediary.model.exception.NotFoundException;
import org.springframework.scheduling.config.Task;
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
    private final StudentTaskRepository studentTaskRepository;

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

    public void gradeTask(StudentTaskRegistrationDto studentTaskRegistrationDto) {
        System.out.println("ActionLog.gradeTask start.");

        UserEntity user = getUser(studentTaskRegistrationDto.getStudentId());
        TaskEntity task = getTask(studentTaskRegistrationDto.getTaskId());

        StudentTaskEntity studentTaskEntity = StudentTaskMapper.INSTANCE.toStudentTaskEntity(studentTaskRegistrationDto, user, task);
        studentTaskRepository.save(studentTaskEntity);

        System.out.println("ActionLog.gradeTask end.");
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

    private TaskEntity getTask(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> {
            throw new NotFoundException("EXCEPTION.TASK-NOT-FOUND");
        });
    }
}
