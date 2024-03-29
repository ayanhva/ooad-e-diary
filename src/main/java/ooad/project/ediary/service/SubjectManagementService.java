package ooad.project.ediary.service;

import lombok.AllArgsConstructor;
import ooad.project.ediary.dao.entity.CourseEntity;
import ooad.project.ediary.dao.entity.FormClassEntity;
import ooad.project.ediary.dao.entity.SubjectEntity;
import ooad.project.ediary.dao.repo.CourseRepository;
import ooad.project.ediary.dao.repo.FormClassRepository;
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
@AllArgsConstructor
public class SubjectManagementService {
    private final SubjectRepository subjectRepository;

    public void registerSubject(SubjectRegistrationDto subjectRegistrationDto) {
        System.out.println("ActionLog.registerSubject start.");

        SubjectEntity subjectEntity = SubjectMapper.INSTANCE.toSubjectClass(subjectRegistrationDto);

        subjectRepository.save(subjectEntity);

        System.out.println("ActionLog.registerSubject end.");
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

    private SubjectEntity getSubject(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow(() -> {
            throw new NotFoundException("EXCEPTION.E-DIARY.SUBJECT-NOT-FOUND");
        });
    }
}
