package ooad.project.ediary.dao.repo;

import ooad.project.ediary.dao.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {
}
