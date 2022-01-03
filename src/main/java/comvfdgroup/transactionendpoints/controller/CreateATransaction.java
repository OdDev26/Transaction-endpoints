package comvfdgroup.transactionendpoints.controller;

import comvfdgroup.transactionendpoints.model.DepositDto;
import comvfdgroup.transactionendpoints.model.TransferDto;
import comvfdgroup.transactionendpoints.model.WithdrawalDto;
import comvfdgroup.transactionendpoints.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateATransaction {
    private TransactionService transactionService;


    public CreateATransaction(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PutMapping("/deposit/{userId}")
    public void depositAmount(@RequestBody DepositDto depositDto, @PathVariable Integer userId){
        transactionService.depositToAccount(depositDto,userId);
    }

    @PutMapping("/withdraw/{accountId}")
    public void withdrawAmount(@RequestBody WithdrawalDto withdrawalDto,@PathVariable Integer accountId){
        transactionService.withdrawAmount(withdrawalDto,accountId);
    }

    @PutMapping("/transfer/{accountInfoId}")
    public void transferAmount(@RequestBody TransferDto transferDto, @PathVariable Integer accountInfoId ){
        transactionService.transferAmount(transferDto,accountInfoId);
    }
}
