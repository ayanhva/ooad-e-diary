package ooad.project.ediary.service;

import ooad.project.ediary.dao.entity.CourseEntity;
import ooad.project.ediary.dao.entity.SubjectEntity;
import ooad.project.ediary.dao.repo.CourseRepository;
import ooad.project.ediary.dao.repo.SubjectRepository;
import ooad.project.ediary.mapper.CourseMapper;
import ooad.project.ediary.mapper.SubjectMapper;
import ooad.project.ediary.model.dto.CourseRegistrationDto;
import ooad.project.ediary.model.dto.SubjectDto;
import ooad.project.ediary.model.dto.SubjectRegistrationDto;
import ooad.project.ediary.model.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectManagementService {
    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;

    public SubjectManagementService(SubjectRepository subjectRepository, CourseRepository courseRepository) {
        this.subjectRepository = subjectRepository;
        this.courseRepository = courseRepository;
    }

    public void registerSubject(SubjectRegistrationDto subjectRegistrationDto) {
        System.out.println("ActionLog.registerSubject start.");

        SubjectEntity subjectEntity = SubjectMapper.INSTANCE.toSubjectClass(subjectRegistrationDto);

        subjectRepository.save(subjectEntity);

        System.out.println("ActionLog.registerSubject end.");
    }

    public void registerCourse(CourseRegistrationDto courseRegistrationDto) {
        System.out.println("ActionLog.registerCourse start.");

        SubjectEntity subjectEntity = getSubject(courseRegistrationDto.getSubjectId());

        CourseEntity courseEntity = CourseMapper.INSTANCE.toCourseEntity(courseRegistrationDto, subjectEntity);

        courseRepository.save(courseEntity);

        System.out.println("ActionLog.registerCourse end.");
    }

    private SubjectEntity getSubject(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow(() -> {
            throw new NotFoundException("EXCEPTION.E-DIARY.SUBJECT-NOT-FOUND");
        });
    }
    public List<SubjectDto> getAllSubjects() {
        System.out.println("ActionLog.getAllSubjects start.");

        List<SubjectEntity> subjects = subjectRepository.findAll();

        List<SubjectDto> subjectDto = subjects.stream()
                .map(fClass -> new SubjectDto(fClass.getId(), fClass.getName()))
                .collect(Collectors.toList());

        System.out.println("ActionLog.getAllSubjects end.");

        return subjectDto;
    }
}
