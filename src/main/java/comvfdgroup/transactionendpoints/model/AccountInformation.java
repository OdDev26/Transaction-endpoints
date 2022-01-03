package comvfdgroup.transactionendpoints.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private LocalDate transactionDate;
    private Double balance;
    private Double transactionAmount;
    private Long depositorAccountNo;
    private Long recipientAccountNo;
    @OneToOne(mappedBy = "accountInformation")
    private User user;
}
