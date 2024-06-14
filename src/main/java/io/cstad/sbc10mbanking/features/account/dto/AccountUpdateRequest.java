package io.cstad.sbc10mbanking.features.account.dto;

import java.math.BigDecimal;

public record AccountUpdateRequest(

    String actName,
    String actNo,
    BigDecimal balance

) {
}
