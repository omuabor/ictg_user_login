package org.ictgprojects.UserManager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "ICTG_S_USER")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    private String userId;
    private String userName;
    private String email;
    private String phoneNumber;
    private String createdDate;
    private String updateDate;
    private String ipAddress;
    private Boolean accountDisabled = Boolean.FALSE;
    private String encryptedPassword;
}

