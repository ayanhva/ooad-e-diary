package ooad.project.ediary.mapper;

import ooad.project.ediary.dao.entity.CourseEntity;
import ooad.project.ediary.dao.entity.TaskEntity;
import ooad.project.ediary.model.dto.TaskRegistrationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TaskMapper {

    public static final TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "name", source = "taskDto.name")
    @Mapping(target = "description", source = "taskDto.description")
    @Mapping(target = "type", source = "taskDto.type")
    @Mapping(target = "deadline", source = "taskDto.deadline")
    @Mapping(target = "course", source = "course")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract TaskEntity toTaskEntity(TaskRegistrationDto taskDto, CourseEntity course);
}
