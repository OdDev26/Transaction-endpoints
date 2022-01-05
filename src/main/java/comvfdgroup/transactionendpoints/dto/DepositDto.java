package comvfdgroup.transactionendpoints.dto;

import comvfdgroup.transactionendpoints.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositDto {
    private Long depositorAccountNo;
    private Double amount;
    private String transactionDate;
    private TransactionType transactionType;
}
