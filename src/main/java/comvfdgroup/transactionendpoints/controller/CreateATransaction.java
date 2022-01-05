package comvfdgroup.transactionendpoints.controller;

import comvfdgroup.transactionendpoints.dto.DepositDto;
import comvfdgroup.transactionendpoints.dto.SuccessMessageDto;
import comvfdgroup.transactionendpoints.dto.TransferDto;
import comvfdgroup.transactionendpoints.dto.WithdrawalDto;
import comvfdgroup.transactionendpoints.exception.ApiRequestException;
import comvfdgroup.transactionendpoints.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateATransaction {
    private TransactionService transactionService;


    public CreateATransaction(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PutMapping("/deposit/{userId}")
    public ResponseEntity<?> depositAmount(@RequestBody DepositDto depositDto, @PathVariable Integer userId){
        transactionService.depositToAccount(depositDto,userId);
        if (HttpStatus.OK.is2xxSuccessful()) {
            SuccessMessageDto successMessageDto = new SuccessMessageDto();
            successMessageDto.setMessage("00, Deposit was successful");
            successMessageDto.setStatus(HttpStatus.OK);
            return new ResponseEntity<>(successMessageDto, HttpStatus.OK);
        }
        throw new ApiRequestException();
    }

    @PutMapping("/withdraw/{accountId}")
    public ResponseEntity<?> withdrawAmount(@RequestBody WithdrawalDto withdrawalDto, @PathVariable Integer accountId) {
        transactionService.withdrawAmount(withdrawalDto, accountId);
        SuccessMessageDto successMessageDto = new SuccessMessageDto();
        successMessageDto.setMessage("00, Withdrawal was successful");
        successMessageDto.setStatus(HttpStatus.OK);
        if (HttpStatus.OK.is2xxSuccessful()) {
            return new ResponseEntity<>(successMessageDto, HttpStatus.OK);
        }
      throw new ApiRequestException();
    }

    @PutMapping("/transfer/{accountInfoId}")
    public ResponseEntity<?> transferAmount(@RequestBody TransferDto transferDto, @PathVariable Integer accountInfoId ){
        transactionService.transferAmount(transferDto,accountInfoId);
        SuccessMessageDto successMessageDto = new SuccessMessageDto();
        successMessageDto.setMessage("00, Transfer was successful");
        successMessageDto.setStatus(HttpStatus.OK);
        if (HttpStatus.OK.is2xxSuccessful()) {
            return new ResponseEntity<>(successMessageDto, HttpStatus.OK);
        }
        throw new ApiRequestException();
    }
}
