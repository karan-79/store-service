package com.rotikhao.storemanagement.models;

import com.rotikhao.storemanagement.api.v1.web.models.CreateCategoryRequest;
import com.rotikhao.storemanagement.api.v1.web.models.CreateItemRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @NotEmpty int id;
    UUID storeId;
    int categoryId;
    String name;
    String description;
    BigDecimal price;
    boolean availability;

    public Item(int id, UUID storeId, CreateItemRequest request) {
        this(id, storeId, request.getCategoryId(), request.getName(), request.getDescription(), request.getPrice(), true); // availability is true in create
    }
}
