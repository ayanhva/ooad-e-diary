package ooad.project.ediary.dao.repo;

import ooad.project.ediary.dao.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

}
