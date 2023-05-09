package ooad.project.ediary.dao.repo;

import ooad.project.ediary.dao.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
}
