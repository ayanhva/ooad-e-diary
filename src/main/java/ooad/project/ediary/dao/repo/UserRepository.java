package ooad.project.ediary.dao.repo;

import ooad.project.ediary.dao.entity.SchoolEntity;
import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.model.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByTypeAndSchool(UserType userType, SchoolEntity scool);
}
