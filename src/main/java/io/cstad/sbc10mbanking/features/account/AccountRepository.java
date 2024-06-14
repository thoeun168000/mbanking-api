package io.cstad.sbc10mbanking.features.account;

import io.cstad.sbc10mbanking.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByAlias(String alias);

    Optional<Account> findByActNo(String actNo);

    // SELECT EXISTS(SELECT * FROM accounts WHERE act_no = ?)
    Boolean existsByActNo(String actNo);

}
