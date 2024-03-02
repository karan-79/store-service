package com.rotikhao.storemanagement.api.v1.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    @JsonProperty String name;
}
