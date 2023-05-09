package ooad.project.ediary.service;

import ooad.project.ediary.dao.entity.SchoolEntity;
import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.dao.repo.SchoolRepository;
import ooad.project.ediary.dao.repo.UserRepository;
import ooad.project.ediary.mapper.UserMapper;
import ooad.project.ediary.model.dto.UserDto;
import ooad.project.ediary.model.enums.UserType;
import ooad.project.ediary.model.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;

@Service
public class UserManagementService {
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;

    public UserManagementService(SchoolRepository schoolRepository, UserRepository userRepository) {
        this.schoolRepository = schoolRepository;
        this.userRepository = userRepository;
    }

    //delete these later
    void testUserRegistration() {
        UserDto userDto = new UserDto("ayanhva", "Ayan", "Hashimova", "Talat Sh",
                "+994513244248", LocalDate.of(2002, 3, 4),
                "ahashimova11422@ada.edu.az", UserType.STUDENT);

        registerUser(2L, userDto);
    }

    public void registerUser(Long schoolId, UserDto userDto) {
        System.out.println("ActionLog.registerUser start.");

        SchoolEntity school = schoolRepository.findById(schoolId).orElseThrow(() -> {
            throw new NotFoundException("EXCEPTION.E-DIARY.SCHOOL-NOT-FOUND");
        });

        String password = RandomStringUtils.randomAlphanumeric(9);
        UserEntity userEntity = UserMapper.INSTANCE.toUserEntity(userDto, password, school);
        userRepository.save(userEntity);

        System.out.println("ActionLog.registerUser end.");
    }
}
