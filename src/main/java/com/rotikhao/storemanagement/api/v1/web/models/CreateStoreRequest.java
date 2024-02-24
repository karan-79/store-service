package com.rotikhao.storemanagement.api.v1.web.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class CreateStoreRequest {
    @NotEmpty
    String name;
    String description;
    String location;
}
