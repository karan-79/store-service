package com.rotikhao.storemanagement.api.v1.web;

import com.rotikhao.storemanagement.BaseIntegrationTests;
import com.rotikhao.storemanagement.TestData;
import com.rotikhao.storemanagement.api.v1.web.models.CreateStoreRequest;
import com.rotikhao.storemanagement.dao.StoreDAO;
import com.rotikhao.storemanagement.models.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class StoreControllerTests extends BaseIntegrationTests {

    @Autowired
    TestData testData;

    @Autowired
    StoreDAO storeDAO;

    @Test
    void getAllStores() {
        var resp = restTemplate.getForObject(makePath("/v1/stores"), Store[].class);
        Assertions.assertTrue(resp.length > 0);
    }

    @Test
    void getStoreById() {
        var storeId = storeDAO.create(testData.getCreateStoreRequest(), ownerID.toString());
        var resp = restTemplate.getForObject(makePath("/v1/stores/" + storeId.toString()), Store.class);
        Assertions.assertNotNull(resp);
    }

    @Test
    void postStore() {
        var req = new CreateStoreRequest("shahi dhaba", "late roti milugi", "soor farm de samne");
        var resp = restTemplate.postForObject(makePath("/v1/stores"), req, Store.class);
        Assertions.assertNotNull(resp);
        assertEquals(resp.getStoreName(), req.getName());
    }

    @Test
    void putStore() {
        var storeId = storeDAO.create(testData.getCreateStoreRequest(), ownerID.toString());
        var req = new HttpEntity<>(new CreateStoreRequest("Delhi dhaba", "abssss", "park de samne"));
        var resp = restTemplate.exchange(makePath("/v1/stores/" + storeId), HttpMethod.PUT, req, Object.class);
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
    }
}
