package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.user.UserDTO;
import com.icesi.economiacircularicesi.dto.user.UserNoPassDTO;
import com.icesi.economiacircularicesi.model.user.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromDTO(UserDTO userDTO);
    UserDTO fromUser(User user);
    UserNoPassDTO fromUserToNoPass(User user);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromUser(User updatedUser, @MappingTarget User user);  // The @MappingTarget annotation lets us update an existing object without writing a lot of code.

}
