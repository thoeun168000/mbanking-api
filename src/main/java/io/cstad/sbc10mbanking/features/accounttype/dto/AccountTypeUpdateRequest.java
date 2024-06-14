package io.cstad.sbc10mbanking.features.accounttype.dto;

public record AccountTypeUpdateRequest(
        String description,
        Boolean isDeleted
) {
}
