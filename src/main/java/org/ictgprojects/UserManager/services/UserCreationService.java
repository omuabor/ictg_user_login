package org.ictgprojects.UserManager.services;

import lombok.RequiredArgsConstructor;
import org.ictgprojects.UserManager.dto.CreateUserRequest;
import org.ictgprojects.UserManager.dto.GetUserDetailsDTO;
import org.ictgprojects.UserManager.entity.AppUser;
import org.ictgprojects.UserManager.exception.CustomException;
import org.ictgprojects.UserManager.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCreationService {

    private final AppUserRepository appUserRepository;

    public AppUser createUser(CreateUserRequest createUserRequest){

        if (doesUserExist(createUserRequest.getUserId())){
            throw new CustomException("FAILED", "77", "User with User Id already exists!");
        }

        AppUser newUser = AppUser.builder()
                .userId(createUserRequest.getUserId())
                .userName(createUserRequest.getUserName())
                .email(createUserRequest.getEmail())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .encryptedPassword(createUserRequest.getEncryptedPassword())
                .createdDate(LocalDateTime.now().toString())
                .updateDate(LocalDateTime.now().toString())
                .accountDisabled(Boolean.FALSE)
                .ipAddress("localhost")
                .build();

        appUserRepository.save(newUser);

        return newUser;

    }


    public AppUser updateUser(CreateUserRequest createUserRequest){
        if(!doesUserExist(createUserRequest.getUserId())){
            throw new CustomException("FAILED", "55", "Please enter a valid user Id to update details!");
        }

        AppUser user = appUserRepository.findByUserId(createUserRequest.getUserId());

        user.builder()
                .userName(createUserRequest.getUserName())
                .email(createUserRequest.getEmail())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .encryptedPassword(createUserRequest.getEncryptedPassword())
                .updateDate(LocalDateTime.now().toString())
                .ipAddress("localhost")
                .build();
        appUserRepository.save(user);
        return user;
    }

    public List<GetUserDetailsDTO> getAllUsers(){
        List<GetUserDetailsDTO> allUserDetails = new ArrayList<>();
        List<AppUser> appUsers = appUserRepository.findAllByAccountDisabledFalse();
        for (AppUser user: appUsers){
            GetUserDetailsDTO userDetails = new GetUserDetailsDTO();
            userDetails.setEmail(user.getEmail());
            userDetails.setPhoneNumber(user.getPhoneNumber());
            userDetails.setUserName(user.getUserName());
            allUserDetails.add(userDetails);
        }
        return allUserDetails;

    }

    public GetUserDetailsDTO getUser(String userId){
        AppUser appUser = appUserRepository.findByUserId(userId);
        if(appUser == null || appUser.getAccountDisabled().equals(Boolean.TRUE)){
            throw new CustomException("FAILED", "99", "User does not exist");
        }

        GetUserDetailsDTO userResponse = new GetUserDetailsDTO();
        userResponse.setUserName(appUser.getUserName());
        userResponse.setPhoneNumber(appUser.getPhoneNumber());
        userResponse.setEmail(appUser.getEmail());

        return userResponse;
    }

    private boolean doesUserExist(String userId){
        Optional<AppUser> user = appUserRepository.findById(userId);
        return user.isPresent();
    }








}
