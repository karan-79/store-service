package com.rotikhao.storemanagement.dao;

import com.rotikhao.storemanagement.BaseIntegrationTests;
import com.rotikhao.storemanagement.TestData;
import com.rotikhao.storemanagement.models.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemsDaoTest extends BaseIntegrationTests {

    @Autowired
    ItemsDao itemsDao;
    @Autowired
    TestData testData;

    @Test
    void getAllItems() {
        var items = itemsDao.getAllItems(storeId);
        assertFalse(items.isEmpty());
    }

    @Test
    void createItem() {
        var id = itemsDao.createItem(testData.getCreateItemRequest(), storeId);
        var items = itemsDao.getAllItems(storeId);
        var item = items.stream().filter(i -> i.getId() == id).findAny();
        assertTrue(item.isPresent());
    }

    @Test
    void updateItem() {
        assertDoesNotThrow(() -> itemsDao.updateItem(testData.getTestItem(storeId), storeId));
    }

    @Test
    void deleteItem() {
        assertDoesNotThrow(()->itemsDao.deleteItem(2, storeId));
    }

    @Test
    void deleteFromCategory() {
        assertDoesNotThrow(()->itemsDao.deleteAllInCategory(2, storeId));
    }
}