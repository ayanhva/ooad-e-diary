package ooad.project.ediary.service;

import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.dao.repo.FormClassRepository;
import ooad.project.ediary.dao.repo.SchoolRepository;
import ooad.project.ediary.dao.repo.UserRepository;
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

    public void registerFormClass(FormClassDto formClassDto) {
        System.out.println("ActionLog.registerFormClass start.");

        UserEntity formTutor;
        if (formClassDto.getFormTutor() != null) {
            formTutor = userRepository.findById(formClassDto.getFormTutor()).orElseThrow(() -> {
                throw new NotFoundException("EXCEPTION.E-DIARY.FORM-TUTOR-NOT-FOUND");
            });
        }


        System.out.println("ActionLog.registerFormClass end.");
    }
}
