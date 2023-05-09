package ooad.project.ediary.mapper;

import ooad.project.ediary.dao.entity.SchoolEntity;
import ooad.project.ediary.dao.entity.UserEntity;
import ooad.project.ediary.model.dto.UserRegistrationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "username", source = "userDto.username")
    @Mapping(target = "name", source = "userDto.name")
    @Mapping(target = "surname", source = "userDto.surname")
    @Mapping(target = "address", source = "userDto.address")
    @Mapping(target = "phoneNumber", source = "userDto.phoneNumber")
    @Mapping(target = "birthDate", source = "userDto.birthDate")
    @Mapping(target = "email", source = "userDto.email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "type", source = "userDto.type")
    @Mapping(target = "school", source = "school")
    public abstract UserEntity toUserEntity(UserRegistrationDto userDto, String password, SchoolEntity school);
}
