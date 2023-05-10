package ooad.project.ediary.mapper;

import ooad.project.ediary.dao.entity.CourseEntity;
import ooad.project.ediary.dao.entity.FormClassEntity;
import ooad.project.ediary.dao.entity.SchoolEntity;
import ooad.project.ediary.dao.entity.SubjectEntity;
import ooad.project.ediary.model.dto.CourseRegistrationDto;
import ooad.project.ediary.model.dto.TimetableCourseDto;
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
    @Mapping(target = "formClass", source = "formClass")
    @Mapping(target = "school", source = "school")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract CourseEntity toCourseEntity(CourseRegistrationDto courseRegistrationDto,
                                                SubjectEntity subject,
                                                FormClassEntity formClass,
                                                SchoolEntity school);


    @Mapping(target = "id", source = "course.id")
    @Mapping(target = "crn", source = "course.crn")
    @Mapping(target = "weekday", source = "course.weekday")
    @Mapping(target = "startTime", source = "course.startTime")
    @Mapping(target = "endTime", source = "course.endTime")
    @Mapping(target = "roomNumber", source = "course.roomNumber")
    @Mapping(target = "subjectName", source = "subjectName")
    @Mapping(target = "formClassYearAndIdentifier", source = "formClassYearAndIdentifier")
    public abstract TimetableCourseDto toTimetableCourseDto(CourseEntity course,
                                                            String subjectName,
                                                            String formClassYearAndIdentifier);
}
