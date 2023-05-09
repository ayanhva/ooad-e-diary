package ooad.project.ediary.dao.repo;

import ooad.project.ediary.dao.entity.FormClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormClassRepository extends JpaRepository<FormClassEntity, Long> {
}
