package comvfdgroup.transactionendpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private Long accountNo;
}
