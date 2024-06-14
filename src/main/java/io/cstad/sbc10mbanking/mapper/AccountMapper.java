package io.cstad.sbc10mbanking.mapper;

import io.cstad.sbc10mbanking.domain.Account;
import io.cstad.sbc10mbanking.features.account.dto.AccountCreateRequest;
import io.cstad.sbc10mbanking.features.account.dto.AccountResponse;
import io.cstad.sbc10mbanking.features.account.dto.AccountUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromAccountUpdateRequest(AccountUpdateRequest accountUpdateRequest,
                                  @MappingTarget Account account);
    Account fromAccountUpdateRequest(AccountUpdateRequest accountUpdateRequest);

    // Map Account to AccountResponse
    // Source = Account
    // Target = AccountResponse
    // @Mapping(source = "accountType.alias", target = "accountTypeAlias") // ues with String to open Mapping at to accountResponse
    AccountResponse toAccountResponse(Account account);

    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);
}
