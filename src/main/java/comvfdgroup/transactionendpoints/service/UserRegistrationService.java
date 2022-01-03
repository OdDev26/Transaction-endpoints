package comvfdgroup.transactionendpoints.service;

import comvfdgroup.transactionendpoints.model.User;
import comvfdgroup.transactionendpoints.model.UserDto;
import comvfdgroup.transactionendpoints.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserDto userDto){
        User user1= new User();
        user1.setFirstName(userDto.getFirstName());
        user1.setLastName(userDto.getLastName());
        user1.setGender(userDto.getGender());
        user1.setDateOfBirth(userDto.getDateOfBirth());
        user1.setAccountNo(userDto.getAccountNo());
        userRepository.save(user1);
    }
}
