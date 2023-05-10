package ooad.project.ediary.dao.repo;

import ooad.project.ediary.dao.entity.StudentTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentTaskRepository extends JpaRepository<StudentTaskEntity, Long> {
}
