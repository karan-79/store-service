package com.rotikhao.storemanagement.dao;

import com.rotikhao.storemanagement.BaseIntegrationTests;
import com.rotikhao.storemanagement.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.rotikhao.storemanagement.utils.UUIDUtils.uuid;

class StoreDAOTests extends BaseIntegrationTests {

    @Autowired
    TestData testData;

    @Autowired
    StoreDAO storeDAO;


    @Test
    void createStoreTests() {

        var req = testData.getCreateStoreRequest();
        var uuid = storeDAO.create(req, uuid("640b238f-a85e-40ce-b754-9dd7607469bc"));
        var savedStore = storeDAO.getStoreById(uuid, uuid("640b238f-a85e-40ce-b754-9dd7607469bc")).orElseThrow();

        Assertions.assertEquals(req.getName(), savedStore.getStoreName());
    }

    @Test
    void getAllStores() {
        var stores = storeDAO.getAllStores(uuid("640b238f-a85e-40ce-b754-9dd7607469bc"));
        Assertions.assertFalse(stores.isEmpty());
    }

    @Test
    void getStoreById() {
        var req = testData.getCreateStoreRequest();
        var uuid = storeDAO.create(req, uuid("640b238f-a85e-40ce-b754-9dd7607469bc"));
        var store = storeDAO.getStoreById(uuid, uuid("640b238f-a85e-40ce-b754-9dd7607469bc"));
        Assertions.assertTrue(store.isPresent());
    }

    @Test
    void updateStore() {
        Assertions.assertDoesNotThrow(() -> storeDAO.updateStore(testData.getStoreData()));
    }


}
