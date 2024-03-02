package com.rotikhao.storemanagement.api.v1.web;

import com.rotikhao.storemanagement.api.v1.web.models.APIItemCategory;
import com.rotikhao.storemanagement.api.v1.web.models.CreateCategoryRequest;
import com.rotikhao.storemanagement.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/{storeId}/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService categoriesService;

    private UUID owner = UUID.fromString("640b238f-a85e-40ce-b754-9dd7607469bc");

    @GetMapping
    public List<APIItemCategory> getAll(@PathVariable("storeId") UUID storeId) {
        return categoriesService.getAll(storeId);
    }

    @PostMapping
    public APIItemCategory post(@PathVariable("storeId") UUID storeId, @RequestBody CreateCategoryRequest req) {
        return categoriesService.createItemCategory(storeId, req);
    }

    @PutMapping
    public void update(@PathVariable("storeId") UUID storeId, @RequestBody APIItemCategory itemCategory) {
        categoriesService.update(itemCategory, storeId);
    }

    @DeleteMapping("/{cid}")
    public void deleteCategory(@PathVariable("storeId") UUID storeId, @PathVariable("cid") int categoryId) {
        categoriesService.deleteCategory(categoryId, storeId);
    }
}
