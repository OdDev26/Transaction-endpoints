package comvfdgroup.transactionendpoints.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DepositDto {
    private Long depositorAccountNo;
    private Double amount;
    private String transactionDate;
    private TransactionType transactionType;
}
