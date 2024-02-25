package com.rotikhao.storemanagement.utils;

import com.rotikhao.storemanagement.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;


class DbUtilsTest{

    TestData testData;

    @Test
    void updateIfChanged() {
        testData = new TestData();
        var req = testData.getCreateStoreRequest();
        var store = testData.getStoreData();

        DbUtils.updateIfChanged(store::getStoreName, req::getName, store::setStoreName);
        assertEquals(store.getStoreName(), req.getName());
    }

}