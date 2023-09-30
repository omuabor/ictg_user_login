package org.ictgprojects.UserManager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUserResponse {
    private String status;
    private String responseCode;
    private String responseMessage;
    private Object responseDetails;
}
