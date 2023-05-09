package ooad.project.ediary.service;

import ooad.project.ediary.dao.entity.FormClassEntity;
import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.dao.repo.FormClassRepository;
import ooad.project.ediary.dao.repo.SchoolRepository;
import ooad.project.ediary.dao.repo.UserRepository;
import ooad.project.ediary.mapper.FormClassMapper;
import ooad.project.ediary.model.dto.FormClassDto;
import ooad.project.ediary.model.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CourseManagementService {
    private final SchoolRepository schoolRepository;
    private final FormClassRepository formClassRepository;
    private final UserRepository userRepository;

    public CourseManagementService(SchoolRepository schoolRepository,
                                   FormClassRepository formClassRepository,
                                   UserRepository userRepository) {
        this.schoolRepository = schoolRepository;
        this.formClassRepository = formClassRepository;
        this.userRepository = userRepository;
    }

    void test() {
        FormClassDto formClassDto = new FormClassDto(9L, "A", null, 333L);

    }

    public void registerFormClass(Long userId, FormClassDto formClassDto) {
        System.out.println("ActionLog.registerFormClass start.");

        UserEntity user = getUser(userId);

        UserEntity formTutor = null;
        if (formClassDto.getFormTutor() != null) {
            formTutor = userRepository.findById(formClassDto.getFormTutor()).orElseThrow(() -> {
                throw new NotFoundException("EXCEPTION.E-DIARY.FORM-TUTOR-NOT-FOUND");
            });
        }

        FormClassEntity formClass = FormClassMapper.INSTANCE.toFormClass(formClassDto, formTutor, user.getSchool());
        formClassRepository.save(formClass);

        System.out.println("ActionLog.registerFormClass end.");
    }

    private UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new NotFoundException("EXCEPTION.E-DIARY.USER-NOT-FOUND");
        });
    }
}
