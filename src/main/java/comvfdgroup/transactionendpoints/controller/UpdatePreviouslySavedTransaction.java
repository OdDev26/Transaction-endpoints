package comvfdgroup.transactionendpoints.controller;

import comvfdgroup.transactionendpoints.dto.SuccessMessageDto;
import comvfdgroup.transactionendpoints.dto.UpdateTransactionDto;
import comvfdgroup.transactionendpoints.exception.ApiRequestException;
import comvfdgroup.transactionendpoints.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdatePreviouslySavedTransaction {
    @Autowired
    private TransactionService transactionService;
    @PutMapping("/updateTransaction/{accountInfoId}")
    public ResponseEntity<?> updateSavedTransaction(@RequestBody UpdateTransactionDto updateTransactionDto,@PathVariable Integer accountInfoId){
        transactionService.updateSavedTransaction(updateTransactionDto,accountInfoId);
        if(HttpStatus.OK.is2xxSuccessful()){
            SuccessMessageDto successMessageDto = new SuccessMessageDto();
            successMessageDto.setMessage("00, Transaction details was successfully updated");
            successMessageDto.setStatus(HttpStatus.OK);
            return new ResponseEntity<>(successMessageDto, HttpStatus.OK);
        }
        throw new ApiRequestException();
    }
}
