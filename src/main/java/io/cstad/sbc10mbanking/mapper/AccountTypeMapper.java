package io.cstad.sbc10mbanking.mapper;

import io.cstad.sbc10mbanking.domain.AccountType;
import io.cstad.sbc10mbanking.features.accounttype.dto.AccountTypeRequest;
import io.cstad.sbc10mbanking.features.accounttype.dto.AccountTypeResponse;
import io.cstad.sbc10mbanking.features.accounttype.dto.AccountTypeUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {

    AccountTypeResponse toAccountTypeResponse(AccountType accountType);

    // Partially map
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromAccountTypeUpdateRequest(AccountTypeUpdateRequest accountTypeUpdateRequest
            , @MappingTarget AccountType accountType);

    AccountType fromAccountTypeUpdateRequest(AccountTypeUpdateRequest accountTypeUpdateRequest);

    AccountType fromAccountTypeRequest(AccountTypeRequest accountTypeRequest);

    List<AccountTypeResponse> toAccountTypeResponseList(List<AccountType> accountTypes);

}
