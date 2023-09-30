package org.ictgprojects.UserManager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CustomException extends RuntimeException {
    private final String status;
    private final String responseCode;
    private final String responseMessage;

}
