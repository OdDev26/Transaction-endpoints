package comvfdgroup.transactionendpoints.controller;

import comvfdgroup.transactionendpoints.dto.SuccessMessageDto;
import comvfdgroup.transactionendpoints.exception.ApiRequestException;
import comvfdgroup.transactionendpoints.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> deleteTransaction(@PathVariable Integer accountInfoId){
        transactionService.deleteTransaction(accountInfoId);
        SuccessMessageDto successMessageDto = new SuccessMessageDto();
        successMessageDto.setMessage("00, transaction detail successfully deleted");
        successMessageDto.setStatus(HttpStatus.OK);
        if (HttpStatus.OK.is2xxSuccessful()) {
            return new ResponseEntity<>(successMessageDto, HttpStatus.OK);
        }
        throw new ApiRequestException();
    }
}
