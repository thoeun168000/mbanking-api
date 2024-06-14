package io.cstad.sbc10mbanking.features.account.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountRenameRequest(
        @NotBlank(message = "Alias is required")
        String alias
) {
}
