package io.cstad.sbc10mbanking.features.account;

import io.cstad.sbc10mbanking.domain.Account;
import io.cstad.sbc10mbanking.domain.AccountType;
import io.cstad.sbc10mbanking.domain.User;
import io.cstad.sbc10mbanking.features.account.dto.*;
import io.cstad.sbc10mbanking.features.accounttype.AccountTypeRepository;
import io.cstad.sbc10mbanking.features.user.UserRepository;
import io.cstad.sbc10mbanking.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;

    //Call to by AccountMapper
    private final AccountMapper accountMapper;

    @Override
    public void softDeleteAccount(String actNo) {
        // Validate account delete soft
        Account account = accountRepository
                .findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account soft delete not found"
                ));
        account.setIsDeleted(true);
        accountRepository.save(account);
    }

    @Override
    public AccountResponse updateByAlias(String alias, AccountUpdateRequest accountUpdateRequest) {

        // Validate alias
        Account account = accountRepository
                .findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account alias has not been found."
                ));
        log.info("Updating account before: {}, {}", account.getActNo(), account.getBalance());
        accountMapper.fromAccountUpdateRequest(accountUpdateRequest, account);
        log.info("Updating account after: {}, {}", account.getActNo(), account.getBalance());

        account = accountRepository.save(account);

        return accountMapper.toAccountResponse(account);
    }

    @Override
    public void deleteByActNo(String actNo) {
        // Validate alias
        Account account = accountRepository
                .findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account deleted by alias has not been found."
                ));
        accountRepository.delete(account);
    }

    @Override
    public void updateTransferLimit(String actNo, AccountTransferLimitRequest accountTransferLimitRequest) {
        // Validate account transfer limit
        Account account = accountRepository
                .findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus
                        .NOT_FOUND,
                        "Account transfer limit is not found"
                ));
        account.setTransferLimit(accountTransferLimitRequest.amount());
        accountRepository.save(account);
    }

    @Override
    public void hideAccount(String actNo) {
        // Validate hide account
        Account account = accountRepository
                .findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus
                        .NOT_FOUND,
                        "Hide Account is not found"
                ));
        account.setIsHidden(true);
        accountRepository.save(account);
    }

    @Override
    public AccountResponse renameAccount(String actNo, AccountRenameRequest accountRenameRequest) {

        // Validate account
        Account account = accountRepository
                .findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found."
                ));
        account.setAlias(accountRenameRequest.alias());
        account = accountRepository.save(account);

        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponse createNew(AccountCreateRequest accountCreateRequest) {

        // Validate account type
        AccountType accountType = accountTypeRepository
                .findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account Type has not been found."
                ));

        // Validate User
        User user = userRepository
                .findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User has not been found."
                ));

        // Validate account no
        if (accountRepository.existsByActNo(accountCreateRequest.actNo())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Account no has already been existed"
            );
        }

        // Validate balance
        if (accountCreateRequest.balance().compareTo(BigDecimal.valueOf(10)) < 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Balance 10$ is required to create account."
            );
        }

        // Transfer DTO to Domain Model
        Account account = accountMapper.fromAccountCreateRequest(accountCreateRequest);
//        account.setActNo(accountCreateRequest.actNo());
//        account.setBalance(accountCreateRequest.balance());
        account.setAccountType(accountType);
        account.setUser(user);

        // System generate data
        account.setActName(user.getName());
        account.setIsHidden(false);
        account.setTransferLimit(BigDecimal.valueOf(1000)); // transfer money 1000$

        // save account into database and get latest data back
        account = accountRepository.save(account);

        //Transfer Domain to DTO
        return accountMapper.toAccountResponse(account);

//        return AccountResponse.builder()
//                .alias(account.getAlias())
//                .actName(account.getActName())
//                .actNo(account.getActNo())
//                .balance(account.getBalance())
//                .accountTypeAlias(account.getAccountType().getName())
//                .build();

    }

    @Override
    public Page<AccountResponse> findList(int pageNumber, int pageSize) {

        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        Page<Account> accounts = accountRepository.findAll(pageRequest);

        return accounts.map(accountMapper::toAccountResponse);
    }


    @Override
    public AccountResponse findByActNo(String actNo) {

        //Validate account no
        Account account = accountRepository
                .findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found."
                ));

        return accountMapper.toAccountResponse(account);
    }
}
