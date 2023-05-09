package ooad.project.ediary.service;

import ooad.project.ediary.dao.entity.FormClassEntity;
import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.dao.repo.FormClassRepository;
import ooad.project.ediary.dao.repo.UserRepository;
import ooad.project.ediary.mapper.FormClassMapper;
import ooad.project.ediary.model.dto.FormClassDto;
import ooad.project.ediary.model.dto.FormClassRegistrationDto;
import ooad.project.ediary.model.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassManagementService {
    private final FormClassRepository formClassRepository;
    private final UserRepository userRepository;

    public ClassManagementService(FormClassRepository formClassRepository,
                                  UserRepository userRepository) {
        this.formClassRepository = formClassRepository;
        this.userRepository = userRepository;
    }

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
