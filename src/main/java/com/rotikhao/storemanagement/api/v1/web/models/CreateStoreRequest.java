package com.rotikhao.storemanagement.api.v1.web.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateStoreRequest {
    @NotEmpty
    String name;
    String description;
    String location;
}
