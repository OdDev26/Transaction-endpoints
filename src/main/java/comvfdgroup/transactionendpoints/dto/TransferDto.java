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
public class TransferDto {
    private Double amount;
    private Long depositorAccountNo;
    private Long recipientAccountNo;
    private TransactionType transactionType;
    private String transactionDate;
}
