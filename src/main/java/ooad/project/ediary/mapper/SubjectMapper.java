package ooad.project.ediary.mapper;

import ooad.project.ediary.dao.entity.SubjectEntity;
import ooad.project.ediary.model.dto.SubjectRegistrationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class SubjectMapper {

    public static final SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    @Mapping(target = "name", source = "subjectDto.name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract SubjectEntity toSubjectClass(SubjectRegistrationDto subjectDto);
}
