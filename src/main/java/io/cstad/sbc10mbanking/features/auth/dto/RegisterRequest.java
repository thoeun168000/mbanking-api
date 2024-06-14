package io.cstad.sbc10mbanking.features.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(

        @NotBlank(message = "Phone number is required")
        String phoneNumber,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Pin is required")
        String pin,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Confirmed Password is required")
        String confirmedPassword,

        @NotBlank(message = "National card is required")
        String nationalCardId,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Gender is required")
        String gender,

        @NotNull(message = "Accept Term must is required")
        Boolean acceptTerm
) {
}
