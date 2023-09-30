package org.ictgprojects.UserManager.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String userId;
    private String userName;
    private String encryptedPassword;
    private String email;
    private String phoneNumber;

}
