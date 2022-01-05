package comvfdgroup.transactionendpoints.service;

import comvfdgroup.transactionendpoints.dto.UserDto;
import comvfdgroup.transactionendpoints.model.User;
import comvfdgroup.transactionendpoints.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceTest {
    @Mock
    private UserRepository userRepository;

    private UserRegistrationService userRegistrationService;

    @BeforeEach
    void setUp() {
        userRegistrationService= new UserRegistrationService(userRepository);
    }

    @Test
    void registerUser() {


        UserDto userDto= new UserDto("Ken","Kent", LocalDate.of(1991,04,21),"Male",
                12345L);

        User ken= new User();
        ken.setFirstName(userDto.getFirstName());
        ken.setLastName(userDto.getLastName());
        ken.setGender(userDto.getGender());
        ken.setDateOfBirth(userDto.getDateOfBirth());
        ken.setAccountNo(userDto.getAccountNo());
        userRegistrationService.registerUser(userDto);

        Mockito.verify(userRepository).save(ken);
    }
}