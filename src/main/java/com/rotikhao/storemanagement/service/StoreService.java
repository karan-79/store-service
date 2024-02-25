package com.rotikhao.storemanagement.service;

import com.rotikhao.storemanagement.api.v1.web.models.CreateStoreRequest;
import com.rotikhao.storemanagement.dao.StoreDAO;
import com.rotikhao.storemanagement.models.Store;
import com.rotikhao.storemanagement.utils.DbUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StoreService {

    private final StoreDAO storeDAO;

    public List<Store> getAllStore(UUID ownerId) {
        return storeDAO.getAllStores(ownerId.toString());
    }

    public Store createStore(CreateStoreRequest createStoreRequest, UUID ownerId) {
        var storeID = storeDAO.create(createStoreRequest, ownerId.toString());
        return storeDAO.getStoreById(storeID.toString(),ownerId.toString()).orElseThrow(()->new RuntimeException());
    }

    public Store getStoreById(UUID storeId, UUID ownerId) {
        return storeDAO.getStoreById(storeId.toString(), ownerId.toString()).orElseGet(null);
    }

    public void updateStore(CreateStoreRequest createStoreRequest, UUID storeId, UUID ownerId) {
        var oldStore = storeDAO.getStoreById(storeId.toString(), ownerId.toString()).orElseThrow(()->new RuntimeException());

        DbUtils.updateIfChanged(oldStore::getStoreName, createStoreRequest::getName, oldStore::setStoreName);
        DbUtils.updateIfChanged(oldStore::getDescription, createStoreRequest::getDescription, oldStore::setStoreName);
        DbUtils.updateIfChanged(oldStore::getLocation, createStoreRequest::getLocation, oldStore::setStoreName);

        storeDAO.updateStore(oldStore);
    }
}
