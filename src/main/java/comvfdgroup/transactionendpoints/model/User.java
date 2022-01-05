package comvfdgroup.transactionendpoints.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appUser")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private Long accountNo;

    public User(Integer userId, String firstName, String lastName, LocalDate dateOfBirth, String gender, Long accountNo) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.accountNo = accountNo;
    }

    @OneToOne
    private AccountInformation accountInformation;
}
