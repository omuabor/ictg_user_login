package org.ictgprojects.UserManager.controller;

import lombok.RequiredArgsConstructor;
import org.ictgprojects.UserManager.dto.CreateUserRequest;
import org.ictgprojects.UserManager.dto.AppUserResponse;
import org.ictgprojects.UserManager.dto.GetUserDetailsDTO;
import org.ictgprojects.UserManager.entity.AppUser;
import org.ictgprojects.UserManager.services.DisableUserService;
import org.ictgprojects.UserManager.services.UserCreationService;
import org.ictgprojects.UserManager.services.UserLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCreationService userCreationService;

    private final DisableUserService disableUserService;

    private final UserLoginService userLoginService;

    @PostMapping("/create-user")
    public ResponseEntity<AppUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {

        AppUser newUser = userCreationService.createUser(createUserRequest);

        return new ResponseEntity<>(new AppUserResponse(
                "SUCCESSFUL",
                "000",
                "User Creation Successful",
                "User with userId: " + newUser.getUserId() + " successfully created!"
        ), HttpStatus.OK);
    }

    @PutMapping("/update-user")
    public ResponseEntity<AppUserResponse> updateUser(@RequestBody CreateUserRequest createUserRequest) {
        AppUser updatedUser = userCreationService.updateUser(createUserRequest);
        return new ResponseEntity<>(new AppUserResponse(
                "SUCCESSFUL",
                "000",
                "User Update Successful",
                "User with userId: " + updatedUser.getUserId() + " successfully updated!"
        ), HttpStatus.OK);
    }

    @PostMapping("/authenticate-user")
    public ResponseEntity<AppUserResponse> authenticateUser(@RequestBody CreateUserRequest createUserRequest) {
        userLoginService.authenticateUser(createUserRequest.getEncryptedPassword(), createUserRequest.getUserId());

        return new ResponseEntity<>(new AppUserResponse(
                "SUCCESSFUL",
                "000",
                "User Successfully Authenticated",
                "User Successfully Authenticated"
        ), HttpStatus.OK);
    }

    @GetMapping("/all-users")
    public ResponseEntity<AppUserResponse> listAllUsers(){

        List<GetUserDetailsDTO> allActiveUsers = userCreationService.getAllUsers();

        return new ResponseEntity<>(new AppUserResponse(
                "SUCCESSFUL",
                "000",
                "All Users Information Fetched!",
                allActiveUsers
        ), HttpStatus.OK);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<AppUserResponse> listUserDetails(@PathVariable String userId){

        GetUserDetailsDTO userDetails = userCreationService.getUser(userId);

        return new ResponseEntity<>(new AppUserResponse(
                "SUCCESSFUL",
                "000",
                "Users Information Fetched!",
                userDetails
        ), HttpStatus.OK);

    }

    @PostMapping("/disable-user/{userId}")
    public ResponseEntity<AppUserResponse> disableUser(@PathVariable String userId){

        disableUserService.disableUser(userId);


        return new ResponseEntity<>(new AppUserResponse(
                "SUCCESSFUL",
                "000",
                "Users Successfully Disabled!",
                null
        ), HttpStatus.OK);

    }



}
