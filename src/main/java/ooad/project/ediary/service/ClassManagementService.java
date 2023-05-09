package ooad.project.ediary.service;

import ooad.project.ediary.dao.entity.FormClassEntity;
import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.dao.repo.FormClassRepository;
import ooad.project.ediary.dao.repo.SchoolRepository;
import ooad.project.ediary.dao.repo.UserRepository;
import ooad.project.ediary.mapper.FormClassMapper;
import ooad.project.ediary.model.dto.FormClassDto;
import ooad.project.ediary.model.dto.FormClassRegistrationDto;
import ooad.project.ediary.model.dto.UserLightDto;
import ooad.project.ediary.model.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassManagementService {
    private final SchoolRepository schoolRepository;
    private final FormClassRepository formClassRepository;
    private final UserRepository userRepository;

    public ClassManagementService(SchoolRepository schoolRepository,
                                  FormClassRepository formClassRepository,
                                  UserRepository userRepository) {
        this.schoolRepository = schoolRepository;
        this.formClassRepository = formClassRepository;
        this.userRepository = userRepository;
    }

    public void registerFormClass(Long userId, FormClassRegistrationDto formClassRegistrationDto) {
        System.out.println("ActionLog.registerFormClass start.");

        UserEntity user = getUser(userId);

        UserEntity formTutor = null;
        if (formClassRegistrationDto.getFormTutor() != null) {
            formTutor = userRepository.findById(formClassRegistrationDto.getFormTutor()).orElseThrow(() -> {
                throw new NotFoundException("EXCEPTION.E-DIARY.FORM-TUTOR-NOT-FOUND");
            });
        }

        FormClassEntity formClass = FormClassMapper.INSTANCE.toFormClass(formClassRegistrationDto, formTutor, user.getSchool());
        formClassRepository.save(formClass);

        System.out.println("ActionLog.registerFormClass end.");
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

    private UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new NotFoundException("EXCEPTION.E-DIARY.USER-NOT-FOUND");
        });
    }
}
