package ooad.project.ediary.dao.repo;

import ooad.project.ediary.dao.entity.SchoolEntity;
import ooad.project.ediary.dao.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
