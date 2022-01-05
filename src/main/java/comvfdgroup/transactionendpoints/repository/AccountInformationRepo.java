package comvfdgroup.transactionendpoints.repository;

import comvfdgroup.transactionendpoints.model.AccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountInformationRepo extends JpaRepository<AccountInformation,Integer> {
Optional<AccountInformation> findById(Integer accountId);
}
