package ooad.project.ediary.mapper;

import ooad.project.ediary.dao.entity.FormClassEntity;
import ooad.project.ediary.dao.entity.SchoolEntity;
import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.model.dto.FormClassDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class FormClassMapper {

    public static final FormClassMapper INSTANCE = Mappers.getMapper(FormClassMapper.class);

    @Mapping(target = "year", source = "formClassDto.year")
    @Mapping(target = "identifier", source = "formClassDto.identifier")
    @Mapping(target = "formTutor", source = "formTutor")
    @Mapping(target = "roomNumber", source = "formClassDto.roomNumber")
    @Mapping(target = "school", source = "school")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract FormClassEntity toFormClass(FormClassDto formClassDto, UserEntity formTutor, SchoolEntity school);
}
