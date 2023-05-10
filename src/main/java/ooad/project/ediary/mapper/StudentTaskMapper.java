package ooad.project.ediary.mapper;

import ooad.project.ediary.dao.entity.CourseEntity;
import ooad.project.ediary.dao.entity.StudentTaskEntity;
import ooad.project.ediary.dao.entity.TaskEntity;
import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.model.dto.StudentTaskDto;
import ooad.project.ediary.model.dto.StudentTaskRegistrationDto;
import ooad.project.ediary.model.dto.TaskRegistrationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class StudentTaskMapper {

    public static final StudentTaskMapper INSTANCE = Mappers.getMapper(StudentTaskMapper.class);

    @Mapping(target = "grade", source = "studentTaskDto.grade")
    @Mapping(target = "student", source = "student")
    @Mapping(target = "task", source = "task")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract StudentTaskEntity toStudentTaskEntity(StudentTaskRegistrationDto studentTaskDto, UserEntity student, TaskEntity task);
}
