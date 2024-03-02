package com.rotikhao.storemanagement.service;

import com.rotikhao.storemanagement.api.v1.web.models.APIItemCategory;
import com.rotikhao.storemanagement.api.v1.web.models.CreateCategoryRequest;
import com.rotikhao.storemanagement.dao.CategoriesDAO;
import com.rotikhao.storemanagement.models.ItemCategory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoriesService {

    private CategoriesDAO categoriesDAO;
    private ItemService itemService;

    public List<APIItemCategory> getAll(UUID storeId) {
        return categoriesDAO.getAllCategories(storeId)
                .stream().map(item -> new APIItemCategory(item.getId(), item.getName()))
                .toList();
    }

    public APIItemCategory createItemCategory(UUID storeId, CreateCategoryRequest categoryRequest) {
        var id = categoriesDAO.createItemCategory(categoryRequest, storeId);
        return new APIItemCategory(id, categoryRequest.getName());
    }

    public void update(APIItemCategory itemCategory, UUID storeId) {
        categoriesDAO.updateCategory(itemCategory, storeId);
    }

    @Transactional
    public void deleteCategory(int categoryId, UUID storeId) {
       itemService.deleteFromCategory(categoryId,storeId);
       categoriesDAO.deleteCategory(categoryId, storeId);
    }
}
