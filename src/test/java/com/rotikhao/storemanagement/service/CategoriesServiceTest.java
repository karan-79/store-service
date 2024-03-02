package com.rotikhao.storemanagement.service;

import com.rotikhao.storemanagement.BaseIntegrationTests;
import com.rotikhao.storemanagement.TestData;
import com.rotikhao.storemanagement.api.v1.web.models.APIItemCategory;
import com.rotikhao.storemanagement.api.v1.web.models.CreateCategoryRequest;
import com.rotikhao.storemanagement.api.v1.web.models.CreateStoreRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.*;

class CategoriesServiceTest extends BaseIntegrationTests {

    @Autowired
    TestData testData;

    private String getCategoriesURL() {
        return makePath("/v1/" + storeId.toString() + "/categories");
    }


    @Test
    void getAll() {
        var res = restTemplate.getForEntity(getCategoriesURL(), APIItemCategory[].class);
        assertTrue(res.getStatusCode().is2xxSuccessful());
        assertNotNull(res.getBody());
    }

    @Test
    void createItemCategory() {
        var res = restTemplate.postForEntity(getCategoriesURL(),
                new CreateCategoryRequest("Shakes"), APIItemCategory.class);
        assertTrue(res.getStatusCode().is2xxSuccessful());
        assertNotNull(res.getBody());
    }


    @Test
    void update() {
        assertDoesNotThrow(() -> restTemplate.put(getCategoriesURL(), new CreateCategoryRequest("Thandi items")));
    }

    @Test
    void deleteCategory() {
        assertDoesNotThrow(() -> restTemplate.delete(getCategoriesURL() + "/1"));
    }
}