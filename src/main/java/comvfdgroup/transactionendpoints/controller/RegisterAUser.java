package comvfdgroup.transactionendpoints.controller;

import comvfdgroup.transactionendpoints.model.User;
import comvfdgroup.transactionendpoints.model.UserDto;
import comvfdgroup.transactionendpoints.service.UserRegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterAUser {
    private UserRegistrationService userRegistrationService;

    public RegisterAUser(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserDto userDto){
        userRegistrationService.registerUser(userDto);
    }
}
