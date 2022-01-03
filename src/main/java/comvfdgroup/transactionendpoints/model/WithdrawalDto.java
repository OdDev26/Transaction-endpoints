package comvfdgroup.transactionendpoints.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WithdrawalDto {
    private double amount;
    private TransactionType transactionType;
    private String transactionDate;
}
