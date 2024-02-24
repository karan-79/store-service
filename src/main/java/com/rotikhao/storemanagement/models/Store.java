package com.rotikhao.storemanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Store {
    UUID id;
    String storeName;
    String description;
    UUID ownerId;
    String location;
    LocalDate createdOn;
    LocalDateTime lastUpdated;
}
