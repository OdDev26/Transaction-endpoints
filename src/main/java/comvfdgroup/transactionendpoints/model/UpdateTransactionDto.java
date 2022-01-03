package comvfdgroup.transactionendpoints.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class UpdateTransactionDto {
    private TransactionType transactionType;
    private LocalDate transactionDate;
    private Double balance;
    private Double transactionAmount;
    private Long depositorAccountNo;
    private Long recipientAccountNo;
}
