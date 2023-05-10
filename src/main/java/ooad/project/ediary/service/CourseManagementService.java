package ooad.project.ediary.service;

import lombok.AllArgsConstructor;
import ooad.project.ediary.dao.entity.CourseEntity;
import ooad.project.ediary.dao.entity.FormClassEntity;
import ooad.project.ediary.dao.entity.SubjectEntity;
import ooad.project.ediary.dao.entity.TaskEntity;
import ooad.project.ediary.dao.repo.CourseRepository;
import ooad.project.ediary.dao.repo.FormClassRepository;
import ooad.project.ediary.dao.repo.SubjectRepository;
import ooad.project.ediary.dao.repo.TaskRepository;
import ooad.project.ediary.mapper.CourseMapper;
import ooad.project.ediary.mapper.TaskMapper;
import ooad.project.ediary.model.dto.CourseRegistrationDto;
import ooad.project.ediary.model.dto.TaskRegistrationDto;
import ooad.project.ediary.model.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseManagementService {
    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;
    private final FormClassRepository formClassRepository;
    private final TaskRepository taskRepository;

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

    private CourseEntity getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> {
            throw new NotFoundException("EXCEPTION.E-DIARY.COURSE-NOT-FOUND");
        });
    }
}
