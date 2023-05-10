package ooad.project.ediary.service;

import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.dao.repo.UserRepository;
import ooad.project.ediary.mapper.UserMapper;
import ooad.project.ediary.model.dto.UserLightDto;
import ooad.project.ediary.model.dto.UserRegistrationDto;
import ooad.project.ediary.model.enums.UserType;
import ooad.project.ediary.model.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementService {
    private final UserRepository userRepository;

    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(Long userId, UserRegistrationDto userRegistrationDto) {
        System.out.println("ActionLog.registerUser start.");

        UserEntity user = getUser(userId);

        String password = RandomStringUtils.randomAlphanumeric(9);
        UserEntity userEntity = UserMapper.INSTANCE.toUserEntity(userRegistrationDto, password, user.getSchool());
        userRepository.save(userEntity);

        System.out.println("ActionLog.registerUser end.");
    }

    public List<UserLightDto> getAllInstructors(Long userId) {
        System.out.println("ActionLog.getAllInstructors start.");

        UserEntity user = getUser(userId);

        List<UserEntity> instructors = userRepository.findAllByTypeAndSchool(UserType.INSTRUCTOR, user.getSchool());

        List<UserLightDto> instructorsDto = instructors.stream()
                .map(instructor -> new UserLightDto(instructor.getId(), instructor.getUsername(),
                        instructor.getName(), instructor.getSurname(), instructor.getEmail()))
                .collect(Collectors.toList());

        System.out.println("ActionLog.getAllInstructors end.");

        return instructorsDto;
    }

    public List<UserLightDto> getAllStudents(Long userId) {
        System.out.println("ActionLog.getAllInstructors start.");

        UserEntity user = getUser(userId);

        List<UserEntity> students = userRepository.findAllByTypeAndSchool(UserType.STUDENT, user.getSchool());

        List<UserLightDto> studentsDto = students.stream()
                .map(student -> new UserLightDto(student.getId(), student.getUsername(),
                        student.getName(), student.getSurname(), student.getEmail()))
                .collect(Collectors.toList());

        System.out.println("ActionLog.getAllInstructors end.");

        return studentsDto;
    }

    public void deleteUser(Long userId) {
        System.out.println("ActionLog.deleteUser start.");

        UserEntity user = getUser(userId);
        userRepository.delete(user);

        System.out.println("ActionLog.deleteUser end.");
    }

    public List<UserLightDto> getAllUsers(Long userId) {
        System.out.println("ActionLog.getAllUsers start.");

        UserEntity user = getUser(userId);
        List<UserEntity> users = userRepository.findAllBySchoolAndTypeNot(user.getSchool(), UserType.ADMIN);

        List<UserLightDto> usersDto = users.stream()
                .map(u -> new UserLightDto(u.getId(), u.getUsername(),
                        u.getName(), u.getSurname(), u.getEmail()))
                .collect(Collectors.toList());

        System.out.println("ActionLog.getAllUsers end.");

        return usersDto;
    }

    public UserType getUserType(Long userId) {
        System.out.println("ActionLog.getUserType start.");

        UserEntity user = getUser(userId);

        System.out.println("ActionLog.getUserType end.");

        return user.getType();
    }

    private UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new NotFoundException("EXCEPTION.E-DIARY.USER-NOT-FOUND");
        });
    }
}
