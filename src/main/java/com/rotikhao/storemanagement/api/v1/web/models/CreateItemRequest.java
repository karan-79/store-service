package com.rotikhao.storemanagement.api.v1.web.models;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record CreateItemRequest(
        @NotEmpty String name,
        @NotEmpty BigDecimal price,
        @NotEmpty int categoryId,
        String description) {
}
