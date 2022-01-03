package comvfdgroup.transactionendpoints.service;

import comvfdgroup.transactionendpoints.model.*;

import comvfdgroup.transactionendpoints.repository.AccountInformationRepo;
import comvfdgroup.transactionendpoints.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private AccountInformationRepo accountInformationRepo;

    @Autowired
    private UserRepository userRepository;

    public void depositToAccount(DepositDto depositDto, Integer userId) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //dd MMM uuuu
        User user = new User();
        user.setUserId(userId);

        AccountInformation accountInformation = new AccountInformation();
        accountInformation.setTransactionAmount(depositDto.getAmount());
        accountInformation.setTransactionDate(LocalDate.parse(depositDto.getTransactionDate()));
        accountInformation.setTransactionType(depositDto.getTransactionType());
        accountInformation.setDepositorAccountNo(depositDto.getDepositorAccountNo());
        accountInformation.setRecipientAccountNo(depositDto.getDepositorAccountNo());


        if (userRepository.findById(userId).get().getAccountInformation() == null) {
            accountInformation.setBalance(0.00);
            accountInformation.setBalance(depositDto.getAmount());
        } else {
            accountInformation.setBalance(userRepository.findById(userId).get().getAccountInformation().getBalance() + depositDto.getAmount());
        }

        user.setAccountInformation(accountInformation);

        if(userRepository.findById(userId).get().getAccountInformation() == null) {
            accountInformationRepo.save(accountInformation);
        }

        if(userRepository.findById(userId).get().getAccountInformation() != null){
            accountInformation.setId(userRepository.findById(userId).get().getAccountInformation().getId());
            accountInformationRepo.save(accountInformation);
        }

        if (userRepository.findById(user.getUserId()).isPresent()) {
            user.setAccountNo(userRepository.findById(userId).get().getAccountNo());
            user.setFirstName(userRepository.findById(userId).get().getFirstName());
            user.setLastName(userRepository.findById(userId).get().getLastName());
            user.setDateOfBirth(userRepository.findById(userId).get().getDateOfBirth());
            user.setGender(userRepository.findById(userId).get().getGender());

            if (userRepository.findById(userId).get().getAccountInformation()== null) {
                userRepository.save(user);
            }
        }
    }

    public void withdrawAmount(WithdrawalDto withdrawalDto, Integer accountId) {


        if (accountInformationRepo.findById(accountId).get().getBalance() == 0.00)
            throw new IllegalStateException("Insufficient balance");

        if (accountInformationRepo.findById(accountId).get().getBalance() > 0.00) {
            AccountInformation accountInformation = new AccountInformation();
            accountInformation.setId(accountId);

            accountInformation.setBalance(accountInformationRepo.findById(accountId).get().getBalance() - withdrawalDto.getAmount());
            accountInformation.setTransactionType(withdrawalDto.getTransactionType());
            accountInformation.setTransactionAmount(withdrawalDto.getAmount());
            // accountInformation.setBalance((accountInformationRepo.findAccountInformationById(accountId)).getBalance()-transferDto.getAmount());
            accountInformation.setDepositorAccountNo(null);
            accountInformation.setRecipientAccountNo(null);
            accountInformation.setTransactionDate(LocalDate.parse(withdrawalDto.getTransactionDate()));

            accountInformationRepo.save(accountInformation);


        }

    }

    public void transferAmount(TransferDto transferDto, Integer accountId) {
        if (accountInformationRepo.findById(accountId).get().getBalance() == 0.00)
            throw new IllegalStateException("Insufficient balance");

        if (accountInformationRepo.findById(accountId).get().getBalance() > 0.00) {
            AccountInformation accountInformation = new AccountInformation();
            accountInformation.setId(accountId);

            accountInformation.setBalance(accountInformationRepo.findById(accountId).get().getBalance() - transferDto.getAmount());
            accountInformation.setTransactionType(transferDto.getTransactionType());
            accountInformation.setTransactionAmount(transferDto.getAmount());
            accountInformation.setDepositorAccountNo(transferDto.getDepositorAccountNo());
            accountInformation.setRecipientAccountNo(transferDto.getRecipientAccountNo());
            accountInformation.setTransactionDate(LocalDate.parse(transferDto.getTransactionDate()));

            accountInformationRepo.save(accountInformation);


        }
    }

    public void deleteTransaction(Integer accountInfoId) {

        Optional<AccountInformation> accountInformation = accountInformationRepo.findById(accountInfoId);
        if (accountInformation.isEmpty()) {
            throw new IllegalStateException("No such transaction exist");
        }

        if (accountInformation.isPresent()) {
            User user = accountInformation.get().getUser();
            user.setAccountInformation(null);
            accountInformationRepo.delete(accountInformation.get());
        }
    }

    public void updateSavedTransaction(UpdateTransactionDto updateTransactionDto, Integer accountInfoId){
       Optional<AccountInformation> accountInformation= accountInformationRepo.findById(accountInfoId);
       if(accountInformation.isPresent()){
           accountInformation.get().setTransactionAmount(updateTransactionDto.getTransactionAmount());
           accountInformation.get().setTransactionDate(updateTransactionDto.getTransactionDate());
           accountInformation.get().setDepositorAccountNo(updateTransactionDto.getDepositorAccountNo());
           accountInformation.get().setRecipientAccountNo(updateTransactionDto.getRecipientAccountNo());
           accountInformation.get().setBalance(updateTransactionDto.getBalance());
           accountInformation.get().setTransactionType(updateTransactionDto.getTransactionType());
       }

       accountInformationRepo.save(accountInformation.get());

    }
}
