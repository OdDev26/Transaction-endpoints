package comvfdgroup.transactionendpoints.controller;

import comvfdgroup.transactionendpoints.dto.SuccessMessageDto;
import comvfdgroup.transactionendpoints.dto.UserDto;
import comvfdgroup.transactionendpoints.exception.ApiRequestException;
import comvfdgroup.transactionendpoints.service.UserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        userRegistrationService.registerUser(userDto);
        if(HttpStatus.OK.is2xxSuccessful()){
            SuccessMessageDto successMessageDto = new SuccessMessageDto();
            successMessageDto.setMessage("00, User was successfully registered");
            successMessageDto.setStatus(HttpStatus.OK);
            return new ResponseEntity<>(successMessageDto, HttpStatus.OK);
        }
        throw new ApiRequestException();
    }
}
