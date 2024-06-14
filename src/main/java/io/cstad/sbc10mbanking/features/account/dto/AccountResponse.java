package io.cstad.sbc10mbanking.features.account.dto;

import io.cstad.sbc10mbanking.features.accounttype.dto.AccountTypeResponse;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountResponse(
        String alias,
        String actName,
        String actNo,
        BigDecimal balance,
//        String accountTypeAlias
        AccountTypeResponse accountType
) {
}
