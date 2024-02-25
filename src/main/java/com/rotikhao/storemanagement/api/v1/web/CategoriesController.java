package com.rotikhao.storemanagement.api.v1.web;

import com.rotikhao.storemanagement.api.v1.web.models.APIItemCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/{storeId}/categories")
public class CategoriesController {

    private UUID owner = UUID.fromString("640b238f-a85e-40ce-b754-9dd7607469bc");
    @GetMapping
    public List<APIItemCategory> getAll(@PathVariable("storeId") UUID storeId){
        return null;
    }
}
