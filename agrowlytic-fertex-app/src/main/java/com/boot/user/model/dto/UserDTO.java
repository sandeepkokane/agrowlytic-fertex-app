package com.boot.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String emailAddress;
    private String password;

}
