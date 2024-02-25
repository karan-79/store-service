package com.rotikhao.storemanagement.dao;

import com.rotikhao.storemanagement.BaseIntegrationTests;
import com.rotikhao.storemanagement.TestData;
import com.rotikhao.storemanagement.api.v1.web.models.APIItemCategory;
import com.rotikhao.storemanagement.api.v1.web.models.CreateCategoryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Component
class CategoriesDAOTest extends BaseIntegrationTests {
    String storeId = "e3ac9673-0629-4df6-89d4-5199f527bfd4"; // same id used in data.sql for mock data
    @Autowired
    TestData testData;

    @Autowired
    CategoriesDAO categoriesDAO;

    @Test
    void getAllCategories() {
        var categories = categoriesDAO.getAllCategories(storeId);
        assertFalse(categories.isEmpty());
    }

    @Test
    @ConditionalOnProperty(name = "db", havingValue = "postgres")
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

//    @Test
    void deleteCategory() {

        assertDoesNotThrow(() -> categoriesDAO.deleteCategory(1, storeId));
    }

}