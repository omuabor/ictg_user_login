package org.ictgprojects.UserManager.services;

import lombok.RequiredArgsConstructor;
import org.ictgprojects.UserManager.entity.AppUser;
import org.ictgprojects.UserManager.exception.CustomException;
import org.ictgprojects.UserManager.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisableUserService {

    private final AppUserRepository appUserRepository;

    public void disableUser(String userId){
        AppUser user = appUserRepository.findByUserId(userId);
        if(user == null){
            throw new CustomException("FAILED", "55", "User does not exist");
        }
        user.setAccountDisabled(Boolean.TRUE);
        appUserRepository.save(user);
    }
}
