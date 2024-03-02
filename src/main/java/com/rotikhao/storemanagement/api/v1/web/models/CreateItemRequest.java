package com.rotikhao.storemanagement.api.v1.web.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemRequest {
        @NotEmpty String name;
        @NotEmpty BigDecimal price;
        @NotEmpty int categoryId;
        String description;
}
