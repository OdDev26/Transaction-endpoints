package comvfdgroup.transactionendpoints.controller;

import comvfdgroup.transactionendpoints.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteTransaction {
    private TransactionService transactionService;

    public DeleteTransaction(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @DeleteMapping("/deleteTransaction/{accountInfoId}")
    public void deleteTransaction(@PathVariable Integer accountInfoId){
        transactionService.deleteTransaction(accountInfoId);
    }
}
