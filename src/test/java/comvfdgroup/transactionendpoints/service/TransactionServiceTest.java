package comvfdgroup.transactionendpoints.service;

import comvfdgroup.transactionendpoints.dto.*;
import comvfdgroup.transactionendpoints.exception.ApiRequestException;
import comvfdgroup.transactionendpoints.model.AccountInformation;
import comvfdgroup.transactionendpoints.model.TransactionType;
import comvfdgroup.transactionendpoints.model.User;
import comvfdgroup.transactionendpoints.repository.AccountInformationRepo;
import comvfdgroup.transactionendpoints.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountInformationRepo accountInformationRepo;

    private TransactionService transactionService;

    private UserRegistrationService userRegistrationService;



    @BeforeEach
    void setUp() {
        transactionService= new TransactionService(accountInformationRepo,userRepository);
    }


    @Test
    void accountInfoCreatedOnDeposit() {
        DepositDto depositDto= new DepositDto(12345L,12000.00,"2022-01-04", TransactionType.DEPOSIT);
        AccountInformation accountInformation= new AccountInformation(1,TransactionType.DEPOSIT,LocalDate.now(),0.00,10000.00,12345L,12345L);
        UserDto userDto= new UserDto("Mike","Egbe-iyon", LocalDate.of(2022,1,4),"Male",12345L
        );

        AccountInformation accountInformation1= new AccountInformation();

        accountInformation1.setId(1);
        accountInformation1.setBalance(depositDto.getAmount());
        accountInformation1.setTransactionAmount(depositDto.getAmount());
        accountInformation1.setTransactionDate(LocalDate.parse(depositDto.getTransactionDate()));
        accountInformation1.setTransactionType(depositDto.getTransactionType());
        accountInformation1.setDepositorAccountNo(depositDto.getDepositorAccountNo());
        accountInformation1.setRecipientAccountNo(depositDto.getDepositorAccountNo());

        User user= new User();
        user.setUserId(1);
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setAccountNo(userDto.getAccountNo());
        user.setAccountInformation(accountInformation);
        user.setGender(userDto.getGender());
        user.setDateOfBirth(userDto.getDateOfBirth());


        given(userRepository.findById(1)).willReturn(Optional.of(user));


        transactionService.depositToAccount(depositDto,1);

        verify(accountInformationRepo).save(accountInformation1);



    }

    @Test
    void throwsInsufficientFundsIfWithDrawalAmountIsGreaterThanBalanceAndBalanceIsZero() {
        WithdrawalDto withdrawalDto= new WithdrawalDto(12345,TransactionType.WITHDRAWAL,"2022-02-12");
        AccountInformation accountInformation= new AccountInformation(1,TransactionType.DEPOSIT,LocalDate.now(),0.00,10000.00,12345L,12345L);
        given(accountInformationRepo.findById(1)).willReturn(Optional.of(accountInformation));
        assertThatThrownBy(()->transactionService.withdrawAmount(withdrawalDto,1)).isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("01, transaction failed due to insufficient balance");
    }

     @Test
     void transactionIsSuccessfulIfWithDrawalAmountIsLessThanBalance(){
        WithdrawalDto withdrawalDto= new WithdrawalDto(12345.00,TransactionType.WITHDRAWAL,"2022-02-12");
        AccountInformation accountInformation= new AccountInformation(1,TransactionType.DEPOSIT,LocalDate.now(),15000.00,10000.00,12345L,12345L);
        given(accountInformationRepo.findById(1)).willReturn(Optional.of(accountInformation));
        transactionService.withdrawAmount(withdrawalDto,1);
         AccountInformation expected= new AccountInformation(1,TransactionType.WITHDRAWAL,LocalDate.of(2022,02,12),15000.00-12345,12345.00,null,null);
        verify(accountInformationRepo).save(expected);
     }
    @Test
    void itThrowsExceptionWhenBalanceIsInsufficient() {
   AccountInformation accountInformation= new AccountInformation(1,TransactionType.TRANSFER,LocalDate.now(),0.00,10000.00,12345L,12345L);
    when(accountInformationRepo.findById(1)).thenReturn(Optional.of(accountInformation));
        assertThatThrownBy(()->transactionService.transferAmount(new TransferDto(
                1000.00,2344L,
                11111L,TransactionType.TRANSFER,
                "2022-09-21"),accountInformation.getId())).isInstanceOf(ApiRequestException.class).hasMessageContaining("01, transaction failed due to insufficient balance");
    }

    @Test
    void throwsExceptionWhenAttemptingToDeleteWithNoTransactionPresent() {
        AccountInformation accountInformation = new AccountInformation(1, TransactionType.DEPOSIT, LocalDate.now(), 0.00, 10000.00, 12345L, 12345L);

        accountInformationRepo.delete(accountInformation);
        given(accountInformationRepo.findById(2)).willReturn(Optional.empty());
        assertThatThrownBy(()->transactionService.deleteTransaction(2)).isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("01, No such account information exist");


        verify(accountInformationRepo).delete(accountInformation);
    }

    @Test
    void throwsInsufficientFundsIfWithDrawalAmountIsGreaterThanBalance() {
        WithdrawalDto withdrawalDto= new WithdrawalDto(12345.00,TransactionType.WITHDRAWAL,"2022-02-12");
        AccountInformation accountInformation= new AccountInformation(1,TransactionType.DEPOSIT,LocalDate.now(),1000.00,10000.00,12345L,12345L);
        given(accountInformationRepo.findById(1)).willReturn(Optional.of(accountInformation));
        assertThatThrownBy(()->transactionService.withdrawAmount(withdrawalDto,1)).isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("01, transaction failed due to insufficient balance");
    }

    @Test
    public void shouldThrowAnExceptionIfTransferAmountIsGreaterThanBalance(){
        AccountInformation accountInformation= new AccountInformation(1,TransactionType.DEPOSIT,
                LocalDate.now(),1000.00,
                10000.00,12345L,12345L);

        TransferDto transferDto= new TransferDto(2000.00,12345L,23455L,
                TransactionType.TRANSFER,"2022-02-09");

        given(accountInformationRepo.findById(1)).willReturn(Optional.of(accountInformation));
        assertThatThrownBy(()->transactionService.transferAmount(transferDto,1))
                .isInstanceOf(ApiRequestException.class).hasMessageContaining("01, transaction failed due to insufficient balance");
    }

    @Test
    void shouldTransferIfBalanceIsGreaterThanTransferAmount() {
        TransferDto transferDto= new TransferDto(2000.00,12345L,23455L,
                TransactionType.TRANSFER,"2022-02-09");

        AccountInformation accountInformation= new AccountInformation(1,TransactionType.DEPOSIT,
                LocalDate.now(),5000.00,
                1000.00,12345L,12345L);

        given(accountInformationRepo.findById(1)).willReturn(Optional.of(accountInformation));

        transactionService.transferAmount(transferDto,1);
        Double expected= accountInformation.getBalance()-transferDto.getAmount();
        AccountInformation accountInformation1= new AccountInformation(
                1,TransactionType.TRANSFER,
                LocalDate.of(2022,02,9),expected,
                transferDto.getAmount(),12345L,23455L
        );
        verify(accountInformationRepo).save(accountInformation1);
    }


    @Test
    @Disabled
    public void shouldDeleteTransactionIfTransactionDetailsPresent(){

        AccountInformation accountInformation= new AccountInformation(1,TransactionType.DEPOSIT,
                LocalDate.now(),5000.00,
                1000.00,12345L,12345L);

        UserDto userDto= new UserDto("Mike","Egbe-iyon", LocalDate.of(2022,1,4),"Male",12345L
        );

        User user= new User();
        user.setUserId(1);
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setAccountNo(userDto.getAccountNo());
        user.setAccountInformation(accountInformation);
        user.setGender(userDto.getGender());
        user.setDateOfBirth(userDto.getDateOfBirth());
        given(userRepository.findById(1)).willReturn(Optional.of(user));

        given(accountInformationRepo.findById(1)).willReturn(Optional.of(accountInformation));

        transactionService.deleteTransaction(1);
        verify(accountInformationRepo).delete(accountInformation);
    }
    @Test
    void wouldUpdateTransaction(){

        UpdateTransactionDto updateTransactionDto= new UpdateTransactionDto(TransactionType.DEPOSIT,LocalDate.of(2022,01,21),1000.00,1900.00,12345L,12345L);

        AccountInformation accountInformation= new AccountInformation(1,TransactionType.DEPOSIT,
                LocalDate.now(),5000.00,
                1000.00,12345L,12345L);

        given(accountInformationRepo.findById(1)).willReturn(Optional.of(accountInformation));

        transactionService.updateSavedTransaction(updateTransactionDto,1);

        verify(accountInformationRepo).save(accountInformation);


    }
}