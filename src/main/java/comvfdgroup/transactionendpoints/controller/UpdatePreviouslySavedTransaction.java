package comvfdgroup.transactionendpoints.controller;

import comvfdgroup.transactionendpoints.model.UpdateTransactionDto;
import comvfdgroup.transactionendpoints.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdatePreviouslySavedTransaction {
    @Autowired
    private TransactionService transactionService;
    @PutMapping("/updateTransaction/{accountInfoId}")
    public void updateSavedTransaction(@RequestBody UpdateTransactionDto updateTransactionDto,@PathVariable Integer accountInfoId){
        transactionService.updateSavedTransaction(updateTransactionDto,accountInfoId);
    }
}
