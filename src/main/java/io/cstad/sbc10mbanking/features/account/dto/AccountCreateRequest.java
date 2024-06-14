package io.cstad.sbc10mbanking.features.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AccountCreateRequest(

    @NotBlank(message = "Account no is required.")
    String actNo,

    @NotNull(message = "Balance is required.")
    @Positive
    BigDecimal balance,

    @NotBlank(message = "Account Type alias is required.")
    String accountTypeAlias,

    @NotBlank(message = "Account Owner is required.")
    String userUuid
) {
}
