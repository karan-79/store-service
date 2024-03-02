package com.rotikhao.storemanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Item {
    int id;
    UUID storeId;
    int categoryId;
    String name;
    String description;
    BigDecimal price;
    boolean availability;
}
