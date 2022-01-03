package comvfdgroup.transactionendpoints.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TransferDto {
    private Double amount;
    private Long depositorAccountNo;
    private Long recipientAccountNo;
    private TransactionType transactionType;
    private String transactionDate;
}
