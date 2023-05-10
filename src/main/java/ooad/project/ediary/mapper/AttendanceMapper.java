package ooad.project.ediary.mapper;

import ooad.project.ediary.dao.entity.AttendanceEntity;
import ooad.project.ediary.dao.entity.CourseEntity;
import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.model.dto.AttendanceRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class AttendanceMapper {

    public static final AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    @Mapping(target = "mark", source = "attendanceRecordDto.mark")
    @Mapping(target = "note", source = "attendanceRecordDto.note")
    @Mapping(target = "student", source = "student")
    @Mapping(target = "course", source = "course")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract AttendanceEntity toAttendanceEntity(AttendanceRecordDto attendanceRecordDto,
                                                        CourseEntity course,
                                                        UserEntity student);
}
