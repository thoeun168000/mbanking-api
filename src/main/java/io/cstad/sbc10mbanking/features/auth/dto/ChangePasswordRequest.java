package io.cstad.sbc10mbanking.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(

        @NotBlank(message = "Old Password is required")
        String oldPassword,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Confirmed Password is required")
        String confirmedPassword
) {
}
