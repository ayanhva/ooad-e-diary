package ooad.project.ediary.dao.repo;

import ooad.project.ediary.dao.entity.FormClassEntity;
import ooad.project.ediary.dao.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormClassRepository extends JpaRepository<FormClassEntity, Long> {
    List<FormClassEntity> findAllBySchool(SchoolEntity school);
}
