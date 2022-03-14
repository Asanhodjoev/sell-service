package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.UserResponseDto;
import kg.itschool.sellservice.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    UserResponseDto userToUserResponseDto(User user);
}
