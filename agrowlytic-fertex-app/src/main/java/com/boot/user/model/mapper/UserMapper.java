package com.boot.user.model.mapper;

import com.boot.user.model.dto.UserDTO;
import com.boot.user.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User getUser(UserDTO userDTO) {
        final User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserDTO getDTO(User user) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmailAddress(user.getEmailAddress());
        userDTO.setMobileNumber(user.getMobileNumber());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
