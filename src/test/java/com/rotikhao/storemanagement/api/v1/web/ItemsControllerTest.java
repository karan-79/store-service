package com.rotikhao.storemanagement.api.v1.web;

import com.rotikhao.storemanagement.BaseIntegrationTests;
import com.rotikhao.storemanagement.TestData;
import com.rotikhao.storemanagement.models.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ItemsControllerTest extends BaseIntegrationTests {

    @Autowired
    TestData testData;

    private String getItemsURL() {
        return makePath("/v1/" + storeId.toString() + "/items");
    }

    @Test
    void getAll() {
        var res = restTemplate.getForObject(getItemsURL(), Item[].class);
        assertTrue(res.length > 0);
    }

    @Test
    void getSearchedItems() {
        var res = restTemplate.getForObject(getItemsURL() + "/search?q=paneer", Item[].class);
        assertTrue(res.length > 0);
    }

    @Test
    void create() {
        var res = restTemplate.postForEntity(
                getItemsURL(),
                testData.getCreateItemRequest(), Item.class);
        assertTrue(res.getStatusCode().is2xxSuccessful());
        assertNotNull(res.getBody());
    }

    @Test
    void update() {
        assertDoesNotThrow(() -> restTemplate.put(getItemsURL(),
                testData.getTestItem(storeId)));
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> restTemplate.delete(getItemsURL() + "/1"));
    }

}