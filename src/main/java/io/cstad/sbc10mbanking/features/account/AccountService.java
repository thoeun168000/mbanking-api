package io.cstad.sbc10mbanking.features.account;

import io.cstad.sbc10mbanking.features.account.dto.*;
import org.springframework.data.domain.Page;

public interface AccountService {

    void softDeleteAccount(String actNo);

    AccountResponse updateByAlias(String alias, AccountUpdateRequest accountUpdateRequest);

    void deleteByActNo(String actNo);

    void updateTransferLimit(String actNo, AccountTransferLimitRequest accountTransferLimitRequest);

    void hideAccount(String actNo);

    AccountResponse renameAccount(String actNo, AccountRenameRequest accountRenameRequest);

    /**
     * Create a New Accounts
     * @param accountCreateRequest {@link AccountCreateRequest}
     * @return {@link AccountResponse}
     */

    // Save New Accounts
    AccountResponse createNew(AccountCreateRequest accountCreateRequest);

    /**
     * Find All Account by pagination
     * @param pageNumber is current page request from client
     * @param pageSize is size of records per page from client
     * @return {@link Page<AccountResponse>}
     */
    // Find Accounts
    Page<AccountResponse> findList(int pageNumber, int pageSize);

    /**
     * Find Account By ActNo
     * @param actNo is no of account
     * @return {@link AccountResponse}
     */
    // Find Accounts By ActNo
    AccountResponse findByActNo(String actNo);
}
