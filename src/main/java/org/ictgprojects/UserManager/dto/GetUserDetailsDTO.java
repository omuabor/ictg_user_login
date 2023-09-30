package org.ictgprojects.UserManager.dto;

import lombok.Data;

@Data
public class GetUserDetailsDTO {
    private String userName;
    private String phoneNumber;
    private String email;
}
