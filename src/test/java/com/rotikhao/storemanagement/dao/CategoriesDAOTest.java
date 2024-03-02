package com.rotikhao.storemanagement.dao;

import com.rotikhao.storemanagement.BaseIntegrationTests;
import com.rotikhao.storemanagement.TestData;
import com.rotikhao.storemanagement.api.v1.web.models.APIItemCategory;
import com.rotikhao.storemanagement.api.v1.web.models.CreateCategoryRequest;
import com.rotikhao.storemanagement.service.ItemService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.rotikhao.storemanagement.utils.UUIDUtils.uuid;
import static org.junit.jupiter.api.Assertions.*;

class CategoriesDAOTest extends BaseIntegrationTests {
    @Autowired
    TestData testData;

    @Autowired
    CategoriesDAO categoriesDAO;

    @Autowired
    ItemService itemService;

    @Test
    void getAllCategories() {
        var categories = categoriesDAO.getAllCategories(storeId);
        assertFalse(categories.isEmpty());
    }

    @Test
    void createCategory() {
        var req = new CreateCategoryRequest("Extras");
        var id = categoriesDAO.createItemCategory(req, storeId);
        var categories = categoriesDAO.getAllCategories(storeId);
        var item = categories.stream().filter(i->i.getId() == id).findAny();
        assertTrue(item.isPresent());
    }

    @Test
    void updateCategory() {
        var req = new APIItemCategory(1, "Special");
        assertDoesNotThrow(() -> categoriesDAO.updateCategory(req, storeId));
    }

    @Test
    void deleteCategory() {
        assertDoesNotThrow(() -> {
            int category = 1;
            itemService.deleteFromCategory(category, storeId);
            categoriesDAO.deleteCategory(1, storeId);
        });
    }

}