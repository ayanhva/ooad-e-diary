package ooad.project.ediary.service;

import ooad.project.ediary.dao.entity.SubjectEntity;
import ooad.project.ediary.dao.repo.SubjectRepository;
import ooad.project.ediary.mapper.SubjectMapper;
import ooad.project.ediary.model.dto.SubjectDto;
import ooad.project.ediary.model.dto.SubjectRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectManagementService {
    private final SubjectRepository subjectRepository;

    public SubjectManagementService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

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
}
