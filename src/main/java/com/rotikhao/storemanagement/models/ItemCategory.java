package com.rotikhao.storemanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ItemCategory {
    int id;
    UUID storeId;
    String name;
}
