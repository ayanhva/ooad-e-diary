package ooad.project.ediary.dao.repo;

import ooad.project.ediary.dao.entity.CourseEntity;
import ooad.project.ediary.dao.entity.FormClassEntity;
import ooad.project.ediary.dao.entity.SchoolEntity;
import ooad.project.ediary.dao.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findAllBySubjectAndSchool(SubjectEntity subject, SchoolEntity school);

    List<CourseEntity> findAllByFormClass(FormClassEntity formClass);
}