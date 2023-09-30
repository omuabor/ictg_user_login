package org.ictgprojects.UserManager.services;

import lombok.RequiredArgsConstructor;
import org.ictgprojects.UserManager.entity.AppUser;
import org.ictgprojects.UserManager.exception.CustomException;
import org.ictgprojects.UserManager.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLoginService {

//    private String salt = "ICTG";

    private final AppUserRepository appUserRepository;

    public boolean authenticateUser(String encryptedPassword, String userId){

        AppUser foundUser = appUserRepository.findByUserId(userId);

        if(foundUser == null){
            throw new CustomException("FAILED", "99", "User does not exist");
        } else if(foundUser.getAccountDisabled().equals(Boolean.TRUE)){
            throw new CustomException("FAILED", "99", "User Account is Inactive");
        } else if(!foundUser.getEncryptedPassword().equals(encryptedPassword)){
            throw new CustomException("FAILED", "99", "Incorrect Password");
        } else {
            return foundUser.getEncryptedPassword().equals(encryptedPassword);
        }

    }
}
