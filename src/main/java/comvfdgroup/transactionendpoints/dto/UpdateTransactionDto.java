package comvfdgroup.transactionendpoints.dto;

import comvfdgroup.transactionendpoints.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateTransactionDto {
    private TransactionType transactionType;
    private LocalDate transactionDate;
    private Double balance;
    private Double transactionAmount;
    private Long depositorAccountNo;
    private Long recipientAccountNo;
}
