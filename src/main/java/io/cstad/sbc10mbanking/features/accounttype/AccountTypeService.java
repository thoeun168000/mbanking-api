package io.cstad.sbc10mbanking.features.accounttype;

import io.cstad.sbc10mbanking.features.accounttype.dto.AccountTypeRequest;
import io.cstad.sbc10mbanking.features.accounttype.dto.AccountTypeResponse;
import io.cstad.sbc10mbanking.features.accounttype.dto.AccountTypeUpdateRequest;

import java.util.List;

public interface AccountTypeService{

    AccountTypeResponse findByAlias(String alias);

    void deleteByAlias(String alias);

    AccountTypeResponse updateByAlias(String alias, AccountTypeUpdateRequest accountTypeUpdateRequest);

    void createNew(AccountTypeRequest accountTypeRequest);

    List<AccountTypeResponse> findList();

}
