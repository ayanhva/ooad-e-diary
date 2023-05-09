package ooad.project.ediary.mapper;

import ooad.project.ediary.dao.entity.CourseEntity;
import ooad.project.ediary.dao.entity.SubjectEntity;
import ooad.project.ediary.model.dto.CourseRegistrationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CourseMapper {

    public static final CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "crn", source = "courseRegistrationDto.crn")
    @Mapping(target = "weekday", source = "courseRegistrationDto.weekday")
    @Mapping(target = "startTime", source = "courseRegistrationDto.startTime")
    @Mapping(target = "endTime", source = "courseRegistrationDto.endTime")
    @Mapping(target = "roomNumber", source = "courseRegistrationDto.roomNumber")
    @Mapping(target = "subject", source = "subject")
    public abstract CourseEntity toCourseEntity(CourseRegistrationDto courseRegistrationDto, SubjectEntity subject);
}
